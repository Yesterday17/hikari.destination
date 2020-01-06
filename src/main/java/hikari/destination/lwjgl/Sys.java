package hikari.destination.lwjgl;

import hikari.destination.Irumine;
import hikari.destination.utils.AWT;
import hikari.destination.utils.Info;

public class Sys {
    public static String getVersion() {
        return Irumine.IrumineVer;
    }

    public static void initialize() {
    }

    public static boolean is64Bit() {
        return Info.is64Bit();
    }

    public static long getTimerResolution() {
        return 1000;
    }

    public static long getTime() {
        return System.currentTimeMillis() & 0x7FFFFFFFFFFFFFFFL; // 1 << 63
    }

    public static void alert(String title, String message) {
        // TODO
    }

    public static boolean openURL(String url) {
        return AWT.openURL(url);
    }

    public static String getClipboard() {
        return AWT.getClipboardString();
    }
}
