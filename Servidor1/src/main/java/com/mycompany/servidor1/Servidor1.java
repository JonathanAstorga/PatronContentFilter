/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.servidor1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject; 
/**
 *
 * @author Dell
 */
public class Servidor1 {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2000)) {
            System.out.println("Servidor esperando conexiones...");
            
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                // Flujo de entrada
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String jsonEntrada = in.readLine();
                
                
                // Procesar el mensaje
                JSONObject  pedido = new JSONObject(jsonEntrada);
                JSONObject pedidoFiltrado = new JSONObject();
                pedidoFiltrado.put("orderId", pedido.getString("orderId"));
                pedidoFiltrado.put("totalAmount", pedido.getDouble("totalAmount"));

                // Enviar respuesta filtrada
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(pedidoFiltrado.toString());

                System.out.println("Mensaje filtrado enviado: " + pedidoFiltrado);
                
                // Cerrar conexión
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
