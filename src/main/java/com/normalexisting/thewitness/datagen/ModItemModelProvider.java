package com.normalexisting.thewitness.datagen;

import com.normalexisting.thewitness.Reference;
import com.normalexisting.thewitness.block.Begin.BeginBlock;
import com.normalexisting.thewitness.block.OtherThings.*;
import com.normalexisting.thewitness.block.Dot.DotBlock;
import com.normalexisting.thewitness.block.End.EndBlock;
import com.normalexisting.thewitness.block.ModBlocks;
import com.normalexisting.thewitness.block.Path.PathBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Reference.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // evenSimplerBlockItem(ModBlocks.PATH, "path");

        for (RegistryObject<Block> b : ModBlocks.BLOCKS.getEntries()) {
            Block bb = b.get();
            if (bb instanceof BeginBlock) continue;
            else if (bb instanceof EndBlock) evenSimplerBlockItem(b, "");
            else if (bb instanceof CancelBlock) evenSimplerBlockItem(b, "");
            else if (bb instanceof DotBlock) evenSimplerBlockItem(b, "dot");
            else if (bb instanceof PathBlock) evenSimplerBlockItem(b, "path");
            else if (bb instanceof BlobBlock) evenSimplerBlockItem(b, "blob");
            else if (bb instanceof StarBlock) evenSimplerBlockItem(b, "star");
            else if (bb instanceof TriangleBlock) evenSimplerBlockItem(b, "trix");
            else if (bb instanceof TwistBlock) evenSimplerBlockItem(b, "twist");
            else if (bb instanceof TetBlock) evenSimplerBlockItem(b, "tet");
        }
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block, String substr) {
        String s2 = (substr.length() > 0) ? "/" : "";
        this.withExistingParent(Reference.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + substr + s2 + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Reference.MODID,"item/" + item.getId().getPath()));
    }
}
