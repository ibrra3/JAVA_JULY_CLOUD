package fp.tipos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Grado implements Comparable<Grado>{

	private String nombre;
	private Set <Asignatura> obligatorias;
	private Set <Asignatura> optativas;
	private Double minCreditosOptativas; // optativas 
	
	
	
	
	
	public Grado(String nombre, Set<Asignatura> obligatorias, Set<Asignatura> optativas, Double minCreditosOptativas) {
		
		Objects.requireNonNull(nombre,"nombre no puede estar null");
		Objects.requireNonNull(obligatorias,"obligatorias no puede estar null");
		Objects.requireNonNull(optativas,"optativas no puede estar null");
		Objects.requireNonNull(minCreditosOptativas,"minCreditosOptativas no puede estar null");
		
		this.nombre = nombre;
		this.obligatorias = obligatorias;
		this.optativas = optativas;
		this.minCreditosOptativas = minCreditosOptativas;
		
		checkOptativas();
		checkCreditosOptativas();
	}





	@Override
	public String toString() {
		return "Grado [nombre=" + nombre + "]";
	}
	
	//(1) Todas las asignaturas optativas del grado deben tener el mismo número de créditos. 
	//(2) El número mínimo de créditos de asignaturas optativas que debe cursar un alumno debe estar comprendido entre cero y
	//		el número total de créditos de asignaturas optativas del grado.
	
	
	public String getNombre() {
		return nombre;
	}

	public Set<Asignatura> getObligatorias() {
		return obligatorias;
	}

	public Set<Asignatura> getOptativas() {
		return optativas;
	}

	public Double getMinCreditosOptativas() {
		return minCreditosOptativas;
	}
	
	
	private void checkOptativas() {
		//(1) Todas las asignaturas optativas del grado deben tener el mismo número de créditos. 

		if(optativas.size()<=1) {
			return;
		}
		
		Set<Double> creditosOptativas = optativas.stream()
										.map(Asignatura :: creditos)
										.collect(Collectors.toSet()); // set sin repeticiones 
		
		
		if(creditosOptativas.size()>1) {
			throw new IllegalArgumentException("todas las optativas deben tener el mismo numero de creditos");
		}
		
	}
	
	
	
	
	private void checkCreditosOptativas() {
		
		Objects.requireNonNull(minCreditosOptativas,"minCreditos no puede ser null");
		Objects.requireNonNull(optativas,"las optativas no pueden ser null");
		
		double creditosOpt = optativas
								.stream().
								filter(a->a!=null && a.creditos()!=null)
								.mapToDouble(Asignatura :: creditos) // this turns it into a DoubleStream so we can use sum()
								.sum();
		
		
		if(minCreditosOptativas<0 || minCreditosOptativas>creditosOpt) {
			
			throw new IllegalArgumentException(
					
	                "El número mínimo de créditos optativos (" + minCreditosOptativas +
	                
	                ") debe estar entre 0.0 y el total de créditos optativos disponibles (" +
	                		
	                creditosOpt + ").");
	    }
		
	}


	
	// el número total de créditos que debe cursar un alumno para obtener el título. 
		//Este número se calcula sumando los créditos de todas las asignaturas obligatorias y
		//el número mínimo de créditos de asignaturas optativas que debe cursar.
	public Double getTotalCreditos() {
		//this refers to the specific Grado object on which the method was called (e.g., miGrado).
		
		double creditosObligatoria = obligatorias.stream().filter(a->a!=null && a.creditos()!=null).mapToDouble(Asignatura :: creditos).sum();
		
		return creditosObligatoria + minCreditosOptativas;
		
		
	}
	


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
		Grado other = (Grado) obj;
		return Objects.equals(nombre, other.nombre);
	}





	@Override
	public int compareTo(Grado o) {
		// TODO Auto-generated method stub
		Objects.requireNonNull(o,"no se puede comparar con un objecto null");
		int res;
		res=nombre.compareTo(o.nombre);
		return res;
	}
	
	
	// boolean changed = list1.removeAll(listToRemove);  DEVUELVE TRUE SI L1 CHANGED , FALSE IF NOT . 

	
	
	
	// METODOS ________________________________________________________________________________
	
	//getAsignaturas, que dado un curso, devuelve un conjunto con todas las asignaturas del grado, tanto obligatorias como optativas, 
	//que se imparten en el curso dado.
	
	public Set<Asignatura> getAsignaturas(Integer curso){
		
		Objects.requireNonNull(curso , "el curso no puede ser null");
		
		Set<Asignatura> conjuntoObligatorias = obligatorias.stream().filter(a->a!=null && a.curso().equals(curso)).collect(Collectors.toSet());
		Set<Asignatura> conjuntoOptativa = optativas.stream().filter(a->a!=null && a.curso().equals(curso)).collect(Collectors.toSet());
		
		Set<Asignatura> conjuntoAsignaturas = new HashSet<Asignatura>();

	    conjuntoAsignaturas.addAll(conjuntoObligatorias);
		conjuntoAsignaturas.addAll(conjuntoOptativa);
				
		return conjuntoAsignaturas;
		// or
		//____________________________________________________________________________________________
											// HAY QUE CONVERTIRLOS A STREAM() PARA PODER USAR CONCAT() -TKR
		//Set<Asignatura> conjunto = Stream.concat(conjuntoObligatorias.stream(), conjuntoOptativa.stream()).collect(Collectors.toSet());  
		 //return conjunto; 
		//____________________________________________________________________________________________
		
		//return Collections.unmodifiableSet(conjuntoAsignaturas);  si otro metodo intenta modificar el resutlado , no puede . 
	}																//add(), remove(), clear(), addAll(), removeAll()
	
	
	
	
	//getAsignatura, que dado un código, devuelve la asignatura del grado cuyo código es el dado como parámetro, 
	//o null si no existe ninguna asignatura con dicho código.
	
	
	public Asignatura getAsignatura2(String codigo){
		

		for(Asignatura a :  obligatorias) {
			if(a.codigo().equals(codigo)) {
				return a;
			}
		}
		
		for (Asignatura b : optativas) {
			if(b.codigo().equals(codigo)) {
				return b;
			}
		}
		
		return null;

	}
	
	
	public Asignatura getAsignatura(String codigo) {
		
		Optional <Asignatura> asOb = obligatorias.stream().filter(a->a!=null && a.codigo().equals(codigo)).findFirst();
		
		Optional <Asignatura> asOp = optativas.stream().filter(a->a!=null && a.codigo().equals(codigo)).findFirst();
		
		if(asOb.isPresent()) {
			return asOb.get();
		}
		else {
			return asOp.orElse(null);
		}
	}





	
	
	
	
}
