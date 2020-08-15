<nav class="container-fluid" id="main-nav-user-home">
    <div class="row">
        <div class="col-md-6" id="user-tools-left">
            <a href="/" id="main-logo">B</a>
            <div id="search-box">
                <input type="text" name="search-friend">
                <button><i class="fa fa-search"></i></button>
            </div>
        </div>
        <div class="col-md-6" id="user-tools-right">
            <ul>
                <li>
                    <a href="/profile">
                        <img id="avatarHeader" class="avatar-small" src="${pageContext.servletContext.contextPath}/avatar">
                        ${sessionScope.user.getFirst_name()}
                    </a>
                </li>
                <li><a href="/">Home <span>20+</span></a></li>
                <li>
                    <a href="/"><i class="fa fa-users" aria-hidden="true"></i></a>
                    <span class="mess-notif">9</span>
                </li>
                <li>
                    <a id="show-chat-box-button" href="/"><i class="fa fa-weixin" aria-hidden="true"></i></a>
                    <span class="mess-notif">25</span>
                </li>
                <li>
                    <a href="/"><i class="fa fa-globe" aria-hidden="true"></i></a>
                    <span class="mess-notif">5</span>
                </li>
                <li onclick="document.logout.submit()">
                    <form name="logout" action="/logout" method="post">
                        <i class="fa fa-power-off" aria-hidden="true"></i>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>