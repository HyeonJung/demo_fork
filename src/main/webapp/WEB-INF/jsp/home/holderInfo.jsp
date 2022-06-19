<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<link href="https://cdn.datatables.net/buttons/2.2.3/css/buttons.dataTables.min.css" rel="stylesheet" />
<link href="/css/style-responsive.css" rel="stylesheet" />


<section class="wrapper" id="holderContent">
    <input type='hidden' id="code" value='${code}'/>
    <!-- page start-->
    <div class="row">
        <div class="col-sm-12">
            <section class="card">
                <header class="card-header">
                    <span id="naming">The Soldiers 홀더 리스트</span><br/>
                    <font color="red"> 매일 24:00 기준 업데이트</font>

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
                                <th>지갑 주소</th>
                                <th>NFT 보유 수량</th>
                                <th><span id="mainCoin"></span></th>
                                <th><span id="subCoin"></span></th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>No</th>
                                <th>지갑 주소</th>
                                <th>NFT 보유 수량</th>
                                <th>TSS 코인</th>
                                <th>TSG 코인</th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <section class="card">
                <div>
                    <canvas id="myChart"></canvas>
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
    var myChart;
    var config;
    function priceToString(price) {
        return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    }

    $(document).ready(function() {

        if($('#code').val()=="TSO"){
            $('#naming').text("The Soldiers 홀더 리스트")
            $('#mainCoin').text("TSS코인");
            $('#subCoin').text("TSG코인");
        } else if($('#code').val()=="BMZ"){
            $('#naming').text("베이비몽즈 홀더 리스트")
            $('#mainCoin').text("BBA코인");
            $('#subCoin').text("준비중");
        } else if($('#code').val()=="MG"){
            $('#naming').text("제네시스몽즈 홀더 리스트");
            $('#mainCoin').text("준비중");
            $('#subCoin').text("준비중");
        }

        setChart();

        const param ={};
        param["nft_code"]=$('#code').val();
        const data={};
        data["data"]=param;
        $('#hidden-table-info tfoot th').each(function () {
            var title = $(this).text();
            console.log(title);
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
                dataType:'json',
                url:"/api/tokeninfo/getholderlist",
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
                { bSearchable: false, "aTargets": [ 2 ], render: $.fn.dataTable.render.number(',', '.', 0, '') },
                { bSearchable: false,  "aTargets": [ 3 ],render: $.fn.dataTable.render.number(',', '.', 0, '') },
                { bSearchable: false, "aTargets": [ 4 ], render: $.fn.dataTable.render.number(',', '.', 0, '')  }, //정렬 사용안함
            ],
            columns:[
                {data: "rownum"},
                {data: "address", width:"15%"},
                {data: "amount",
                    render: function (data, type, row, mete)
                    {
                        if(type == 'display')
                            if (row.calc_amount.toString().indexOf('-') == 0)
                                return priceToString(row.amount) + '( <i style="color:blue; "class="fa fa-caret-down"></i> ' + priceToString(row.calc_amount) + ')';
                            else
                                return priceToString(row.amount) + '( <i style="color:red; "class="fa fa-caret-up"></i>' + priceToString(row.calc_amount) + ')';
                        else
                            return data;
                    }
                },
                {data: "amount_held",
                    render: function (data, type, row, mete)
                    {
                        if($('#code').val()=="MG"){
                            return "준비중";
                        }
                        if(type == 'display')
                            if (row.calc_tss_coin.toString().indexOf('-') == 0)
                                return priceToString(row.amount_held) + '( <i style="color:blue; "class="fa fa-caret-down"></i> ' + priceToString(row.calc_tss_coin) + ')';
                             else
                                return priceToString(row.amount_held) + '( <i style="color:red; "class="fa fa-caret-up"></i> ' + priceToString(row.calc_tss_coin) + ')';
                        else
                            return data;
                    }
                },
                {data: "tsg_amount_held"}
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

   function setChart(){
       $.ajax({
          url:'/api/transaction/readecttransaction?nftCode=TSO',
          type:'get',
           async:false,
           success:function(data){
              let dateList =[];
              let dataList =[];
              for(let i=0; i<data.length; i++){
                  dateList.push(data[i].date);
                  dataList.push(data[i].amount);
              }

               const chartData = {
                   labels: dateList,
                   datasets: [{
                       label: '더 솔저스',
                       backgroundColor: 'rgb(255, 99, 132)',
                       borderColor: 'rgb(255, 99, 132)',
                       data: dataList
                   }]
               };
               config = {
                   type: 'line',
                   data: chartData,
                   options: {
                   }
               };

               myChart = new Chart(
                   document.getElementById('myChart'),
                   config
               );

           },
           error:function(data){
              alert(data);
           }
       });


       $.ajax({
           url:'/api/transaction/readecttransaction?nftCode=BMZ',
           type:'get',
           async:false,
           success:function(data){
               let dateList =[];
               let dataList =[];
               for(let i=0; i<data.length; i++){
                   dateList.push(data[i].date);
                   dataList.push(data[i].amount);
               }

               var color1 = Math.floor(Math.random() * 256);
               var color2 = Math.floor(Math.random() * 256);
               var color3 = Math.floor(Math.random() * 256);

               var newDataset = {
                   label: '베이비몽즈',
                   borderColor : 'rgba('+color1+', '+color2+', '+color3+', 1)',
                   backgroundColor : 'rgba('+color1+', '+color2+', '+color3+', 1)',
                   data: dataList,
                   fill: false
               }
               // chart에 newDataset 푸쉬
               config.data.datasets.push(newDataset);

               myChart.update();	//차트 업데이트
           },
           error:function(data){
               alert(data);
           }
       });

       $.ajax({
           url:'/api/transaction/readecttransaction?nftCode=MG',
           type:'get',
           async:false,
           success:function(data){
               let dateList =[];
               let dataList =[];
               for(let i=0; i<data.length; i++){
                   dateList.push(data[i].date);
                   dataList.push(data[i].amount);
               }

               var color1 = Math.floor(Math.random() * 256);
               var color2 = Math.floor(Math.random() * 256);
               var color3 = Math.floor(Math.random() * 256);

               var newDataset = {
                   label: '제네시스몽즈',
                   borderColor : 'rgba('+color1+', '+color2+', '+color3+', 1)',
                   backgroundColor : 'rgba('+color1+', '+color2+', '+color3+', 1)',
                   data: dataList,
                   fill: false
               }
               // chart에 newDataset 푸쉬
               config.data.datasets.push(newDataset);

               myChart.update();	//차트 업데이트
           },
           error:function(data){
               alert(data);
           }
       });

   }

</script>

