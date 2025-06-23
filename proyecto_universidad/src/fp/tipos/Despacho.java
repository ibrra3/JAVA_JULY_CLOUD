package fp.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.utiles.Checkers;
import fp.tipos.Profesor;
import fp.tipos.Persona;
import fp.tipos.Espacio;

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

    public Despacho(String nombre,Integer planta, Integer capacidad, Set<Profesor> profesores) {
        super(nombre, planta, capacidad, tipoEspacio.OTRO);
        Checkers.checkNoNull(profesores);
        this.profesores = new HashSet<>(profesores); // Initialize with provided professors
        checkDespacho(); // Validate after initialization
        
    }//												 Ahora todos los constructores reciben la 'planta' para pasarla a super().

    // Constructor with a single professor
    public Despacho(String nombre,Integer planta, Integer capacidad, Profesor profesor) {
        super(nombre,planta, capacidad, tipoEspacio.OTRO);
        Checkers.checkNoNull(profesor);
        this.profesores = new HashSet<>();
        this.profesores.add(profesor); // Add the single professor
        checkDespacho(); // Validate after initialization
    }

    // Constructor with no professors
    public Despacho(String nombre,Integer planta, Integer capacidad) {
        super(nombre, planta, capacidad, tipoEspacio.OTRO);
        this.profesores = new HashSet<>(); // Initialize empty Set
        checkDespacho(); // Validate after initialization
    }

    // Getter for professors
    public Set<Profesor> getProfesores() {
        return new HashSet<>(profesores); // Return a defensive copy
    }

    // Setter for professors with validation
    public void setProfesores(Set<Profesor> profesores) {
        Checkers.checkNoNull(profesores);
        this.profesores = new HashSet<>(profesores); // Assign a new Set
        checkDespacho(); // Validate after assignment
    }

    // Validation method
    private void checkDespacho() {
        if (profesores.size() > getCapacidad()) {
            throw new IllegalArgumentException("El número de profesores no puede superar la capacidad del despacho.");
        }
        if (getTipo() != tipoEspacio.OTRO) {
            throw new UnsupportedOperationException("El tipo de despacho debe ser 'OTRO'.");
        }
    }

  

	//public void setTipo(tipoEspacio tipo) {
     //   throw new UnsupportedOperationException("No se puede cambiar el tipo de un despacho.");
    //}

	@Override
	public void setTipo(tipoEspacio tipo) {
		// TODO Auto-generated method stub
		super.setTipo(tipo);
		checkDespacho();
		
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
