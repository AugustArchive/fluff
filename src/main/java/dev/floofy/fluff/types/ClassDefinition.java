package dev.floofy.fluff.types;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import java.util.List;

public class ClassDefinition implements TypedName {
    private final List<InterfaceElement> implementedIfs;
    private final List<String> modifiers;
    private final Package pkg;
    private final String name;

    public ClassDefinition(TypeElement element) {
        this.implementedIfs = element
                .getInterfaces()
                .stream()
                .filter(f -> f.getKind() == TypeKind.DECLARED)
                .map(s -> new InterfaceElement((TypeElement) ((DeclaredType)s).asElement()))
                .toList();

        this.modifiers = element.getModifiers().stream().map(Modifier::toString).toList();
        this.name = element.getSimpleName().toString();
        this.pkg = new Package((PackageElement) element.getEnclosingElement());
    }

    /**
     * @return All the implemented interfaces for this class.
     */
    public List<InterfaceElement> getImplements() {
        return implementedIfs;
    }

    /**
     * @return all modifiers of this class
     */
    public List<String> getModifiers() {
        return modifiers;
    }

    /**
     * Returns the class name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current type by its name.
     */
    @Override
    public String getType() {
        return "class";
    }

    /**
     * Returns the package that this class is in
     */
    public Package getPackage() {
        return pkg;
    }
}
