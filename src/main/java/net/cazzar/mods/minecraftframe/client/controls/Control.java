package net.cazzar.mods.minecraftframe.client.controls;


import com.google.common.collect.Lists;
import net.cazzar.mods.minecraftframe.client.listener.GuiEvent;
import net.cazzar.mods.minecraftframe.client.listener.IListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;

/**
 * Created by Cayde on 6/12/2014.
 */
public abstract class Control {
    List<IListener> listeners = Lists.newLinkedList();
    int x, y;
    Dimension size;
    boolean enabled = true;
    Control parent;
    private boolean visible = true;

    protected static void drawRect(int x, int y, int w, int h, int color) {
        if (x < w) {
            int temp = x;
            x = w;
            w = temp;
        }

        if (y < h) {
            int temp = y;
            y = h;
            h = temp;
        }

        float a = (float) (color >> 24 & 255) / 255.0F;
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRender = tessellator.getWorldRenderer();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);

        GL11.glColor4f(r, g, b, a);
        worldRender.startDrawingQuads();
        worldRender.addVertex((double) x, (double) h, 0.0D);
        worldRender.addVertex((double) w, (double) h, 0.0D);
        worldRender.addVertex((double) w, (double) y, 0.0D);
        worldRender.addVertex((double) x, (double) y, 0.0D);
        tessellator.draw();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public void drawTexturedRect(ResourceLocation texture, int x, int y, int u, int v, int w, int h) {
        ResourceLocation textureLocation = new ResourceLocation(texture.getResourceDomain(), "textures/gui/" + texture.getResourcePath());

        drawTexturedRectAbs(textureLocation, x, y, u, v, w, h);
    }

    public void drawTexturedRectAbs(ResourceLocation texture, int x, int y, int u, int v, int w, int h) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tesselator = Tessellator.getInstance();
        WorldRenderer worldrender = tesselator.getWorldRenderer();
        worldrender.startDrawingQuads();
        worldrender.addVertexWithUV((double) (x + 0), (double) (y + h), 0, (double) ((float) (u + 0) * f), (double) ((float) (v + h) * f1));
        worldrender.addVertexWithUV((double) (x + w), (double) (y + h), 0, (double) ((float) (u + w) * f), (double) ((float) (v + h) * f1));
        worldrender.addVertexWithUV((double) (x + w), (double) (y + 0), 0, (double) ((float) (u + w) * f), (double) ((float) (v + 0) * f1));
        worldrender.addVertexWithUV((double) (x + 0), (double) (y + 0), 0, (double) ((float) (u + 0) * f), (double) ((float) (v + 0) * f1));
        tesselator.draw();
    }

    public void drawTexturedRectAbs(ResourceLocation texture, int x, int y, int u, int v, int w, int h, int uMax, int vMax) {
        //U V
        //X Y
        //W H

        for (int x1 = x; x1 < (x + w); x1 += uMax ) {
            for (int y1 = y; y1 < (y + h); y1 += vMax) {
                drawTexturedRectAbs(texture, x1, y1, u, v, Math.min(w - x1 + 1, uMax), Math.min(h - y1 + 1, vMax));
            }
        }
    }

    public void drawTexturedRect(ResourceLocation texture, int x, int y, int u, int v, int w, int h, int uMax, int vMax) {
        ResourceLocation textureLocation = new ResourceLocation(texture.getResourceDomain(), "textures/gui/" + texture.getResourcePath());

        drawTexturedRectAbs(textureLocation, x, y, u, v, w, h, uMax, vMax);
    }

    public Control getParent() {
        return parent;
    }

    public void setParent(Control parent) {
        this.parent = parent;
    }

    /**
     * This should handle all rendering of each control,
     * assuming translation is already done by the system.
     *
     * @param mouseX the mouse position in relative to the top right hand corner of the GUI
     * @param mouseY the mouse position in relative to the top right hand corner of the GUI
     */
    public abstract void render(int mouseX, int mouseY);

    public boolean isHovered(int mouseX, int mouseY) {
        boolean inX = (this.getX() <= mouseX) && mouseX <= (this.getX() + this.getSize().getWidth());
        boolean inY = (this.getY() <= mouseY) && mouseY <= (this.getY() + this.getSize().getHeight());

        return inX && inY;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public void setSize(int w, int h) { // this is a helper, but
        setSize(new Dimension(w, h));
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean addListener(IListener listener) {
        return listeners.add(listener);
    }

    public void onClicked(GuiEvent event) {
        postEvent(event);
        playClickSound();
    }

    public void postEvent(GuiEvent event) {
        for (IListener listener : listeners) listener.onEvent(event);
    }

    protected void playClickSound() {
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
    }

    protected FontRenderer getFontRenderer() {
        return Minecraft.getMinecraft().fontRendererObj;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
