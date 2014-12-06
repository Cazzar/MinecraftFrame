package net.cazzar.mods.minecraftframe.client.listener;

import net.cazzar.mods.minecraftframe.client.controls.Control;
import net.cazzar.mods.minecraftframe.client.gui.GuiBase;

/**
 * Created by Cayde on 6/12/2014.
 */
public class GuiEvent {
    private final Control source;
    private final GuiBase gui;
    private final int x;
    private final int y;

    public GuiEvent(Control source, GuiBase gui, int x, int y) {
        this.source = source;
        this.gui = gui;
        this.x = x;
        this.y = y;
    }

    public Control getSource() {
        return source;
    }

    public GuiBase getGui() {
        return gui;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
