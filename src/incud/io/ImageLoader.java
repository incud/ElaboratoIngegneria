package incud.io;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLoader {

	private static void loadEmptyImage(JLabel label) {
		label.setText("<no image>");
		label.setIcon(null);
	}
	
	public static void caricaImmagineNellaLabel(JLabel label, String path) {
		if(path == null || path.isEmpty()) {
			loadEmptyImage(label);
			return;
		}
		
		Image image = null;
		
		if(path.startsWith("http://") || path.startsWith("https://")) {
			try { image = ImageIO.read(new URL(path)); } catch(IOException e) { image = null; }
		}
		
		if(image != null) {
			image = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			label.setText(null);
			label.setIcon(new ImageIcon(image));
		} else {
			loadEmptyImage(label);
		}
		
		return;
	}
}
