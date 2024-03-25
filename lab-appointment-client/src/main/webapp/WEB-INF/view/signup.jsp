<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Sign Up | Laboratory Appointment System</title>

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
            sign up section
            ===================
        -->

        <section class="form-section">
            <div class="section-center clearfix">
                <!-- signup form -->
                <article class="form" id="signup-form">
                    <h2>
                        <span><i class="fas fa-user-plus"></i></span> <br />
                        user registration
                    </h2>
                    
                    <!-- SIGN UP FORM  -->
                    <form:form action="processform" modelAttribute="patient" method="POST">
                        <!-- form group -->
                        <div class="form-group form-container">
                            <!-- form control -->
                            <div class="form-child child-1">
                                <form:input path="lastName" placeholder="* surname" class="form-control" />
                                <form:errors path="lastName" cssClass="form-error"/>
                            </div>
                            <!-- form control -->
                            <div class="form-child child-2">
                                <form:input path="firstName" placeholder="* first name" class="form-control" />
                                <div><form:errors path="firstName" cssClass="form-error"/></div>
                            </div>
                            <!-- form control -->
                            <div class="form-child child-3">
                                <form:input path="email" placeholder="* email" class="form-control" />
                                <form:errors path="email" cssClass="form-error"/>
                            </div>
                            <!-- form control -->
                            <div class="form-child child-4">
                                <form:input path="phone" placeholder="* phone" class="form-control" />
                                <form:errors path="phone" cssClass="form-error"/>
                            </div>

                            <!-- form control -->
                            <div class="form-child child-5">
                                <form:radiobutton path="gender" value="male"/>&nbsp; <span>male</span>
                                &emsp;
                                <form:radiobutton path="gender" value="female" />&nbsp; <span>female</span>
                                <br>
                                <form:errors path="gender" cssClass="form-error"/>
                            </div>

                            <!-- form control -->
                            <div class="form-child child-6">
                                <form:input path="dateOfBirth" placeholder="* DOB:  dd/mm/yyyy" class="form-control" />
                                <form:errors path="dateOfBirth" cssClass="form-error"/>
                            </div>

                            <!-- form control -->
                            <div class="form-child child-7">
                                <form:textarea  path="address" cols="30" rows="5" placeholder="* Address"
                                    class="form-control"></form:textarea>
                                    <form:errors path="address" cssClass="form-error"/>
                            </div>

                            <!-- form control -->
                            <div class="form-child child-9">
                                 <form:password path="password" placeholder="* Password" class="form-control" />
                                 <form:errors path="password" cssClass="form-error"/>
                            </div>
                            
                            <!-- form control -->
                            <div class="form-child child-8">
                                <form:password path="conPassword" placeholder="* Confirm Password"
                                    class="form-control" />
                                    <form:errors path="conPassword" cssClass="form-error"/>
                            </div>
                        </div>
                        
                        <h4>
                            already have an account?
                            <span><a href="../login/page">login</a></span>
                        </h4>

                        <!-- form control - button-->
                        <button type="submit" class="btn submit-btn" >
                            sign up
                        </button>
                    </form:form>
                </article>
            </div>
        </section>
		
		<c:if test="${not empty signupStatus}">
			<script type="text/javascript">
				(function() {
				    alert("${signupStatus}");
				})();
			</script>
		</c:if>

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