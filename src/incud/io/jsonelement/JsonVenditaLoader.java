package incud.io.jsonelement;

import org.json.JSONArray;
import org.json.JSONObject;

import incud.immutable.*;
import incud.immutable.Vendita.Builder;
import incud.stato.Inventario;

public class JsonVenditaLoader extends JsonElement<Vendita> implements JsonArrayElement<Vendita.Builder> {

	public JsonVenditaLoader(JSONObject json) {
		super(json);
	}

	@Override
	protected Vendita getSimple(Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Vendita getStructured(JSONObject json) {
		Vendita.Builder builder = Vendita.newBuilder()
				.addNick(json.getString("nickCliente"))
				.addPrezzoTotale(json.getDouble("prezzoTotale"))
				.addDataAcquisto(incud.util.FormatoData.instance().parse(json.getString("dataAcquisto")))
				.addIp(json.getString("ip"))
				.addPagamento(Pagamento.valueOf(json.getString("pagamento")))
				.addConsegna(Consegna.valueOf(json.getString("consegna")));
		
		JSONArray array = json.getJSONArray("prodotti");
		JsonUtils.fillSimpleArrayInBuilder(array, this, builder);
		
		return builder.build();
	}

	@Override
	public void addSimple(Object element, Builder builder) {
		String codice = (String)element;
		Disco disco = Inventario.instance().findDisco(codice);
		builder.addProdotto(disco);
	}

	@Override
	public void addStructured(JSONObject element, Builder builder) {
		throw new UnsupportedOperationException();
	}

}
