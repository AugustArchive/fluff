package dev.floofy.fluff;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.floofy.fluff.types.FluffSyntaxTree;
import jdk.javadoc.doclet.DocletEnvironment;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Writer;

/**
 * Represents the main entrypoint
 */
public interface Fluff {
    /**
     * Creates a new {@link Fluff} instance.
     */
    static Fluff create() {
        return new DefaultFluffImpl();
    }

    /**
     * Analyzes the current Javadoc doclet environment and returns the {@link FluffSyntaxTree abstract syntax tree}
     * about the documentation that is being generated.
     */
    @NotNull
    FluffSyntaxTree analyzeAst(@NotNull DocletEnvironment environment);

    /**
     * Analyzes the current {@link DocletEnvironment doclet environment} and writes the object into
     * the linked {@link Writer writer}.
     *
     * @param environment The doclet environment to analyze
     * @param writer      The {@link Writer writer} to write the object in.
     */
    <T extends Writer> void analyze(@NotNull DocletEnvironment environment, @NotNull T writer) throws IOException;

    /**
     * Analyzes the current {@link DocletEnvironment doclet environment} and writes the object into
     * the linked {@link Writer writer}.
     *
     * @param environment The doclet environment to analyze
     * @param writer      The {@link Writer writer} to write the object in.
     * @param mapper      The {@link ObjectMapper} to use to write in the {@link Writer writer}.
     */
    <T extends Writer> void analyze(
            @NotNull DocletEnvironment environment,
            @NotNull T writer,
            @NotNull ObjectMapper mapper
    ) throws IOException;
}
