<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    환율 - <input type='text' id="txtBasePrice" value='<c:out value="${unit.price}"/>' /> <br/>
    FP - <fmt:formatNumber value="${unit.soldiersFP}"/><br/>
    하루 채굴금액(설정) - <b id="dayTotalAmout"></b><br/>
    한달 채굴금액(설정) - <b id="monthTotalAmout"></b><br/>
    하루 채굴수량(TSS) - <b id="dayTSSTotalQty"></b>, 하루 채굴수량(TSG) - <b id="dayTSGTotalQty"></b> <br/>
    한달 채굴수량(TSS) - <b id="monthTSSTotalQty"></b>, 한달 채굴수량(TSG) - <b id="monthTSGTotalQty"></b> <br/>
    <input type="hidden" id="hdnBasePrice" value='<c:out value="${unit.price}"/>' />
</header>
<table class="table table-striped table-advance table-hover">
    <thead>
    <tr>
        <th><i class="fa"></i></th>
        <th><i class="fa fa-upload"></i>수량</th>
        <th class=""><i class="fa fa-gift"></i>채굴량</th>
        <th>보유수량(빈칸: 1)</th>
        <th><i class=" fa fa-money"></i>채굴금액 (1개기준-일)</th>
        <th><i class=" fa fa-money"></i>채굴금액(일)</th>
        <th><i class=" fa fa-money"></i>채굴금액(월)</th>
        <th>
            <i class=" fa fa-money"></i>적정FP가(선미기준)<br/>
            1개 채굴금액(일) - <fmt:formatNumber value="${unit.sunmiOnePrice}"/> <br/>
            FP - <fmt:formatNumber value="${unit.sunmiFP}"/>
        </th>
        <th>
            <i class=" fa fa-money"></i>적정FP가(메콩기준)<br/>
            1개 채굴금액(일) - <fmt:formatNumber value="${unit.metaKongsOnePrice}"/> <br/>
            FP - <fmt:formatNumber value="${unit.metakongsFP}"/>
        </th>
        <th>
            <i class=" fa fa-money"></i>적정FP가(지릴라기준)<br/>
            1개 채굴금액(일) - <fmt:formatNumber value="${unit.grillaOnePrice}"/> <br/>
            FP - <fmt:formatNumber value="${unit.grillaFP}"/>
        </th>
        <th></th>
    </tr>
    </thead>
    <tbody id="tbody">
    <c:forEach var="unit" items="${unit.soldiers}" varStatus="status">
        <tr>
            <td>${unit.name}</td>
            <td><fmt:formatNumber value="${unit.qty}"/></td>
            <td><fmt:formatNumber value="${unit.getQty}"/></td>
            <td><input type="text" class="input-small" numberOnly style="width:40px;" id="txtSoldierQty${status.index}" value="0"
                       onkeyup="DayCalc(${unit.day_item}, ${unit.month_item}, ${status.index}, ${unit.getQty}, this)"></td>
            <td id="tdFixItem${status.index}"><fmt:formatNumber value="${unit.day_item}"/></td>
            <td id="tdDayItem${status.index}"><fmt:formatNumber value="${unit.day_item}"/></td>
            <td id="tdMonthItem${status.index}"><fmt:formatNumber value="${unit.month_item}"/></td>
            <td><fmt:formatNumber value="${unit.sunmi}"/></td>
            <td><fmt:formatNumber value="${unit.metakongs}"/></td>
            <td><fmt:formatNumber value="${unit.grilla}"/></td>
            <td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</section>


<script>
    $(function(){
        setCount();
        $("input:text[numberOnly]").on("keyup", function() {
            $(this).val($(this).val().replace(/[^0-9]/g,"0"));
            if($(this).val() != '0')
            $(this).val($(this).val().replace(/[^0-9]/g,"0").replace(/(^0+)/g,""));
        });

        $("#txtBasePrice").on("keyup", function() {
            setCount();
        });
    });


    function priceToString(price) {
        return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    }

    function DayCalc(dayPrice, monthPrice, index, getQty, qty) {

        console.log(dayPrice);
        var DayItem = 1 * dayPrice;
        var MonthItem = 1 * monthPrice;
        if (qty.value != '1' && qty.value != '' && qty.value != '0' ){
            DayItem = qty.value * dayPrice;
            MonthItem = qty.value * monthPrice;
        }
        $('#tdDayItem' + index).text(priceToString(DayItem));
        $('#tdMonthItem' + index).text(priceToString(MonthItem));
        $.cookie(('DayPrice' + index), dayPrice, {expires: 365});
        $.cookie(('DayQty' + index), qty.value, {expires: 365});
        $.cookie(('DayGetQty' + index), getQty, {expires: 365});
        setCount();
    }

    function setCount(){
        var totalAmount=0;
        var totalQty = 0;
        for (var i = 0; i < 19; i++) {


            var dayPrice = $('#tdFixItem' + i).text().replaceAll(",","")
            var qty = $.cookie(('DayGetQty' + i));
            if($("#hdnBasePrice").val() != $("#txtBasePrice").val())
                dayPrice = $("#txtBasePrice").val() * qty;

            if ($.cookie(('DayQty' + i)) != null && $.cookie(('DayQty' + i)) != '0') {
                var DayItem = $.cookie(('DayQty' + i)) * dayPrice;
                var MonthItem = $.cookie(('DayQty' + i)) * dayPrice * 30;
                $('#tdDayItem' + i).text(priceToString((DayItem)));
                $('#tdMonthItem' + i).text(priceToString((MonthItem)));
                $('#txtSoldierQty' + i).val(priceToString($.cookie(('DayQty' + i))));
                totalAmount =  totalAmount + DayItem;
                if( $.cookie(('DayGetQty' + i)) != null && $.cookie(('DayGetQty' + i)) != '0')
                totalQty += Number($.cookie(('DayQty' + i))) * Number($.cookie(('DayGetQty' + i)));

            }
        }
        $('#dayTotalAmout').text(priceToString((totalAmount)));
        $('#monthTotalAmout').text(priceToString((totalAmount * 30))); //솔져스에 저장은 하지만 굳이 쿠키에 저장할 필요 없음
        $('#dayTSSTotalQty').text(priceToString((totalQty * 2)));
        $('#monthTSSTotalQty').text(priceToString((totalQty * 30 * 2)));
        $('#dayTSGTotalQty').text(priceToString((totalQty)));
        $('#monthTSGTotalQty').text(priceToString((totalQty * 30)));

    }
</script>
</body>
</html>