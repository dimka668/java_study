package com.klyshov.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by 16688641 on 18.03.2019.
 */
public class HelloServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(HelloServlet.class.getName());

    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        log.info("\nreq="+req.toString()+"\nresp="+resp.toString());
        log.info(">>>>>>>>> GET: req.getContextPath()=" + req.getContextPath());
        log.info(">>>>>>>>> GET: req.getPathInfo()=" + req.getPathInfo());
        req.setAttribute("application_name", req.getServletContext().getAttribute("application.name"));
        log.info(">>>>>>>>> GET: Read context attribute application_name="+req.getServletContext().getAttribute("application.name"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/welcome.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.info(">>>>>>>>> POST: req.getContextPath()=" + req.getContextPath());
        log.info(">>>>>>>>> POST: req.getPathInfo()=" + req.getPathInfo());
        req.setAttribute("application_name", req.getServletContext().getAttribute("application.name"));
        log.info(">>>>>>>>> POST: Read context attribute application_name="+req.getServletContext().getAttribute("application.name"));

        log.info(">>>>>>>>> POST: Read parameter name="+req.getParameter("name"));
        String name = req.getParameter("name");
        log.info(">>>>>>>>> POST: set attribute name="+name);
        req.setAttribute("name", name);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/hello.jsp");
        dispatcher.forward(req, resp);
    }

}
