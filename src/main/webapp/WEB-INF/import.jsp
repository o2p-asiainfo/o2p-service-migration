<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.asiainfo.integretion.o2p.servicemigration.common.Constant" %>
<%@page import="com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>
<%

%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Service Object Import</title>
		<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
		<link rel="stylesheet" href="../resources/js/bootstrap-toastr/toastr.min.css">
		<link rel="stylesheet" href="../resources/css/custom.css">
	</head>
	<body>
	<div class="container-fluid">
			<form name="uploadForm" action="" method="post" id="form" enctype="multipart/form-data">
				<h2>Select File</h2>
				<label> <span>XML File</span> </label>
				<div class="fileinput fileinput-new input-group" data-provides="fileinput">
					  <div class="form-control" data-trigger="fileinput"><span class="icon-file fileinput-exists"></span> <span class="fileinput-filename"></span> </div>
					  <span class="input-group-addon btn btn-default btn-file"><span class="fileinput-new">Select file</span><span class="fileinput-exists">Change</span>
					  <input type="file" name="file" id="file"  onchange="javascript:submitForXsdCheck()">
					  </span><!-- <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">Reset</a>  -->
				</div>
			</form>

			<div>
				<div id="importContent">
				<hr>
				<h2>Change List</h2>
				<div class="mb20">
			   <label for="" class="label label-success">Add</label>
			   <label for="" class="label label-warning">Modify</label>
			   <label for="" class="label label-danger">Remove</label>
			  </div>
				<div class="row">
				  <div class="col-xs-12">
				  	<div class="table-responsive">
				    <table class="table table-bordered table-striped text-middle">
				      <colgroup width="30"></colgroup>
				      <colgroup></colgroup>
				      <colgroup></colgroup>
				      <colgroup></colgroup>
				      <thead>
				        <tr>
				          <th>#</th>
				          <th>Object Name</th>
				          <th>Object Code</th>
				        </tr>
				      </thead>
				      <tbody id="removeTable">
				      </tbody>
				    </table>
				    </div>
				  </div>
				</div>
				<div class="form-actions">
					<div class="row">
				    	<div class="col-md-12 text-center">
				      		<button class="btn btn-primary btn-lg" type="button" id="submit" onclick="javascript:importAction();">Import</button>
				   		</div>
				  	</div>
				</div>
			<div class="modal" id="announce">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title">Report</h4>
			      </div>
			      <div class="modal-body" id="content"> <img src="../resources/images/loading.gif" alt=""> </div>
			      <div class="modal-footer">
			        <button class="btn btn-default" data-dismiss="modal">Canel</button>
			        <button class="btn btn-primary">OK</button>
			      </div>
			    </div>
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
		<script src="../resources/js/ajaxfileupload.js"></script>
		<script src="../resources/js/bootstrap.min.js"></script> 
		<script src="../resources/js/jasny-bootstrap-fileinput.js"></script> 
		<script src="../resources/js/bootstrap-toastr/toastr.min.js"></script> 
		<script src="../resources/js/import.js"></script>
		<script src="../resources/js/bootbox.min.js"></script>
		<script src="../resources/js/jquery.blockui.min.js"></script>
		<script type="text/javascript">
		
		//Html结构转字符串形式显示 支持<br>换行
		function ToHtmlString(htmlStr) {
			if(htmlStr==null){
				return "";
			}
		    return toTXT(htmlStr).replace(/\&lt\;br[\&ensp\;|\&emsp\;]*[\/]?\&gt\;|\r\n|\n/g, "<br/>");
		}
		//Html结构转字符串形式显示
		function toTXT(str) {
		    var RexStr = /\<|\>|\"|\'|\&|　| /g
		    str = str.replace(RexStr,
		    function (MatchStr) {
		        switch (MatchStr) {
		            case "<":
		                return "&lt;";
		                break;
		            case ">":
		                return "&gt;";
		                break;
		            case "\"":
		                return "&quot;";
		                break;
		            case "'":
		                return "&#39;";
		                break;
		            case "&":
		                return "&amp;";
		                break;
		            case " ":
		                return "&ensp;";
		                break;
		            case "　":
		                return "&emsp;";
		                break;
		            default:
		                break;
		        }
		    }
		    )
		    return str;
		}
		   	
		var globalSet = function() {
	        toastr.options = {
	            positionClass: 'toast-bottom-right',
	            closeButton: true,
	            timeOut: 2000,
	        }        
	    }
/*
	    var checkInput = function() {
	        var $this = $('#file');
	        if ($this.val() == '') {
	            return false;
	        }
	        var nReg = (/\.xml$/i);
	        var isXML = nReg.test($this.val());
	        return isXML;
	    }*/
	   
	    function submitForXsdCheck(){
	    	var filepath=$("#file").val();
	    	if(filepath==null || filepath==""){
	    		return;
	    	}
	    	var extStart=filepath.lastIndexOf(".");
	    	var ext=filepath.substring(extStart,filepath.length).toUpperCase();
	    	if(ext!=".XML"){
	    		bootbox.alert("Invald file! Please choose a xml file!");
	    		return;
	    	}
	    	var files = document.getElementById("file").files;
	    	if(files[0].size>10*1024*1024){
	    		bootbox.alert("file size can not exceed 10m!");
	    		return;
	    	}
	    	$.blockUI({ message: '<h5><img src="../resources/images/loading.gif" /> Processing...</h5>' });
			$.ajaxFileUpload({
		         url:"xsdCheck.do",
		         dataType:"text",
		         fileElementId:'file',
		         success: function (data, status){
		        	 $.unblockUI();
		        	 $("#removeTable").text("");
		        	 data=data.substring(0, data.lastIndexOf("}")+1);
		        	 var obj = $.parseJSON(data);
		        	 if(obj.checkResult=='success'){
		        		 var removeList = obj.removeList;
		        		 var updateList = obj.updateList;
		        		 var addList = obj.addList;
		        		 if(removeList=="" && updateList=="" && addList==""){
		        			 bootbox.alert("The service object is already exist in system, please check!");
		        			 return;
		        		 }
		        		 for(i in removeList){
		        			 var removeItem = removeList[i];
	        				 var removeTable = document.all["removeTable"];
	        				 var newTr = removeTable.insertRow();
	        				 newTr.className = "danger";
	        				 
	        				 var newTd0 = newTr.insertCell();
	        				 var newTd1 = newTr.insertCell();
	        				 var newTd2 = newTr.insertCell();
	        				 
	        				 newTd0.innerHTML = parseInt(i) + 1;
	        				 newTd1.innerHTML = removeItem.name;
	        				 newTd2.innerHTML = invokeTableData("remove", removeItem.code, removeItem.detail);
		        		 }
		        		 
		        		 for(i in updateList){
		        			 var updateItem = updateList[i];
		        			
	        				 var removeTable = document.all["removeTable"];
	        				 var newTr = removeTable.insertRow();
	        				 newTr.className = "warning";
	        				 
	        				 var newTd0 = newTr.insertCell();
	        				 var newTd1 = newTr.insertCell();
	        				 var newTd2 = newTr.insertCell();
	        				 
	        				 newTd0.innerHTML = parseInt(i) + 1;
	        				 newTd1.innerHTML = updateItem.name;
	        				 newTd2.innerHTML = invokeTableData("update", updateItem.code, updateItem.detail);
		        		 }
		        	 
		        		 for(i in addList){	 
		        			 var addItem = addList[i];
	        				 var removeTable = document.all["removeTable"];
	        				 var newTr = removeTable.insertRow();
	        				 newTr.className = "success";
	        				 
	        				 var newTd0 = newTr.insertCell();
	        				 var newTd1 = newTr.insertCell();
	        				 var newTd2 = newTr.insertCell();
	        				 
	        				 newTd0.innerHTML = parseInt(i) + 1;
	        				 newTd1.innerHTML = addItem.name;
	        				 newTd2.innerHTML = invokeTableData("add", addItem.code, addItem.detail);
		        		 }
						$('[data-toggle="popover"]').popover({"html":true});
		        	 }else{
		        		 toastr.error(obj.info);
		        	 }
		         },
		         error: function (data, status){
		        	 $.unblockUI();
		             alert("error!");
		         }
			});
		}
		
		function invokeTableData(type, code, obj){
			if(type=="update"){
				var returnStr = "<a data-toggle='popover' data-trigger='hover' data-placement='top' title='Object infomation' data-content='<table class=\"table table-bordered table-striped text-middle\"><tr><td>name</td><td>new</td><td>old</td></tr>";
				var newObj = obj.newObj;
				var oldObj = obj.oldObj;
				for(i in newObj){
					if(newObj[i]!=oldObj[i]){
						returnStr = returnStr + "<tr><td>"+i+"</td><td>"+ToHtmlString(newObj[i])+"</td><td>"+ToHtmlString(oldObj[i])+"</td></tr>";
					}
				}
			}else{
				var returnStr = "<a data-toggle='popover' data-trigger='hover' data-placement='top' title='Object infomation' data-content='<table class=\"table table-bordered table-striped text-middle\"><tr><td>name</td><td>value</td></tr>";
				for(i in obj){
					returnStr = returnStr + "<tr><td>"+i+"</td><td>"+ToHtmlString(obj[i])+"</td></tr>";
				}
			}
			returnStr = returnStr + "</table>'>"+code+"</a>"; 
			return returnStr;
		}
		
		function importAction(){
			//before check
			if($("#file").val()==null || ""==$("#file").val()){
				toastr.error("No file selected!");
				return;
			}
			
			bootbox.confirm("Sure for import?", function(result){ 
				/* your callback code */ 
				if(result){
					$.blockUI({ message: '<h5><img src="../resources/images/loading.gif" /> Processing...</h5>' });
					$.post("importAction.do", {
						serviceObjectsStr: $("#serviceObjectStr").text()
					}, function(data){
						$.unblockUI();
						if(data=="success"){
							bootbox.alert("Import success!");
						}else{
							toastr.error(data);
						}
						$("#removeTable").text("");
					}).error(function(XMLHttpRequest, textStatus, errorThrown) {
						$.unblockUI();
						toastr.error("error!");
					});
				}
			});
		}
	    $(document).ready(function($) {
		       Import.init();
		 });
	    
	    String.prototype.replaceAll = function(s1,s2){
			return this.replace(new RegExp(s1,"gm"),s2);
		}
		</script>
	</body>
</html>
