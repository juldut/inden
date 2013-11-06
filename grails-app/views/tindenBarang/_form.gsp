<%@ page import="inden.TindenBarang" %>



<div class="fieldcontain ${hasErrors(bean: tindenBarangInstance, field: 'branchShop', 'error')} ">
	<label for="branchShop">
		<g:message code="tindenBarang.branchShop.label" default="Branch Shop" />
		
	</label>
	<g:textField name="branchShop" value="${tindenBarangInstance?.branchShop}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tindenBarangInstance, field: 'history', 'error')} ">
	<label for="history">
		<g:message code="tindenBarang.history.label" default="History" />
		
	</label>
	<g:select name="history" from="${inden.ThistoryInden.list()}" multiple="multiple" optionKey="id" size="5" value="${tindenBarangInstance?.history*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tindenBarangInstance, field: 'keterangan', 'error')} ">
	<label for="keterangan">
		<g:message code="tindenBarang.keterangan.label" default="Keterangan" />
		
	</label>
	<g:textField name="keterangan" value="${tindenBarangInstance?.keterangan}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tindenBarangInstance, field: 'namaBarang', 'error')} ">
	<label for="namaBarang">
		<g:message code="tindenBarang.namaBarang.label" default="Nama Barang" />
		
	</label>
	<g:textField name="namaBarang" value="${tindenBarangInstance?.namaBarang}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tindenBarangInstance, field: 'pembuat', 'error')} required">
	<label for="pembuat">
		<g:message code="tindenBarang.pembuat.label" default="Pembuat" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pembuat" name="pembuat.id" from="${inden.Mlogin.list()}" optionKey="id" required="" value="${tindenBarangInstance?.pembuat?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tindenBarangInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="tindenBarang.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="status" name="status.id" from="${inden.MstatusInden.list()}" optionKey="id" required="" value="${tindenBarangInstance?.status?.id}" class="many-to-one"/>
</div>

