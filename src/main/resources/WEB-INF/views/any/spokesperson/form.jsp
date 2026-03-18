<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.spokesperson.form.label.fullName" path="identity.fullName"/>
	<acme:form-textbox code="any.spokesperson.form.label.email" path="identity.email"/>
	<acme:form-textarea code="any.spokesperson.form.label.cv" path="cv"/>
	<acme:form-textbox code="any.spokesperson.form.label.achievements" path="achievements"/>
	<acme:form-checkbox code="any.spokesperson.form.label.licensed" path="licensed"/>
</acme:form>