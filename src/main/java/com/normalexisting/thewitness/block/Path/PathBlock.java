package com.normalexisting.thewitness.block.Path;

import com.normalexisting.thewitness.Reference;
import com.normalexisting.thewitness.block.Begin.BeginBlock;
import com.normalexisting.thewitness.block.ModBlocks;
import com.normalexisting.thewitness.block.PuzzleBlock;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.ticks.ScheduledTick;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import org.checkerframework.checker.units.qual.C;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.function.ToIntFunction;

public class PathBlock extends PuzzleBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    // If there is a line running through a given position. The line is formed as the trail of the player as they traverse the grid.
    public static HashSet<BlockPos> isOccupied = new HashSet<BlockPos>();
    // If the puzzle is solved and the completed line should be permanently displayed.
    public static HashSet<BlockPos> isSolved = new HashSet<BlockPos>();
    // What block the player last stepped on.
    public static HashMap<UUID, BlockPos> steppingOn = new HashMap<UUID, BlockPos>();



    public static final boolean SHOW_PARTICLES = false;




    Block reference0 = Blocks.FURNACE;
    Block obs = Blocks.OBSERVER;
    Block lamp = Blocks.REDSTONE_LAMP;
    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
        };
    }

    public PathBlock(BlockBehaviour.Properties properties) {
        super(properties.lightLevel(litBlockEmission(15)));
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.valueOf(false)));
        COLOR = Color.WHITE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState();
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }


    public static boolean shouldLightUp(BlockPos pos) {
        return (pos != null && (isSolved.contains(pos) || (activePlayer.containsKey(pos)) && ((BeginBlock.chosenpath.get(activePlayer.get(pos)).contains(pos))) ));
    }


    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player,
                                 InteractionHand hand, BlockHitResult blockHitResult) {
        super.use(state, level, blockPos, player, hand, blockHitResult);
        // Server: Main Hand & Off Hand
        // Client: Main Hand & Off Hand

        // player.sendSystemMessage(Component.literal("!!!"));

        InteractionResult intres = super.use(state, level, blockPos, player, hand, blockHitResult);
        level.updateNeighborsAt(blockPos, level.getBlockState(blockPos).getBlock());



        return intres;
    }

    public Player getPlayer(ServerLevel world, BlockPos pos) {
        PlayerList players = world.getServer().getPlayerList();
        for (Player player : players.getPlayers()) {
            if (Math.floor(player.getX()) == pos.getX() && Math.floor(player.getZ()) == pos.getZ()) {
                if (player.getY() >= pos.getY() && player.getY() <= pos.getY() + Reference.MAX_PLAYER_HEIGHT) {
                    return player;
                }
            }
        }
        return null;
    }

    public boolean checkPlayerUUID(Level world, BlockPos pos, UUID uuid) {
        Player player = world.getPlayerByUUID(uuid);
        // System.out.println("...." + pos.toString() + " / " + activePlayer.toString());
        boolean hpa = false;
        if (Math.floor(player.getX()) == pos.getX() && Math.floor(player.getZ()) == pos.getZ()) {
            if (player.getY() >= pos.getY() && player.getY() <= pos.getY() + Reference.MAX_PLAYER_HEIGHT) {
                hpa = true;
            }
        }
        return hpa;
    }

    public boolean checkActivePlayer(ServerLevel world, BlockPos pos) {
        // System.out.println("...." + pos.toString() + " / " + activePlayer.toString());
        PlayerList players = world.getServer().getPlayerList();
        Player p = null;
        boolean hpa = false;
        if (!activePlayer.containsKey(pos)) return false;
        for (Player player : players.getPlayers()) {
            if (player.getUUID() != activePlayer.get(pos)) continue;
            if (Math.floor(player.getX()) == pos.getX() && Math.floor(player.getZ()) == pos.getZ()) {
                if (player.getY() >= pos.getY() && player.getY() <= pos.getY() + Reference.MAX_PLAYER_HEIGHT) {
                    hpa = true;
                }
            }
        }
        return hpa;
    }

    public boolean checkForPlayer(ServerLevel world, BlockPos pos) {
        // System.out.println("...." + pos.toString() + " / " + activePlayer.toString());
        PlayerList players = world.getServer().getPlayerList();
        Player p = null;
        boolean hpa = false;
        for (Player player : players.getPlayers()) {
            if (Math.floor(player.getX()) == pos.getX() && Math.floor(player.getZ()) == pos.getZ()) {
                if (player.getY() >= pos.getY() && player.getY() <= pos.getY() + Reference.MAX_PLAYER_HEIGHT) {
                    hpa = true;
                }
            }
        }
        return hpa;
    }

    boolean MSGDB = false;

    // This is called every tick. For certain blocks, such as the starting point, that can affect the results, this is called once again during its execution.
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rs) {
        super.tick(state, level, pos, rs);
        level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(shouldLightUp(pos))), 2);
        level.updateNeighborsAt(pos, level.getBlockState(pos).getBlock());
        level.neighborChanged(pos, this, pos);
        try {

            // First we check for an active player...
            if (!activePlayer.containsKey(pos)) {

                /*

                if (isSolved.contains(pos)) {
                    ParticleOptions particle = ParticleTypes.DRAGON_BREATH;
                    if (Math.random() < 0.5)
                        level.sendParticles(particle, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, 1, 0.1, 0.1, 0.1, 0);
                }
                */
                return;
            }
            UUID uuid = activePlayer.get(pos);

            if (MSGDB && Math.random() < 1.0 / 64.0)
                Reference.msgall("BOUNDING BOX " + boundingbox.get(pos) + " HAS PLAYER " + uuid.toString(), level);

            // Now we check if the player is directly on this block.
            if (checkActivePlayer(level, pos)) {
                // Remember this happens when a player decides to step on the block.
                // This means that chosenpath does not store this block yet.
                if (steppingOn.get(uuid) != pos) {
                    // At this point steppingOn.get(uuid) is the block that the player left off from.
                    // pos is now the block that the player determines to step on.
                    if (BeginBlock.chosenpath.get(uuid).size() == 0) BeginBlock.chosenpath.get(uuid).add(pos);
                    else if (BeginBlock.chosenpath.get(uuid).size() > 1) {
                        if (pos == BeginBlock.chosenpath.get(uuid).get(BeginBlock.chosenpath.get(uuid).size() - 2)) {
                            BeginBlock.chosenpath.get(uuid).remove(BeginBlock.chosenpath.get(uuid).size() - 1);
                        } else if (BeginBlock.chosenpath.get(uuid).contains(pos)) { // UH OH SPAGHETTIO
                            AABB aabb = boundingbox.get(pos);
                            ArrayList<BlockPos> thelist = getBlocksInBB(level, boundingbox.get(pos), true);
                            boundingbox.remove(pos);
                            for (BlockPos p : thelist) {
                                boundingbox.remove(p);
                                activePlayer.remove(p);
                            }
                            reps.remove(aabb);
                            BeginBlock.chosenpath.remove(uuid);
                            steppingOn.remove(uuid);
                            return;
                        } else BeginBlock.chosenpath.get(uuid).add(pos);
                    } else BeginBlock.chosenpath.get(uuid).add(pos);
                    steppingOn.put(uuid, pos);
                }
            }


            // And finally ... the fireworks

            if (SHOW_PARTICLES) {

                ParticleOptions particle = ParticleTypes.END_ROD;
                if (BeginBlock.chosenpath.get(uuid).contains(pos) || isSolved.contains(pos))
                    particle = ParticleTypes.DRAGON_BREATH;
                if (Math.random() < 0.5)
                    level.sendParticles(particle, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, 1, 0.1, 0.1, 0.1, 0);

            }
        }
        catch (Exception e) {
            // System.out.println(e);
        }
    }


    @OnlyIn(Dist.CLIENT)
    public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.PATH.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.PATH_GRAY.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.PATH_RED.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.PATH_ORANGE.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.PATH_YELLOW.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.PATH_GREEN.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.PATH_CYAN.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.PATH_BLUE.get());

        event.getBlockColors().register((bs, world, pos, index) -> {
            return (world != null && shouldLightUp(pos)) ? Reference.LIT : Reference.DARK;
        }, ModBlocks.PATH_MAGENTA.get());


    }
}