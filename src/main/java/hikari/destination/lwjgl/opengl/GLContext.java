package hikari.destination.lwjgl.opengl;

import hikari.destination.hitomi.HitomiDisplay;
import org.lwjgl.opengl.ContextCapabilities;

public final class GLContext {
    public static ContextCapabilities getCapabilities() {
        return (ContextCapabilities) (Object) HitomiDisplay.capabilities;
    }
}
