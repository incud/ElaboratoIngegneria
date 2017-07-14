package incud.util;

import incud.immutable.Disco;

public class AuthorFilter implements Filter<Disco> {

	private final String author;
	
	public AuthorFilter(String author) {
		this.author = author;
	}

	@Override
	public boolean canPass(Disco data) {
		return data.autore.toLowerCase().equalsIgnoreCase(author.toLowerCase());
	}
	
}
