package net.cazzar.mods.minecraftframe.client.gui;

import com.google.common.collect.Lists;
import net.cazzar.mods.minecraftframe.client.controls.Control;
import net.cazzar.mods.minecraftframe.client.listener.GuiEvent;

import java.awt.*;
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

    public ILayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(ILayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    private ILayoutManager layoutManager = null;

    public ContentPane(GuiBase parent) {
        this.parent = parent;
    }

    public int getXPadding() {
        return xPadding;
    }

    public void setxPadding(int xPadding) {
        this.xPadding = xPadding;
    }

    public int getYPadding() {
        return yPadding;
    }

    public void setyPadding(int yPadding) {
        this.yPadding = yPadding;
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

            if (control.isHovered(mouseX, mouseY))
                control.postEvent(new GuiEvent.Hover(control, this, mouseX - control.getX(), mouseY - control.getY()));

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
                GuiEvent event = new GuiEvent.Click(control, this, mouseX - control.getX(), mouseY - control.getY(), mouseButton);
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
        }
    }

    @Override
    public Dimension getSize() {
        if (super.getSize() == null && layoutManager != null) this.setSize(layoutManager.getSize(this));

        return super.getSize();
    }

    public void pack() {
        if (layoutManager != null) {
            this.setSize(layoutManager.getSize(this));

            layoutManager.layoutContainer(this);

            if (getParentGui() != null) {
                Dimension size = layoutManager.getSize(this);
                size.height += getParentGui().xPadding * 2;
                size.width += getParentGui().yPadding * 2;
                getParentGui().setSize(size);
            }
        }
    }
}
