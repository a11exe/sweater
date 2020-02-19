<#include "security.ftl">
<#import "pager.ftl" as p>

<@p.pager url page />

<div class="card-columns" id="message-list">
    <#list page.content as message>
      <div class="card my-3" data-id="${message.id?c}">
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top" />
            </#if>
        <div class="m-2">
          <span>${message.text}</span><br/>
          <i>#${message.tag}</i>
        </div>
        <div class="card-footer text-muted container">
          <div class="row">
            <a class="col align-self-center" href="/user-messages/${message.author.id?c}">${message.authorName}</a>
            <a class="col align-self-center" href="/messages/${message.id?c}/like">
                        <#if message.meLiked>
                            <i class="fas fa-heart"></i>
                        <#else>
                            <i class="far fa-heart"></i>
                        </#if>
              ${message.likes}
            </a>
                    <#if message.author.id?c == currentUserId?c>
                        <a class="col btn btn-primary" href="/user-messages/${message.author.id?c}?message=${message.id?c}">
                          Edit
                        </a>
                    </#if>
          </div>
        </div>
      </div>
    <#else>
        No message
    </#list>
</div>

<@p.pager url page />