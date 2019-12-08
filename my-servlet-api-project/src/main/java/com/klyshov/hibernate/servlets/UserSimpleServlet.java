package com.klyshov.hibernate.servlets;

import com.klyshov.hibernate.User;
import com.klyshov.hibernate.dao.UserDaoImpl;
import com.klyshov.hibernate.services.UserService;

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
 * Created by 16688641 on 10.01.2019.
 */
public class UserSimpleServlet extends HttpServlet {

    private UserService service = new UserService();
    private static final Logger log = Logger.getLogger(UserDaoImpl.class.getName());

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
        log.info(">>>>>>>>> GET: req.getContextPath()" + req.getContextPath());
        log.info(">>>>>>>>> GET: req.getPathInfo()" + req.getPathInfo());
        List<User> users = service.findAllUsers();
        req.setAttribute("users", users);
        req.setAttribute("application_name", req.getServletContext().getAttribute("application.name"));
        log.info(">>>>>>>>> GET: Read context attribute application_name="+req.getServletContext().getAttribute("application.name"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/showUsers.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.info(">>>>>>>>> POST: req.getContextPath()" + req.getContextPath());
        log.info(">>>>>>>>> POST: req.getPathInfo()" + req.getPathInfo());

        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        User user = new User(name, age);
        service.saveUser(user);
        resp.sendRedirect(req.getContextPath()+"/users");
    }

    @Override
    protected void	doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = service.findUser(id);
        user.setName(req.getParameter("name"));
        user.setAge(Integer.parseInt(req.getParameter("age")));
        service.updateUser(user);
        resp.sendRedirect(req.getContextPath()+"/users");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteUser(service.findUser(id));
        resp.sendRedirect(req.getContextPath()+"/users");
    }
}
