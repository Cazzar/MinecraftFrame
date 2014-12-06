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
        super.drawScreen(mouseX, mouseY, partialTicks);

        for (Control control : controls) {
            GL11.glPushMatrix();
            //since we can be a little safer if we push and pop ourselves.
            GL11.glTranslated(control.getX(), control.getY(), 0);
            control.render(mouseX, mouseY);
            GL11.glPopMatrix();
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        for (Control control : controls) {
            //Since we dont want to send unnecessary function calls.
            boolean inX = (control.getX() <= mouseX) && mouseX <= (control.getX() + control.getSize().getWidth());
            boolean inY = (control.getY() <= mouseY) && mouseY <= (control.getY() + control.getSize().getHeight());

            if (inX && inY) {
                GuiEvent event = new GuiEvent(control, this, mouseX - control.getX(), mouseY - control.getY());
                control.onClicked(event);
            }
        }
    }

    public void add(Control control) {
        controls.add(control);
        control.setParent(this);
    }
}
