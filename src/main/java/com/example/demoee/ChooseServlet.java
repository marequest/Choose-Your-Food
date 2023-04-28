package com.example.demoee;

import java.io.*;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "chooseServlet", value = "/choose-servlet")
public class ChooseServlet extends HttpServlet {

    // Promenljive u servletu nisu thread safe!
    private int counter = 0;
    HashMap<String, Integer> brojJela = new HashMap<String, Integer>();

    public ChooseServlet() {
        System.out.println("Constructor");
    }

    public void init() {
        System.out.println("init method");

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
            this.counter++;
        }

        try {
            boolean narucio = request.getSession().getAttribute("narucio").equals(true);
            if (narucio) {
                response.getOutputStream().println("Vec ste narucili, sacekajte sledecu nedelju.");
                response.setStatus(403);
                return;
            }
        } catch (Exception e){

        }

        PrintWriter out = response.getWriter();

        out.println("<html><head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "    <title>Domaci 4</title>\n" +
                "\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n" +
                "</head><body>");
        out.println("<h1>Choose your food</h1>");
        out.println("<h3>Odaberite vas rucak:</h3>");

        out.println();
        File file1 = new File("/Users/mareq/Desktop/tomcat/tekstfajlovi/petak.txt");
        File file2 = new File("/Users/mareq/Desktop/tomcat/tekstfajlovi/utorak.txt");
        File file3 = new File("/Users/mareq/Desktop/tomcat/tekstfajlovi/sreda.txt");
        File file4 = new File("/Users/mareq/Desktop/tomcat/tekstfajlovi/cetvrtak.txt");
        File file5 = new File("/Users/mareq/Desktop/tomcat/tekstfajlovi/petak.txt");

        BufferedReader br = new BufferedReader(new FileReader(file1));

        out.println("<form method=\"POST\" action=\"/potvrdjen-unos-servlet\">\n");

        out.println("<select id=\"ponedeljak\" class=\"form-select\" aria-label=\"Default select example\">");
        out.println("<p>Ponedeljak</p>");
        String st;
        while ((st = br.readLine()) != null) {
            out.println("<option value=\"1\">" + st + "</option>");
        }
        out.println("</select><hr>");

        out.println("<select id=\"utorak\" class=\"form-select\" aria-label=\"Default select example\">");
        out.println("<p>Utorak</p>");
        br = new BufferedReader(new FileReader(file2));
        while ((st = br.readLine()) != null) {
            out.println("<option value=\"1\">" + st + "</option>");
        }
        out.println("</select><hr>");

        out.println("<select id=\"sreda\" class=\"form-select\" aria-label=\"Default select example\">");
        out.println("<p>Sreda</p>");
        br = new BufferedReader(new FileReader(file3));
        while ((st = br.readLine()) != null) {
            out.println("<option value=\"1\">" + st + "</option>");
        }
        out.println("</select><hr>");

        out.println("<select id=\"cetvrtak\" class=\"form-select\" aria-label=\"Default select example\">");
        out.println("<p>Cetvrtak</p>");
        br = new BufferedReader(new FileReader(file4));
        while ((st = br.readLine()) != null) {
            out.println("<option value=\"1\">" + st + "</option>");
        }
        out.println("</select><hr>");

        out.println("<select id=\"petak\" class=\"form-select\" aria-label=\"Default select example\">");
        out.println("<p>Petak</p>");
        br = new BufferedReader(new FileReader(file5));
        while ((st = br.readLine()) != null) {
            out.println("<option value=\"1\">" + st + "</option>");
        }
        out.println("</select><hr>");

        out.println("<input class=\"btn btn-primary\" type=\"submit\" value=\"Potvrdite Unos\">");
        out.println("</form>");

        out.println("<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>\n");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO Pitaj da li je vec odabrao jela


        response.sendRedirect("/choose-servlet");
    }

    public void destroy() {
        System.out.println("destroy method");
    }
}
