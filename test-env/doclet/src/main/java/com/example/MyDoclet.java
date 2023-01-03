package com.example;

import dev.floofy.fluff.Fluff;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import jdk.javadoc.doclet.StandardDoclet;

import javax.lang.model.SourceVersion;
import javax.print.Doc;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MyDoclet implements Doclet {
    private Reporter reporter;
    private Locale locale;

    /**
     * Initializes this doclet with the given locale and error reporter.
     * This locale will be used by the reporter and the doclet components.
     *
     * @param locale   the locale to be used
     * @param reporter the reporter to be used
     */
    @Override
    public void init(Locale locale, Reporter reporter) {
        reporter.print(Diagnostic.Kind.NOTE, "running with locale %s".formatted(locale));

        this.reporter = reporter;
        this.locale = locale;
    }

    /**
     * Returns a name identifying the doclet. A name is a simple identifier
     * without white spaces, as defined in <cite>The Java Language Specification</cite>,
     * section 6.2 "Names and Identifiers".
     *
     * @return name of the Doclet
     */
    @Override
    public String getName() {
        return "fluff";
    }

    /**
     * Returns all the supported options.
     *
     * @return a set containing all the supported options, an empty set if none
     */
    @Override
    public Set<? extends Option> getSupportedOptions() {
        final StandardDoclet doclet = new StandardDoclet();
        doclet.init(locale, reporter);

        return doclet.getSupportedOptions();
    }

    /**
     * Returns the version of the Java Programming Language supported
     * by this doclet.
     *
     * @return the language version supported by this doclet, usually
     * the latest version
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * The entry point of the doclet. Further processing will commence as
     * instructed by this method.
     *
     * @param environment from which essential information can be extracted
     * @return true on success
     */
    @Override
    public boolean run(DocletEnvironment environment) {
        final Fluff fluff = Fluff.create();
        try {
            fluff.analyze(environment, new PrintWriter(System.out));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
