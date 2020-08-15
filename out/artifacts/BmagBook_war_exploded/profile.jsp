<%@ page import="Database.Entity.User" %>
<!DOCTYPE html>
<html>

<jsp:include page="head.jsp">
    <jsp:param name="Title" value="Profile"/>
</jsp:include>
<body>
<jsp:include page="nav-header.jsp">
    <jsp:param name="Title" value="Home"/>
</jsp:include>

<section class="container-fluid" id="main-body">
    <div class="row no-pad">
        <div class="col-md-8 no-pad">
            <div class="profile-header">
                <div class="profile-header-top">
                    <span id="profile-button-add-cover"><i class="fa fa-camera" aria-hidden="true"></i> Add Cover Photo</span>
                    <img id="avatarProfile" height=160px width=160px src="${pageContext.servletContext.contextPath}/avatar" onclick="document.getElementById('avatarInput').click()">
                    <%
                        User user = (User) session.getAttribute("user");
                        String fullName = user.getFirst_name() + " " + user.getLast_name();
                    %>
                    <input id="avatarInput" type="file" style="display: none" accept="image/*" onchange="handleChangeAvatar(this)">
                    <h3><%= fullName%>
                    </h3>
                    <a href="#" id="profile-button-update-info">Update Info <span>1</span></a>
                    <a href="#" id="profile-button-view-log">View Activity Log <span>5</span></a>
                </div>
                <ul class="profile-header-nav">
                    <li></li>
                    <li><a href="/">Timeline</a></li>
                    <li><a href="/">About</a></li>
                    <li><a href="/">Friends</a></li>
                    <li><a href="/">Photos</a></li>
                    <li><a href="/">More <i class="fa fa-caret-down" aria-hidden="true"></i></a></li>
                </ul>
            </div>
            <div class="profile-body">
                <div class="profile-body-header">
                    <h3><i class="fa fa-user" aria-hidden="true"></i> About</h3>
                </div>
                <div class="profile-body-content">
                    <div class="row no-pad">
                        <div class="col-md-4 profile-body-content-tool no-pad">
                            <a href="/" class="active">Overview</a>
                            <a href="/">Work and education</a>
                            <a href="/">Places you've lived</a>
                            <a href="/">Contact information</a>
                            <a href="/">Family and relationship</a>
                            <a href="/">Detailed about you</a>
                            <a href="/">Life events</a>
                        </div>
                        <div class="col-md-8 profile-body-content-editing">
                            <h4>Overview</h4>
                            <div class="overview-form">
                                <form action="/profile" method="post">
                                    <label>First Name:</label>
                                    <input required type="text" name="first-name" maxlength="30"
                                           value='<%= user.getFirst_name()%>'/>

                                    <label>Last Name:</label>
                                    <input required type="text" name="last-name" maxlength="30"
                                           value='<%= user.getLast_name()%>'/>

                                    <label>Email/Mobile:</label>
                                    <input required type="text" name="mobile-or-email"
                                           value='<%= user.getEmail_mobile()%>'/>

                                    <label>Sex:</label>
                                    <input <%= user.getSex().equals("male")  ? "checked" : ""%>
                                        type="radio" name="sex" value="male"
                                        id="male"/>
                                    <label class="light" for="male">Male</label>
                                    <input <%= user.getSex().equals("female") ? "checked" : ""%>
                                        type="radio" name="sex" value="female"
                                        id="female"/>
                                    <label class="light" for="female">Female</label>
                                    <label style="display:block;">Birthday</label>
                                    <div class="reg-input">
                                        <select name="day" id="days">
                                            <option>Day</option>
                                        </select>
                                        <select name="month" id="months">
                                            <option>Month</option>
                                        </select>
                                        <select name="year" id="years">
                                            <option>Year</option>
                                        </select>
                                    </div>
                                    <button type="submit">Save Changes</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <div class="middle-right-ads">
                <h4>Sponsored</h4>
                <div class="ads-item">
                    <img src="img/ad1.jpg">
                    <a href="#">ABC Banking</a>
                    <p>
                        ACB PRIVILEGE BANKING – TRỌN VẸN ĐẲNG CẤP Đồng hành cùng đẳng cấp và vị thế của bạn, phải...
                    </p>
                </div>
                <div class="ads-item">
                    <img src="img/ad2.jpg">
                    <a href="#">ABC Banking</a>
                    <p>
                        ACB PRIVILEGE BANKING – TRỌN VẸN ĐẲNG CẤP Đồng hành cùng đẳng cấp và vị thế của bạn, phải...
                    </p>
                </div>
                <div class="ads-item">
                    <img src="img/ad3.png">
                    <a href="#">ABC Banking</a>
                    <p>
                        ACB PRIVILEGE BANKING – TRỌN VẸN ĐẲNG CẤP Đồng hành cùng đẳng cấp và vị thế của bạn, phải...
                    </p>
                </div>
            </div>
        </div>
        <div class="col-md-2 no-pad">
            <jsp:include page="friendList.jsp">
                <jsp:param name="id" value="user"/>
            </jsp:include>
        </div>
    </div>
</section>
<script>
    $(function () {
        let birthday = '<%= user.getBirthday()%>'
        let [day,month,year] = birthday.toString().split('-')
        let i

        for (i = 1; i <= 31; i++) {
            if (i === parseInt(day)){
                $("#days").append("<option selected>" + i + "</option>");
            }else{
                $("#days").append("<option>" + i + "</option>");
            }
        }

        for (i = 1; i <= 12; i++) {
            if (i === parseInt(month)){
                $("#months").append("<option selected>" + i + "</option>");
            }else{
                $("#months").append("<option>" + i + "</option>");
            }
        }

        for (i = 2016; i >= 1905; i--) {
            if (i === parseInt(year)){
                $("#years").append("<option selected>" + i + "</option>");
            }else{
                $("#years").append("<option>" + i + "</option>");
            }
        }
        let viewportHeight = $(window).height();
        $("#online-list").css("max-height", viewportHeight);
    });


</script>
<script src="js/app.js"></script>
<script>
    const handleChangeAvatar = (e) => {
        let data = new FormData
        data.append('avatar', e.files[0])
        $.ajax({
            url: "${pageContext.servletContext.contextPath}/avatar",
            type:"Post",
            data: data,
            contentType: false,
            processData: false,
            success: function () {
                document.getElementById("avatarProfile").setAttribute('src', "${pageContext.servletContext.contextPath}/avatar")
                document.getElementById("avatarHeader").setAttribute('src', "${pageContext.servletContext.contextPath}/avatar")
            }
        })
    }
</script>
</body>

</html>
