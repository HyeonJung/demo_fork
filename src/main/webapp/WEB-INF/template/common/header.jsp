<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script src="/kks/config.js"></script>
<script src="/kks/mintScriptCustom.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bignumber.js/8.0.2/bignumber.js" integrity="sha512-XQDG6H87RgMf+aNFwDPCUcFwUf3t9liyqN/2BkJ8KGixupbjPwQHNhypNA972mLIkg4KPo4lkGmqAf3yfDz8hw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
                <a href="/home/nftinfo?nftcode=MG">
                    <i class="fa fa-dashboard"></i>
                    <span>NFT정보 - 제네시스몽즈</span>
                </a>
            </li>
            <li>
                <a href="/home/holderinfo?nftcode=TSO">
                    <i class="fa fa-user"></i>
                    <span>지갑 - 더 솔져스</span>
                </a>
            </li>
            <li>
                <a href="/home/holderinfo?nftcode=BMZ">
                    <i class="fa fa-user"></i>
                    <span>지갑  - 베이비몽즈</span>
                </a>
            </li>
            <li>
                <a href="/home/holderinfo?nftcode=MG">
                    <i class="fa fa-user"></i>
                    <span>지갑  - 제네시스몽즈</span>
                </a>
            </li>
            <li>
                <a href="/lass/lassinfo?nftcode=MG">
                    <i class="fa fa-user"></i>
                    <span>레저스테이션 정보</span>
                </a>
            </li>
        </ul>
        <div style="text-align: center;">
        <button class="btn btn-large btn-info" style="text-align: center; margin-bottom: 20px;" id="btnConnet">지갑연결</button>
            <div style="text-align: center;">
                보유 클레이 : <strong id="kalyAmount">0 Klay</strong>
            </div>
            <div  style="text-align: center;">
                지갑 주소 : <strong id="wallet">....</strong>
            </div>
        </div>
        <!-- sidebar menu end-->
    </div>
</aside>
<!--sidebar end-->
<script>
    $(document).ready(function (){
        console.log()
        $('#btnConnet').click(async function(){
            console.log('Connect');
   /*         const accounts = await klaytn.enable();
            if (!accounts) {
                alert("KaiKas 확장 프로그램을 활성화 해주세요!");
            }*/
            connect();
        });
    });

</script>
