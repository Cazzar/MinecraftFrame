package net.cazzar.mods.minecraftframe.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

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
        public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
            p_149727_5_.openGui(MinecraftFrame.this, 0, p_149727_1_, 1, 1, 1);
            return true;
        }
    }
}
