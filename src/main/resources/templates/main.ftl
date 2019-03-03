<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<body>

    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Insert text">
            <input type="text" name="tag" placeholder="Insert tag">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Send</button>
        </form>
    </div>

    <div>
        <@l.logout />
    </div>

    <form method="get" action="main">
        <input type="text" name="filter" value="${filter}">
        <button type="submit">Filter</button>
    </form>

    <div>Список сообщений</div>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
        </div>
    <#else>
        No messages
    </#list>
</body>
</@c.page>