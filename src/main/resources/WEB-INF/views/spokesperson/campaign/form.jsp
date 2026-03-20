<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form readonly="${published == true}">
    <acme:form-textbox code="spokesperson.campaign.form.label.ticker" path="ticker" placeholder="spokesperson.campaign.form.placeholder.ticker"/>
    <acme:form-textbox code="spokesperson.campaign.form.label.name" path="name" placeholder="spokesperson.campaign.form.placeholder.name"/>
    <acme:form-textarea code="spokesperson.campaign.form.label.description" path="description" placeholder="spokesperson.campaign.form.placeholder.description"/>
    <acme:form-moment code="spokesperson.campaign.form.label.startMoment" path="startMoment"/>
    <acme:form-moment code="spokesperson.campaign.form.label.endMoment" path="endMoment"/>
    <acme:form-url code="spokesperson.campaign.form.label.moreInfo" path="moreInfo" placeholder="spokesperson.campaign.form.placeholder.moreInfo"/>

    <jstl:choose>
        <jstl:when test="${_command == 'create'}">
            <acme:submit code="spokesperson.campaign.form.button.create" action="/spokesperson/campaign/create"/>
        </jstl:when>
	<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && published == false}">
            <acme:form-double code="spokesperson.campaign.form.label.monthsActive" path="monthsActive" readonly="true"/>
            <acme:form-double code="spokesperson.campaign.form.label.effort" path="effort" readonly="true"/>
            
            <acme:form-textbox code="spokesperson.campaign.form.label.published" path="publishedLabel" readonly="true"/>
            <acme:button code="spokesperson.campaign.form.button.milestones" action="/spokesperson/milestone/list?campaignId=${id}"/>
            <acme:submit code="spokesperson.campaign.form.button.update" action="/spokesperson/campaign/update"/>
            <acme:submit code="spokesperson.campaign.form.button.delete" action="/spokesperson/campaign/delete"/>
            <acme:submit code="spokesperson.campaign.form.button.publish" action="/spokesperson/campaign/publish"/>
        </jstl:when>
        <jstl:when test="${_command == 'show' && published == true}">
            <acme:form-textbox code="spokesperson.campaign.form.label.published" path="publishedLabel" readonly="true"/>
            <acme:button code="spokesperson.campaign.form.button.milestones" action="/spokesperson/milestone/list?campaignId=${id}"/>
        </jstl:when>
    </jstl:choose>
</acme:form>