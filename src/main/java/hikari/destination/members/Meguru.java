package hikari.destination.members;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

public class Meguru implements ClassFileTransformer {
    private static Map<String, String> meguruMap = new HashMap<>();

    static {
        meguruMap.put("org/lwjgl/opengl/ContextCapabilities", "org/lwjgl/opengl/GLCapabilities");
    }

    @Override
    public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) {
        if (meguruMap.containsKey(s)) {
            String shine = meguruMap.get(s);
            Producer.load(shine);
            byte[] shinyClass = Producer.get(shine);
            return shinyClass;
        }
        return bytes;
    }
}
