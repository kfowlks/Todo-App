<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<stripes:layout-render name="/WEB-INF/protected_jsps/layout/_template.jsp" pageTitle="TurnOffTheLights" nav="default">
    
    <stripes:layout-component name="html_head">

    </stripes:layout-component>
    
    <stripes:layout-component name="html_navbar">
    <!-- 
      <div class="masthead">
        <ul class="nav nav-pills pull-right">
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#">About</a></li>
          <li><a href="#">Contact</a></li>
        </ul>
      </div>
      -->
     <h3 class="muted">Todo Example</h3>
    
    </stripes:layout-component>
    
    <stripes:layout-component name="contents">
    
    <stripes:form beanclass="net.p2pmag.totl.web.controller.DefaultActionBean" focus="">
    	<stripes:submit name="addList" value="addList" >Add New Todo List</stripes:submit>    	
    	<stripes:text   name="list.name"></stripes:text>
    </stripes:form>
     <br />
     
    <ul>
    <c:forEach items="${actionBean.lists}" var="item" varStatus="loop">
        <li>
        ${item.name}
        <stripes:link beanclass="net.p2pmag.totl.web.controller.TodoTaskActionBean" event="doPage" ><stripes:param name="list.id">${item.id}</stripes:param>View</stripes:link>
        <stripes:link beanclass="net.p2pmag.totl.web.controller.DefaultActionBean" event="deleteTaskList" ><stripes:param name="list.id">${item.id}</stripes:param>Delete</stripes:link>
     	</li>
    </c:forEach>
    </ul>
    <!-- 
	  <div class="jumbotron">
        <h1>Super awesome marketing speak!</h1>
        <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        <a class="btn btn-large btn-success" href="#">Sign up today</a>
      </div>
       -->
	<!--  <img src="${ctx}/UI/site/images/headtorso.jpg" class="half" width="%50" />  -->
    </stripes:layout-component>
</stripes:layout-render>