package net.cazzar.mods.minecraftframe.client.gui;

import com.google.common.collect.Lists;
import net.cazzar.mods.minecraftframe.client.controls.Control;
import net.cazzar.mods.minecraftframe.client.listener.GuiEvent;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

/**
 * Created by Cayde on 6/12/2014.
 */
public class GuiBase extends GuiScreen {
    List<Control> controls = Lists.newLinkedList();

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
}
