
<%@ page import="inden.Mlogin" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'mlogin.label', default: 'Mlogin')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-mlogin" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-mlogin" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list mlogin">
			
				<g:if test="${mloginInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="mlogin.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${mloginInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${mloginInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="mlogin.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${mloginInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${mloginInstance?.accountExpired}">
				<li class="fieldcontain">
					<span id="accountExpired-label" class="property-label"><g:message code="mlogin.accountExpired.label" default="Account Expired" /></span>
					
						<span class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean boolean="${mloginInstance?.accountExpired}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${mloginInstance?.accountLocked}">
				<li class="fieldcontain">
					<span id="accountLocked-label" class="property-label"><g:message code="mlogin.accountLocked.label" default="Account Locked" /></span>
					
						<span class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean boolean="${mloginInstance?.accountLocked}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${mloginInstance?.enabled}">
				<li class="fieldcontain">
					<span id="enabled-label" class="property-label"><g:message code="mlogin.enabled.label" default="Enabled" /></span>
					
						<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${mloginInstance?.enabled}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${mloginInstance?.passwordExpired}">
				<li class="fieldcontain">
					<span id="passwordExpired-label" class="property-label"><g:message code="mlogin.passwordExpired.label" default="Password Expired" /></span>
					
						<span class="property-value" aria-labelledby="passwordExpired-label"><g:formatBoolean boolean="${mloginInstance?.passwordExpired}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${mloginInstance?.id}" />
					<g:link class="edit" action="edit" id="${mloginInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
