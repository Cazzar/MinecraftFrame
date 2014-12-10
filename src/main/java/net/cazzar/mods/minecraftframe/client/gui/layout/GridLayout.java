package net.cazzar.mods.minecraftframe.client.gui.layout;

import net.cazzar.mods.minecraftframe.client.controls.Control;
import net.cazzar.mods.minecraftframe.client.gui.ContentPane;
import net.cazzar.mods.minecraftframe.client.gui.ILayoutManager;

import java.awt.*;

/**
 * Created by Cayde on 10/12/2014.
 *
 * This is essentially a port over of the OpenJDK GridLayout for AWT containers.
 */
public class GridLayout implements ILayoutManager {
    int hgap;
    int vgap;
    int rows;
    int cols;

    public GridLayout() {
        this(1, 0, 0, 0);
    }

    public GridLayout(int rows, int cols) {
        this(rows, cols, 0, 0);
    }

    public GridLayout(int rows, int cols, int hgap, int vgap) {
        if ((rows == 0) && (cols == 0)) {
            throw new IllegalArgumentException("rows and cols cannot both be zero");
        }
        this.rows = rows;
        this.cols = cols;
        this.hgap = hgap;
        this.vgap = vgap;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if ((rows == 0) && (this.cols == 0)) {
            throw new IllegalArgumentException("rows and cols cannot both be zero");
        }
        this.rows = rows;
    }

    public int getColumns() {
        return cols;
    }

    public void setColumns(int cols) {
        if ((cols == 0) && (this.rows == 0)) {
            throw new IllegalArgumentException("rows and cols cannot both be zero");
        }
        this.cols = cols;
    }

    public int getHgap() {
        return hgap;
    }

    public void setHgap(int hgap) {
        this.hgap = hgap;
    }

    public int getVgap() {
        return vgap;
    }

    public void setVgap(int vgap) {
        this.vgap = vgap;
    }

    @Override
    public void addControl(Control control) {
    }

    @Override
    public void removeControl(Control control) {

    }

    @Override
    public void layoutContainer(ContentPane parent) {
        int ncomponents = parent.getControlCount();
        int nrows = rows;
        int ncols = cols;
        boolean ltr = true;

        if (ncomponents == 0) {
            return;
        }
        if (nrows > 0) {
            ncols = (ncomponents + nrows - 1) / nrows;
        } else {
            nrows = (ncomponents + ncols - 1) / ncols;
        }
        // 4370316. To position components in the center we should:
        // 1. get an amount of extra space within Container
        // 2. incorporate half of that value to the left/top position
        // Note that we use trancating division for widthOnComponent
        // The reminder goes to extraWidthAvailable
        int totalGapsWidth = (ncols - 1) * hgap;
        int widthWOInsets = parent.getSize().width - (parent.getXPadding() * 2);
        int widthOnComponent = (widthWOInsets - totalGapsWidth) / ncols;
        int extraWidthAvailable = (widthWOInsets - (widthOnComponent * ncols + totalGapsWidth)) / 2;

        int totalGapsHeight = (nrows - 1) * vgap;
        int heightWOInsets = parent.getSize().height - (parent.getYPadding() * 2);
        int heightOnComponent = (heightWOInsets - totalGapsHeight) / nrows;
        int extraHeightAvailable = (heightWOInsets - (heightOnComponent * nrows + totalGapsHeight)) / 2;
        if (ltr) {
            for (int c = 0, x = parent.getXPadding() + extraWidthAvailable; c < ncols ; c++, x += widthOnComponent + hgap) {
                for (int r = 0, y = parent.getYPadding() + extraHeightAvailable; r < nrows ; r++, y += heightOnComponent + vgap) {
                    int i = r * ncols + c;
                    if (i < ncomponents) {
                        parent.getControl(i).setPosition(x, y);
                        parent.getControl(i).setSize(widthOnComponent, heightOnComponent);
                    }
                }
            }
        } else {
            for (int c = 0, x = (parent.getSize().width - parent.getXPadding() - widthOnComponent) - extraWidthAvailable; c < ncols ; c++, x -= widthOnComponent + hgap) {
                for (int r = 0, y = parent.getYPadding() + extraHeightAvailable; r < nrows ; r++, y += heightOnComponent + vgap) {
                    int i = r * ncols + c;
                    if (i < ncomponents) {
                        parent.getControl(i).setPosition(x, y);
                        parent.getControl(i).setSize(widthOnComponent, heightOnComponent);
                    }
                }
            }
        }
    }

    @Override
    public Dimension getSize(ContentPane parent) {
        int ncomponents = parent.getControlCount();
        int nrows = rows;
        int ncols = cols;

        if (nrows > 0) {
            ncols = (ncomponents + nrows - 1) / nrows;
        } else {
            nrows = (ncomponents + ncols - 1) / ncols;
        }
        int w = 0;
        int h = 0;
        for (int i = 0 ; i < ncomponents ; i++) {
            Control comp = parent.getControl(i);
            Dimension d = comp.getSize();
            if (w < d.width) {
                w = d.width;
            }
            if (h < d.height) {
                h = d.height;
            }
        }
        return new Dimension(parent.getXPadding()*2 + ncols*w + (ncols-1)*hgap,
                             parent.getYPadding()*2 + nrows*h + (nrows-1)*vgap);
    }
}
