package hikari.destination.members;

import hikari.destination.hitomi.HitomiStorage;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Mano implements ClassFileTransformer {
    private static Map<String, byte[]> manoMap = new HashMap<>();

    static {
        manoMap.put("org/lwjgl/PointerBuffer", Base64.getDecoder().decode(HitomiStorage.PointerBuffer));
    }

    @Override
    public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) {
        if (manoMap.containsKey(s)) {
            bytes = manoMap.get(s);
        }
        return bytes;
    }
}
