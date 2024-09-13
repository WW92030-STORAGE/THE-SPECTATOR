package com.normalexisting.thewitness.block.OtherThings;

import com.normalexisting.thewitness.block.SymbolBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

public class TriangleBlock extends SymbolBlock {
    public TriangleBlock(Properties properties) {
        super(properties);
        COLOR = Color.BLACK;
    }

    public TriangleBlock(Properties properties, Color color) {
        super(properties);
        COLOR = color;
    }


    boolean MSGDB = false;

    // This is called every tick. For certain blocks, such as the starting point, that can affect the results, this is called once again during its execution.
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rs) {
        super.tick(state, level, pos, rs);

    }
}
