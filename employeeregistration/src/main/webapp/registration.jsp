<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<div class="container">
		<form action="<%=request.getContextPath()%>/register" class="form" id="formId" method="post">
			<p class="title">Employee Register</p>
				<label>
					<input class="input" type="text" name="firstName" id="firstname" placeholder="" oninput="validateFirstNameField()">
					<span>Firstname</span>
					<span id="firstname-message"></span>
				</label>
				
				<label>
					<input class="input" type="text" name="lastName" id="lastname" placeholder="" oninput="validateLastnameField()" >
					<span>Lastname</span>
					<span id="lastname-message"></span>
				</label>

				<label>
					<input class="input" type="text" name="username" id="username" placeholder="" onblur="validateUsernameField()">
					<span>Username</span>
					<span id="user-message"></span>
				</label> 
			
				<label>
					<input class="input" type="password" name="password" id="password" placeholder="" oninput="validatePasswordField()" onblur="validatePasswordField()">
					<span>Password</span>
					<span id="password-message"></span>
					<span class="password-toggle-icon"><i class="fas fa-eye"></i></span>
				</label> 
			
				<label>
					<input class="input" type="text" name="contactnumber" id="contactnumber" placeholder="" oninput="validateContactNumberField()"">
					<span>Contactnumber</span>
					<span id="contact-message"></span>
				</label>
			
				<label>
					<input class="input" type="text" name="address" id="address" placeholder="" oninput="validateAddressField()" onblur="validateAddressField()">
					<span>Address</span>
					<span id="address-message"></span>
				</label>
			
				<button class="submit" name="submitRequest" id="registerButton" value="submit" onclick="successFullMessage()" disabled>Submit</button>
				
				<div class="popup" id="popup">
        			<p id="popupMessage">Registration Successful!</p>
    			</div>

				<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
				<script type="text/javascript" src="javascript/script.js"></script>
		</form>
	</div>
</body>
</html>