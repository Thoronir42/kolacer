package kolacerfx;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

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
