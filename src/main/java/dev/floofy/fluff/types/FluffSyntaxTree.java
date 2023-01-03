package dev.floofy.fluff.types;

import java.util.List;

public record FluffSyntaxTree(
        List<Package> packages,
        List<ClassDefinition> classes,
        List<InterfaceElement> interfaces
) {}
