
<%@ page import="inden.MloginMloginRole" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'mloginMloginRole.label', default: 'MloginMloginRole')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-mloginMloginRole" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-mloginMloginRole" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="mloginMloginRole.mlogin.label" default="Mlogin" /></th>
					
						<th><g:message code="mloginMloginRole.mloginRole.label" default="Mlogin Role" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${mloginMloginRoleInstanceList}" status="i" var="mloginMloginRoleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${mloginMloginRoleInstance.id}">${fieldValue(bean: mloginMloginRoleInstance, field: "mlogin")}</g:link></td>
					
						<td>${fieldValue(bean: mloginMloginRoleInstance, field: "mloginRole")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${mloginMloginRoleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
