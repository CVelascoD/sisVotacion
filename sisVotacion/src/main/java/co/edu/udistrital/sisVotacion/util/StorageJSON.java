package co.edu.udistrital.sisVotacion.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StorageJSON {

	private static final ObjectMapper mapper = new ObjectMapper();

//Leer Json
	public static <T> List<T> load(String FILE_PATH, Class<T> tipo) {
		System.out.println("ingresa a load: " + FILE_PATH);

		try {
			File file = new File(FILE_PATH);
			if (!file.exists()) {
				System.out.println("ruta: " + FILE_PATH);
				return new ArrayList<>();
			}
			return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, tipo));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error load json: " + e.getMessage());

			return new ArrayList<>();
		}
	}

	// Guardar Json
	public static <T> void save(String FILE_PATH, List<T> datos) {
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), datos);
		} catch (Exception e) {
			System.out.println("error save json: " + e.getMessage());
			e.printStackTrace();
		}
	}
}