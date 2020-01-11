package hikari.destination.lwjgl.opengl;

import hikari.destination.hitomi.HitomiDisplay;
import hikari.destination.hitomi.HitomiLock;
import org.lwjgl.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.opengl.*;

import javax.naming.InsufficientResourcesException;
import java.nio.ByteBuffer;

@SuppressWarnings("unused")
public final class Display {
    public static Drawable getDrawable() {
        // TODO
        return null;
    }

    public static DisplayMode[] getAvailableDisplayModes() {
        return HitomiDisplay.getAvailableDisplayModes();
    }

    public static DisplayMode getDesktopDisplayMode() {
        return HitomiDisplay.initialMode;
    }

    @SuppressWarnings("all")
    public static DisplayMode getDisplayMode() {
        return HitomiDisplay.currentMode;
    }

    public static void setDisplayMode(DisplayMode mode) {
        HitomiDisplay.setDisplayModeAndFullscreenInternal(isFullscreen(), mode);
    }

    public static void setDisplayConfiguration(float gamma, float brightness, float contrast) throws LWJGLException {
        // TODO
    }

    public static void sync(int fps) {
        HitomiDisplay.sync(fps);
    }

    public static String getTitle() {
        return HitomiDisplay.title;
    }

    public static java.awt.Canvas getParent() {
        return null;
    }

    @Deprecated
    public static void setParent(java.awt.Canvas parent) {
    }

    public static void setFullscreen(boolean fullscreen) throws LWJGLException {
        HitomiDisplay.setDisplayModeAndFullscreenInternal(fullscreen, HitomiDisplay.currentMode);
    }

    public static void setDisplayModeAndFullscreen(DisplayMode mode) throws LWJGLException {
        HitomiDisplay.setDisplayModeAndFullscreenInternal(mode.isFullscreenCapable(), mode);
    }

    @SuppressWarnings("all")
    public static boolean isFullscreen() {
        return HitomiDisplay.isFullscreen;
    }

    public static void setTitle(String newTitle) {
        synchronized (HitomiLock.lock) {
            // FIXME: Delay setting title
            if (newTitle == null)
                newTitle = "";
            HitomiDisplay.title = newTitle;

            if (isCreated()) {
                GLFW.glfwSetWindowTitle(HitomiDisplay.window, newTitle);
            }
        }
    }

    public static boolean isCloseRequested() {
        return GLFW.glfwWindowShouldClose(HitomiDisplay.window);
    }

    public static boolean isVisible() {
        return !HitomiDisplay.isMinimized;
    }

    public static boolean isActive() {
        return HitomiDisplay.isFocused;
    }

    public static boolean isDirty() {
        if (!isCreated()) {
            throw new IllegalStateException("Cannot determine dirty state of uncreated window");
        }
        boolean result = HitomiDisplay.isDirty;
        HitomiDisplay.isDirty = false;
        return result;
    }

    public static void processMessages() {
        // TODO
    }

    public static void swapBuffers() throws LWJGLException {
        // TODO
    }

    public static void update() {
        // TODO
    }

    public static void update(boolean processMessages) {
        // TODO
    }

    public static void releaseContext() throws LWJGLException {
        // TODO
    }

    public static boolean isCurrent() throws LWJGLException {
        // TODO
        return true;
    }

    public static void makeCurrent() throws LWJGLException {
        // TODO
    }

    public static void create() throws LWJGLException {
        create(new PixelFormat());
    }

    @SuppressWarnings("all")
    public static void create(PixelFormat pixel_format) throws LWJGLException {
        create(pixel_format, null, (ContextAttribs) null);
    }

    public static void create(PixelFormat pixel_format, Drawable shared_drawable) throws LWJGLException {
        create(pixel_format, shared_drawable, (ContextAttribs) null);
    }

    public static void create(PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
        create(pixel_format, null, attribs);
    }

    @SuppressWarnings("all")
    public static void create(PixelFormat pixel_format, Drawable shared_drawable, ContextAttribs attr) throws LWJGLException {
        synchronized (HitomiLock.lock) {
            if (attr != null) {
                GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, attr.getMajorVersion());
                GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, attr.getMinorVersion());
            }
            if (!GLFW.glfwInit()) {
                throw new IllegalStateException("Unable to initialize GLFW");
            }
            GLFW.glfwDefaultWindowHints();
            GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_TRUE);
            HitomiDisplay.window = GLFW.glfwCreateWindow(HitomiDisplay.currentMode.getWidth(), HitomiDisplay.currentMode.getHeight(), "hikari.destination", 0, 0);
            if (HitomiDisplay.window == 0) {
                throw new RuntimeException("Failed to create window");
            }

            GLFW.glfwMakeContextCurrent(HitomiDisplay.window);
            HitomiDisplay.capabilities = GL.createCapabilities();
            GLFW.glfwShowWindow(HitomiDisplay.window);
        }
    }

    public static void create(PixelFormatLWJGL pixel_format) throws LWJGLException {
        create(pixel_format, null, null);
    }

    public static void create(PixelFormatLWJGL pixel_format, Drawable shared_drawable) throws LWJGLException {
        create(pixel_format, shared_drawable, null);
    }

    public static void create(PixelFormatLWJGL pixel_format, org.lwjgl.opengles.ContextAttribs attribs) throws LWJGLException {
        create(pixel_format, null, attribs);
    }

    @SuppressWarnings("all")
    public static void create(PixelFormatLWJGL pixel_format, Drawable shared_drawable, org.lwjgl.opengles.ContextAttribs attribs) throws LWJGLException {
        // TODO
    }

    @Deprecated
    public static void setInitialBackground(float red, float green, float blue) {
    }

    @SuppressWarnings("all")
    public static boolean isCreated() {
        synchronized (HitomiLock.lock) {
            return HitomiDisplay.isCreated;
        }
    }

    @SuppressWarnings("all")
    public static void setSwapInterval(int value) {
        GLFW.glfwSwapInterval(value);
    }

    public static void setVSyncEnabled(boolean sync) {
        setSwapInterval(sync ? 1 : 0);
    }

    public static void setLocation(int x_pos, int y_pos) {
        GLFW.glfwSetWindowPos(HitomiDisplay.window, x_pos, y_pos);
    }

    @Deprecated
    public static String getAdapter() {
        return null;
    }

    @Deprecated
    public static String getVersion() {
        return null;
    }

    public static int setIcon(ByteBuffer[] icons) throws InsufficientResourcesException {
        synchronized (HitomiLock.lock) {
            // FIXME: Delay setting title
            if (icons.length < 1)
                throw new InsufficientResourcesException("At lease one icon is needed!");
            if (isCreated()) {
                GLFW.glfwSetWindowIcon(HitomiDisplay.window, new GLFWImage.Buffer(icons[0]));
            }
            return icons.length;
        }
    }

    public static void setResizable(boolean resizable) {
        // TODO: compact for resizeable
        if (HitomiDisplay.isResizeable != resizable) {
            HitomiDisplay.isResizeable = resizable;
//            GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, resizable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
        }
    }

    public static boolean isResizable() {
        return HitomiDisplay.isResizeable;
    }

    public static boolean wasResized() {
        return HitomiDisplay.isResized;
    }

    public static int getX() {
        if (Display.isFullscreen()) {
            return 0;
        }

        int[] xpos = {0}, ypos = {0};
        GLFW.glfwGetWindowPos(HitomiDisplay.window, xpos, ypos);
        return xpos[0];
    }

    public static int getY() {
        if (Display.isFullscreen()) {
            return 0;
        }

        int[] xpos = {0}, ypos = {0};
        GLFW.glfwGetWindowPos(HitomiDisplay.window, xpos, ypos);
        return ypos[0];
    }

    public static int getWidth() {
        if (Display.isFullscreen()) {
            return Display.getDisplayMode().getWidth();
        }

        int[] width = {0}, height = {0};
        GLFW.glfwGetWindowSize(HitomiDisplay.window, width, height);
        return width[0];
    }

    public static int getHeight() {
        if (Display.isFullscreen()) {
            return Display.getDisplayMode().getHeight();
        }

        int[] width = {0}, height = {0};
        GLFW.glfwGetWindowSize(HitomiDisplay.window, width, height);
        return height[0];
    }

    public static float getPixelScaleFactor() {
        // TODO: macOS scaleFactor
        return 1f;
    }
}
