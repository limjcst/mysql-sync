package com.example.app.models;

public abstract class Model {

    /**
     * Key.
     */
    private long no = -1;

    /**
     * Compare two Object.
     * @param a One Object.
     * @param b The other Object.
     * @return whether a equals b.
     */
    public static final boolean objectEquals(final Object a, final Object b) {
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
