package Graphics;

import Graphics.Shape;
import Graphics.Button;

public class Mouse {
    private double x = 0, y = 0, xBeforeClicked = 0, yBeforeClicked = 0;
    private boolean isPressed = false;
    private Button occupiedRegion = new Button(0, 0, 0, 0, "clear", 0);
    private int delay;

    public Mouse(int d) {
        delay = d;
    }

    public void setX(double newX) {
        x = newX;
    }

    public void setY(double newY) {
        y = newY;
    }

    public void setXClciked(double newX) {
        xBeforeClicked = newX;
    }

    public void setYClicked(double newY) {
        yBeforeClicked = newY;
    }

    public void setIsPressed(boolean ispressed) {
        isPressed = ispressed;
    }

    public void setOccupied(Button newRegion) {
        occupiedRegion = newRegion;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getXClicked() {
        return xBeforeClicked;
    }

    public double getYClicked() {
        return yBeforeClicked;
    }

    public boolean getIsPressed() {
        return isPressed;
    }

    public Button getOccupied() {
        return occupiedRegion;
    }

    public void update(double newX, double newY) {
        setX(newX);
        setY(newY);

        if (!isPressed) {
            xBeforeClicked = x;// MouseInfo.getPointerInfo().getLocation().x;
            yBeforeClicked = y;// MouseInfo.getPointerInfo().getLocation().y;
        }
    }

    public void update() {
        if (!isPressed) {
            xBeforeClicked = x;// MouseInfo.getPointerInfo().getLocation().x;
            yBeforeClicked = y;// MouseInfo.getPointerInfo().getLocation().y;
        }
    }

    public void clear() {
        setOccupied(new Button(0, 0, 0, 0, "clear", delay));
    }
}