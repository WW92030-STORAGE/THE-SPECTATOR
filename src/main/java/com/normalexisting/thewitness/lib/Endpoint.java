package com.normalexisting.thewitness.lib;

public class Endpoint extends PuzzleEntity {
    public boolean starting;
    public Endpoint(boolean s) {
        super();
        starting = s;
        isPath = true;
    }
}
