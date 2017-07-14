package incud.io;

import org.json.JSONArray;
import org.json.JSONObject;

import incud.immutable.*;
import incud.io.jsonelement.JsonArrayElement;
import incud.io.jsonelement.JsonAutoreElement;
import incud.io.jsonelement.JsonClienteElement;
import incud.io.jsonelement.JsonDiscoElement;
import incud.io.jsonelement.JsonUtils;
import incud.io.jsonelement.JsonVenditaLoader;
import incud.stato.Inventario;
import incud.stato.RegistroClienti;

public class JsonLoader {

	private class AutoreLoader implements JsonArrayElement<Void> {

		@Override
		public void addSimple(Object element, Void builder) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addStructured(JSONObject element, Void builder) {
			JsonAutoreElement json = new JsonAutoreElement(element);
			Inventario.instance().addAutore(json.get());
		}
	}
	
	private class DiscoLoader implements JsonArrayElement<Void> {

		@Override
		public void addSimple(Object element, Void builder) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addStructured(JSONObject element, Void builder) {
			JsonDiscoElement json = new JsonDiscoElement(element);
			Inventario.instance().addDisco(json.get());			
		}
	}
	
	private class ScorteLoader implements JsonArrayElement<Void> {

		@Override
		public void addSimple(Object element, Void builder) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addStructured(JSONObject element, Void builder) {
			String codice = element.getString("codice");
			Disco disco   = Inventario.instance().findDisco(codice);
			int pezzi     = element.getInt("pezzi");
			Inventario.instance().addScorteDisco(disco, pezzi);
		}
	}
	
	private class ClienteLoader implements JsonArrayElement<Void> {

		@Override
		public void addSimple(Object element, Void builder) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addStructured(JSONObject element, Void builder) {
			JsonClienteElement json = new JsonClienteElement(element);
			RegistroClienti.instance().addCliente(json.get());
		}
	}

	private class VenditaLoader implements JsonArrayElement<Void> {

		@Override
		public void addSimple(Object element, Void builder) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addStructured(JSONObject element, Void builder) {
			JsonVenditaLoader json = new JsonVenditaLoader(element);
			RegistroClienti.instance().addVendita(json.get());
		}
	}
	
	private final String source;
	
	public JsonLoader(String source) {
		this.source = source;
	}
	
	public void load() {
		JSONObject json       = new JSONObject(source);
		JSONArray jsonAutori  = json.getJSONArray("autori");
		JSONArray jsonDischi  = json.getJSONArray("dischi");
		JSONArray jsonScorte  = json.getJSONArray("scorte");
		JSONArray jsonClienti = json.getJSONArray("clienti");
		JSONArray jsonVendite = json.getJSONArray("vendite");
		JsonUtils.fillStructuredArrayInBuilder(jsonAutori,  new AutoreLoader(),  null);
		JsonUtils.fillStructuredArrayInBuilder(jsonDischi,  new DiscoLoader(),   null);
		JsonUtils.fillStructuredArrayInBuilder(jsonScorte,  new ScorteLoader(),  null);
		JsonUtils.fillStructuredArrayInBuilder(jsonClienti, new ClienteLoader(), null);
		JsonUtils.fillStructuredArrayInBuilder(jsonVendite, new VenditaLoader(), null);
	}
}
