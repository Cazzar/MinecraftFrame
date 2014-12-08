package net.cazzar.mods.minecraftframe.client.gui;

import com.google.common.collect.Lists;
import net.cazzar.mods.minecraftframe.client.controls.Control;
import net.cazzar.mods.minecraftframe.client.listener.GuiEvent;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Cayde on 8/12/2014.
 */
public class ContentPane extends Control {
    protected int xPadding = 0;
    protected int yPadding = 0;
    protected List<Control> controls = Lists.newArrayList();
    GuiBase parent;
    private ILayoutManager layoutManager = null;

    public ContentPane(GuiBase parent) {
        this.parent = parent;
    }

    @Override
    public void render(int mouseX, int mouseY) {
        glTranslated(xPadding, yPadding, 0);

        for (Control control : controls) {
            if (!control.isVisible()) continue;

            glPushMatrix();
            //since we can be a little safer if we push and pop ourselves.
            glTranslated(control.getX(), control.getY(), 0);
            glColor4f(1, 1, 1, 1);

            control.render(mouseX - xPadding, mouseY - xPadding);
            glPopMatrix();
        }
    }


    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        //we do not need to recalculate for every control
        final int xStart = 0;
        final int yStart = 0;

        for (Control control : controls) {
            if (!control.isVisible() || !control.isEnabled()) continue; //ignore it if it is disabled

            //Since we dont want to send unnecessary function calls.
            int controlX = control.getX() + xStart + xPadding;
            int controlY = control.getY() + yStart + xPadding;

            boolean inX = (controlX <= mouseX) && mouseX <= (controlX + control.getSize().getWidth());
            boolean inY = (controlY <= mouseY) && mouseY <= (controlY + control.getSize().getHeight());

            if (inX && inY) {
                GuiEvent event = new GuiEvent(control, this, mouseX - control.getX(), mouseY - control.getY(), mouseButton);
                control.onClicked(event);
            }
        }
    }

    public GuiBase getParentGui() {
        return parent;
    }

    public void add(Control control) {
        controls.add(control);
        control.setParent(this);
        if (layoutManager != null) {
            layoutManager.addControl(control);
            layoutManager.layoutContainer();
            getParentGui().setSize(layoutManager.getSize());
        }
    }

    public int getControlCount() {
        return controls.size();
    }

    public Control getControl(int i) {
        return controls.get(i);
    }

    public void remove(Control control) {
        control.setParent(null);
        controls.remove(control);
        if (layoutManager != null) {
            layoutManager.removeControl(control);
            layoutManager.layoutContainer();
            getParent().setSize(layoutManager.getSize());
        }
    }
}
