package Graphics;

import Graphics.Area;

public class Mouse {
    private double x = 0, y = 0, xClicked = 0, yClicked = 0;
    private boolean isPressed = false, justClicked = false;
    private Area occupiedRegion = new Area(0, 0, 0, 0, "clear");

    public void setX(double newX) {
        x = newX;
    }

    public void setY(double newY) {
        y = newY;
    }

    public void setXClciked(double newX) {
        xClicked = newX;
    }

    public void setYClicked(double newY) {
        yClicked = newY;
    }

    public void setIsPressed(boolean ispressed) {
        isPressed = ispressed;
    }

    public void setJustClicked(boolean justclicked) {
        justClicked = justclicked;
    }

    public void setOccupied(Area newRegion) {
        occupiedRegion = newRegion;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getXClciked() {
        return xClicked;
    }

    public double getYClicked() {
        return yClicked;
    }

    public boolean getIsPressed() {
        return isPressed;
    }

    public boolean getJustClicked() {
        return justClicked;
    }

    public Area getOccupied() {
        return occupiedRegion;
    }

    public void update(double newX, double newY) {
        setX(newX);
        setY(newY);
        if (!isPressed) {
            xClicked = x;// MouseInfo.getPointerInfo().getLocation().x;
            yClicked = y;// MouseInfo.getPointerInfo().getLocation().y;
            justClicked = true;
        } else {
            justClicked = false;
        }
    }

    public void clear() {
        setOccupied(new Area(0, 0, 0, 0, "clear"));
    }
}