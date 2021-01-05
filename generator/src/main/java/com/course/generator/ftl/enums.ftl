${objectName} = {<#list objects as object>${object.name()}: {key: "${object.code}", value: "${object.desc}"}, </#list>}
<#--${objectName} = {<#list objects as object>${object.name()}: },</#list>}-->