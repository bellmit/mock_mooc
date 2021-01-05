<#--${objectName} = {<#list objectList as object><#list object as enum>${enum.name()}: {key: "${enum.code}", value: "${enum.desc}"}, </#list></#list>}-->

<#list objectList as object>
<#list object as key, value>
${key}={<#list value as enum>${enum.name()}: {key: "${enum.code}", value: "${enum.desc}"}, </#list>}
</#list>
</#list>