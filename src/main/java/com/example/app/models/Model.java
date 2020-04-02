package com.example.app.models;

public abstract class Model {

    /**
     * Compare two String.
     * @param a One String.
     * @param b The other String.
     * @return whether a equals b.
     */
    public static final boolean stringEquals(final String a, final String b) {
        if (a == null) {
            return a == b;
        }
        return a.equals(b);
    }

}
