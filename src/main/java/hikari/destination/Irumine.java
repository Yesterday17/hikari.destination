package hikari.destination;

import hikari.destination.members.Hiori;
import hikari.destination.members.Mano;
import hikari.destination.members.Meguru;
import hikari.destination.members.Producer;

import java.lang.instrument.Instrumentation;

public class Irumine {
    public static final String IrumineVer = org.lwjgl.Version.getVersion();

    public static void premain(String args, Instrumentation inst) {
        Producer.unsealShinyColors(); // これはプロヂューサーの仕事ですｗｗ
        inst.addTransformer(new Mano(), true); // Transform class entirely
        inst.addTransformer(new Hiori(), true); // Rename package names
        inst.addTransformer(new Meguru(), true); // Safely replace LWJGL2 classes with LWJGL3 ones
    }
}
