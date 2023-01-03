package dev.floofy.fluff;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.floofy.fluff.types.ClassDefinition;
import dev.floofy.fluff.types.FluffSyntaxTree;
import dev.floofy.fluff.types.InterfaceElement;
import dev.floofy.fluff.types.Package;
import jdk.javadoc.doclet.DocletEnvironment;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.*;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

class DefaultFluffImpl implements Fluff {
    /**
     * Analyzes the current Javadoc doclet environment and returns the {@link FluffSyntaxTree abstract syntax tree}
     * about the documentation that is being generated.
     */
    @Override
    public @NotNull FluffSyntaxTree analyzeAst(@NotNull DocletEnvironment environment) {
        // Get all the elements included in the doclet environment
        final Set<? extends Element> elements = environment.getIncludedElements();

        final List<Package> packages = elements
                .stream()
                .filter(s -> s.getKind() == ElementKind.PACKAGE)
                .map(s -> new Package((PackageElement)s))
                .toList();

        final List<ClassDefinition> classes = elements
                .stream()
                .filter(s -> s.getKind() == ElementKind.CLASS)
                .map(element -> new ClassDefinition((TypeElement)element))
                .toList();

        final List<InterfaceElement> interfaces = elements
                .stream()
                .filter(s -> s.getKind() == ElementKind.INTERFACE)
                .map(element -> new InterfaceElement((TypeElement) element))
                .toList();

        return new FluffSyntaxTree(packages, classes, interfaces);
    }

    /**
     * Analyzes the current {@link DocletEnvironment doclet environment} and returns an object that
     * can be writable from a writer.
     *
     * @param environment The doclet environment to analyze
     * @param writer      The {@link Writer writer} to write the object in.
     */
    @Override
    public <T extends Writer> void analyze(@NotNull DocletEnvironment environment, @NotNull T writer) throws IOException {
        analyze(environment, writer, new ObjectMapper());
    }

    /**
     * Analyzes the current {@link DocletEnvironment doclet environment} and writes the object into
     * the linked {@link Writer writer}.
     *
     * @param environment The doclet environment to analyze
     * @param writer      The {@link Writer writer} to write the object in.
     * @param mapper      The {@link ObjectMapper} to use to write in the {@link Writer writer}.
     */
    @Override
    public <T extends Writer> void analyze(@NotNull DocletEnvironment environment, @NotNull T writer, @NotNull ObjectMapper mapper) throws IOException {
        final FluffSyntaxTree ast = analyzeAst(environment);
        final JsonGenerator generator = mapper.createGenerator(writer);

        generator.writePOJO(ast);
    }
}
