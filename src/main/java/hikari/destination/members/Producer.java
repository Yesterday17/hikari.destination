package hikari.destination.members;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class Producer {
    private static Map<String, byte[]> producerLoaded = new HashMap<>();

    public static void unsealShinyColors() {
        try {
            Field f = Attributes.Name.class.getDeclaredField("SEALED");
            f.setAccessible(true);

            Field fm = Field.class.getDeclaredField("modifiers");
            fm.setAccessible(true);
            fm.setInt(f, f.getModifiers() & ~Modifier.FINAL);

            f.set(null, new Attributes.Name("ShinyColors"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static boolean loaded(String key) {
        return producerLoaded.containsKey(key);
    }

    static byte[] get(String key) {
        return producerLoaded.get(key);
    }

    static void load(String shine) {
        InputStream is = Meguru.class.getResourceAsStream("/" + shine + ".class");
        try {
            ClassReader reader = new ClassReader(is);
            ClassNode node = new ClassNode();
            reader.accept(node, 0);
            node.name = Hiori.hioriBackIfNecessary(node.name);
            node.superName = Hiori.hioriBackIfNecessary(node.superName);
            for (int i = 0; i < node.interfaces.size(); i++) {
                node.interfaces.set(i, Hiori.hioriBackIfNecessary(node.interfaces.get(i)));
            }

            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            node.accept(writer);
            producerLoaded.putIfAbsent(shine, writer.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
