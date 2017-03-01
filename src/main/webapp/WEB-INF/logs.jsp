<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page
	import="com.asiainfo.integretion.o2p.servicemigration.domain.ServiceMigrationLog"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Logs</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/js/bootstrap-toastr/toastr.min.css">
<link rel="stylesheet" href="../resources/js/data-tables/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="../resources/js/bootstrap-datepicker/datepicker.css" />
<link rel="stylesheet" href="../resources/css/custom.css">

</head>

<body>
<div class="container-fluid">
<div class="search-form-default">
      <form class="form-inline mb20" action="#">
        <div class="form-group mr20">
          <label for="">Operator: </label>
          <input type="text" class="form-control" id="operator">
        </div>
        <div class="form-group">
          <label for="">Date range: </label>
          <div class="input-group input-large date-picker input-daterange from">
            <input type="text" class="form-control" name="from" id="fromTime" placeHolder="yyyy-mm-dd">
            <span class="input-group-addon"> to </span>
            <input type="text" class="form-control" name="to" id="toTime" placeHolder="yyyy-mm-dd">
          </div>
          <label for="">To: </label>
        </div>
        <button type="button" class="btn btn-primary" onclick="doSearch()">Search</button>
      </form>
    </div>
 <div class="portlet box blue">
      <div class="portlet-title">
        <div class="caption"> Operate History </div>
      </div>
      <div class="portlet-body">
        <table class="table group-check text-middle table-bordered" id="logs">
        <thead>
          <tr>
            <th>operator</th>
            <th>time</th>
			<th>ip</th>
			<th>old_doc</th>
			<th>new_doc</th>
			<th>operation</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
      </div>
    </div>
</div>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="../resources/js/html5shiv.min.js"></script>
<script src="../resources/js/respond.min.js"></script>
<![endif]-->
<script src="../resources/js/jquery.min.js"></script> 
<script src="../resources/js/bootstrap.min.js"></script>
<script src="../resources/js/bootstrap-toastr/toastr.min.js"></script>
<script src="../resources/js/data-tables/jquery.dataTables.min.js"></script> 
<script src="../resources/js/bootstrap-datepicker/bootstrap-datepicker.js"></script>
<script src="../resources/js/bootbox.min.js"></script>
<script src="../resources/js/jquery.blockui.min.js"></script>
<script src="../resources/js/logs.js"></script>
<script>
    $(document).ready(function($) {
       Logs.init();
    });
</script>
</body>
</html>
