<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Manager Home | Laboratory Appointment System</title>

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

        <style>
            .form-container {
                display: grid;
                grid-template-columns: repeat(4, 1fr);
                grid-auto-rows: minmax(100px, auto);
                column-gap: 10px;
                align-items: center;
                grid-template-areas:
                    "a b c c"
                    "d d d d"
                    "x x e f"
                    "x x z m";
            }
        </style>
    
    	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	    <script type="text/javascript">
	      google.charts.load("current", {packages:["corechart"]});
	      google.charts.setOnLoadCallback(drawChart);
	      function drawChart() {
	        var data = google.visualization.arrayToDataTable([
	          ['Element', 'Number of Elements'],
	          ['Appointments',     5],
	          ['Patients',      11],
	          ['Lab Reports Submitted',  9]
	        ]);
	
	        var options = {
	          title: 'Records of Patients',
	          is3D: true,
	        };
	
	        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
	        chart.draw(data, options);
	      }
	    </script>
    	
	    	<script type="text/javascript">
			    google.charts.load("current", {packages:["corechart"]});
			    google.charts.setOnLoadCallback(drawChart);
			    function drawChart() {
			      var data = google.visualization.arrayToDataTable([
			        ["Service", "How much consumed", { role: "style" } ],
			        ["General lab services", 8.94, "#b87333"],
			        ["Outpatient Laboratory Services", 10.49, "silver"],
			        ["Pathology services", 19.30, "gold"]
			      ]);
			
			      var view = new google.visualization.DataView(data);
			      view.setColumns([0, 1,
			                       { calc: "stringify",
			                         sourceColumn: 1,
			                         type: "string",
			                         role: "annotation" },
			                       2]);
			
			      var options = {
			        title: "Lab Services Trend",
			        width: 600,
			        height: 400,
			        bar: {groupWidth: "95%"},
			        legend: { position: "none" },
			      };
			      var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
			      chart.draw(view, options);
			    }
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
                    <div class="dashboard-child-3"><span>Manager Dashboard</span></div>
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
                        <a href="#appointment-section" class="sidebar-link">patient appointments</a>
                    </li>
                    <li>
                        <a href="#medical-test-section" class="sidebar-link">Medical test records</a>
                    </li>
                    
                    <li>
                        <a href="#mgtReports" class="sidebar-link">Management Reports</a>
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
                    Patient Appointments
                    ===============
                    -->
                <div class="content2" id="appointment-section">
                    <div class="appointment-summary">
                        <h4>Patient appointments</h4>
                        <table id="table-appointments" class="display">
                            <thead>
                                <tr>
                                    <th>Appointment ID</th>
                                    <th>Patient Name</th>
                                    <th>Date &amp; Time</th>
                                    <th>Description</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="item" items="${appointmentList}">
                            		<c:url var="link" value="loadAppointment">
                            			<c:param name="patientID" value="${item.id}"></c:param>
                            		</c:url>
                            	
								   <tr>
	                                    <td>${item.id}</td>
	                                    <td>${item.patientName}</td>
	                                    <td>${item.date} ${item.time}</td>
	                                    <td>${item.description}</td>
	                                    <td><a href="${link}">Select</a></td>
									</tr>
								</c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="new-appointment">
                        <!-- appointment form -->
                        <article class="form" id="payment-form">
                            <h3><span><i class="far fa-calendar-check"></i></span>Confirm appointment</h3>
                            <!-- form -->
                            <form:form action="confirmAppointment" method="POST" modelAttribute="selectedAppointment">
                            
                            	<form:hidden path="description"/>
                            	<form:hidden path="enabled"/>
                            	<form:hidden path="patientName"/>
                            	
                                <!-- form group -->
                                <div class="form-group">
                                    <!-- form control -->
                                    <form:input path="id" placeholder="Appointment ID" class="form-control" />
                                     <form:errors path="id" cssClass="form-error"/>
                                    <!-- form control -->
                                    <form:input path="date" placeholder="Date" class="form-control" />
                                     <form:errors path="date" cssClass="form-error"/>
                                    <!-- form control -->
                                    <form:input path="time" placeholder="Time" class="form-control" />
                                     <form:errors path="time" cssClass="form-error"/>
                                    <!-- form control -->
                                    <form:input path="laboratory" placeholder="laboratory" class="form-control" />
                                     <form:errors path="laboratory" cssClass="form-error"/>
                                    <!-- form control -->
                                    <form:input path="patientNumber" placeholder="Number to undergo"
                                        class="form-control" />
                                         <form:errors path="patientNumber" cssClass="form-error"/>
                                </div>
                                <!-- form control - button-->
                                <button type="submit" class="btn submit-btn">
                                    Confirm
                                </button>
                            </form:form>
                        </article>
                    </div>
                </div>


                <!-- 
                    ===============
                    Medical Tests
                    ===============
                -->
                <div class="content1" id="medical-test-section">

                    <h4>Organize medical test reports</h4>

                    <h4>Lab reports</h4>
                    <table id="table-test-results" class="display">
                        <thead>
                            <tr>
                                <th>Test ID</th>
                                <th>patient</th>
                                <th>Type</th>
                                <th>Reason</th>
                                <th>Laboratory</th>
                                <th>Date &amp; Time</th>
                                <th>Lab technician</th>
                                <th>Doctor</th>
                                <th>Overall test result</th>
                                <th>Report URL</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="tempItem" items="${reportList}">
                            
                            	<c:url var="link2" value="loadReport">
                            			<c:param name="reportID" value="${tempItem.id}"></c:param>
                            	</c:url>
                            	
                            	<c:url var="link3" value="deleteReport">
                            			<c:param name="reportID" value="${tempItem.id}"></c:param>
                            	</c:url>	
                            		
	                            <tr>
	                                <td>${tempItem.id}</td>
	                                <td>${tempItem.patientName}</td>
	                                <td>${tempItem.testType}</td>
	                                <td>${tempItem.reason}</td>
	                                <td>${tempItem.laboratory}</td>
	                                <td>${tempItem.dateTime}</td>
	                                <td>${tempItem.labTechnician}</td>
	                                <td>${tempItem.doctor}</td>
	                                <td>${tempItem.overallResult}</td>
	                                <td><a href="${tempItem.labReport}" target="_blank">Report URL</a></td>
	                                <td><a href="${link3}" 
	                                onclick="if(!confirm('Are you sure you want to delete this report?')) return false;">delete</a></td>
	                            </tr>
                            </c:forEach>
                            
                        </tbody>
                    </table>
                    
                    <br>
                    
                    <!-- Lab report form -->
                    <article class="form" id="signup-form">
                        <h2>
                            <span><i class="fas fa-user-plus"></i></span> <br />
                            Create a lab report
                        </h2>
                        
                        <!-- LAB REPORT FORM -->
                        <form:form action="saveReport" method="POST" modelAttribute="medReport">
                        
                        	<form:hidden path="id"/>
                       		<form:hidden path="dateTime"/>
                       		
                            <!-- form group -->
                            <div class="form-group form-container">
                               <!-- form control -->
                                <div class="form-child child-1">
                                    <form:input path="patientName" placeholder="Patient Name" class="form-control" />
                                </div> 
                                <!-- form control -->
                                <div class="form-child child-2">
                                    <form:input path="testType" placeholder="test type" class="form-control" />
                                </div>
                                <!-- form control -->
                                <div class="form-child child-3">
                                    <form:input path="laboratory" placeholder="Laboratory" class="form-control" />
                                </div>
                                <!-- form control -->
                                <div class="form-child child-4">
                                    <form:input path="labReport" placeholder="Report URL" class="form-control" />
                                </div>

                                <!-- form control -->
                                <div class="form-child child-9">
                                    <form:radiobutton path="overallResult" value="negative" />&nbsp; <span>Negative</span>
                                    &emsp;
                                    <form:radiobutton path="overallResult" value="positive" />&nbsp; <span>Positive</span>
                                </div>

                                <!-- form control -->
                                <div class="form-child child-7">
                                    <form:textarea path="reason" ols="30" rows="5" placeholder="Reason"
                                        class="form-control"></form:textarea>
                                </div>

                                <!-- form control -->
                                <div class="form-child child-8">
                                    <form:input path="doctor" placeholder="Doctor recommended"
                                        class="form-control" />
                                </div>

                                <!-- form control -->
                                <div class="form-child child-5">
                                    <form:input path="labTechnician" placeholder="Lab technician" class="form-control" />
                                </div>
                            </div>

                            <!-- form control - button-->
                            <button type="submit" class="btn submit-btn">
                                Update & Store
                            </button>
                        </form:form>
                        
                    </article>
                </div>
                <br>
                <!-- 
	            ===============
	            Management Reports
	            ===============
	        	-->
                <h4 id="mgtReports">Data Charts for Managerial Decision Making</h4>
                <br>
                <div class="content3">
                	<div id="piechart_3d" style="width: 900px; height: 500px;"></div>
                	<br>
                	<div id="barchart_values" style="width: 900px; height: 300px;"></div>
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