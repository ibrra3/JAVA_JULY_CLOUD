package fp.tipos;

import java.util.Objects;

public class Espacio implements Comparable<Espacio> {

	private String nombre;
	private Integer capacidad;
	private tipoEspacio tipo;
	
	
	
	
	public Espacio(String nombre, Integer capacidad, tipoEspacio tipo) {
		super();
		Objects.requireNonNull(nombre);
		Objects.requireNonNull(capacidad);
		Objects.requireNonNull(tipo);
		
		
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.tipo = tipo;
		
		checkNombre();
		checkCapacidad();
	}
	
//_____________________	


//_____________________
	private void checkNombre() {
		if(nombre == null) {
			throw new NullPointerException();
		}
		if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del espacio no puede estar vacío.");
       }
       // Validación del formato del nombre para getPlanta()
       if (nombre.length() < 2) {
           throw new IllegalArgumentException("El nombre del espacio debe tener al menos 2 caracteres para determinar la planta.");
       }
	}
//_____________________
	private void checkCapacidad() {
		if(capacidad == null) {
			throw new NullPointerException();
		}
		if(capacidad<1) {
			throw new IllegalArgumentException("cap debe ser mayor que 0");
		}
	}
//_____________________

	@Override
	public String toString() { // "A3.10 (planta 3)"
		return  nombre + " (" + getPlanta() + ")";
	}


//_____________________

	private int getPlantaValor() {

        if (nombre == null || nombre.length() < 2) {
             throw new IllegalStateException("Estado interno inconsistente: el nombre no es válido para obtener la planta.");
        }
		return (int) nombre.charAt(1); // Devuelve el valor Unicode del carácter
	}

	public Integer getPlanta() {
		
		return getPlantaValor(); // Llama al método que obtiene el int, y el resultado int se convierte a Integer automáticamente.
	}

//_______________________________________________

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		checkNombre();
		this.nombre = nombre;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		checkCapacidad();
		this.capacidad = capacidad;
	}

	public tipoEspacio getTipo() {
		return tipo;
	}

	public void setTipo(tipoEspacio tipo) {
		Objects.requireNonNull(tipo);
		this.tipo = tipo;
	}

	
	
//_____________________	___________________________________
	
	
	
// 	dos espacios sean iguales deben coincidir su nombre y su planta. 
// 	Los espacios se ordenan por planta, y a igualdad de ésta por nombre.	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre,getPlanta());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Espacio other = (Espacio) obj;
		return ( Objects.equals(nombre, other.nombre) && Objects.equals(getPlanta(), other.getPlanta()) );
	}
	
	public int compareTo(Espacio o) {
		int res;
		if(o==null) {
			throw new NullPointerException();
		}
		res= getPlanta().compareTo(o.getPlanta());
		if(res==0) {
			res=getNombre().compareTo(o.getNombre());
		}
		return res;
	}
	
	
	
	
	
}
