package com.sxzo.gestionlibros.model;

import java.time.LocalDate;

public class LibroFisico extends Libro {

    private LocalDate fechaImpresion;
    private String tipoTapa;
    private Editorial editorial;

    public LibroFisico(String isbn, String titulo, String autor, double precio,
                       LocalDate fechaImpresion, String tipoTapa, Editorial editorial) throws Exception {
        super(isbn, titulo, autor, precio);
        this.fechaImpresion = fechaImpresion;
        this.tipoTapa = tipoTapa;
        this.editorial = editorial;
    }

    public LocalDate getFechaImpresion() {
        return fechaImpresion;
    }

    public void setFechaImpresion(LocalDate fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }

    public String getTipoTapa() {
        return tipoTapa;
    }

    public void setTipoTapa(String tipoTapa) {
        this.tipoTapa = tipoTapa;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public double totalPagar() {
        if (tipoTapa.equalsIgnoreCase("Dura")) {
            return getPrecio() + 10000;
        } else {
            return getPrecio() + 5000;
        }
    }
}