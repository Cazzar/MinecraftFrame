package net.cazzar.mods.minecraftframe.client.controls;


import com.google.common.collect.Lists;
import net.cazzar.mods.minecraftframe.client.listener.GuiEvent;
import net.cazzar.mods.minecraftframe.client.listener.IListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
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
    boolean enabled;
    Gui parent;

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

        Tessellator tessellator = Tessellator.instance;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);

        GL11.glColor4f(r, g, b, a);
        tessellator.startDrawingQuads();
        tessellator.addVertex((double) x, (double) h, 0.0D);
        tessellator.addVertex((double) w, (double) h, 0.0D);
        tessellator.addVertex((double) w, (double) y, 0.0D);
        tessellator.addVertex((double) x, (double) y, 0.0D);
        tessellator.draw();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    public void drawTexturedRect(ResourceLocation texture, int x, int y, int w, int h, int u, int v) {
        ResourceLocation textureLocation = new ResourceLocation(texture.getResourceDomain(), "textures/gui/" + texture.getResourcePath());

        Minecraft.getMinecraft().renderEngine.bindTexture(textureLocation);
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double) (x + 0), (double) (y + v), 0, (double) ((float) (w + 0) * f), (double) ((float) (h + v) * f1));
        tessellator.addVertexWithUV((double) (x + u), (double) (y + v), 0, (double) ((float) (w + u) * f), (double) ((float) (h + v) * f1));
        tessellator.addVertexWithUV((double) (x + u), (double) (y + 0), 0, (double) ((float) (w + u) * f), (double) ((float) (h + 0) * f1));
        tessellator.addVertexWithUV((double) (x + 0), (double) (y + 0), 0, (double) ((float) (w + 0) * f), (double) ((float) (h + 0) * f1));
        tessellator.draw();
    }

    public Gui getParent() {
        return parent;
    }

    public void setParent(Gui parent) {
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
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
        for (IListener listener : listeners) listener.onClicked(event);
        playClickSound();
    }

    protected void playClickSound() {
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
    }

    protected FontRenderer getFontRenderer() {
        return Minecraft.getMinecraft().fontRenderer;
    }
}
