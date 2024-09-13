package com.normalexisting.thewitness.datagen;

import com.normalexisting.thewitness.block.Begin.BeginBlock;
import com.normalexisting.thewitness.block.Dot.DotBlock;
import com.normalexisting.thewitness.block.ModBlocks;
import com.normalexisting.thewitness.block.OtherThings.*;
import com.normalexisting.thewitness.block.Path.PathBlock;
import com.normalexisting.thewitness.block.PuzzleBlock;
import com.normalexisting.thewitness.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public static final int OUTPUT_N = 4;
    public static final int PATH_N = 16;
    public static final Item BASE_ITEM = Items.STONE;

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    static final Color COL[] = {Color.BLACK, Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA, Color.GRAY};

    static int getColorIndex(PuzzleBlock b) {
        Color c = b.COLOR;
        for (int i = 0; i < 10; i++) {
            if (c == COL[i]) return i;
        }
        return (int)(Math.random() * 10);
    }

    static final Item DYES[] = {Items.BLACK_DYE, Items.WHITE_DYE, Items.RED_DYE, Items.ORANGE_DYE, Items.YELLOW_DYE, Items.LIME_DYE, Items.CYAN_DYE, Items.BLUE_DYE, Items.MAGENTA_DYE, Items.GRAY_DYE};
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.END.get(), OUTPUT_N)
                .pattern("SSS")
                .pattern("SXS")
                .pattern("SSS")
                .define('S', BASE_ITEM)
                .define('X', Items.QUARTZ)
                .unlockedBy(getHasName(BASE_ITEM), has(BASE_ITEM))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CANCEL.get(), OUTPUT_N)
                .pattern("SSS")
                .pattern("SXS")
                .pattern("SSS")
                .define('S', BASE_ITEM)
                .define('X', Ingredient.of(Blocks.GLASS))
                .unlockedBy(getHasName(BASE_ITEM), has(BASE_ITEM))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RESET.get(), 1)
                .pattern("SSS")
                .pattern("SXS")
                .pattern("SSS")
                .define('S', Items.REDSTONE)
                .define('X', Items.QUARTZ)
                .unlockedBy(getHasName(BASE_ITEM), has(BASE_ITEM))
                .save(pWriter);

        for (RegistryObject<Block> b : ModBlocks.BLOCKS.getEntries()) {
            if (!(b.get() instanceof PuzzleBlock)) continue;
            if (b == ModBlocks.END) continue;
            System.out.println("ADDING RECIPE ... " + b);
            PuzzleBlock block = (PuzzleBlock)(b.get());
            int i = getColorIndex(block);
            if (block instanceof BeginBlock) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, b.get(), OUTPUT_N)
                        .pattern(" S ")
                        .pattern("SXS")
                        .pattern(" S ")
                        .define('S', BASE_ITEM)
                        .define('X', DYES[i])
                        .unlockedBy(getHasName(BASE_ITEM), has(BASE_ITEM))
                        .save(pWriter);
            }
            else if (block instanceof PathBlock) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, b.get(), PATH_N)
                        .pattern(" S ")
                        .pattern(" X ")
                        .pattern(" S ")
                        .define('S', BASE_ITEM)
                        .define('X', DYES[i])
                        .unlockedBy(getHasName(BASE_ITEM), has(BASE_ITEM))
                        .save(pWriter);
            }
            else if (block instanceof DotBlock) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, b.get(), OUTPUT_N)
                        .pattern("S S")
                        .pattern(" X ")
                        .pattern("S S")
                        .define('S', BASE_ITEM)
                        .define('X', DYES[i])
                        .unlockedBy(getHasName(BASE_ITEM), has(BASE_ITEM))
                        .save(pWriter);
            }
            else if (block instanceof BlobBlock) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, b.get(), OUTPUT_N)
                        .pattern("XXX")
                        .pattern("XSX")
                        .pattern("XXX")
                        .define('S', BASE_ITEM)
                        .define('X', DYES[i])
                        .unlockedBy(getHasName(BASE_ITEM), has(BASE_ITEM))
                        .save(pWriter);
            }
            else if (block instanceof StarBlock) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, b.get(), OUTPUT_N)
                        .pattern("SXS")
                        .pattern("XXX")
                        .pattern("SXS")
                        .define('S', BASE_ITEM)
                        .define('X', DYES[i])
                        .unlockedBy(getHasName(BASE_ITEM), has(BASE_ITEM))
                        .save(pWriter);
            }
            else if (block instanceof TriangleBlock) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, b.get(), OUTPUT_N)
                        .pattern("SXS")
                        .pattern("XXX")
                        .pattern("SSS")
                        .define('S', BASE_ITEM)
                        .define('X', DYES[i])
                        .unlockedBy(getHasName(BASE_ITEM), has(BASE_ITEM))
                        .save(pWriter);
            }
            else if (block instanceof TwistBlock) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, b.get(), OUTPUT_N)
                        .pattern("SSS")
                        .pattern("SXX")
                        .pattern("SXX")
                        .define('S', BASE_ITEM)
                        .define('X', DYES[i])
                        .unlockedBy(getHasName(BASE_ITEM), has(BASE_ITEM))
                        .save(pWriter);
            }
            else if (block instanceof TetBlock) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, b.get(), OUTPUT_N)
                        .pattern("XXX")
                        .pattern("XSS")
                        .pattern("XSS")
                        .define('S', BASE_ITEM)
                        .define('X', DYES[i])
                        .unlockedBy(getHasName(BASE_ITEM), has(BASE_ITEM))
                        .save(pWriter);
            }
        }
    }
}