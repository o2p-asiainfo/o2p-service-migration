<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.asiainfo.integretion.o2p.servicemigration.common.Constant" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../resources/jquery-2.1.4.min.js" type="text/javascript"></script>
<script type="text/javascript">
function submitForXsdCheck(){
	document.uploadForm.action="xsdCheck.do";
	document.uploadForm.submit();
}
function importAction(){
	if(confirm("Sure for import?")){
		$.post("importAction.do", {
			serviceObjectsStr: $("#serviceObjectsStr").text()
		}, function(data){
			alert(data);
			if(data=="success"){
				window.location.href="success.do";
			}
		});
	}
}
</script>
<title>service object import</title>
</head>
<body>
	<%if(request.getAttribute("checkResult")!=null && Constant.SUCCESS.equals(request.getAttribute("checkResult")) && request.getAttribute("existObjects")!=null){ %>
	<font color="red">Warn!!!These Object <%=request.getAttribute("existObjects") %> are exist! It will be covered by new object after you import the service object, please check it!</font></br>
	<table border="1">
		<tr>
			<td></td>
			<td>new</td>
			<td>old</td>
			<td>operate</td>
		</tr>
		<%
		List<Object> list = (List<Object>)request.getAttribute("existObjects"); 
		for(Object o : list){
		%>
		<tr>
			<td><%=((Map<String, Object>)o).get(Constant.OBJECT_NAME).toString() %> with code <%=((Map<String, Object>)o).get(Constant.OBJECT_CODE).toString() %></td>
			<td><%=((Map<String, Object>)o).get(Constant.OBJECT_NEW).toString() %></td>
			<td><%=((Map<String, Object>)o).get(Constant.OBJECT_OLD).toString() %></td>
			<td>
				<select id="<%=((Map<String, Object>)o).get(Constant.OBJECT_NAME).toString()+"_"+((Map<String, Object>)o).get(Constant.OBJECT_CODE).toString() %>" onchange="alert(this.id)">
					<option>select</option>
					<option value="update">update</option>
					<option value="ignore">ignore</option>
				</select>
			</td>
		</tr>
		<%} %>
	</table>
	
	<%} else {%>
	<form name="uploadForm" action="service/xsdCheck" method="post" enctype="multipart/form-data">  
		<input type="file" name="file" onchange="javascript:submitForXsdCheck()"/>
	</form>
	<%
		if(request.getAttribute("checkResult")!=null && Constant.SUCCESS.equals(request.getAttribute("checkResult"))){
			out.println("<font color='green'>check ok!</font>");
		}
	}
	%>
	<%if(request.getAttribute("checkResult")!=null && !"success".equals(request.getAttribute("checkResult"))){ %></br><font color="red"><%=request.getAttribute("checkResult") %></font><%} %>
	</br>
	<%if(request.getAttribute("checkResult")!=null && "success".equals(request.getAttribute("checkResult"))){ %><button onclick="javascript:importAction()">import</button><button onclick="javascript:history.go(-1)">back</button><%} %>
<textarea style="display: none" id="serviceObjectsStr"><%=request.getAttribute("serviceObjectsStr") %></textarea>
</body>
</html>