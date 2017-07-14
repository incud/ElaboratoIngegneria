package incud.util;

import incud.immutable.Disco;

public class TitleFilter implements Filter<Disco> {

	private final String title;
	
	public TitleFilter(String title) {
		this.title = title;
	}

	@Override
	public boolean canPass(Disco data) {
		return data.titolo.toLowerCase().equalsIgnoreCase(title.toLowerCase());
	}
	
}
