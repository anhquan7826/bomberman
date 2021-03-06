package com.oopproj.bomberman.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class GameSound {
    private static final int MUSIC = 0;
    private static final int SOUND = 1;

    private static Music mainMenu = Gdx.audio.newMusic(Gdx.files.internal("sounds/01_music.wav"));
    private static Music level1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/02_music.wav"));
    private static Music level2 = Gdx.audio.newMusic(Gdx.files.internal("sounds/03_music.wav"));
    private static Music level3 = Gdx.audio.newMusic(Gdx.files.internal("sounds/04_music.wav"));

    private static Music gameOver = Gdx.audio.newMusic(Gdx.files.internal("sounds/game_over.wav"));
    private static Music gameWin = Gdx.audio.newMusic(Gdx.files.internal("sounds/game_win.wav"));
    private static Music subMenu = Gdx.audio.newMusic(Gdx.files.internal("sounds/menu.wav"));


    private static Sound bombTick = Gdx.audio.newSound(Gdx.files.internal("sounds/bomb_tick.wav"));
    private static Sound enemyDeath = Gdx.audio.newSound(Gdx.files.internal("sounds/enemy_death.wav"));
    private static Sound explosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
    private static Sound placeBomb = Gdx.audio.newSound(Gdx.files.internal("sounds/place_bomb.wav"));
    private static Sound playerDeath = Gdx.audio.newSound(Gdx.files.internal("sounds/player_death.wav"));
    private static Sound powerUp = Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.wav"));

    private static float[] volume = new float[]{1f, 1f};

    public static void playMainMenu() {
        mainMenu.setLooping(true);
        mainMenu.setVolume(volume[MUSIC]);
        mainMenu.play();
    }

    public static void stopMainMenu() {
        mainMenu.stop();
    }

    public static void playLevel(int level) {
        switch (level) {
            case 1: {
                level1.setLooping(true);
                level1.setVolume(volume[MUSIC]);
                level1.play();
                break;
            }
            case 2: {
                level2.setLooping(true);
                level2.setVolume(volume[MUSIC]);
                level2.play();
                break;
            }
            case 3: {
                level3.setLooping(true);
                level3.setVolume(volume[MUSIC]);
                level3.play();
                break;
            }
        }
    }

    public static void stopLevel(int level) {
        switch (level) {
            case 1: {
                level1.stop();
                break;
            }
            case 2: {
                level2.stop();
                break;
            }
            case 3: {
                level3.stop();
                break;
            }
        }
    }

    public static void playGameOver() {
        gameOver.setVolume(volume[SOUND]);
        gameOver.play();
    }

    public static void stopGameOver() {
        gameOver.stop();
    }

    public static void playGameWin() {
        gameWin.setVolume(volume[SOUND]);
        gameWin.play();
    }

    public static void stopGameWin() {
        gameWin.stop();
    }

    public static void playSubMenu() {
        subMenu.setLooping(true);
        subMenu.setVolume(volume[MUSIC]);
        subMenu.play();
    }

    public static void stopSubMenu() {
        subMenu.stop();
    }

    public static void playBombTick() {
        bombTick.play(volume[SOUND]);
    }

    public static void playEnemyDeath() {
        enemyDeath.play(volume[SOUND]);
    }

    public static void playExplosion() {
        explosion.play(volume[SOUND]);
    }

    public static void playPlaceBomb() {
        placeBomb.play(volume[SOUND]);
    }

    public static void playPlayerDeath() {
        playerDeath.play(volume[SOUND]);
    }

    public static void playPowerUp() {
        powerUp.play(volume[SOUND]);
    }

    public static float getSoundVolume() {
        return volume[SOUND];
    }

    public static void setSoundVolume(float v) {
        volume[SOUND] = v;
    }

    public static float getMusicVolume() {
        return volume[MUSIC];
    }

    public static void setMusicVolume(float v) {
        volume[MUSIC] = v;
        mainMenu.setVolume(volume[MUSIC]);
        level1.setVolume(volume[MUSIC]);
        level2.setVolume(volume[MUSIC]);
        level3.setVolume(volume[MUSIC]);
        gameOver.setVolume(volume[SOUND]);
        gameWin.setVolume(volume[SOUND]);
        subMenu.setVolume(volume[MUSIC]);
    }

    public static boolean isLevelPlaying(int level) {
        switch (level) {
            case 1: {
                return level1.isPlaying();
            }
            case 2: {
                return level2.isPlaying();
            }
            case 3: {
                return level3.isPlaying();
            }
        }
        return false;
    }

    public static void pauseLevel(int level) {
        switch (level) {
            case 1: {
                level1.pause();
            }
            case 2: {
                level2.pause();
            }
            case 3: {
                level3.pause();
            }
        }
    }
}
