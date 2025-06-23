package fp.utiles;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ficheros {

    // Define la codificación de caracteres estándar (UTF-8) para todos los ficheros.

    static final Charset ENCODING = StandardCharsets.UTF_8;

    
    public static List<String> leeFichero(String errMsg, String path) {

	// 										lee y lo guarda en una arrayList  Devuelve una lista vacía si ocurre un error.   --------------- 
        List<String> res = null;
        try {
            res = Files.readAllLines(Paths.get(path), ENCODING); 

        } catch (IOException e) {
            System.out.println(errMsg);
            e.printStackTrace();
            res = new ArrayList<String>();
        }
        return res;
    }

   
    public static String leeFicheroTexto(String errMsg, String path) {
        
     // ------------------------------------------Lee un fichero de texto completo y lo devuelve como un único String.

		List<String> lineas = leeFichero(errMsg, path); // ahora lo tenemos todo en una AList , una linea por elemento 
        // Une todas las líneas de la lista en un solo String, eliminando los saltos de línea.
        return lineas.stream()
                .collect(Collectors.joining()); // el joining convierte una colección de textos en uno solo.
    }

    
    public static <T> List<T> leeFichero(String errMsg, String path, Function<String,T> deString_a_T) {
    
	 // lee el fichero y devuelve Una lista de objetos de tipo T. Devuelve una lista vacía si ocurre un error.

		List<T> res = null;// handler 
        try {
            // Convierte el fichero en un Stream de líneas, aplica la función de transformación a cada una
            // y recoge los resultados en una nueva lista.
            res = Files.lines(Paths.get(path), ENCODING)
                    .map(deString_a_T)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            System.out.println(errMsg);
            e.printStackTrace();
            res = new ArrayList<T>();
        }
        return res;
    }
}