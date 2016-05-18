package kolacerfx.Colors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Stepan
 */
public class ColorBundle implements ITreeColor{
	
	public static Collection<ColorBundle> Defaults(){
		ArrayList<ColorBundle> bundles = new ArrayList<>();
		
		bundles.add(Red());
		bundles.add(Yellow());
		
		return bundles;
	}
	
	public static ColorBundle Red(){
		String[] colors = new String[]{"#ED5314", "#FF8C00", "#FF1493", 
            "#CE0000", "#DD4B11", "#F54040"};
		return new ColorBundle(colors, "Červená");
	}
	public static ColorBundle Yellow(){
		String[] colors = new String[]{"#FFCC00", "#FFDE00", "#F7AD00",
            "#FFE500",};
		return new ColorBundle(colors, "Žlutá");
	}
	
	String name;
	TreeItem<LabeledColor> branch;
	
	public ColorBundle(String name){
		this(new ArrayList<>(), name);
		
	}
	
	public ColorBundle(String[] colors, String name){
		this(LabeledColor.parse(colors), name);
	}
	
	public ColorBundle(Collection<LabeledColor> labeledColors, String name){
		this.name = name;
		branch = new TreeItem<>();
		ObservableList<TreeItem<LabeledColor>> leaves = branch.getChildren();
		
		for(LabeledColor lc : labeledColors){
			leaves.add(new TreeItem<>(lc));
		}
		
	}
	
	public String getName(){
		return this.name;
	}

	public Iterator<LabeledColor> getIterator() {
		return new BundleIterator<>(branch.getChildren().iterator());
	}
	
	class BundleIterator<T> implements Iterator<T>{

		Iterator<TreeItem<T>> treeIterator;
		
		public BundleIterator(Iterator<TreeItem<T>> treeIterator) {
			this.treeIterator = treeIterator;
		}

		
		
		@Override
		public boolean hasNext() {
			return treeIterator.hasNext();
		}

		@Override
		public T next() {
			return treeIterator.next().getValue();
		}
		
	}
	
}
