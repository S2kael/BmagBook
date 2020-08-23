<%@ page import="java.util.List" %>
<%@ page import="Database.Entity.User" %>
<div id="online-list">
    <div id="friend-detail-popup">
        <span id="back-triange"></span>
        <span id="front-triange"></span>
        <img id="friend-img" src="img/profile.jpg">
        <div id="friend-detail-popup-detail">
            <p>
                <a id="friend-full-name" href="/">Nguyễn Quốc Bảo</a>
                <span>36 followers</span>
            </p>
            <p>
                <i class="fa fa-user-plus" aria-hidden="true"></i> Became friends with <br><a href="/">Hien
                Tran and 3 others...</a>
            </p>
            <p>
                <i class="fa fa-user-plus" aria-hidden="true"></i> Became friends with <br><a href="/">Ca
                Chua and 3 others...</a>
            </p>
        </div>
    </div>
    <ul>
        <%
            User user = (User) session.getAttribute("user");
            List<User> listFriend = user.getListFriend();
            for (User friend: listFriend) {
                String fullName = friend.getFirst_name() + " " + friend.getLast_name();
        %>
        <li id="<%= friend.getId()%>">
            <img class="avatar-small" src="${pageContext.servletContext.contextPath}/avatar?id=<%=friend.getId()%>">
            <span><%=fullName%></span>
            <span></span>
        </li>
        <% }%>
    </ul>
</div>



<script>
    $(document).ready(function() {
        $('#online-list li').hover(function(){
            const id = $(this).attr('id');
            $.ajax({
                url: '${pageContext.servletContext.contextPath}/individualProfile',
                type: 'POST',
                dataType: 'json',
                data: {
                    id: id
                },
                success: function(data) {
                    $('#friend-img').attr('src', data.imageString);
                    $('#friend-full-name').html(data.fullName);
                },
                error: function() {
                    alert('cannot load data');
                }
            });

        }, function(){});
    });
</script>