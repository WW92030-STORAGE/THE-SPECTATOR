package com.normalexisting.thewitness.datagen;

import com.normalexisting.thewitness.Reference;
import com.normalexisting.thewitness.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Reference.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        for (net.minecraftforge.registries.RegistryObject<Block> b : ModBlocks.BLOCKS.getEntries()) {
            System.out.println("BLOCK ADDED " + b.toString());
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(b.get());
        }

        for (net.minecraftforge.registries.RegistryObject<Block> b : ModBlocks.BLOCKS.getEntries()) {
            this.tag(BlockTags.NEEDS_STONE_TOOL).add(b.get());
        }

    }
}