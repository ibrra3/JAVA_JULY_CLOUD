package fp.tipos;

import fp.utiles.Checkers; // Assuming you have this from your professor
import java.util.Set;
import java.util.HashSet;
// import java.util.Objects; // Replaced by Checkers for null checks

// Assuming Espacio, Profesor, and tipoEspacio are in fp.tipos

public class Despachos extends Espacios {

    private Set<Profesor> profesores;

    // Helper method for checking capacity constraint
    private void checkCapacidadProfesores(Integer capacidadDespacho, Set<Profesor> profesoresAAsignar) {
        Checkers.checkNoNull(capacidadDespacho, profesoresAAsignar);
        Checkers.check("El número de profesores (" + profesoresAAsignar.size() +
                       ") no puede superar la capacidad del despacho (" + capacidadDespacho + ").",
                       profesoresAAsignar.size() <= capacidadDespacho);
    }

    // Constructor 1: Con nombre, capacidad y Set de Profesores
    // Assuming Espacio constructor is: Espacio(String nombre, Integer capacidad, Integer planta, tipoEspacio tipo)
    // If your Espacio does not take 'planta' but derives it, this constructor needs adjustment.
    // For now, I'll proceed assuming Espacio takes 'planta'. Let's assume 'planta' is also a param for Despacho.
    public Despacho(String nombre, Integer capacidad, Integer planta, Set<Profesor> profesores) {
        super(nombre, capacidad, planta, tipoEspacio.OTRO); // Llama al constructor de Espacio con tipo OTRO
                                                       // y passthrough de planta
        Checkers.checkNoNull(profesores);
        checkCapacidadProfesores(this.getCapacidad(), profesores); // this.getCapacidad() comes from Espacio
        this.profesores = new HashSet<>(profesores); // Crea una copia defensiva
    }

    // Constructor 2: Con nombre, capacidad y un solo Profesor
    public Despacho(String nombre, Integer capacidad, Integer planta, Profesor profesor) {
        super(nombre, capacidad, planta, tipoEspacio.OTRO);
        Checkers.checkNoNull(profesor);
        Set<Profesor> tempProfesores = new HashSet<>();
        tempProfesores.add(profesor);
        checkCapacidadProfesores(this.getCapacidad(), tempProfesores);
        this.profesores = tempProfesores; // No need for new HashSet<>(tempProfesores) as it's already a new set
    }

    // Constructor 3: Con nombre y capacidad (sin profesores inicialmente)
    public Despacho(String nombre, Integer capacidad, Integer planta) {
        super(nombre, capacidad, planta, tipoEspacio.OTRO);
        this.profesores = new HashSet<>(); // Inicialmente vacío
        // No need for checkCapacidadProfesores here as 0 <= capacidad is always true if capacidad >=0
    }


    // Getters y Setters para profesores
    public Set<Profesor> getProfesores() {
        return new HashSet<>(this.profesores); // Devuelve copia defensiva
    }

    public void setProfesores(Set<Profesor> profesores) {
        Checkers.checkNoNull(profesores);
        checkCapacidadProfesores(this.getCapacidad(), profesores);
        this.profesores = new HashSet<>(profesores); // Asigna una copia defensiva
    }

    // Operaciones para añadir y quitar profesores (opcional, pero útil)
    public void addProfesor(Profesor profesor) {
        Checkers.checkNoNull(profesor);
        Set<Profesor> futurosProfesores = new HashSet<>(this.profesores);
        futurosProfesores.add(profesor);
        checkCapacidadProfesores(this.getCapacidad(), futurosProfesores);
        this.profesores.add(profesor); // Add to the actual set
    }

    public void removeProfesor(Profesor profesor) {
        Checkers.checkNoNull(profesor);
        this.profesores.remove(profesor);
    }


    // Restricción: anular setTipo de Espacio
    @Override
    public void setTipo(tipoEspacio tipo) {
        throw new UnsupportedOperationException("El tipo de un Despacho no se puede cambiar y es siempre OTRO.");
    }


    // toString
    @Override
    public String toString() {
        // "misma representación que los espacios, seguida de los profesores que ocupan el despacho"
        // Ejemplo: "M2.25 (planta 2) [prof1.toString(), prof2.toString()]"
        return super.toString() + " " + this.profesores.toString();
    }

    // Equals y HashCode:
    // "Dos despachos son iguales si tienen el mismo nombre y están en la misma planta."
    // Esto es idéntico al criterio de igualdad de Espacio.
    // Si Espacio.equals y Espacio.hashCode están correctamente implementados
    // (y son final, o Despacho no añade campos que participen en su propia igualdad),
    // entonces Despacho puede heredar estos métodos.
    // El conjunto de profesores NO participa en la igualdad del Despacho.

    // CompareTo:
    // "su ordenación natural coincide con la de cualquier otro espacio."
    // Heredado de Espacio.
}------------------------------------------------------------------------------------------------------------------------



package fp.tipos;

import java.util.HashSet;
import java.util.Set;

// import fp.tipos.*; // This is a wildcard import, generally fine, but specific imports are often preferred.
// It implies you'll need:
// import fp.tipos.Espacio;
// import fp.tipos.Profesor;
// import fp.tipos.tipoEspacio; // Your enum
// import fp.utiles.Checkers; // If you intend to use it here

public class Despacho extends Espacio {

    private Set<Profesor> profesores; // Good

    /*
     * (Comments from PARTE-03.md about constructors - good for reference)
     */

    // Constructor 1
    public Despacho(String nombre, Integer capacidad, tipoEspacio tipo, Set<Profesor> profesores) {
        // super(nombre, capacidad, tipoEspacio.OTRO); // <<< POINT 1: Parameter 'tipo' is ignored.
                                                    //           The super() call should match Espacio's constructor.
                                                    //           Your Espacio constructor is: Espacio(String nombre, Integer capacidad, tipoEspacio tipo)
                                                    //           So, this should be: super(nombre, capacidad, tipoEspacio.OTRO);
                                                    //           The 'tipo' parameter to this Despacho constructor is unused and misleading.

        // Let's assume the corrected super() call and constructor signature:
        // public Despacho(String nombre, Integer capacidad, Set<Profesor> profesoresEntrada) {
        //    super(nombre, capacidad, tipoEspacio.OTRO);
        //    Checkers.checkNoNull(profesoresEntrada);
        //    this.checkDespacho(this.getCapacidad(), profesoresEntrada); // Pass capacity and the set
        //    this.profesores = new HashSet<>(profesoresEntrada); // Defensive copy
        // }
        // ---- Based on YOUR current constructor signature for Despacho: ----
        super(nombre, capacidad, tipoEspacio.OTRO); // This line correctly sets type to OTRO, IGNORING the 'tipo' parameter passed to this constructor.

        // 'checkDespacho()' called here refers to 'this.profesores', which is NULL at this point.
        // This will cause a NullPointerException inside checkDespacho.
        // this.profesores = new HashSet<Profesor>(); // This line initializes 'this.profesores' *after* checkDespacho might have tried to use it.
        
        // Corrected flow for this constructor signature:
        // 1. super()
        // 2. Checkers.checkNoNull(profesores); // the parameter 'profesores'
        // 3. this.profesores = new HashSet<>(profesores); // Assign the copy
        // 4. checkDespacho(); // Now 'this.profesores' is initialized.

        // <<< POINT 2: Logic for handling input 'profesores' and calling checkDespacho
        // You are passing a 'tipo' to this Despacho constructor which is then ignored.
        // The spec says: "mismos parámetros que el constructor del tipo Espacio *salvo el tipo*,
        // que se inicializará con el valor 'OTRO', junto con un conjunto de profesores."
        // This implies this constructor should be: Despacho(String nombre, Integer capacidad, Set<Profesor> profesores)

        // Based on your current signature and intent:
        Checkers.checkNoNull(profesores); // Check the input parameter
        this.profesores = new HashSet<>(profesores); // Assign with defensive copy FIRST
        checkDespacho(); // Now call the check on the initialized this.profesores
    }


    // Constructor 2
    public Despacho(String nombre, Integer capacidad, tipoEspacio tipo, Profesor profesor) {
        super(nombre, capacidad, tipoEspacio.OTRO); // <<< POINT 1: Same as above, 'tipo' param is ignored.
                                                    // Constructor should likely be: Despacho(String nombre, Integer capacidad, Profesor profesor)

        // <<< POINT 2: Logic for handling input 'profesor' and calling checkDespacho
        // Based on your current signature and intent:
        Checkers.checkNoNull(profesor); // Check the input parameter
        this.profesores = new HashSet<Profesor>();
        this.profesores.add(profesor);
        checkDespacho(); // Now call the check
    }


    // Constructor 3
    public Despacho(String nombre, Integer capacidad, tipoEspacio tipo) {
        super(nombre, capacidad, tipoEspacio.OTRO); // <<< POINT 1: Same as above, 'tipo' param is ignored.
                                                    // Constructor should likely be: Despacho(String nombre, Integer capacidad)

        // <<< POINT 2: checkDespacho will check against an empty 'this.profesores'
        this.profesores = new HashSet<Profesor>();
        checkDespacho(); // This is fine, as 0 professors will pass capacity check.
    }


    public Set<Profesor> getProfesores() {
        // return profesores; // <<< POINT 3: Should return a defensive copy
        return new HashSet<>(this.profesores); // Corrected for defensive copy
    }

    public void setProfesores(Set<Profesor> profesores) {
        // <<< POINT 4: Null check and defensive copy
        // fp.utiles.Checkers.checkNoNull(profesores);
        // this.profesores = new HashSet<>(profesores);
        // checkDespacho(); // Re-check capacity after setting

        // Corrected version:
        Checkers.checkNoNull(profesores);
        Set<Profesor> copiaProfesores = new HashSet<>(profesores); // Work with a copy for the check
        checkDespacho(this.getCapacidad(), copiaProfesores); // Pass capacity and the new set to check
        this.profesores = copiaProfesores; // Assign the validated copy
    }


    // Your checkDespacho method needs to be more flexible or renamed.
    // It currently checks this.profesores.size() against this.getCapacidad()
    // and this.getTipo(). This is fine for use *after* professors and type are set.
    private void checkDespacho() { // This version relies on internal state
        // This will cause NullPointerException if this.profesores is null when called.
        // It needs to be called AFTER this.profesores is initialized.
        if (this.profesores.size() > getCapacidad()) {
            throw new IllegalArgumentException("el numero de profesores no puede superar la capacidad del Despacho");
        }

        // This check should primarily be in setTipo or implicitly handled by super() in constructor
        // If super() always sets tipo to OTRO, this check in Despacho might be redundant
        // or could be a final assertion.
        // However, the spec requires setTipo() to be overridden to throw an exception.
        // if (getTipo() != tipoEspacio.OTRO) { // As super() always sets it to OTRO, this might always pass
        // throw new UnsupportedOperationException("el tipo de Despacho / Espacio debe ser 'OTRO' ");
        // }
        // The main constraint is that `setTipo` must be overridden.
    }

    // <<< POINT 5: Overload checkDespacho or use a different helper for constructors/setProfesores
    // This version is better for checking before assignment.
    private void checkDespacho(Integer capacidadActual, Set<Profesor> profesoresAComprobar) {
        Checkers.checkNoNull(capacidadActual, profesoresAComprobar);
        if (profesoresAComprobar.size() > capacidadActual) {
            throw new IllegalArgumentException("El número de profesores (" + profesoresAComprobar.size() +
                                           ") no puede superar la capacidad del Despacho (" + capacidadActual + ").");
        }
        // The check for getTipo() != tipoEspacio.OTRO is more about ensuring the Despacho *remains* OTRO.
        // The constructors already enforce this by calling super() with tipoEspacio.OTRO.
        // The overridden setTipo() method ensures it cannot be changed.
    }


    // <<< POINT 6: Missing Override for setTipo(tipoEspacio tipo)
    // The spec requires: "si se intenta invocar a la operación setTipo, heredada del tipo Espacio,
    // se debe elevar la excepción UnsupportedOperationException."
    @Override
    public void setTipo(tipoEspacio tipo) {
        throw new UnsupportedOperationException("El tipo de un Despacho no se puede cambiar y es siempre OTRO.");
    }


    //Dos despachos son iguales si tienen el mismo nombre y están en la misma planta.  (IGUAL QUE ESPACIO -> NO SE IMPLEMENTA ) // Correct
    //Además, su ordenación natural coincide con la de cualquier otro espacio.          (TAMPOCO SE IMPLEMENTA ) // Correct


    //un despacho tiene la misma representación que los espacios, seguida de los profesores que ocupan el despacho
    //(utilice la representación como cadena de la propiedad profesores).
    @Override
    public String toString() {
        // TODO Auto-generated method stub // <<< POINT 7: Remove TODO
        // return super.toString() + this.profesores.toString(); // Missing a space for readability
        return super.toString() + " " + this.profesores.toString(); // Added space
    }
}