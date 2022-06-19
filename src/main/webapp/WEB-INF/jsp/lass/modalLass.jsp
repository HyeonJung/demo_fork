<%--
  Created by IntelliJ IDEA.
  User: daeyong
  Date: 2022/06/18
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="lassBody">

</div>
<script>
    $(document).ready(function(){
        $('#lassBody').append($('#template').html());

        var obj = {};
        obj['link'] = $('.link').val();

        $.ajax({
            dataType:'json',
            contentType:"application/json; charset=utf-8",
            type:"POST",
            url : '/api/Lass/getHouseInfo',
            data: JSON.stringify(obj),
            async:false,
            error: function(xhr, status, error){
                alert(status);
            },
            success : function(data){
                //$('.m_naming').text(data.loc_name);
            },
        });
    });


</script>
<script type="mTemplate" id="template"/>
<section class="wrapper">
    <input type="hidden" class="link" value="${link}" />
    <!-- page start-->
    <div class="row">
        <div class="col-sm-12">
            <section class="card">
                <header class="card-header">
                    <span><strong class="m_naming">${loc}</strong></span><br/>
                    <span class="tools pull-right">
                <a href="javascript:;" class="fa fa-chevron-down"></a>
                <a href="javascript:;" class="fa fa-times"></a>
             </span>
                </header>
            </section>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <section class="card">
                <header class="card-header">
                    <span id="type"><strong>${name}</strong></span><br/>
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
                                <th>
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
</script>