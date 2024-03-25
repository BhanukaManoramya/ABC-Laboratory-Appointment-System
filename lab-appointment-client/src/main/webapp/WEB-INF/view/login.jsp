<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Login | Laboratory Appointment System</title>

        <!-- css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form.css" />

        <!-- icons -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/js/all.min.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <body>
        <!-- 
            ===============
            navbar
            ===============
        -->

        <!-- nav button -->
        <span class="nav-btn" id="nav-btn">
            <i class="fas fa-bars"></i>
        </span>

        <!-- navbar -->
        <nav class="navbar" id="navbar">
            <div class="navbar-header">
                <span class="nav-close" id="nav-close">
                    <i class="fas fa-window-close"></i>
                </span>
            </div>
            <ul class="navbar-items">
                <li><a href="../home" class="nav-link">Back to Home</a></li>
            </ul>
        </nav>

        <!-- 
            ===================
           Login section
            ===================
        -->

        <section class="form-section">
            <div class="section-center clearfix">
                <!-- contact form -->
                <article class="form" id="login-form">
                    <h2><span><i class="fas fa-user-lock"></i></span> <br> User login</h2>
                    <br>
                    
                    <!-- LOGIN FORM -->
                    <form:form action="${pageContext.request.contextPath}/login/authenticate" method="POST" modelAttribute="activeuser">
                    
                    	<c:if test="${param.error != null}">
	                    	<div class="login-error">
	                    		<b> <span><i class="fas fa-exclamation-triangle"></i></span> Bad Login Credentials</b>
	                    	</div>
                    	</c:if>
                    	<c:if test="${param.logout != null}">
	                    	<div class="logout-message">
	                    		<b> <span><i class="fas fa-sign-out-alt"></i></span> You have been logged out!</b>
	                    	</div>
                    	</c:if>
                        <!-- form group -->
                        <div class="form-group">
                            <!-- form control -->
                            <form:input path="username" placeholder="username"  class="form-control" />
                            <!-- form control -->
                            <input type="password" name="password" placeholder="password" class="form-control" />

                        </div>
                        <br>
                        <h4>new user? <span><a href="../register/page">create account</a></span></h4>

                        <!-- form control - button-->
                        <button type="submit" class="btn submit-btn">
                            login
                        </button>
                    </form:form>
                    
                </article>
            </div>
        </section>

        <!-- 
            ===================
            Footer section
            ===================
        -->
        <footer class="footer">
            <div class="section-center">
                <!-- social icons -->
                <div class="social-icon">
                    <!-- icon 1 -->
                    <a href="#" class="social-icon">
                        <i class="fab fa-facebook"></i>
                    </a>
                    <!-- icon 2 -->
                    <a href="#" class="social-icon">
                        <i class="fab fa-youtube"></i>
                    </a>
                    <!-- icon 3 -->
                    <a href="#" class="social-icon">
                        <i class="fab fa-instagram"></i>
                    </a>
                </div>
                <h4 class="footer-text">
                    &copy;<span id="date"></span>
                    <span class="company">ABC Laboratories</span>, all right
                    reserved
                </h4>
            </div>
        </footer>
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    </body>

</html>