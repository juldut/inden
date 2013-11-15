
<%@ page import="inden.MloginMloginRole" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'mloginMloginRole.label', default: 'MloginMloginRole')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-mloginMloginRole" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-mloginMloginRole" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list mloginMloginRole">
			
				<g:if test="${mloginMloginRoleInstance?.mlogin}">
				<li class="fieldcontain">
					<span id="mlogin-label" class="property-label"><g:message code="mloginMloginRole.mlogin.label" default="Mlogin" /></span>
					
						<span class="property-value" aria-labelledby="mlogin-label"><g:link controller="mlogin" action="show" id="${mloginMloginRoleInstance?.mlogin?.id}">${mloginMloginRoleInstance?.mlogin?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${mloginMloginRoleInstance?.mloginRole}">
				<li class="fieldcontain">
					<span id="mloginRole-label" class="property-label"><g:message code="mloginMloginRole.mloginRole.label" default="Mlogin Role" /></span>
					
						<span class="property-value" aria-labelledby="mloginRole-label"><g:link controller="mloginRole" action="show" id="${mloginMloginRoleInstance?.mloginRole?.id}">${mloginMloginRoleInstance?.mloginRole?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${mloginMloginRoleInstance?.id}" />
					<g:link class="edit" action="edit" id="${mloginMloginRoleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
