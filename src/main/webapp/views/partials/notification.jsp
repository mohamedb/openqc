<%@page import="main.servelet.Notification" %>
<% Notification notification =(Notification)session.getAttribute("notification"); %>
<% if(notification!=null) { %>
    <center id="alertArea">
        
       <div class="alert alert-<%= notification.getType()%> alert-dismissible" role="alert">
           <button type="button" class="close" data-dismiss="alert" aria-label="Close" id="btnClose">
            <span aria-hidden="true">&times;</span></button>
           <strong><%= notification.getType()%></strong>. <%= notification.getMessage() %>
      </div>
    </center>
<% } %>



