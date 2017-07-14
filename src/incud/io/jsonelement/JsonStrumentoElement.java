package incud.io.jsonelement;

import org.json.JSONObject;

import incud.immutable.Strumento;

public class JsonStrumentoElement extends JsonElement<Strumento> {

	public JsonStrumentoElement(Object object) {
		super(object);
	}

	@Override
	protected Strumento getSimple(Object object) {
		return Strumento.valueOf((String)object);
	}

	@Override
	protected Strumento getStructured(JSONObject json) {
		throw new UnsupportedOperationException();
	}

}
