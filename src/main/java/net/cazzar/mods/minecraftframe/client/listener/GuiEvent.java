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

    public GuiEvent(Control source, ContentPane parent, int x, int y) {
        this.source = source;
        this.parent = parent;
        this.x = x;
        this.y = y;
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

    public static class Click extends GuiEvent {
        private final int mouseButton;

        public Click(Control source, ContentPane parent, int x, int y, int mouseButton) {
            super(source, parent, x, y);
            this.mouseButton = mouseButton;
        }

        public int getMouseButton() {
            return mouseButton;
        }
    }

    public static class Hover extends GuiEvent {
        public Hover(Control source, ContentPane parent, int x, int y) {
            super(source, parent, x, y);
        }
    }
}
