/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sxzo.gestionlibros.model;

import java.time.LocalDate;

/**
 *
 * @author carinaortiz
 */
public class Libro {

    private String isbn;
    private String titulo;
    private String autor;
    private double precio;

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Libro(String isbn, String titulo, String autor, double precio) throws Exception {
        this(isbn, titulo, autor);
        setPrecio(precio);
    }

    public void setPrecio(double precio) throws Exception {
        if (precio < 0) {
            throw new Exception("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double totalPagar() {
        return precio;
    }

}
