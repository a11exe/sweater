<#import "parts/common.ftl" as c>

<@c.page>
<div class="form-row mb-3">
  <form method="get" action="/main" class="form-inline">
    <input type="text" name="filter" value="${filter!}" placeholder="Search by tag..">
    <button type="submit" class="btn btn-primary ml-2">Search</button>
  </form>
</div>

<#include "parts/messageEdit.ftl" />
<#include "parts/messageList.ftl" />

</@c.page>