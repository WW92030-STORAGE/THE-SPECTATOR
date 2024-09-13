package com.normalexisting.thewitness.lib;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        IntegerPair[] bar3 = {new IntegerPair(0, 1), new IntegerPair(0, 2), new IntegerPair(0, 3)};
        IntegerPair[] bar2 = {new IntegerPair(0, 0), new IntegerPair(1, 0)};
        IntegerPair[] lshape = {new IntegerPair(100, 100), new IntegerPair(100, 101), new IntegerPair(101, 100)};
        IntegerPair[] threebythree = new IntegerPair[9];
        IntegerPair[] cell = {new IntegerPair(1000, 1000)};
        for (int i = 0; i < 9; i++) threebythree[i] = new IntegerPair(i % 3, i / 3);

        BlockGroup workbench = new BlockGroup(true, false, threebythree);
        BlockGroup corner = new BlockGroup(false, false, lshape);
        BlockGroup spline = new BlockGroup(true, false, bar3);
        BlockGroup unit = new BlockGroup(true, false, cell);
        BlockGroup dom = new BlockGroup(true, false, bar2);

        BlockGroup[] v = {corner, corner, spline};
        System.out.println(workbench.solve(v));

        PuzzleEntity[][] plapp = new PuzzleEntity[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) plapp[i][j] = new PuzzleEntity();
        }
        plapp[8][0] = new Endpoint(true);
        plapp[0][8] = new Endpoint(false);

        plapp[1][1] = new Triangle(3);


        Grid grid = new Grid(plapp);
        grid.defaultGrid();

        // grid.drawLine(new Pair(8, 0), new Pair(0, 0));
        // grid.drawLine(new Pair(0, 0), new Pair(0, 8));

        grid.drawLine(new IntegerPair(8, 0), new IntegerPair(0, 0));
        grid.drawLine(new IntegerPair(0, 0), new IntegerPair(0, 2));
        grid.drawLine(new IntegerPair(0, 2), new IntegerPair(2, 2));
        grid.drawLine(new IntegerPair(2, 2), new IntegerPair(2, 8));
        grid.drawLine(new IntegerPair(2, 8), new IntegerPair(0, 8));
        System.out.println(grid);

        System.out.println(grid.check());



        plapp = new PuzzleEntity[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) plapp[i][j] = new PuzzleEntity();
        }
        plapp[4][0] = new Endpoint(true);
        plapp[0][4] = new Endpoint(false);

        plapp[1][1] = new Star(Color.BLACK);
        plapp[1][3] = new Blob(Color.BLACK);

        plapp[3][1] = new Star(Color.BLACK);
        plapp[3][3] = new Blob(Color.BLACK);


        grid = new Grid(plapp);
        grid.defaultGrid();

        IntegerPair path[] = {new IntegerPair(4, 0), new IntegerPair(2, 0), new IntegerPair(2, 4), new IntegerPair(0, 4)};
        grid.drawPath(path);

        System.out.println(grid);

        System.out.println(grid.check());



        plapp = new PuzzleEntity[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) plapp[i][j] = new PuzzleEntity();
        }
        plapp[4][0] = new Endpoint(true);
        plapp[0][4] = new Endpoint(false);

        plapp[1][1] = dom;

        grid = new Grid(plapp);
        grid.defaultGrid();

        IntegerPair path2[] = {new IntegerPair(4, 0), new IntegerPair(2, 0), new IntegerPair(2, 4), new IntegerPair(0, 4)};

        grid.drawPath(path2);

        System.out.println(grid);

        System.out.println(grid.check());







        plapp = new PuzzleEntity[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) plapp[i][j] = new PuzzleEntity();
        }
        plapp[4][0] = new Endpoint(true);
        plapp[0][4] = new Endpoint(false);

        plapp[1][1] = new Cancel();
        plapp[0][2] = new Dot();

        grid = new Grid(plapp);
        grid.defaultGrid();

        IntegerPair path3[] = {new IntegerPair(4, 0), new IntegerPair(2, 0), new IntegerPair(2, 4), new IntegerPair(0, 4)};

        grid.drawPath(path3);

        System.out.println(grid);

        System.out.println(grid.check());


        plapp = new PuzzleEntity[1][3];
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 3; j++) plapp[i][j] = new PuzzleEntity();
        }
        plapp[0][0] = new Endpoint(true);
        plapp[0][2] = new Endpoint(false);

        grid = new Grid(plapp);
        grid.defaultGrid();
        grid.drawLine(new IntegerPair(0, 0), new IntegerPair(0, 2));
        System.out.println(grid);

        System.out.println(grid.check());



        plapp = new PuzzleEntity[3][1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) plapp[i][j] = new PuzzleEntity();
        }
        plapp[2][0] = new Endpoint(true);
        plapp[0][0] = new Endpoint(false);

        grid = new Grid(plapp);
        grid.defaultGrid();
        grid.drawLine(new IntegerPair(0, 0), new IntegerPair(2, 0));
        System.out.println(grid);

        System.out.println(grid.check());

    }
}
