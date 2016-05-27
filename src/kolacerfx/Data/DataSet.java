package kolacerfx.Data;

import java.util.Date;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Stepan
 */
public class DataSet {
	
	public final SimpleStringProperty Title;
	public final ObservableList<DataItem> Items;
	
	public final SimpleObjectProperty<Date> DateFrom;
	public final SimpleObjectProperty<Date> DateTo;
	
	
	public DataSet(){
		this("");
	}
	
	public DataSet(String title){
		this(FXCollections.observableArrayList(), title);
	}
	
	public DataSet(ObservableList<DataItem> set){
		this(set, "");
	}
	
	public DataSet(ObservableList<DataItem> set, String title){
		this.Title = new SimpleStringProperty(title);
		this.Items = set;
		
		this.DateFrom = new SimpleObjectProperty<>();
		this.DateTo = new SimpleObjectProperty<>();
	}
}
