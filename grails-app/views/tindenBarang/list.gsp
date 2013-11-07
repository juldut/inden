
<%@ page import="inden.TindenBarang" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tindenBarang.label', default: 'TindenBarang')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tindenBarang" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="propose">Propose</g:link></li>
			</ul>
		</div>
		<div id="list-tindenBarang" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="branchShop" title="${message(code: 'tindenBarang.branchShop.label', default: 'Branch Shop')}" />
					
						<g:sortableColumn property="namaBarang" title="${message(code: 'tindenBarang.namaBarang.label', default: 'Nama Barang')}" />
					
						<th><g:message code="tindenBarang.pembuat.label" default="Pembuat" /></th>
					
%{-- 						<th><g:message code="tindenBarang.status.label" default="Status" /></th>
 --}%					
 						<g:sortableColumn property="status" title="Status" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${tindenBarangInstanceList}" status="i" var="tindenBarangInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tindenBarangInstance.id}">${fieldValue(bean: tindenBarangInstance, field: "branchShop")}</g:link></td>
					
						<td>${fieldValue(bean: tindenBarangInstance, field: "namaBarang")}</td>
					
						<td>${fieldValue(bean: tindenBarangInstance, field: "pembuat")}</td>
					
						<td>${fieldValue(bean: tindenBarangInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tindenBarangInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
