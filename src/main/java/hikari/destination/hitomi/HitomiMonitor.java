package hikari.destination.hitomi;

import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class HitomiMonitor {
    private static long[] getMonitors() {
        PointerBuffer glfwMonitors = GLFW.glfwGetMonitors();
        assert glfwMonitors != null;
        long[] monitors = new long[glfwMonitors.limit()];
        glfwMonitors.get(monitors);
        return monitors;
    }

    public static GLFWVidMode[] getVideoModes() {
        long[] monitors = getMonitors();
        GLFWVidMode[] modes = new GLFWVidMode[monitors.length];
        for (int i = 0; i < monitors.length; i++) {
            modes[i] = GLFW.glfwGetVideoMode(monitors[i]);
        }
        return modes;
    }

    public static DisplayMode[] fromGLFWVidMode(GLFWVidMode[] modes) {
        DisplayMode[] arr = new DisplayMode[modes.length];
        for (int i = 0; i < modes.length; i++) {
//            arr[i] = new DisplayMode(
//                    modes[i].width(), modes[i].height(),
//                    modes[i].redBits() + modes[i].greenBits() + modes[i].blueBits(),
//                    modes[i].refreshRate(), true
//            );
            arr[i] = new DisplayMode(modes[i].width(), modes[i].height());
        }
        return arr;
    }
}
