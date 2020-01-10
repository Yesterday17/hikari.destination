package hikari.destination.members;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class Meguru implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) {
        // Meguru lost her job, sad(
        return bytes;
    }
}
