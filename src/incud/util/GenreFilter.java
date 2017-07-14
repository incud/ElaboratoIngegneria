package incud.util;

import incud.immutable.*;

public class GenreFilter implements Filter<Disco> {

	private final Genere genre;
	
	public GenreFilter(Genere genere) {
		this.genre = genere;
	}

	@Override
	public boolean canPass(Disco data) {
		return data.genere.equals(genre);
	}
	
}
