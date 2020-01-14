<#import "parts/common.ftl" as c>

<@c.page>
<div class="form-row mb-3">
  <form method="get" action="/main" class="form-inline">
    <input type="text" name="filter" value="${filter!}" placeholder="Search by tag..">
    <button type="submit" class="btn btn-primary ml-2">Search</button>
  </form>
</div>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button"
   aria-expanded="false" aria-controls="collapseExample">
  Add new message
</a>
<div class="collapse" id="collapseExample">
  <div class="form-group mt-3">
    <form method="post" enctype="multipart/form-data">
      <div class="form-group">
        <input type="text" class="form-control" name="text" placeholder="Введите сообщение"/>
      </div>
      <div class="form-group">
        <input type="text" class="form-control" name="tag" placeholder="Тэг">
      </div>
      <div class="form-group">
        <div class="custom-file">
          <label class="custom-file-label" for="custom-file">Choose file</label>
          <input type="file" class="form-control" name="file" id="custom-file"/>
        </div>
      </div>
      <input type="hidden" name="_csrf" value="${_csrf.token}"/>
      <button class="btn btn-primary" type="submit">Add</button>
    </form>
  </div>
</div>
<div class="card-columns">
  <#list messages as message>
    <div class="card my-3">
        <#if message.filename??>
          <img class="card-img-top" src="/img/${message.filename}">
        </#if>

      <div class="m-2">
        <span>${message.text}</span>
        <i>${message.tag}</i>
      </div>
      <div class="card-footer text-muted">
        ${message.authorName}
      </div>

    </div>
  <#else>
      No message
  </#list>
</div>
</@c.page>