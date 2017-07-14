package incud.io.jsonelement;

import org.json.JSONArray;
import org.json.JSONObject;

import incud.immutable.Canzone;
import incud.immutable.Canzone.Builder;
import incud.immutable.Partecipazione;

public class JsonCanzoneElement extends JsonElement<Canzone> implements JsonArrayElement<Canzone.Builder> {

	public JsonCanzoneElement(JSONObject json) {
		super(json);
	}

	@Override
	protected Canzone getSimple(Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Canzone getStructured(JSONObject json) {
		Canzone.Builder builder = Canzone.newBuilder().addTitolo(json.getString("titolo"));
		
		JSONArray array = json.getJSONArray("partecipazioni");
		JsonUtils.fillStructuredArrayInBuilder(array, this, builder);
		
		return builder.build();
	}

	@Override
	public void addSimple(Object element, Builder builder) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addStructured(JSONObject element, Builder builder) {
		JsonPartecipazioneElement json = new JsonPartecipazioneElement(element);
		builder.addPartecipazione(json.get());
	}
}
