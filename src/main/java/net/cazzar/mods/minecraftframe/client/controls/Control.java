package net.cazzar.mods.minecraftframe.client.controls;


import com.google.common.collect.Lists;
import net.cazzar.mods.minecraftframe.client.listener.GuiEvent;
import net.cazzar.mods.minecraftframe.client.listener.IListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

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

    public Gui getParent() {
        return parent;
    }

    public void setParent(Gui parent) {
        this.parent = parent;
    }

    /**
     * This should handle all rendering of each control,
     * assuming translation is already done by the system.
     */
    public abstract void render();

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public void setPostition(int x, int y) {
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
