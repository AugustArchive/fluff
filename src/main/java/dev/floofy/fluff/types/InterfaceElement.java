package dev.floofy.fluff.types;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

/**
 * Represents an element that represents a Java interface
 */
public class InterfaceElement implements TypedName {
    private final Package pkg;
    private final String name;

    public InterfaceElement(TypeElement element) {
        this.pkg = new Package((PackageElement)element.getEnclosingElement());
        this.name = element.getSimpleName().toString();
    }

    /**
     * Returns the current type by its name.
     */
    @Override
    public String getType() {
        return "interface";
    }

    /**
     * Returns the interface name
     */
    public String getName() {
        return name;
    }

    public Package getPackage() {
        return pkg;
    }
}
