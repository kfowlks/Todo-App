<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<stripes:layout-render name="/WEB-INF/protected_jsps/layout/_template.jsp" pageTitle="Todo Sample Task List" nav="default">
    
    <stripes:layout-component name="html_head">
    <style>
		body{
		    background-color:#EEEEEE;
		}
		
		input[type=submit] {
			margin-top: 10px;
		}
		
		.todolist{
		    background-color:#FFF;
		    padding:20px 20px 10px 20px;
		    margin-top:30px;
		}
		.todolist h1{
		    margin:0;
		    padding-bottom:20px;
		    text-align:center;
		}
		.form-control{
		    border-radius:0;
		}
		li.ui-state-default{
		    background:#fff;
		    border:none;
		    border-bottom:1px solid #ddd;
		}
		
		li.ui-state-default:last-child{
		    border-bottom:none;
		}
		
		.todo-footer{
		    background-color:#F4FCE8;
		    margin:0 -20px -10px -20px;
		    padding: 10px 20px;
		}
		#done-items li{
		    padding:10px 0;
		    border-bottom:1px solid #ddd;
		    text-decoration:line-through;
		}
		#done-items li:last-child{
		    border-bottom:none;
		}
		#checkAll{
		    margin-top:10px;
		}
    </style>
    </stripes:layout-component>
    
    <stripes:layout-component name="html_navbar">
    	
    </stripes:layout-component>
    
    <stripes:layout-component name="contents">
	
	<c:set var="noncompleteTasks" value="0" scope="page" />
          <!-- This is filter out the count to only task that have been completed.  -->
    <c:forEach items="${actionBean.lists}" var="item">
	<c:if test="${!item.completed}">
		<c:set var="noncompleteTasks" value="${noncompleteTasks + 1}" scope="page"/>
	</c:if>
	</c:forEach>
	
	<stripes:form beanclass="net.p2pmag.totl.web.controller.DefaultActionBean" focus="task.description">
	<div class="row">
	
	
        <div class="col-md-6">
            <div class="todolist not-done">
            	<stripes:errors/>
				<stripes:messages/>
            
             <h1>Todos</h1>                
                <stripes:text name="task.description" class="form-control add-todo"></stripes:text>                
                <stripes:submit name="addTask"  class="btn btn-success" value="Add" />
                <stripes:submit name="deleteTasks" class="btn btn-success" value="Remove" />
                <stripes:submit name="completeTasks" class="btn btn-success" value="Mark Complete" />
                <hr />
                <c:if test="${noncompleteTasks > 0}">
                	<input type="checkbox" id="selecctall"/><span id="checkAll">Select/Unselect All</span>
                </c:if>
                <ul id="sortable" class="list-unstyled">
                <c:forEach items="${actionBean.lists}" var="item" varStatus="loop">
			        <li class="ui-state-default">
			        	<div class="checkbox">
				        	<stripes:label class="toggle" for="${item.id}">
				        	<c:choose>
								<c:when test="${item.completed}">
									<s>${item.description}</s>
									<stripes:link  beanclass="net.p2pmag.totl.web.controller.DefaultActionBean" event="deleteTask" >
				     				<stripes:param name="task.id">${item.id}</stripes:param>
     								<span class="glyphicon glyphicon-remove-sign"></span>
     								</stripes:link>
								</c:when>
								<c:otherwise>
									<stripes:checkbox name="selectedTodoTaskIds[${loop.index}]" value="${item.id}" id="${item.id}"/> ${item.description}
								</c:otherwise>
							</c:choose>
							</stripes:label>				        	
                        </div>
                    </li>
	    		</c:forEach>
            	</ul>
	            <div class="todo-footer">
	            	<strong><span class="count-todos">${noncompleteTasks}</span></strong>&nbsp;Items Left
	            </div>
      		</div>
        </div>
    </div>
    
	</stripes:form>
	
    </stripes:layout-component>
    
</stripes:layout-render>