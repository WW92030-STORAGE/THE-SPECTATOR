package com.normalexisting.thewitness.block.Path;

import com.normalexisting.thewitness.Reference;
import com.normalexisting.thewitness.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

import java.awt.*;

public class PathBlockYellow extends PathBlock {
    public PathBlockYellow(Properties properties) {
        super(properties);
        COLOR = Color.YELLOW;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player,
                                 InteractionHand hand, BlockHitResult blockHitResult) {
        super.use(state, level, blockPos, player, hand, blockHitResult);
        // Server: Main Hand & Off Hand
        // Client: Main Hand & Off Hand

        // player.sendSystemMessage(Component.literal("!!!"));

        InteractionResult intres = super.use(state, level, blockPos, player, hand, blockHitResult);




        return intres;
    }

    // This is called every tick. For certain blocks, such as the starting point, that can affect the results, this is called once again during its execution.
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rs) {
        super.tick(state, level, pos, rs);
    }
}