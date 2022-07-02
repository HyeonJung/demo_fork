<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<section class="wrapper" style="max-width: 1500px">
  <!-- page start-->
  <div class="row">
      <div class="col-lg-12">
          <section class="card">
              <header id="coin-title" class="card-header">

              </header>
              <table class="table table-striped table-advance table-hover" id="table">

              </table>
          </section>
      </div>
  </div>
</section>
  <script>
      $(function () {
          $.ajax({
              type : 'get',
              url : '/home/soldiers',
              error: function(xhr, status, error){
                  alert(status);
              },
              success : function(html){
                  $('#table').html(html);
              },
          });
      });

      $('#btnHolderCheck').click(function(){
          $('#table').html('');
      });

      function priceToString(price) {
          return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
      }
  </script>

