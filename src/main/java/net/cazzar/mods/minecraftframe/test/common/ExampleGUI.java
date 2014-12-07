package net.cazzar.mods.minecraftframe.test.common;

import net.cazzar.mods.minecraftframe.client.controls.Button;
import net.cazzar.mods.minecraftframe.client.controls.ImageRender;
import net.cazzar.mods.minecraftframe.client.gui.GuiBase;
import net.cazzar.mods.minecraftframe.client.listener.GuiEvent;
import net.cazzar.mods.minecraftframe.client.listener.IListener;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

/**
 * Created by Cayde on 6/12/2014.
 */
public class ExampleGUI extends GuiBase {
    private final Button control = new Button("btn");

    public ExampleGUI() {
        setSize(200, 100);
        setPadding(10, 10);
        add(control);
        control.addListener(new IListener() {
            @Override
            public void onClicked(GuiEvent e) {
                e.getSource().setSize(new Dimension(40, 40));
            }
        });

        ImageRender img = new ImageRender(new ResourceLocation("minecraftframe:textures/gui/image.png"));
        img.setPosition(30, 0);
        img.setSize(80, 80);
        add(img);
    }
}
