package hikari.destination.members;

import hikari.destination.Irumine;
import org.objectweb.asm.*;

import java.io.IOException;
import java.io.InputStream;

class Meguru {
    static void load(String shine) {
        InputStream is = Meguru.class.getResourceAsStream("/" + shine + ".class");
        try {
            ClassReader cr = new ClassReader(is);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new ClassVisitor(Opcodes.ASM5, cw) {
                @Override
                public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                    // Replace current class
                    name = Irumine.hioriReverseMap.get(shine);
                    // Replace superclass
                    if (Irumine.hioriMap.containsKey(superName))
                        superName = Irumine.hioriReverseMap.get(superName);
                    // Replace interfaces
                    for (int i = 0; i < interfaces.length; i++) {
                        if (Irumine.hioriMap.containsKey(interfaces[i]))
                            interfaces[i] = Irumine.hioriReverseMap.get(interfaces[i]);
                    }
                    super.visit(version, access, name, signature, superName, interfaces);
                }
//
//                @Override
//                public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//                    MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
//                    return new MethodVisitor(Opcodes.ASM5, mv) {
//                        @Override
//                        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
//                            System.out.println(owner);
//                            if (Irumine.hioriMap.containsKey(owner))
//                                owner = Irumine.hioriReverseMap.get(owner);
//                            super.visitMethodInsn(opcode, owner, name, desc, itf);
//                        }
//
//                        @Override
//                        public void visitFieldInsn(int opcode, String owner, String name, String desc) {
//                            System.out.println(owner);
//                            if (Irumine.hioriMap.containsKey(owner))
//                                owner = Irumine.hioriReverseMap.get(owner);
//                            super.visitFieldInsn(opcode, owner, name, desc);
//                        }
//                    };
//                }
            };
            cr.accept(cv, 0);
            Irumine.meguruLoaded.putIfAbsent(shine, cw.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
