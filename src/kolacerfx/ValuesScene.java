package kolacerfx;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import kolacerfx.Data.DataItem;
import kolacerfx.Data.DataSet;
import kolacerfx.Data.DataParser;

/**
 *
 * @author Stepan
 */
public class ValuesScene extends Scene {

	public static ValuesScene getInstance() {
		BorderPane root = new BorderPane();
		ValuesScene scene = new ValuesScene(root, 1024, 760);

		root.setTop(createMenu(scene));
		root.setCenter(createCenter(scene));
		return scene;
	}

	private static MenuBar createMenu(ValuesScene scene) {
		MenuBar menu = new MenuBar();

		Menu menuFile = new Menu("Soubor");
		Menu fileImport = new Menu("Importovat");
		MenuItem fileImportClipboard = new MenuItem("Ze schránky");
		fileImportClipboard.setOnAction(e -> {
			scene.parseFromClipboard();
		});
		fileImport.getItems().add(fileImportClipboard);

		MenuItem fileClose = new MenuItem("Zavřít");
		menuFile.getItems().addAll(fileImport, new SeparatorMenuItem(), fileClose);

		menu.getMenus().addAll(menuFile);
		return menu;
	}

	private static GridPane createCenter(ValuesScene scene) {
		GridPane center = new GridPane();
		center.setAlignment(Pos.CENTER);
		center.setHgap(6);
		center.setVgap(4);

		scene.pieData = new ListView<>();
		center.add(scene.pieData, 0, 0);

		VBox vbox_cpr = new VBox(6); // color picker radios
		vbox_cpr.setPadding(new Insets(6));

		scene.radio_cp_random = new RadioButton("Náhodně");
		scene.radio_cp_random.setToggleGroup(scene.toggleGroup_colorPicker);

		scene.radio_cp_gradient = new RadioButton("Gradient");
		scene.radio_cp_gradient.setToggleGroup(scene.toggleGroup_colorPicker);
		scene.radio_cp_gradient.setDisable(true);

		scene.toggleGroup_colorPicker.selectToggle(scene.radio_cp_random);
		Label lbl_pickers = new Label("Způsob výběru barvy");
		vbox_cpr.getChildren().addAll(lbl_pickers, scene.radio_cp_random, scene.radio_cp_gradient);

		center.add(vbox_cpr, 1, 0);

		return center;
	}

	public final SimpleStringProperty title;

	private final SimpleObjectProperty<DataSet> dataSet;
	private ListView<DataItem> pieData;

	private final ToggleGroup toggleGroup_colorPicker;
	private RadioButton radio_cp_random, radio_cp_gradient;

	private ValuesScene(Parent root, double width, double height) {
		super(root, width, height);

		title = new SimpleStringProperty("Koláčer");

		dataSet = new SimpleObjectProperty<>();
		dataSet.addListener((listener, o, n) -> {
			this.pieData.setItems(n.Items);
		});

		toggleGroup_colorPicker = new ToggleGroup();
	}

	private void parseFromClipboard() {
		String content = ClipboardHelper.get();
		DataSet set = DataParser.FinfoParser().parse(content);
		System.out.format("[%s] - %d items\n", set.Title.get(), set.Items.size());
		this.dataSet.set(set);
	}
}
