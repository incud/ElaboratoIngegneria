package incud.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatoData extends SimpleDateFormat {
	
	public static FormatoData instance() {
		return new FormatoData();
	}
	
	private FormatoData() {
		super("dd-MM-yyyy");
	}
	
	@Override
	public Date parse(String source) {
		try {
			return super.parse(source);
		} catch(ParseException e) {
			return null;
		}
	}
}
