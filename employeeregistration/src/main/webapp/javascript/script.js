$(document).ready(function() {
	// Attach blur event handlers for AJAX validation
	$('#username').blur(validateUsernameField);
	$('#contactnumber').blur(validateContactNumberAjax);

	// Attach input event handlers for real-time validation
	$('#firstname').on('input', validateFirstNameField);
	$('#lastname').on('input', validateLastnameField);
	$('#password').on('input', validatePasswordField);
	$('#contactnumber').on('input', validateContactNumberField);
	$('#address').on('input', validateAddressField);

	// Attach click event handler for password visibility toggle
	const togglePassword = document.querySelector(".password-toggle-icon i");
	togglePassword.addEventListener("click", function() {
		const passwordField = document.getElementById("password");
		if (passwordField.type === "password") {
			passwordField.type = "text";
			togglePassword.classList.remove("fa-eye");
			togglePassword.classList.add("fa-eye-slash");
		} else {
			passwordField.type = "password";
			togglePassword.classList.remove("fa-eye-slash");
			togglePassword.classList.add("fa-eye");
		}
	});

	// Attach input event handlers to enable/disable the submit button
	var form = document.getElementById('formId');
	var inputs = form.querySelectorAll('input');
	inputs.forEach(function(input) {
		input.addEventListener('input', function(event) {
			validateForm();
		});
	});

	// Close popup event handler
	var close = document.getElementById("close");
	close.addEventListener("click", function() {
		$('#formId')[0].reset();
		var popup = document.getElementById("popup");
		popup.style.display = "none";
	});
	$('#registerButton').click(function() {
		// Example AJAX form submission
		$.ajax({
			type: "POST",
			url: "register",
			data: $('#formId').serialize(),
			success: function(response) {
				response = 'success';
				if (response == "success"){
				$('#formId')[0].reset(); // Clear the form	
				}
				validateForm(); // Revalidate the form to disable the button
			}
		});
	});
});


function validateUsernameField() {
	var username = $('#username').val();
	if (username === '') {
		$('#user-message').html('<span style="color:red;">Username is required</span>');
		validateForm();
		return;
	}
	$.ajax({
		type: "POST",
		url: "register",
		data: { username: username },
		dataType: "html",
		success: function(result) {
			if (result === 'exist') {
				$('#user-message').html('<span style="color:red;">User already exists</span>');
			} else {
				$('#user-message').html('');
			}
			validateForm();
		}
	});
}

function validateContactNumberAjax() {
	var contactnumber = $('#contactnumber').val();
	if (contactnumber === '') {
		$('#contact-message').html('<span style="color:red;">Contact number is required</span>');
		validateForm();
		return;
	}
	$.ajax({
		type: "POST",
		url: "register",
		data: { contactnumber: contactnumber },
		dataType: "html",
		success: function(result) {
			if (result === 'exist') {
				$('#contact-message').html('<span style="color:red;">Contact number already exists</span>');
			} else {
				$('#contact-message').html('');
			}
			validateForm();
		}
	});
}

function validateFirstNameField() {
	var firstName = $('#firstname').val();
	var lettersRegex = /^[A-Za-z]+$/;
	if (firstName.length < 2) {
		$('#firstname-message').html('<span style="color:red;">First name minimum 2 characters</span>');
	} else if (firstName.length > 30) {
		$('#firstname-message').html('<span style="color:red;">First name maximum 30 characters</span>');
	} else if (!lettersRegex.test(firstName)) {
		$('#firstname-message').html('<span style="color:red;">First name must contain only letters</span>');
	} else {
		$('#firstname-message').html('');
	}
	validateForm();
}

function validateLastnameField() {
	var lastName = $('#lastname').val();
	var lettersRegex = /^[A-Za-z]+$/;
	if (lastName.length < 2) {
		$('#lastname-message').html('<span style="color:red;">Last name minimum 2 characters</span>');
	} else if (lastName.length > 30) {
		$('#lastname-message').html('<span style="color:red;">Last name maximum 30 characters</span>');
	} else if (!lettersRegex.test(lastName)) {
		$('#lastname-message').html('<span style="color:red;">Last name must contain only letters</span>');
	} else {
		$('#lastname-message').html('');
	}
	validateForm();
}

function validatePasswordField() {
	var password = $('#password').val();
	var capitalRegex = /[A-Z]/;
	var smallRegex = /[a-z]/;
	var specialRegex = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
	if (password.length < 10 || !capitalRegex.test(password) || !smallRegex.test(password) || !specialRegex.test(password)) {
		$('#password-message').html('<span style="color:red;">Password must contain at least 1 capital letter, 1 small letter, 1 special character, and be at least 10 characters long.</span>');
	} else {
		$('#password-message').html('');
	}
	validateForm();
}

function validateContactNumberField() {
	var contactNumber = $('#contactnumber').val();
	var numberRegex = /^[0-9]+$/;
	if (!numberRegex.test(contactNumber)) {
		$('#contact-message').html('<span style="color:red;">Contact number must contain only numbers</span>');
	} else if (contactNumber.length < 10) {
		$('#contact-message').html('<span style="color:red;">Contact number minimum 10 characters</span>');
	} else if (contactNumber.length > 17) {
		$('#contact-message').html('<span style="color:red;">Contact number maximum 17 characters</span>');
	} else {
		$('#contact-message').html('');
	}
	validateForm();
}

function validateAddressField() {
	var address = $('#address').val();
	if (address === '') {
		$('#address-message').html('<span style="color:red;">*Required</span>');
	} else if (address.length < 2) {
		$('#address-message').html('<span style="color:red;">Address minimum 2 characters</span>');
	} else if (address.length > 70) {
		$('#address-message').html('<span style="color:red;">Address maximum 70 characters</span>');
	} else {
		$('#address-message').html('');
	}
	validateForm();
}

function validateForm() {
	var firstNameValid = $('#firstname-message').html() === '';
	var lastNameValid = $('#lastname-message').html() === '';
	var userNameValid = $('#user-message').html() === '';
	var passwordValid = $('#password-message').html() === '';
	var contactNumberValid = $('#contact-message').html() === '';
	var addressValid = $('#address-message').html() === '';

	var firstName = $('#firstname').val().trim();
	var lastName = $('#lastname').val().trim();
	var userName = $('#username').val().trim();
	var password = $('#password').val().trim();
	var contactNumber = $('#contactnumber').val().trim();
	var address = $('#address').val().trim();

	var submitButton = $('#registerButton');

	if (firstNameValid && lastNameValid && userNameValid && passwordValid && contactNumberValid && addressValid &&
		firstName !== '' && lastName !== '' && userName !== '' && password !== '' && contactNumber !== '' && address !== '') {
		submitButton.prop('disabled', false);
	} else {
		submitButton.prop('disabled', true);
	}
}

function successFullMessage() {
	var popup = $('#popup');
	popup.show();
	setTimeout(function() {
		popup.hide();
	}, 1500);
}