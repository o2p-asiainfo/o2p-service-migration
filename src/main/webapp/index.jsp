<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Service Migration</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/js/bootstrap-toastr/toastr.min.css">
<link rel="stylesheet" href="resources/css/custom.css">
</head>
<body style="overflow:hidden">
<div class="container">
  <ul class="nav nav-tabs">
    <li class="active"><a href="#tab1" data-toggle="tab" id="exportTab">Export</a> </li>
    <li><a href="#tab2" data-toggle="tab" id="importTab">Import</a> </li>
    <li><a href="#tab3" data-toggle="tab" id="logsTab">Logs</a> </li>
  </ul>
  <div class="tab-content">
    <div id="tab1" class="tab-pane active">
      <iframe src="service/export.do" frameborder="0" width="100%" scrolling="yes" name="iframepage1" id="iframepage1"></iframe>
    </div>
    <div id="tab2" class="tab-pane">
      <iframe src="service/import.do" frameborder="0" width="100%" scrolling="yes" name="iframepage2" id="iframepage2"></iframe>
    </div>
     <div id="tab3" class="tab-pane">
      <iframe src="service/logs.do" frameborder="0" width="100%" scrolling="yes" name="iframepage3" id="iframepage3"></iframe>
    </div>
  </div>
</div>
<script src="resources/js/jquery.min.js"></script> 
<script src="resources/js/bootstrap.min.js"></script> 
<script>
 $(document).ready(function($) {
    $('#exportTab').on('shown.bs.tab', function(e) {
	     $("#iframepage1")[0].contentWindow.Export.handleDT();
	     $("#iframepage1").contents().find('#export').css({'width':'100%'});
    });
    $('#importTab').on('shown.bs.tab', function(e) {
	     $("#iframepage2").contents().find('#import').css({'width':'100%'});
    });
     $('#logsTab').on('shown.bs.tab', function(e) {
	     $("#iframepage3")[0].contentWindow.Logs.handleDT();
	     $("#iframepage3").contents().find('#logs').css({'width':'100%'});
    });

  function setHeight(){
     var viewHeight = $(window).height(); //窗口可视高度
     var navHeight = $('ul.nav-tabs').outerHeight(true);
     var height = viewHeight -30*2 - navHeight;
     $('iframe').each(function(){
      $(this).height(height);
     })
  } 
  setHeight();
  window.onresize=setHeight;
})
 </script>
</body>
</html>
