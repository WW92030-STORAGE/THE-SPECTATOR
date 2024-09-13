package com.normalexisting.thewitness.lib;

import java.awt.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class BlockGroup extends PuzzleEntity {
    public boolean oriented; // Fixed Orientation
    public boolean sub;

    public TreeSet<IntegerPair> pairs;
    public int n;

    public IntegerPair bottomleft;
    public IntegerPair topright;
    public IntegerPair boundingbox;

    public BlockGroup(boolean orientation, boolean subtractive, IntegerPair[] v, Color c) {
        oriented = orientation;
        sub = subtractive;
        pairs = new TreeSet<IntegerPair>();
        n = v.length;
        color = c;

        bottomleft = new IntegerPair(Integer.MAX_VALUE, Integer.MAX_VALUE);
        topright = new IntegerPair(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            pairs.add(v[i]);
            bottomleft.x = Math.min(bottomleft.x, v[i].x);
            bottomleft.y = Math.min(bottomleft.y, v[i].y);
            topright.x = Math.max(topright.x, v[i].x);
            topright.y = Math.max(topright.y, v[i].y);
        }

        boundingbox = new IntegerPair(topright.x - bottomleft.x + 1, topright.y - bottomleft.y + 1);
    }

    public BlockGroup(boolean orientation, boolean subtractive, IntegerPair[] v) {
        this(orientation, subtractive, v, Color.YELLOW);
    }

    public void updateBounds() {
        bottomleft = new IntegerPair(Integer.MAX_VALUE, Integer.MAX_VALUE);
        topright = new IntegerPair(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (IntegerPair p : pairs) {
            bottomleft.x = Math.min(bottomleft.x, p.x);
            bottomleft.y = Math.min(bottomleft.y, p.y);
            topright.x = Math.max(topright.x, p.x);
            topright.y = Math.max(topright.y, p.y);
        }

        boundingbox = new IntegerPair(topright.x - bottomleft.x + 1, topright.y - bottomleft.y + 1);
    }

    // Utility Functions

    public boolean contains(IntegerPair p) {
        return pairs.contains(p);
    }

    public void add(IntegerPair p) {
        if (contains(p)) return;
        pairs.add(p.clone());
        n++;
    }

    public void remove(IntegerPair p) {
        if (!contains(p)) return;
        pairs.remove(p);
        n--;
    }

    public void reset(IntegerPair[] v) {
        n = v.length;
        pairs.clear();
        bottomleft = new IntegerPair(Integer.MAX_VALUE, Integer.MAX_VALUE);
        topright = new IntegerPair(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            pairs.add(v[i]);
            bottomleft.x = Math.min(bottomleft.x, v[i].x);
            bottomleft.y = Math.min(bottomleft.y, v[i].y);
            topright.x = Math.max(topright.x, v[i].x);
            topright.y = Math.max(topright.y, v[i].y);
        }

        boundingbox = new IntegerPair(topright.x - bottomleft.x + 1, topright.y - bottomleft.y + 1);
    }

    public BlockGroup clone() {
        IntegerPair[] v = new IntegerPair[pairs.size()];
        int index = 0;
        for (IntegerPair p : pairs) v[index++] = p.clone();
        return new BlockGroup(this.oriented, this.sub, v, this.color);
    }

    // General Functions

    public void rotate(int x) {
        while (x < 0) x += 8;
        x %= 4;
        if (x <= 0) return;
        rotate(x - 1);

        IntegerPair[] res = new IntegerPair[pairs.size()];
        int index = 0;
        for (IntegerPair p : pairs) res[index++] = new IntegerPair(-1 * p.y, p.x);
        reset(res);
    }

    public void move(IntegerPair n) {
        IntegerPair[] res = new IntegerPair[pairs.size()];
        int index = 0;
        for (IntegerPair p : pairs) res[index++] = p.add(n);
        reset(res);
    }

    public void invmov(IntegerPair n) {
        move(n.inv());
    }

    public void normalize() {
        invmov(bottomleft);
    }

    public void removeRegion(BlockGroup o) {
        for (IntegerPair p : o.pairs) this.remove(p);
    }

    public void addRegion(BlockGroup o) {
        for (IntegerPair p : o.pairs) this.add(p);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[O = ");
        sb.append(this.oriented ? "1" : "0");
        sb.append(" S = ");
        sb.append(this.sub ? "1" : "0");
        sb.append(" N = ");
        sb.append(Integer.toString(n));
        sb.append("] = [");
        for (IntegerPair i : pairs) sb.append(i.toString());
        sb.append("]");
        return sb.toString();
    }

    // RegionTesting

    public boolean containsBB(BlockGroup b) { // Can the bounding box contain that belonging to region b?
        if (boundingbox.x < b.boundingbox.x) return false;
        if (boundingbox.y < b.boundingbox.y) return false;
        return true;
    }

    public boolean directoverlay(BlockGroup b) { // Does this region contain region b in terms of absolute coordinates?
        if (n < b.n) return false;
        for (IntegerPair p : b.pairs) {
            if (!contains(p)) return false;
        }
        return true;
    }

    ArrayList<IntegerPair> fixedoverlay(BlockGroup b) { // Does this region contain region b? Return the offset if yes, INT_MIN if no.
        ArrayList<IntegerPair> res = new ArrayList<IntegerPair>();
        if (n < b.n) return res;

        int dx = bottomleft.x - b.bottomleft.x;
        int dy = bottomleft.y - b.bottomleft.y;

        BlockGroup test = b.clone();
        test.move(new IntegerPair(dx, dy));

        int width = Math.abs(boundingbox.x - b.boundingbox.x);
        int height = Math.abs(boundingbox.y - b.boundingbox.y);

        IntegerPair carriagereturn = new IntegerPair(1, -1 * height - 1);

        for (int i = 0; i <= width; i++) {
            for (int j = 0; j <= height; j++) {
                if (directoverlay(test)) res.add(new IntegerPair(i, j));
                test.move(IntegerPair.PLUS_Y);
            }
            test.move(carriagereturn);
        }

        return res;
    }

    ArrayList<ArrayList<IntegerPair>> overlay(BlockGroup b) {
        ArrayList<ArrayList<IntegerPair>> res = new ArrayList<ArrayList<IntegerPair>>();
        res.add(fixedoverlay(b));
        if (!b.oriented) {
            BlockGroup test = b.clone();
            test.normalize();
            for (int i = 0; i < 3; i++) {
                test.rotate(1);
                test.normalize();
                res.add(fixedoverlay(test));
            }
        }

        return res;
    }

    // Now for the real thing

    public boolean dfsUtil(BlockGroup region, BlockGroup[] v, int index) {
        if (index >= v.length) return region.n == 0;

        BlockGroup group = v[index].clone();
        group.normalize();
        ArrayList<ArrayList<IntegerPair>> options = region.overlay(group);

        boolean res = false;
        for (int i = 0; i < 4; i++) {
            group.move(region.bottomleft);
            for (IntegerPair op : options.get(i)) {
                group.move(op);
                region.removeRegion(group);
                res |= dfsUtil(region, v, index + 1);
                if (res) return true;
                region.addRegion(group);
                group.invmov(op);
            }
            if (group.oriented) break;
            group.rotate(1);
            group.normalize();
        }
        return res;
    }

    public boolean solve(BlockGroup[] v) {
        int diff = n;
        for (BlockGroup i : v) {
            if (i.sub) diff += i.n;
            else diff -= i.n;
        }
        if (diff != 0) return false;
        return dfsUtil(clone(), v, 0);
    }
}
