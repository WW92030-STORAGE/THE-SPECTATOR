package com.normalexisting.thewitness.lib;

import java.awt.*;

public class Triangle extends PuzzleEntity {
    public int x;
    public Triangle(int n) {
        super();
        x = n;
        color = Color.ORANGE;
    }
    public Triangle(int n, Color c) {
        super();
        x = n;
        color = c;
    }
}
