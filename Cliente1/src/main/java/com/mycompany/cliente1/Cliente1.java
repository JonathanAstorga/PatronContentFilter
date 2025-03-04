/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cliente1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.JSONObject;

/**
 *
 * @author Dell
 */
public class Cliente1 {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 2000)) {
            // Flujo de salida
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            // Crear mensaje JSON con todos los datos
            JSONObject pedido = new JSONObject();
            pedido.put("orderId", "12345");
            pedido.put("customerName", "Juan Pérez");
            pedido.put("email", "juan@email.com");
            pedido.put("totalAmount", 150.00);

            System.out.println("Enviando mensaje: " + pedido);
            out.println(pedido.toString());

            // Flujo de entrada para recibir respuesta filtrada
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String respuesta = in.readLine();

            System.out.println("Respuesta filtrada del servidor: " + respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
