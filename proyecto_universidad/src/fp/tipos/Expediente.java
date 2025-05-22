package fp.tipos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Expediente {
	private final List <Nota> notas;
	
	
	public Expediente() {
		this.notas = new ArrayList<>();
	}

	
	
	
	public List<Nota> getNotas() {
		return notas;
	}




	public double getNotaMedia() {
		
		//nota media, que se calcula como el promedio de las calificaciones 
		//con valores numéricos iguales o superiores a 5. En caso de que el expediente esté vacío, la nota media será 0.0.

		double suma=0.0;
		int nNotas=0;
		
		for (Nota nota : notas) {
			if(nota.nota()==null) {
				return 0.0;
			}
			if(5.0<=nota.nota()) {
				suma+=nota.nota();
				nNotas+=1;
			}
		}
		if(nNotas==0) {
			return 0.0;
		}
		else {
			return suma/nNotas;
		}
	}

	@Override
	public String toString() {
		//public record Nota(Asignatura asignatura, Integer curso, Double nota, convocatoria convo, Boolean mencionHonor)
		//return "Expediente [notas=" + notas + "]";
		return this.notas.toString();
		//The List.toString() method naturally produces the format [element1, element2, ...].
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(notas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expediente other = (Expediente) obj;
		return Objects.equals(notas, other.notas);
	}
	
	
	
	public void nuevaNota(Nota n) {
	
	//Añade la nota n al expediente. Si en el expediente ya existe una nota para la misma asignatura, 
	//convocatoria y curso académico (es decir, otra nota igual según el criterio de igualdad del tipo Nota), 
	//se elimina la nota antigua y se añade la nueva. Las notas se añaden siempre al final de la lista.
	
		
		Objects.requireNonNull(n, "La nota a añadir no puede ser null."); // Add this
		notas.remove(n); // Attempt to remove 'n' if an equal one exists. Does nothing if not found.
		notas.add(n);    // Add the new 'n' to the end of the list.
		    
	}
		
		
	private void nuevaNota2(Nota n) {

		if (notas.contains(n)) {
				
				notas.remove(n);
				notas.add(n);
		}
		else {
			notas.add(n);
			
		}
		}
	
	
	
	
	
	
	
}
