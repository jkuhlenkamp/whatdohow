
<%@ page import="whatdohow.Statement" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'statement.label', default: 'Statement')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-statement" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-statement" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="statewhat" title="${message(code: 'statement.statewhat.label', default: 'Statewhat')}" />
					
						<g:sortableColumn property="statedo" title="${message(code: 'statement.statedo.label', default: 'Statedo')}" />
					
						<g:sortableColumn property="statehow" title="${message(code: 'statement.statehow.label', default: 'Statehow')}" />
					
						<g:sortableColumn property="location" title="${message(code: 'statement.location.label', default: 'Location')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${statementInstanceList}" status="i" var="statementInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${statementInstance.id}">${fieldValue(bean: statementInstance, field: "statewhat")}</g:link></td>
					
						<td>${fieldValue(bean: statementInstance, field: "statedo")}</td>
					
						<td>${fieldValue(bean: statementInstance, field: "statehow")}</td>
					
						<td>${fieldValue(bean: statementInstance, field: "location")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${statementInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
