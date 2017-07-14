package incud.io.jsonelement;

import org.json.JSONObject;

import incud.immutable.Partecipazione;
import incud.immutable.Strumento;

public class JsonPartecipazioneElement extends JsonElement<Partecipazione> {

	public JsonPartecipazioneElement(JSONObject json) {
		super(json);
	}

	@Override
	protected Partecipazione getSimple(Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Partecipazione getStructured(JSONObject json) {
		String musicista    = json.getString("musicista");
		Strumento strumento = Strumento.valueOf(json.getString("strumento"));
		return new Partecipazione(musicista, strumento);
	}

}
