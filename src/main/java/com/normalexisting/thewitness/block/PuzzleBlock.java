package com.normalexisting.thewitness.block;

import com.normalexisting.thewitness.Reference;
import com.normalexisting.thewitness.block.Begin.BeginBlock;
import com.normalexisting.thewitness.block.OtherThings.*;
import com.normalexisting.thewitness.block.Dot.DotBlock;
import com.normalexisting.thewitness.block.End.EndBlock;
import com.normalexisting.thewitness.block.Path.PathBlock;
import com.normalexisting.thewitness.lib.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.MinecraftForge;

import java.awt.*;
import java.util.*;

public class PuzzleBlock extends Block {
    // Puzzle blocks store information pertatining to the entire puzzle. Each block stores the same information:
    // Active player, if the puzzle is being solved, if the puzzle has been solved, and the bounding area of the puzzle.
    // The bounding area of the puzzle is a cuboid. No two puzzles can intersect cuboids.
    // And finally the color. For path blocks this color lights up as the player moves. For symbols it becomes the color of the block.

    // UUID of the active player representing a particular position
    public static HashMap<BlockPos, UUID> activePlayer = new HashMap<BlockPos, UUID>();
    // The bounding box a particular position is in
    public static HashMap<BlockPos, AABB> boundingbox = new HashMap<>();
    // The color of a block at a particular position
    public static HashMap<BlockPos, Color> colors = new HashMap<>();
    // The starting point representing a particular bounding box
    public static HashMap<AABB, BlockPos> reps = new HashMap<>();

    public Color COLOR;
    public PuzzleBlock(BlockBehaviour.Properties properties) {
        super(properties.lightLevel(s -> Reference.LIGHT));
        MinecraftForge.EVENT_BUS.register(this);
        COLOR = Color.WHITE;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player,
                                 InteractionHand hand, BlockHitResult blockHitResult) {
        // Server: Main Hand & Off Hand
        // Client: Main Hand & Off Hand

        // player.sendSystemMessage(Component.literal("!!!"));

        InteractionResult intres = super.use(state, level, blockPos, player, hand, blockHitResult);




        return intres;
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 1);
    }

    public AABB getBoundingBox(Level level, BlockPos pos) {
        int aabb[] = {pos.getX(), pos.getY(), pos.getZ(), pos.getX(), pos.getY(), pos.getZ()};
        // Use a BFS to determine adjacent points. The BFS operates in 4 directions and up and down one block.
        final int dx[] = {1, 1, 1, 0, 0, 0, -1, -1, -1, 0, 0, 0};
        final int dz[] = {0, 0, 0, -1, -1, -1, 0, 0, 0, 1, 1, 1};
        final int dy[] = {-1, 0, 1, -1, 0, 1, -1, 0, 1, -1, 0, 1};

        HashSet<BlockPos> vis = new HashSet<>();

        Queue<BlockPos> h = new LinkedList<BlockPos>();
        h.add(pos);

        while (h.size() > 0) {
            BlockPos now = h.poll();
            // System.out.println("> " + now.toString());
            for (int i = 0; i < 12; i++) {
                BlockPos next = new BlockPos(now.getX() + dx[i], now.getY() + dy[i], now.getZ() + dz[i]);
                // System.out.println("< " + next.toString());
                if (Math.max(Math.abs(next.getX() - pos.getX()), Math.max(Math.abs(next.getY() - pos.getY()), Math.abs(next.getZ() - pos.getZ()))) > Reference.MAX_PUZZLE_WIDTH)
                    continue;
                if (vis.contains(next)) continue;
                Block b = Reference.getBlock(level, next);
                if (b == null) continue;
                if (!(b instanceof PuzzleBlock)) continue;
                // System.out.println("+ " + next.toString());
                h.add(next);
                vis.add(next);

                aabb[0] = Math.min(aabb[0], next.getX());
                aabb[1] = Math.min(aabb[1], next.getY());
                aabb[2] = Math.min(aabb[2], next.getZ());
                aabb[3] = Math.max(aabb[3], next.getX());
                aabb[4] = Math.max(aabb[4], next.getY());
                aabb[5] = Math.max(aabb[5], next.getZ());
            }
        }

        // System.out.println("SEARCHING COMPLETE " + Arrays.toString(aabb));
        return new AABB(aabb[0], aabb[1], aabb[2], aabb[3], aabb[4], aabb[5]);
    }

    public ArrayList<BlockPos> getBlocksInBB(Level level, AABB aabb, boolean mustPath) {
        ArrayList<BlockPos> res = new ArrayList<>();
        for (int i = (int)(aabb.minX); i <= aabb.maxX; i++) {
            for (int j = (int)aabb.minY; j <= aabb.maxY; j++) {
                for (int k = (int)aabb.minZ; k <= aabb.maxZ; k++) {
                    BlockPos p = new BlockPos(i, j, k);
                    Block b = Reference.getBlock(level, p);
                    if (b == null) continue;
                    if (!(b instanceof PuzzleBlock)) continue;
                    if (mustPath && !(b instanceof PathBlock)) continue;
                    res.add(p);
                }
            }
        }
        return res;
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rs) {
        super.tick(state, world, pos, rs);
        world.scheduleTick(pos, this, 1);

        /*
        if (activePlayer.containsKey(pos) && Math.random() < 1.0 / 64.0) {
            System.out.println("POS " + pos + " HAS PLAYER " + activePlayer.get(pos));
        }
        */
    }


    public boolean MSGDB = false;

    public int getSpacing(Level level, BlockPos pos) {
        boolean MSGDB_ = MSGDB && Math.random() < 0.125;
        AABB aabb = getBoundingBox(level, pos);
        int sx = (int) Math.floor(aabb.minX);
        int sz = (int) Math.floor(aabb.minZ);

        int dx = (int) Math.floor(aabb.maxX - aabb.minX + 1);
        int dz = (int) Math.floor(aabb.maxZ - aabb.minZ + 1);

        int maxspacing = Reference.gcd(dx - 1, dz - 1);
        if (MSGDB_) Reference.msgall("GCD OF " + Integer.toString(dx - 1) + " . " + Integer.toString(dz - 1) + " = " + Integer.toString(maxspacing), level);
        for (int spacing = maxspacing; spacing >= 1; spacing--) {
            if (maxspacing % spacing != 0) continue;
            boolean hasinvalid = false;
            for (BlockPos p : getBlocksInBB(level, aabb, true)) {
                int ox = Reference.floor(p.getX() - aabb.minX);
                int oz = Reference.floor(p.getZ() - aabb.minZ);
                if (ox % spacing != 0 && oz % spacing != 0) hasinvalid = true;
            }
            if (hasinvalid) continue;
            int gridwidth = (dx - 1) / spacing;
            int gridheight = (dz - 1) / spacing;
            return spacing;
        }
        return 0;
    }
    public Grid translate(Level level, BlockPos pos, int spacing) {
        System.out.println("SPACING " + spacing);
        if (spacing <= 1) return null;
        AABB aabb = getBoundingBox(level, pos);
        int sx = (int)Math.floor(aabb.minX);
        int sz = (int)Math.floor(aabb.minZ);

        int dx = (int)Math.floor(aabb.maxX - aabb.minX + 1);
        int dz = (int)Math.floor(aabb.maxZ - aabb.minZ + 1);

        boolean hasinvalid = false;
        for (BlockPos p : getBlocksInBB(level, aabb, true)) {
            int ox = Reference.floor(p.getX() - aabb.minX);
            int oz = Reference.floor(p.getZ() - aabb.minZ);
            if (ox % spacing != 0 && oz % spacing != 0) hasinvalid = true;
        }
        if (hasinvalid) return null;

        int gridwidth = (dx - 1) / spacing;
        int gridheight = (dz - 1) / spacing;

        if (MSGDB) {
            Reference.msgall("TRANSLATING PUZZLE " + aabb.toString() + " / " + Integer.toString(spacing) + " = " + Integer.toString(gridwidth) + "x" + Integer.toString(gridheight), level);
        }

        PuzzleEntity v[][] = new PuzzleEntity[2 * gridwidth + 1][2 * gridheight + 1];

        for (int i = 0; i < 2 * gridwidth + 1; i++) {
            for (int j = 0; j < 2 * gridheight + 1; j++) {
                v[i][j] = new PuzzleEntity();
                // if (i % 2 == 0 || j % 2 == 0) v[i][j].isPath = true;
            }
        }

        // Look for objects

        Color cols[][] = new Color[2 * gridwidth + 1][2 * gridheight + 1];
        int trixcnt[][] = new int[2 * gridwidth + 1][2 * gridheight + 1];
        boolean twist[][] = new boolean[2 * gridwidth + 1][2 * gridheight + 1];
        ArrayList<ArrayList<HashSet<IntegerPair>>> blocks = new ArrayList<>();

        for (int i = 0; i < 2 * gridwidth + 1; i++) {
            ArrayList<HashSet<IntegerPair>> vx = new ArrayList<>();
            for (int j = 0; j < 2 * gridheight + 1; j++) {
                cols[i][j] = null;
                trixcnt[i][j] = 0;
                vx.add(new HashSet<IntegerPair>());
                twist[i][j] = false;
            }
            blocks.add(vx);
        }

        for (int x = Reference.floor(aabb.minX); x <= aabb.maxX; x++) {
            for (int z = Reference.floor(aabb.minZ); z <= aabb.maxZ; z++) {
                for (int y = Reference.floor(aabb.minY); y <= aabb.maxY; y++) {
                    dx = Reference.floor((x - aabb.minX));
                    dz = Reference.floor((z - aabb.minZ));
                    int ix = Reference.floor(dx / spacing);
                    int iz = Reference.floor(dz / spacing);
                    Block block = level.getBlockState(new BlockPos(x, y, z)).getBlock();
                    System.out.println(block.toString()  + " " + new BlockPos(x, y, z));
                    // Intersections
                    int px, pz;
                    if (dx % spacing == 0 && dz % spacing == 0) {
                        px = ix * 2;
                        pz = iz * 2;
                        if (block instanceof PathBlock) v[px][pz].isPath = true;
                        if (block instanceof BeginBlock) v[px][pz] = new Endpoint(true);
                        else if (block instanceof EndBlock) v[px][pz] = new Endpoint(false);
                        else if (block instanceof SymbolBlock) {
                            if (MSGDB) Reference.msgall("BAD INTERSECTION " + Integer.toString(x) + " " + Integer.toString(z), level);
                            return null;
                        }

                        else if (block instanceof DotBlock) {
                            if (v[px][pz].isPath == false) return null;
                            else v[px][pz] = new Dot();
                        }
                        else if (!(block instanceof PathBlock)) { // air block
                            if (v[px][pz] instanceof Dot) return null;
                        }
                    }
                    // Edges
                    else if (dx % spacing == 0 && dz % spacing != 0) {
                        px = ix * 2;
                        pz = iz * 2 + 1;
                        if (block instanceof PathBlock) v[px][pz].isPath = true;
                        if (block instanceof BeginBlock) v[px][pz] = new Endpoint(true);
                        else if (block instanceof EndBlock) v[px][pz] = new Endpoint(false);
                        else if (block instanceof SymbolBlock) {
                            if (MSGDB) Reference.msgall("BAD EDGE VERTICAL " + Integer.toString(x) + " " + Integer.toString(z), level);
                            return null;
                        }
                        else if (block instanceof DotBlock) {
                            if (v[px][pz].isPath == false) return null;
                            else v[px][pz] = new Dot();
                        }
                        else if (!(block instanceof PathBlock)) { // air block
                            if (v[px][pz] instanceof Dot) return null;
                        }
                    }
                    else if (dz % spacing == 0 && dx % spacing != 0) {
                        px = ix * 2 + 1;
                        pz = iz * 2;
                        if (block instanceof PathBlock) v[px][pz].isPath = true;
                        if (block instanceof BeginBlock) v[px][pz] = new Endpoint(true);
                        else if (block instanceof EndBlock) v[px][pz] = new Endpoint(false);
                        else if (block instanceof SymbolBlock) {
                            if (MSGDB) Reference.msgall("BAD EDGE HORIZONTAL " + Integer.toString(x) + " " + Integer.toString(z), level);
                            return null;
                        }
                        else if (block instanceof DotBlock) {
                            if (v[px][pz].isPath == false) return null;
                            else v[px][pz] = new Dot();
                        }
                        else if (!(block instanceof PathBlock)) { // air block
                            if (v[px][pz] instanceof Dot) return null;
                        }
                    }
                    // CELLS CELLS CELLS CELLS CELLS CELLS CELLS CELLS CELLS CELLS
                    else if (dx % spacing != 0 && dz % spacing != 0) {
                        px = ix * 2 + 1;
                        pz = iz * 2 + 1;

                        if (block instanceof BeginBlock || block instanceof EndBlock) {
                            if (MSGDB) Reference.msgall("BAD REGION " + Integer.toString(x) + " " + Integer.toString(z), level);
                            return null;
                        }
                        else if (block instanceof BlobBlock) {
                            v[px][pz] = new Blob(((PuzzleBlock)block).COLOR);
                            // System.out.println(px + " " + pz + "IS COLORED " + v[px][pz].color);
                        }
                        else if (block instanceof StarBlock) {
                            v[px][pz] = new Star(((PuzzleBlock)block).COLOR);
                        }
                        else if (block instanceof TriangleBlock) {
                            cols[px][pz] = ((PuzzleBlock)block).COLOR;
                            trixcnt[px][pz]++;
                        }
                        else if (block instanceof TetBlock) {
                            cols[px][pz] = ((PuzzleBlock)block).COLOR;
                            if (block instanceof TwistBlock) twist[px][pz] = true;
                            blocks.get(px).get(pz).add(new IntegerPair(dz, -1 * dx));
                        }
                        else if (block instanceof CancelBlock) v[px][pz] = new Cancel();
                    }
                }
            }
        }

        for (int i = 0; i < gridwidth; i++) {
            for (int j = 0; j < gridheight; j++) {
                int px = i * 2 + 1;
                int pz = j * 2 + 1;
                if (trixcnt[px][pz] > 0) v[px][pz] = new Triangle(trixcnt[px][pz], cols[px][pz]);
                else {
                    try {
                        ArrayList<IntegerPair> list = new ArrayList<>();
                        for (IntegerPair p : blocks.get(px).get(pz)) list.add(p);
                        IntegerPair thearray[] = new IntegerPair[list.size()];
                        for (int x = 0; x < list.size(); x++) thearray[x] = list.get(x);
                        if (list.size() > 0) {
                            BlockGroup bg = new BlockGroup(!twist[px][pz], false, thearray, cols[px][pz]);
                            bg.normalize();
                            // System.out.println("BLOCK GROUP AT " + px + " " + pz + " = " + bg.toString());
                            v[px][pz] = bg;
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }


        Grid grid = new Grid(v);

        boolean FINAL_ = false;
        if (FINAL_) {
            Reference.msgall(">> " + Integer.toString(v.length) + " " + Integer.toString(v[0].length), level);
            Reference.msgall("GRID...\n" + grid.toString(), level);
        }
        return grid;
    }
}
