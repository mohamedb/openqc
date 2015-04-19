<jsp:include page="views/partials/header.jsp" />
<jsp:include page="views/partials/topBar.jsp" />
<div  class="row">
    <div class="container-fluid">
        <jsp:include page="/views/partials/leftMenu.jsp" />
         <div class="col-sm-8 col-md-10 main" ui-view autoscroll="false" class="mainView-animate">
              <h4>Page index global: <br>
            <hr>            
            <a href="/OpenQC/ws/etudiants">WS/Etudiants</a> <br><br><br>
        </h4>
       
            
        </div>

    </div>
</div>
<jsp:include page="views/partials/footer.jsp" />