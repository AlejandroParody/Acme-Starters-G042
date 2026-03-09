<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.inventor.form.label.fullName" path="identity.fullName"/>
	<acme:form-textbox code="any.inventor.form.label.email" path="identity.email"/>
	<acme:form-textarea code="any.inventor.form.label.bio" path="bio"/>
	<acme:form-textbox code="any.inventor.form.label.keyWords" path="keyWords"/>
	<acme:form-checkbox code="any.inventor.form.label.licensed" path="licensed"/>
</acme:form>