<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.fundraiser.form.label.fullName" path="identity.fullName"/>
	<acme:form-textbox code="any.fundraiser.form.label.email" path="identity.email"/>
	<acme:form-textarea code="any.fundraiser.form.label.bank" path="bank"/>
	<acme:form-textbox code="any.fundraiser.form.label.statement" path="statement"/>
	<acme:form-checkbox code="any.fundraiser.form.label.agent" path="agent"/>
</acme:form>