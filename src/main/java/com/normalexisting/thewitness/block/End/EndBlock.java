package com.normalexisting.thewitness.block.End;

import com.normalexisting.thewitness.Reference;
import com.normalexisting.thewitness.block.Begin.BeginBlock;
import com.normalexisting.thewitness.block.Path.PathBlock;
import com.normalexisting.thewitness.lib.Grid;
import com.normalexisting.thewitness.lib.IntegerPair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class EndBlock extends PathBlock {

    Block reference0 = Blocks.TORCH;

    // The begin block is where the player begins solving a puzzle. It stores if a player began at that location.
    public static HashMap<UUID, BlockPos> playerBegin = new HashMap<UUID, BlockPos>();
    // A particular begin block also represents the chosen path that the player takes.
    public static HashMap<UUID, ArrayList<BlockPos>> chosenpath = new HashMap<>();
    public EndBlock(Properties properties) {

        super(properties);
    }

    static final boolean DEBUG = false;
    static final boolean MSGDB = false;

    boolean verify(Level level, BlockPos pos, UUID uuid) {
        int spacing = getSpacing(level, pos);
        Grid grid = translate(level, pos, spacing);
        if (grid == null) return true;
        if (!BeginBlock.chosenpath.containsKey(uuid)) return false;
        ArrayList<BlockPos> path = BeginBlock.chosenpath.get(uuid);

        AABB aabb = getBoundingBox(level, pos);

        int prevx = path.get(0).getX() - Reference.floor(aabb.minX);
        int prevz = path.get(0).getZ() - Reference.floor(aabb.minZ);
        prevx *= 2;
        prevz *= 2;

        int sx = prevx;
        int sz = prevz;
        for (int i = 1; i < path.size(); i++) {
            int dx = path.get(i).getX() - Reference.floor(aabb.minX);
            int dz = path.get(i).getZ() - Reference.floor(aabb.minZ);

            dx *= 2;
            dz *= 2;
            if (dz % spacing == 0 && dx % spacing == 0) {
                grid.drawLine(new IntegerPair(prevx / spacing, prevz / spacing), new IntegerPair(dx / spacing, dz / spacing));
                prevx = dx;
                prevz = dz;

            }
        }

        // System.out.println("PATHY GRID " + grid.toString());

        if (MSGDB) Reference.msgall(grid.toString(), level);


        try {
            // System.out.println((sx / spacing) + " " + (sz / spacing));
            System.out.println(grid.toString() + "\n" + grid.ver(sx / spacing, sz / spacing));
            return grid.ver(sx / spacing, sz / spacing);
            // return grid.check();
        }
        catch (Exception e) {
            Reference.msgall("VERIFICATION FAILED FOR PUZZLE " + aabb.toString() + " STARTING AT " + Integer.toString(sx) + " " + Integer.toString(sz), level);
            return false;
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        InteractionResult res = super.use(state, level, pos, entity, hand, hit);
        if (!activePlayer.containsKey(pos)) return res;
        UUID uuid = activePlayer.get(pos);
        if (checkPlayerUUID(level, pos, uuid)) {

            if (verify(level, pos, uuid)) {
                Reference.msgall("PUZZLE AT BOUNDING BOX " + boundingbox.get(pos).toString() + " HAS BEEN SOLVED", level);
                for (BlockPos b : BeginBlock.chosenpath.get(uuid)) isSolved.add(b);
            }
            else {
                AABB aabb = boundingbox.get(pos);
                BeginBlock bb = (BeginBlock)(level.getBlockState(reps.get(aabb)).getBlock());
                bb.reset(level, reps.get(aabb));
            }
        }
        level.updateNeighborsAt(pos, level.getBlockState(pos).getBlock());
        level.scheduleTick(pos, level.getBlockState(pos).getBlock(), 0);

        return res;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rs) {
        super.tick(state, level, pos, rs);
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
        Level level = (Level)blockAccess;
        return isSolved.contains(pos) ? 15 : 0;
    }

}