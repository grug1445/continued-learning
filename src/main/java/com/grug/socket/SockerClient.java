package com.grug.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class SockerClient {

    public static void main(String[] args) {
        System.out.print("======client=======");
        Socket socket;
        try {
            socket = new Socket("127.0.0.1", 9999);
            socket.setSoTimeout(5000);
            Writer writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write("hello");
            writer.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(br.readLine());
        } catch (Exception e) {

        }
    }
}
