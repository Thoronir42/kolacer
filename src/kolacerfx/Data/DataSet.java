package kolacerfx.Data;

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
	}
}
