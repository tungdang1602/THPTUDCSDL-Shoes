<%-- 
    Document   : Detail
    Created on : Jul 8, 2021, 9:54:22 PM
    Author     : Trung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
        <title>Thần Kinh Giày</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }
            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }
            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
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
                        <div class="container">
                            <div class="card">
                                <div class="row">
                                    <aside class="col-sm-5 border-right">
                                        <article class="gallery-wrap"> 
                                            <div class="img-big-wrap">
                                                <div> <a href="#"><img src="${detail.imageLink}"></a></div>
                                        </div> <!-- slider-product.// -->
                                        <div class="img-small-wrap">
                                        </div> <!-- slider-nav.// -->
                                    </article> <!-- gallery-wrap .end// -->
                                </aside>
                                <aside class="col-sm-7">
                                    <h3 style="color: red">${message}</h3>
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${detail.name}</h3>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="currency">VND </span><span class="num">${detail.priceWithDot}</span>
                                            </span> 
                                        </p> <!-- price-detail-wrap .// -->
                                        <dl class="item-property">
                                            <dt>Description</dt>
                                            <dd><p>
                                                    ${detail.description}
                                                </p></dd>
                                        </dl>

                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="param param-inline">
                                                    <dt>Quantity: </dt>
                                                    <dd>
                                                        <select class="form-control form-control-sm" style="width:70px;" id="select_id">
                                                            <c:forEach begin="1" end="10" var="o">
                                                                <option> ${o} </option>
                                                            </c:forEach>
                                                        </select>
                                                    </dd>
                                                </dl>
                                            </div>
                                        </div>
                                        <hr>
                                        <button onclick="buy(${detail.id})" class="btn btn-lg btn-info text-uppercase"> Buy now </button>
                                        <button onclick="addCart2(${detail.id})" class="btn btn-lg btn-outline-info text-uppercase"> <i class="fas fa-shopping-cart"></i> Add to cart </button>
                                    </article>
                                </aside> 
                            </div>
                        </div> 

                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
                                            function addCart2(ProductID) {
            <c:if test="${sessionScope.acc != null}">
                                                var select_value = document.getElementById("select_id").value;
                                                //Sử dụng Ajax
                                                $.ajax({
                                                    url: "/Shoes/addMany",
                                                    type: "get", //send it through get method
                                                    data: {
                                                        ProductID: ProductID,
                                                        Quantity: select_value
                                                    },
                                                    success: function (message) {
                                                        alert(message);
                                                    }
                                                });
            </c:if>
            <c:if test="${sessionScope.acc == null}">
                                                location.href = "login";
            </c:if>
                                            }
                                            function buy(ProductID) {
                                                var select_value = document.getElementById("select_id").value;
                                                location.href = "buyNow?ProductID=" + ProductID + "&Quantity=" + select_value;
                                            }
        </script>  
    </body>
</html>