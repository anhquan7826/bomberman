package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.ui.*;
import com.oopproj.bomberman.utils.State;

import java.util.ArrayList;
import java.util.LinkedList;

public class Leaderboard extends Scene {
    private Button back;
    private Banner[] leaderboard_banner;
    private Font font;

    public Leaderboard(BombermanGame game) {
        super(game, new Texture(Gdx.files.internal("ui/background.png")));
        back = new Button(new Texture(Gdx.files.internal("ui/back.png")), ScreenRes.getWidth() / 2f, 50);
        leaderboard_banner = new Banner[10];
        font = new Font("fonts/whitrabt.ttf", 16);
        uiElements = new ArrayList<>();
        renderOrder = new LinkedList<UIElement>() {
            {
                Texture leaderboardHolder = new Texture(Gdx.files.internal("ui/leaderboard_holder.png"));
                for (int i = 0; i < 10; i++) {
                    add(leaderboard_banner[i] = new Banner(leaderboardHolder, ScreenRes.getWidth() / 2f, 525 - i * 40));
                }
                add(back);
            }
        };
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        boolean isDoneRendering = true;
        for (int i = 0; i < uiElements.size(); i++) {
            if (i < game.lboard.get10HighestScore().size()) {
                font.setColor(1, 1, 1, uiElements.get(i).getAlpha());
                font.draw(
                        game.lboard.get10HighestScore().get(i).name,
                        uiElements.get(i).getX() - 80,
                        uiElements.get(i).getCurrentY() + 11
                );
                font.draw(
                        Long.toString(game.lboard.get10HighestScore().get(i).score),
                        uiElements.get(i).getX() + 135,
                        uiElements.get(i).getCurrentY() + 11
                );
            }
        }
        batch.end();

        if ((boolean) back.process(uiElements)) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                game.setScreen(prevScene);
            }
        }

        for (int i = 0; i < 10; i++) {
            leaderboard_banner[i].process(null);
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
