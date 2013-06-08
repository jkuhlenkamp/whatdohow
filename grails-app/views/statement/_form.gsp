<%@ page import="whatdohow.Statement" %>



<div class="fieldcontain ${hasErrors(bean: statementInstance, field: 'statewhat', 'error')} required">
	<label for="statewhat">
		<g:message code="statement.statewhat.label" default="Statewhat" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="statewhat" required="" value="${statementInstance?.statewhat}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: statementInstance, field: 'statedo', 'error')} required">
	<label for="statedo">
		<g:message code="statement.statedo.label" default="Statedo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="statedo" required="" value="${statementInstance?.statedo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: statementInstance, field: 'statehow', 'error')} required">
	<label for="statehow">
		<g:message code="statement.statehow.label" default="Statehow" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="statehow" required="" value="${statementInstance?.statehow}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: statementInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="statement.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${statementInstance?.location}"/>
</div>

