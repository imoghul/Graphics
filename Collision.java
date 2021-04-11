package Graphics;
import java.awt.Graphics;

public class Collision {

    public int displayW, displayH;

    public boolean rectCollide(Shape one, Shape two) {
        double x = 0, x2 = 0, y = 0, y2 = 0, w = 0, w2 = 0, h = 0, h2 = 0;

        if (one.getSubType().equals("normal")) {
            x = one.getX();
            y = one.getY();
            w = one.getW();
            h = one.getH();
        } else if (one.getSubType().equals("centered")) {
            x = one.getX() - one.getW() / 2;
            y = one.getY() - one.getH() / 2;
            w = one.getW();
            h = one.getH();
        }

        if (two.getSubType().equals("normal")) {
            x2 = two.getX();
            y2 = two.getY();
            w2 = two.getW();
            h2 = two.getH();
        } else if (two.getSubType().equals("centered")) {
            x2 = two.getX() - two.getW() / 2;
            y2 = two.getY() - two.getH() / 2;
            w2 = two.getW();
            h2 = two.getH();
        }

        return (((x2 + w2 >= x) && (x2 <= x + w)) && ((y2 <= y + h) && (y2 + h2 >= y)));
    }

    // public boolean ovalCollide(Shape one, Shape two) {
    //     // check if (xcoor,ycoor) is in elipse with (x,y)center and width,w and height,h
    //     // (Math.pow(((xcoor-x)/(w/2)),2) + Math.pow((ycoor-y)/(h/2),2))<=1

    //     for (int xcoor = 0; xcoor < displayW; xcoor++) {
    //         for (int ycoor = 0; ycoor < displayH; ycoor++) {
    //             if (pointInOval(xcoor, ycoor, one) && pointInOval(xcoor, ycoor, two)) {
    //                 return true;
    //             }
    //         }
    //     }

    //     return false;
    // }

    private double plugOval(double xcoor, double ycoor, Shape oval) {
        double x = oval.getX(), y = oval.getY(), w = oval.getW(), h = oval.getH();
        return (Math.pow(((xcoor - x) / (w / 2)), 2) + Math.pow((ycoor - y) / (h / 2), 2));
    }

    public boolean ovalCollide(Shape one, Shape two) {
        double d = Math.sqrt(Math.pow(one.getX() - two.getX(), 2) + Math.pow(one.getY() - two.getY(), 2));
        double a1 = one.getW() / 2, b1 = one.getH() / 2, a2 = two.getW() / 2, b2 = two.getH() / 2;
        double theta2 = Math.atan2(one.getY() - two.getY(), one.getX() - two.getX());
        double theta1 = Math.atan2(two.getY() - one.getY(), two.getX() - one.getX());
        double r1 = Math.sqrt(Math.pow(a1 * Math.cos(theta1), 2) + Math.pow(b1 * Math.sin(theta1), 2));
        double r2 = Math.sqrt(Math.pow(a2 * Math.cos(theta2), 2) + Math.pow(b2 * Math.sin(theta2), 2));
        return (d < (r1 + r2));
    }

    public boolean pointInOval(double xcoor, double ycoor, Shape oval) {
        double x = oval.getX(), y = oval.getY(), w = oval.getW(), h = oval.getH();
        return (Math.pow(((xcoor - x) / (w / 2)), 2) + Math.pow((ycoor - y) / (h / 2), 2)) <= 1;
    }

    public boolean pointInRect(double xcoor, double ycoor, Shape rect) {
        // double x2 = rect.getX(), y2 = rect.getY(), w2 = rect.getW(), h2 =
        // rect.getH();
        double x2 = 0, y2 = 0, w2 = 0, h2 = 0;

        if (rect.getSubType().equals("normal")) {
            x2 = rect.getX();
            y2 = rect.getY();
            w2 = rect.getW();
            h2 = rect.getH();
        } else if (rect.getSubType().equals("centered")) {
            x2 = rect.getX() - rect.getW() / 2;
            y2 = rect.getY() - rect.getH() / 2;
            w2 = rect.getW();
            h2 = rect.getH();
        }

        return ((xcoor >= x2) && (xcoor <= (x2 + w2)) && (ycoor >= y2) && (ycoor <= (y2 + h2)));
    }

    // public boolean ovalRectCollide(Shape oval, Shape rect) {
    //     for (long xcoor = Math.round(Math.min(oval.getCenterX() - oval.getW() / 2, rect.getCenterX() - rect.getW() / 2)); xcoor <  Math.round(Math.max(oval.getCenterX() + oval.getW() / 2, rect.getCenterX() + rect.getW() / 2)); xcoor++) {
    //         for (long ycoor =  Math.round(Math.min(oval.getCenterY() - oval.getH() / 2, rect.getCenterY() - rect.getH() / 2)); ycoor < Math.round(Math.max(oval.getCenterY() + oval.getH() / 2, rect.getCenterY() + rect.getH() / 2)); ycoor++) {
    //             if (pointInOval(xcoor, ycoor, oval) && pointInRect(xcoor, ycoor, rect)) {
    //                 return true;
    //             }
    //         }
    //     }

    //     return false;
    // }

    public boolean ovalRectCollide(Shape oval, Shape rect) {
        double d = Math.sqrt(Math.pow(oval.getCenterX() - rect.getCenterX(), 2) + Math.pow(oval.getCenterY() - rect.getCenterY(), 2));
        double a1 = oval.getW() / 2, b1 = oval.getH() / 2, a2 = rect.getW() / 2, b2 = rect.getH() / 2;
        double theta2 = Math.atan2(oval.getCenterY() - rect.getCenterY(), oval.getCenterX() - rect.getCenterX());
        double theta1 = Math.atan2(rect.getCenterY() - oval.getCenterY(), rect.getCenterX() - oval.getCenterX());
        double ovalR = Math.sqrt(Math.pow(a1 * Math.cos(theta1), 2) + Math.pow(b1 * Math.sin(theta1), 2));
        double rectR;
        theta2 = theta2 < 0 ? 2 * Math.PI + theta2 : theta2;

        double degrees = theta2 * 180 / Math.PI;
        double corner =  Math.atan2(b2, a2) * 180 / Math.PI;

        // System.out.print(degrees + " " + (180-corner) + " " );
        if(degrees >= 360 - corner || degrees <= corner) {
            rectR = a2 / Math.cos(theta2);
        } else if (degrees <= (180 - corner)) {
            rectR = b2 / Math.sin(theta2);
        } else if (degrees <= (180 + corner)) {
            rectR = a2 / Math.cos(theta2);
        } else {
            rectR = b2 / Math.sin(theta2);
        }

        rectR = Math.abs(rectR);

        // int xRect = (int)(Math.round(rectR * Math.cos(theta2)));
        // int yRect = (int)(Math.round(rectR * Math.sin(theta2)));
        // g.drawLine((int)rect.getCenterX(), (int)rect.getCenterY(), (int)rect.getCenterX() + xRect, (int)rect.getCenterY() + yRect);


        // int xOval = (int)Math.round(ovalR * Math.cos(theta1));
        // int yOval = (int)Math.round(ovalR * Math.sin(theta1));
        // g.drawLine((int)oval.getCenterX(), (int)oval.getCenterY(), (int)oval.getCenterX() + xOval, (int)oval.getCenterY() + yOval);

        int x = (int)Math.round(a1 * Math.cos(theta1));
        int y = (int)Math.round(b1 * Math.sin(theta1));

        // g.drawLine((int)oval.getCenterX(), (int)oval.getCenterY(), (int)oval.getCenterX() + x, (int)oval.getCenterY() + y);

        return (d < (Math.sqrt(x * x + y * y)) + rectR);
    }

    public boolean autoIsIn(double x, double y, Shape one) {
        if (one.getType().equals("rect")) {
            return pointInRect(x, y, one);
        } else if (one.getType().equals("oval")) {
            return pointInOval(x, y, one);
        }

        return false;
    }

    public boolean autoCollide(Shape one, Shape two, Graphics g) {
        if (one.getType().equals("rect") && two.getType().equals("rect")) return rectCollide(one, two);
        else if (one.getType().equals("oval") && two.getType().equals("rect")) return ovalRectCollide(one, two);
        else if (one.getType().equals("rect") && two.getType().equals("oval")) return ovalRectCollide(two, one);
        else if (one.getType().equals("oval") && two.getType().equals("oval")) return ovalCollide(one, two);

        return false;
    }
}