package com.normalexisting.thewitness.block;

import com.normalexisting.thewitness.Reference;
import com.normalexisting.thewitness.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MODID);

    public static final RegistryObject<CreativeModeTab> THE_WITNESS_TAB = CREATIVE_MODE_TABS.register("the_witness",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BEGIN.get()))
                    .title(Component.translatable("creativetab.the_witness"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.BEGIN.get());
                        pOutput.accept(ModBlocks.BEGIN_GRAY.get());
                        pOutput.accept(ModBlocks.BEGIN_RED.get());
                        pOutput.accept(ModBlocks.BEGIN_ORANGE.get());
                        pOutput.accept(ModBlocks.BEGIN_YELLOW.get());
                        pOutput.accept(ModBlocks.BEGIN_GREEN.get());
                        pOutput.accept(ModBlocks.BEGIN_CYAN.get());
                        pOutput.accept(ModBlocks.BEGIN_BLUE.get());
                        pOutput.accept(ModBlocks.BEGIN_MAGENTA.get());

                        pOutput.accept(ModBlocks.PATH.get());
                        pOutput.accept(ModBlocks.PATH_GRAY.get());
                        pOutput.accept(ModBlocks.PATH_RED.get());
                        pOutput.accept(ModBlocks.PATH_ORANGE.get());
                        pOutput.accept(ModBlocks.PATH_YELLOW.get());
                        pOutput.accept(ModBlocks.PATH_GREEN.get());
                        pOutput.accept(ModBlocks.PATH_CYAN.get());
                        pOutput.accept(ModBlocks.PATH_BLUE.get());
                        pOutput.accept(ModBlocks.PATH_MAGENTA.get());

                        pOutput.accept(ModBlocks.DOT.get());
                        pOutput.accept(ModBlocks.DOT_GRAY.get());
                        pOutput.accept(ModBlocks.DOT_RED.get());
                        pOutput.accept(ModBlocks.DOT_ORANGE.get());
                        pOutput.accept(ModBlocks.DOT_YELLOW.get());
                        pOutput.accept(ModBlocks.DOT_GREEN.get());
                        pOutput.accept(ModBlocks.DOT_CYAN.get());
                        pOutput.accept(ModBlocks.DOT_BLUE.get());
                        pOutput.accept(ModBlocks.DOT_MAGENTA.get());

                        pOutput.accept(ModBlocks.BLOB.get());
                        pOutput.accept(ModBlocks.BLOB_WHITE.get());
                        pOutput.accept(ModBlocks.BLOB_RED.get());
                        pOutput.accept(ModBlocks.BLOB_ORANGE.get());
                        pOutput.accept(ModBlocks.BLOB_YELLOW.get());
                        pOutput.accept(ModBlocks.BLOB_GREEN.get());
                        pOutput.accept(ModBlocks.BLOB_CYAN.get());
                        pOutput.accept(ModBlocks.BLOB_BLUE.get());
                        pOutput.accept(ModBlocks.BLOB_MAGENTA.get());

                        pOutput.accept(ModBlocks.STAR.get());
                        pOutput.accept(ModBlocks.STAR_WHITE.get());
                        pOutput.accept(ModBlocks.STAR_RED.get());
                        pOutput.accept(ModBlocks.STAR_ORANGE.get());
                        pOutput.accept(ModBlocks.STAR_YELLOW.get());
                        pOutput.accept(ModBlocks.STAR_GREEN.get());
                        pOutput.accept(ModBlocks.STAR_CYAN.get());
                        pOutput.accept(ModBlocks.STAR_BLUE.get());
                        pOutput.accept(ModBlocks.STAR_MAGENTA.get());

                        pOutput.accept(ModBlocks.TRIX.get());
                        pOutput.accept(ModBlocks.TRIX_WHITE.get());
                        pOutput.accept(ModBlocks.TRIX_RED.get());
                        pOutput.accept(ModBlocks.TRIX_ORANGE.get());
                        pOutput.accept(ModBlocks.TRIX_YELLOW.get());
                        pOutput.accept(ModBlocks.TRIX_GREEN.get());
                        pOutput.accept(ModBlocks.TRIX_CYAN.get());
                        pOutput.accept(ModBlocks.TRIX_BLUE.get());
                        pOutput.accept(ModBlocks.TRIX_MAGENTA.get());

                        pOutput.accept(ModBlocks.TET.get());
                        pOutput.accept(ModBlocks.TET_WHITE.get());
                        pOutput.accept(ModBlocks.TET_RED.get());
                        pOutput.accept(ModBlocks.TET_ORANGE.get());
                        pOutput.accept(ModBlocks.TET_YELLOW.get());
                        pOutput.accept(ModBlocks.TET_GREEN.get());
                        pOutput.accept(ModBlocks.TET_CYAN.get());
                        pOutput.accept(ModBlocks.TET_BLUE.get());
                        pOutput.accept(ModBlocks.TET_MAGENTA.get());

                        pOutput.accept(ModBlocks.TWIST.get());
                        pOutput.accept(ModBlocks.TWIST_WHITE.get());
                        pOutput.accept(ModBlocks.TWIST_RED.get());
                        pOutput.accept(ModBlocks.TWIST_ORANGE.get());
                        pOutput.accept(ModBlocks.TWIST_YELLOW.get());
                        pOutput.accept(ModBlocks.TWIST_GREEN.get());
                        pOutput.accept(ModBlocks.TWIST_CYAN.get());
                        pOutput.accept(ModBlocks.TWIST_BLUE.get());
                        pOutput.accept(ModBlocks.TWIST_MAGENTA.get());




                        pOutput.accept(ModBlocks.END.get());
                        pOutput.accept(ModBlocks.CANCEL.get());




                        pOutput.accept(ModItems.RESET.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}