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

/**
 * Servicio (Singleton) que gestiona la información de los libros:
 * valida los datos recibidos, aplica las reglas de negocio y persiste
 * los objetos en memoria principal. Implementa IServicioLibro para
 * que el resto de la aplicación dependa del contrato, no de esta
 * implementación concreta.
 *
 * @author misae
 */
public class ServicioLibro implements IServicioLibro {

    private static ServicioLibro instancia;

    private final Map<String, Libro> libros = new HashMap<>();

    /**
     * Constructor privado: nadie fuera de esta clase puede hacer
     * "new ServicioLibro()", garantizando que solo exista una instancia.
     */
    private ServicioLibro() {
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

    @Override
    public LibroFisico agregarLibroFisico(String isbn, String titulo, String autor,
            String strPrecio, String strFechaImpresion, String tipoTapa,
            String nombreEditorial, String strAnioFundacion) throws Exception {

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

        LocalDate fechaImpresion;
        try {
            fechaImpresion = LocalDate.parse(strFechaImpresion.trim());
        } catch (DateTimeParseException e) {
            throw new Exception("La fecha debe tener el formato AAAA-MM-DD, ejemplo: 2020-05-20");
        }

        int anioFundacion;
        try {
            anioFundacion = Integer.parseInt(strAnioFundacion.trim());
        } catch (NumberFormatException e) {
            throw new Exception("El año de fundación debe ser un número entero, ejemplo: 1998");
        }

        if (nombreEditorial == null || nombreEditorial.trim().isEmpty()) {
            throw new Exception("El nombre de la editorial es obligatorio.");
        }

        Editorial editorial = new Editorial(nombreEditorial.trim(), anioFundacion);

        LibroFisico lib = new LibroFisico(isbn.trim(), titulo.trim(), autor.trim(),
                precio, fechaImpresion, tipoTapa, editorial);

        libros.put(lib.getIsbn(), lib);
        return lib;
    }

    @Override
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
        return lib;
    }

    @Override
    public List<LibroFisico> listarLibrosFisicos() {
        List<LibroFisico> resultado = new ArrayList<>();
        for (Libro lib : libros.values()) {
            if (lib instanceof LibroFisico) {
                resultado.add((LibroFisico) lib);
            }
        }
        return resultado;
    }

    @Override
    public List<LibroAudio> listarLibrosAudio() {
        List<LibroAudio> resultado = new ArrayList<>();
        for (Libro lib : libros.values()) {
            if (lib instanceof LibroAudio) {
                resultado.add((LibroAudio) lib);
            }
        }
        return resultado;
    }

    @Override
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

    @Override
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

    @Override
    public Libro eliminarLibroFisico(String isbn) throws Exception {
        LibroFisico libro = buscarLibroFisico(isbn);
        libros.remove(isbn.trim());
        return libro;
    }

    @Override
    public Libro eliminarLibroAudio(String isbn) throws Exception {
        LibroAudio libro = buscarLibroAudio(isbn);
        libros.remove(isbn.trim());
        return libro;
    }

    /**
     * Búsqueda interna directa por ISBN, sin validaciones ni excepciones.
     * Devuelve null si no existe. Usado por los demás métodos públicos.
     */
    private Libro buscarLibroPorIsbn(String isbn) {
        return libros.get(isbn);
    }
}