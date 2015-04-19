<nav class="navbar navbar-default navbar-fixed-top" >
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">OpenQC</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <% if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) { %>
            <ul class="nav navbar-nav">
                <li><a class="menu-item-a active" href="#">Users<span class="sr-only">(current)</span></a></li>
                <li><a href="#" class="menu-item-a">Inbox</a></li>              
            </ul>
            <span class="dropdown">          
                <input id="nav_search_input" type="text" class="form-control dropdown-toggle" placeholder="Search ?">
                <ul class="dropdown-menu search-results" role="menu">
                    <li><a href="#">User: ABCD <span class="pull-right"><code>6 results </code></span></a></li>
                    <li><a href="#">Message: draft</a></li>
                    <li><a href="#">Role: Admin</a></li>
                    <li><a href="#">User: ABCD</a></li>
                    <li><a href="#">Message: draft</a></li>
                    <li><a href="#">Role: Admin</a></li>
                </ul>
            </span>
            <% } %>
            <ul class="nav navbar-nav navbar-right">

                <% if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle menu-item-a" role="button" aria-expanded="false">
                        <%= session.getAttribute("username")%>                       
                        <span class="dropdown-sign"> &#926; </span> </a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="main?action=logout">Logout</a></li>
                    </ul>
                </li>
                <% } else { %>
                <li><a href="register.jsp" class="menu-item-a">Créer compte</a></li>
                <li><a href="login.jsp" class="menu-item-a">Login</a></li>
                    <%    }%>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="row">
    <div class="col-sm-12 body-top-menu">

        <button class="pull-right new-btn new-primary-btn "> Update </button>
    </div>
</div>

