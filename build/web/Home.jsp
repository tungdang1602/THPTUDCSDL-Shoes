<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
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
                                <li style="margin-right: 20px"><a href="Home1.jsp">Home</a></li>
                                <li style="margin-right: 20px">
                                    <a class="btn btn-info btn-sm ml-3" href="#" style="height: 30px; padding-top: 2px">
                                        <i class="fa fa-shopping-cart"></i> Cart                                      
                                </a>
                            </li>
                        </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                <jsp:include page="Left.jsp"></jsp:include>
                    <div class="col-sm-6">
                        <div class="row" id="content">
                            <!--List Product-->
                        <c:forEach begin ="1" end = "6" var="o">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <!--Product's image-->
                                    <a href="#" title="View Product"><img class="card-img-top" src=https://image.yes24.vn/Upload/ProductImage/thethaochinhang/2063162_L.jpg?width=550&height=550 alt="Card image cap"></a>
                                    <div class="card-body">
                                        
                                        <h4 class="card-title show_txt"><a href="#" title="View Product">Giày</a></h4>
                                        <div class="row">
                                            <div class="col">
                                                <!--Product's Price-->
                                                <p class="btn btn-warning btn-block">100 VND</p>
                                            </div>
                                            <div class="col">
                                                <a class="btn btn-info btn-block" style="color: white">Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>

        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>

