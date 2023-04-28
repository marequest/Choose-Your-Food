package com.example.demoee;


import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "potvrdjenUnosServlet", value = "/potvrdjen-unos-servlet")
public class PotvrdjenUnosServlet extends HttpServlet {

    private String message;

    // Promenljive u servletu nisu thread safe!
    private int counter = 0;

    public PotvrdjenUnosServlet() {
        System.out.println("Constructor");
    }

    public void init() {
        System.out.println("init method");

        message = "Hello from Servlet!";
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("service method");
        super.service(req, resp);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("doGet method");

        response.setContentType("text/html");

        synchronized (this) {
            this.counter ++;
        }

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Vas izbor je upisan!</h1>");

        out.println();

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("narucio", true);

        response.sendRedirect("/potvrdjen-unos-servlet");

    }

    public void destroy() {
        System.out.println("destroy method");
    }
}
