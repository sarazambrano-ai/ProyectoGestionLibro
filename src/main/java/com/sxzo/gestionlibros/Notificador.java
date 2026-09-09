/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sxzo.gestionlibros;

import com.sxzo.gestionlibros.model.Libro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author guimel78
 */
public class Notificador 
{
    
    Map<String, List<IObservador>> listeners = new HashMap();

    
    public Notificador(String... operations) {
        for (String operation : operations) {
            listeners.put(operation, new ArrayList<>());
        }
    }
    

    public void agregarObservador(String tipoEvento, IObservador obs ) {
        List<IObservador> users = listeners.get(tipoEvento);
        users.add(obs);
    }

    public void removerObservador(String tipoEvento, IObservador obs ) {
        List<IObservador> users = listeners.get(tipoEvento);
        users.remove(obs);
    }

    public void notificarObservadores(String tipoEvento) 
    {
        List<IObservador> users = listeners.get(tipoEvento);
        for (IObservador listener : users) {
            listener.actualizar();
        }
    }
    
}
