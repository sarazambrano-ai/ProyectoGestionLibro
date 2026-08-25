/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sxzo.gestionlibros.controller;
import com.sxzo.gestionlibros.ServicioLibro;
import com.sxzo.gestionlibros.model.Editorial;
import com.sxzo.gestionlibros.model.Libro;
import com.sxzo.gestionlibros.model.LibroAudio;
import com.sxzo.gestionlibros.model.LibroFisico;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controlador: intermedia entre las vistas (GUI) y el modelo/repositorio.
 * Aquí vive la validación, conversión de datos y las reglas de negocio,
 * para que las clases de la capa Vista solo se encarguen de mostrar
 * información y capturar la interacción del usuario.
 *
 * @author misae
 */
public class LibroController {

    public LibroFisico agregarLibroFisico(String isbn, String titulo, String autor,
            String strPrecio, String strFechaImpresion, String tipoTapa,
            String nombreEditorial, String strAnioFundacion) throws Exception {

        if (isbn == null || isbn.trim().isEmpty()) {
            throw new Exception("El ISBN es obligatorio.");
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

        ServicioLibro.addLibro(lib);
        return lib;
    }

    public LibroAudio agregarLibroAudio(String isbn, String titulo, String autor,
            String strPrecio, String strDuracionMinutos, String narrador) throws Exception {

        if (isbn == null || isbn.trim().isEmpty()) {
            throw new Exception("El ISBN es obligatorio.");
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

        ServicioLibro.addLibro(lib);
        return lib;
    }

    public List<LibroFisico> listarLibrosFisicos() {
        List<LibroFisico> resultado = new ArrayList<>();
        Map<String, Libro> libros = ServicioLibro.getLibro();
        for (Libro lib : libros.values()) {
            if (lib instanceof LibroFisico) {
                resultado.add((LibroFisico) lib);
            }
        }
        return resultado;
    }

    public List<LibroAudio> listarLibrosAudio() {
        List<LibroAudio> resultado = new ArrayList<>();
        Map<String, Libro> libros = ServicioLibro.getLibro();
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

        Libro lib = ServicioLibro.buscarLibroPorIsbn(isbn.trim());

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

        Libro lib = ServicioLibro.buscarLibroPorIsbn(isbn.trim());

        if (lib == null) {
            throw new Exception("No se encontró ningún audiolibro con ese ISBN.");
        }
        if (!(lib instanceof LibroAudio)) {
            throw new Exception("El ISBN existe, pero corresponde a un Libro Físico, no a un Audiolibro.");
        }

        return (LibroAudio) lib;
    }
}