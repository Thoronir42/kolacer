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
		Collection<ColorBundle> bundles = ColorBundle.Defaults();
		for(ColorBundle bundle : bundles){
			System.out.println(bundle.getName());
			LabeledColor color = null;
			for(Iterator<LabeledColor> it = bundle.getIterator(); it.hasNext(); color = it.next()){
				System.out.println(color);
			}
		}
		
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});
		
		StackPane root = new StackPane();
		root.getChildren().add(btn);
		
		Scene scene = new Scene(root, 300, 250);
		
		primaryStage.setTitle("Hello World!");
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
