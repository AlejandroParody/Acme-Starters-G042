<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
    <acme:list-column code="spokesperson.milestone.list.label.title" path="title" width="40%"/>
    <acme:list-column code="spokesperson.milestone.list.label.achievements" path="achievements" width="20%"/>
    <acme:list-column code="spokesperson.milestone.list.label.effort" path="effort" width="40%"/>
</acme:list>

<jstl:if test="${!published}">
    <acme:button code="spokesperson.milestone.list.button.create" action="/spokesperson/milestone/create?campaignId=${campaignId}"/>
</jstl:if>