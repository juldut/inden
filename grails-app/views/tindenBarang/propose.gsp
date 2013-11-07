
<%@ page import="inden.TindenBarang" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tindenBarang.label', default: 'TindenBarang')}" />
		<title>Propose</title>
	</head>
	<body>
		<a href="#list-tindenBarang" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tindenBarang" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

			<g:form action="savePropose" >

				<table>
					<thead>
						<tr>
							<th></th>
						
							<g:sortableColumn property="branchShop" title="${message(code: 'tindenBarang.branchShop.label', default: 'Branch Shop')}" />
						
						
							<g:sortableColumn property="namaBarang" title="${message(code: 'tindenBarang.namaBarang.label', default: 'Nama Barang')}" />
						
							<th><g:message code="tindenBarang.pembuat.label" default="Pembuat" /></th>
						
							<th><g:message code="tindenBarang.status.label" default="Status" /></th>
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${tindenBarangInstanceList}" status="i" var="tindenBarangInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td><g:checkBox name="arrbarang.${i}" checked="${false}" value="${fieldValue(bean: tindenBarangInstance, field: "id")}"/></td>
						
							<td><g:link action="show" id="${tindenBarangInstance.id}">${fieldValue(bean: tindenBarangInstance, field: "branchShop")}</g:link></td>
						
						
							<td>${fieldValue(bean: tindenBarangInstance, field: "namaBarang")}</td>
						
							<td>${fieldValue(bean: tindenBarangInstance, field: "pembuat")}</td>
						
							<td>${fieldValue(bean: tindenBarangInstance, field: "status")}</td>
						
						</tr>
					</g:each>
					<g:hiddenField name="barangCount" value="${tindenBarangInstanceList.size()}" />
					</tbody>
				</table>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>

			<div class="pagination">
				<g:paginate total="${tindenBarangInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
