package Graphics;

import java.awt.*;

public class Button extends Area {
    Collision checker = new Collision();
    boolean wasIn = false;
    boolean beganIn = false;
    private double mouseX, mouseY;

    public Button(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public Button(double x, double y, double w, double h, String t) {
        super(x, y, w, h, t);
    }

    public boolean isPressed(double x, double y, double xOrig, double yOrig, boolean justPressed, boolean pressed,
            Button occupied) {
        mouseX = x;
        mouseY = y;
        if (justPressed) {
            beganIn = isIn(xOrig, yOrig) && justPressed;
        }
        if (pressed && beganIn) {
            if (wasIn == false) {
                wasIn = isIn(x, y);
            }
            if (wasIn && occupied.isClear()) {
                occupied = this;
            }
            if (occupied == this) {
                return wasIn;
            } else {
                return false;
            }
        } else {
            wasIn = false;
            return false;
        }
    }

    private boolean isIn(double x, double y) {
        return checker.autoIsIn(x, y, this) || checker.autoIsIn(x, y, getBoundingBox());// checker.autoIsIn(x, y, this)
                                                                                        // ||
        // checker.autoIsIn(x, y,
        // getMidBar());
    }

    public void drawState(Graphics g, Color unpressed, Color pressed, boolean filled, boolean filledPressed,
            String type, double mX, double mY, double xOrig, double yOrig, boolean justPressed, boolean ispressed,
            Button occupied) {
        if (isPressed(mX, mY, xOrig, yOrig, justPressed, ispressed, occupied)) {// !this.isPressed(mX, mY)) {
            draw(g, unpressed, filled, type);
        } else {
            draw(g, pressed, filledPressed, type);
        }
    }

    public boolean isPressed(Point p) {
        return false;// isPressed(p.getX(), p.getY());
    }

    public boolean isClear() {
        return getType().equals("clear");// && getX() == 0 && getY() == 0 && getW() == 0 && getH() == 0;
    }

    public Area getMidBar() {
        return this;
    }

    public Area getBoundingBox() {
        return this;
    }

    public void doAction() {
        System.out.println("button was pressed");
    }
}