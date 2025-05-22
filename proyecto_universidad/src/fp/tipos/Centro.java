package fp.tipos;

import java.util.HashSet;
import java.util.Objects;

public record Centro(String nombre , String dir , Integer plantas , Integer sotanos , HashSet <Espacio> espacios ) implements Comparable<Centro>{
	
	public Centro{
		//(1) Un centro debe tener al menos una planta, y (2) Un centro puede tener cero o más sótanos.
		Objects.requireNonNull(nombre);
		Objects.requireNonNull(dir);
		
		if(plantas==null || plantas <1) {
			throw new IllegalArgumentException("plantas minimo 1");
		}
		if(sotanos == null || sotanos<0) {
			throw new IllegalArgumentException("sotanos minimo 0");
		}
		
		Objects.requireNonNull(espacios);
		
	}
	
	
	public Centro(String nombre , String dir ,Integer plantas, Integer sotanos) {
		this(nombre ,dir , plantas ,sotanos , new HashSet<>());
	}
	
	
	public void nuevoEspacio(Espacio e) {
		
	
	//añade el espacio e al centro, siempre que la planta del espacio sea un número de planta válido en el centro. 
	//Para ello debe estar comprendida en el intervalo [-s, p-1], 
	//siendo p el número de plantas del centro y s el número de sótanos. En caso contrario, eleve la excepción IllegalArgumentException.
	
		if(e.getPlanta()>=sotanos && e.getPlanta() <= plantas ) {
			
			espacios.add(e);
		}
		
		else {
			throw new IllegalArgumentException("el espacio debe estar ubicado en una planta / sotano valido");
		}
		
		
	
	}
	
	
	public void eliminaEspacio(Espacio e) {
		
		// Elimina el espacio e del centro. Si el espacio no pertenece al centro, la operación no tiene efecto.
		Objects.requireNonNull(e);
		
			espacios.remove(e);
		
		
	}

//  	IGUALDAD Y ORDEN ----------------------------------------------------------
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Centro other = (Centro) obj;
		return Objects.equals(nombre, other.nombre);
	}


	@Override
	public int compareTo(Centro o) {
		// TODO Auto-generated method stub
		int res;
		if(o==null) {
			throw new NullPointerException();
		}
		res=nombre.compareTo(o.nombre);
		return res;
	}
	
	/*El tipo Centro también tendrá las siguientes operaciones, que de momento, dejaremos en blanco,
	 *  haciendo que devuelvan null y poniendo un comentario //TODO:
	 *  
	 *  getConteosEspacios, devuelve un array de 5 elementos de tipo Integer que representan el número de espacios de tipo aula de teoría, laboratorio, 
	 *  seminario, aula de examen u otro tipo, respectivamente, que hay en el centro.
	 *  
		getDespachos, devuelve un conjunto con todos los espacios de tipo Despacho que hay en el centro.
		
		getDespachos, dado un departamento d, devuelve un conjunto con todos los despachos del centro donde hay al menos un profesor del departamento d.
		
		getProfesores, devuelve el conjunto de todos los profesores que tienen un despacho en el centro.
		
		getProfesores, dado un departametno d, devuelve el conjunto de todos los profesores que pertenecen al departamento d y tienen un despacho en el centro.
		
		getEspacioMayorCapacidad, devuelve el espacio con mayor capacidad del centro.
	 */


	
	
}
