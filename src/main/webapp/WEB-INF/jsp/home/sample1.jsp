<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <link rel="shortcut icon" href="/img/favicon.png">

    <title>Basic Table</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
      <!--right slidebar-->
      <link href="/css/slidebars.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet" />

  </head>

  <body>

  <section id="container" class="">
      <!--header start-->
      <header class="header white-bg">
          <div class="sidebar-toggle-box">
              <i class="fa fa-bars"></i>
          </div>
          <!--logo start-->
          <a href="#" class="logo">Flat<span>lab</span></a>
          <!--logo end-->

      </header>
      <!--header end-->
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
                  <li>
                      <a href="#">
                          <i class="fa fa-dashboard"></i>
                          <span>Dashboard</span>
                      </a>
                  </li>

              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
              <!-- page start-->
              <div class="row">
                  <div class="col-lg-12">
                      <section class="card">
                          <header class="card-header">
                              Advanced Table
                          </header>
                          <table class="table table-striped table-advance table-hover">
                              <thead>
                              <tr>
                                  <th><i class="fa fa-upload"></i>수량</th>
                                  <th class=""><i class="fa fa-gift"></i>채굴량</th>
                                  <th><i class="fa fa-gift"></i>절반채굴량</th>
                                  <th><i class=" fa fa-money"></i>일일보상</th>
                                  <th><i class=" fa fa-money"></i>월보상</th>
                                  <th><i class=" fa fa-money"></i>총금액</th>
                                  <th><i class=" fa fa-money"></i>깡통가(선미기준)</th>
                                  <th><i class=" fa fa-money"></i>깡통가(메콩)</th>
                                  <th><i class=" fa fa-money"></i>깡통가(지릴)</th>
                                  <th></th>
                              </tr>
                              </thead>
                              <tbody>
<%--                              <tr>
                                  <td><a href="#">Vector Ltd</a></td>
                                  <td class="hidden-phone">Lorem Ipsum dorolo imit</td>
                                  <td>12120.00$ </td>
                                  <td><span class="badge badge-info label-mini">Due</span></td>
                                  <td>
                                      <button class="btn btn-success btn-sm"><i class="fa fa-check"></i></button>
                                      <button class="btn btn-primary btn-sm"><i class="fa fa-pencil"></i></button>
                                      <button class="btn btn-danger btn-sm"><i class="fa fa-trash-o "></i></button>
                                  </td>
                              </tr>--%>
                              </tbody>
                          </table>
                      </section>
                  </div>
              </div>
              <!-- page end-->
          </section>
      </section>
      <!--main content end-->


      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              2018 &copy; FlatLab by VectorLab.
              <a href="#" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script class="include" type="text/javascript" src="/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="/js/jquery.scrollTo.min.js"></script>
    <script src="/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="/js/respond.min.js" ></script>

    <script src="/js/slidebars.min.js"></script>

    <!--common script for all pages-->
    <script src="/js/common-scripts.js"></script>
  <script>
      $(function () {
        console.log('test22');

          $.ajax({
              type : 'get',
              url : '/api/soldiers',
              contentType : "application/json; charset=utf-8",
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
      });




  </script>


  </body>
</html>
