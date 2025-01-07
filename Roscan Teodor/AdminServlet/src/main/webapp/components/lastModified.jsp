<%@ page import="com.example.adminservlet.core.provider.ModificationRecord" %>
<%@ page import="java.util.List" %>

<%
    List<ModificationRecord> modificationList = (List<ModificationRecord>) request.getAttribute("modificationList");
    if (modificationList != null && !modificationList.isEmpty()) {
        ModificationRecord lastModification = modificationList.get(0);
%>
<div class="last-modified">
    <span><%= lastModification.getUsername() %></span>
    &nbsp; <span><%= lastModification.getModificationName() %></span>
    &nbsp; <span><%= lastModification.getDate() %></span>
</div>
<%
} else {
%>
<div class="last-modified">
    No modifications have been made yet.
</div>
<%
    }
%>
