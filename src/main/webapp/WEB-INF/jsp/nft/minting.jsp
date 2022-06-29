<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JoCoding Club Minitng</title>
    <script src="/kks/config.js"></script>
    <script src="/kks/mintScript.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bignumber.js/8.0.2/bignumber.js" integrity="sha512-XQDG6H87RgMf+aNFwDPCUcFwUf3t9liyqN/2BkJ8KGixupbjPwQHNhypNA972mLIkg4KPo4lkGmqAf3yfDz8hw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>

<body>
<h2>MINTING INFO</h2>
<p id="blockNubmer">현재 블록: #00000000</p>
<p id="mintStartBlockNumber">민팅 시작 블록: #00000000</p>
<p id="mintPrice">민팅 가격: 0 KLAY</p>
<p id="mintLimitPerBlock">트랜잭션당 최대 수량: 0개</p>
<a href="https://scope.klaytn.com/">현재 블록넘버 확인하기1(Klaytnscope)</a><br>
<a href="https://klayswap.com/">현재 블록넘버 확인하기2(Klayswap)</a><br>
<hr/>
<h2>MY WALLET</h2>
<button onclick="connect()">카이카스 지갑연결</button>
<p id="myWallet">지갑주소: 연결되지 않음</p>
<p id="myKlay">잔액: 연결되지 않음</p>
<hr/>
<h2>MINT</h2>
<p id="mintCnt">0 / 0</p>
<label for="amount">민팅 수량</label>
<input type="number" id="amount" name="amount" min="1" max="3" value="1">
<button onclick="publicMint()">민팅</button>
<hr/>
<footer style="text-align: center;">Copyright &copy; 2022 <a href="https://www.youtube.com/channel/UCQNE2JmbasNYbjGAcuBiRRg">JoCoding</a> All rights reserved</footer>
<script>
    document.addEventListener("DOMContentLoaded", async function (event) {
        const accounts = await klaytn.enable();
        if (!accounts) {
            alert("KaiKas 확장 프로그램을 활성화 해주세요!");
        }
        //check_status();
    });
</script>
</body>

</html>