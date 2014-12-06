package net.cazzar.mods.minecraftframe.common;

import net.cazzar.mods.minecraftframe.client.controls.Button;
import net.cazzar.mods.minecraftframe.client.gui.GuiBase;
import net.cazzar.mods.minecraftframe.client.listener.GuiEvent;
import net.cazzar.mods.minecraftframe.client.listener.IListener;

/**
 * Created by Cayde on 6/12/2014.
 */
public class ExampleGUI extends GuiBase {
    private final Button control = new Button("Example button");

    public ExampleGUI() {

        add(control);
        control.setPostition(20, 20);
        control.addListener(new IListener() {
            @Override
            public void onClicked(GuiEvent e) {
                System.out.println(e.getSource());
            }
        });
    }
}
