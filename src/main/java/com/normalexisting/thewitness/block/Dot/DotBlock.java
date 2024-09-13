package com.normalexisting.thewitness.block.Dot;

import com.normalexisting.thewitness.Reference;
import com.normalexisting.thewitness.block.ModBlocks;
import com.normalexisting.thewitness.block.Path.PathBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DotBlock extends PathBlock {
    public static HashMap<UUID, ArrayList<BlockPos>> chosenpath = new HashMap<>();
    public DotBlock(Properties properties) {
        super(properties);
    }

    public DotBlock(Properties properties, Color color) {
        super(properties);
        COLOR = color;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rs) {
        super.tick(state, level, pos, rs);
    }

    @OnlyIn(Dist.CLIENT)
    public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.DOT.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.DOT_GRAY.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.DOT_RED.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.DOT_ORANGE.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.DOT_YELLOW.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.DOT_GREEN.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.DOT_CYAN.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.DOT_BLUE.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.DOT_MAGENTA.get());
    }

}