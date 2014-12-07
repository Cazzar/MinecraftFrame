package net.cazzar.mods.minecraftframe.client;

import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * Created by Cayde on 6/12/2014.
 */
public class GLUtil {
    public static void glColorAWT(Color color) {
        GL11.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
    }
}
