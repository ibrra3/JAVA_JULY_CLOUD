package fp.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;	
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class Profesor extends Persona {
	
	private tipoProfesor tipo;
	private SortedSet<Tutoria> tutorias;		//  INICIALMENTE EL PROFESOR NO TENDRIA TUTORIAS 
	
	
	/*
	 * subset operations  SortedSet<Integer> setName = new TreeSet<>(); ______________	106	 ________________________________________________ 
	 * first()
	 * last()
	 * headSet() returns elements before the specified element , not index
	 * tailSet() reuturns elementsa after and including the specified element 
	 * subSet(e1,e2) returns elements between e1 e2  including e1
	 * 
	 */
	
	public Profesor(String name, String dni, String apellidos, LocalDate fechaNac, String correo,tipoProfesor tipo) {
		super(name, dni, apellidos, fechaNac, correo);
		// TODO Auto-generated constructor stub
		
		checkEdad();
		Checkers.checkNoNull(tipo);
		
		this.tipo = tipo;
		this.tutorias = new TreeSet<>();
		
	}
	
	
	private void checkEdad() {
		if(this.getEdad()<18) {
			throw new IllegalArgumentException("el profesor debe ser mayor que 18");
		}
	}


	public tipoProfesor getTipo() {
		return tipo;
	}

	public void setTipo(tipoProfesor tipo) {
		Checkers.checkNoNull(tipo);
		this.tipo = tipo;
	}

	public SortedSet<Tutoria> getTutorias() {
		return tutorias;
	}


	//Se considera que dos profesores son iguales si tienen el mismo DNI, nombre y apellidos. 
	//Dos profesores se ordenan de forma natural utilizando el mismo criterio que cualquier otra persona.
	
	
	// IGUALDAD Y ORDEN IGUAL QUE PERSONA ENTONCES NO SE IMPLEMENTA .
	
	
	@Override
	public String toString() {
		//un profesor se representa de la misma forma que una persona, seguido de la categoría profesional entre paréntesis. 
		//Por ejemplo, "28200400P – Martín Oviedo, María – 21/05/1985 (TITULAR)"
		
		return super.toString() + " ("+tipo+")";
	}



	
	
	/*
	 * nuevaTutoria: que dados una hora de comienzo, de tipo LocalTime, un número entero que representa la duración y 
	 * un día de la semana (de tipo DayOfWeek), añade una nueva tutoría al profesor con esos datos.
	 * 
	 * 
	 * 
	 * borraTutoria: que dados una hora de comienzo (de tipo LocalTime) y un día de la semana (de tipo DayOfWeek), 
	 * elimina la tutoría con el día y hora de comienzo indicados; si el profesor no tenía esa tutoría, la operación no tiene efecto.
	 * 
	 * 
		borraTutorias: que elimina todas las tutorías del profesor.
	 */
	
	
	public void nuevaTutoria(LocalTime horaComienzo , Integer duracion , DayOfWeek diaSemana) {
		Checkers.checkNoNull(horaComienzo , duracion , diaSemana);
		Tutoria tutoria = new Tutoria(diaSemana, duracion, horaComienzo);
		tutorias.add(tutoria);
	}
	
	
	
	
	
	public void borraTutoria(LocalTime horaComienzo , DayOfWeek diaSemana) {
		Checkers.checkNoNull(horaComienzo , diaSemana);
		
		tutorias.removeIf(a->a.dia().equals(diaSemana) && a.horaComienzo().equals(horaComienzo));
		
		
	}
	
		
	
	public void borraTutorias() {
		this.tutorias.clear();
		//this.tutorias.removeAll(tutorias);  WORKS BUT NOT RECOMMENDED !! -106
	}
	
	
	
	

}
