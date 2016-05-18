package kolacerfx;

import java.util.Collection;
import java.util.Iterator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import kolacerfx.Colors.ColorBundle;
import kolacerfx.Colors.LabeledColor;

/**
 *
 * @author Stepan
 */
public class KolacerFX extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		ValuesScene scene = ValuesScene.getInstance();
		
		primaryStage.titleProperty().bind(scene.title);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
