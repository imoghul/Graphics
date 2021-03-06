package Graphics;
public class YController extends PIDController {
    public Drawer drawer;
    public YController(double kP, double kI, double kD, double kdT, Drawer drawer) {
        super(kP, kI, kD, kdT);
        this.drawer = drawer;
    }

    public YController(double kP, double kI, double kD, Drawer drawer) {
        super(kP, kI, kD);
        this.drawer = drawer;
    }

    public void setDrawer(Drawer drawer) {
        this.drawer = drawer;
    }

    @Override
    public void set(double val) {
        drawer.setY(val);
    }

    @Override
    public double get() {
        return drawer.getY();
    }
}