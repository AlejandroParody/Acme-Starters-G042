<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox path="ticker" code="any.strategy.form.label.ticker"/>
    <acme:form-textbox path="name" code="any.strategy.form.label.name"/>

    <acme:form-textarea path="description" code="any.strategy.form.label.description"/>

    <acme:form-moment path="startMoment" code="any.strategy.form.label.startMoment"/>
    <acme:form-moment path="endMoment" code="any.strategy.form.label.endMoment"/>

    <acme:form-url path="moreInfo" code="any.strategy.form.label.moreInfo"/>
    
    <acme:form-double code="any.strategy.form.label.monthsActive" path="monthsActive" readonly="true"/>
    <acme:form-double code="any.strategy.form.label.expectedPercentage" path="expectedPercentage" readonly="true"/>
    
    <acme:button code="any.strategy.form.button.tactics" action="/any/tactic/list?strategyId=${id}"/>
    <acme:button code="any.strategy.form.button.fundraiser" action="/any/fundraiser/show?id=${fundraiserId}"/>
</acme:form>