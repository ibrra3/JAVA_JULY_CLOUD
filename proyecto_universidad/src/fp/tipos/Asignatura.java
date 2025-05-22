package fp.tipos;

import java.util.Objects;

public record Asignatura(String nombre, String codigo, Double creditos, 
		tipoAsignatura tipoAsignatura, Integer curso) implements Comparable<Asignatura> {

	

//Incluya para cada uno de ellos los atributos necesarios, los constructores, los getters/setters, 
	//y la representación como cadena (toString). Implemente también en el mismo paquete los tipos 
	//enumerados que dan soporte a las entidades descritas anteriormente.

	
// "(0000230) Fundamentos de Programación"
	
	public Asignatura{
		if (nombre == null || codigo == null || creditos == null || tipoAsignatura == null || curso == null) {
            throw new IllegalArgumentException("Ningún argumento del constructor puede ser null.");
       }
		checkAsignatura(codigo , creditos , curso);
	}
	
	
	@Override
	public String toString() {
		return "(" + codigo + ") " + nombre;
	}
	
	private void checkAsignatura(String codigo , Double creditos , Integer curso) {
		if (codigo.length() != 7) {
            throw new IllegalArgumentException("el codigo de asignatura debe tener exactamente 7 digitos. Longitud actual: " + codigo.length());
            }
		
		for(int i =0 ; i<7 ; i++) {
			
			if(!(Character.isDigit(codigo.charAt(i)))) {
				throw new IllegalArgumentException("el codigo de asignatura debe estar formado por 7 digitos");
			}
		}
		
		if(!(creditos>0)) {
			throw new IllegalArgumentException("El num de creditos debe ser mayor que cero");
		}
		
		if(!(0<curso&&curso<5)) {
			throw new IllegalArgumentException("el curso debe estar comprendido entre el primero y el cuarto");
		}
	}
	
		
	
//________________ CRITERIOS DE IGUALDAD Y COMPARACION 
	//misma asignatura si ambos objetos almacenan el mismo código.
	//las asignaturas ordenadas alfabéticamente por su código (se considera éste el orden natural para las asignaturas).
	
		@Override
		public int hashCode() {
			return Objects.hash(codigo);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Asignatura other = (Asignatura) obj;
			return Objects.equals(codigo, other.codigo);
		}
// otro equals pero hace lo mismo ____________________________________
		public boolean equalsOtro(Object o) {
			boolean result = false;
			if (o instanceof Asignatura) {
				Asignatura a = (Asignatura) o;
				result = Objects.equals(a.codigo, codigo);
				}
			return result;
		}
	
	
		public int compareTo(Asignatura o) {
			if(o==null) {
				throw new NullPointerException();
			}
			return codigo.compareTo(o.codigo);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
}