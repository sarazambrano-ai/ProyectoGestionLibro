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

        double precio = Double.parseDouble(strPrecio.trim());
        LocalDate fechaImpresion = LocalDate.parse(strFechaImpresion.trim());
        Editorial editorial = new Editorial(nombreEditorial.trim(), Integer.parseInt(strAnioFundacion.trim()));

        LibroFisico lib = new LibroFisico(isbn.trim(), titulo.trim(), autor.trim(),
                precio, fechaImpresion, tipoTapa, editorial);

        ServicioLibro.addLibro(lib);
        return lib;
    }

    public LibroAudio agregarLibroAudio(String isbn, String titulo, String autor,
            String strPrecio, String strDuracionMinutos, String narrador) throws Exception {

        double precio = Double.parseDouble(strPrecio.trim());
        int duracionMinutos = Integer.parseInt(strDuracionMinutos.trim());

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
}