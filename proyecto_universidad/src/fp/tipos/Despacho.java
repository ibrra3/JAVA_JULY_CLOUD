package fp.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.tipos.*;
import fp.utiles.Checkers;
public class Despacho extends Espacio {

	

	private Set<Profesor> profesores;
	
	
	/*
	 * El primer constructor creará un despacho a partir de los mismos parámetros que el constructor del tipo Espacio salvo el tipo, 
	 * que se inicializará con el valor 'OTRO', junto con un conjunto de profesores. 
	 * 
	 * El segundo constructor creará un despacho a partir 
	 * de los mismos parámetros que el constructor del tipo Espacio salvo el tipo, que se inicializará con el valor 'OTRO', junto con 
	 * un solo profesor. 
	 * 
	 * Finalmente, el tercer constructor creará un despacho a partir de los mismos parámetros que el constructor 
	 * del tipo Espacio salvo el tipo, que se inicializará con el valor 'OTRO'. En este caso el despacho no tendrá ningún profesor asignado.			
	 */

	public Despacho(String nombre, Integer capacidad, Set<Profesor> profesores) {
		super(nombre, capacidad, tipoEspacio.OTRO);
		Checkers.checkNoNull(profesores);
		this.profesores = new HashSet<Profesor>();
		checkDespacho();

		
	}
	
	
	public Despacho(String nombre, Integer capacidad, Profesor profesor) {
		super(nombre, capacidad, tipoEspacio.OTRO);
		Checkers.checkNoNull(profesores);
		
		this.profesores = new HashSet<Profesor>();
		this.profesores.add(profesor);
		checkDespacho();
	}
	
	
	public Despacho(String nombre, Integer capacidad) {
		super(nombre, capacidad, tipoEspacio.OTRO);
		Checkers.checkNoNull(profesores);
		this.profesores = new HashSet<Profesor>();
		checkDespacho();

	}
	
			
			
	public Set<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(Set<Profesor> profesores) {
		this.profesores = profesores;
	}


	private void checkDespacho() {
		if(profesores.size()>getCapacidad()) {
			throw new IllegalArgumentException("el numero de profesores no puede superar la capacidad del Despacho");
		}
		
		if(getTipo()!=tipoEspacio.OTRO) {
			throw new  UnsupportedOperationException("el tipo de Despacho / Espacio debe ser 'OTRO' ");
		}
	}


	



			
			
			
	//Dos despachos son iguales si tienen el mismo nombre y están en la misma planta.  (IGUAL QUE ESPACIO -> NO SE IMPLEMENTA ) 
	
	//Además, su ordenación natural coincide con la de cualquier otro espacio. 			(TAMPOCO SE IMPLEMENTA ) 
			
	
	
	
	//un despacho tiene la misma representación que los espacios, seguida de los profesores que ocupan el despacho 
	//(utilice la representación como cadena de la propiedad profesores). 
	//Por ejemplo: "M2.25 (planta 2) [28200400P – Martín Oviedo, María – 21/05/1985 (TITULAR),
	//	33123210J – Vegarredonda Ordiales,Jorge – 25/11/1990 (CONTRATADO_DOCTOR)]"		
			
			
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + this.profesores.toString();
	}		
			
			
			
			
}
