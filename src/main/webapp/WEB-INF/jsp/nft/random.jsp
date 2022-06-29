<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <style>
        #slideshow {
            margin: 50px auto;
            position: relative;
            width: 100%;
            height: 240px;
            padding: 10px;
        }

        #slideshow > div {
            position: absolute;
            width: 100%;
            top: 10px;
            left: 10px;
            right: 10px;
            bottom: 10px;
        }

        p, span {
            font-family: "Montserrat", Sans-Serif;
            font-weight: bold;
            font-size: 50px;
            color: white;
            margin-top: 0px;
            margin-bottom: 0px;
        }
    </style>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jeep Random Machine</title>
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">


    <!-- js placed at the end of the document so the pages load faster -->
    <script src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery.cookie.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script class="include" type="text/javascript" src="/js/jquery.dcjqaccordion.2.7.js"></script>

    <script src="/js/jquery.scrollTo.min.js"></script>
    <script src="/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="/js/respond.min.js"></script>
    <script src="/js/slidebars.min.js"></script>

</head>

<body style="background-size: cover; background-image: url('/img/soldiers/soldiersBG.png')">
<section  id="Content">
    <div style="text-align: center;">
        <%--    <video style="width: 100%;" playsinline="" autoplay="" loop="" >
                <source src="https://soldiers.team/main-video.mp4" type="video/mp4">
            </video>--%>
        <img style="width: 40%;text-align: center;" src="https://soldiers.team/static/media/logo.svg">
    </div>
    <div style="text-align: center;">
        <p style="text-align: center;">Jeep 추첨</p>
    </div>

    <div id="slideshow">
        <div class="subImg" style="text-align: center">
            <img style="height: 200px;" src="https://soldiers.team/static/media/a6.png">
        </div>
        <div class="subImg" style="text-align: center">
            <img style="height: 200px;" src="https://soldiers.team/static/media/a7.png">
        </div>
        <div class="subImg" style="text-align: center">
            <img style="height: 200px;" src="https://soldiers.team/static/media/a8.png">
        </div>
        <div id="jeepImg" style="text-align: center; display: none">
            <img style="height: 200px; " src="https://lh3.googleusercontent.com/L_NfQcufu2jnrZM_wt2z-WMlpnVa4cqUWmhCq1JfG_kVRzeboNEN3j2-mAeJes9deFYyEiaMAKq0XinBLmUl0Iw48q4XYqy1gVsBiQ=w379">
        </div>
    </div>
    <div style="text-align: center;">
        <span id="head" style="text-align: center;">#</span> <span id="number" style="text-align: center;">999</span><br/>
        <span id="resultMsg" style="text-align: center;"></span>

    </div>
    <div style="margin-top: 20px; text-align: center;">
        <button id="btnDraw" style="text-align: center;" class="btn btn-large btn-danger">추첨하기</button>
<%--
        <button id="btnStop" style="text-align: center;" class="btn btn-large btn-danger">정지</button>
--%>
    </div>
    <div style="text-align: center;">
        <input id="txtStart" type="text"  class="input-medium" style="text-align: center; height: 50px;width: 90px;font-size:45px;"><span> 부터</span>
        <input id="txtEnd" type="text"  class="input-medium" style="text-align: center; height: 50px;width: 90px;font-size:45px;"><span> 까지</span>
    </div>
</section>
</body>
<script>
    var interval = 2000;
    $(document).ready(function () {
        $('#btnDraw').click(function () {
            fnDraw();
        });
        $('#btnStop').click(function () {
            stop();
        });
        $('#jeepImg').hide();
    });

    $("#slideshow > div:gt(0)").hide();

    var image = setInterval(function () {
        $('#slideshow > div:first')
            .fadeOut(1000)
            .next()
            .fadeIn(1000)
            .end()
            .appendTo('#slideshow');
    }, interval);

    var isStart = false;
    var draw = null
    var num = null;

    function fnDraw() {
        if (!isStart) {

            var start = Number($('#txtStart').val());
            var end = Number($('#txtEnd').val());

            if(start == null || start == '' || end == null || end == '' ){
                alert('시작갑과 끝값은 필수입니다.');
                return null;
            }

            if(start > end){
                alert('시작값은 끝값보다 클 수 없습니다.');
                return null;
            }

            $('#head').text('행운의 당첨번호는 ??');
            $('#resultMsg').text('');

            draw = setInterval(function () {
                num = Math.floor(Math.random() * 999) + 1;
                $('#number').text(('000' + num).slice(-3));
                console.log("await..")
            }, 1)
            isStart = true;

            $('.subImg').fadeIn(1000);
            $('#jeepImg').css('display', 'none');
            $('#jeepImg').css('display', 'none');



            $.ajax({
               'url':'/api/randomDraw?start=' + start + '&end=' + end,
               'method':'GET',
               'success':function(data){
                   console.log(data)
                   stop(data);
               },
               'error':function(error){
                   alert(error);
               }
            });
        }
    }

    function stop(data) {
        console.log("stopped")
        clearInterval(draw)
        $('#number').text('축하드립니다 !!!');
        $('#head').text('(경축)')
        $('#resultMsg').text(' 행운의 Jeep 당첨번호 :  ' + data)

        isStart = false;


        clearInterval(image);

        $('.subImg').css('display', 'none');
        $('#jeepImg').css('display', 'block');
        $('#jeepImg').fadeIn(1000);

    }
</script>
</html>