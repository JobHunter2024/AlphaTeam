package com.example.adminservlet.controlpanel.servlet;

import java.io.*;

import com.example.adminservlet.logger.AppConfig;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet(name = "adminServlet", value = "/admin-servlet")
public class AdminServlet extends HttpServlet {
    private String message;

    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}