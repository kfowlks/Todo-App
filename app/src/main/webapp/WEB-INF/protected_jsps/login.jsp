<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    

<stripes:layout-render name="/WEB-INF/protected_jsps/layout/_template.jsp" pageTitle="Login">
    <stripes:layout-component name="contents">

	<div class="furatto-block">
    <div class="furatto-jumbo-header">
      <div class="furatto-jumbo-container">
        <form action="" class="login-form centered-form" method="get" accept-charset="utf-8">
                <div class="furatto-login-icon">
                   <img src="assets/img/icons/lock-icon128.png" alt="">
                   <h1 class="login-header">Sign in</h1>
                </div>
                <input type="text" name="" id="" value="" placeholder="username">
                <input type="password" name="" id="" value="" placeholder="password">
                <input type="submit" name="" id="" value="Let me in!" class="btn btn-block btn-primary btn-large">
              </form>
      </div>
    </div>
    <hr />
    <div>

    </div>

  </div>

	
    </stripes:layout-component>
</stripes:layout-render>
