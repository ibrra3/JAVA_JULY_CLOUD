package fp.tipos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import fp.tipos.Persona;
import fp.tipos.Asignatura;

public class Alumno extends Persona{

	private Set<Asignatura> asignaturas = new HashSet<Asignatura>();
		
	//private Integer curso;   TODO
				
			
	public Alumno(String name, String dni, String apellidos, LocalDate fechaNac, String correo) {
		
		super(name, dni, apellidos, fechaNac, correo); // super() must be the first thing  !!  -106
		
		checkEmailAlumno(correo);
		
		this.asignaturas = new HashSet<>();
		// TODO Auto-generated constructor stub
	}
	
	private void checkEmailAlumno(String correo) {
		
		
		Objects.requireNonNull(correo,"el correo no puede estar null");
		
		if(correo.isEmpty()) {
			throw new IllegalArgumentException("el correo no puede estar vacio");
		}
		
		if(!correo.endsWith("@alum.us.es")) {
			throw new IllegalArgumentException("el correo debe terminar con @alum.us.es ");
		}
	}
	
	
	
	public Set<Asignatura> getAsignaturasMatriculadas() { // Or just getAsignaturas()
	    return new HashSet<>(this.asignaturas); // Return a defensive copy
	}

	
	public Integer getCurso() {
		// TODO Auto-generated method stub
		
		if(this.asignaturas ==null || this.asignaturas.isEmpty()) {
			return 0;
		}
		
		int maxCurso = 0;
		for(Asignatura asignatura : this.asignaturas) {
			
			if(asignatura.curso()>maxCurso) {
				maxCurso=asignatura.curso();
			}
		}
		
		return maxCurso;
	}
	
	public Integer getCurso2() {
		
		Integer res = this.asignaturas.stream().mapToInt(Asignatura :: curso).max().orElse(0);
		
		return res;
		
	}
	
	
	@Override
	public void setCorreo(String correo) {
		// TODO Auto-generated method stub
		checkEmailAlumno(correo);
		super.setCorreo(correo);
	}


	@Override
	public String toString() {// Formato: "(1º) 28864657W – García Vaquero, Pascual – 15/09/1998”
		// TODO Auto-generated method stub
		return "(" + getCurso() + "º)" + super.toString();  	// the super gives me access to the superior subject -106 
	}

			
	
	// IGUALDAD IGUAL QUE PERSONA , NO LO VOLVEMOS A IMPLEMENTAR _________________________________________________________________________________________________
	
	
	
	//matriculaAsignatura, que dada una asignatura, añade esa asignatura al conjunto de asignaturas en las que está matriculado el alumno. 
	//Si el alumno ya está matriculado en esa asignatura, se elevará la excepción IllegalArgumentException
	
	

	public void matriculaAsignatura(Asignatura asignatura) {
		Objects.requireNonNull(asignatura , "la asignatura a añadir no puede ser null");
		if(this.asignaturas.contains(asignatura)) {
			throw new IllegalArgumentException("la asignatura ya esta matriculada");
		}
	
		this.asignaturas.add(asignatura);
		
	}
	
	//eliminaAsignatura, que dada una asignatura, la elimina del conjunto de asignaturas en las que está matriculado el alumno. 
	//Si el alumno no está matriculado en esa asignatura, se elevará la excepción IllegalArgumentException.
	
	
	public void eliminaAsignatura (Asignatura asignatura) {
		Objects.requireNonNull(asignatura,"la asig a eliminar npuede ser null");
		if(this.asignaturas.contains(asignatura)) {
			this.asignaturas.remove(asignatura);
		}
		else {
			throw new IllegalArgumentException("la asignatura no se encuentra matriculada");
		}
	}
	
	

	//estaMatriculadoEn, que dada una asignatura devuelve true si el alumno está matriculado en la asignatura dada, y false en caso contrario.
	
	public boolean estaMatriculadoEn(Asignatura asignatura) {
		Objects.requireNonNull(asignatura,"la asignatura a comprobar no puede ser null");
		boolean res;
		res = this.asignaturas.contains(asignatura);
		return res;
	}
	
	
	
}
