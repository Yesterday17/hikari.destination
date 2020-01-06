package hikari.destination.utils;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.net.URI;

public class AWT {
    public static boolean openURL(String url) {
        try {
            java.awt.Desktop.getDesktop().browse(URI.create(url));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String getClipboardString() {
        try {
            return (String) java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            return null;
        }
    }
}
