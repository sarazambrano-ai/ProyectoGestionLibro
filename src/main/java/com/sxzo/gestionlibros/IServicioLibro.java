/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sxzo.gestionlibros;

import com.sxzo.gestionlibros.model.Libro;
import com.sxzo.gestionlibros.model.LibroAudio;
import com.sxzo.gestionlibros.model.LibroFisico;
import java.util.List;

/**
 * Contrato del servicio de gestión de libros. Declara todas las
 * operaciones de negocio disponibles (validación, transformación de
 * datos y persistencia en memoria), independientemente de cómo se
 * implementen internamente.
 *
 * @author misae
 */
public interface IServicioLibro {

    LibroFisico agregarLibroFisico(String isbn, String titulo, String autor,
            String strPrecio, String strFechaImpresion, String tipoTapa,
            String nombreEditorial, String strAnioFundacion) throws Exception;

    LibroAudio agregarLibroAudio(String isbn, String titulo, String autor,
            String strPrecio, String strDuracionMinutos, String narrador) throws Exception;

    List<LibroFisico> listarLibrosFisicos();

    List<LibroAudio> listarLibrosAudio();

    LibroFisico buscarLibroFisico(String isbn) throws Exception;

    LibroAudio buscarLibroAudio(String isbn) throws Exception;

    Libro eliminarLibroFisico(String isbn) throws Exception;

    Libro eliminarLibroAudio(String isbn) throws Exception;
}