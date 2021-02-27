package Graphics;

import Graphics.Mouse;
import java.awt.*;

public class Slider extends Button {
    boolean vertical, horizontal;
    // for child classes: super(b.getX(), b.getY(), b.getW(), b.getH(),
    // b.getTypeFull(), b.delay);
    public double midBarX, midBarY, midBarW, midBarH;
    private Shape midBar;
    private double midBarThickness = 3;
    public double lastVal = 0;

    public double min = 0.0, max = 1.0;

    public Slider(double x, double y, double w, double h, boolean vert, boolean hori, double small, double big, int d) {
        super(x, y, w, h, d);
        min = small;
        max = big;
        setOrientation(vert, hori);
    }

    private void setOrientation(boolean v, boolean h) {
        if (!(v == h)) {
            vertical = v;
            horizontal = h;
        } else {
            throw new IllegalArgumentException("Can't be Vertical and Horizontal");
        }
    }

    public Slider(double x, double y, double w, double h, boolean vert, boolean hori, double min, double max,
                  double small, double big, int d) {
        this(x, y, w, h, vert, hori, small, big, d);

        if (horizontal) {
            setMinY(y);
            setMaxY(y);
            setMinX(min);
            setMaxX(max);
            midBarX = min;
            midBarW = max - min;
            midBarH = midBarThickness;
            midBarY = y - (int) midBarH / 2;
        } else if (vertical) {
            setMinX(x);
            setMaxX(x);
            setMinY(min);
            setMaxY(max);
            midBarY = min;
            midBarH = max - min;
            midBarW = midBarThickness;
            midBarX = x - (int) midBarW / 2;
        }

        midBar = new Shape(midBarX, midBarY, midBarW, midBarH, "rect normal", d);
    }

    public Slider(double x, double y, double w, double h, boolean vert, boolean hori, double min, double max,
                  double small, double big, double initial, int d) {
        this(x, y, w, h, vert, hori, min, max, small, big, d);
        lastVal = initial;
        setVal(initial);
    }

    public Slider(Slider s) {
        this(s.getX(), s.getY(), s.getW(), s.getH(), s.vertical, s.horizontal, s.getMinCoor(), s.getMaxCoor(), s.min,
             s.max, s.lastVal, s.delay);
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

    public double getMaxCoor() {
        if (horizontal) {
            return getMaxX();
        } else if (vertical) {
            return getMaxY();
        }

        return 0;
    }

    public double getMinCoor() {
        if (horizontal) {
            return getMinX();
        } else if (vertical) {
            return getMinY();
        }

        return 0;
    }

    private void updateMidBar() {
        if (horizontal) {
            midBarX = getMinX();
            midBarW = getMaxX() - getMinX();
            midBarH = midBarThickness;
            midBarY = getY() - (int) midBarH / 2;
        } else if (vertical) {
            midBarY = getMinY();
            midBarH = getMaxY() - getMinY();
            midBarW = midBarThickness;
            midBarX = getX() - (int) midBarW / 2;
        }

        midBar = new Shape(midBarX, midBarY, midBarW, midBarH, "rect", delay);
    }

    protected void slide(Graphics g, Color unpressed, Color pressed, boolean filled, boolean filledPressed, String type,
                         double mouseX, double mouseY, double xOrig, double yOrig, boolean ispressed, Button occupied) {
        updateMidBar();
        setVal(lastVal);

        if (isPressed(mouseX, mouseY, xOrig, yOrig, ispressed, occupied)) {
            setXSafe(mouseX);
            setYSafe(mouseY);
        } else {
            setXSafe(getX());
            setYSafe(getY());
        }

        drawState(g, unpressed, pressed, filled, filledPressed, type, mouseX, mouseY, xOrig, yOrig, ispressed,
                  occupied);
        midBar.draw(g, Color.gray, true);
        new Shape(getX(), getY(), 5, 5, delay).draw(g, Color.white, true, "oval");
    }

    public void slide(Graphics g, Color unpressed, Color pressed, boolean filled, boolean filledPressed, String type,
                      Mouse m) {
        slide(g, unpressed, pressed, filled, filledPressed, type, m.getX(), m.getY(), m.getXClicked(), m.getYClicked(),
              m.getIsPressed(), m.getOccupied());
    }

    public double getVal(double minimum, double maximum) {
        min = minimum;
        max = maximum;
        return getVal();
    }

    public double getVal() {
        if (horizontal) {
            lastVal = map(getX(), getMinX(), getMaxX(), min, max);// min + ((max - min) / (getMaxX() - getMinX())) *
            // (getX() - getMinX());
            return lastVal;
        } else if (vertical) {
            lastVal = map(getY(), getMinY(), getMaxY(), min, max);// min + ((max - min) / (getMaxY() - getMinY())) *
            // (getY() - getMinY());
            return lastVal;
        }

        return 0.0;
    }

    public void setVal(double val) {
        lastVal = val;

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
    public Shape getMidBar() {
        updateMidBar();
        return midBar;
    }

    @Override
    public Shape getBoundingBox() {
        updateMidBar();

        if (getType().equals("oval")) {
            if (horizontal) {
                return new Shape(getMinX(), getMinY() - getH() / 2, midBarW, getH() + 1, "rect normal", delay);
            } else if (vertical) {
                return new Shape(getMinX() - getW() / 2, getMinY(), getW(), midBarH, "rect normal", delay);
            }
        } else if (getType().equals("rect")) {
            if (getSubType().equals("normal")) {
                if (horizontal) {
                    return new Shape(getMinX(), getMinY(), midBarW, getH(), "rect normal", delay);
                } else if (vertical) {
                    return new Shape(getMinX(), getMinY(), getW(), midBarH, "rect normal", delay);
                }

            } else if (getSubType().equals("centered")) {
                if (horizontal) {
                    return new Shape(getMinX(), getMinY() - getH() / 2, midBarW, getH() + 1, "rect normal", delay);
                } else if (vertical) {
                    return new Shape(getMinX() - getW() / 2, getMinY(), getW(), midBarH, "rect normal", delay);
                }
            }
        }

        return new Shape(0, 0, 0, 0, delay);
    }

    // to override, does this every iteration run is called and should set the value
    // of whatever this slider denotes
    public void doAction() {
    }

    // to override, does this every iteration run is called and should set the value
    // of whatever this slider denotes
    public void update() {
    }

    public void run(Graphics g, Color unpressed, Color pressed, boolean filled, boolean filledPressed, String type,
                    Mouse m) {
        update();
        slide(g, unpressed, pressed, filled, filledPressed, type, m.getX(), m.getY(), m.getXClicked(), m.getYClicked(),
              m.getIsPressed(), m.getOccupied());
        doAction();

    }

    protected void center(double displayW, double displayH, double offset) {
        if (horizontal) {
            setMinCoor((displayW / 2) - (displayW * offset));
            setMaxCoor((displayW / 2) + (displayW * offset));
        } else if (vertical) {
            setMinCoor((displayH / 2) - (displayH * offset));
            setMaxCoor((displayH / 2) + (displayH * offset));
        }
    }

}