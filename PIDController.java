package Graphics;

public class PIDController {
    public double I, P, D;
    private double error, lastError = 0.0, lastIntegral = 0.0;

    private double dT = .1;

    public PIDController(double kP, double kI, double kD, double kdT) {
        P = kP;
        I = kI;
        D = kD;
        dT = kdT;
    }

    public PIDController(double kP, double kI, double kD) {
        P = kP;
        I = kI;
        D = kD;
    }

    public void reset() {
        lastError = 0.0;
        lastIntegral = 0.0;
    }

    public void setP(double p) {
        P = p;
    }

    public void setI(double i) {
        I = i;
    }

    public void setD(double d) {
        D = d;
    }

    public double PIDout(double actual, double desired) {
        error = desired - actual;
        double integral = lastIntegral + (error * dT);
        double derivative = (error - lastError) / dT;
        lastError = error;
        lastIntegral = integral;
        return actual + P * (error) + I * integral + D * derivative;
    }

    public double PIDoutputVal(double actual, double desired) {
        error = desired - actual;
        double integral = lastIntegral + (error * dT);
        double derivative = (error - lastError) / dT;
        lastError = error;
        lastIntegral = integral;
        return P * (error) + I * integral + D * derivative;
    }

    public boolean setPID(double desired) {
        double actual = get();

        if (Math.abs(actual - desired) > 0.01) {
            set(PIDout(actual, desired));
            return false;
        } else {
            reset();
            return true;
        }
    }

    // to override, sets the value
    public void set(double val) {

    }

    // to override, gets the value
    public double get() {
        return 0.0;
    }

    public boolean hasReached(double actual, double desired) {
        return Math.abs(actual - desired) <= 0.01;
    }

}
