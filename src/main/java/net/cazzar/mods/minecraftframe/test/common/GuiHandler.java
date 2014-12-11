package net.cazzar.mods.minecraftframe.test.common;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Cayde on 6/12/2014.
 */
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new Container() {
            @Override
            public boolean canInteractWith(EntityPlayer p_75145_1_) {
                return true;
            }
        };
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new ExampleGUI();
    }
}
