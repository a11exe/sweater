<#import "parts/common.ftl" as c>

<@c.page>
<h2>User edit ${user.id}</h2>
<form action="/user" method="post">
  <input type="hidden" name="_csrf" value="${_csrf.token}" />
  <input type="text" name="username" value="${user.username}" />
  <#list roles as role>
    <div>
      <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
    </div>
  </#list>
  <input type="hidden" name="userId" value="${user.id}" />
  <button type="submit">Edit</button>
</form>
</@c.page>