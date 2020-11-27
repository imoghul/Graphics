package Graphics;

import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;

public class Text extends Drawer {
    String text;
    Font font;
    FontRenderContext context;
    double w, h;

    public Text(String text, double newX, double newY, double P, double I, double D) {
        super(newX, newY, P, I, D);
        this.text = text;
    }

    public Text(String text, double newX, double newY, String t) {
        super(newX, newY, t);
        this.text = text;
    }

    public Text(String text, double newX, double newY) {
        super(newX, newY);
        this.text = text;
    }

    public Text(String text, double newX, double newY, double P, double I, double D, String t) {
        super(newX, newY, P, I, D, t);
        this.text = text;
    }

    private void updateContext(Graphics g) {
        font = g.getFont();
        context = new FontRenderContext(new AffineTransform(), true, true);
    }

    public void setDimensions(Graphics g) {
        updateContext(g);
        w = font.getStringBounds(text, context).getWidth();
        h = font.getStringBounds(text, context).getHeight();
    }

    public void setDimensions(Font font) {
        this.font = font;
        w = font.getStringBounds(text, context).getWidth();
        h = font.getStringBounds(text, context).getHeight();
    }

    public void setDimensions() {
        w = font.getStringBounds(text, context).getWidth();
        h = font.getStringBounds(text, context).getHeight();
    }

    public Shape getBoudingBox(Graphics g) {
        setDimensions(g);
        return new Shape(x, y, w, h, "rect " + getSubType());
    }

    public Shape getBoudingBox() {
        return new Shape(x, y, w, h, "rect " + getSubType());
    }

    public String getText() {
        return text;
    }

    public void setString(String t) {
        text = t;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public void draw(Graphics g, Color c) {
        type = "text normal";
        g.setColor(c);
        setDimensions(g);
        g.drawString(text, (int) getX(), (int) (getY() + w));
    }

    public void drawCentered(Graphics g, Color c) {
        type = "text centered";
        g.setColor(c);
        setDimensions(g);
        g.drawString(text, (int) (getX() - w / 2), (int) (getY() + h / 2));
    }

    public void draw(Graphics g, Color c, Font f) {
        g.setColor(c);
        setDimensions(f);
        g.drawString(text, (int) (getX() - w / 2), (int) (getY() + h / 2));
    }

}