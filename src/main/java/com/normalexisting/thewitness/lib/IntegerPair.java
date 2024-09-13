package com.normalexisting.thewitness.lib;

public class IntegerPair implements Comparable<IntegerPair> {
    public int x;
    public int y;

    public IntegerPair() {
        x = 0;
        y = 0;
    }

    public IntegerPair(int a, int b) {
        x = a;
        y = b;
    }

    public IntegerPair clone() {
        return new IntegerPair(this.x, this.y);
    }

    public int compareTo(IntegerPair o) {
        if (x != o.x) return x - o.x;
        return y - o.y;
    }

    public IntegerPair add(IntegerPair other) {
        return new IntegerPair(this.x + other.x, this.y + other.y);
    }

    public IntegerPair inv() {
        return new IntegerPair(-1 * this.x, -1 * this.y);
    }

    public IntegerPair sub(IntegerPair other) {
        return this.add(other.inv());
    }

    public boolean equals(IntegerPair o) {
        return this.compareTo(o) == 0;
    }

    public String toString() {
        return "<" + Integer.toString(this.x) + " " + Integer.toString(this.y) + ">";
    }



    public static final IntegerPair PLUS_X = new IntegerPair(1, 0);
    public static final IntegerPair PLUS_Y = new IntegerPair(0, 1);
    public static final IntegerPair MINUS_X = new IntegerPair(-1, 0);
    public static final IntegerPair MINUS_Y = new IntegerPair(0, -1);
}
