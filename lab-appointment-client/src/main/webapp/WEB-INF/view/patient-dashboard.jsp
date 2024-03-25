<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Patient | Laboratory Appointment System</title>

        <!-- css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dashboard.css" />

        <!-- icons -->
        <script src="https://kit.fontawesome.com/18424ece7a.js" crossorigin="anonymous"></script>

        <!-- jquery table plugin -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css" />
        <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#table-test-results').DataTable();
                $('#table-appointments').DataTable();
            });
            
            /* DISABLE BROWSER BACK BUTTON */
            history.pushState(null, null, document.URL);
            window.addEventListener('popstate', function () {
                history.pushState(null, null, document.URL);
            });
        </script>
        
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
                    <div class="dashboard-child-3"><span>Patient Dashboard</span></div>
                    <div class="dashboard-child-4"> 
                    	<span>
                    		<i class="fas fa-user-circle"></i>&nbsp;<security:authentication property="principal.username"/>
                    		 
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
                        <a href="#medical-test-section" class="sidebar-link">lab reports</a>
                    </li>
                    <li>
                        <a href="#appointment-section" class="sidebar-link">appointments</a>
                    </li>
                    <li>
                        <a href="#payment-section" class="sidebar-link">pay bills</a>
                    </li>
                    <li>
                        <a href="#profile-section" class="sidebar-link">your account</a>
                    </li>
                </ul>
            </div>

            <!-- 
            ===============
            Content
            ===============
        -->
            <div class="content">

                <!-- 
                    ===============
                    Medical Tests
                    ===============
                    -->
                <div class="content1" id="medical-test-section">
                    <h4>Medical test results</h4>
                    
                    <table id="table-test-results" class="display">
                            <thead>
                                <tr>
                                    <th>Test ID</th>
                                    <th>testType</th>
                                    <th>reason</th>
                                    <th>laboratory</th>
                                    <th>dateTime</th>
                                    <th>labTechnician</th>
                                    <th>doctor</th>
                                    <th>overallResult</th>
                                    <th>labReport</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="tempReport" items="${testReports}">
	                            	<tr>
	                                    <td>${tempReport.id}</td>
	                                    <td>${tempReport.testType}</td>
	                                    <td>${tempReport.reason}</td>
	                                    <td>${tempReport.laboratory}</td>
	                                    <td>${tempReport.dateTime}</td>
	                                    <td>${tempReport.labTechnician}</td>
	                                    <td>${tempReport.doctor}</td>
	                                    <td>${tempReport.overallResult}</td>
	                                    <!-- ${tempReport.labReport} -->
	                                    <td><a href="${tempReport.labReport}" target="_blank" 
	                                    >Download</a></td>
	                                </tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                   
                    <br>
                    <p style="text-align: end; color: red;">*Your outstanding bill for medical service charges is
                        LKR XXX,XXX.XX.
                        <br><a href="#" class="btn">pay now</a></p>
                </div>
                

                <!-- 
                    ===============
                    Appointments
                    ===============
                    -->

                <div class="content2" id="appointment-section">
                    <div class="appointment-summary">
                        <h4>Your appointment schedules</h4>
                        <table id="table-appointments" class="display">
                            <thead>
                                <tr>
                                    <th>Appointment ID</th>
                                    <th>Date &amp; time</th>
                                    <th>Laboratory</th>
                                    <th>Number to undergo the test</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="tempItem" items="${appointments}">
                                	<tr>
	                                    <td>${tempItem.id}</td>
	                                    <td>${tempItem.date} &amp; ${tempItem.time}</td>
	                                    <td>${tempItem.laboratory}</td>
	                                    <td>${tempItem.patientNumber}</td>
                                	</tr>
                                </c:forEach>
                                
                            </tbody>
                        </table>
                    </div>

                    <div class="new-appointment">
                        <!-- appointment form -->
                        <article class="form" id="appointment-form">
                            <h3><span><i class="far fa-calendar-check"></i></span>Make an appointment</h3>
                            <!-- form -->
                            <form action="processAppointment" method="GET">
                                <!-- form group -->
                                <div class="form-group">
                                    <!-- form control -->
                                    <textarea name="description" cols="10" rows="5" class="form-control"
                                        placeholder="Describe about your medical condition..."></textarea>
                                    <!-- form control -->
                                    <label for="time">Select time</label>
                                    <input type="time" id="time" name="time" class="form-control" />
                                    <!-- form control -->
                                    <label for="date">Pick a date</label>
                                    <input type="date" name="date" placeholder="select date" class="form-control">
                                </div>
                                <!-- form control - button-->
                                <button type="submit" class="btn submit-btn">
                                    Request
                                </button>
                            </form>
                        </article>
                    </div>
                </div>
                
                
                <!-- 
                    ===============
                    Payments
                    ===============
                    -->
                <div class="content3" id="payment-section">
                    <h4>Pay your bills with a credit card!</h4>
                    <!-- payment form -->
                    <article class="form" id="payment-form">
                        <h3>Complete payment <span><i class="fab fa-cc-visa"></i></span> </h3>
                        <!-- form -->
                        <form:form action="processPayment" modelAttribute="paymentCard" method="POST">
                            <!-- form group -->
                            <div class="form-group">
                                <!-- form control -->
                                <div>
                                	<form:input path="cardName" placeholder="Name on card" class="form-control" />
                                	<form:errors path="cardName" cssClass="form-error"/>
                                </div>
                                
                                <!-- form control -->
                                <div>
                                	<form:input path="cardNumber" placeholder="card number" class="form-control" />
                                	<form:errors path="cardNumber" cssClass="form-error"/>
                                </div>
                                
                                <!-- form control -->
                                <div>
                                	<form:input path="cardExpireDate" placeholder="MM/YY" class="form-control" />
                                	<form:errors path="cardExpireDate" cssClass="form-error"/>
                                </div>
                                
                                <!-- form control -->
                                <div>
                                 	<form:input path="securityCode" placeholder="Security Code" class="form-control" />
                                	<form:errors path="securityCode" cssClass="form-error"/>
                                </div>
                               
                                <!-- form control -->
                                <div>
                                	<form:input path="zipCode" placeholder="Zip/Postal Code" class="form-control" />
                                	<form:errors path="zipCode" cssClass="form-error"/>
                                </div>
                            </div>
                            <!-- form control - button-->
                            <button type="submit" class="btn submit-btn">
                                pay now
                            </button>
                        </form:form>
                    </article>
                </div>

                <!-- 
                    ===============
                    Profile
                    ===============
                    -->

                <div class="content4" id="profile-section">
                    <article class="form" id="signup-form">
                        <h2>
                            <span><i class="fas fa-user-circle"></i></span> <br />
                                Your profile
                        </h2>
                        <form:form action="processProfile" modelAttribute="currentPatient" method="POST">
                        
                        	<form:hidden path="id"/>
                        	<form:hidden path="firstName"/>
                        	<form:hidden path="lastName"/>
                        	<form:hidden path="dateOfBirth"/>
                        	<form:hidden path="gender"/>
                        	
                            <!-- form group -->
                            <div class="form-group form-container">
                         
	                            <!-- form control -->
	                            <div class="form-child child-1">
	                                <form:input path="email" placeholder="* email" class="form-control" />
	                                <form:errors path="email" cssClass="form-error"/>
	                            </div>
	                            <!-- form control -->
	                            <div class="form-child child-2">
	                                <form:input path="phone" placeholder="* phone" class="form-control" />
	                                <form:errors path="phone" cssClass="form-error"/>
	                            </div>
	
	                            <!-- form control -->
	                            <div class="form-child child-3">
	                                <form:textarea  path="address" cols="30" rows="5" placeholder="* Address"
	                                    class="form-control"></form:textarea>
	                                    <form:errors path="address" cssClass="form-error"/>
	                            </div>
	
	                            <!-- form control -->
	                            <div class="form-child child-4">
	                                 <form:password path="password" placeholder="* Change Password" class="form-control" />
	                                 <form:errors path="password" cssClass="form-error"/>
	                            </div>
	                            
	                            <!-- form control -->
	                            <div class="form-child child-5">
	                                <form:password path="conPassword" placeholder="* Confirm Password"
	                                    class="form-control" />
	                                    <form:errors path="conPassword" cssClass="form-error"/>
	                            </div>
	                            
                        	</div>
                       <!--      <h4>
                                <a href="#">Give us a feedback!</a>
                            </h4> -->

                            <!-- form control - button-->
                            <button type="submit" class="btn submit-btn">
                                save
                            </button>
                        </form:form>
                    </article>
                </div>
            </div>
			
			
			<c:if test="${not empty updateStatus}">
				<script type="text/javascript">
					(function() {
					    alert("${updateStatus}");
					})();
				</script>
			</c:if>
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