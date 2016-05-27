package kolacerfx;

import javafx.scene.input.Clipboard;

/**
 *
 * @author Stepan
 */
public class ClipboardHelper {
	public static String get(){
		Clipboard clipboard = Clipboard.getSystemClipboard();
		return clipboard.getString();
	}
}
