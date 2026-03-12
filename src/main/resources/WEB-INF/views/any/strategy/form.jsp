<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox path="ticker" code="any.strategy.form.label.ticker"/>
    <acme:form-textbox path="name" code="any.strategy.form.label.name"/>

    <acme:form-textarea path="description" code="any.strategy.form.label.description"/>

    <acme:form-moment path="startMoment" code="any.strategy.form.label.start"/>
    <acme:form-moment path="endMoment" code="any.strategy.form.label.end"/>

    <acme:form-url path="moreInfo" code="any.strategy.form.label.moreInfo"/>

    <acme:form-textbox path="fundraiser" code="any.strategy.form.label.fundraiser"/>
</acme:form>