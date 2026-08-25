package com.sxzo.gestionlibros;
import com.sxzo.gestionlibros.model.Libro;
import java.util.HashMap;
import java.util.Map;
public class ServicioLibro {
 
    private static Map<String, Libro> libros = new HashMap<>();
    
    public static Map<String, Libro> getLibro() {
        return Map.copyOf(libros);
    }
    
    public static void addLibro(Libro lib) {
        libros.put(lib.getIsbn(), lib);
    }

    public static Libro buscarLibroPorIsbn(String isbn) {
        return libros.get(isbn);
    }
    
    
    /**
     * Elimina un libro del mapa por su ISBN.
     * 
     * @param isbn ISBN del libro a eliminar
     * @return true si el libro existía y fue eliminado, false en caso contrario
     */
    public static boolean eliminarLibro(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return false;
        }
        return libros.remove(isbn.trim()) != null;
    }
    
    
  
}