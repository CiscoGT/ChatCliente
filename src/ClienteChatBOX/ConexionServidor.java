/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClienteChatBOX;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JTextField;
import org.apache.log4j.Logger;

/**
 * Esta clase gestiona el envio de datos entre el cliente y el servidor.
 *
 * @author Carlos Bautista
 * @CanalOficcial Cisco GT
 * @Carrera Ingenieria en Sistemas y TICs
 *
 */

public class ConexionServidor implements ActionListener
{
     private Logger log = Logger.getLogger(ConexionServidor.class);
    private Socket socket; 
    private JTextField tfMensaje;
    private String usuario;
    private DataOutputStream salidaDatos;
    
    
    public ConexionServidor(Socket socket, JTextField tfMensaje, String usuario) 
    {
        this.socket = socket;
            this.tfMensaje = tfMensaje;
        this.usuario = usuario;
        
        try 
        {
            this.salidaDatos = new DataOutputStream(socket.getOutputStream());
        } 
        catch (IOException ex) 
        {
            log.error("Error al crear el stream de salida : " + ex.getMessage());
        } 
        catch (NullPointerException ex) 
        {
            log.error("El socket no se creo correctamente. ");
        }
    }

    
    
    public void actionPerformed(ActionEvent e) 
    {
        try 
        {
            salidaDatos.writeUTF(usuario + ": " + tfMensaje.getText() );
            tfMensaje.setText("");
        } 
        catch (IOException ex) 
        {
            log.error("Error al intentar enviar un mensaje: " + ex.getMessage());
        }
    }
}
