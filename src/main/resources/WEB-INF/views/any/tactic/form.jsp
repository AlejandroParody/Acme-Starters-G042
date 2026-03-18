<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.tactic.form.label.name" path="name"/>
	<acme:form-textarea code="any.tactic.form.label.notes" path="notes"/>
	<acme:form-textbox code="any.tactic.form.label.expectedPercentage" path="expectedPercentage"/>
	<acme:form-select code="any.tactic.form.label.tacticKind" path="tacticKind" choices="${kinds}"/>
	<acme:form-textbox code="any.tactic.form.label.strategy" path="strategy"/>
</acme:form>