package Graphics;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Area extends Shape {
    private double w, h;
    // int delay = 10;// Main.timerSpeed;

    public Area(double newX, double newY, double newW, double newH, double P, double I, double D) {
        super(newX, newY, P, I, D);
        w = newW;
        h = newH;
    }

    public Area(Area newOne) {
        super(newOne);
        w = newOne.getW();
        h = newOne.getH();
        // this.delay = newOne.delay;
    }

    public Area(double newX, double newY, double newW, double newH) {
        super(newX, newY);
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

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
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

}
