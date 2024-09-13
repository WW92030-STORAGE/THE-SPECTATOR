package com.normalexisting.thewitness.lib;

public class Util {
    public static String getType(PuzzleEntity e) {
        if (e instanceof BlockGroup) return "+BLOCK";
        if (e instanceof Endpoint) {
            if (((Endpoint)e).starting) return "START!";
            return "ENDPT!";
        }
        if (e instanceof Dot) return "PATHDT";
        if (e instanceof Star) return "_STAR_";
        if (e instanceof Blob) return "_BLOB_";
        if (e instanceof Triangle) return "TRIX_" + Integer.toString(((Triangle)e).x);
        if (e instanceof Cancel) {
            if (((Cancel)e).ignored) return "OBJECT";
            return "CANCEL";
        }
        return e.isPath ? "0BJECT" : "OBJECT";
    }

    public static boolean isStartingPoint(PuzzleEntity e) {
        if (e instanceof Endpoint) return ((Endpoint)e).starting;
        return false;
    }

    public static boolean isEndingPoint(PuzzleEntity e) {
        if (e instanceof Endpoint) return !(((Endpoint)e).starting);
        return false;
    }

    public static boolean isSymbol(PuzzleEntity e) {
        if (e instanceof BlockGroup) return true;
        if (e instanceof Endpoint) return true;
        if (e instanceof Dot) return true;
        if (e instanceof Blob) return true;
        if (e instanceof Star) return true;
        if (e instanceof Triangle) return true;
        if (e instanceof Cancel) return true;
        return false;
    }
}
