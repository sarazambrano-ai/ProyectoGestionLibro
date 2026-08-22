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
  
}