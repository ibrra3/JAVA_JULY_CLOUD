package fp.tipos;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;


public record Tutoria(DayOfWeek dia , LocalTime horaComienzo , LocalTime horaFin) implements Comparable<Tutoria> {
		
	
	//La implementación del tipo tendrá dos constructores, uno con el día de la semana, 
	// la hora de inicio y la hora de fin, y otro con el día, la hora de inicio y la duración.
	
	public Tutoria{
		if (dia == null || horaComienzo == null || horaFin == null) {
	        throw new IllegalArgumentException("dia, horaComienzo y horaFin no pueden ser null.");
	    }
		checkHora(horaComienzo, horaFin);
		checkDia(dia);
		checkDuracion(horaComienzo,horaFin);
		//checkDuration2();
	}

	// TEKKERA ELECTRONIX , THE IMPOSIBLE DEATH;)
	public Tutoria(DayOfWeek dia , Integer duracion , LocalTime horaComienzo) {
		this(dia , horaComienzo , horaComienzo.plusMinutes(duracion));
		checkDuracion(horaComienzo,horaFin);

	}
	
	public Integer duracion() {
		Duration duracion = Duration.between(horaComienzo, horaFin);
		Long minutos = duracion.toMinutes();
		
		return minutos.intValue();
		/*
		int minutosComienzo = comienzo.getHour() * 60 + comienzo.getMinute();
		int minutosFin = fin.getHour() * 60 + fin.getMinute();
		return minutosFin - minutosComienzo;
		*/
	}
	
	public void checkDia(DayOfWeek dia) {
		if(dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY) {
			throw  new IllegalArgumentException("las tutorias deben ser entre semanas");
		}	
	}
	
	private void checkHora(LocalTime horaComienzo , LocalTime horaFin) {
		if (horaComienzo.isAfter(horaFin)){
			throw  new IllegalArgumentException("hora comienzo debe ser antes de hora fin");
		}
	}
	
	
	private void checkDuracion(LocalTime horaComienzo , LocalTime horaFin) {
		
		Duration duracion1 = Duration.between(horaComienzo , horaFin);
		
		if(duracion1 == null || duracion1.toMinutes()<15) {
			throw  new IllegalArgumentException("la tutoria debe durar al menos 15 minutos");
		}
	}
	private void checkDuration2() {
		if(duracion()==null  || duracion()<15) {
			
			throw  new IllegalArgumentException("la tutoria debe durar al menos 15 minutos");
		}
	}
	
	@Override
	public String toString() {	//"X 10:30-12:30"

		return getDia() + " " + horaComienzo + "-" + horaFin;
	}

	private String getDia() {
		
		DayOfWeek day = dia;
		
		return switch(day) {
		
		 case SATURDAY -> "S";
		 case SUNDAY -> "D";
	     case MONDAY -> "L";
	     case TUESDAY -> "M";
	     case WEDNESDAY ->"X";
	     case THURSDAY -> "J";
	     case FRIDAY -> "V";
	     };
	}


	
	
	// igualdad y orden -------------------------------------
	
	@Override
	public int hashCode() {
		return Objects.hash(dia, horaComienzo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tutoria other = (Tutoria) obj;
		return dia == other.dia && Objects.equals(horaComienzo, other.horaComienzo);
	}	
	
	
	public int compareTo(Tutoria o) {
		int res;
		
		res=dia.compareTo(o.dia);
		
		if(res==0) {
			res=horaComienzo.compareTo(o.horaComienzo);
		}
		return res;
	}
	
	
	
	
}
