package incud.io.jsonelement;

import org.json.JSONObject;

public interface JsonArrayElement<Builder> {
	
	public void addSimple(Object element, Builder builder);

	public void addStructured(JSONObject element, Builder builder);
}
