<%@ page import="inden.MloginMloginRole" %>



<div class="fieldcontain ${hasErrors(bean: mloginMloginRoleInstance, field: 'mlogin', 'error')} required">
	<label for="mlogin">
		<g:message code="mloginMloginRole.mlogin.label" default="Mlogin" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="mlogin" name="mlogin.id" from="${inden.Mlogin.list()}" optionKey="id" required="" value="${mloginMloginRoleInstance?.mlogin?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mloginMloginRoleInstance, field: 'mloginRole', 'error')} required">
	<label for="mloginRole">
		<g:message code="mloginMloginRole.mloginRole.label" default="Mlogin Role" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="mloginRole" name="mloginRole.id" from="${inden.MloginRole.list()}" optionKey="id" required="" value="${mloginMloginRoleInstance?.mloginRole?.id}" class="many-to-one"/>
</div>

