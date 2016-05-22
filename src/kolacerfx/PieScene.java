package kolacerfx;

import kolacerfx.Data.DataItem;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.BorderPane;
import kolacerfx.Colors.IColorPicker;

/**
 *
 * @author Stepan
 */
public class PieScene extends Scene{
	
	public static PieScene createInstance(Collection<DataItem> items, IColorPicker colorPicker){
		ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
		for(DataItem item : items){
			PieChart.Data d = new Data(item.Caption.get(), item.Count.get());
			pieData.add(d);
		}
		
		return createInstance(pieData, colorPicker);
	}
	
	public static PieScene createInstance(ObservableList<PieChart.Data> pieData, IColorPicker colorPicker){
		BorderPane root = new BorderPane();
		PieScene scene = new PieScene(root, pieData, colorPicker);
		
		return scene;
	}
	
	PieChart pieChart;
	
	private PieScene(Parent root, ObservableList<PieChart.Data> pieData, IColorPicker colorPicker){
		super(root, 1080, 600);
		pieChart = new PieChart(pieData);
	}
}
