<%@page%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
    <acme:list-column code="fundraiser.strategy.list.label.ticker" path="ticker" width="25%"/>
    <acme:list-column code="fundraiser.strategy.list.label.name" path="name" width="25%"/>
    <acme:list-column code="fundraiser.strategy.list.label.draftMode" path="draftMode" width="20%"/>
    <acme:list-column code="fundraiser.strategy.list.label.startMoment" path="startMoment" width="30%"/>
</acme:list>

<acme:button code="fundraiser.strategy.list.button.create" action="/fundraiser/strategy/create"/>