package Graphics;

import Graphics.Button;

public abstract class ButtonImplementation extends Button {

    public ButtonImplementation(Button b) {
        super(b);
    }

    @Override
    public abstract void doAction();

    @Override
    public abstract void update();
}