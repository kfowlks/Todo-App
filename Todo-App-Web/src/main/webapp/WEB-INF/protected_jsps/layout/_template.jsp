<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--  
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:if test="${ctx == '/'}">
	<c:set var="ctx" value=""></c:set>
</c:if>
 -->
<stripes:layout-definition>
<!DOCTYPE html>
<html>
	<head>
		<meta charset=utf-8 />
		<title>${pageTitle}</title>
		<link href="webjars/bootstrap/3.3.4/dist/css/bootstrap.css" rel="stylesheet" media="screen" />
		<stripes:layout-component name="html_head" />
	</head>  	
	<body>		
		<div class="container">
		<stripes:layout-component name="html_navbar" />
		<stripes:layout-component name="contents" />
	    <script src="http://code.jquery.com/jquery-latest.js"></script>
	    <script src="webjars/bootstrap/3.3.4/dist/js/bootstrap.min.js"></script>			    
	    <script type="text/javascript">
	    $(document).ready(function() {
	        $('#selecctall').click(function(event) {  //on click 
	            if(this.checked) { // check select status
	                $("input[name^='selectedTodoTaskIds']").each(function() { //loop through each checkbox
	                    this.checked = true;  //select all checkboxes with class "checkbox1"               
	                });
	            }else{
	            	$("input[name^='selectedTodoTaskIds']").each(function() { //loop through each checkbox
	                    this.checked = false; //deselect all checkboxes with class "checkbox1"                       
	                });         
	            }
	        });
	    });	   
	    </script>
	    </div>
  	</body>
</html>
</stripes:layout-definition>