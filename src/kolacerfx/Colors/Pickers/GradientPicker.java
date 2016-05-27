package kolacerfx.Colors.Pickers;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

/**
 *
 * @author Stepan
 */
public class GradientPicker implements IColorPicker {

	public final SimpleIntegerProperty ItemCount;
	public final SimpleDoubleProperty Step;

	public final SimpleObjectProperty<Color> ColorA;
	public final SimpleObjectProperty<Color> ColorB;

	public GradientPicker(int count) {
		this(count, Color.WHITE, Color.BLACK);
	}

	public GradientPicker(int count, Color colorA, Color colorB) {
		ItemCount = new SimpleIntegerProperty(count);
		Step = new SimpleDoubleProperty();

		SimpleIntegerProperty One = new SimpleIntegerProperty(1);
		Step.bind(One.divide(ItemCount));

		ColorA = new SimpleObjectProperty<>(colorA);
		ColorB = new SimpleObjectProperty<>(colorB);
	}

	@Override
	public Color pick(int n) {
		if (n < 0 || ItemCount.get() < n) {
			throw new IllegalArgumentException(String.format("Color index %d  is out of available range <%d, %d>", n, 0, ItemCount.get()));
		}

		Color A = ColorA.get();
		Color B = ColorB.get();

		return A.interpolate(B, Step.multiply(n).doubleValue());
	}

}
