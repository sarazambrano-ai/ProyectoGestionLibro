/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sxzo.gestionlibros;

import com.sxzo.gestionlibros.model.LibroAudio;
import com.sxzo.gestionlibros.model.LibroFisico;
import java.util.List;

/**
 *
 * @author guimel78
 */
public interface INotificador 
{
    
    void agregarObservador(IObservador obs);

    void removerObservador(IObservador obs);

    void notificarObservadores();
    
}
