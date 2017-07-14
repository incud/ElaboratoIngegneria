package main;

import incud.gui.controller.Controller;
import incud.gui.frame.Frame;
import incud.io.DataLoader;
import incud.io.JsonLoader;
import incud.stato.StatoUtente;

public class Main {

	public static void main(String[] args) {
		String jsonSource = DataLoader.caricaFileTestualeDaJar(DataLoader.DATABASE_PATH);
		(new JsonLoader(jsonSource)).load();
		
		Frame frame = Frame.instance();
		frame.cambiaMenu(StatoUtente.Stato.OSPITE);
		(new Controller()).openCatalogue();
		frame.setVisible(true);
	}

}