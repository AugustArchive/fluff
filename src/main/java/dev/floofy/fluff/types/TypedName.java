package dev.floofy.fluff.types;

/**
 * Represents an interface to make sure that {@link #getType()} is always included.
 */
public interface TypedName {
    /**
     * Returns the current type by its name.
     */
    String getType();
}
