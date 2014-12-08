package net.cazzar.mods.minecraftframe.client.listener;

import net.cazzar.mods.minecraftframe.client.controls.Control;
import net.cazzar.mods.minecraftframe.client.gui.ContentPane;

/**
 * Created by Cayde on 6/12/2014.
 */
public class GuiEvent {
    private final Control source;
    private final ContentPane parent;
    private final int x;
    private final int y;
    private final int mouseButton;

    public GuiEvent(Control source, ContentPane parent, int x, int y, int mouseButton) {
        this.source = source;
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.mouseButton = mouseButton;
    }

    public Control getSource() {
        return source;
    }

    public ContentPane getParent() {
        return parent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMouseButton() {
        return mouseButton;
    }
}
