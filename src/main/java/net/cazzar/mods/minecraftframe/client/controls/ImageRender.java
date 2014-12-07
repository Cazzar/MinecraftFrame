package net.cazzar.mods.minecraftframe.client.controls;

import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Cayde on 8/12/2014.
 */
public class ImageRender extends Control {
    ResourceLocation image;

    public ImageRender(ResourceLocation image) {
        this.image = image;
    }

    public ResourceLocation getImage() {
        return image;
    }

    public void setImage(ResourceLocation image) {
        this.image = image;
    }

    @Override
    public void render(int mouseX, int mouseY) {
        GL11.glScalef(getSize().height / 255f, getSize().width / 255f, 0);
        drawTexturedRectAbs(image, 0, 0, 0, 0, 255, 255);
    }
}
