<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thần Kinh Giày</title>
    </head>
    <body>
        <div class="col-sm-1">

        </div>
        <div class="col-sm-2">
            <div class="card bg-light mb-3">
                <div class="card-header bg-info text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                <ul class="list-group category_block">
                    <c:forEach items="${allCategory}" var="o">
                        <!--Category nao dang dc chon thi se noi bat-->
                        <li class="list-group-item text-white ${CateID == o.id ? "category" : ""}">
                            <a href="home?CategoryID=${o.id}">${o.icon} ${o.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="card bg-light mb-3">
                <div class="card-header bg-info text-white text-uppercase">HOT product</div>
                <div class="card-body">
                    <img class="img-fluid" src="${hot.imageLink}" />
                    <h5 class="card-title">
                        <a href="#">
                            ${hot.name}
                        </a>
                    </h5>
                    <p class="bloc_left_price">${hot.priceWithDot} VND</p>
                </div>
            </div>
            
        </div>
    </body>
</html>
