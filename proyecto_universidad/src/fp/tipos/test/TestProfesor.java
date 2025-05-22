package fp.tipos.test;

import fp.tipos.*;
import fp.utiles.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
public class TestProfesor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Profesor p1 = new Profesor("ibra", "70954560E", "abu",LocalDate.of(2002, 9, 5), "a@email", tipoProfesor.CATEDRATICO);
		
		System.out.println(p1.toString());
		System.out.println(p1.getFechaNac());
		System.out.println(" -106 on a Mission - TEKKERA ELECTRONIX- ");
		
	 System.out.println("\n--- Test Clase Profesor (Parte 3) ---");
	
	 // Sample data for Persona part of Profesor
	 String nombreP = "Juan";
	 String dniP = "11223344P";
	 String apellidosP = "Pérez Gómez";
	 String correoP = "juan.perez@example.com"; // Assuming Persona allows this general email
	
	 // Valid dates of birth
	 LocalDate dobValid = LocalDate.of(1980, 5, 15); // For a professor older than 18
	 LocalDate dobInvalidTooYoung = LocalDate.now().minusYears(17); // 17 years old
	 LocalDate dobInvalidExactly18 = LocalDate.now().minusYears(18); // Exactly 18 years old
	
	
	 System.out.println("    -- Test Constructor Profesor y Restricciones --");
	 // Test valid constructor
	 try {
	     Profesor profValido = new Profesor(nombreP, dniP, apellidosP, dobValid, correoP, tipoProfesor.TITULAR);
	     System.out.println("ÉXITO Profesor: Creado válido: " + profValido);
	     System.out.println("  Edad: " + profValido.getEdad() + " (Esperado: > 18)");
	     System.out.println("  Categoría: " + profValido.getTipo());
	     System.out.println("  Tutorías iniciales: " + profValido.getTutorias() + " (Esperado: [])");
	 } catch (Exception e) {
	     System.err.println("ERROR Profesor: Creando profesor válido: " + e.getMessage());
	     e.printStackTrace();
	 }
	
	 // Test constructor: Age constraint (too young)
	 // Your checkEdad() is: if(this.getEdad()<18) { throw ... "el profesor debe ser mayor que 18"}
	 // This means age 18 IS allowed. The message is a bit ambiguous if it means strictly >18 or >=18.
	 // PARTE-03.md says "mayor de edad (tener más de 18 años)". This typically means age > 18 (so 19+).
	 // If your check is <18, it means 17 is too young, 18 is okay.
	 // If the spec means "strictly older than 18", your check should be this.getEdad() <= 18.
	 // I will test based on YOUR `checkEdad()`: `< 18` throws.
	 try {
	     new Profesor("Joven", "22334455J", "Promesa", dobInvalidTooYoung, "joven@example.com", tipoProfesor.AYUDANTE);
	     System.err.println("ERROR Profesor: Se permitió crear profesor demasiado joven (17 años).");
	 } catch (IllegalArgumentException e) {
	     System.out.println("ÉXITO Profesor: Excepción por profesor demasiado joven (17 años): " + e.getMessage());
	 }
	
	 // Test constructor: Age constraint (exactly 18 - should be allowed by your current checkEdad())
	 try {
	     Profesor prof18 = new Profesor("Justo", "33445566K", "Dieciocho", dobInvalidExactly18, "justo@example.com", tipoProfesor.AYUDANTE);
	     System.out.println("ÉXITO Profesor: Creado profesor con 18 años (permitido por `getEdad()<18` check): " + prof18.getName() + ", Edad: " + prof18.getEdad());
	 } catch (IllegalArgumentException e) {
	     System.err.println("ERROR Profesor: No se permitió crear profesor con 18 años: " + e.getMessage());
	 }
	 
	 // Test constructor: Null tipoProfesor (should be caught by Checkers.checkNoNull if you added it to constructor)
	 // If not, this might pass and lead to NPE later.
	 try {
	     new Profesor(nombreP, "44556677L", apellidosP, dobValid, correoP, null);
	     System.err.println("ERROR Profesor: Se permitió crear profesor con tipo/categoría null.");
	 } catch (NullPointerException | IllegalArgumentException e) { // Depending on how null check is done (Checkers or Objects.requireNonNull)
	     System.out.println("ÉXITO Profesor: Excepción por tipo/categoría null: " + e.getMessage());
	 }
	
	
	 System.out.println("\n    -- Test setTipo (Categoría) Profesor --");
	 Profesor profSetTipo = new Profesor("Ana", "55667788M", "Lopez Diaz", dobValid, "ana.lopez@example.com", tipoProfesor.COLABORADOR);
	 System.out.println("Categoría inicial: " + profSetTipo.getTipo());
	 profSetTipo.setTipo(tipoProfesor.CATEDRATICO);
	 System.out.println("Categoría después de setTipo(CATEDRATICO): " + profSetTipo.getTipo() + " (Esperado: CATEDRATICO)");
	 try {
	     profSetTipo.setTipo(null);
	     System.err.println("ERROR Profesor: setTipo(null) permitido.");
	 } catch (NullPointerException | IllegalArgumentException e) { // Checkers.checkNoNull throws IAE, Objects.requireNonNull NPE
	     System.out.println("ÉXITO Profesor: Excepción por setTipo(null): " + e.getMessage());
	 }
	
	
	 System.out.println("\n    -- Test Gestión de Tutorías --");
	 Profesor profTutorias = new Profesor("Carlos", "66778899N", "Martin Sanz", dobValid, "carlos.martin@example.com", tipoProfesor.TITULAR);
	
	 // Test nuevaTutoria
	 System.out.println("Tutorías iniciales: " + profTutorias.getTutorias() + " (Esperado: [])");
	 profTutorias.nuevaTutoria(LocalTime.of(10, 0), 60, DayOfWeek.MONDAY); // L 10:00-11:00
	 profTutorias.nuevaTutoria(LocalTime.of(15, 30), 90, DayOfWeek.WEDNESDAY); // X 15:30-17:00
	 System.out.println("Tutorías después de añadir dos: " + profTutorias.getTutorias());
	 System.out.println("  Número de tutorías: " + profTutorias.getTutorias().size() + " (Esperado: 2)");
	
	 // Test nuevaTutoria con nulls (should be caught by Checkers in nuevaTutoria)
	 try {
	     profTutorias.nuevaTutoria(null, 60, DayOfWeek.FRIDAY);
	     System.err.println("ERROR Profesor-nuevaTutoria: horaComienzo null permitido.");
	 } catch (NullPointerException | IllegalArgumentException e) {
	     System.out.println("ÉXITO Profesor-nuevaTutoria: Excepción por horaComienzo null: " + e.getMessage());
	 }
	 // Your Tutoria constructor likely handles nulls for duracion and diaSemana if Checkers not used in nuevaTutoria directly.
	 // My `Profesor` skeleton added Checkers to `nuevaTutoria` for its direct params.
	
	
	 // Test borraTutoria
	 System.out.println("Borrando tutoría Lunes 10:00...");
	 profTutorias.borraTutoria(LocalTime.of(10, 0), DayOfWeek.MONDAY);
	 System.out.println("Tutorías después de borrar L 10:00: " + profTutorias.getTutorias());
	 System.out.println("  Número de tutorías: " + profTutorias.getTutorias().size() + " (Esperado: 1)");
	 
	 System.out.println("Intentando borrar tutoría no existente (Martes 10:00)...");
	 profTutorias.borraTutoria(LocalTime.of(10,0), DayOfWeek.TUESDAY); // No tiene efecto
	 System.out.println("Tutorías después de borrar inexistente: " + profTutorias.getTutorias());
	 System.out.println("  Número de tutorías: " + profTutorias.getTutorias().size() + " (Esperado: 1)");
	
	 // Test borraTutorias
	 System.out.println("Borrando todas las tutorías...");
	 profTutorias.borraTutorias();
	 System.out.println("Tutorías después de borraTutorias(): " + profTutorias.getTutorias() + " (Esperado: [])");
	 System.out.println("  Número de tutorías: " + profTutorias.getTutorias().size() + " (Esperado: 0)");
	 
	 // Test getTutorias - defensive copy (if implemented)
	 // If you implemented getTutorias to return a copy:
	 // SortedSet<Tutoria> copiaTutorias = profTutorias.getTutorias();
	 // copiaTutorias.add(new Tutoria(DayOfWeek.FRIDAY, 30, LocalTime.of(9,0))); // Add to copy
	 // System.out.println("Tutorías originales después de modificar copia: " + profTutorias.getTutorias() + " (Esperado: [])");
	 // System.out.println("Copia modificada: " + copiaTutorias);
	
	
	 System.out.println("\n    -- Test toString Profesor --");
	 Profesor profToString = new Profesor("Laura", "77889900P", "Vidal Luna", dobValid, "laura.vidal@example.com", tipoProfesor.CONTRATADO_DOCTOR);
	 System.out.println(profToString.toString());
	 // Esperado: 77889900P - Vidal Luna, Laura - YYYY-MM-DD (CONTRATADO_DOCTOR) (adapt YYYY-MM-DD to dobValid)
	
	 System.out.println("\n    -- Test equals y hashCode Profesor --");
	 // Igualdad se basa en DNI, nombre, apellidos (heredado de Persona)
	 Profesor profA = new Profesor("Miguel", "88990011M", "Nuñez Soto", dobValid, "miguel.nunez@example.com", tipoProfesor.TITULAR);
	 Profesor profB = new Profesor("Miguel", "88990011M", "Nuñez Soto", LocalDate.of(1975,1,1), "miguel.otro@example.com", tipoProfesor.CATEDRATICO); // Mismos datos clave para Persona.equals
	 Profesor profC = new Profesor("Diferente", "88990011M", "Nuñez Soto", dobValid, "diferente@example.com", tipoProfesor.TITULAR);
	
	 System.out.println("profA.equals(profB)? (Mismos datos clave Persona): " + profA.equals(profB) + " (Esperado: true)");
	 System.out.println("  profA.hashCode() == profB.hashCode()? " + (profA.hashCode() == profB.hashCode()) + " (Esperado: true si equals es true)");
	 System.out.println("profA.equals(profC)? (Diferente nombre): " + profA.equals(profC) + " (Esperado: false)");
	
	 // Test compareTo (heredado de Persona - orden por apellidos, nombre, DNI)
	 // System.out.println("\n    -- Test compareTo Profesor --");
	 // Profesor profComp1 = new Profesor("Garcia", "111A", "Ana", dobValid, "a@a.com", tipoProfesor.AYUDANTE);
	 // Profesor profComp2 = new Profesor("Garcia", "222B", "Pedro", dobValid, "p@p.com", tipoProfesor.AYUDANTE);
	 // System.out.println("profComp1.compareTo(profComp2) (Ana Garcia vs Pedro Garcia): " + profComp1.compareTo(profComp2) + " (Esperado: negativo)");
	
	 System.out.println("Fin Pruebas Profesor");
		}
	}
	