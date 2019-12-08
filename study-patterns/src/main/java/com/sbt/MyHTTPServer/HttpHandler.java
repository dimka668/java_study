package com.sbt.MyHTTPServer;

import com.sun.deploy.net.HttpUtils;

import java.io.*;
import java.net.Socket;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;

/**
 * Created by 16688641 on 26.09.2018.
 */
public class HttpHandler implements Callable<Void> {

    private final Socket socket;

    public HttpHandler(Socket socket){
        this.socket=socket;
    }

    @Override
    public Void call() throws IOException {
        try(InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream()){
//            StringReader stringReader = new StringReader();
//            stringReader.read(CharBuffer.wrap("\n\n"));
            os.write("HTTP/1.1\n".getBytes());
            os.write("Content-Type: text/plain\n".getBytes());
            os.write("Set-Cookie: a=b;\n".getBytes());
            os.write("\n".getBytes());
            os.write("Hello!!".getBytes());
            byte[] b = new byte[1024];
            int i=1024;
            ByteArrayOutputStream responseBAOS = new ByteArrayOutputStream();
            while (i == 1024){
                i = is.read(b);
//                System.out.println(i);
//                os.write(b);
                responseBAOS.write(b);
                System.out.println(responseBAOS.toString());
            }
            System.out.println("ddd");
        }
        return null;
    }
}
