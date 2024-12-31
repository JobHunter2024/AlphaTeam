package com.example.provider;

import com.example.provider.database.ProviderInterface;
import com.example.provider.database.entity.DataToExtract;
import com.example.provider.database.entity.HistoryRecord;
import com.example.provider.database.entity.ResultRecord;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/data")
public class DataResource {

    private final ProviderInterface provider;

    public DataResource() {
        this.provider = new ProviderInterface();
    }

    @GET
    @Path("/extract")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataToExtract() {
        List<DataToExtract> dataList = provider.getScraperConfig();
        return Response.ok(dataList).build();
    }

    @GET
    @Path("/history")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScrappingHistory() {
        List<HistoryRecord> historyList = provider.getScrappingHistory();
        return Response.ok(historyList).build();
    }

    @GET
    @Path("/results")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScrappingResults() {
        List<ResultRecord> resultsList = provider.getScrappingResults();
        return Response.ok(resultsList).build();
    }
}
