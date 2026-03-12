<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox code="inventor.invention.form.label.ticker" path="ticker"/>
    <acme:form-textbox code="inventor.invention.form.label.name" path="name"/>
    <acme:form-textarea code="inventor.invention.form.label.description" path="description"/>
    <acme:form-moment code="inventor.invention.form.label.startMoment" path="startMoment"/>
    <acme:form-moment code="inventor.invention.form.label.endMoment" path="endMoment"/>
    <acme:form-url code="inventor.invention.form.label.moreInfo" path="moreInfo"/>
    <acme:form-textbox code="inventor.invention.form.label.published" path="published"/>

    <jstl:if test="${!published}">
        <acme:submit code="inventor.invention.form.button.update" action="/inventor/invention/update"/>
        <acme:submit code="inventor.invention.form.button.delete" action="/inventor/invention/delete"/>
        <acme:submit code="inventor.invention.form.button.publish" action="/inventor/invention/publish"/>
    </jstl:if>

    <acme:button code="inventor.invention.form.button.parts" action="/inventor/part/list?inventionId=${id}"/>
</acme:form>