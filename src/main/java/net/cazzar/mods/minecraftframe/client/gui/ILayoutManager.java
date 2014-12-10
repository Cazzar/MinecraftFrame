package net.cazzar.mods.minecraftframe.client.gui;

import net.cazzar.mods.minecraftframe.client.controls.Control;

import java.awt.*;

/**
 * Created by Cayde on 8/12/2014.
 */
public interface ILayoutManager {
    void addControl(Control control);
    void removeControl(Control control);
    void layoutContainer(ContentPane parent);
    Dimension getSize(ContentPane parent); // since we are not resizable.
}
