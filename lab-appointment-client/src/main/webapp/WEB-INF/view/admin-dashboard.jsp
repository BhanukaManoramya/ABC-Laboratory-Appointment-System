<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Admin Home | Laboratory Appointment System</title>

        <!-- css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dashboard.css" />

        <!-- icons -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/js/all.min.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
       
        <script type="text/javascript">
	        /* DISABLE BROWSER BACK BUTTON */
	        history.pushState(null, null, document.URL);
	        window.addEventListener('popstate', function () {
	            history.pushState(null, null, document.URL);
	        });
        </script>

        <style>
            .form-container {
                display: grid;
                grid-template-columns: repeat(4, 1fr);
                grid-auto-rows: minmax(100px, auto);
                column-gap: 10px;
                align-items: center;
                grid-template-areas:
                    "a a b b"
                    "c c d d"
                    "x x e e"
                    "x x y z";
            }
        </style>
    </head>

    <body>

        <div class="container">

            <!-- 
            ===============
            Dashboard Header
            ===============
        -->
            <div class="dashboard-header">
                <div class="dashboard-container">
                    <div class="dashboard-child-1">
                        <span>ABC Laboratories</span>
                    </div>
                    <div class="dashboard-child-2"></div>
                    <div class="dashboard-child-3"><span>Admin Dashboard</span></div>
                    <div class="dashboard-child-4"> 
                    	<span>
                    		<i class="fas fa-user-circle"></i> Welcome <security:authentication property="principal.username"/> 
                    	</span>
                    </div>
                    <div class="dashboard-child-5">
                        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
	                        <button style="
	                        background: var(--secondaryColor); 
	                        color: var(--primaryColor);
	                        font-family: var(--secondaryFont);
	                        border: none;
	                        cursor: pointer;
	                        font-size: medium;
	                        " onclick="if(!confirm('Are you sure you want to log out?')) return false"><span><i
	                                    class="fas fa-sign-out-alt"></i>Log out</span></button>
                        </form:form>
                     </div>
                </div>
            </div>

            <!-- 
            ===============
            Sidebar
            ===============
        -->
            <div class="sidebar">
                <ul class="sidebar-items">
                    <li>
                        <a href="#user-account-section" class="sidebar-link">User account management</a>
                    </li>
                </ul>
            </div>

            <!-- 
            ===============
            Content
            ===============
        -->
            <div class="content">

                <!-- Login information of all users -->
                <h4>Note: Admin functionalities have not been implemented because It is not a project requirements or criteria (Assumption).</h4>
                    	
                <script type="text/javascript">
					(function() {
					    alert("Admin functionalities have not been implemented because It is not a project requirement or criteria (Assumption).");
					})();
				</script>
                    
            </div>




            </div>

            <!-- 
            ===================
            Footer section
            ===================
            -->
            <div class="myfooter">
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
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    </body>

</html>