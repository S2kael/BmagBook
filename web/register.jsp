<!DOCTYPE html>
<html>

<jsp:include page="head.jsp">
    <jsp:param name="Title" value="Register"/>
</jsp:include>

<body>
    <nav class="container-fluid" id="main-nav-login">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <a href="/" id="logo-large">BMAGBOOK</a>
                </div>
                <div class="col-md-6">
                    <form action="/login" method="post" class="login-form-top">
                        <div class="login-form-input">
                            <label>Email or phone</label>
                            <input type="text" name="user-login" />
                        </div>
                        <div class="login-form-input">
                            <label>Password</label>
                            <input type="password" name="user-login-password" />
                            <button type="submit" id="btn-login">Login</button>
                            <a href="/">Forgotten password?</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </nav>
    
    <section class="container-fluid" id="body-register-form">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h3>BMAGBOOK helps you connect and share with the people in your life.</h3>
                    <img src="img/user-map.png">
                </div>
                <div class="col-md-6">
                    <h3>Create an account</h3>
                    <p>It's free and always will be.</p>
                    <form action="/register" method="post" id="register-form" class="register-form">
                        <div class="reg-input-full-name">
                            <div class="reg-input" id="first-name">
                                <input type="text" name="first-name" value="<%= request.getAttribute("first-name") != null ? request.getAttribute("first-name") : ""%>" placeholder="First name">
                                <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
                                <span class="error-popup">Input your first name
                                    <span></span><span></span>
                                </span>
                            </div>
                            <div class="reg-input" id="last-name">
                                <input type="text" name="last-name" value="<%= request.getAttribute("last-name") != null ? request.getAttribute("last-name") : ""%>" placeholder="Last name">
                                <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
                                <span class="error-popup">Input your last name
                                    <span></span><span></span>
                                </span>
                            </div>
                        </div>
                        <div class="reg-input" id="mobile-or-email">
                            <input type="text" name="mobile-or-email" value="<%= request.getAttribute("mobile-or-email") != null ? request.getAttribute("mobile-or-email") : ""%>" placeholder="Mobile phone or email address">
                            <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
                            <span class="error-popup">Input your email or phone
                                <span></span><span></span>
                            </span>
                        </div>
                        <div class="reg-input" id="re-mobile-or-email">
                            <input type="text" name="re-mobile-or-email" value="<%= request.getAttribute("re-mobile-or-email") != null ? request.getAttribute("re-mobile-or-email") : ""%>" placeholder="Re-enter mobile phone or email address">
                            <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
                            <span class="error-popup">Re-input your email or phone
                                <span></span><span></span>
                            </span>
                        </div>
                        <div class="reg-input" id="user-password">
                            <input type="password" name="user-password"  placeholder="New password">
                            <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
                            <span class="error-popup">Input your password
                                <span></span><span></span>
                            </span>
                        </div>
                        <label style="display:block;">Birthday</label>
                        <div class="reg-input">
                            <select name="day" id="days" value="<%= request.getAttribute("day") != null ? request.getAttribute("day") : ""%>">
                                <option>Day</option>
                            </select>
                            <select name="month" id="months" value="<%= request.getAttribute("month") != null ? request.getAttribute("month") : ""%>">
                                <option>Month</option>
                            </select>
                            <select name="year" id="years" value="<%= request.getAttribute("year") != null ? request.getAttribute("year") : ""%>">
                                <option>Year</option>
                            </select>
                            <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
                            <span class="error-popup">Select your birthday
                                <span></span><span></span>
                            </span>
                        </div>
                        <div class="reg-input">
                            <input type="radio" <%= request.getAttribute("sex") == "female" ? " checked= 'true'" : ""%> name="sex" value="female"><label class="gender">Female</label>
                            <input type="radio" <%= request.getAttribute("sex") == "male" ? "checked= 'true'" : ""%> name="sex" value="male"><label class="gender">Male</label>
                        </div>
                        <p class="help-block">
                            By clicking Create an account, you agree to our Terms and that you have read our Data Policy, including our Cookie Use.
                        </p>
                        <button type="button" id="reg-button">Create an account</button>
                    </form>
                </div>
            </div>
        </div>
    </section>

    <footer class="container">
        <ul id="lans">
            <li>English (UK)</li>
            <li>
                <a href="#">Tiếng việt</a>
            </li>
            <li>
                <a href="#">中文(台灣)</a>
            </li>
            <li>
                <a href="#">한국어</a>
            </li>
            <li>
                <a href="#">日本語</a>
            </li>
            <li>
                <a href="#">Français (France)</a>
            </li>
            <li>
                <a href="#">ภาษาไทย</a>
            </li>
            <li>
                <a href="#">Español</a>
            </li>
            <li>
                <a href="#">Português (Brasil)</a>
            </li>
            <li>
                <a href="#">Deutsch</a>
            </li>
            <li>
                <a href="#">Italiano</a>
            </li>
            <li>+</li>
        </ul>
        <ul id="footer-tools">
            <li><a href="/">Sign Up</a></li>
            <li>
                <a href="#">Log In</a>
            </li>
            <li>
                <a href="#">Messenger</a>
            </li>
            <li>
                <a href="#">Facebook Lite</a>
            </li>
            <li>
                <a href="#">Mobile</a>
            </li>
            <li>
                <a href="#">Find Friends</a>
            </li>
            <li>
                <a href="#">Badges</a>
            </li>
            <li>
                <a href="#">People</a>
            </li>
            <li>
                <a href="#">Pages</a>
            </li>
            <li>
                <a href="#">Places</a>
            </li>
            <li>
                <a href="#">Games</a>
            </li>
            <li><a href="/">Locations</a></li>
            <li>
                <a href="#">Celebrities</a>
            </li>
            <li>
                <a href="#">Groups</a>
            </li>
            <li>
                <a href="#">Facebook Lite</a>
            </li>
            <li>
                <a href="#">Mobile</a>
            </li>
            <li>
                <a href="#">Find Friends</a>
            </li>
            <li>
                <a href="#">Badges</a>
            </li>
            <li>
                <a href="#">People</a>
            </li>
            <li>
                <a href="#">Pages</a>
            </li>
            <li>
                <a href="#">Places</a>
            </li>
            <li>
                <a href="#">Games</a>
            </li>
        </ul>
        BMAGBOOK &copy; 2016
    </footer>

    <script src="./js/app.js"></script>
    
    <script>
        $(function() {

            let day = "<%= request.getAttribute("day") != null ? Integer.parseInt((String) request.getAttribute("day"))  : 0%>"
            let month = "<%= request.getAttribute("month") != null ? Integer.parseInt((String) request.getAttribute("month"))  : 0%>"
            let year = "<%= request.getAttribute("year") != null ? Integer.parseInt((String) request.getAttribute("year"))  : 0%>"
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
        });
    </script>
</body>

</html>
