<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!--header start-->
<header class="header white-bg">
    <div class="sidebar-toggle-box">
        <i class="fa fa-bars"></i>
    </div>
    <!--logo start-->
    <img style="width:100px; height: 50px;" src="/img/logo.jpeg"/>
    <!--logo end-->
</header>
<!--header end-->
<!--sidebar start-->
<aside>
    <div id="sidebar"  class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
            <li>
                <a href="/home/sample1">
                    <i class="fa fa-dashboard"></i>
                    <span>솔져스 코인 계산기</span>
                </a>
            </li>
            <li>
                <a href="/home/nftinfo?nftcode=TSO">
                    <i class="fa fa-dashboard"></i>
                    <span>NFT정보 - 더 솔져스</span>
                </a>
            </li>
            <li>
                <a href="/home/nftinfo?nftcode=BMZ">
                    <i class="fa fa-dashboard"></i>
                    <span>NFT정보 - 베이비몽즈</span>
                </a>
            </li>
            <li>
                <a href="/home/holderinfo?nftcode=TSO">
                    <i class="fa fa-user"></i>
                    <span>지갑(홀더&코인) 정보 - 더 솔져스</span>
                </a>
            </li>
            <li>
                <a href="/home/holderinfo?nftcode=BMZ">
                    <i class="fa fa-user"></i>
                    <span>지갑(홀더&코인) 정보 - 베이비몽즈</span>
                </a>
            </li>
        </ul>
        <!-- sidebar menu end-->
    </div>
</aside>
<!--sidebar end-->
<script>


</script>
