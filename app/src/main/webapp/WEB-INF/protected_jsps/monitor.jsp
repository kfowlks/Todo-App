<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

Server Name: ${actionBean.serverName}<br />
Date: ${actionBean.currentDate}<br />
<br />
Database Status: <c:choose><c:when test="${actionBean.dbStatus}">Online</c:when><c:otherwise>Offline</c:otherwise></c:choose><br /> 
<br />
Application Status: <c:choose><c:when test="${actionBean.dbStatus}">OK</c:when><c:otherwise>Offline</c:otherwise></c:choose>

