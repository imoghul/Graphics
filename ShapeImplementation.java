package Graphics;

import Graphics.Shape;
import Graphics.Mouse;

public abstract class ShapeImplementation extends Shape {
    public ShapeImplementation(Shape s) {
        super(s);
    }

    @Override
    public abstract void update(Mouse m);
}