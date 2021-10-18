package Graphics;

import Graphics.Mouse;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Shape extends Drawer {
    protected double w, h;
    // int delay = 10;// Main.timerSpeed;

    public Shape(double newX, double newY, double newW, double newH, double P, double I, double D, int d) {
        super(newX, newY, P, I, D, d);
        w = newW;
        h = newH;
    }

    public Shape(Shape newOne) {
        super(newOne);
        w = newOne.getW();
        h = newOne.getH();
        // this.delay = newOne.delay;
    }

    public Shape(double newX, double newY, double newW, double newH, int d) {
        super(newX, newY, d);
        w = newW;
        h = newH;
    }

    public Shape(double newX, double newY, double newW, double newH, String t, int d) {
        this(newX, newY, newW, newH, d);
        type = t;
    }

    public Shape(double newX, double newY, double newW, double newH, double P, double I, double D, String t, int d) {
        this(newX, newY, newW, newH, P, I, D, d);
        type = t;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public double getCenterX() {
        if (getType().equals("rect")) {
            if (getSubType().equals("normal")) {
                return x + (getW() / 2);
            } else if (getSubType().equals("centered")) {
                return x;
            }

        } else if (getType().equals("oval")) {
            return x;
        } else if (getType().equals("text")) {
            if (getSubType().equals("normal")) {
                return x + (getW() / 2);
            } else if (getSubType().equals("centered")) {
                return x;
            }
        }

        return 0;
    }

    public double getCenterY() {
        if (getType().equals("rect")) {
            if (getSubType().equals("normal")) {
                return y + (getH() / 2);
            } else if (getSubType().equals("centered")) {
                return y;
            }

        } else if (getType().equals("oval")) {
            return y;
        } else if (getType().equals("text")) {
            if (getSubType().equals("normal")) {
                return y + (getH() / 2);
            } else if (getSubType().equals("normal")) {
                return y;
            }
        }

        return 0;
    }

    public void setW(double newW) {
        w = newW;
    }

    public void setH(double newH) {
        h = newH;
    }

    public void incrementW(double i) {
        w += i;
    }

    public void incrementH(double i) {
        h += i;
    }

    public void rect(Graphics g, Color c, boolean filled) {
        type = "rect normal";
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
            g.fillRect((int) (getX() - w / 2.0), (int) (getY() - h / 2.0), (int) getW(), (int) getH());
        } else {
            g.drawRect((int) (getX() - w / 2.0), (int) (getY() - h / 2.0), (int) getW(), (int) getH());
        }
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
        if (getType().equals("rect")) {
            if (getSubType().equals("normal")) {
                rect(g, c, filled);
            } else if (getSubType().equals("centered")) {
                rectCentered(g, c, filled);
            }
        } else if (getType().equals("oval")) {
            oval(g, c, filled);
        }
    }

    public void draw(Graphics g, boolean filled) {
        if (getType().equals("rect")) {
            if (getSubType().equals("normal")) {
                rect(g, c, filled);
            } else if (getSubType().equals("centered")) {
                rectCentered(g, c, filled);
            }
        } else if (getType().equals("oval")) {
            oval(g, c, filled);
        }
    }

    public void run(Graphics g, Color c, boolean filled, Mouse m) {
        update(m);
        draw(g, c, filled);
    }

    public void run(Graphics g, Color c, boolean filled, String type, Mouse m) {
        update(m);
        draw(g, c, filled, type);
    }

    public void run(Graphics g, boolean filled, Mouse m) {
        update(m);
        draw(g, c, filled);
    }

    public void run(Graphics g, boolean filled, String type, Mouse m) {
        update(m);
        draw(g, c, filled, type);
    }

    // to Override
    public void update(Mouse m) {

    }

}
