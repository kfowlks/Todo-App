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
		<link href="webjars/flatstrap/3.1.2/css/flatstrap.css" rel="stylesheet" media="screen">
	  <style type="text/css">
      body {
        padding-top: 20px;
        padding-bottom: 40px;
      }

      /* Custom container */
      .container-narrow {
        margin: 0 auto;
        max-width: 700px;
      }
      .container-narrow > hr {
        margin: 30px 0;
      }

      /* Main marketing message and sign up button */
      .jumbotron {
        margin: 60px 0;
        text-align: center;
      }
      .jumbotron h1 {
        font-size: 72px;
        line-height: 1;
      }
      .jumbotron .btn {
        font-size: 21px;
        padding: 14px 24px;
      }

      /* Supporting marketing content */
      .marketing {
        margin: 60px 0;
      }
      .marketing p + h4 {
        margin-top: 28px;
      }
    </style>
	
	<stripes:layout-component name="html_head" />
	</head>
  
	  <body>		
		<div class="container-narrow">
		
			<stripes:layout-component name="html_navbar" />			
			<stripes:layout-component name="contents" />
			
		    <script src="http://code.jquery.com/jquery-latest.js"></script>
		    <script src="webjars/flatstrap/3.1.2/flatstrap.min.js"></script>	    
	    </div>
	  </body>
</html>
</stripes:layout-definition>