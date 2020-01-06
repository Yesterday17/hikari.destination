package hikari.destination.utils;

public class Info {
    public static boolean is64Bit() {
        return System.getProperty("os.arch").contains("64");
    }
}
