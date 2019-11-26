package com.sbt.MyHTTPServer;

import com.sun.deploy.net.HttpUtils;

import javax.xml.ws.spi.http.HttpExchange;
import javax.xml.ws.spi.http.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by 16688641 on 26.09.2018.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = new ThreadPoolExecutor(
                4, 64,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(256)
        );
        ServerSocket serverSocket = new ServerSocket(81, 256);
        while (true){
            final Socket socket = serverSocket.accept();
            threadPool.submit(new com.sbt.MyHTTPServer.HttpHandler(socket));
        }

    }


}
