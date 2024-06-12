package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DiaDiaProperties {
	
	private static final URL DIADIA_PROPERTIES = ClassLoader.getSystemResource("diadia.properties");
	private static final String PESO_MAX = "peso_max_borsa";
	private static final String CFU = "cfu_iniziali";
	private static Properties prop = null;

	public static int getCFU() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(CFU));
	}

	public static int getPesoMax() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}

	private static void carica() {
		prop = new Properties();
		try {
			FileInputStream input = new FileInputStream(DIADIA_PROPERTIES.getFile());
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}