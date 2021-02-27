package Graphics;

import Graphics.Mouse;
import Graphics.Text;
import java.awt.*;

public class Button extends Shape {
    Collision checker = new Collision();
    boolean wasIn = false;
    boolean beganIn = false;
    private double mouseX, mouseY;
    public Text label;

    public Button(double x, double y, double w, double h, int d) {
        super(x, y, w, h, d);
    }

    public Button(double x, double y, double w, double h, String t, int d) {
        super(x, y, w, h, t, d);
    }

    public Button(Button b) {
        this(b.getX(), b.getY(), b.getW(), b.getH(), b.getTypeFull(), b.delay);
    }

    protected boolean isPressed(double x, double y, double xOrig, double yOrig, boolean pressed, Button occupied) {
        mouseX = x;
        mouseY = y;
        boolean justPressed = !pressed;

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

            // or just return true, but for some reason I spent a long time on this so leave
            // it;
        } else {
            wasIn = false;
            return false;
        }
    }

    public boolean isPressed(Mouse m) {
        return isPressed(m.getX(), m.getY(), m.getXClicked(), m.getYClicked(), m.getIsPressed(), m.getOccupied());
    }

    private boolean isIn(double x, double y) {
        return checker.autoIsIn(x, y, this) || checker.autoIsIn(x, y, getBoundingBox()) || checker.autoIsIn(x, y, getMidBar());
        // checker.autoIsIn(x, y, this) || checker.autoIsIn(x, y,getMidBar());
    }

    public void drawState(Graphics g, Color unpressed, Color pressed, boolean filled, boolean filledPressed,
                          String type, Mouse m) {
        drawState(g, unpressed, pressed, filled, filledPressed, type, m.getX(), m.getY(), m.getXClicked(),
                  m.getYClicked(), m.getIsPressed(), m.getOccupied());
    }

    protected void drawState(Graphics g, Color unpressed, Color pressed, boolean filled, boolean filledPressed,
                             String type, double mX, double mY, double xOrig, double yOrig, boolean isPressed, Button occupied) {

        if (isPressed(mX, mY, xOrig, yOrig, isPressed, occupied)) {// !this.isPressed(mX, mY)) {
            draw(g, pressed, filledPressed, type);
        } else {
            draw(g, unpressed, filled, type);
        }

        if (!(label == null)) {
            label.setX(this.getCenterX());
            label.setY(this.getCenterY());
            label.drawCentered(g);
        }
    }

    public Text getLabel() {
        return this.label;
    }

    public void run(Graphics g, Color unpressed, Color pressed, boolean filled, boolean filledPressed, String type,
                    Mouse m) {
        if (wasIn && !isPressed(m.getX(), m.getY(), m.getXClicked(), m.getYClicked(), m.getIsPressed(), m.getOccupied())
                && isIn(m.getX(), m.getY())) {
            doAction();
        }

        update();
        drawState(g, unpressed, pressed, filled, filledPressed, type, m.getX(), m.getY(), m.getXClicked(),
                  m.getYClicked(), m.getIsPressed(), m.getOccupied());
    }

    public boolean isClear() {
        return getType().equals("clear");// && getX() == 0 && getY() == 0 && getW() == 0 && getH() == 0;
    }

    public Shape getMidBar() {
        return this;
    }

    public Shape getBoundingBox() {
        return this;
    }

    public void setLabel(String s) {
        this.label = new Text(s, this.getCenterX(), this.getCenterY(), this.delay);
    }

    // to override, does this every time run is called and it is pressed
    public void doAction() {

    }

    // to override, does this every time run is called
    public void update() {

    }

    protected void center(double displayW, double displayH) {
        if (getType().equals("rect")) {
            if (getSubType().equals("centered")) {
                setX(displayW / 2.0);
                setY(displayH / 2.0);
            } else if (getSubType().equals("normal")) {
                setX((displayW / 2) - getW() / 2);
                setY((displayH / 2) - getH() / 2);
            }
        } else if (getType().equals("oval")) {
            setX(displayW / 2.0);
            setY(displayH / 2.0);
        }
    }
}