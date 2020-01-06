package org.lwjgl.opengl;

import hikari.destination.lwjgl.opengl.Display;

public final class DisplayMode {

    /**
     * properties of the display mode
     */
    private final int width, height, bpp, freq;
    /**
     * If true, this instance can be used for fullscreen modes
     */
    private final boolean fullscreen;

    /**
     * Construct a display mode. DisplayModes constructed through the
     * public constructor can only be used to specify the dimensions of
     * the Display in windowed mode. To get the available DisplayModes for
     * fullscreen modes, use Display.getAvailableDisplayModes().
     *
     * @param width  The Display width.
     * @param height The Display height.
     * @see Display
     */
    public DisplayMode(int width, int height) {
        this(width, height, 0, 0, false);
    }

    DisplayMode(int width, int height, int bpp, int freq) {
        this(width, height, bpp, freq, true);
    }

    private DisplayMode(int width, int height, int bpp, int freq, boolean fullscreen) {
        this.width = width;
        this.height = height;
        this.bpp = bpp;
        this.freq = freq;
        this.fullscreen = fullscreen;
    }

    /**
     * True if this instance can be used for fullscreen modes
     */
    public boolean isFullscreenCapable() {
        return fullscreen;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBitsPerPixel() {
        return bpp;
    }

    public int getFrequency() {
        return freq;
    }

    /**
     * Tests for <code>DisplayMode</code> equality
     *
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof DisplayMode)) {
            return false;
        }

        DisplayMode dm = (DisplayMode) obj;
        return dm.width == width
                && dm.height == height
                && dm.bpp == bpp
                && dm.freq == freq;
    }

    /**
     * Retrieves the hashcode for this object
     *
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return width ^ height ^ freq ^ bpp;
    }

    /**
     * Retrieves a String representation of this <code>DisplayMode</code>
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return width + " x " + height + " x " + bpp + " @" + freq + "Hz";
    }
}

