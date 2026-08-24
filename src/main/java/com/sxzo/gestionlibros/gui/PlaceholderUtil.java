/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sxzo.gestionlibros.gui;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

/**
 * Utilidad para mostrar texto de ejemplo (placeholder) dentro de un
 * JTextField, que desaparece al hacer clic y reaparece si el campo
 * queda vacío. Evita repetir la misma lógica en cada ventana.
 *
 * @author misae
 */
public class PlaceholderUtil {

    public static void configurar(JTextField campo, String placeholder) {
        campo.setText(placeholder);
        campo.setForeground(Color.GRAY);

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (campo.getText().trim().isEmpty()) {
                    campo.setText(placeholder);
                    campo.setForeground(Color.GRAY);
                }
            }
        });
    }

    /**
     * Devuelve el texto real del campo, o cadena vacía si todavía
     * tiene puesto el placeholder (el usuario no escribió nada).
     */
    public static String obtenerTexto(JTextField campo, String placeholder) {
        String texto = campo.getText();
        return texto.equals(placeholder) ? "" : texto;
    }
}