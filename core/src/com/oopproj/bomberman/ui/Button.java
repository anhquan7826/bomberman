package com.oopproj.bomberman.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.utils.State;

import java.util.List;

public class Button extends UIElement {
    protected final double DURATION = 0.25;
    protected Texture texture;
    private boolean isTouched;

    public Button(Texture texture, float x, float y) {
        this.x = x - (float) texture.getWidth() / 2;
        this.y = y - (float) texture.getHeight() / 2;
        this.currentY = y - 50;
        this.texture = texture;
        this.isTouched = false;
    }

    public void render() {
        renderCalled = true;
        batch.setColor(1, 1, 1, alpha);
        batch.begin();
        batch.draw(texture, x, currentY);
        batch.end();
    }

    public Object process(List<UIElement> uiElements) {
        if (!renderCalled) {
            return false;
        }
        switch (state) {
            case SLIDEIN: {
                delta = MathUtils.clamp(delta + Gdx.graphics.getDeltaTime(), 0, DURATION);
                alpha = (float) parabol(delta, DURATION);
                currentY = (float) (y - 50 + 50 * parabol(delta, DURATION));
                if (delta == DURATION) {
                    state = State.STATIC;
                    delta = 0;
                    doneRendering = true;
                }
                break;
            }
            case SLIDEOUT: {
                delta = MathUtils.clamp(delta + Gdx.graphics.getDeltaTime(), DURATION, DURATION * 2);
                alpha = (float) parabol(delta, DURATION);
                currentY = (float) (y - 50 + 50 * parabol(delta, DURATION));
                for (UIElement element : uiElements) {
                    if (element != this) {
                        element.setAlpha(this.getAlpha());
                    }
                }
                if (delta == DURATION * 2) {
                    state = State.DISAPPEARED;
                    delta = 0;
                    isTouched = true;
                }
                break;
            }
            case FADEOUT: {
                delta = MathUtils.clamp(delta + Gdx.graphics.getDeltaTime(), DURATION, DURATION * 2);
                alpha = (float) parabol(delta, DURATION);
                if (delta == DURATION * 2) {
                    state = State.DISAPPEARED;
                    delta = 0;
                }
                break;
            }
            case STATIC: {
                boolean done = true;
                for (UIElement e : uiElements) {
                    if (!e.isDoneRendering()) {
                        done = false;
                        break;
                    }
                }
                if (!done) {
                    break;
                }
                if (Gdx.input.justTouched()) {
                    int mouseX = Gdx.input.getX();
                    int mouseY = ScreenRes.getHeight() - Gdx.input.getY();
                    if (this.x <= mouseX && mouseX <= this.x + texture.getWidth()
                            && this.y <= mouseY && mouseY <= this.y + texture.getHeight()) {
                        state = State.SLIDEOUT;
                        delta = 0;
                    }
                }
                break;
            }
        }
        return isTouched;
    }

    @Override
    public float getCurrentY() {
        return this.currentY + (float) texture.getHeight() / 2;
    }

    @Override
    public float getWidth() {
        return texture.getWidth();
    }

    @Override
    public float getHeight() {
        return texture.getHeight();
    }

    @Override
    public float getX() {
        return this.x + (float) texture.getWidth() / 2;
    }

    @Override
    public float getY() {
        return this.y + (float) texture.getHeight() / 2;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void reset() {
        super.reset();
        currentY = y - 50;
        isTouched = false;
    }
}