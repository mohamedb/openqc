<jsp:include page="views/partials/header.jsp" />
<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-5">
        <h3>Register   <div class="pull-right">
                <a href="register.jsp">Login</a>
            </div>
        </h3>
        <form method="POST" action="main">
            <div class="form-group">
                <label>Email address</label>
                <input type="email" name="username" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
            </div>
            <div class="form-group">
                <label>Compte Enseignant</label>
                <input type="checkbox" name="roleProf" value="false">
            </div>
             <input type="hidden" name="action" value="register" />
            <button type="submit" class="new-btn new-primary-btn new-btn-large">Envoyer</button>
        </form>
    </div>
</div>
<!-- comment -->
<jsp:include page="views/partials/footer.jsp" />
