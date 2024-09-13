package com.normalexisting.thewitness.item;

import com.normalexisting.thewitness.Reference;
import com.normalexisting.thewitness.block.Begin.BeginBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ResetItem extends Item {
    public ResetItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();
        BlockPos bp = context.getClickedPos();
        if (level.isClientSide()) return super.useOn(context);
        if (hand != InteractionHand.MAIN_HAND) return super.useOn(context);

        int x = bp.getX();
        int y = bp.getY();
        int z = bp.getZ();

        if (level.getBlockState(bp).getBlock() instanceof BeginBlock) {
            boolean res = ((BeginBlock)(level.getBlockState(bp).getBlock())).resetSolved(level, bp);
            if (res) Reference.msgall("PUZZLE AT " + bp.toString() + " RESET", level);
            else Reference.msgall("FAILED TO RESET PUZZLE AT " + bp.toString(), level);
        }
        return super.useOn(context);
    }
}