package incud.io;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import incud.immutable.*;
import incud.stato.Inventario;
import incud.stato.RegistroClienti;
import incud.util.FormatoData;

public class DataLoader {
	
	public static final String DATABASE_PATH = "/incud/io/database.json";
	
	public static InputStream caricaFileDaJar(String path) {
		return DataLoader.class.getResourceAsStream(path);
	}
	
	public static String caricaFileTestualeDaJar(String path) {
		Scanner scanner = new Scanner(caricaFileDaJar(path));
		String source = scanner.useDelimiter("\\Z").next();
		scanner.close();
		return source;
	}
}