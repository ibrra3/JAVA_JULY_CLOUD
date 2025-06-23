package fp.tipos;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Persona implements Comparable<Persona>{
	private String name;
	private String dni;
	private String apellidos;
	private LocalDate fechaNac;
	private String correo;

	
	
	
	// CONSTRUCTORES ____________________________________________________________________________________		
	public Persona(String name, String dni, String apellidos, LocalDate fechaNac, String correo) {
		super();
		checkDni(dni);
		checkEmail(correo);
		this.name = name;
		this.dni = dni;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
		this.correo = correo;
	}


	public Persona(String name, String dni, String apellidos, LocalDate fechaNac) {
		
		this(name,dni,apellidos,fechaNac,"");
		
	}
//----------------------
	private void checkEmail(String correo) {
	    if (correo != null) { // Check not null first!
	        // If it's not null, it must contain '@' and not be empty
	        if (!correo.isEmpty() && !correo.contains("@")) {
	            throw new IllegalArgumentException("El correo proporcionado debe contener '@' y no puede estar vacío.");
	        }
	    }// empty string -> myString.equals("") returns true._______-------________------
	    // If correo is null, no exception is thrown (null is allowed)
	}
//----------------------	
	private void checkDni(String dni) {//El DNI está formado por 8 dígitos y una letra
		
		if (dni == null) {
	        throw new IllegalArgumentException("El DNI no puede ser null.");
	    }

		if(dni.length()!=9) {
			throw new IllegalArgumentException("El DNI está formado por 8 dígitos y una letra");
		}
		
		for (int i=0 ; i<8  ; i++) {
			
			if( (!(Character.isDigit(dni.charAt(i)) ))) {
				throw new IllegalArgumentException("El DNI debe estar formado por 8 dígitos y una letra");
			}
		}
		
		if  (!(Character.isLetter(dni.charAt(8)) )) {
			
			throw new IllegalArgumentException("El DNI debe estar formado por 8 dígitos y una letra");
		}
		
	}
//----------------------
	
	public Integer getEdad() {
		Period periodo = fechaNac.until(LocalDate.now());
		return periodo.getYears();
	}
	
	

	
//----------------------


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		checkDni(dni);
		this.dni = dni;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public LocalDate getFechaNac() {
		return fechaNac;
	}


	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		checkEmail(correo);
		this.correo = correo;
	}
//----------------------


	// toString ____________________________________________________________________________________	
	@Override
	public String toString() {// "28864657W – García Vaquero, Pascual – 15/09/1998"
		return dni + " - " + apellidos + ", " + name + " - " + fechaNac;
	}


	
	
// --_______________________  igualdad y eso....
	
//	Dos personas son iguales si tienen el mismo DNI, nombre y apellidos. 
//	Las personas se ordenan por apellidos, a igualdad de apellidos por nombre, y a igualdad de nombre por DNI.	
	
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, dni, name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(dni, other.dni) && Objects.equals(name, other.name);
	}
	
	public int compareTo(Persona o) {
		int res;
		
		res = getApellidos().compareTo(o.getApellidos());
		if(res==0) {
			res=getName().compareTo(o.getName());
			
			if(res==0) {
				res=getDni().compareTo(o.getDni());
			}
		}
		return res;
	  }
	
	
	
	
	
}
