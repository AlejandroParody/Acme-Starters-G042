<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form readonly="${published == 'true'}">
    <acme:form-textbox code="sponsor.sponsorship.form.label.ticker" path="ticker" placeholder="sponsor.sponsorship.form.placeholder.ticker"/>
    <acme:form-textbox code="sponsor.sponsorship.form.label.name" path="name" placeholder="sponsor.sponsorship.form.placeholder.name"/>
    <acme:form-textarea code="sponsor.sponsorship.form.label.description" path="description" placeholder="sponsor.sponsorship.form.placeholder.description"/>
    <acme:form-moment code="sponsor.sponsorship.form.label.startMoment" path="startMoment"/>
    <acme:form-moment code="sponsor.sponsorship.form.label.endMoment" path="endMoment"/>
    <acme:form-url code="sponsor.sponsorship.form.label.moreInfo" path="moreInfo" placeholder="sponsor.sponsorship.form.placeholder.moreInfo"/>
    
    <jstl:choose>
        <jstl:when test="${_command == 'create'}">
            <acme:submit code="sponsor.sponsorship.form.button.create" action="/sponsor/sponsorship/create"/>
        </jstl:when>
        <jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && published == 'false'}">
            <acme:form-textbox code="sponsor.sponsorship.form.label.monthsActive" path="monthsActive" readonly="true"/>
	    	<acme:form-textbox code="sponsor.sponsorship.form.label.totalMoney" path="totalMoney" readonly="true"/>
            <acme:form-textbox code="sponsor.sponsorship.form.label.published" path="published" readonly="true"/>
            <acme:button code="sponsor.sponsorship.form.button.donations" action="/sponsor/donation/list?sponsorshipId=${id}"/>
            <acme:submit code="sponsor.sponsorship.form.button.update" action="/sponsor/sponsorship/update"/>
            <acme:submit code="sponsor.sponsorship.form.button.delete" action="/sponsor/sponsorship/delete"/>
            <acme:submit code="sponsor.sponsorship.form.button.publish" action="/sponsor/sponsorship/publish"/>
        </jstl:when>

        <jstl:when test="${_command == 'show' && published == 'true'}">
            <acme:form-textbox code="sponsor.sponsorship.form.label.monthsActive" path="monthsActive" readonly="true"/>
	    	<acme:form-textbox code="sponsor.sponsorship.form.label.totalMoney" path="totalMoney" readonly="true"/>
            <acme:form-textbox code="sponsor.sponsorship.form.label.published" path="published" readonly="true"/>
            <acme:button code="sponsor.sponsorship.form.button.donations" action="/sponsor/donation/list?sponsorshipId=${id}"/>

        </jstl:when>
    </jstl:choose>
</acme:form>