package com.example.app.models;

public abstract class Model {

    /**
     * Key.
     */
    private long no = -1;

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

    public final long getNo() {
        return no;
    }

    public final void setNo(final long no) {
        this.no = no;
    }

}
