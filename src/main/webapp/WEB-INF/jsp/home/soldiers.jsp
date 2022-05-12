<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <link rel="shortcut icon" href="/img/favicon.png">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!--right slidebar-->
    <link href="/css/slidebars.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet"/>
</head>

<body>

                        <header id="coin-title" class="card-header">
                            Coin - <c:out value="${unit.name}"/> <br/>
                            환율 - <c:out value="${unit.price}"/>
                        </header>
                        <table class="table table-striped table-advance table-hover">
                            <thead>
                            <tr>
                                <th><i class="fa"></i></th>
                                <th><i class="fa fa-upload"></i>수량</th>
                                <th class=""><i class="fa fa-gift"></i>채굴량</th>
                                <th><i class=" fa fa-money"></i>일일보상</th>
                                <th><i class=" fa fa-money"></i>월보상</th>
                                <th><i class=" fa fa-money"></i>깡통가(선미기준)</th>
                                <th><i class=" fa fa-money"></i>깡통가(메콩)</th>
                                <th><i class=" fa fa-money"></i>깡통가(지릴)</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody id="tbody">
                            <c:forEach var="unit" items="${unit.soldiers}">
                                <tr>
                                    <td>${unit.name}</td>
                                    <td><fmt:formatNumber value="${unit.qty}" /></td>
                                    <td><fmt:formatNumber value="${unit.getQty}" /></td>
                                    <td><fmt:formatNumber value="${unit.day_item}" /></td>
                                    <td><fmt:formatNumber value="${unit.month_item}" /></td>
                                    <td><fmt:formatNumber value="${unit.sunmi}" /></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>


</section>

<!-- js placed at the end of the document so the pages load faster -->
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script>
<script class="include" type="text/javascript" src="/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="/js/jquery.scrollTo.min.js"></script>
<script src="/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="/js/respond.min.js"></script>

<script src="/js/slidebars.min.js"></script>

<!--common script for all pages-->
<script src="/js/common-scripts.js"></script>
<script>
    $(function () {
    });
    function priceToString(price) {
        return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    }
</script>
</body>
</html>