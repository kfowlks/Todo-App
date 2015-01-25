<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<stripes:useActionBean id="actionBean" beanclass="net.p2pmag.totl.web.controller.TodoTaskActionBean" />
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
     <h3 class="muted">Editing Todo List ${actionBean.list.name}</h3>
     
     <!-- -->
     
    </stripes:layout-component>
    
    <stripes:layout-component name="contents">
    
     <stripes:form beanclass="net.p2pmag.totl.web.controller.TodoTaskActionBean" focus="">
    	<stripes:submit name="addTask" value="addTask" >Add Task</stripes:submit>    	
    	<stripes:text size="100"  name="task.description"></stripes:text>
    	<stripes:hidden name="list.id" value="${actionBean.list.id}"></stripes:hidden>    	
    </stripes:form>
    
     <ul>
	    <c:forEach items="${actionBean.list.todoTasks}" var="task" varStatus="loop">
	        <li>
		        ${task.description} <stripes:link  beanclass="net.p2pmag.totl.web.controller.TodoTaskActionBean" event="deleteTask" >
     				<stripes:param name="task.id">${task.id}</stripes:param>
     				<stripes:param name="list.id">${actionBean.list.id}</stripes:param>  Remove
     			</stripes:link>
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