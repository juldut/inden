<%@ page import="inden.ThistoryInden" %>



<div class="fieldcontain ${hasErrors(bean: thistoryIndenInstance, field: 'memo', 'error')} ">
	<label for="memo">
		<g:message code="thistoryInden.memo.label" default="Memo" />
		
	</label>
	<g:textField name="memo" value="${thistoryIndenInstance?.memo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: thistoryIndenInstance, field: 'pembuat', 'error')} required">
	<label for="pembuat">
		<g:message code="thistoryInden.pembuat.label" default="Pembuat" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pembuat" name="pembuat.id" from="${inden.Mlogin.list()}" optionKey="id" required="" value="${thistoryIndenInstance?.pembuat?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: thistoryIndenInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="thistoryInden.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="status" name="status.id" from="${inden.MstatusInden.list()}" optionKey="id" required="" value="${thistoryIndenInstance?.status?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: thistoryIndenInstance, field: 'tanggalBuat', 'error')} required">
	<label for="tanggalBuat">
		<g:message code="thistoryInden.tanggalBuat.label" default="Tanggal Buat" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="tanggalBuat" precision="day"  value="${thistoryIndenInstance?.tanggalBuat}"  />
</div>

