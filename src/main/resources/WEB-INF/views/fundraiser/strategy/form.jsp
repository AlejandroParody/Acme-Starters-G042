<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form readonly="${published == 'true'}">
    <acme:form-textbox code="fundraiser.strategy.form.label.ticker" path="ticker" placeholder="fundraiser.strategy.form.placeholder.ticker"/>
    <acme:form-textbox code="fundraiser.strategy.form.label.name" path="name" placeholder="fundraiser.strategy.form.placeholder.name"/>
    <acme:form-textarea code="fundraiser.strategy.form.label.description" path="description" placeholder="fundraiser.strategy.form.placeholder.description"/>
    <acme:form-moment code="fundraiser.strategy.form.label.startMoment" path="startMoment"/>
    <acme:form-moment code="fundraiser.strategy.form.label.endMoment" path="endMoment"/>
    <acme:form-url code="fundraiser.strategy.form.label.moreInfo" path="moreInfo" placeholder="fundraiser.strategy.form.placeholder.moreInfo"/>

    <jstl:choose>
        <jstl:when test="${_command == 'create'}">
            <acme:submit code="fundraiser.strategy.form.button.create" action="/fundraiser/strategy/create"/>
        </jstl:when>
        <jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && published == 'false'}">
            <acme:form-textbox code="fundraiser.strategy.form.label.published" path="published" readonly="true"/>
            <acme:button code="fundraiser.strategy.form.button.tactics" action="/fundraiser/tactic/list?strategyId=${id}"/>
            <acme:submit code="fundraiser.strategy.form.button.update" action="/fundraiser/strategy/update"/>
            <acme:submit code="fundraiser.strategy.form.button.delete" action="/fundraiser/strategy/delete"/>
            <acme:submit code="fundraiser.strategy.form.button.publish" action="/fundraiser/strategy/publish"/>
        </jstl:when>
        <jstl:when test="${_command == 'show' && published == 'true'}">
            <acme:form-textbox code="fundraiser.strategy.form.label.published" path="published" readonly="true"/>
            <acme:button code="fundraiser.strategy.form.button.tactics" action="/fundraiser/tactic/list?strategyId=${id}"/>
        </jstl:when>
    </jstl:choose>
</acme:form>