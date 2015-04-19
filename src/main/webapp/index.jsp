<jsp:include page="views/partials/header.jsp" />
<jsp:include page="views/partials/topBar.jsp" />
<div class="row">
    <div class="container-fluid">
        <div class="col-sm-8 col-md-12 main">
            <h3> <%
                Object message = request.getAttribute("message");
                if (message != null) {
                    out.println(message.toString());
                }
                %>
            </h3>
        </div>
    </div>
</div>
<jsp:include page="views/partials/footer.jsp" />