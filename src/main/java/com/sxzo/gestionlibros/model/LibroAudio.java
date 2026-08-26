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
public class LibroAudio extends Libro implements IReproducible {

    private int duracionMinutos;
    private String narrador;

    public LibroAudio(String isbn, String titulo, String autor, double precio, int duracionMinutos, String narrador) throws Exception {
        super(isbn, titulo, autor, precio);
        this.duracionMinutos = duracionMinutos;
        this.narrador = narrador;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public String getNarrador() {
        return narrador;
    }

    public void setNarrador(String narrador) {
        this.narrador = narrador;
    }

    @Override
    public double totalPagar() {
        return getPrecio() + (duracionMinutos * 100);
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo Audiolibro");
    }

    @Override
    public void pausar() {
        System.out.println("Audiolibro Pausado");
    }

}
