package hikari.destination.members;

import hikari.destination.Irumine;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;
import java.io.InputStream;

class Meguru {
    static void load(String shine) {
        InputStream is = Meguru.class.getResourceAsStream("/" + shine + ".class");
        try {
            ClassReader reader = new ClassReader(is);
            ClassNode node = new ClassNode();
            reader.accept(node, 0);
            node.name = Irumine.hioriBackIfNecessary(node.name);
            node.superName = Irumine.hioriBackIfNecessary(node.superName);
            for (int i = 0; i < node.interfaces.size(); i++) {
                node.interfaces.set(i, Irumine.hioriBackIfNecessary(node.interfaces.get(i)));
            }

            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            node.accept(writer);
            Irumine.meguruLoaded.putIfAbsent(shine, writer.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
