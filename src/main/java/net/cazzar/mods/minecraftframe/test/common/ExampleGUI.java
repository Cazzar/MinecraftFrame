package net.cazzar.mods.minecraftframe.test.common;

        import net.cazzar.mods.minecraftframe.client.controls.Button;
        import net.cazzar.mods.minecraftframe.client.gui.GuiBase;
        import net.cazzar.mods.minecraftframe.client.listener.GuiEvent;
        import net.cazzar.mods.minecraftframe.client.listener.IListener;

        import java.awt.*;

/**
 * Created by Cayde on 6/12/2014.
 */
public class ExampleGUI extends GuiBase {
    private final Button control = new Button("btn");

    public ExampleGUI() {
        setSize(100, 100);
        setPadding(10, 10);
        add(control);
        control.addListener(new IListener() {
            @Override
            public void onClicked(GuiEvent e) {
                e.getSource().setSize(new Dimension(40, 40));
            }
        });
    }
}
