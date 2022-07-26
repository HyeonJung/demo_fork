<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<style>
    .dt-buttons{
        margin-left: 50%;
    }
</style>

<!--dynamic table-->
<link href="/assets/advanced-datatable/media/css/demo_page.css" rel="stylesheet" />
<link href="/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
<link rel="stylesheet" href="/assets/data-tables/DT_bootstrap.css" />
<!-- Custom styles for this template -->
<link href="/css/style.css" rel="stylesheet">
<link href="/css/style-responsive.css" rel="stylesheet" />
<link href="https://cdn.datatables.net/buttons/2.2.3/css/buttons.dataTables.min.css" rel="stylesheet" />

<!--dynamic table initialization -->
<script type="text/javascript" src="/assets/advanced-datatable/media/js/datatables-1.12.1.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/2.2.3/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="/assets/data-tables/DT_bootstrap.js"></script>

<section class="wrapper">
    <input type='hidden' id="code" value='${code}'/>
    <!-- page start-->
    <div class="row">
        <div class="col-sm-12">
            <section class="card">
                <header class="card-header">
                    <span id="naming">The Soldiers NFT 리스트</span> <br/>
                    <font color="red"> 최신여부 매일 24:00 기준 업데이트</font>
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
                                <th>No</th>
                                <th>NFT ID</th>
                                <th>계급</th>
                                <th class="hidden-phone">지갑주소</th>
                                <th class="hidden-phone">스테이킹 여부</th>
                                <th>최신 여부</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tfoot>
                            <tr>
                                <th>No</th>
                                <th>NFT ID</th>
                                <th>계급</th>
                                <th>지갑주소</th>
                                <th>스테이킹 여부</th>
                                <th>최신 여부</th>
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

<script>

    $(document).ready(function() {

        if($('#code').val()=="TSO"){
            $('#naming').text("The Soldiers NFT 리스트")
        } else if($('#code').val()=="BMZ"){
            $('#naming').text("베이비몽즈 NFT 리스트")
        } else if($('#code').val()=="MG"){
            $('#naming').text("제네시스몽즈 홀더 리스트")
        }

        var param ={};
        param["nft_code"]=$('#code').val();
        var data={};
        data["data"]=param;

        $('#hidden-table-info tfoot th').each(function () {
            var title = $(this).text();
            console.log(title);
            if (title != 'No' )
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
                dataType:'json',
                url:"/api/tokeninfo/getalltokeninfolist",
                contentType:"application/json; charset=utf-8",
                type:"POST",
                data: function(){
                    return JSON.stringify(data);
                }
            },
            autoWidth:false,

            fnRowCallback:function(nRow, aData, iDisplayIndex){
                $(nRow).addClass('gradeA')
            },
            "aoColumnDefs": [
                { "bSortable": false, bSearchable: false, "aTargets": [ 0 ], width:"10px" }, //정렬 사용안함
                { "aTargets": [ 1 ], },
                { "aTargets": [ 2 ] },
                {  "aTargets": [ 3 ] },
                { "aTargets": [ 4 ] }, //정렬 사용안함
                {  "aTargets": [ 5 ] } //정렬 사용안함
            ],
            aoColumns:[
                {mData: "rownum"},
                {mData: "token_id", width:"15%"},
                {mData: "level"},
                {mData: "address"},
                {mData: "staking_status", width:"15%"},
                {mData: "get_status"}
            ],
            "aaSorting": [[1, 'asc']],
            language : {
                processing:"loading",
                lengthMenu : '<select>'+
                    '<option value="10">10</option>'+
                    '<option value="25">25</option>'+
                    '<option value="50">50</option>'+
                    '<option value="100">100</option>'+
                    '</select> 개씩',
                "info":"_START_ 부터 _END_ 까지 총 _TOTAL_ 건"
            },
            "dom": 'lBfrtip',
            buttons: [
                {
                    extend: 'excel',
                    text: '엑셀다운'
                }
            ]
        });

    } );

</script>

