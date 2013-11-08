
<%@ page import="inden.ThistoryInden" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'thistoryInden.label', default: 'ThistoryInden')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-thistoryInden" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-thistoryInden" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list thistoryInden">
			
				<g:if test="${thistoryIndenInstance?.memo}">
				<li class="fieldcontain">
					<span id="memo-label" class="property-label"><g:message code="thistoryInden.memo.label" default="Memo" /></span>
					
						<span class="property-value" aria-labelledby="memo-label"><g:fieldValue bean="${thistoryIndenInstance}" field="memo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${thistoryIndenInstance?.pembuat}">
				<li class="fieldcontain">
					<span id="pembuat-label" class="property-label"><g:message code="thistoryInden.pembuat.label" default="Pembuat" /></span>
					
						<span class="property-value" aria-labelledby="pembuat-label"><g:link controller="mlogin" action="show" id="${thistoryIndenInstance?.pembuat?.id}">${thistoryIndenInstance?.pembuat?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${thistoryIndenInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="thistoryInden.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:link controller="mstatusInden" action="show" id="${thistoryIndenInstance?.status?.id}">${thistoryIndenInstance?.status?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${thistoryIndenInstance?.tanggalBuat}">
				<li class="fieldcontain">
					<span id="tanggalBuat-label" class="property-label"><g:message code="thistoryInden.tanggalBuat.label" default="Tanggal Buat" /></span>
					
						<span class="property-value" aria-labelledby="tanggalBuat-label"><g:formatDate date="${thistoryIndenInstance?.tanggalBuat}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${thistoryIndenInstance?.id}" />
					<g:link class="edit" action="edit" id="${thistoryIndenInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
