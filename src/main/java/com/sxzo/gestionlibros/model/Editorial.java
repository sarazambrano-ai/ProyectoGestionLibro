/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sxzo.gestionlibros.model;


public class Editorial extends LibroFisico {

    private String nombre;
    private int añoFundacion;

    public Editorial(String isbn, String titulo, String autor, double precio,
                     java.time.LocalDate fechaImpresion, String tipoTapa,
                     String nombre, int añoFundacion) throws Exception {

        super(isbn, titulo, autor, precio, fechaImpresion, tipoTapa);

        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAñoFundacion() {
        return añoFundacion;
    }

    public void setAñoFundacion(int añoFundacion) {
        this.añoFundacion = añoFundacion;
        

    }
}
