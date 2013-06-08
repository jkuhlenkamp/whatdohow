<%@ page import="whatdohow.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="user.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${userInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'statements', 'error')} ">
	<label for="statements">
		<g:message code="user.statements.label" default="Statements" />
		
	</label>
	<g:select name="statements" from="${whatdohow.Statement.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.statements*.id}" class="many-to-many"/>
</div>

