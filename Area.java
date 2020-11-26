package Graphics;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Area {

    private double x, y, w, h;
    private String type;
    private double minX = Double.MIN_VALUE;
    private double minY = Double.MIN_VALUE;
    private double maxX = Double.MAX_VALUE;
    private double maxY = Double.MAX_VALUE;

    public PIDController xcontroller;
    public PIDController ycontroller;
    int delay = 10;// Main.timerSpeed;

    public Area(double newX, double newY, double newW, double newH, double P, double I, double D) {
        x = newX;
        y = newY;
        w = newW;
        h = newH;
        xcontroller = new PIDController(P, I, D, (double) delay / 1000.0);
        ycontroller = new PIDController(P, I, D, (double) delay / 1000.0);
    }

    public Area(Area newOne) {
        x = newOne.getX();
        y = newOne.getY();
        w = newOne.getW();
        h = newOne.getH();
        type = newOne.getType();
        minX = newOne.getMinX();
        maxX = newOne.getMaxX();
        minY = newOne.getMinY();
        maxY = newOne.getMaxY();
        xcontroller = newOne.xcontroller;
        ycontroller = newOne.ycontroller;
        delay = newOne.delay;
    }

    public Area(double newX, double newY, double newW, double newH) {
        x = newX;
        y = newY;
        w = newW;
        h = newH;
    }

    public Area(double newX, double newY, double newW, double newH, String t) {
        this(newX, newY, newW, newH);
        type = t;
    }

    public Area(double newX, double newY, double newW, double newH, double P, double I, double D, String t) {
        this(newX, newY, newW, newH, P, I, D);
        type = t;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public String getType() {
        if (type != null) {
            return type;
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
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
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
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            return false;
        } else {
            ycontroller.reset();
            return true;
        }
    }

    public void setW(double newW) {
        w = newW;
    }

    public void setH(double newH) {
        h = newH;
    }

    public void incrementX(double i) {
        setX(getX() + i);
    }

    public void incrementY(double i) {
        setY(getY() + i);
    }

    public void incrementW(double i) {
        w += i;
    }

    public void incrementH(double i) {
        h += i;
    }

    public void travelVector(int x, int y, int m) {
        System.out.println((int) ((double) x / (double) gcd(x, y)) + ", " + (int) ((double) y / (double) gcd(x, y)));

        incrementX((int) ((double) x / (double) gcd(x, y)));
        incrementY((int) ((double) y / (double) gcd(x, y)));
    }

    public void rect(Graphics g, Color c, boolean filled) {
        type = "rect";
        g.setColor(c);
        if (filled) {
            g.fillRect((int) getX(), (int) getY(), (int) getW(), (int) getH());
        } else {
            g.drawRect((int) getX(), (int) getY(), (int) getW(), (int) getH());// g.drawRect(getX(), getY(), getW(),
                                                                               // getH());
        }
    }

    public void rectCentered(Graphics g, Color c, boolean filled) {
        type = "rect centered";
        g.setColor(c);
        if (filled) {
            g.fillRect((int) (getX() + w / 2.0), (int) (getY() + h / 2.0), (int) getW(), (int) getH());
        } else {
            g.drawRect((int) (getX() + w / 2.0), (int) (getY() + h / 2.0), (int) getW(), (int) getH());
        }
    }

    public void clear(Graphics g) {
        g.setColor(Color.black);
        // g.drawRect(1, 1, 1, 1);
    }

    public void oval(Graphics g, Color c, boolean filled) {
        type = "oval";
        g.setColor(c);
        if (filled) {
            g.fillOval((int) getX() - (int) (getW() / 2.0), (int) getY() - (int) (getH() / 2.0), (int) getW(),
                    (int) getH());
        } else {
            g.drawOval((int) getX() - (int) (getW() / 2.0), (int) getY() - (int) (getH() / 2.0), (int) getW(),
                    (int) getH());
        }
    }

    public void draw(Graphics g, Color c, boolean filled, String t) {
        type = t;
        draw(g, c, filled);
    }

    public void draw(Graphics g, Color c, boolean filled) {
        if (type.equals("rect")) {
            rect(g, c, filled);
        } else if (type.equals("oval")) {
            oval(g, c, filled);
        } else if (type.equals("rectCentered")) {
            rectCentered(g, c, filled);
        }
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);

    }

    // public boolean isClear() {
    // return getType().equals("clear");// && getX() == 0 && getY() == 0 && getW()
    // == 0 && getH() == 0;
    // }

}
