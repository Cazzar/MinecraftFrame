package net.cazzar.mods.minecraftframe.client.controls;

import org.lwjgl.input.Mouse;

import java.awt.*;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

/**
 * Created by Cayde on 6/12/2014.
 */
public class Button extends Control {
    String message;

    public Button(String message) {
        setMessage(message, true); // auto resizing
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message, boolean resize) {
        this.message = message;
        if (resize) {
            int width = getFontRenderer().getStringWidth(message) + 10; //+10 for padding, since we are fairly constrained.
            int height = getFontRenderer().FONT_HEIGHT + 10; // same here again.

            this.setSize(new Dimension(width, height));
        }
    }

    @Override
    public void render(int mouseX, int mouseY) {
        glPushMatrix();

        drawRect(0, 0, getSize().width, getSize().height, Color.black.getRGB());
        Color inner = Color.darkGray;

        boolean inX = (this.getX() <= mouseX) && mouseX <= (this.getX() + this.getSize().getWidth());
        boolean inY = (this.getY() <= mouseY) && mouseY <= (this.getY() + this.getSize().getHeight());

        if (inX && inY) {
            inner = Mouse.isButtonDown(0) ? Color.cyan : Color.red;
        }

        drawRect(2, 2, getSize().width - 2, getSize().height - 2, inner.getRGB());

        drawCentredString(message, this.getSize().width / 2, this.getSize().height / 2);

        glPopMatrix();
    }

    protected void drawCentredString(String str, int x, int y) {
        int nX = x - (getFontRenderer().getStringWidth(str) / 2);
        int nY = y - getFontRenderer().FONT_HEIGHT / 2; // mono height fonts.

        getFontRenderer().drawStringWithShadow(str, nX, nY, -1); //draw the white string
    }
}
