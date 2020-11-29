package Graphics;

import Graphics.Slider;

public abstract class SliderImplementation extends Slider {

    public SliderImplementation(Slider s) {
        super(s);
    }

    @Override
    public abstract void doAction();

    @Override
    public abstract void update();
}