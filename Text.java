package Graphics;

import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;

public class Text extends Drawer {
    private String text;
    Font font;
    FontRenderContext context;
    protected double w, h;

    public Text(String text, double newX, double newY, double P, double I, double D, int d) {
        super(newX, newY, P, I, D, d);
        this.text = text;
    }

    public Text(String text, double newX, double newY, String t, int d) {
        super(newX, newY, t, d);
        this.text = text;
    }

    public Text(String text, double newX, double newY, int d) {
        super(newX, newY, d);
        this.text = text;
    }

    public Text(String text, double newX, double newY, Color color, int d) {
        super(newX, newY, d);
        this.text = text;
        c = color;
    }

    public Text(String text, double newX, double newY, double P, double I, double D, String t, int d) {
        super(newX, newY, P, I, D, t, d);
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
        return new Shape(x, y, w, h, "rect " + getSubType(), delay);
    }

    public Shape getBoudingBox() {
        setDimensions();
        return new Shape(x, y, w, h, "rect " + getSubType(), delay);
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
        g.setFont(font);
        setDimensions(g);
        g.drawString(text, (int) getX(), (int) (getY() + w));
    }

    public void draw(Graphics g) {
        type = "text normal";
        g.setColor(c);
        g.setFont(font);
        setDimensions(g);
        g.drawString(text, (int) getX(), (int) (getY() + w));
    }

    public void drawCentered(Graphics g, Color c) {
        type = "text centered";
        g.setColor(c);
        g.setFont(font);
        setDimensions(g);
        g.drawString(text, (int) (getX() - w / 2), (int) (getY() + h / 2));
    }

    public void drawCentered(Graphics g) {
        type = "text centered";
        g.setColor(c);
        g.setFont(font);
        setDimensions(g);
        g.drawString(text, (int) (getX() - w / 2), (int) (getY() + h / 2));
    }

    public void draw(Graphics g, Color c, Font f) {
        type = "text centered";
        g.setColor(c);
        g.setFont(font);
        setDimensions(f);
        g.drawString(text, (int) (getX() - w / 2), (int) (getY() + h / 2));
    }

    public void setFont(Font f) {
        font = f;
    }

}