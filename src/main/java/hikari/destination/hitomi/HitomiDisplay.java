package hikari.destination.hitomi;

import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.glfw.GLFW;

import java.awt.*;


public class HitomiDisplay {
    // Window Information
    public static String title;

    // Window Status
    public static boolean isCreated;
    public static boolean isFocused;
    public static boolean isMinimized;
    public static boolean isFullscreen;
    public static boolean isResizeable;
    public static boolean isResized;
    public static boolean isDirty; // has been damaged by external events

    // Window Relation
    public static long window;
    public static Canvas parent;
    public static DisplayMode initialMode;
    public static DisplayMode currentMode;

    // FPS
    private static long variableYieldTime, lastTime;

    public static void setDisplayModeAndFullscreenInternal(boolean fullscreen, DisplayMode mode) {
        if (mode == null)
            throw new NullPointerException("mode must be non-null");
        DisplayMode old_mode = currentMode;
        currentMode = mode;

        boolean wasFullscreen = isFullscreen;

        if (fullscreen != wasFullscreen || !mode.equals(old_mode)) {
            if (!isCreated)
                return;
            GLFW.glfwSetWindowMonitor(window, fullscreen ? 1 : 0, 0, 0, 1280, 720, 60);
        }
    }

    /**
     * An accurate sync method that adapts automatically
     * to the system it runs on to provide reliable results.
     *
     * @param fps The desired frame rate, in frames per second
     * @author kappa (On the LWJGL Forums)
     */
    public static void sync(int fps) {
        // TODO
        if (fps <= 0) return;

        long sleepTime = 1000000000 / fps; // nanoseconds to sleep this frame
        // yieldTime + remainder micro & nano seconds if smaller than sleepTime
        long yieldTime = Math.min(sleepTime, variableYieldTime + sleepTime % (1000 * 1000));
        long overSleep = 0; // time the sync goes over by

        try {
            while (true) {
                long t = System.nanoTime() - lastTime;

                if (t < sleepTime - yieldTime) {
                    Thread.sleep(1);
                } else if (t < sleepTime) {
                    // burn the last few CPU cycles to ensure accuracy
                    Thread.yield();
                } else {
                    overSleep = t - sleepTime;
                    break; // exit while loop
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lastTime = System.nanoTime() - Math.min(overSleep, sleepTime);

            // auto tune the time sync should yield
            if (overSleep > variableYieldTime) {
                // increase by 200 microseconds (1/5 a ms)
                variableYieldTime = Math.min(variableYieldTime + 200 * 1000, sleepTime);
            } else if (overSleep < variableYieldTime - 200 * 1000) {
                // decrease by 2 microseconds
                variableYieldTime = Math.max(variableYieldTime - 2 * 1000, 0);
            }
        }
    }
}
