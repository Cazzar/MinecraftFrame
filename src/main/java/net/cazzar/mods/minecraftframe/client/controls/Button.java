package net.cazzar.mods.minecraftframe.client.controls;

import net.cazzar.mods.minecraftframe.client.GLUtil;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Cayde on 6/12/2014.
 */
public class Button extends Control {
    String message;

    public Button(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message, boolean resize) {
        this.message = message;
        if (resize) {
            int width = getFontRenderer().getStringWidth(message) + 20; //+20 for padding, since we are fairly constrained.
            int height = getFontRenderer().FONT_HEIGHT + 20; // same here again.

            this.setSize(new Dimension(width, height));
        }
    }

    @Override
    public void render() {
        glBegin(GL_QUADS);
        glPushMatrix();

        GLUtil.glColorAWT(Color.BLACK);

        glVertex2i(0, 0);
        glVertex2i(getSize().width, 0);
        glVertex2i(getSize().width, getSize().height);
        glVertex2i(0, getSize().height);

        GLUtil.glColorAWT(Color.DARK_GRAY); // Probably will replace this with a tile-able texture. or even a 64*64 one.

        glVertex2i(5, 5);
        glVertex2i(getSize().width - 5, 5);
        glVertex2i(getSize().width - 5, getSize().height - 5);
        glVertex2i(5, getSize().height - 5);

        drawCentredString(message, this.getSize().width / 2, this.getSize().height / 2);

        glPopMatrix();
        glEnd();
    }

    protected void drawCentredString(String str, int x, int y) {
        int nX = x - (getFontRenderer().getStringWidth(str) / 2);
        int nY = y + getFontRenderer().FONT_HEIGHT / 2; // mono height fonts.

        getFontRenderer().drawStringWithShadow(str, nX, nY, -1); //draw the white string
    }
}
