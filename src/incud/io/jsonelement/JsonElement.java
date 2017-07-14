package incud.io.jsonelement;

import org.json.JSONObject;

public abstract class JsonElement<T> {

	private Object object;

	private JSONObject json;
	
	public JsonElement(Object object) {
		this.object = object;
		this.json = null;
	}
	
	public JsonElement(JSONObject json) {
		this.object = null;
		this.json = json;
	}
	
	protected abstract T getSimple(Object object);
	
	protected abstract T getStructured(JSONObject json);
	
	public T get() {
		if(object != null) {
			return getSimple(object);
		} else if(json != null) {
			return getStructured(json);
		} else {
			throw new AssertionError("Opzione impossibile");
		}
	}
}
