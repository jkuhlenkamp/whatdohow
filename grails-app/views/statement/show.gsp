
<%@ page import="whatdohow.Statement" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'statement.label', default: 'Statement')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-statement" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-statement" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list statement">
			
				<g:if test="${statementInstance?.statewhat}">
				<li class="fieldcontain">
					<span id="statewhat-label" class="property-label"><g:message code="statement.statewhat.label" default="Statewhat" /></span>
					
						<span class="property-value" aria-labelledby="statewhat-label"><g:fieldValue bean="${statementInstance}" field="statewhat"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${statementInstance?.statedo}">
				<li class="fieldcontain">
					<span id="statedo-label" class="property-label"><g:message code="statement.statedo.label" default="Statedo" /></span>
					
						<span class="property-value" aria-labelledby="statedo-label"><g:fieldValue bean="${statementInstance}" field="statedo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${statementInstance?.statehow}">
				<li class="fieldcontain">
					<span id="statehow-label" class="property-label"><g:message code="statement.statehow.label" default="Statehow" /></span>
					
						<span class="property-value" aria-labelledby="statehow-label"><g:fieldValue bean="${statementInstance}" field="statehow"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${statementInstance?.location}">
				<li class="fieldcontain">
					<span id="location-label" class="property-label"><g:message code="statement.location.label" default="Location" /></span>
					
						<span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${statementInstance}" field="location"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${statementInstance?.id}" />
					<g:link class="edit" action="edit" id="${statementInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
