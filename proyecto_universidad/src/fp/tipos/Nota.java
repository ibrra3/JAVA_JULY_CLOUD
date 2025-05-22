package fp.tipos;

import java.util.Objects;

public record Nota(Asignatura asignatura, Integer curso, Double nota, convocatoria convo, Boolean mencionHonor) implements Comparable<Nota> {

	
	// curso es asi 2024-25
	
	
	
	public Nota(Asignatura asignatura, Integer curso, Double nota, convocatoria convo) {
        this(asignatura, curso, nota, convo, false);
        checkNota(nota);
        checkHonor(nota, mencionHonor);
    }
	public Nota {
		checkNota(nota);
		checkHonor(nota, mencionHonor);
	}
	@Override
	public String toString() {// "(0000230) Fundamentos de Programación, curso es asi 2014 ; cursoAcademico es asi 2014-15, PRIMERA, 7.5, NOTABLE"
		return asignatura + ", " +  curso  + ", " + convo + ", " + nota + ", " + getCalificacion();
				
	}	
	//_____________________________________________________________________
	private void checkNota(Double nota) {
		if(nota<0.0 || 10.0<nota) {
			throw new IllegalArgumentException("la nota debe estar comprendida entre 0 y 10");
		}
	}
	
	private void checkHonor(Double nota , Boolean mencionHonor) {
		if(nota<9.0 && mencionHonor) {
			throw new IllegalArgumentException("no se puede dar mat honor con menos de un 9");
		}
	}
	//_________________________________________________________________
	public Calificacion getCalificacion() {
		if(nota<5) {
			return Calificacion.SUSPENSO;
		}
		else if (5<=nota && nota<7) {
			return Calificacion.APROBADO;
		}
		else if (7<=nota && nota<9) {
			return Calificacion.NOTABLE;
		}
		else if(9<=nota && mencionHonor==false) {
			return Calificacion.SOBRESALIENTE;
		}
		else if(9<=nota && mencionHonor) {
			return Calificacion.MATRICULA_HONOR;
		}
		else return Calificacion.SUSPENSO;
	}
	//_____________________________________________________________________
	public String cursoAcademico() {
		return curso +"-"+ curso%100 +1;
	}

// EQUALS - IGUALDAD Y ORDEN -----------------------------------------------

	@Override
	public int hashCode() {
		return Objects.hash(asignatura, convo, curso);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		return Objects.equals(asignatura, other.asignatura) && convo == other.convo
				&& Objects.equals(curso, other.curso);
	}


	public int compareTo(Nota o) {
		int res;
		
		res=curso.compareTo(o.curso);
		if(res==0) {
			res=asignatura.compareTo(o.asignatura);
			if(res==0) {
				res=convo.compareTo(o.convo);
			}
		}
		return res;
	}
	
	//_____________________________________________________________________
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
