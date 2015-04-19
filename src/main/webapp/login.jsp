<jsp:include page="views/partials/header.jsp" />
<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-5">
        <h3>Login   <div class="pull-right">
                <a href="register.jsp">Regiter</a>
            </div>
        </h3>
        <form method="POST" action="main">
            <div class="form-group">
                <label>Email address</label>
                <input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
            </div>
            <div class="form-group">
                <label for="exampleInputFile">Remember me</label>
                <input type="checkbox" id="rememberMe" name="rememberMe" value="true">
            </div>

            <button type="submit" class="new-btn new-primary-btn new-btn-large">Submit</button>
        </form>

    </div>
</div>
<!-- comment -->
<jsp:include page="views/partials/footer.jsp" />