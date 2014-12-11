package net.cazzar.mods.minecraftframe.test.common;

import net.cazzar.mods.minecraftframe.client.controls.Button;
import net.cazzar.mods.minecraftframe.client.controls.ImageRender;
import net.cazzar.mods.minecraftframe.client.gui.GuiBase;
import net.cazzar.mods.minecraftframe.client.gui.layout.GridLayout;
import net.minecraft.util.ResourceLocation;

public class ExampleGUI extends GuiBase {
    private final Button control = new Button("OMGWTFBBQSAUCE!");
    ImageRender img = new ImageRender(new ResourceLocation("minecraftframe:textures/gui/image.png"));

    public ExampleGUI() {
//        setSize(200, 100);
        getContentPane().setLayoutManager(new GridLayout(3, 3, 5, 5));
        setPadding(10, 10);
        addControls();

        pack(); // call this to make pretty
    }

    public void addControls() {
        add(control);
        img.setSize(100, 100);
        add(img);
        for (int i = 0; i < 7; i ++) {
            add(new Button("Button" + ((i == 4) ? ". BUTTON!? " : " ") + (i + 1)));
        }
    }
}
