/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sxzo.gestionlibros.model;

/**
 * Representa la editorial (casa editorial) asociada a un LibroFisico.
 * Relación de asociación: un LibroFisico "tiene una" Editorial.
 */
public class Editorial {

    private String nombre;
    private int anioFundacion;


    public Editorial(String nombre, int añoFundacion) {
        this.nombre = nombre;
        this.anioFundacion = añoFundacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAñoFundacion() {
        return anioFundacion;
    }

    public void setAñoFundacion(int añoFundacion) {
        this.anioFundacion = añoFundacion;
    }
  
    
}