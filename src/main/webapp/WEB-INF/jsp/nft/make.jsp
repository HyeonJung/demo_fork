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
<script type="text/javascript" src="/jsonMaker/index.js"></script>
<section class="wrapper" id="holderContent">
    <!-- page start-->
    <div class="row">
        <div class="col-sm-12">
            <section class="card">
                <header class="card-header">
                    <span id="naming"><strong>NFT 이미지 만들기</strong></span><br/>
                    <span class="tools pull-right">
                <a href="javascript:;" class="fa fa-chevron-down"></a>
                <a href="javascript:;" class="fa fa-times"></a>
             </span>
                </header>
                <div class="card-body" style="text-align: center">
                    <button id="btnTutorial" type="button" class="btn btn-sm btn-info">Tutorial</button>
                </div>
            </section>
        </div>
    </div>
    <!-- page end-->
</section>
<script>
$(document).ready(function(){
   $('#btnTutorial').click(function(){
     tutorial();
   });
});
</script>

