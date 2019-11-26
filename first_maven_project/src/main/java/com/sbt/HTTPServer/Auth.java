package com.sbt.HTTPServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import com.sun.net.httpserver.Authenticator;


/**
 * Created by SBT-Klyshov-DA on 03.07.2018.
 */
class Auth extends Authenticator {
    @Override
    public Result authenticate(HttpExchange httpExchange) {
        if ("/forbidden".equals(httpExchange.getRequestURI().toString()))
            return new Failure(403);
        else
            return new Success(new HttpPrincipal("c0nst", "realm"));
    }
}
