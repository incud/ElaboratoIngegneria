package incud.io.jsonelement;

import java.util.Collection;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import incud.immutable.Autore;
import incud.immutable.Genere;
import incud.immutable.Autore.Builder;
import incud.util.FormatoData;
import incud.immutable.Strumento;

public class JsonAutoreElement extends JsonElement<Autore> implements JsonArrayElement<Autore.Builder> {

	public JsonAutoreElement(JSONObject json) {
		super(json);
	}

	@Override
	protected Autore getSimple(Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Autore getStructured(JSONObject json) {
		String nome   = json.getString("nomeArte");
		Genere genere = Genere.valueOf(json.getString("generePrincipale"));
		Date anno     = FormatoData.instance().parse(json.getString("annoNascita"));
		
		Autore.Builder builder = Autore.newBuilder()
				.addNome(nome)
				.addGenere(genere)
				.addAnnoNascita(anno);
		
		JSONArray array = json.getJSONArray("strumentiSuonati");
		JsonUtils.fillSimpleArrayInBuilder(array, this, builder);
		
		return builder.build();
	}

	@Override
	public void addSimple(Object element, Builder builder) {
		JsonStrumentoElement json = new JsonStrumentoElement(element);
		builder.addStrumento(json.get());
	}

	@Override
	public void addStructured(JSONObject element, Builder builder) {
		throw new UnsupportedOperationException();
	}

}
