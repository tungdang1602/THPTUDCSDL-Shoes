<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thần Kinh Giày</title>
        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!--Using Bootstrap-->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script src="https://kit.fontawesome.com/6c7ad27b5d.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <section class="text-center" style="background-color: white;">
                <div class="container-fluid" style="background-image: url('img/banner.png'); height: 400px;background-size: cover">
                </div>
            </section>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li style="margin-right: 20px"><a href="home">Home</a></li>
                                <li style="margin-right: 20px">
                                    <a class="btn btn-info btn-sm ml-3" href="show" style="height: 30px; padding-top: 2px">
                                        <i class="fa fa-shopping-cart"></i> Cart
                                        <span class="badge badge-light">${c.countNumCart(sessionScope.acc.id)}</span>
                                </a>
                            </li>
                            <!--<li class="breadcrumb-item active" aria-current="page">${CateName}</li>-->
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="Left.jsp"></jsp:include>

                    <div class="col-sm-6">
                        <div class="row" id="content">
                            <!--List Product-->
                        <c:forEach items="${listP}" var="o">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <!--Product's image-->
                                    <a href="detail?ProductID=${o.id}" title="View Product"><img class="card-img-top" src="${o.imageLink}" alt="Card image cap"></a>
                                    <div class="card-body">
                                        
                                        <h4 class="card-title show_txt"><a href="detail?ProductID=${o.id}" title="View Product">${o.name}</a></h4>
                                        <div class="row">
                                            <div class="col">
                                                <!--Product's Price-->
                                                <p class="btn btn-warning btn-block">${o.priceWithDot} VND</p>
                                            </div>
                                            <div class="col">
                                                <a onclick="addCart(${o.id})" class="btn btn-info btn-block" style="color: white">Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="clearfix">
                        <!--<div class="hint-text">Showing <b>6</b> out of <b>${count}</b> entries</div>-->
                        <ul class="pagination">
                            <li class="page-item paging active"><a onclick="load(this, 1, ${CategoryID})"  class="page-link">1</a></li>
                                <c:forEach begin="2" end="${end}" var="i">
                                <li class="page-item paging"><a onclick="load(this, ${i}, ${CategoryID})"  class="page-link">${i}</a></li>
<!--<li class="page-item"><a style="cursor:pointer" onclick="load(${i}, ${CategoryID})" >${i}</a></li>-->   
<!--<li class="page-item ${tag == i?"active":""}"><a href="home?index=${i}&CategoryID=${CategoryID}" class="page-link">${i}</a></li>-->
                            </c:forEach>
                        </ul>
                    </div>
                </div>
        </div>

        <jsp:include page="Footer.jsp"></jsp:include>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script>
                                    function load(a_link, index, CateID) {
                                        var arr = document.getElementsByClassName('paging');
                                        for (var i = 0; i < arr.length; i++) {
                                            arr[i].classList.remove("active");
                                        }

                                        a_link.parentElement.classList.add("active");
                                        //Sử dụng Ajax
                                        $.ajax({
                                            url: "/Shoes/paging",
                                            type: "get",
                                            data: {
                                                index: index,
                                                CategoryID: CateID
                                            },
                                            success: function (responseData) {
                                                document.getElementById("content").innerHTML = responseData;
                                            }
                                        });
                                    }
             </script>
    </body>
</html>
