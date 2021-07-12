<%@page import="dao.CartDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thần Kinh Giày</title>
        
        <jsp:useBean id="c" class="dao.CartDAO"></jsp:useBean>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="home"><img src="img/logo.png" width="150px" height="80px"></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
                    </li>
                  
                    <c:if test="${sessionScope.acc == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="login">Login</a>
                        </li>
                    </c:if>

                   
                    <c:if test = "${sessionScope.acc != null}">
                        <li class="nav-item">
                            
                            <a class="nav-link text-info" href="login">Hello ${sessionScope.acc.user}</a>
                        </li>
                        
                        <c:if test="${sessionScope.acc.isSell == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="manager">Manager Product</a>
                            </li> 
                           
                        </c:if>
                        
                        <c:if test="${sessionScope.acc.isAdmin == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="accountManager">Manager Account</a>
                            </li> 
                        </c:if>
                        <li class="nav-item">
                            
                            <a class="nav-link" href="logout">LogOut</a>
                        </li>
                    </c:if>
                </ul>
                <form class="form-inline my-2 my-lg-0" action="search" method="post">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search..." oninput="searchByName(this)" value="${txtS}" name="txt">
                    <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>

                    <a class="btn btn-info btn-sm ml-3" href="show" style="height: 36px; padding-top: 6px">
                        <i class="fa fa-shopping-cart"></i> Cart
                        <span class="badge badge-light">${c.countNumCart(sessionScope.acc.id)}</span>
                    </a>
                </form>
            </div>
        </nav>
    </body>
</html>