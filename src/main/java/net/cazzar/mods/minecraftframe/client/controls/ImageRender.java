package net.cazzar.mods.minecraftframe.client.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Cayde on 8/12/2014.
 */
public class ImageRender extends Control {
    protected ResourceLocation image;

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
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer render = tessellator.getWorldRenderer();

        Minecraft.getMinecraft().renderEngine.bindTexture(getImage()); // lets not forget this.

        render.startDrawingQuads();

        int w = getSize().width, h = getSize().height;

                             //X, Y, Z,   U, V
        render.addVertexWithUV(0, h, 0,   0, 1);
        render.addVertexWithUV(w, h, 0,   1, 1);
        render.addVertexWithUV(w, 0, 0,   1, 0);
        render.addVertexWithUV(0, 0, 0,   0, 0);

        tessellator.draw();

//        drawTexturedRectAbs(getImage(), 0, 0, 0, 0, w, h);
    }
}
