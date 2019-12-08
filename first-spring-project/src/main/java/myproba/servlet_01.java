package myproba;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servlet_01 implements Servlet {
    private ServletConfig config;
    public void init (ServletConfig config) throws ServletException
    {
        this.config = config;
    }
    public void destroy() {}
    public ServletConfig getServletConfig()
    {
        return config;
    }
    public String getServletInfo()
    {
        return "A Simple Servlet";
    }
    public void service (ServletRequest request, ServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType( "text/html" );
        PrintWriter out = response.getWriter();
        out.println( "<html><head>" );
        out.println( "<title>A Sample Servlet!</title>" );
        out.println( "</head>" );
        out.println( "<body>" );
        out.println( "<h1>Hello, World!</h1>" );
        out.println( "</body></html>" );
        out.close();
    }
}
