<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<body>

    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Insert text">
            <input type="text" name="tag" placeholder="Insert tag">
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Send</button>
        </form>
    </div>

    <div>
        <@l.logout />
        <span>
            <a href="user">User list</a>
        </span>
    </div>

    <form method="get" action="main">
        <input type="text" name="filter" value="${filter?ifExists}">
        <button type="submit">Filter</button>
    </form>

    <div>Список сообщений</div>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
            <div>
                <#if message.filename??>
                    <img src="/img/${message.filename}">
                </#if>
            </div>
        </div>
    <#else>
        No messages
    </#list>
</body>
</@c.page>