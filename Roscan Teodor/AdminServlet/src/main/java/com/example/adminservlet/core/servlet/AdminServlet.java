package com.example.adminservlet.core.servlet;

import java.io.*;
import java.net.MalformedURLException;
import java.util.*;

import com.example.adminservlet.core.config.ConfigValidator;
import com.example.adminservlet.core.config.ConfigInterface;
import com.example.adminservlet.core.config.ScrapperConfig;
import com.example.adminservlet.core.provider.DataToExtract;
import com.example.adminservlet.core.provider.DataToExtractAdvanced;
import com.example.adminservlet.core.provider.ProviderInterface;
import com.example.adminservlet.core.security.UserAccount;
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
    private ProviderInterface providerInterface=new ProviderInterface(scrapperConfig.getDatabaseCRUD(), scrapperConfig.getDatabaseCRUDAdvanced());
    private UserAccount userAccount =new UserAccount();

    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        if(userAccount.isUserLoggedIn(request)==false)
            response.sendRedirect("login.jsp");
        else
        {
            switch (action)
            {
                case "config":
                    request.setAttribute("dataList", providerInterface.getScraperConfig());
                    request.setAttribute("dataListAdvanced", providerInterface.getScraperConfigAdvanced());
                    String targetUuid = request.getParameter("uuid");
                    if(targetUuid!=null)
                        request.setAttribute("targetData", configInterface.getConfigurationByUUID(UUID.fromString(targetUuid)));

                    request.getRequestDispatcher("/protected/config.jsp").forward(request, response);
                    break;
                case "configAdvanced":
                    request.setAttribute("dataList", providerInterface.getScraperConfig());
                    request.setAttribute("dataListAdvanced", providerInterface.getScraperConfigAdvanced());
                    String targetUuidAdvanced = request.getParameter("uuidAdvanced");
                    if(targetUuidAdvanced!=null)
                        request.setAttribute("targetDataAdvanced", configInterface.getConfigurationByUUIDAdvanced(UUID.fromString(targetUuidAdvanced)));

                    request.getRequestDispatcher("/protected/config.jsp").forward(request, response);
                    break;
                case "credentials":
                    request.getRequestDispatcher("/protected/credentials.jsp").forward(request, response);
                    break;
                case "history":
                    request.setAttribute("historyList", providerInterface.getScrappingHistory());
                    request.getRequestDispatcher("/protected/history.jsp").forward(request, response);
                    break;
                case "results":
                    request.setAttribute("resultList", providerInterface.getScrappingResults());
                    request.setAttribute("resultListAdvanced", providerInterface.getScrappingResultsAdvanced());
                    request.getRequestDispatcher("/protected/results.jsp").forward(request, response);
                    break;
                case "statistics":
                    request.getRequestDispatcher("/protected/statistics.jsp").forward(request, response);
                    break;
                case "logout":
                    userAccount.logout(request);
                    response.sendRedirect("login.jsp");
                    break;
                case "deleteConfig":

                    String uuid = request.getParameter("uuid");
                    configInterface.removeConfiguration(UUID.fromString(uuid));

                    request.setAttribute("dataList", providerInterface.getScraperConfig());
                    request.setAttribute("dataListAdvanced", providerInterface.getScraperConfigAdvanced());
                    request.getRequestDispatcher("/protected/config.jsp").forward(request, response);
                    break;
                case "deleteConfigAdvanced":

                    String uuidAdvanced = request.getParameter("uuidAdvanced");
                    configInterface.removeConfigurationAdvanced(UUID.fromString(uuidAdvanced));

                    request.setAttribute("dataList", providerInterface.getScraperConfig());
                    request.setAttribute("dataListAdvanced", providerInterface.getScraperConfigAdvanced());
                    request.getRequestDispatcher("/protected/config.jsp").forward(request, response);
                    break;

                default:
                    response.sendRedirect("index.jsp");
            }
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
                request.setAttribute("dataListAdvanced", providerInterface.getScraperConfigAdvanced());
                request.getRequestDispatcher("/protected/config.jsp").forward(request, response);
                break;
            case "updateConfigAdvanced":
                String uuidStringAdvanced = request.getParameter("uuidAdvanced");
                String urlAdvanced = request.getParameter("urlAdvanced");
                String jobUrlPath = request.getParameter("jobUrlPath");
                String jobDescriptionPath = request.getParameter("jobDescriptionPath");
                String jobLocationPath = request.getParameter("jobLocationPath");
                String jobCompanyPath = request.getParameter("jobCompanyPath");
                String jobDatePath = request.getParameter("jobDatePath");
                String jobTitlePath=request.getParameter("jobTitlePath");
                boolean followLink = request.getParameter("followLink") != null;

                buildDataToExtractAdvanced(uuidStringAdvanced,urlAdvanced, jobUrlPath, jobDescriptionPath, jobLocationPath, jobCompanyPath, jobDatePath, jobTitlePath, followLink);
                request.setAttribute("dataList", providerInterface.getScraperConfig());
                request.setAttribute("dataListAdvanced", providerInterface.getScraperConfigAdvanced());
                request.getRequestDispatcher("/protected/config.jsp").forward(request, response);
                break;
            case "updateCredentials":
                String newUsername = request.getParameter("newUsername");
                String newPassword = request.getParameter("newPassword");
                String confirmPassword = request.getParameter("confirmPassword");

                if(userAccount.updateCredentials(request,newUsername,newPassword,confirmPassword).equals("success"))
                    request.getRequestDispatcher("/protected/credentials.jsp").forward(request, response);
                else
                    request.getRequestDispatcher("/protected/protectedError.jsp").forward(request, response);
                break;
            case "login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String loginResult =userAccount.login(request, username, password);

                response.setContentType("text/plain");
                response.getWriter().write(loginResult);

                if(loginResult.equals("success"))
                    request.getRequestDispatcher("/protected/credentials.jsp").forward(request, response);
                else
                    request.getRequestDispatcher("/protected/protectedError.jsp").forward(request, response);
                break;
            case "deleteHistory":
                providerInterface.deleteHistory();
                request.getRequestDispatcher("/protected/history.jsp").forward(request, response);
                break;
            case "deleteResults":
                providerInterface.deleteResults();
                request.getRequestDispatcher("/protected/results.jsp").forward(request, response);
                break;
            case "deleteResultsAdvanced":
                providerInterface.deleteResultsAdvanced();
                request.getRequestDispatcher("/protected/results.jsp").forward(request, response);
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

    public void buildDataToExtractAdvanced(
            String uuidStringAdvanced,
            String urlAdvanced,
            String jobUrlPath,
            String jobDescriptionPath,
            String jobLocationPath,
            String jobCompanyPath,
            String jobDatePath,
            String jobTitlePath,
            boolean followLink
    ) throws MalformedURLException {

        UUID uuid = (!Objects.equals(uuidStringAdvanced, "")) ? UUID.fromString(uuidStringAdvanced) : UUID.randomUUID();

        DataToExtractAdvanced dataToExtractAdvanced = new DataToExtractAdvanced(urlAdvanced,jobUrlPath,jobDescriptionPath,jobLocationPath, jobCompanyPath,jobDatePath,followLink, uuid, jobTitlePath);
        if(configInterface.getConfigurationByUUIDAdvanced(dataToExtractAdvanced.getUuid())==null)
            configInterface.addConfigurationAdvanced(dataToExtractAdvanced);
        else
            configInterface.updateConfigurationAdvanced(dataToExtractAdvanced);
    }

    public void destroy() {
    }
}