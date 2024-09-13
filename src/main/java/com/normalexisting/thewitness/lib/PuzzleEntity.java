package com.normalexisting.thewitness.lib;

import java.awt.*;

public class PuzzleEntity {
    public Color color;
    public boolean isPath;
    public boolean isPathOccupied;

    public PuzzleEntity() {
        color = null;
        isPath = false;
        isPathOccupied = false;
    }

    public PuzzleEntity clear() {
        PuzzleEntity res = new PuzzleEntity();
        res.isPath = isPath;
        res.isPathOccupied = isPathOccupied;
        res.color = null;
        return res;
    }


}
