package com.grug.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) {

        System.out.println("------Server-----");

        ServerSocket serverSocket;
        try {

            serverSocket = new ServerSocket(9999);

            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.print(br.readLine());
            socket.setSoTimeout(50000);
            Writer writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write("get\n");
            Thread.sleep(10000);
            writer.flush();
        } catch (Exception e) {

        }
    }
}
