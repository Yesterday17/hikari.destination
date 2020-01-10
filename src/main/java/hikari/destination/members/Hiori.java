package hikari.destination.members;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

public class Hiori implements ClassFileTransformer {
    private static Map<String, String> hioriMap = new HashMap<>();
    private static Map<String, String> hioriReverseMap = new HashMap<>();

    private static void hioriPut(String k, String v) {
        hioriMap.put(k, v);
        hioriReverseMap.put(v, k);
    }

    private static void hioriDirectPut(String name) {
        hioriPut("org/lwjgl/" + name, "hikari/destination/lwjgl/" + name);
    }

    static {
        hioriDirectPut("Sys");
        hioriDirectPut("opengl/Display");
    }

    static String get(String key) {
        return hioriMap.get(key);
    }

    static String getKey(String value) {
        return hioriReverseMap.get(value);
    }

    static String hioriBackIfNecessary(String current) {
        if (hioriReverseMap.containsKey(current))
            return hioriReverseMap.get(current);
        return current;
    }

    @Override
    public byte[] transform(ClassLoader classLoader, String name, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] clz) {
        if (hioriMap.containsKey(name)) {
            String shine = hioriMap.get(name);
            if (!Producer.loaded(shine)) {
                Producer.load(shine);
            }
            clz = Producer.get(shine);
        }
        return clz;
    }
}
