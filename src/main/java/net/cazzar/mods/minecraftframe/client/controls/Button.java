package net.cazzar.mods.minecraftframe.client.controls;

import net.minecraft.util.ResourceLocation;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Cayde on 6/12/2014.
 */
public class Button extends Control {
    private static final ResourceLocation texture = new ResourceLocation("minecraftframe", "buttons.png");
    String message;

    public Button(String message) {
        setMessage(message, true); // auto resizing
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message, boolean resize) {
        this.message = message;
        if (resize) compact();
    }

    public void compact() {
        int width = getFontRenderer().getStringWidth(message) + 10; //+10 for padding, since we are fairly constrained.
        int height = getFontRenderer().FONT_HEIGHT + 10; // same here again.

        this.setSize(new Dimension(width, height));
    }

    @Override
    public void render(int mouseX, int mouseY) {
        glPushMatrix();

        drawRect(0, 0, getSize().width, getSize().height, Color.black.getRGB());
        glColor3d(1, 1, 1); //Like, I am disliking the tesselator for this, it really should RESET ITS OWN COLOUR!
//        Color inner = Color.darkGray;

        int u = 0, v = 0;

        boolean renderBorder = isEnabled();

        if (isEnabled()) {
            if (isHovered(mouseX, mouseY)) v = 128;
            else u = 128;
        }

        drawTexturedRect(texture, 1, 1, u, v, getSize().width - 2, getSize().height - 2);

        drawRect(1, 1, getSize().width - 1, 2, 0x50FFFFFF); //ARGB!
        drawRect(1, 2, 2, getSize().height - 1, 0x50FFFFFF);
        drawRect(1, getSize().height - 3, getSize().width - 1, getSize().height - 1, 0x50303030);
        drawRect(getSize().width - 2, 1, getSize().width - 1, getSize().height - 2,  0x50303030);

        int stringColor = 0xe0e0e0; //vanilla, using hex literals so I can read the RGB
        if (!isEnabled()) {
          stringColor = 0xa0a0a0;
        } else if (isHovered(mouseX, mouseY)) {
            stringColor = 0xffffa0;
        }

        drawCentredString(message, this.getSize().width / 2, this.getSize().height / 2, stringColor);

        glPopMatrix();
    }

    protected void drawCentredString(String str, int x, int y, int color) {
        int nX = x - (getFontRenderer().getStringWidth(str) / 2);
        int nY = y - getFontRenderer().FONT_HEIGHT / 2; // mono height fonts.

        getFontRenderer().drawStringWithShadow(str, nX, nY, color); //draw the white string
    }
}
