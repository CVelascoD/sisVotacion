package co.edu.udistrital.sisVotacion.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; 

public class StorageJSON {

    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    // Leer Json
    public static <T> List<T> load(String FILE_PATH, Class<T> tipo) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, tipo));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error cargando JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Guardar Json
    public static <T> void save(String FILE_PATH, List<T> datos) {
        try {
            File file = new File(FILE_PATH);
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, datos);
        } catch (Exception e) {
            System.out.println("Error guardando JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}