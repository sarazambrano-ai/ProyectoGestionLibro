package com.sxzo.gestionlibros.model;

import java.time.LocalDate;

public class LibroFisico extends Libro {

    private LocalDate fechaImpresion;
    private String tipoTapa;

    public LibroFisico(String isbn, String titulo, String autor, double precio,
                       LocalDate fechaImpresion, String tipoTapa) throws Exception {

        super(isbn, titulo, autor, precio);

        this.fechaImpresion = fechaImpresion;
        this.tipoTapa = tipoTapa;
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
    
    @Override

    public double totalPagar() {

    if (tipoTapa.equalsIgnoreCase("Dura")) {
        return getPrecio() + 10000;
    } else {
        return getPrecio() + 5000;
    }
   }
}

   
