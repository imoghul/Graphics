package Graphics;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Drawer {

    protected double x, y;
    protected String type;// "type subType subsubType ...", ex: "rect centered rounded"
    protected double minX = Double.MIN_VALUE;
    protected double minY = Double.MIN_VALUE;
    protected double maxX = Double.MAX_VALUE;
    protected double maxY = Double.MAX_VALUE;

    public PIDController xcontroller;
    public PIDController ycontroller;
    public int delay = 20;// Main.timerSpeed;

    public Drawer(double newX, double newY, double P, double I, double D, int d) {
        x = newX;
        y = newY;
        delay = d;
        xcontroller = new PIDController(P, I, D, (double) delay / 1000.0);
        ycontroller = new PIDController(P, I, D, (double) delay / 1000.0);
    }

    public Drawer(Drawer newOne) {
        x = newOne.getX();
        y = newOne.getY();
        type = newOne.getType();
        minX = newOne.getMinX();
        maxX = newOne.getMaxX();
        minY = newOne.getMinY();
        maxY = newOne.getMaxY();
        xcontroller = newOne.xcontroller;
        ycontroller = newOne.ycontroller;
        // this.delay = newOne.delay;
    }

    public Drawer(double newX, double newY, int d) {
        x = newX;
        y = newY;
        delay = d;
        xcontroller = new PIDController(1, 0, 0, (double) delay / 1000.0);
        ycontroller = new PIDController(1, 0, 0, (double) delay / 1000.0);
    }

    public Drawer(double newX, double newY, String t, int d) {
        this(newX, newY, d);
        type = t;
    }

    public Drawer(double newX, double newY, double P, double I, double D, String t, int d) {
        this(newX, newY, P, I, D, d);
        type = t;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getType() {
        if (type != null && type.split(" ").length >= 1) {
            return type.split(" ")[0];
        }
        return "";
    }

    public String getSubType() {
        if (type != null && type.split(" ").length > 1) {
            return type.split(" ")[1];
        } else if (type != null && !(type.split(" ").length > 1)) {
            return "normal";
        }
        return "";
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxX(double newx) {
        maxX = newx;
    }

    public void setMinX(double newx) {
        minX = newx;
    }

    public void setMaxY(double newy) {
        maxY = newy;
    }

    public void setMinY(double newy) {
        minY = newy;
    }

    public void setX(double newX) {

        x = newX; // - getW(); // -getW/2 if top corner is needed

    }

    public void setY(double newY) {

        y = newY; // - getH(); // -getH/2 if top corner is needed

    }

    public void setXSafe(double newX) {
        if (newX >= minX && newX <= maxX) {
            x = newX; // - getW(); // -getW/2 if top corner is needed
        } else if (newX <= minX) {
            x = minX;
        } else if (newX > maxX) {
            x = maxX;
        }
    }

    public void setYSafe(double newY) {
        if (newY >= minY && newY <= maxY) {
            y = newY; // - getH(); // -getH/2 if top corner is needed
        } else if (newY <= minY) {
            y = minY;
        } else if (newY > maxY) {
            y = maxY;
        }
    }

    public void updateControllers(double p, double i, double d) {
        xcontroller.setP(p);
        xcontroller.setI(i);
        xcontroller.setD(d);
        ycontroller.setP(p);
        ycontroller.setI(i);
        ycontroller.setD(d);
    }

    public boolean setXPID(double desired) {
        double actual = getX();
        if (Math.abs(actual - desired) > 0.01) {
            actual = xcontroller.PIDout(actual, desired);
            setX(actual);
            // try {
            // Thread.sleep(delay);
            // } catch (InterruptedException ex) {
            // Thread.currentThread().interrupt();
            // }
            return false;
        } else {
            xcontroller.reset();
            return true;
        }
    }

    public boolean setYPID(double desired) {
        double actual = getY();
        if (Math.abs(actual - desired) > 0.01) {
            actual = ycontroller.PIDout(actual, desired);
            setY(actual);
            // try {
            // Thread.sleep(delay);
            // } catch (InterruptedException ex) {
            // Thread.currentThread().interrupt();
            // }
            return false;
        } else {
            ycontroller.reset();
            return true;
        }
    }

    public void incrementX(double i) {
        setX(getX() + i);
    }

    public void incrementY(double i) {
        setY(getY() + i);
    }

    public void clear(Graphics g) {
        g.setColor(Color.black);
        // g.drawRect(1, 1, 1, 1);
    }

    public boolean isClear() {
        return getType().equals("clear");
    }

    public void sleep() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

}
