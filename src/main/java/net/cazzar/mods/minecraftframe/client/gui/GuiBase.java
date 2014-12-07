package net.cazzar.mods.minecraftframe.client.gui;

import com.google.common.collect.Lists;
import net.cazzar.mods.minecraftframe.client.controls.Control;
import net.cazzar.mods.minecraftframe.client.listener.GuiEvent;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Created by Cayde on 6/12/2014.
 */
public class GuiBase extends GuiScreen {
    protected int xSize = 0;
    protected int ySize = 0;
    protected int xPadding = 0;
    protected int yPadding = 0;

    private int rgb = -1;

    List<Control> controls = Lists.newLinkedList();
    private boolean pauses = false;

    public void setPauses() {
        pauses = true;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return pauses;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        //Temp draw background
        final int xStart = (width - xSize) / 2;
        final int yStart = (height - ySize) / 2;

        drawRect(xStart, yStart, xStart + xSize, yStart + ySize, rgb);

        super.drawScreen(mouseX, mouseY, partialTicks);

        GL11.glColor3f(1, 1, 1); //seriously, fuck you minecraft.

        GL11.glPushMatrix();

        GL11.glTranslated(xStart + xPadding, yStart + yPadding, 0);

        for (Control control : controls) {
            if (!control.isVisible()) continue;

            GL11.glPushMatrix();
            //since we can be a little safer if we push and pop ourselves.
            GL11.glTranslated(control.getX(), control.getY(), 0);
            control.render(mouseX - xStart - xPadding, mouseY - yStart - xPadding);
            GL11.glPopMatrix();
        }

        GL11.glPopMatrix();
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        //we do not need to recalculate for every control
        final int xStart = (width - xSize) / 2;
        final int yStart = (height - ySize) / 2;

        super.mouseClicked(mouseX, mouseY, mouseButton);

        for (Control control : controls) {
            if (!control.isVisible() || !control.isEnabled()) continue; //ignore it if it is disabled

            //Since we dont want to send unnecessary function calls.
            int controlX = control.getX() + xStart + xPadding;
            int controlY = control.getY() + yStart + xPadding;

            boolean inX = (controlX <= mouseX) && mouseX <= (controlX + control.getSize().getWidth());
            boolean inY = (controlY <= mouseY) && mouseY <= (controlY + control.getSize().getHeight());

            if (inX && inY) {
                GuiEvent event = new GuiEvent(control, this, mouseX - control.getX(), mouseY - control.getY());
                control.onClicked(event);
            }
        }
    }

    protected void onClick(GuiEvent event) {

    }

    protected void setSize(int x, int y) {
        this.xSize = x;
        this.ySize = y;
    }

    protected void setPadding(int x, int y) {
        this.xPadding = x;
        this.yPadding = y;
    }

    public void add(Control control) {
        controls.add(control);
        control.setParent(this);
    }
}
