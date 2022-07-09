<%--
  Created by IntelliJ IDEA.
  User: daeyong
  Date: 2022/06/20
  Time: 9:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Wallet</title>
    <script src="/kks/wallet/config.js"></script>
    <script src="/kks/wallet/script.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bignumber.js/8.0.2/bignumber.js"
            integrity="sha512-XQDG6H87RgMf+aNFwDPCUcFwUf3t9liyqN/2BkJ8KGixupbjPwQHNhypNA972mLIkg4KPo4lkGmqAf3yfDz8hw=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/caver-js/1.8.2/caver.min.js"></script>
    <script src="/js/jquery.js"></script>

</head>
<body>
<h2>MY WALLET</h2>
<button onclick="connect()">카이카스 지갑연결</button>
<p id="myWallet">지갑주소: 연결되지 않음</p>
<p id="myKlay">잔액: 연결되지 않음</p>
<hr/>
<h2>코인전송</h2>
<p id="mintCnt">0 / 0</p>
<div>
    <label for="amount">전송</label>
    <input type="number" id="amount" name="amount" value="1">
</div>
<div>
    <label for="amount">주소</label>
    <input type="text" class="input-medium" id="rAddr" name="rAddr"/>
</div>
<button onclick="transfer()">전송</button>
<hr/>
<h2>NFT 전송</h2>

<button onclick="getContract()">조회</button>

<footer style="text-align: center;">Copyright &copy; 2022 BigDragon All rights reserved
</footer>
<script>
    document.addEventListener("DOMContentLoaded", async function (event) {
        /*    const accounts = await klaytn.enable();
            if (!accounts) {
                alert("KaiKas 확장 프로그램을 활성화 해주세요!");
            }*/
        //connect();
    });
</script>
</body>

</html>