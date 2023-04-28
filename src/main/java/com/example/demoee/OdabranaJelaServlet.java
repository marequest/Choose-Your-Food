package com.example.demoee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

@WebServlet(name = "odabranaJelaServlet", value = "/odabrana-jela-servlet")
public class OdabranaJelaServlet extends HttpServlet {

    private String message;

    // Promenljive u servletu nisu thread safe!
    private int counter = 0;

    public OdabranaJelaServlet() {
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

        File fileLozinke = new File("/Users/mareq/Desktop/tomcat/tekstfajlovi/password.txt");
        BufferedReader brLozinke = new BufferedReader(new FileReader(fileLozinke));
        ArrayList<String> lozinkeList = new ArrayList<>();
        String stLozinke;
        while ((stLozinke = brLozinke.readLine()) != null) {
            lozinkeList.add(stLozinke);
        }

        if (request.getParameter("lozinka") == null) {
            response.getOutputStream().println("Potrebna vam je lozinka za tu stranicu");
            response.setStatus(403);
            return;
        }
        if(!lozinkeList.contains(request.getParameter("lozinka"))){
            response.getOutputStream().println("Pogresna Lozinka");
            response.setStatus(403);
            return;
        }

        PrintWriter out = response.getWriter();

        out.println("<html><head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "    <title>Domaci 4</title>\n" +
                "\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n" +
                "</head><body>");
        out.println("<h1>Odabrana Jela</h1>");

        out.println("<form method=\"POST\" action=\"/obnovi-servlet\">");
            out.println("<input class=\"btn btn-danger\" type=\"submit\" value=\"Ocisti\">");
        out.println("</form>");

        out.println();
        File file1 = new File("/Users/mareq/Desktop/tomcat/tekstfajlovi/ponedeljak.txt");
        File file2 = new File("/Users/mareq/Desktop/tomcat/tekstfajlovi/utorak.txt");
        File file3 = new File("/Users/mareq/Desktop/tomcat/tekstfajlovi/sreda.txt");
        File file4 = new File("/Users/mareq/Desktop/tomcat/tekstfajlovi/cetvrtak.txt");
        File file5 = new File("/Users/mareq/Desktop/tomcat/tekstfajlovi/petak.txt");

        BufferedReader br = new BufferedReader(new FileReader(file1));

        out.println("<h3>Ponedeljak</h3>");
        out.println("<table class=\"table table-striped\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">#</th>\n" +
                "      <th scope=\"col\">Jelo</th>\n" +
                "      <th scope=\"col\">Kolicina</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <th scope=\"row\">1</th>\n" +
                "      <td>Mark</td>\n" +
                "      <td>Otto</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <th scope=\"row\">2</th>\n" +
                "      <td>Jacob</td>\n" +
                "      <td>Thornton</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <th scope=\"row\">3</th>\n" +
                "      <td>Larry</td>\n" +
                "      <td>the Bird</td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>");

        out.println("<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>\n");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/odabrana-jela-servlet");
    }

    public void destroy() {
        System.out.println("destroy method");
    }
}
