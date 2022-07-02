<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <link rel="shortcut icon" href="/img/favicon.png">

    <title>더 솔져스 정보페이지</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!--right slidebar-->
    <link href="/css/slidebars.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet" />
    <link href="/css/soldiers/loading.css" rel="stylesheet"/>

</head>

<!-- js placed at the end of the document so the pages load faster -->
<script src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script>
<script class="include" type="text/javascript" src="/js/jquery.dcjqaccordion.2.7.js"></script>

<script src="/js/jquery.scrollTo.min.js"></script>
<script src="/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="/js/respond.min.js"></script>
<script src="/js/slidebars.min.js"></script>

<body>
<!-- 로딩 -->
<div class="loadingWrap">
    <div class="loadingArea">
        <span class="icon xposDragon1"></span>
        <span class="icon xposDragon2"></span>
        <span class="icon xposDragon3"></span>
        <span class="icon xposDragon4"></span>
    </div>
</div>
<!-- //로딩 -->
<section id="container" class="">
    <tiles:insertAttribute name="header"/>

    <!--main content start-->
    <section id="main-content">
            <tiles:insertAttribute name="contents"/>
    </section>
    <!--main content end-->

    <tiles:insertAttribute name="footer"/>

</section>

</body>
<script>
    // 로딩
    (function($) {
        $.ajaxSetup({
            beforeSend: function(xhr) {
                xhr.setRequestHeader("AJAX", "true");
            },
            error: function(xhr, status, err) {
                if (xhr.status == 403) {
                    location.href = contextPath + "/login?redirect=true";
                }
                else {
                    console.log("error: " + xhr.status);
                }
            }
        });

        $(document).ajaxStart(function() {
            $(".loadingWrap").show();

        }).ajaxStop(function() {
            $(".loadingWrap").hide();
        });
    })(jQuery);
</script>
<!--common script for all pages-->
<script src="/js/common-scripts.js"></script>
</html>