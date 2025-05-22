package fp.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set; // Added for Alumno's getAsignaturasMatriculadas

import fp.tipos.*;

public class main {
	public static void main (String[] args) {

		Asignatura a2 = new Asignatura("Fundamentos de Programación", "0000230", 12.0, tipoAsignatura.ANUAL, 1);
		System.out.println(a2);
		System.out.println(a2.creditos());

		Asignatura a3 = new Asignatura("Fundamentos de Programación", "0000230", 12.0, tipoAsignatura.ANUAL, 1);
		System.out.println(a3);
		System.out.println(a3.creditos());

		//System.out.println("a1 y a2 son iguales? " + a.equals(a2));
		System.out.println("a2 y a3 son iguales? " + a2.equals(a3));

		Persona p = new Persona("Miguel", "12345678y", "TORO" ,LocalDate.of(2004, 10, 23), "prueba@");
		System.out.println(p);

		// Espacio e = new Espacio(TipoEspacio.LABORATORIO, "A3.10", 100);
		// System.out.println(e);
		// System.out.println(e.getPlanta());
		Nota n = new Nota(a2, 2014, 9.0, convocatoria.PRIMERA, true);
		System.out.println(n);
		

        Tutoria t = new Tutoria(DayOfWeek.MONDAY, LocalTime.of(17, 30), LocalTime.of(19, 0));
        System.out.println(t);
        System.out.println("Duración de tutoría t: " + t.duracion());
        System.out.println("==============================================================\n");

	
}
}