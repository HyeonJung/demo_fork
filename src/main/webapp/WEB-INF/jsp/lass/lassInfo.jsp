<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!--dynamic table-->
<link href="/assets/advanced-datatable/media/css/demo_page.css" rel="stylesheet" />
<link href="/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
<link rel="stylesheet" href="/assets/data-tables/DT_bootstrap.css" />
<!-- Custom styles for this template -->
<link href="/css/style.css" rel="stylesheet">
<link href="https://cdn.datatables.net/buttons/2.2.3/css/buttons.dataTables.min.css" rel="stylesheet" />
<link href="/css/style-responsive.css" rel="stylesheet" />
<section class="wrapper" id="holderContent">
    <!-- page start-->
    <div class="row">
        <div class="col-sm-12">
            <section class="card">
                <header class="card-header">
                    <span id="naming"><strong>레저스테이션 정보</strong></span><br/>
                    <span class="tools pull-right">
                <a href="javascript:;" class="fa fa-chevron-down"></a>
                <a href="javascript:;" class="fa fa-times"></a>
             </span>
                </header>
                <div class="card-body" style="text-align: center">
                    <button type="button" class="btn btn-sm btn-info">인천시</button>
                    <button type="button" class="btn btn-sm btn-info">경기도</button>
                    <button type="button" class="btn btn-sm btn-info">경상남도</button>
                    <button type="button" class="btn btn-sm btn-info">경상북도</button>
                    <button type="button" class="btn btn-sm btn-info">강원도</button>
                    <button type="button" class="btn btn-sm btn-info">충청남도</button>
                    <button type="button" class="btn btn-sm btn-info">충청북도</button>
                    <button type="button" class="btn btn-sm btn-info">전라남도</button>
                    <button type="button" class="btn btn-sm btn-info">전라북도</button>
                    <button type="button" class="btn btn-sm btn-info">제주도</button>
                </div>
            </section>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <section class="card">
                <header class="card-header">
                    <span id="type" ><strong>인천시</strong></span><br/>
                    <span class="tools pull-right">
                <a href="javascript:;" class="fa fa-chevron-down"></a>
                <a href="javascript:;" class="fa fa-times"></a>
             </span>
                </header>
                <div class="card-body">
                    <div class="adv-table">
                        <table class="display table table-bordered" id="hidden-table-info">
                            <thead>
                            <tr>
                                <th>지역</th>
                                <th>펜션명</th>
                                <th>이동</th>

                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>지역</th>
                                <th>펜션명</th>
                                <th><button type="button" class="btn btn-sm btn-info">이동</button>
                                </th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <!-- page end-->
</section>
<!--dynamic table initialization -->
<script type="text/javascript" src="/assets/advanced-datatable/media/js/datatables-1.12.1.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/2.2.3/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="/assets/data-tables/DT_bootstrap.js"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/hammerjs@2.0.8"></script>
<script src="/js/chartjs-plugin-zoom.js"></script>
<script>
    function sendform(sendValueList, loginUrl){
        $('#scmform').remove();
        $form = $("<form name='scmform' id='scmform' method='post'></form>");
        $(document.body).append($form);
        scmform.target ="_aa";
        scmform.action = loginUrl;
        var len = sendValueList.length;
        for(var i=0; i<len; i++){
            var keyValue = sendValueList[i];
            var key = keyValue[0];
            var value = keyValue[1];
            var $hidden = $("<input type='hidden' name='" + key + "' value='" + value + "'/>");
            $('#scmform').append($hidden);
        }
        $('#scmform').submit();
    }

    function setParam(id, pw){
        var sendvalueList =[];
        sendvalueList.push(["LoginAction","login"]);
        sendvalueList.push(["memId",id]);
        sendvalueList.push(["memPw",pw]);
        return sendvalueList
    }

    function moceredirectUrl(url){
        var target_name ="_aa";
        _aa = window.open("about:blank", target_name);
        _aa.location.href = url;
    }

    $(document).ready(function() {


        $('#hidden-table-info tfoot th').each(function () {
            var title = $(this).text();
            if (title != 'No' && title != 'NFT 보유 수량' && title != 'TSS 코인' && title != 'TSG 코인')
                $(this).html('<input type="text" placeholder="Search ' + title + '" />');
            else{
                $(this).html('');
            }
        });

        var oTable = $('#hidden-table-info').dataTable( {
            initComplete: function () {
                // Apply the search
                this.api()
                    .columns()
                    .every(function () {
                        var that = this;

                        $('input', this.footer()).on('keyup change clear', function () {
                            if (that.search() !== this.value) {
                                that.search(this.value).draw();
                            }
                        });

                        $('#hidden-table-info_filter').hide();
                    });
            },
            bProcessing:true,
            ajax:{
                url:"/api/transaction/test",
                type:"GET"
            },
            autoWidth:false,

            fnRowCallback:function(nRow, aData, iDisplayIndex){
                $(nRow).addClass('gradeA')
            },
            "aoColumnDefs": [
                { "aTargets": [ 0 ]},
                {  "aTargets": [ 1 ]},
                { "aTargets": [ 2 ]}, //정렬 사용안함
            ],
            columns:[
                {data: "loc", width:"15%"},
                {data: "name"},
                {data: "link",
                    render: function (data, type, row, mete)
                    {
                           const url ="";
                        return '<button class="btn btn-sm btn-info" onclick="move(\'http://www.lsstation.co.kr/member_login_ok.php\', \'' + data + '\')")>이동</button>';
                    }
                },
            ],
            "aaSorting": [[2, 'desc']],
            language : {
                processing:"loading",
                lengthMenu : '<select>'+
                    '<option value="10">10</option>'+
                    '<option value="25">25</option>'+
                    '<option value="50">50</option>'+
                    '<option value="100">100</option>'+
                    '</select> 개씩',
                "info":"_START_ 부터 _END_ 까지 총 _TOTAL_ 건"
            }
        });

    } );

    function move(url, moveurl){
            sendform(setParam('${id}', '${pw}'), url);
            setTimeout("moceredirectUrl('" + moveurl + "')",500);
    }

</script>

