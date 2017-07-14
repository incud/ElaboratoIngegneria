package incud.io.jsonelement;

import org.json.JSONArray;
import org.json.JSONObject;

import incud.immutable.*;
import incud.immutable.Disco.Builder;

public class JsonDiscoElement extends JsonElement<Disco> implements JsonArrayElement<Disco.Builder> {

	public JsonDiscoElement(JSONObject json) {
		super(json);
	}

	@Override
	public void addSimple(Object element, Builder builder) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addStructured(JSONObject element, Builder builder) {
		JsonCanzoneElement json = new JsonCanzoneElement(element);
		builder.addCanzone(json.get());
	}

	@Override
	protected Disco getSimple(Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Disco getStructured(JSONObject json) {
		Disco.Builder builder = Disco.newBuilder()
				.addCodice(json.getString("codice"))
				.addTitolo(json.getString("titolo"))
				.addAutore(json.getString("autore"))
				.addGenere(Genere.valueOf(json.getString("genere")))
				.addPrezzo(json.getDouble("prezzo"))
				.addDescrizione(json.getString("descrizione"))
				.addPercorsoFoto(json.getString("percorsoFoto"))
				.addDataCaricamento(incud.util.FormatoData.instance().parse(json.getString("dataCaricamento")));
		
		JSONArray array = json.getJSONArray("canzoni");
		JsonUtils.fillStructuredArrayInBuilder(array, this, builder);
		
		return builder.build();
	}

}
