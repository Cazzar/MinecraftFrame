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
    protected double uMin = 0;
    protected double uMax = 1;
    protected double vMin = 0;
    protected double vMax = 1;


    public ImageRender(ResourceLocation image) {
        this.image = image;
    }

    public ResourceLocation getImage() {
        return image;
    }

    public void setImage(ResourceLocation image) {
        this.image = image;
    }

    public void setUV(double u, double v) {
        if (u > 1 || v > 1) throw new IllegalArgumentException("U or V cannot be greater than 1!");

        this.uMin = u;
        this.vMin = v;
    }

    public void setUVMax(double u, double v) {
        if (u > 1 || v > 1) throw new IllegalArgumentException("U or V cannot be greater than 1!");

        this.uMax = u;
        this.vMax = v;
    }

    @Override
    public void render(int mouseX, int mouseY) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer render = tessellator.getWorldRenderer();

        Minecraft.getMinecraft().renderEngine.bindTexture(getImage()); // lets not forget this.

        render.startDrawingQuads();

        int w = getSize().width, h = getSize().height;

                             //X, Y, Z,   U, V
        render.addVertexWithUV(0, h, 0,   uMin, vMax);
        render.addVertexWithUV(w, h, 0,   uMax, vMax);
        render.addVertexWithUV(w, 0, 0,   uMax, vMin);
        render.addVertexWithUV(0, 0, 0,   uMax, vMax);

        tessellator.draw();

//        drawTexturedRectAbs(getImage(), 0, 0, 0, 0, w, h);
    }
}
