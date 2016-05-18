package kolacerfx.Colors;

import java.util.ArrayList;
import java.util.Collection;
import javafx.scene.paint.Color;

/**
 *
 * @author Stepan
 */
public class LabeledColor implements ITreeColor{
	
	public static Collection<LabeledColor> parse(String[] colors){
		ArrayList<LabeledColor> labeledColors = new ArrayList<>();
		int n = 0;
		for(String colorString : colors){
			Color c = Color.web(colorString);
			LabeledColor lc = new LabeledColor(++n +"", c);
			labeledColors.add(lc);
		}
		return labeledColors;
	}
	
	public final String label;
	public final Color color;

	public LabeledColor(String label, Color Color) {
		this.label = label;
		this.color = Color;
	}
	
	@Override
	public String getName(){
		return this.label;
	}

	@Override
	public String toString() {
		return String.format("%s \"%s\" [%s]", getClass().getSimpleName(), this.label, this.color);
	}
	
	

}
