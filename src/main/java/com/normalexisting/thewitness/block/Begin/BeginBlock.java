package com.normalexisting.thewitness.block.Begin;

import com.normalexisting.thewitness.block.ModBlocks;
import com.normalexisting.thewitness.block.Path.PathBlock;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class BeginBlock extends PathBlock {

    Block reference0 = Blocks.REDSTONE_WIRE;
    Block reference1 = Blocks.REDSTONE_LAMP;
    // The begin block is where the player begins solving a puzzle. It stores if a player began at that location.
    public static HashMap<UUID, BlockPos> playerBegin = new HashMap<UUID, BlockPos>();
    // A particular begin block also represents the chosen path that the player takes.
    public static HashMap<UUID, ArrayList<BlockPos>> chosenpath = new HashMap<>();
    public BeginBlock(Properties properties) {
        super(properties);
        COLOR = Color.WHITE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player,
                                 InteractionHand hand, BlockHitResult blockHitResult) {
        // Server: Main Hand & Off Hand
        // Client: Main Hand & Off Hand

        // player.sendSystemMessage(Component.literal("!!!"));

        InteractionResult intres = super.use(state, level, blockPos, player, hand, blockHitResult);




        return intres;
    }

    static final boolean DEBUG = false;
    static final boolean MSGDB = false;

    public void reset(Level level, BlockPos pos) {
        try {
            UUID player = activePlayer.get(pos);
            AABB aabb = boundingbox.get(pos);
            ArrayList<BlockPos> thelist = getBlocksInBB(level, boundingbox.get(pos), true);
            boundingbox.remove(pos);
            for (BlockPos p : thelist) {
                boundingbox.remove(p);
                activePlayer.remove(p);
            }
            reps.remove(aabb);
            chosenpath.remove(player);
            steppingOn.remove(player);
        }
        catch (Exception e) {
            return;
        }
    }

    public void updateAll(Level level, BlockPos pos) {
        try {
            AABB aabb = getBoundingBox(level, pos);
            for (BlockPos bp : getBlocksInBB(level, aabb, true)) {
                level.updateNeighborsAt(bp, level.getBlockState(bp).getBlock());
            }
        }
        catch (Exception e) {
        }
    }

    public boolean resetSolved(Level level, BlockPos pos) {
        try {
            AABB aabb = getBoundingBox(level, pos);
            for (BlockPos bp : getBlocksInBB(level, aabb, true)) {
                if (isSolved.contains(bp)) isSolved.remove(bp);
                level.updateNeighborsAt(bp, level.getBlockState(bp).getBlock());
            }

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public void checkForReset(ServerLevel level, BlockPos pos) {
        // First we check if there is supposed to be an active player...
        if (!activePlayer.containsKey(pos)) return;
        UUID player = activePlayer.get(pos);
        // So if there is supposed to be an active player...
        boolean playerStillActive = false;
        ArrayList<BlockPos> thelist = getBlocksInBB(level, boundingbox.get(pos), true);
        for (BlockPos p : thelist) {
            if (checkActivePlayer(level, p)) playerStillActive = true;
        }
        // If there is no active player...
        if (!playerStillActive) {
            reset(level, pos);
        }
    }

    Block xx = Blocks.REDSTONE_LAMP;
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rs) {
        super.tick(state, level, pos, rs);

        if (checkForPlayer(level, pos)) {
            // When the player first steps onto a path it must be thru the start block.
            // This happens when the starting point detects a player while not already having an active player.
            // When this happens every single path block in the bounding box registers the player as a participant.

            Player player = getPlayer(level, pos);
            if (player == null) return;
            UUID uuid = player.getUUID();
            if (isSolved.contains(pos)) return;
            if (chosenpath.containsKey(uuid)) return;
            if (!activePlayer.containsKey(pos)) {
                AABB aabb = getBoundingBox(level, pos);
                boundingbox.put(pos, aabb);
                for (BlockPos p : getBlocksInBB(level, boundingbox.get(pos), true)) {
                    // Every single block in a bounding box shares knowledge of the bounding box.
                    boundingbox.put(p, aabb);
                    // Every single block in a bounding box shares knowledge of the player in question
                    activePlayer.put(p, uuid);

                    BlockState bs = level.getBlockState(pos);

                    level.updateNeighborsAt(pos, bs.getBlock());
                    level.setBlockAndUpdate(pos, bs);
                    level.scheduleTick(pos, bs.getBlock(), 0);
                    level.blockUpdated(pos, bs.getBlock());
                    level.markAndNotifyBlock(pos, level.getChunkAt(pos), bs, bs, 0, 16);
                }
                // As for the starting point itself...
                // The starting point becomes the representative of the bounding box.
                reps.put(aabb, pos);
                // ... and the chosen path.
                chosenpath.put(uuid, new ArrayList<>());
                chosenpath.get(uuid).add(pos);
                // ... and the player's stored position
                steppingOn.put(uuid, pos);
                super.tick(state, level, pos, rs); // Then the start point begins to act like a path.
                return;
            }
        }
        else { // If the player is not on the starting point...
            checkForReset(level, pos);
            super.tick(state, level, pos, rs);
            return;
        }
    }
}