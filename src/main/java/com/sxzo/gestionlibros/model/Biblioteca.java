/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sxzo.gestionlibros.model;

/**
 *
 * @author carinaortiz
 */

public class Biblioteca {

 
    private static Biblioteca instancia;

    
    private String nombre;
    private String nit;
    private String direccion;

    
    private Biblioteca() {
        nombre = "Biblioteca Gestión de Libros";
        nit = "123456789-0";
        direccion = "Ibagué, Tolima";
    }

    public static Biblioteca getInstance() {

        if (instancia == null) {
            instancia = new Biblioteca();
        }

        return instancia;
    }

   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
