package incud.io.jsonelement;

import org.json.JSONObject;

import incud.immutable.Cliente;

public class JsonClienteElement extends JsonElement<Cliente> {

	public JsonClienteElement(JSONObject json) {
		super(json);
	}

	@Override
	protected Cliente getSimple(Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Cliente getStructured(JSONObject json) {
		return Cliente.newBuilder()
				.addCodiceFiscale(json.getString("codiceFiscale"))
				.addNome(json.getString("nome"))
				.addCognome(json.getString("cognome"))
				.addNickname(json.getString("nickname"))
				.addPassword(json.getString("password"))
				.addCitta(json.getString("citta"))
				.addTelefono(json.getString("telefono"))
				.addCellulare(json.getString("cellulare"))
				.build();
	}

}
