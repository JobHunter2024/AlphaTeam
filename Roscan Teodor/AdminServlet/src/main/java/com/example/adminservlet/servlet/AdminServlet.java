package com.example.adminservlet.servlet;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private String message;
    private ConfigValidator configValidator=new ConfigValidator();
    private ScrapperConfig scrapperConfig=new ScrapperConfig();
    private ConfigInterface configInterface=new ConfigInterface(configValidator,scrapperConfig);
    private ProviderInterface providerInterface=new ProviderInterface(scrapperConfig.getDatabaseCRUD());

    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        message = "Hello World!";
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
                request.getRequestDispatcher("/history.jsp").forward(request, response);
                break;
            case "results":
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
                String[] keys = request.getParameterValues("pathKey");
                String[] values = request.getParameterValues("pathValue");

                buildDataToExtract(urlString, uuidString, keys, values);
                request.setAttribute("dataList", providerInterface.getScraperConfig());
                request.getRequestDispatcher("/config.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("index.jsp");
        }
    }

    public void buildDataToExtract(String urlString, String uuidString, String[] keys, String[] values) throws MalformedURLException {
        Map<String, String> path = new HashMap<>();
        if (keys != null && values != null)
        {
            for (int i = 0; i < keys.length; i++)
            {
                if (!keys[i].isEmpty() && !values[i].isEmpty())
                    path.put(keys[i], values[i]);
            }
        }

        UUID uuid = (uuidString != null) ? UUID.fromString(uuidString) : UUID.randomUUID();

        DataToExtract dataToExtract = new DataToExtract(urlString, path, uuid);
        if(configInterface.getConfigurationByUUID(dataToExtract.getUUID())==null)
            configInterface.addConfiguration(dataToExtract);
        else
            configInterface.updateConfiguration(dataToExtract);
    }

    public void destroy() {
    }
}