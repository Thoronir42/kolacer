package kolacerfx;

import java.time.LocalDate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
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
		
		center.add(createVariousPane(scene), 0, 0);
		center.add(createItemsPane(scene), 1, 0);
		center.add(createAppearancePane(scene), 2, 0);
		
		
		return center;
	}

	private static GridPane createVariousPane(ValuesScene scene){
		GridPane left = new GridPane();
		
		LocalDate dateNow = LocalDate.now();
		LocalDate dateThen = dateNow.minusWeeks(2);
		
		DatePicker dateFrom = new DatePicker(dateThen);
		DatePicker dateTo = new DatePicker(dateNow);
		
		left.add(new Label("Období"), 0, 0, 3, 1);
		left.add(new Label("Od"), 1, 1);
		left.add(dateFrom, 2, 1);
		left.add(new Label("Do"), 1, 2);
		left.add(dateTo, 2, 2);
		
		return left;
	}
	
	private static GridPane createItemsPane(ValuesScene scene){
		GridPane middle = new GridPane();
		middle.setAlignment(Pos.CENTER);
		middle.setHgap(6);
		middle.setVgap(4);

		scene.pieDataView = new ListView<>();
		
		middle.add(scene.pieDataView, 0, 0);
		
		Button but_order = new Button("Seřadit");
		but_order.setOnAction(e -> {
			scene.orderItems();
		});
		
		middle.add(but_order, 0, 1);
		
		
		return middle;
	}
	
	private static GridPane createAppearancePane(ValuesScene scene){
		GridPane right = new GridPane();
		right.setAlignment(Pos.CENTER);
		right.setHgap(6);
		right.setVgap(4);

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
		
		right.add(vbox_cpr, 0, 0);
		right.add(new Separator(Orientation.HORIZONTAL), 0, 1);
		right.add(new Button("Nastavit barevník"), 0, 2);
		
		return right;
	}
	////
	
	public final SimpleStringProperty title;

	private final SimpleObjectProperty<DataSet> dataSet;
	private ListView<DataItem> pieDataView;

	private final ToggleGroup toggleGroup_colorPicker;
	private RadioButton radio_cp_random, radio_cp_gradient;

	private ValuesScene(Parent root, double width, double height) {
		super(root, width, height);

		title = new SimpleStringProperty("Koláčer");

		dataSet = new SimpleObjectProperty<>(new DataSet());
		dataSet.addListener((listener, o, n) -> {
			this.pieDataView.setItems(n.Items);
		});

		toggleGroup_colorPicker = new ToggleGroup();
	}

	private void parseFromClipboard() {
		String content = ClipboardHelper.get();
		DataSet set = DataParser.FinfoParser().parse(content);
		System.out.format("[%s] - %d items\n", set.Title.get(), set.Items.size());
		this.dataSet.set(set);
	}

	private void orderItems() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
