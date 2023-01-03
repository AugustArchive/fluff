package dev.floofy.fluff.types;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import java.util.Collections;
import java.util.List;

public class Package implements TypedName {
    private List<String> modifiers;
    private String name;

    public Package(PackageElement element) {
        this.modifiers = element.getModifiers().stream().map(Modifier::toString).toList();
        this.name = element.getQualifiedName().toString();
    }

    @Override
    public String getType() {
        return "package";
    }

    /**
     * Returns the package's canonical name, i.e, <code>dev.floofy.fluff.types</code>. This method
     * might return an empty string for empty package names.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the package's canonical name
     * @param name The canonical name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns all the modifiers for this package
     */
    public List<String> getModifiers() {
        return Collections.unmodifiableList(modifiers);
    }

    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }
}
