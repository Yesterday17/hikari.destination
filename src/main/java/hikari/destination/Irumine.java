package hikari.destination;

import hikari.destination.members.Hiori;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

public class Irumine {
    public static Map<String, String> hioriMap = new HashMap<>();
    public static Map<String, String> hioriReverseMap = new HashMap<>();
    public static Map<String, byte[]> meguruLoaded = new HashMap<>();

    private static void hioriPut(String k, String v) {
        hioriMap.put(k, v);
        hioriReverseMap.put(v, k);
    }

    static {
        hioriPut("org/lwjgl/Sys", "hikari/destination/lwjgl/Sys");
    }

    public static final String IrumineVer = "1.14.5.14";

    public static void premain(String args, Instrumentation inst) {
        inst.addTransformer(new Hiori(), true);
    }
}
