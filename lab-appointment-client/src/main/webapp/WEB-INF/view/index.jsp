<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <!-- JSP standard tag library -->
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    <!-- spring security tags for user redirection -->
    <%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
    <security:authorize access="hasAnyRole('ADMIN','MANAGER','PATIENT')">
    	<% response.sendRedirect("auth/redirect"); %>
    </security:authorize>
    
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Welcome | Laboratory Appointment System</title>

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
                <li><a href="#" class="nav-link">Home</a></li>
                <li><a href="#a" class="nav-link">About</a></li>
                <li><a href="#s" class="nav-link">Services</a></li>
                <li>
                    <a href="#c" class="nav-link">Support</a>
                </li>
            </ul>
        </nav>

        <!-- 
            ===============
            header
            ===============
        -->
        <header class="header">
            <div class="nav-container">
                <div class="nav-child nav-login"><a href="auth/redirect"><span><i
                                class="fas fa-sign-in-alt"></i></span>&emsp;login</a></div>
                <div class="nav-child"><a href="register/page"><span><i class="fas fa-user-plus"></i></span>Register</a>
                </div>
            </div>
            <div class="banner">
                <h1>ABC Laboratories</h1>
                <a href="#b" class="btn banner-btn">explore</a>
            </div>
        </header>

        <!-- 
            ===============
            divider
            ===============
        -->
        <div class="content-divider"></div>

        <!-- 
            ===============
            Benefit section
            ===============
        -->
        <section class="benefit-section clearfix" id="b">
            <!-- benefit 1-->
            <article class="benefit">
                <span class="benefit-icon">
                    <i class="fas fa-heartbeat"></i>
                </span>
                <h4 class="benefit-title">provide better health</h4>

            </article>

            <!-- benefit 2-->
            <article class="benefit">
                <span class="benefit-icon">
                    <i class="fas fa-user-shield"></i>
                </span>
                <h4 class="benefit-title">Ensuring Your Safety</h4>
            </article>
            <!-- benefit 3-->
            <article class="benefit">
                <span class="benefit-icon">
                    <i class="fas fa-hand-holding-medical"></i>
                </span>
                <h4 class="benefit-title">We build trust and confidence</h4>
            </article>
            <!-- benefit 4-->
            <article class="benefit">
                <span class="benefit-icon">
                    <i class="fas fa-thumbs-up"></i>
                </span>
                <h4 class="benefit-title">verified lab results</h4>
            </article>
        </section>

        <!-- 
            ===============
            About section
            ===============
        -->
        <section id="a">
            <div class="section-center clearfix">
                <!-- about image -->
                <article class="about-image">
                    <div class="about-img-container">
                        <img src="${pageContext.request.contextPath}/assets/images/about-background.jpg" alt="fitness models" class="about-img" />
                    </div>
                </article>

                <!-- about info -->
                <article class="about-info">
                    <!-- title -->
                    <div class="section-title">
                        <h2>about us</h2>
                    </div>
                    <!-- text -->
                    <p class="about-text">
                        With more than 10 years of experience in laboratory testing including stringent quality
                        control programs and a high volume of lab tests – ABC Laboratories has one of the most
                        accomplished labs
                        in New Jersey.
                    </p>
                    <p class="about-text">
                        We offer convenient scheduling and trusted care for a variety of laboratory procedures.
                        Schedule your appointment online and get prompt care when you arrive.
                    </p>
                    <a href="login.html" class="btn">Our services</a>
                </article>
            </div>
        </section>

        <!-- 
            ===============
            Services section
            ===============
        -->
        <section class="services" id="s">
            <div class="section-center clearfix">
                <!-- services info -->
                <article class="service-info">
                    <!-- title -->
                    <div class="section-title">
                        <h2>laboratory services</h2>
                    </div>
                    <!-- text -->
                    <p class="services-text">
                        While we do our best to accommodate walk-ins, appointments are strongly encouraged.
                        Select the button below to book your laboratory service.
                    </p>
                    <a href="login.html" class="btn">make appointment</a>
                </article>

                <!-- service -->
                <article class="service-list clearfix">
                    <!-- service 1 -->
                    <div class="service">
                        <img src="${pageContext.request.contextPath}/assets/images/service1.jpg" alt="service 1" class="service-img" />
                        <h4 class="service-title">General lab services</h4>
                    </div>

                    <!-- service 2 -->
                    <div class="service">
                        <img src="${pageContext.request.contextPath}/assets/images/service2.jpg
                        " alt="service 2" class="service-img" />
                        <h4 class="service-title">
                            Outpatient Laboratory Services
                        </h4>

                    </div>

                    <!-- service 3 -->
                    <div class="service">
                        <img src="${pageContext.request.contextPath}/assets/images/service3.jpg" alt="service 3" class="service-img" />
                        <h4 class="service-title">Pathology services</h4>
                    </div>
                </article>
            </div>
        </section>



        <!-- 
            ===================
            Contact section
            ===================
        -->

        <section class="contact" id="c">
            <div class="section-center clearfix">
                <!-- contact info -->
                <article class="contact-info">
                    <!-- item 1-->
                    <div class="contact-item">
                        <h4 class="contact-title">
                            <span class="contact-icon"><i class="fas fa-location-arrow"></i></span>address
                        </h4>
                        <h4 class="contact-text">
                            438/A, Hospital road, Kalutara
                        </h4>
                    </div>

                    <!-- item 2-->
                    <div class="contact-item">
                        <h4 class="contact-title">
                            <span class="contact-icon"><i class="fas fa-envelope"></i></span>email
                        </h4>
                        <h4 class="contact-text">
                            abclabs@gmail.com
                        </h4>
                    </div>

                    <!-- item 2-->
                    <div class="contact-item">
                        <h4 class="contact-title">
                            <span class="contact-icon">
                                <i class="fas fa-phone-alt"></i></span>phone
                        </h4>
                        <h4 class="contact-text">+1234567890</h4>
                    </div>
                </article>

                <!-- contact form -->
                <article class="form" id="contact-form">
                    <h2>contact us</h2>
                    
                    <!-- CONTACT FORM -->
                    <form:form action="${pageContext.request.contextPath}/contact" modelAttribute="customerSupport" 
                    method="POST">
                        <!-- form group -->
                        <div class="form-group">
                            <!-- form control -->
                            <form:input path="name" placeholder="Name" class="form-control" />
                            <!-- form control -->
                            <form:input path="email" placeholder="Email" class="form-control" />
                            <!-- form control -->
                            <form:textarea path="message" cols="30" rows="5" placeholder="Your Message"
                                class="form-control"></form:textarea>
                        </div>
                        <!-- form control - button-->
                        <button type="submit" class="btn submit-btn">
                            submit
                        </button>
                    </form:form>
                    
                </article>
            </div>
        </section>
        
        <c:if test="${not empty message}">
			<script type="text/javascript">
				(function() {
				    alert("${message}");
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