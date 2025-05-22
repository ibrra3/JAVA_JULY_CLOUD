package fp.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import fp.tipos.*;
public class TestPart2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


	        //====================================================================================
	        // INICIO DE PRUEBAS ESPECÍFICAS DE PARTE 2
	        //====================================================================================
	        System.out.println("\n\n==== INICIO PRUEBAS PARTE 2 ====");

	        // --- Pruebas para Asignatura (Restricciones, Igualdad, Orden) ---
	        System.out.println("\n--- Pruebas Asignatura (Parte 2) ---");
	        testAsignaturaParte2();

	        // --- Pruebas para Persona (Restricciones, Igualdad, Orden) ---
	        System.out.println("\n--- Pruebas Persona (Parte 2) ---");
	        testPersonaParte2();

	        // --- Pruebas para Espacio (Restricciones, Igualdad, Orden) ---
	        // System.out.println("\n--- Pruebas Espacio (Parte 2) ---");
	        // testEspacioParte2(); // Assuming Espacio and TipoEspacio are defined

	        // --- Pruebas para Nota (Restricciones, Igualdad, Orden) ---
	        System.out.println("\n--- Pruebas Nota (Parte 2) ---");
	        testNotaParte2();

	        // --- Pruebas para Tutoria (Restricciones, Igualdad, Orden) ---
	        System.out.println("\n--- Pruebas Tutoria (Parte 2) ---");
	        testTutoriaParte2();

	        // --- Pruebas para Nuevos Tipos Contenedores (Centro, Expediente, Grado) ---
	        System.out.println("\n--- Pruebas Tipos Contenedores (Parte 2) ---");
	        // testCentro();   // Assuming Centro is defined
	        // testExpediente(); // Assuming Expediente is defined
	        // testGrado();      // Assuming Grado is defined


	        System.out.println("\n\n==== FIN PRUEBAS PARTE 2 ====");
	        //====================================================================================
	        // FIN DE PRUEBAS ESPECÍFICAS DE PARTE 2
	        //====================================================================================

	    } // END OF main method

	    //---------------------------------------------------------------------
	    // Métodos de prueba para Parte 2 (deben estar dentro de la clase main)
	    //---------------------------------------------------------------------

	    public static void testAsignaturaParte2() {
	        System.out.println("  --- Test Restricciones Asignatura ---");
	        // Código numérico (7 dígitos), créditos (>0), curso (1-4)
	        try {
	            new Asignatura("Mal Código Corto", "12345", 6.0, tipoAsignatura.ANUAL, 1);
	            System.err.println("ERROR Asignatura: Código corto permitido.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Asignatura: Restricción código corto: " + e.getMessage());
	        }
	        try {
	            new Asignatura("Mal Código Largo", "12345678", 6.0, tipoAsignatura.ANUAL, 1);
	            System.err.println("ERROR Asignatura: Código largo permitido.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Asignatura: Restricción código largo: " + e.getMessage());
	        }
	        try {
	            new Asignatura("Mal Código No Num", "123456A", 6.0, tipoAsignatura.ANUAL, 1);
	            System.err.println("ERROR Asignatura: Código no numérico permitido.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Asignatura: Restricción código no numérico: " + e.getMessage());
	        }
	        try {
	            new Asignatura("Créditos Cero", "7654321", 0.0, tipoAsignatura.ANUAL, 1);
	            System.err.println("ERROR Asignatura: Créditos cero permitidos.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Asignatura: Restricción créditos cero: " + e.getMessage());
	        }
	        try {
	            new Asignatura("Créditos Negativos", "7654321", -1.0, tipoAsignatura.ANUAL, 1);
	            System.err.println("ERROR Asignatura: Créditos negativos permitidos.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Asignatura: Restricción créditos negativos: " + e.getMessage());
	        }
	        try {
	            new Asignatura("Curso Cero", "7654321", 6.0, tipoAsignatura.ANUAL, 0);
	            System.err.println("ERROR Asignatura: Curso cero permitido.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Asignatura: Restricción curso cero: " + e.getMessage());
	        }
	        try {
	            new Asignatura("Curso Alto", "7654321", 6.0, tipoAsignatura.ANUAL, 5);
	            System.err.println("ERROR Asignatura: Curso > 4 permitido.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Asignatura: Restricción curso alto: " + e.getMessage());
	        }
	        Asignatura asigValida = new Asignatura("Válida", "1111111", 6.0, tipoAsignatura.ANUAL, 1);
	        System.out.println("Asignatura válida creada: " + asigValida);


	        System.out.println("\n  --- Test Igualdad y Orden Asignatura ---");
	        // Igualdad: mismo código. Orden: por código.
	        Asignatura a1 = new Asignatura("FP", "0000230", 6.0, tipoAsignatura.ANUAL, 1);
	        Asignatura a2 = new Asignatura("Fundamentos Prog", "0000230", 12.0, tipoAsignatura.ANUAL, 1); // Mismo código
	        Asignatura a3 = new Asignatura("ED", "0000231", 6.0, tipoAsignatura.PRIMERCUATRI, 1); // Diferente código
	        Asignatura a4 = new Asignatura("Cálculo", "0000110", 9.0, tipoAsignatura.ANUAL, 1); // Código menor

	        System.out.println("a1: " + a1);
	        System.out.println("a2: " + a2);
	        System.out.println("a3: " + a3);
	        System.out.println("a4: " + a4);

	        System.out.println("a1.equals(a2)? (Mismo código): " + a1.equals(a2) + " (Esperado: true)");
	        System.out.println("a1.hashCode() == a2.hashCode()? " + (a1.hashCode() == a2.hashCode()) + " (Esperado: true)");
	        System.out.println("a1.equals(a3)? (Dif código): " + a1.equals(a3) + " (Esperado: false)");
	        System.out.println("a1.equals(null)? : " + a1.equals(null) + " (Esperado: false)");
	        System.out.println("a1.equals(\"texto\")? : " + a1.equals("texto") + " (Esperado: false)");


	        System.out.println("a1.compareTo(a2) (Mismo código): " + a1.compareTo(a2) + " (Esperado: 0)");
	        System.out.println("a1.compareTo(a3) ('0000230' vs '0000231'): " + a1.compareTo(a3) + " (Esperado: negativo)");
	        System.out.println("a3.compareTo(a1) ('0000231' vs '0000230'): " + a3.compareTo(a1) + " (Esperado: positivo)");
	        System.out.println("a1.compareTo(a4) ('0000230' vs '0000110'): " + a1.compareTo(a4) + " (Esperado: positivo)");
	    }


	    public static void testPersonaParte2() {
	        System.out.println("  --- Test Restricciones Persona ---");
	        // DNI (8 dígitos + letra), email (contiene '@' o vacío)
	        // Las propiedades de Persona son modificables, por lo que también hay que probar setters si existen.
	        // Asumimos que las restricciones se comprueban en constructor y setters.

	        try {
	            new Persona("Nombre", "1234567", "Apellidos", LocalDate.now(), "email@valido.com"); // DNI corto
	            System.err.println("ERROR Persona: DNI corto permitido.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Persona: Restricción DNI corto: " + e.getMessage());
	        }
	        try {
	            new Persona("Nombre", "123456789A", "Apellidos", LocalDate.now(), "email@valido.com"); // DNI largo
	            System.err.println("ERROR Persona: DNI largo permitido.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Persona: Restricción DNI largo: " + e.getMessage());
	        }
	        try {
	            new Persona("Nombre", "1234567A", "Apellidos", LocalDate.now(), "email@valido.com"); // DNI letra mal
	            System.err.println("ERROR Persona: DNI letra mal permitido.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Persona: Restricción DNI letra mal: " + e.getMessage());
	        }
	        try {
	            new Persona("Nombre", "ABCDEFGHZ", "Apellidos", LocalDate.now(), "email@valido.com"); // DNI no dígitos
	            System.err.println("ERROR Persona: DNI no dígitos permitido.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Persona: Restricción DNI no dígitos: " + e.getMessage());
	        }

	        // Email: o contiene '@' o es cadena vacía
	        Persona pEmail = new Persona("Test Email", "11223344A", "Probando", LocalDate.now(), "test@example.com");
	        System.out.println("Persona con email válido creada: " + pEmail.getCorreo());
	        Persona pEmailVacio = new Persona("Test Email Vacío", "22334455B", "Probando Vacío", LocalDate.now(), "");
	        System.out.println("Persona con email vacío creada: '" + pEmailVacio.getCorreo() + "'");

	        try {
	            new Persona("Mail Malo", "33445566C", "Sin Arroba", LocalDate.now(), "emailinvalido.com");
	            System.err.println("ERROR Persona: Email sin '@' (y no vacío) permitido en constructor.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Persona: Restricción email sin '@' en constructor: " + e.getMessage());
	        }
	        
	        // Prueba setCorreo (si existe y es público/protegido y lo usas)
	        // Si setCorreo no es parte de la interfaz pública directa y solo se usa en Alumno/Profesor,
	        // esas pruebas de setEmail ya están/estarán en AlumnoTest/ProfesorTest.
	        // Para Persona genérica, la restricción es más laxa.
	        // Alumno/Profesor imponen reglas más estrictas.
	        // La prueba de setEmail aquí sería para la regla de Persona: "debe contener '@' o ser cadena vacía"

	        System.out.println("\n  --- Test Igualdad y Orden Persona ---");
	        // Igualdad: mismo DNI, nombre y apellidos. Orden: apellidos, nombre, DNI.
	        Persona p1 = new Persona("Ana", "12345678A", "Garcia Perez", LocalDate.of(1990, 1, 1), "ana@example.com");
	        Persona p2 = new Persona("Ana", "12345678A", "Garcia Perez", LocalDate.of(1992, 5, 5), "ana.garcia@example.com"); // Mismos datos clave
	        Persona p3 = new Persona("Juan", "12345678A", "Garcia Perez", LocalDate.of(1990, 1, 1), "juan@example.com"); // Mismo DNI, Apellido, dif Nombre
	        Persona p4 = new Persona("Ana", "87654321B", "Garcia Perez", LocalDate.of(1990, 1, 1), "ana.otro@example.com"); // Mismo Nombre, Apellido, dif DNI
	        Persona p5 = new Persona("Ana", "12345678A", "Lopez Martin", LocalDate.of(1990, 1, 1), "ana.lopez@example.com"); // Mismo DNI, Nombre, dif Apellido
	        Persona p6 = new Persona("Luis", "00000000Z", "Abad Garcia", LocalDate.of(1980,1,1), ""); // Apellido menor

	        System.out.println("p1: " + p1);
	        System.out.println("p2: " + p2); // Debería ser igual a p1
	        System.out.println("p3: " + p3); // No igual a p1 (nombre)
	        System.out.println("p4: " + p4); // No igual a p1 (DNI)
	        System.out.println("p5: " + p5); // No igual a p1 (apellido)
	        System.out.println("p6: " + p6); // No igual a p1

	        System.out.println("p1.equals(p2)? (Mismos datos clave): " + p1.equals(p2) + " (Esperado: true)");
	        System.out.println("p1.hashCode() == p2.hashCode()? " + (p1.hashCode() == p2.hashCode()) + " (Esperado: true)");
	        System.out.println("p1.equals(p3)? (Dif nombre): " + p1.equals(p3) + " (Esperado: false)");
	        System.out.println("p1.equals(p4)? (Dif DNI): " + p1.equals(p4) + " (Esperado: false)");
	        System.out.println("p1.equals(p5)? (Dif apellido): " + p1.equals(p5) + " (Esperado: false)");

	        System.out.println("Comparaciones (Apellido, Nombre, DNI):");
	        System.out.println("  p1 vs p3 (Garcia Perez, Ana vs Garcia Perez, Juan): " + p1.compareTo(p3) + " (Esperado: negativo, Ana < Juan)");
	        System.out.println("  p1 vs p4 (Garcia Perez, Ana, 123...A vs Garcia Perez, Ana, 876...B): " + p1.compareTo(p4) + " (Esperado: negativo, DNI 1... < DNI 8...)");
	        System.out.println("  p1 vs p5 (Garcia Perez vs Lopez Martin): " + p1.compareTo(p5) + " (Esperado: negativo, Garcia < Lopez)");
	        System.out.println("  p5 vs p1 (Lopez Martin vs Garcia Perez): " + p5.compareTo(p1) + " (Esperado: positivo, Lopez > Garcia)");
	        System.out.println("  p1 vs p6 (Garcia Perez vs Abad Garcia): " + p1.compareTo(p6) + " (Esperado: positivo, Garcia > Abad)");
	    }

	    // Necesitaríamos las definiciones de Espacio y TipoEspacio para implementar testEspacioParte2()
	    
	    public static void testEspacioParte2() {
	        System.out.println("  --- Test Restricciones Espacio ---");
	        // Capacidad > 0. Planta no modificable (no hay setter para probar)
	        try {
	            new Espacio("A1.1",0,tipoEspacio.TEORIA); // Capacidad cero
	            System.err.println("ERROR Espacio: Capacidad cero permitida.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Espacio: Restricción capacidad cero: " + e.getMessage());
	        }
	        Espacio espValido = new Espacio("L0.1",20,tipoEspacio.LAB);
	        System.out.println("Espacio válido creado: " + espValido);
	        // Probar setCapacidad (si existe y es parte de la interfaz pública)
	         try {
	             espValido.setCapacidad(0);
	             System.err.println("ERROR Espacio: setCapacidad(0) permitido.");
	         } catch (IllegalArgumentException e) {
	             System.out.println("ÉXITO Espacio: Restricción setCapacidad(0): " + e.getMessage());
	         }

	        System.out.println("\n  --- Test Igualdad y Orden Espacio ---");
	        // Igualdad: nombre y planta. Orden: planta, nombre.
	        Espacio e1 = new Espacio( "A1.1", 50,tipoEspacio.TEORIA);
	        Espacio e2 = new Espacio( "A1.1", 30,tipoEspacio.LAB); // Mismo nombre y planta
	        Espacio e3 = new Espacio( "A1.2", 50,tipoEspacio.TEORIA); // Diferente nombre, misma planta
	        Espacio e4 = new Espacio( "A1.1", 50,tipoEspacio.TEORIA); // Mismo nombre, diferente planta
	        Espacio e5 = new Espacio("S0.5", 25,tipoEspacio.SEMINARIO ); // Planta menor

	        System.out.println("e1.equals(e2)? (Mismo nombre, planta): " + e1.equals(e2) + " (Esperado: true)");
	        System.out.println("e1.hashCode() == e2.hashCode()? " + (e1.hashCode() == e2.hashCode()) + " (Esperado: true)");
	        System.out.println("e1.equals(e3)? (Dif nombre): " + e1.equals(e3) + " (Esperado: false)");
	        System.out.println("e1.equals(e4)? (Dif planta): " + e1.equals(e4) + " (Esperado: false)");
	        
	        System.out.println("Comparaciones (Planta, Nombre):");
	        System.out.println("  e1 vs e3 (Planta 1, A1.1 vs A1.2): " + e1.compareTo(e3) + " (Esperado: negativo, A1.1 < A1.2)");
	        System.out.println("  e1 vs e4 (Planta 1 vs Planta 2): " + e1.compareTo(e4) + " (Esperado: negativo, Planta 1 < Planta 2)");
	        System.out.println("  e1 vs e5 (Planta 1 vs Planta 0): " + e1.compareTo(e5) + " (Esperado: positivo, Planta 1 > Planta 0)");
	    }
	    

	    public static void testNotaParte2() {
	        System.out.println("  --- Test Restricciones Nota ---");
	        // Valor numérico (0-10). Mención honor solo si valor >= 9.
	        // Nota es inmutable, así que solo probamos constructores.
	        Asignatura fp = new Asignatura("FP", "1234567", 6.0, tipoAsignatura.ANUAL, 1);
	        try {
	            new Nota(fp, 2023, -0.1, convocatoria.PRIMERA, false); // Valor negativo
	            System.err.println("ERROR Nota: Valor negativo permitido.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Nota: Restricción valor negativo: " + e.getMessage());
	        }
	        try {
	            new Nota(fp, 2023, 10.1, convocatoria.PRIMERA, false); // Valor > 10
	            System.err.println("ERROR Nota: Valor > 10 permitido.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Nota: Restricción valor > 10: " + e.getMessage());
	        }
	        try {
	            new Nota(fp, 2023, 8.9, convocatoria.PRIMERA, true); // Mención con nota < 9
	            System.err.println("ERROR Nota: Mención con nota < 9 permitida.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Nota: Restricción mención con nota < 9: " + e.getMessage());
	        }
	        Nota notaValidaMH = new Nota(fp, 2023, 9.5, convocatoria.SEGUNDA, true);
	        System.out.println("Nota válida con MH creada: " + notaValidaMH + " (Calificación: " + notaValidaMH.getCalificacion() + ")");
	        Nota notaValidaSinMH = new Nota(fp, 2023, 9.0, convocatoria.TERCERA, false);
	        System.out.println("Nota válida sin MH creada: " + notaValidaSinMH + " (Calificación: " + notaValidaSinMH.getCalificacion() + ")");
	        Nota notaAprob = new Nota(fp, 2023, 5.0, convocatoria.PRIMERA, false);
	        System.out.println("Nota aprobada creada: " + notaAprob + " (Calificación: " + notaAprob.getCalificacion() + ")");


	        System.out.println("\n  --- Test Igualdad y Orden Nota ---");
	        // Igualdad: curso académico, asignatura, convocatoria. Orden: igual.
	        Asignatura ed = new Asignatura("ED", "7654321", 6.0, tipoAsignatura.ANUAL, 1);
	        Nota n1 = new Nota(fp, 2023, 7.0, convocatoria.PRIMERA, false);
	        Nota n2 = new Nota(fp, 2023, 8.0, convocatoria.PRIMERA, false); // Mismos curso, asig, conv
	        Nota n3 = new Nota(ed, 2023, 7.0, convocatoria.PRIMERA, false); // Dif asig
	        Nota n4 = new Nota(fp, 2022, 7.0, convocatoria.PRIMERA, false); // Dif curso acad
	        Nota n5 = new Nota(fp, 2023, 7.0, convocatoria.SEGUNDA, false); // Dif conv

	        System.out.println("n1.equals(n2)? (Mismos curso, asig, conv): " + n1.equals(n2) + " (Esperado: true)");
	        System.out.println("n1.hashCode() == n2.hashCode()? " + (n1.hashCode() == n2.hashCode()) + " (Esperado: true)");
	        System.out.println("n1.equals(n3)? (Dif asig): " + n1.equals(n3) + " (Esperado: false)");
	        System.out.println("n1.equals(n4)? (Dif curso acad): " + n1.equals(n4) + " (Esperado: false)");
	        System.out.println("n1.equals(n5)? (Dif conv): " + n1.equals(n5) + " (Esperado: false)");

	        System.out.println("Comparaciones (Curso Acad, Asignatura, Convocatoria):");
	        // Orden: Curso Académico, Asignatura, Convocatoria
	        System.out.println("  n1 vs n4 (2023 vs 2022): " + n1.compareTo(n4) + " (Esperado: positivo)"); // 2023 > 2022
	        System.out.println("  n1 vs n3 (FP vs ED, mismo curso): " + n1.compareTo(n3) + " (Esperado: negativo, si FP<ED por código)"); // Depende del orden de Asignatura
	        System.out.println("  n1 vs n5 (PRIMERA vs SEGUNDA, mismo curso, asig): " + n1.compareTo(n5) + " (Esperado: negativo)"); // PRIMERA < SEGUNDA
	    }

	    public static void testTutoriaParte2() {
	        System.out.println("  --- Test Restricciones Tutoria ---");
	        // Día (L-V), duración (>=15 min). Inmutable.
	        try {
	            new Tutoria(DayOfWeek.SATURDAY, LocalTime.of(10,0), LocalTime.of(11,0)); // Día inválido
	            System.err.println("ERROR Tutoria: Día Sábado permitido.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Tutoria: Restricción día inválido: " + e.getMessage());
	        }
	        try {
	            new Tutoria(DayOfWeek.MONDAY, LocalTime.of(10,0), LocalTime.of(10,10)); // Duración < 15 min
	            System.err.println("ERROR Tutoria: Duración < 15 min permitida.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Tutoria: Restricción duración < 15 min: " + e.getMessage());
	        }
	        // Constructor con duración
	        try {
	            new Tutoria(DayOfWeek.TUESDAY, LocalTime.of(10,0), LocalTime.of(10,10)); // Duración < 15 min
	            System.err.println("ERROR Tutoria: Duración (param) < 15 min permitida.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO Tutoria: Restricción duración (param) < 15 min: " + e.getMessage());
	        }					
	        Tutoria tutValida = new Tutoria(DayOfWeek.WEDNESDAY, LocalTime.of(12,0),LocalTime.of(19, 0));
	        System.out.println("Tutoría válida creada: " + tutValida + " (Duración: " + tutValida.duracion() + " min)");

	        System.out.println("\n  --- Test Igualdad y Orden Tutoria ---");
	        
	        Tutoria tut1 = new Tutoria(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 0));    // L 10:00-11:00
	        Tutoria tut2 = new Tutoria(DayOfWeek.MONDAY, 60, LocalTime.of(10, 0));                     // L 10:00-11:00 (igual a tut1)
	        Tutoria tut3 = new Tutoria(DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(12, 0));    // L 11:00-12:00
	        Tutoria tut4 = new Tutoria(DayOfWeek.TUESDAY, LocalTime.of(10, 0), LocalTime.of(11, 0));   // M 10:00-11:00
	        Tutoria tut5 = new Tutoria(DayOfWeek.FRIDAY, LocalTime.of(9,0), LocalTime.of(9,45));      // V 09:00-09:45

	        System.out.println("tut1: " + tut1);
	        System.out.println("tut2: " + tut2 + " (creada con duración)");
	        System.out.println("tut3: " + tut3);
	        System.out.println("tut4: " + tut4);
	        System.out.println("tut5: " + tut5);

	        System.out.println("tut1.equals(tut2)? (Mismo día, hora comienzo): " + tut1.equals(tut2) + " (Esperado: true)");
	        System.out.println("  tut1.hashCode() == tut2.hashCode()? " + (tut1.hashCode() == tut2.hashCode()) + " (Esperado: true)");

	        System.out.println("tut1.equals(tut3)? (Mismo día, dif hora comienzo): " + tut1.equals(tut3) + " (Esperado: false)");
	        System.out.println("tut1.equals(tut4)? (Dif día): " + tut1.equals(tut4) + " (Esperado: false)");
	        System.out.println("tut1.equals(null)? : " + tut1.equals(null) + " (Esperado: false)");
	        System.out.println("tut1.equals(\"texto\")? : " + tut1.equals("texto") + " (Esperado: false)");


	        System.out.println("\n  Comparaciones (Orden: Día, luego Hora Comienzo):");
	        // tut1: Lunes 10:00
	        // tut3: Lunes 11:00
	        // tut4: Martes 10:00
	        // tut5: Viernes 09:00

	        System.out.println("  tut1.compareTo(tut3) (L 10h vs L 11h): " + tut1.compareTo(tut3) + " (Esperado: negativo)"); // L 10:00 < L 11:00
	        System.out.println("  tut3.compareTo(tut1) (L 11h vs L 10h): " + tut3.compareTo(tut1) + " (Esperado: positivo)"); // L 11:00 > L 10:00
	        System.out.println("  tut1.compareTo(tut2) (Iguales): " + tut1.compareTo(tut2) + " (Esperado: 0)");
	        System.out.println("  tut1.compareTo(tut4) (L 10h vs M 10h): " + tut1.compareTo(tut4) + " (Esperado: negativo)"); // Lunes < Martes
	        System.out.println("  tut4.compareTo(tut1) (M 10h vs L 10h): " + tut4.compareTo(tut1) + " (Esperado: positivo)"); // Martes > Lunes
	        System.out.println("  tut5.compareTo(tut1) (V 9h vs L 10h): " + tut5.compareTo(tut1) + " (Esperado: positivo)"); // Viernes > Lunes
            System.out.println("-____________________________________________________________________________________________________________");

	    }
	    //____________________________________________________________________________________________________________
	    //____________________________________________________________________________________________________________
	    
	     // Add this static method to your 'main' class

	        public static void testExpediente() {
	            System.out.println("  --- Test Clase Expediente ---");

	            // Necesitamos algunas Asignaturas y convocatorias para crear Notas
	            Asignatura expFP = new Asignatura("Fund. Prog.", "000101", 6.0, tipoAsignatura.PRIMERCUATRI, 1);
	            Asignatura expED = new Asignatura("Est. Datos", "000102", 6.0, tipoAsignatura.SEGUNDOCUATRI, 1);
	            Asignatura expCalc = new Asignatura("Calculo", "000103", 9.0, tipoAsignatura.ANUAL, 1);

	            // Asumimos constructores para Nota y su método equals/hashCode/nota()
	            // Nota(Asignatura asig, Integer cursoAcad, Double valor, convocatoria conv, Boolean mencion)
	            // Nota(Asignatura asig, Integer cursoAcad, Double valor, convocatoria conv) -> mencion false
	            Nota notaFP_7_5 = new Nota(expFP, 2022, 7.5, convocatoria.PRIMERA, false);
	            Nota notaFP_8_0_REPE = new Nota(expFP, 2022, 8.0, convocatoria.PRIMERA, false); // Misma asig, curso, conv -> reemplazará
	            Nota notaED_4_0 = new Nota(expED, 2022, 4.0, convocatoria.PRIMERA, false);
	            Nota notaCalc_9_5_MH = new Nota(expCalc, 2022, 9.5, convocatoria.SEGUNDA, true);
	            Nota notaCalc_6_0 = new Nota(expCalc, 2023, 6.0, convocatoria.PRIMERA, false);


	            // Test Constructor y estado inicial
	            System.out.println("    -- Test Constructor y Estado Inicial --");
	            Expediente ex1 = new Expediente();
	            System.out.println("Expediente recién creado: " + ex1); // toString() usa List.toString()
	            System.out.println("  Notas (inicial): " + ex1.getNotas() + " (Esperado: [])");
	            System.out.println("  Nota Media (inicial): " + ex1.getNotaMedia() + " (Esperado: 0.0)");

	            // Test nuevaNota
	            System.out.println("\n    -- Test nuevaNota --");
	            ex1.nuevaNota(notaFP_7_5);
	            System.out.println("Expediente después de añadir notaFP_7_5: " + ex1.getNotas());
	            // Esperado: [(000101) Fund. Prog., 2022-23, PRIMERA, 7.5, NOTABLE] (o similar formato de Nota)
	            System.out.println("  Tamaño: " + ex1.getNotas().size() + " (Esperado: 1)");

	            ex1.nuevaNota(notaED_4_0);
	            System.out.println("Expediente después de añadir notaED_4_0: " + ex1.getNotas());
	            System.out.println("  Tamaño: " + ex1.getNotas().size() + " (Esperado: 2)");

	            // Añadir nota que reemplaza (misma asignatura, convocatoria, curso académico)
	            System.out.println("Añadiendo notaFP_8_0_REPE (debería reemplazar notaFP_7_5)...");
	            ex1.nuevaNota(notaFP_8_0_REPE);
	            System.out.println("Expediente después de reemplazar notaFP: " + ex1.getNotas());
	            System.out.println("  Tamaño: " + ex1.getNotas().size() + " (Esperado: 2)");
	            // Verificar que la nota de FP es ahora la de 8.0
	            boolean fpNotaActualizada = false;
	            for (Nota n : ex1.getNotas()) {
	                if (n.asignatura().equals(expFP) && n.nota().equals(8.0)) {
	                    fpNotaActualizada = true;
	                    break;
	                }
	            }
	            System.out.println("  Nota de FP es 8.0? " + fpNotaActualizada + " (Esperado: true)");
	            
	            try {
	                ex1.nuevaNota(null);
	                System.err.println("ERROR Expediente-nuevaNota: Se permitió añadir nota null.");
	            } catch (NullPointerException e) {
	                System.out.println("ÉXITO Expediente-nuevaNota: Excepción al añadir nota null: " + e.getMessage());
	            }


	            // Test getNotaMedia
	            System.out.println("\n    -- Test getNotaMedia --");
	            Expediente exMedia = new Expediente();
	            System.out.println("Nota Media (vacío): " + exMedia.getNotaMedia() + " (Esperado: 0.0)");

	            exMedia.nuevaNota(notaED_4_0); // Nota: 4.0 (suspensa)
	            System.out.println("Notas en exMedia: " + exMedia.getNotas());
	            System.out.println("Nota Media (con 4.0): " + exMedia.getNotaMedia() + " (Esperado: 0.0, ya que 4.0 < 5)");

	            exMedia.nuevaNota(notaFP_7_5); // Nota: 7.5 (aprobada)
	            System.out.println("Notas en exMedia: " + exMedia.getNotas());
	            System.out.println("Nota Media (con 4.0, 7.5): " + exMedia.getNotaMedia() + " (Esperado: 7.5)");

	            exMedia.nuevaNota(notaCalc_9_5_MH); // Nota: 9.5 (aprobada)
	            System.out.println("Notas en exMedia: " + exMedia.getNotas());
	            System.out.println("Nota Media (con 4.0, 7.5, 9.5): " + exMedia.getNotaMedia() + " (Esperado: (7.5+9.5)/2 = 8.5)");
	            // Suponiendo que nota.nota() no es null para estas notas.
	            // Tu getNotaMedia tiene: if(nota.nota()==null) { return 0.0; }
	            // Esto significa que si *cualquier* nota en la lista tiene un valor numérico null,
	            // la media de *todo* el expediente se vuelve 0.0. Esto es un poco drástico.
	            // PARTE-02.md: "promedio de las calificaciones con valores numéricos iguales o superiores a 5"
	            // Podría ser que solo se ignoren las notas con valor null, en lugar de anular toda la media.
	            // Voy a asumir que tu implementación de getNotaMedia es la que quieres probar.

	            // Test equals y hashCode
	            System.out.println("\n    -- Test equals y hashCode Expediente --");
	            Expediente eqEx1 = new Expediente();
	            eqEx1.nuevaNota(notaFP_7_5);
	            eqEx1.nuevaNota(notaED_4_0);

	            Expediente eqEx2 = new Expediente();
	            eqEx2.nuevaNota(notaFP_7_5);
	            eqEx2.nuevaNota(notaED_4_0); // Mismas notas en mismo orden

	            Expediente eqEx3 = new Expediente();
	            eqEx3.nuevaNota(notaED_4_0); // Notas diferentes o en diferente orden
	            eqEx3.nuevaNota(notaFP_7_5);

	            Expediente eqEx4 = new Expediente(); // Vacío

	            System.out.println("eqEx1.equals(eqEx2)? (Mismas notas, mismo orden): " + eqEx1.equals(eqEx2) + " (Esperado: true)");
	            System.out.println("  eqEx1.hashCode() == eqEx2.hashCode()? " + (eqEx1.hashCode() == eqEx2.hashCode()) + " (Esperado: true)");
	            System.out.println("eqEx1.equals(eqEx3)? (Mismas notas, orden diferente): " + eqEx1.equals(eqEx3) + " (Esperado: false, List.equals es sensible al orden)");
	            System.out.println("eqEx1.equals(eqEx4)? (Uno con notas, otro vacío): " + eqEx1.equals(eqEx4) + " (Esperado: false)");

	            System.out.println("Fin Pruebas Expediente");
	            System.out.println("-____________________________________________________________________________________________________________");

	        }
	     //____________________________________________________________________________________________________________
	    //____________________________________________________________________________________________________________
	    	       
	         // Add this static method to your 'main' class
	            public static void testGrado() {
	                System.out.println("\n  --- Test Clase Grado ---");

	                // Asignaturas de prueba
	                Asignatura gFP = new Asignatura("Fundamentos Prog.", "G001", 6.0, tipoAsignatura.PRIMERCUATRI, 1);
	                Asignatura gED = new Asignatura("Estructura Datos", "G002", 6.0, tipoAsignatura.SEGUNDOCUATRI, 1);
	                Asignatura gCalc = new Asignatura("Cálculo", "G003", 6.0, tipoAsignatura.ANUAL, 1);
	                Asignatura gAlg = new Asignatura("Álgebra", "G004", 6.0, tipoAsignatura.ANUAL, 1);

	                Asignatura gOpt1 = new Asignatura("IA", "G101", 6.0, tipoAsignatura.OPTATIVA, 3);
	                Asignatura gOpt2 = new Asignatura("Redes", "G102", 6.0, tipoAsignatura.OPTATIVA, 3);
	                Asignatura gOpt3_difCred = new Asignatura("Compiladores", "G103", 4.5, tipoAsignatura.OPTATIVA, 4); // Creditos diferentes
	                Asignatura gOpt4_curso2 = new Asignatura("Inglés Técnico", "G104", 6.0, tipoAsignatura.OPTATIVA, 2);


	                Set<Asignatura> obligatoriasBase = Set.of(gFP, gED, gCalc);
	                Set<Asignatura> optativasBase = Set.of(gOpt1, gOpt2); // Ambas 6.0 creditos
	                Set<Asignatura> optativasMixCred = Set.of(gOpt1, gOpt3_difCred); // Creditos 6.0 y 4.5

	                // Test Constructor y restricciones
	                System.out.println("    -- Test Constructor y Restricciones Grado --");
	                try {
	                    Grado gValido = new Grado("Ingeniería Informática", obligatoriasBase, optativasBase, 6.0);
	                    System.out.println("Grado creado (válido): " + gValido);
	                    System.out.println("  Nombre: " + gValido.getNombre());
	                    System.out.println("  Obligatorias: " + gValido.getObligatorias().size());
	                    System.out.println("  Optativas: " + gValido.getOptativas().size());
	                    System.out.println("  Min. Créditos Optativas: " + gValido.getMinCreditosOptativas());
	                    System.out.println("  Total Créditos Grado: " + gValido.getTotalCreditos() + " (Esperado: 6+6+6 + 6 = 24.0)");
	                } catch (Exception e) {
	                    System.err.println("ERROR Grado: Creando válido: " + e.getMessage());
	                    e.printStackTrace();
	                }

	                // Restricción: Optativas con diferentes créditos
	                try {
	                    new Grado("Grado Malo Optativas", obligatoriasBase, optativasMixCred, 6.0);
	                    System.err.println("ERROR Grado: Optativas con diferentes créditos permitido.");
	                } catch (IllegalArgumentException e) {
	                    System.out.println("ÉXITO Grado: Restricción optativas con diferentes créditos: " + e.getMessage());
	                }

	                // Restricción: minCreditosOptativas < 0
	                try {
	                    new Grado("Grado Malo MinCred1", obligatoriasBase, optativasBase, -1.0);
	                    System.err.println("ERROR Grado: minCreditosOptativas < 0 permitido.");
	                } catch (IllegalArgumentException e) {
	                    System.out.println("ÉXITO Grado: Restricción minCreditosOptativas < 0: " + e.getMessage());
	                }

	                // Restricción: minCreditosOptativas > total de créditos optativos disponibles
	                // optativasBase tiene gOpt1 (6.0) y gOpt2 (6.0) = 12.0 créditos optativos en total
	                try {
	                    new Grado("Grado Malo MinCred2", obligatoriasBase, optativasBase, 13.0); // 13.0 > 12.0
	                    System.err.println("ERROR Grado: minCreditosOptativas > total optativas permitido.");
	                } catch (IllegalArgumentException e) {
	                    System.out.println("ÉXITO Grado: Restricción minCreditosOptativas > total optativas: " + e.getMessage());
	                }
	                
	                // Restricción: Nulls
	                try {
	                    new Grado(null, obligatoriasBase, optativasBase, 6.0);
	                    System.err.println("ERROR Grado: nombre null permitido.");
	                } catch (NullPointerException e) {
	                    System.out.println("ÉXITO Grado: Restricción nombre null: " + e.getMessage());
	                }


	                // Test Métodos getAsignaturas y getAsignatura
	                System.out.println("\n    -- Test Métodos Grado --");
	                Grado gTestMetodos = new Grado("Grado de Prueba",
	                    Set.of(gFP, gED, gAlg), // Obligatorias: gFP (c1), gED (c1), gAlg (c1)
	                    Set.of(gOpt1, gOpt4_curso2),    // Optativas: gOpt1 (c3), gOpt4_curso2 (c2)
	                    6.0);

	                System.out.println("Asignaturas de curso 1: " + gTestMetodos.getAsignaturas(1));
	                // Esperado: gFP, gED, gAlg
	                System.out.println("  Contiene gFP? " + gTestMetodos.getAsignaturas(1).contains(gFP) + " (Esperado: true)");
	                System.out.println("  No contiene gOpt1? " + !gTestMetodos.getAsignaturas(1).contains(gOpt1) + " (Esperado: true)");


	                System.out.println("Asignaturas de curso 2: " + gTestMetodos.getAsignaturas(2));
	                // Esperado: gOpt4_curso2
	                 System.out.println("  Contiene gOpt4_curso2? " + gTestMetodos.getAsignaturas(2).contains(gOpt4_curso2) + " (Esperado: true)");

	                System.out.println("Asignaturas de curso 3: " + gTestMetodos.getAsignaturas(3));
	                // Esperado: gOpt1
	                 System.out.println("  Contiene gOpt1? " + gTestMetodos.getAsignaturas(3).contains(gOpt1) + " (Esperado: true)");

	                System.out.println("Asignaturas de curso 4 (vacío): " + gTestMetodos.getAsignaturas(4)); // Esperado: []

	                System.out.println("getAsignatura('G001') (FP): " + gTestMetodos.getAsignatura("G001")); // Esperado: gFP
	                System.out.println("getAsignatura('G104') (Inglés Opt): " + gTestMetodos.getAsignatura("G104")); // Esperado: gOpt4_curso2
	                System.out.println("getAsignatura('XXXX') (No existe): " + gTestMetodos.getAsignatura("XXXX")); // Esperado: null


	                // Test equals, hashCode, compareTo
	                System.out.println("\n    -- Test equals, hashCode, compareTo Grado --");
	                Grado gA = new Grado("Ingeniería del Software", obligatoriasBase, optativasBase, 6.0);
	                Grado gB = new Grado("Ingeniería del Software", Set.of(gCalc), Set.of(gOpt1), 0.0); // Mismo nombre
	                Grado gC = new Grado("Ingeniería de Computadores", obligatoriasBase, optativasBase, 6.0);

	                System.out.println("gA.equals(gB)? (Mismo nombre): " + gA.equals(gB) + " (Esperado: true)");
	                System.out.println("  gA.hashCode() == gB.hashCode()? " + (gA.hashCode() == gB.hashCode()) + " (Esperado: true)");
	                System.out.println("gA.equals(gC)? (Dif nombre): " + gA.equals(gC) + " (Esperado: false)");

	                System.out.println("gA.compareTo(gB) (Mismo nombre): " + gA.compareTo(gB) + " (Esperado: 0)");
	                System.out.println("gA.compareTo(gC) (Software vs Computadores): " + gA.compareTo(gC) + " (Esperado: positivo, 'Software' > 'Computadores')");
	                System.out.println("gC.compareTo(gA) (Computadores vs Software): " + gC.compareTo(gA) + " (Esperado: negativo, 'Computadores' < 'Software')");

	                System.out.println("Fin Pruebas Grado");
	            
	    
	    }
}
	    // Aquí irían los métodos de prueba para Centro, Expediente, Grado
	    // public static void testCentro() { /* ... */ }
	    // public static void testExpediente() { /* ... */ }
		//public static void testGrado() { /* ... */ }


