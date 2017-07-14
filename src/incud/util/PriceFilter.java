package incud.util;

import incud.immutable.*;

public class PriceFilter implements Filter<Disco> {
	
	private final double min, max;
	
	public PriceFilter(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public PriceFilter(String min, String max) {
		this(Double.parseDouble(min), Double.parseDouble(max));
	}

	@Override
	public boolean canPass(Disco data) {
		return min <= data.prezzo && data.prezzo <= max;
	}
}
