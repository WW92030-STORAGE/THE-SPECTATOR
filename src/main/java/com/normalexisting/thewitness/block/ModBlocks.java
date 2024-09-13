package com.normalexisting.thewitness.block;

import com.normalexisting.thewitness.Reference;
import com.normalexisting.thewitness.block.Begin.*;
import com.normalexisting.thewitness.block.OtherThings.*;
import com.normalexisting.thewitness.block.Dot.*;
import com.normalexisting.thewitness.block.End.EndBlock;
import com.normalexisting.thewitness.block.Path.*;
import com.normalexisting.thewitness.item.ModItems;
import com.normalexisting.thewitness.lib.Triangle;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);
    // BEGIN BLOCKS

    public static final RegistryObject<Block> BEGIN = registerBlock("begin",
            () -> new BeginBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BEGIN_GRAY = registerBlock("begin_gray",
            () -> new BeginBlockGray(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BEGIN_RED = registerBlock("begin_red",
            () -> new BeginBlockRed(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BEGIN_ORANGE = registerBlock("begin_orange",
            () -> new BeginBlockOrange(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BEGIN_YELLOW = registerBlock("begin_yellow",
            () -> new BeginBlockYellow(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BEGIN_GREEN = registerBlock("begin_green",
            () -> new BeginBlockGreen(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BEGIN_CYAN = registerBlock("begin_cyan",
            () -> new BeginBlockCyan(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BEGIN_BLUE = registerBlock("begin_blue",
            () -> new BeginBlockBlue(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BEGIN_MAGENTA = registerBlock("begin_magenta",
            () -> new BeginBlockMagenta(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));

    // PATH BLOCKS

    public static final RegistryObject<Block> PATH = registerBlock("path",
            () -> new PathBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PATH_GRAY = registerBlock("path_gray",
            () -> new PathBlockGray(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PATH_RED = registerBlock("path_red",
            () -> new PathBlockRed(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PATH_ORANGE = registerBlock("path_orange",
            () -> new PathBlockOrange(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PATH_YELLOW = registerBlock("path_yellow",
            () -> new PathBlockYellow(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PATH_GREEN = registerBlock("path_green",
            () -> new PathBlockGreen(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PATH_CYAN = registerBlock("path_cyan",
            () -> new PathBlockCyan(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PATH_BLUE = registerBlock("path_blue",
            () -> new PathBlockBlue(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PATH_MAGENTA = registerBlock("path_magenta",
            () -> new PathBlockMagenta(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> END = registerBlock("end",
            () -> new EndBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CANCEL = registerBlock("cancel",
            () -> new CancelBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));

    // DOT BLOCKS

    public static final RegistryObject<Block> DOT = registerBlock("dot",
            () -> new DotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DOT_GRAY = registerBlock("dot_gray",
            () -> new DotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.GRAY));
    public static final RegistryObject<Block> DOT_RED = registerBlock("dot_red",
            () -> new DotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.RED));
    public static final RegistryObject<Block> DOT_ORANGE = registerBlock("dot_orange",
            () -> new DotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.ORANGE));
    public static final RegistryObject<Block> DOT_YELLOW = registerBlock("dot_yellow",
            () -> new DotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.YELLOW));
    public static final RegistryObject<Block> DOT_GREEN = registerBlock("dot_green",
            () -> new DotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.GREEN));
    public static final RegistryObject<Block> DOT_CYAN = registerBlock("dot_cyan",
            () -> new DotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.CYAN));
    public static final RegistryObject<Block> DOT_BLUE = registerBlock("dot_blue",
            () -> new DotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.BLUE));
    public static final RegistryObject<Block> DOT_MAGENTA = registerBlock("dot_magenta",
            () -> new DotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.MAGENTA));

    // BLOBS

    public static final RegistryObject<Block> BLOB = registerBlock("blob",
            () -> new BlobBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLOB_WHITE = registerBlock("blob_white",
            () -> new BlobBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.WHITE));
    public static final RegistryObject<Block> BLOB_RED = registerBlock("blob_red",
            () -> new BlobBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.RED));
    public static final RegistryObject<Block> BLOB_ORANGE = registerBlock("blob_orange",
            () -> new BlobBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.ORANGE));
    public static final RegistryObject<Block> BLOB_YELLOW = registerBlock("blob_yellow",
            () -> new BlobBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.YELLOW));
    public static final RegistryObject<Block> BLOB_GREEN = registerBlock("blob_green",
            () -> new BlobBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.GREEN));
    public static final RegistryObject<Block> BLOB_CYAN = registerBlock("blob_cyan",
            () -> new BlobBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.CYAN));
    public static final RegistryObject<Block> BLOB_BLUE = registerBlock("blob_blue",
            () -> new BlobBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.BLUE));
    public static final RegistryObject<Block> BLOB_MAGENTA = registerBlock("blob_magenta",
            () -> new BlobBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.MAGENTA));

    // STARS

    public static final RegistryObject<Block> STAR = registerBlock("star",
            () -> new StarBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STAR_WHITE = registerBlock("star_white",
            () -> new StarBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.WHITE));
    public static final RegistryObject<Block> STAR_RED = registerBlock("star_red",
            () -> new StarBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.RED));
    public static final RegistryObject<Block> STAR_ORANGE = registerBlock("star_orange",
            () -> new StarBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.ORANGE));
    public static final RegistryObject<Block> STAR_YELLOW = registerBlock("star_yellow",
            () -> new StarBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.YELLOW));
    public static final RegistryObject<Block> STAR_GREEN = registerBlock("star_green",
            () -> new StarBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.GREEN));
    public static final RegistryObject<Block> STAR_CYAN = registerBlock("star_cyan",
            () -> new StarBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.CYAN));
    public static final RegistryObject<Block> STAR_BLUE = registerBlock("star_blue",
            () -> new StarBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.BLUE));
    public static final RegistryObject<Block> STAR_MAGENTA = registerBlock("star_magenta",
            () -> new StarBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.MAGENTA));

    // TRIANGLE BLOCKS

    public static final RegistryObject<Block> TRIX = registerBlock("trix",
            () -> new TriangleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TRIX_WHITE = registerBlock("trix_white",
            () -> new TriangleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.WHITE));
    public static final RegistryObject<Block> TRIX_RED = registerBlock("trix_red",
            () -> new TriangleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.RED));
    public static final RegistryObject<Block> TRIX_ORANGE = registerBlock("trix_orange",
            () -> new TriangleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.ORANGE));
    public static final RegistryObject<Block> TRIX_YELLOW = registerBlock("trix_yellow",
            () -> new TriangleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.YELLOW));
    public static final RegistryObject<Block> TRIX_GREEN = registerBlock("trix_green",
            () -> new TriangleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.GREEN));
    public static final RegistryObject<Block> TRIX_CYAN = registerBlock("trix_cyan",
            () -> new TriangleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.CYAN));
    public static final RegistryObject<Block> TRIX_BLUE = registerBlock("trix_blue",
            () -> new TriangleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.BLUE));
    public static final RegistryObject<Block> TRIX_MAGENTA = registerBlock("trix_magenta",
            () -> new TriangleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.MAGENTA));


    // TETRIS BLOCKS
    public static final RegistryObject<Block> TET = registerBlock("tet",
            () -> new TetBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TET_WHITE = registerBlock("tet_white",
            () -> new TetBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.WHITE));
    public static final RegistryObject<Block> TET_RED = registerBlock("tet_red",
            () -> new TetBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.RED));
    public static final RegistryObject<Block> TET_ORANGE = registerBlock("tet_orange",
            () -> new TetBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.ORANGE));
    public static final RegistryObject<Block> TET_YELLOW = registerBlock("tet_yellow",
            () -> new TetBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.YELLOW));
    public static final RegistryObject<Block> TET_GREEN = registerBlock("tet_green",
            () -> new TetBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.GREEN));
    public static final RegistryObject<Block> TET_CYAN = registerBlock("tet_cyan",
            () -> new TetBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.CYAN));
    public static final RegistryObject<Block> TET_BLUE = registerBlock("tet_blue",
            () -> new TetBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.BLUE));
    public static final RegistryObject<Block> TET_MAGENTA = registerBlock("tet_magenta",
            () -> new TetBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.MAGENTA));

    // TWIST BLOCKS

    public static final RegistryObject<Block> TWIST = registerBlock("twist",
            () -> new TwistBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TWIST_WHITE = registerBlock("twist_white",
            () -> new TwistBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.WHITE));
    public static final RegistryObject<Block> TWIST_RED = registerBlock("twist_red",
            () -> new TwistBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.RED));
    public static final RegistryObject<Block> TWIST_ORANGE = registerBlock("twist_orange",
            () -> new TwistBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.ORANGE));
    public static final RegistryObject<Block> TWIST_YELLOW = registerBlock("twist_yellow",
            () -> new TwistBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.YELLOW));
    public static final RegistryObject<Block> TWIST_GREEN = registerBlock("twist_green",
            () -> new TwistBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.GREEN));
    public static final RegistryObject<Block> TWIST_CYAN = registerBlock("twist_cyan",
            () -> new TwistBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.CYAN));
    public static final RegistryObject<Block> TWIST_BLUE = registerBlock("twist_blue",
            () -> new TwistBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.BLUE));
    public static final RegistryObject<Block> TWIST_MAGENTA = registerBlock("twist_magenta",
            () -> new TwistBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(6f).requiresCorrectToolForDrops(), Color.MAGENTA));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }



    // Start of user code block custom blocks
    // End of user code block custom blocks
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class BlocksClientSideHandler {
        @SubscribeEvent
        public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
            PathBlock.blockColorLoad(event);
            DotBlock.blockColorLoad(event);
        }
    }

}