<%@ page import="java.util.List" %>
<%@ page import="Database.Entity.User" %>

<%
    List<User> suggestList = (List<User>) request.getAttribute("SuggestedList");
    for (User friend: suggestList
         ) {
%>
    <div class="friend-item">
        <img class="avatar-small" src="<%= pageContext.getServletContext().getContextPath()+"/avatar?id="+friend.getId()%>">
        <span>
            <a href="#"><%= friend.getFirst_name() + " " + friend.getLast_name()%></a>
            <span style="display:block;">123 mutual friends </span>
            <button id="<%= friend.getId()%>">
                <i class="fa fa-user-plus" aria-hidden="true"></i>
                Add friend
            </button>
        </span>
        <a href="#" class="ignore-friend"><i class="fa fa-times" aria-hidden="true"></i></a>
    </div>
<% }%>

<script>
    $(function () {
        $('.friend-item button').click(function () {
            var friendId = $(this).attr('id');
            $.ajax({
                url: "${pageContext.servletContext.contextPath}/AddFriend",
                type: 'POST',
                data: {
                    action: 'add-friend',
                    'friend-id': friendId
                },
                success: function (data) {
                    $('#SuggestedFriend').html(data);
                },
                error: function (e) {
                    alert('Error loading ajax: ');
                }
            });
        });
    });
</script>