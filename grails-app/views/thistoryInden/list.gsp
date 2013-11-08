
<%@ page import="inden.ThistoryInden" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'thistoryInden.label', default: 'ThistoryInden')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-thistoryInden" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-thistoryInden" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="memo" title="${message(code: 'thistoryInden.memo.label', default: 'Memo')}" />
					
						<th><g:message code="thistoryInden.pembuat.label" default="Pembuat" /></th>
					
						<th><g:message code="thistoryInden.status.label" default="Status" /></th>
					
						<g:sortableColumn property="tanggalBuat" title="${message(code: 'thistoryInden.tanggalBuat.label', default: 'Tanggal Buat')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${thistoryIndenInstanceList}" status="i" var="thistoryIndenInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${thistoryIndenInstance.id}">${fieldValue(bean: thistoryIndenInstance, field: "memo")}</g:link></td>
					
						<td>${fieldValue(bean: thistoryIndenInstance, field: "pembuat")}</td>
					
						<td>${fieldValue(bean: thistoryIndenInstance, field: "status")}</td>
					
						<td><g:formatDate date="${thistoryIndenInstance.tanggalBuat}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${thistoryIndenInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
