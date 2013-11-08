
<%@ page import="inden.TindenBarang" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tindenBarang.label', default: 'TindenBarang')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tindenBarang" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tindenBarang" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tindenBarang">
			
				<g:if test="${tindenBarangInstance?.branchShop}">
				<li class="fieldcontain">
					<span id="branchShop-label" class="property-label"><g:message code="tindenBarang.branchShop.label" default="Branch Shop" /></span>
					
						<span class="property-value" aria-labelledby="branchShop-label"><g:fieldValue bean="${tindenBarangInstance}" field="branchShop"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tindenBarangInstance?.history}">
				<li class="fieldcontain">
					<span id="history-label" class="property-label"><g:message code="tindenBarang.history.label" default="History" /></span>
					
						<g:each in="${tindenBarangInstance.history}" var="h">
						<span class="property-value" aria-labelledby="history-label">
							%{-- <g:link controller="thistoryInden" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link> --}%
							<table>
								<tr>
									<th width="40%">${h?.status?.status} By ${h?.pembuat?.username}</th>
									<th>${h?.toStringTanggalBuat()}</th>
								</tr>
								<tr>
									<td></td>
									<td>${h?.memo}</td>
								</tr>
							</table>

						</span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${tindenBarangInstance?.namaBarang}">
				<li class="fieldcontain">
					<span id="namaBarang-label" class="property-label"><g:message code="tindenBarang.namaBarang.label" default="Nama Barang" /></span>
					
						<span class="property-value" aria-labelledby="namaBarang-label"><g:fieldValue bean="${tindenBarangInstance}" field="namaBarang"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tindenBarangInstance?.pembuat}">
				<li class="fieldcontain">
					<span id="pembuat-label" class="property-label"><g:message code="tindenBarang.pembuat.label" default="Pembuat" /></span>
					
						<span class="property-value" aria-labelledby="pembuat-label">
							%{-- <g:link controller="mlogin" action="show" id="${tindenBarangInstance?.pembuat?.id}"> --}%
								${tindenBarangInstance?.pembuat?.encodeAsHTML()}
							%{-- </g:link> --}%
						</span>
					
				</li>
				</g:if>
			
				<g:if test="${tindenBarangInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="tindenBarang.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label">
							%{-- <g:link controller="mstatusInden" action="show" id="${tindenBarangInstance?.status?.id}"> --}%
								${tindenBarangInstance?.status?.encodeAsHTML()}
							%{-- </g:link> --}%
						</span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tindenBarangInstance?.id}" />
					<g:link class="edit" action="edit" id="${tindenBarangInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					<g:link class="delete" action="reject" id="${tindenBarangInstance?.id}">Reject</g:link>

				</fieldset>
			</g:form>
		</div>
	</body>
</html>
