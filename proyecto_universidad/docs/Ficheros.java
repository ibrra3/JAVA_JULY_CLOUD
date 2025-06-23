package fp.utiles;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Ficheros {

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

    /**
     * Escribe un Stream de objetos genéricos en un fichero.
     * @param <T>           Tipo genérico del objeto a escribir.
     * @param errMsg        Mensaje de error a mostrar si la escritura falla.
     * @param objetos       Stream de objetos que se van a escribir.
     * @param funcion       Función que sabe cómo convertir un objeto de tipo T en un String.
     * @param nombreFichero Nombre y ruta del fichero de salida.
     */
    public static <T> void escribeFichero(String errMsg, Stream<T> objetos, Function<T, String> funcion, String nombreFichero) {
        List<String> cadenas = objetos
                .map(funcion)
                .collect(Collectors.toList());
        try {
            Files.write(Paths.get(nombreFichero), cadenas, ENCODING);
        } catch (IOException e) {
            System.err.println(errMsg);
        }
    }

    /**
     * Escribe una única cadena de texto en un fichero.
     * @param errMsg        Mensaje de error a mostrar si la escritura falla.
     * @param texto         Cadena de texto a guardar.
     * @param nombreFichero Nombre y ruta del fichero de salida.
     */
    public static <T> void escribeFicheroTexto(String errMsg, String texto, String nombreFichero) {
        try {
            Files.write(Paths.get(nombreFichero),
                    texto.getBytes());
        } catch (IOException e) {
            System.err.println(errMsg);
        }
    }

    /**
     * Escribe la representación String de un único objeto en un fichero.
     * @param <T>           Tipo genérico del objeto.
     * @param errMsg        Mensaje de error.
     * @param objeto        Objeto a escribir en el fichero.
     * @param nombreFichero Nombre y ruta del fichero de salida.
     */
    public static <T> void escribeFichero(String errMsg, T objeto, String nombreFichero) {
        // Reutiliza el método principal de escritura, pasando un Stream con un solo objeto
        // y la función para convertirlo a String (Object::toString).
        escribeFichero(errMsg, Stream.of(objeto), Object::toString, nombreFichero);
    }

    /**
     * Escribe una colección de objetos en un fichero, un objeto por línea.
     * @param <T>           Tipo genérico de los objetos de la colección.
     * @param errMsg        Mensaje de error.
     * @param objetos       Colección de objetos a escribir.
     * @param nombreFichero Nombre y ruta del fichero de salida.
     */
    public static <T> void escribeFichero(String errMsg, Collection<T> objetos, String nombreFichero) {
        // Reutiliza el método principal, convirtiendo la colección en un Stream.
        escribeFichero(errMsg, objetos.stream(), Object::toString, nombreFichero);
    }

    /**
     * Escribe las entradas de un Map (clave-valor) en un fichero.
     * @param <K>           Tipo genérico de la clave.
     * @param <V>           Tipo genérico del valor.
     * @param errMsg        Mensaje de error.
     * @param m             Map cuyas entradas se van a escribir.
     * @param nombreFichero Nombre y ruta del fichero de salida.
     */
    public static <K, V> void escribeFichero(String errMsg, Map<K, V> m, String nombreFichero) {
        // Reutiliza el método principal, definiendo una función para formatear cada entrada
        // como "clave -> valor".
        escribeFichero(errMsg, m.entrySet().stream(),
                e -> e.getKey().toString() + "-> " + e.getValue().toString(),
                nombreFichero);
    }
}