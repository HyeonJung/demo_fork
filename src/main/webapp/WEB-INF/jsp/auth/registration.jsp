<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <link rel="shortcut icon" href="/img/favicon.png">

    <title>FlatLab - Flat & Responsive Bootstrap Admin Template</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />

    <!--toastr-->
    <link href="/assets/toastr-master/toastr.css" rel="stylesheet" type="text/css" />

    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet" />


    <!-- js placed at the end of the document so the pages load faster -->
    <script src="/js/jquery.js"></script>2
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script class="include" type="text/javascript" src="/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="/js/jquery.scrollTo.min.js"></script>
    <script src="/js/slidebars.min.js"></script>
    <script src="/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="/js/respond.min.js" ></script>

    <!--toastr-->
    <script src="/assets/toastr-master/toastr.js"></script>

    <!--common script for all pages-->
    <script src="/js/common-scripts.js"></script>
</head>

<body class="login-body">

<div class="container">

    <form class="form-signin" id="regForm" method="post" action="/api/user/regist">
        <h2 class="form-signin-heading">registration now</h2>
        <div class="login-wrap">
            <p>Enter your personal details below</p>
            <input type="text" class="form-control" placeholder="Name" id="name" name="name" autofocus>
<%--            <input type="text" class="form-control" placeholder="Address" autofocus>--%>
            <input type="text" class="form-control" placeholder="Email" id="email" name="email" autofocus>
<%--            <input type="text" class="form-control" placeholder="City/Town" autofocus>--%>
<%--            <div class="radios">
                <label class="label_radio col-lg-6 col-sm-6" for="radio-01">
                    <input name="sample-radio" id="radio-01" value="1" type="radio" checked /> Male
                </label>
                <label class="label_radio col-lg-6 col-sm-6" for="radio-02">
                    <input name="sample-radio" id="radio-02" value="1" type="radio" /> Female
                </label>
            </div>--%>

            <p> Enter your account details below</p>
            <input type="text" class="form-control" placeholder="User ID" id="userid" name="userid" autofocus>
            <input type="password" class="form-control" placeholder="Password" id="password" name="password">
            <input type="password" class="form-control" placeholder="Re-type Password" id="re_password" name="re_password">
<%--            <label class="checkbox">
                <input type="checkbox" value="agree this condition"> I agree to the Terms of Service and Privacy Policy
            </label>--%>
            <button class="btn btn-lg btn-login btn-block" type="button" onclick="cmdReg();">회원가입</button>
            <div class="registration">
                Already Registered.
                <a class="" href="/auth/login">
                    Login
                </a>
            </div>
        </div>

    </form>

</div>
<script>
    $(function () {

    });
    jQuery.fn.serializeObject = function() {
        var obj = null;
        try {
            if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
                var arr = this.serializeArray();
                if(arr){ obj = {};
                    jQuery.each(arr, function() {
                        obj[this.name] = this.value; });
                }
            }
        }catch(e) {
            alert(e.message);
        }finally {}
        return obj;
    }

    function cmdReg() {
        var msg ="";
        if($('#password').val() !== $('#re_password').val() || $('#password').val() == ""){
            msg="패스워드를 다시한번 확인해주시기 바랍니다";
        }

        if($('#userid').val() == '' || $('#userid').val() == null )
            msg = "UserID는 필수 입니다.";

        if($('#name').val() == '' || $('#name').val() == null )
            msg = "Name은 필수 입니다.";


        if($('#email').val() == '' || $('#email').val() == null )
            msg = "Email은 필수 입니다.";

        if (msg !== "") {
            toastr.options = {
                "closeButton": true,
                "debug": false,
                "progressBar": false,
                "positionClass": "toast-bottom-right",
                "onclick": null,
                "showDuration": "3000",
                "hideDuration": "10000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }

            toastr['error'](msg, "회원가입오류"); // Wire up an event handler to a button in the toast, if it exists
        }

        const serializedValues = $('#regForm').serializeObject();

        $.ajax({
            type : 'post',
            url : '/api/user/regist',
            data : JSON.stringify(serializedValues),
            contentType : "application/json; charset=utf-8",
            dataType : 'json',
            error: function(xhr, status, error){
                alert(status);
                alert(JSON.stringify(xhr));
            },
            success : function(json){
                alert(JSON.stringify(json));
                if(json.result_code =="OK"){
                    alert('회원가입성공');
                }
            },
        });
    };
</script>

</body>
</html>
