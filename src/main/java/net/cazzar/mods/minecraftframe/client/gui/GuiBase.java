package net.cazzar.mods.minecraftframe.client.gui;

import net.cazzar.mods.minecraftframe.client.controls.Control;
import net.minecraft.client.gui.GuiScreen;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Cayde on 6/12/2014.
 */
public class GuiBase extends GuiScreen {
    private ContentPane contentPane = new ContentPane(this);
    protected int xSize = 0;
    protected int ySize = 0;
    protected int xPadding = 0;
    protected int yPadding = 0;

    private int rgb = -1;

//    List<Control> controls = Lists.newLinkedList();
    private boolean pauses = false;

    public void setPauses() {
        pauses = true;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return pauses;
    }

    public ContentPane getContentPane() {
        return contentPane;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        //Temp draw background
        final int xStart = (width - xSize) / 2;
        final int yStart = (height - ySize) / 2;

        drawRect(xStart, yStart, xStart + xSize, yStart + ySize, rgb);

        super.drawScreen(mouseX, mouseY, partialTicks);

        glColor4f(1, 1, 1, 1); //seriously, fuck you minecraft.

        glPushMatrix();

        glTranslatef(xStart + xPadding, yStart + yPadding, 0);
        getContentPane().render(mouseX - xStart - xPadding, mouseY - yStart - yPadding);

        glPopMatrix();
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        //we do not need to recalculate for every control
        final int xStart = (width - xSize) / 2;
        final int yStart = (height - ySize) / 2;

        super.mouseClicked(mouseX, mouseY, mouseButton);

        getContentPane().mouseClicked(mouseX - xStart - xPadding, mouseY - yStart - yPadding, mouseButton);
    }

    protected void setSize(int x, int y) {
        this.xSize = x;
        this.ySize = y;
    }

    protected void setSize(java.awt.Dimension size) {
        setSize(size.width, size.height);
    }

    protected void setPadding(int x, int y) {
        this.xPadding = x;
        this.yPadding = y;
    }

    public void add(Control control) {
        getContentPane().add(control);
        control.setParent(getContentPane());
    }

    public void pack() {
        getContentPane().pack();
    }
}
