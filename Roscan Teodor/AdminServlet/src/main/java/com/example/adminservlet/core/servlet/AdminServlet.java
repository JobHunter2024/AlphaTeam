package com.example.adminservlet.core.servlet;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import com.example.adminservlet.core.config.ConfigValidator;
import com.example.adminservlet.core.config.ConfigInterface;
import com.example.adminservlet.core.config.ScrapperConfig;
import com.example.adminservlet.core.data.extraction.DataToExtract;
import com.example.adminservlet.core.provider.ProviderInterface;
import com.example.adminservlet.logger.AppConfig;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet(name = "adminServlet", value = "/admin-servlet")
public class AdminServlet extends HttpServlet {
    private ConfigValidator configValidator=new ConfigValidator();
    private ScrapperConfig scrapperConfig=new ScrapperConfig();
    private ConfigInterface configInterface=new ConfigInterface(configValidator,scrapperConfig);
    private ProviderInterface providerInterface=new ProviderInterface(scrapperConfig.getDatabaseCRUD());

    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        providerInterface.resultMockery();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        switch (action)
        {
            case "config":
                request.setAttribute("dataList", providerInterface.getScraperConfig());
                String targetUuid = request.getParameter("uuid");
                if(targetUuid!=null)
                    request.setAttribute("targetData", configInterface.getConfigurationByUUID(UUID.fromString(targetUuid)));

                request.getRequestDispatcher("/config.jsp").forward(request, response);
                break;
            case "credentials":
                request.getRequestDispatcher("/credentials.jsp").forward(request, response);
                break;
            case "history":
                request.setAttribute("historyList", providerInterface.getScrappingHistory());
                request.getRequestDispatcher("/history.jsp").forward(request, response);
                break;
            case "results":
                request.setAttribute("resultList", providerInterface.getScrappingResults());
                request.getRequestDispatcher("/results.jsp").forward(request, response);
                break;
            case "statistics":
                request.getRequestDispatcher("/statistics.jsp").forward(request, response);
                break;
            case "deleteConfig":

                String uuid = request.getParameter("uuid");
                configInterface.removeConfiguration(UUID.fromString(uuid));

                request.setAttribute("dataList", providerInterface.getScraperConfig());
                request.getRequestDispatcher("/config.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");

        switch (action)
        {
            case "updateConfig":
                String uuidString = request.getParameter("uuid");
                String urlString = request.getParameter("url");
                String[] values = request.getParameterValues("pathValue");
                String path = String.join(" > ", values);
                buildDataToExtract(urlString, path, uuidString);
                request.setAttribute("dataList", providerInterface.getScraperConfig());
                request.getRequestDispatcher("/config.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("index.jsp");
        }
    }

    public void buildDataToExtract(String urlString, String path, String uuidString) throws MalformedURLException {

        UUID uuid = (!Objects.equals(uuidString, "")) ? UUID.fromString(uuidString) : UUID.randomUUID();

        DataToExtract dataToExtract = new DataToExtract(urlString, path, uuid);
        if(configInterface.getConfigurationByUUID(dataToExtract.getUuid())==null)
            configInterface.addConfiguration(dataToExtract);
        else
            configInterface.updateConfiguration(dataToExtract);
    }

    public void destroy() {
    }
}