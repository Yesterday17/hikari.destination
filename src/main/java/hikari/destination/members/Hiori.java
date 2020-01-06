package hikari.destination.members;

import hikari.destination.Irumine;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class Hiori implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader classLoader, String name, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] clz) {
        if (Irumine.hioriMap.containsKey(name)) {
            String shine = Irumine.hioriMap.get(name);
            if (!Irumine.meguruLoaded.containsKey(shine)) {
                Meguru.load(shine);
            }
            clz = Irumine.meguruLoaded.get(shine);
        }
        return clz;
    }
}
