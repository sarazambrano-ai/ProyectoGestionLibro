/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sxzo.gestionlibros;

import com.sxzo.gestionlibros.model.Editorial;
import com.sxzo.gestionlibros.model.Libro;
import com.sxzo.gestionlibros.model.LibroAudio;
import com.sxzo.gestionlibros.model.LibroFisico;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sxzo.gestionlibros.Notificador;
import com.sxzo.gestionlibros.Notificador;

/**
 * Servicio (Singleton) que gestiona la información de los libros:
 * valida los datos recibidos, aplica las reglas de negocio y persiste
 * los objetos en memoria principal. Implementa IServicioLibro para
 * que el resto de la aplicación dependa del contrato, no de esta
 * implementación concreta.
 *
 * @author misae
 */
public class ServicioLibro {

    private static ServicioLibro instancia;
    
    private static Notificador notificador;

    private final Map<String, Libro> libros = new HashMap<>();
    
    private ArrayList<Editorial> editoriales = new ArrayList<>();
    

    /**
     * Constructor privado: nadie fuera de esta clase puede hacer
     * "new ServicioLibro()", garantizando que solo exista una instancia.
     */
    private ServicioLibro() 
    {
        notificador = new Notificador( "modificar_libroFisico", "modificar_Audiolibro");
    }

    /**
     * Punto único de acceso a la instancia del Singleton. La crea la
     * primera vez que se pide, y reutiliza la misma en adelante.
     */
    public static synchronized ServicioLibro getInstance() {
        if (instancia == null) {
            instancia = new ServicioLibro();
        }
        return instancia;
    }
    
    public static Notificador getNotificador()
    {
        return notificador;
    }
    

    
    public LibroFisico agregarLibroFisico(String isbn, String titulo, String autor,
            String strPrecio, LocalDate fechaImpresion, String tipoTapa,
            Editorial editorial, String strAnioFundacion) throws Exception {

        if (isbn == null || isbn.trim().isEmpty()) {
            throw new Exception("El ISBN es obligatorio.");
        }
        if (buscarLibroPorIsbn(isbn.trim()) != null) {
            throw new Exception("Ya existe un libro registrado con ese ISBN. Usa uno diferente.");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("El título es obligatorio.");
        }
        if (autor == null || autor.trim().isEmpty()) {
            throw new Exception("El autor es obligatorio.");
        }

        double precio;
        try {
            precio = Double.parseDouble(strPrecio.trim());
        } catch (NumberFormatException e) {
            throw new Exception("El precio debe ser un número válido, ejemplo: 25000");
        }

        int anioFundacion;
        try {
            anioFundacion = Integer.parseInt(strAnioFundacion.trim());
        } catch (NumberFormatException e) {
            throw new Exception("El año de fundación debe ser un número entero, ejemplo: 1998");
        }

        if (editorial.getNombre() == null || editorial.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre de la editorial es obligatorio.");
        }

        LibroFisico lib = new LibroFisico(isbn.trim(), titulo.trim(), autor.trim(),
                precio, fechaImpresion, tipoTapa, editorial);

        libros.put(lib.getIsbn(), lib);
        
        notificador.notificarObservadores("modificar_libroFisico");
        
        return lib;
    }

   
    public LibroAudio agregarLibroAudio(String isbn, String titulo, String autor,
            String strPrecio, String strDuracionMinutos, String narrador) throws Exception {

        if (isbn == null || isbn.trim().isEmpty()) {
            throw new Exception("El ISBN es obligatorio.");
        }
        if (buscarLibroPorIsbn(isbn.trim()) != null) {
            throw new Exception("Ya existe un libro registrado con ese ISBN. Usa uno diferente.");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("El título es obligatorio.");
        }
        if (autor == null || autor.trim().isEmpty()) {
            throw new Exception("El autor es obligatorio.");
        }

        double precio;
        try {
            precio = Double.parseDouble(strPrecio.trim());
        } catch (NumberFormatException e) {
            throw new Exception("El precio debe ser un número válido, ejemplo: 25000");
        }

        int duracionMinutos;
        try {
            duracionMinutos = Integer.parseInt(strDuracionMinutos.trim());
        } catch (NumberFormatException e) {
            throw new Exception("La duración debe ser un número entero de minutos, ejemplo: 120");
        }

        if (narrador == null || narrador.trim().isEmpty()) {
            throw new Exception("El narrador es obligatorio.");
        }

        LibroAudio lib = new LibroAudio(isbn.trim(), titulo.trim(), autor.trim(),
                precio, duracionMinutos, narrador.trim());

        libros.put(lib.getIsbn(), lib);
        
        notificador.notificarObservadores("modificar_Audiolibro");
        
        return lib;
    }

    
    public List<LibroFisico> listarLibrosFisicos() {
        List<LibroFisico> resultado = new ArrayList<>();
        for (Libro lib : libros.values()) {
            if (lib instanceof LibroFisico) {
                resultado.add((LibroFisico) lib);
            }
        }
        return resultado;
    }

    
    public List<LibroAudio> listarLibrosAudio() {
        List<LibroAudio> resultado = new ArrayList<>();
        for (Libro lib : libros.values()) {
            if (lib instanceof LibroAudio) {
                resultado.add((LibroAudio) lib);
            }
        }
        return resultado;
    }

    
    public LibroFisico buscarLibroFisico(String isbn) throws Exception {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new Exception("Debes ingresar un ISBN para buscar.");
        }

        Libro lib = buscarLibroPorIsbn(isbn.trim());

        if (lib == null) {
            throw new Exception("No se encontró ningún libro físico con ese ISBN.");
        }
        if (!(lib instanceof LibroFisico)) {
            throw new Exception("El ISBN existe, pero corresponde a un Audiolibro, no a un Libro Físico.");
        }

        return (LibroFisico) lib;
    }

    
    public LibroAudio buscarLibroAudio(String isbn) throws Exception {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new Exception("Debes ingresar un ISBN para buscar.");
        }

        Libro lib = buscarLibroPorIsbn(isbn.trim());

        if (lib == null) {
            throw new Exception("No se encontró ningún audiolibro con ese ISBN.");
        }
        if (!(lib instanceof LibroAudio)) {
            throw new Exception("El ISBN existe, pero corresponde a un Libro Físico, no a un Audiolibro.");
        }

        return (LibroAudio) lib;
    }

    
    public Libro eliminarLibroFisico(String isbn) throws Exception {
        LibroFisico libro = buscarLibroFisico(isbn);
        libros.remove(isbn.trim());
        
        notificador.notificarObservadores("modificar_libroFisico");

        
        return libro;
    }

    
    public Libro eliminarLibroAudio(String isbn) throws Exception {
        LibroAudio libro = buscarLibroAudio(isbn);
        libros.remove(isbn.trim());
        
        notificador.notificarObservadores("modificar_Audiolibro");

        
        return libro;
    }

    /**
     * Búsqueda interna directa por ISBN, sin validaciones ni excepciones.
     * Devuelve null si no existe. Usado por los demás métodos públicos.
     */
    private Libro buscarLibroPorIsbn(String isbn) {
        return libros.get(isbn);
    }
    
    
    /**
     * Crea y añade una nueva Editorial a la lista si no existe previa coincidencia.
     */
    public Editorial crearEditorial(String nombre, String strAnioFundacion) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre de la editorial es obligatorio.");
        }

        String nombreLimpio = nombre.trim();

        if (buscarEditorialPorNombreExacto(nombreLimpio) != null) {
            throw new Exception("Ya existe una editorial registrada con el nombre: " + nombreLimpio);
        }

        int anioFundacion;
        try {
            anioFundacion = Integer.parseInt(strAnioFundacion.trim());
        } catch (NumberFormatException e) {
            throw new Exception("El año de fundación debe ser un número entero válido, ejemplo: 1998");
        }

        Editorial editorial = new Editorial(nombreLimpio, anioFundacion);
        editoriales.add(editorial);

        return editorial;
    }

    /**
     * Obtiene la lista completa de editoriales.
     */
    public List<Editorial> listarEditoriales() {
        return new ArrayList<>(editoriales);
    }

    /**
     * Busca una editorial de forma exacta por su nombre (ignorando mayúsculas/minúsculas).
     */
    public Editorial buscarEditorialPorNombreExacto(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        for (Editorial ed : editoriales) {
            if (ed.getNombre().equalsIgnoreCase(nombre.trim())) {
                return ed;
            }
        }
        return null;
    }

    /**
     * Busca editoriales cuyo nombre contenga el texto dado.
     * Ideal para autocompletar o desplegables con filtro dinámico al escribir.
     */
    public List<Editorial> buscarEditorialesPorCoincidencia(String textoBusqueda) {
        List<Editorial> coincidencias = new ArrayList<>();
        if (textoBusqueda == null || textoBusqueda.trim().isEmpty()) {
            return listarEditoriales();
        }

        String filtro = textoBusqueda.trim().toLowerCase();
        for (Editorial ed : editoriales) {
            if (ed.getNombre().toLowerCase().contains(filtro)) {
                coincidencias.add(ed);
            }
        }
        return coincidencias;
    }

    /**
     * Actualiza el nombre y/o año de fundación de una editorial existente.
     */
    public Editorial actualizarEditorial(String nombreActual, String nuevoNombre, String strNuevoAnio) throws Exception {
        Editorial editorial = buscarEditorialPorNombreExacto(nombreActual);
        if (editorial == null) {
            throw new Exception("No se encontró ninguna editorial registrada con el nombre: " + nombreActual);
        }

        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            throw new Exception("El nuevo nombre de la editorial es obligatorio.");
        }

        String nuevoNombreLimpio = nuevoNombre.trim();

        // Validar si el nuevo nombre entra en conflicto con otra editorial existente
        if (!editorial.getNombre().equalsIgnoreCase(nuevoNombreLimpio) 
                && buscarEditorialPorNombreExacto(nuevoNombreLimpio) != null) {
            throw new Exception("Ya existe otra editorial registrada con el nombre: " + nuevoNombreLimpio);
        }

        int nuevoAnio;
        try {
            nuevoAnio = Integer.parseInt(strNuevoAnio.trim());
        } catch (NumberFormatException e) {
            throw new Exception("El año de fundación debe ser un número entero válido.");
        }

        editorial.setNombre(nuevoNombreLimpio);
        editorial.setAñoFundacion(nuevoAnio);

        return editorial;
    }

    /**
     * Elimina una editorial de la lista por su nombre.
     */
    public Editorial eliminarEditorial(String nombre) throws Exception {
        Editorial editorial = buscarEditorialPorNombreExacto(nombre);
        if (editorial == null) {
            throw new Exception("No se encontró la editorial que deseas eliminar.");
        }

        editoriales.remove(editorial);

        return editorial;
    }
    
    
}