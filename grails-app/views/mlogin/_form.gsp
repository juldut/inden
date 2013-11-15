<%@ page import="inden.Mlogin" %>



<div class="fieldcontain ${hasErrors(bean: mloginInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="mlogin.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${mloginInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mloginInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="mlogin.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${mloginInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mloginInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="mlogin.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${mloginInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: mloginInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="mlogin.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${mloginInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: mloginInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="mlogin.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${mloginInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: mloginInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="mlogin.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${mloginInstance?.passwordExpired}" />
</div>

