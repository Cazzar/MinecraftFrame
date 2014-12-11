package net.cazzar.mods.minecraftframe.test.common;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = "feature_1")
public class MinecraftFrame {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        GameRegistry.registerBlock(new ExampleBlock(), "example");
    }

    private class ExampleBlock extends Block {
        public ExampleBlock() {
            super(Material.rock);
        }

        @Override
        public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
            if (worldIn.isRemote) playerIn.openGui(MinecraftFrame.this, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());

            return true;
        }
    }
}
