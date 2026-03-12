<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox code="inventor.invention.form.label.ticker" path="ticker" placeholder="inventor.invention.form.placeholder.ticker"/>
    <acme:form-textbox code="inventor.invention.form.label.name" path="name" placeholder="inventor.invention.form.placeholder.name"/>
    <acme:form-textarea code="inventor.invention.form.label.description" path="description" placeholder="inventor.invention.form.placeholder.description"/>
    <acme:form-moment code="inventor.invention.form.label.startMoment" path="startMoment"/>
    <acme:form-moment code="inventor.invention.form.label.endMoment" path="endMoment"/>
    <acme:form-url code="inventor.invention.form.label.moreInfo" path="moreInfo" placeholder="inventor.invention.form.placeholder.moreInfo"/>

    <jstl:choose>
        <jstl:when test="${_command == 'create'}">
            <acme:submit code="inventor.invention.form.button.create" action="/inventor/invention/create"/>
        </jstl:when>
        <jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && published == false}">
			<acme:form-textbox code="inventor.invention.form.label.published" path="published" readonly="true"/>
            <acme:button code="inventor.invention.form.button.parts" action="/inventor/part/list?inventionId=${id}"/>
            <acme:submit code="inventor.invention.form.button.update" action="/inventor/invention/update"/>
            <acme:submit code="inventor.invention.form.button.delete" action="/inventor/invention/delete"/>
            <acme:submit code="inventor.invention.form.button.publish" action="/inventor/invention/publish"/>
        </jstl:when>
        <jstl:when test="${_command == 'show' && published == true}">
			<acme:form-textbox code="inventor.invention.form.label.published" path="published" readonly="true"/>
            <acme:button code="inventor.invention.form.button.parts" action="/inventor/part/list?inventionId=${id}"/>
        </jstl:when>
    </jstl:choose>
</acme:form>