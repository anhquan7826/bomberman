package com.oopproj.bomberman.ui;

public class ScreenRes {
    private static int width;
    private static int height;
    public static final int scale = 64;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setResolution(int w, int h) {
        width = w;
        height = h;
    }

    public static float getRatio() {
        return (float) width / height;
    }
}
