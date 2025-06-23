package fp.tipos;

import java.util.Objects;

public class Espacio implements Comparable<Espacio> {

    private String nombre;
    private Integer capacidad;
    private tipoEspacio tipo;
    private Integer planta; // <-- CAMBIO 1: Se añade el atributo planta.

    /**
     * Constructor principal que recibe todos los atributos, incluyendo la planta.
     */
    public Espacio(String nombre, Integer planta, Integer capacidad, tipoEspacio tipo) {
        // --- CAMBIO 2: El constructor ahora recibe y almacena la planta ---
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(planta); // Se añade la comprobación para el nuevo atributo.
        Objects.requireNonNull(capacidad);
        Objects.requireNonNull(tipo);

        this.nombre = nombre;
        this.planta = planta;
        this.capacidad = capacidad;
        this.tipo = tipo;

        checkNombre();
        checkCapacidad();
    }
    
    // --- TAREA DE LA PARTE 4 ---
    /**
     * Constructor a partir de una cadena de texto en formato CSV.
     * @param lineaCSV Cadena con el formato "nombre,planta,capacidad,tipo".
     */
    public Espacio(String lineaCSV) {
        String[] trozos = lineaCSV.split(",");
        if (trozos.length != 4) {
            throw new IllegalArgumentException("La cadena de entrada no tiene el formato esperado (4 elementos separados por comas).");
        }

        // Se parsean los trozos y se asignan a los atributos.
        this.nombre = trozos[0].trim();
        this.planta = Integer.parseInt(trozos[1].trim());
        this.capacidad = Integer.parseInt(trozos[2].trim());
        this.tipo = tipoEspacio.valueOf(trozos[3].trim().toUpperCase());
        
        // Se ejecutan las comprobaciones de las propiedades.
        checkNombre();
        checkCapacidad();
    }
    // --- FIN DE LA TAREA ---

    private void checkNombre() {
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del espacio no puede estar vacío.");
        }
    }

    private void checkCapacidad() {
        if (capacidad < 1) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0.");
        }
    }

    // --- CAMBIO 3: getPlanta() ahora es un getter simple y correcto. ---
    public Integer getPlanta() {
        return this.planta;
    }
    
    // --- Getters y Setters estándar ---
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        checkNombre();
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
        checkCapacidad();
    }

    public tipoEspacio getTipo() {
        return tipo;
    }

    public void setTipo(tipoEspacio tipo) {
        this.tipo = tipo;
    }

    // --- Representación como cadena ---
    @Override
    public String toString() { // "A3.10 (planta 3)"
        return nombre + " (planta " + getPlanta() + ")";
    }

    // --- Igualdad y Orden (ahora usan el atributo 'planta') ---
    
    
// 	dos espacios sean iguales deben coincidir su nombre y su planta. 
// 	Los espacios se ordenan por planta, y a igualdad de ésta por nombre.	
	
    
    
    @Override
    public int hashCode() {
        return Objects.hash(nombre, planta);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Espacio other = (Espacio) obj;
        return Objects.equals(nombre, other.nombre) && Objects.equals(planta, other.planta);
    }

    @Override
    public int compareTo(Espacio o) {
        int res = this.planta.compareTo(o.getPlanta());
        if (res == 0) {
            res = this.nombre.compareTo(o.getNombre());
        }
        return res;
    }
}
