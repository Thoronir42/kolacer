package kolacerfx.Colors.Pickers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.paint.Color;
import kolacerfx.Colors.ColorBundle;
import kolacerfx.Colors.LabeledColor;

/**
 *
 * @author Stepan
 */
public class RandomPicker implements IColorPicker{

	private final Collection<ColorBundle> colorBundles;
	
	public RandomPicker(){
		colorBundles = ColorBundle.Defaults();
		
		for(ColorBundle bundle : colorBundles){
			System.out.println(bundle.getName());
			
			for(Iterator<LabeledColor> it = bundle.getIterator(); it.hasNext();){
				LabeledColor color = it.next();
				System.out.println(color);
			}
		}
	}
	
	@Override
	public Color pick(int n) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
