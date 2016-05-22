package kolacerfx.Data;

import java.util.Random;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

/**
 *
 * @author Stepan
 */
public class DataItem {
	
	public final SimpleStringProperty Caption;
	
	public final SimpleIntegerProperty Count;
	
	public final SimpleObjectProperty<Color> FillColor;
	
	public DataItem(String caption, int count){
		this(caption, count, null);
	}
	
	public DataItem(String caption, int count, Color color){
		this.Caption = new SimpleStringProperty(caption);
		this.Count = new SimpleIntegerProperty(count);
		
		if(color == null){
			color = randomColor();
		}
		
		this.FillColor = new SimpleObjectProperty<>(color);
	}
	
	private Color randomColor(){
		Random rand = new Random();
		return new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1);
	}
	
}
