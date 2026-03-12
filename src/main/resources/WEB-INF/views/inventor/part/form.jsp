<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox code="inventor.part.form.label.name" path="name"/>
    <acme:form-textarea code="inventor.part.form.label.description" path="description"/>
    <acme:form-money code="inventor.part.form.label.cost" path="cost"/>
    <acme:form-select code="inventor.part.form.label.kind" path="kind" choices="${kinds}"/>

    <jstl:if test="${!published}">
        <acme:submit code="inventor.part.form.button.update" action="/inventor/part/update"/>
        <acme:submit code="inventor.part.form.button.delete" action="/inventor/part/delete"/>
    </jstl:if>

    <acme:button code="inventor.part.form.button.back" action="/inventor/invention/show?id=${inventionId}"/>
</acme:form>