<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.asiainfo.integretion.o2p.servicemigration.domain.Service"%>
<%@ page import="com.asiainfo.integration.o2p.session.web.http.CookieUtil"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Map<String, Object> errorResult = (Map<String, Object>)request.getAttribute("exportError");
	String result = "";
	if(errorResult != null && errorResult.keySet() != null) {
		for(String key : errorResult.keySet()) {
	result += "export exists some error:\\nservice:  " + key + ", cause:  " + errorResult.get(key);
	result += "\\n";
		}
	}
%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Services Export</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/js/bootstrap-toastr/toastr.min.css">
<link rel="stylesheet" href="../resources/js/uniform/css/uniform.default.min.css">
<link rel="stylesheet" href="../resources/js/data-tables/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="../resources/css/custom.css">
</head>

<body>
<div class="container-fluid">
<div class="search-form-default">
      <form class="form-inline mb20" action="#">
        <div class="form-group mr20">
          <label for="">Service Name: </label>
          <input type="text" class="form-control" id="serviceName">
        </div>
        <div class="form-group">
          <label for="">Style: </label>
          <select class="form-control" id="style">
          	<option value="">select</option>
          	<option value="1">rest</option>
          	<option value="2">webservice</option>
          	<option value="4">http</option>
          	<option value="3">file</option>
          </select>
        </div>
        <button type="button" class="btn btn-primary" onclick="doSearch()">Search</button>
      </form>
    </div>
 <div class="portlet box blue">
      <div class="portlet-title">
        <div class="caption"> Service List </div>
        <div class="actions">          
          <button class="btn btn-default" onclick="doExport()">Export</button>
        </div>
      </div>
      <div class="portlet-body">
        <table class="table group-check text-middle table-bordered" id="export">
        <thead>
          <tr>
            <th> <input type="checkbox" class="group-checkable" data-set=".group-check .checkboxes">
            </th>
            <th>serviceCode(#<%=CookieUtil.getTenantId(request) %>)</th>
			<th>style</th>
			<th>name</th>
			<th>lasted_time</th>
			<th>serviceAddress</th>
			<th>status</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
      </div>
    </div>
</div>

<form id="download" action="download.do" method="post" style="display: none">
	<input type="hidden" id="serviceCodes" name="serviceCodes" value=""/>
</form>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="../resources/js/html5shiv.min.js"></script>
<script src="../resources/js/respond.min.js"></script>
<![endif]-->
<script src="../resources/js/jquery.min.js"></script> 
<script src="../resources/js/bootstrap.min.js"></script>
<script src="../resources/js/bootstrap-toastr/toastr.min.js"></script> 
<script src="../resources/js/uniform/jquery.uniform.min.js"></script> 
<script src="../resources/js/data-tables/jquery.dataTables.min.js"></script> 
<script src="../resources/js/export.js"></script> 
<script src="../resources/js/bootbox.min.js"></script>
<script src="../resources/js/jquery.blockui.min.js"></script>
</body>
<script>




	<%if(!result.equals("")) {%>
	bootbox.alert("<%=result%>");
	<%}%>
    $(document).ready(function($) {
       Export.init();
    });
    
    
    
    var allSelectItem = new Array();
    function doExport() {
    	var names = '';
    	$.each(allSelectItem, function(index, item){
    		names += item + ",";
    	});
    	names = names.substring(0, names.length-1);
    	if(names==null || names==""){
    		bootbox.alert("No service choose for export!");
    		return;
    	}
    	$.blockUI({ message: '<h5><img src="../resources/images/loading.gif" /> Processing...</h5>' });
    	$("#serviceCodes").val(names);
    	$("#download").submit();
    	$.unblockUI();
    }

    function doSearch() {
    	var serviceName = $("#serviceName").val();
    	var style = $("#style").val();
    	Export.handleDT(serviceName, style);
    }
</script>
</html>
