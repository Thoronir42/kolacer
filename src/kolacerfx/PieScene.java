package kolacerfx;

import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import kolacerfx.Colors.IColorPicker;

/**
 *
 * @author Stepan
 */
public class PieScene extends Scene{
	
	public static PieScene createInstance(ObservableList<PieChart.Data> pieData, IColorPicker colorPicker){
		BorderPane root = new BorderPane();
		PieScene scene = new PieScene(root, pieData, colorPicker);
		
		return scene;
	}
	
	
	private PieScene(Parent root, ObservableList<PieChart.Data> pieData, IColorPicker colorPicker){
		super(root, 1080, 600);
		
	}
}
