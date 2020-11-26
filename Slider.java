package Graphics;

import Graphics.Mouse;
import java.awt.*;

public class Slider extends Button {
    boolean vertical, horizontal;

    public double midBarX, midBarY, midBarW, midBarH;
    private Area midBar;
    private double midBarWH = 3;

    public double min = 0.0, max = 1.0;

    public Slider(double x, double y, double w, double h, boolean vert, boolean hori, double small, double big) {
        super(x, y, w, h);
        min = small;
        max = big;
        if (!(vert == true && hori == true)) {
            vertical = vert;
            horizontal = hori;
        }
    }

    public Slider(double x, double y, double w, double h, boolean vert, boolean hori, double min, double max,
            double small, double big) {
        this(x, y, w, h, vert, hori, small, big);
        if (horizontal) {
            setMinY(y);
            setMaxY(y);
            setMinX(min);
            setMaxX(max);
            midBarX = min;
            midBarW = max - min;
            midBarH = midBarWH;
            midBarY = y - (int) midBarH / 2;
        } else if (vertical) {
            setMinX(x);
            setMaxX(x);
            setMinY(min);
            setMaxY(max);
            midBarY = min;
            midBarH = max - min;
            midBarW = midBarWH;
            midBarX = x - (int) midBarW / 2;
        }
        midBar = new Area(midBarX, midBarY, midBarW, midBarH, "rect");
    }

    public Slider(double x, double y, double w, double h, boolean vert, boolean hori, double min, double max,
            double small, double big, double initial) {
        this(x, y, w, h, vert, hori, min, max, small, big);
        setVal(initial);
    }

    public void setMinCoor(double coor) {
        if (horizontal) {
            setMinX(coor);
        } else if (vertical) {
            setMinY(coor);
        }
    }

    public void setMaxCoor(double coor) {
        if (horizontal) {
            setMaxX(coor);
        } else if (vertical) {
            setMaxY(coor);
        }
    }

    private void updateMidBar() {
        if (horizontal) {
            midBarX = getMinX();
            midBarW = getMaxX() - getMinX();
            midBarH = midBarWH;
            midBarY = getY() - (int) midBarH / 2;
        } else if (vertical) {
            midBarY = getMinY();
            midBarH = getMaxY() - getMinY();
            midBarW = midBarWH;
            midBarX = getX() - (int) midBarW / 2;
        }
        midBar = new Area(midBarX, midBarY, midBarW, midBarH, "rect");
    }

    protected void slide(Graphics g, Color unpressed, Color pressed, boolean filled, boolean filledPressed, String type,
            double mouseX, double mouseY, double xOrig, double yOrig, boolean justPressed, boolean ispressed,
            Button occupied) {
        updateMidBar();
        if (isPressed(mouseX, mouseY, xOrig, yOrig, justPressed, ispressed, occupied)) {
            setXSafe(mouseX);
            setYSafe(mouseY);
        } else {
            setXSafe(getX());
            setYSafe(getY());
        }
        drawState(g, unpressed, pressed, filled, filledPressed, type, mouseX, mouseY, xOrig, yOrig, justPressed,
                ispressed, occupied);
        midBar.draw(g, Color.gray, true);
        new Area(getX(), getY(), 5, 5).draw(g, Color.white, true, "oval");
    }

    public void slide(Graphics g, Color unpressed, Color pressed, boolean filled, boolean filledPressed, String type,
            Mouse m) {
        slide(g, unpressed, pressed, filled, filledPressed, type, m.getX(), m.getY(), m.getXClicked(), m.getYClicked(),
                m.getJustClicked(), m.getIsPressed(), m.getOccupied());
    }

    public double getVal(double minimum, double maximum) {
        min = minimum;
        max = maximum;
        return getVal();
    }

    public double getVal() {
        if (horizontal) {
            return map(getX(), getMinX(), getMaxX(), min, max);// min + ((max - min) / (getMaxX() - getMinX())) *
                                                               // (getX() - getMinX());
        } else if (vertical) {
            return map(getY(), getMinY(), getMaxY(), min, max);// min + ((max - min) / (getMaxY() - getMinY())) *
                                                               // (getY() - getMinY());
        }
        return 0.0;
    }

    public void setVal(double val) {
        if (horizontal) {
            setXSafe(map(val, min, max, getMinX(), getMaxX()));
        } else if (vertical) {
            setYSafe(map(val, min, max, getMinY(), getMaxY()));
        }

    }

    private double map(double input, double inputMin, double inputMax, double outputMin, double outputMax) {
        return outputMin + ((outputMax - outputMin) / (inputMax - inputMin)) * (input - inputMin);
    }

    @Override
    public Area getMidBar() {
        updateMidBar();
        return midBar;
    }

    @Override
    public Area getBoundingBox() {
        updateMidBar();
        if (getType().equals("oval")) {
            if (horizontal) {
                return new Area(getMinX(), getMinY() - getH() / 2, midBarW, getH() + 1, "rect");
            } else if (vertical) {
                return new Area(getMinX() - getW() / 2, getMinY(), getW(), midBarH, "rect");
            }
        }
        return new Area(0, 0, 0, 0);
    }

}