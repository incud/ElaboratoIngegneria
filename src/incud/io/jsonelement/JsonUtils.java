package incud.io.jsonelement;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {
	
	public static <T, Builder> void fillSimpleArrayInBuilder(
			JSONArray array,
			JsonArrayElement<Builder> arrayElement,
			Builder builder) {
		
		for(int i = 0; i < array.length(); i++) {
			Object object = array.get(i);
			arrayElement.addSimple(object, builder);
		}
	}
	
	public static <T, Builder> void fillStructuredArrayInBuilder(
			JSONArray array,
			JsonArrayElement<Builder> arrayElement,
			Builder builder) {
		
		for(int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			arrayElement.addStructured(object, builder);
		}
	}
	
}
