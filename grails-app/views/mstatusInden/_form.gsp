<%@ page import="inden.MstatusInden" %>



<div class="fieldcontain ${hasErrors(bean: mstatusIndenInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="mstatusInden.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="status" required="" value="${mstatusIndenInstance?.status}"/>
</div>

