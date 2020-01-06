package hikari.destination;

import hikari.destination.members.Hiori;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class Irumine {
    public static Map<String, String> hioriMap = new HashMap<>();
    public static Map<String, String> hioriReverseMap = new HashMap<>();
    public static Map<String, byte[]> meguruLoaded = new HashMap<>();

    private static void hioriPut(String k, String v) {
        hioriMap.put(k, v);
        hioriReverseMap.put(v, k);
    }

    private static void hioriDirectPut(String name) {
        hioriPut("org/lwjgl/" + name, "hikari/destination/lwjgl/" + name);
    }

    public static String hioriBackIfNecessary(String current) {
        if (hioriReverseMap.containsKey(current))
            return hioriReverseMap.get(current);
        return current;
    }

    static {
        hioriDirectPut("Sys");
//        hioriDirectPut("LWJGLException");
//        hioriDirectPut("ContextAttribs");
        hioriDirectPut("opengl/Display");
//        hioriDirectPut("opengl/DisplayMode");
//        hioriDirectPut("Drawable");
//        hioriDirectPut("");
    }

    public static final String IrumineVer = "3.2.3"; // org.lwjgl.Version.getVersion();

    public static void premain(String args, Instrumentation inst) {
        inst.addTransformer(new Hiori(), true);
    }
}
