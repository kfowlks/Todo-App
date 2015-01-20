<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<stripes:layout-render name="/WEB-INF/protected_jsps/layout/_template.jsp" title="Logout" nav="undefined">
<stripes:layout-component name="contents">
	<p><strong>Your session has been terminated.</strong></p>
</stripes:layout-component> 
</stripes:layout-render>
