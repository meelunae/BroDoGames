function validateSignUp(obj){
		
	$("#name").focus(function (){
		
		$("#name").css("background-color", "white");
		
	});
	
	$("#surname").focus(function (){
		
		$("#surname").css("background-color", "white");
		
	});
	
	$("#date").focus(function (){
		
		$("#date").css("background-color", "white");
		
	});
	
	$("#email").focus(function(){
	
		$("#email").css("background-color", "white");
		
	});
	
	$("#username").focus(function(){
		
		$("#username").css("background-color", "white");
	
	});
	
	$("#password").focus(function(){
		
		$("#password").css("background-color", "white");
	
	});
	
	var valid = true;
	var name = $("#name").val();
	if(!checkNomeCognome(name)){
		
		$("#name").css("background-color", "rgba(255, 0, 0, 0.5)");
		$("#errNome").html("Formato nome non valido!");
		$("#errNome").css("color", "red");
		valid = false;
		
	} else {
		
		$("#name").css("background-color", "white");
		$("#errNome").html("");

	}
	
	var cognome = $("#surname").val();
	if(!checkNomeCognome(cognome)){
		
		$("#surname").css("background-color", "rgba(255, 0, 0, 0.5)");
		$("#errCognome").html("Formato cognome non valido!");
		$("#errCognome").css("color", "red");
		valid = false;
		
	} else {
		
		$("#surname").css("background-color", "white");
		$("#errCognome").html("");

	}
	
	var nascita = $("#date").val();
	console.log(nascita);
	if(!checkData(nascita)){
		
		$("#date").css("background-color", "rgba(255, 0, 0, 0.5)");
		$("#errData").html("Data non valida!");
		$("#errData").css("color", "red");
		valid = false;
		
	} else {
		
		$("#date").css("background-color", "white");
		$("#errData").html("");		
		
	}
	
	var email = $("#email").val();
	if(!checkEmail(email)){
		
		$("#email").css("background-color", "rgba(255, 0, 0, 0.5)");
		$("#errEmail").html("Formato email non valida!");
		$("#errEmail").css("color", "red");
		valid = false;

	} else {
		
		$("#email").css("background-color", "white");
		$("#errEmail").html("");
		
	}
	
	var username = $("#username").val();
	if(!checkUsername(username)){
		
		$("#username").css("background-color", "rgba(255, 0, 0, 0.5)");
		$("#errUsername").html("Formato username non valido!");
		$("#errUsername").css("color", "red");
		valid = false;
		
	} else {
		
		$("#username").css("background-color", "white");
		$("#errUsername").html("");
		
	}
	
	var password = $("#password").val();
	if(!checkPassword(password)){
		
		$("#password").css("background-color", "rgba(255, 0, 0, 0.5)");
		$("#errPw").html("La password deve contenere almeno 8 caratteri, almeno una maiuscola, una minuscola, un numero!");
		$("#errPw").css("color", "red");
		valid = false;

	} else {
		
		$("#password").css("background-color", "white");
		$("#errPw").html("");
		
	}
	
	if(valid){
		
		obj.submit();
		
	}
	
}

function validateLogIn(obj){
	console.log("CIAO");
	$("#email").focus(function(){
		
		$("#email").css("background-color", "white");
		
	});
	
	$("#pw").focus(function(){
		
		$("#pw").css("background-color", "white");
	
	});
	
	var valid = true;
	var email = $("#email").val();
	if(!checkEmail(email)){
		
		$("#email").css("background-color", "rgba(255, 0, 0, 0.5)");
		$("#errEmail").html("Formato email non valido!");
		$("#errEmail").css("color", "red");
		valid = false;
		
	} else {
		
		$("#email").css("background-color", "white");
		$("#errEmail").html("");
		
	}
	
	var pw = $("#pw").val();
	if(!checkPassword(pw)){
		
		$("#pw").css("background-color", "rgba(255, 0, 0, 0.5)");
		$("#errPw").html("La password deve contenere almeno 8 caratteri con almeno una maiuscola, una minuscola e un numero!");
		$("#errPw").css("color", "red");
		valid = false;
		
	} else {
		
		$("#pw").css("background-color", "white");
		$("#errPw").html("");
		
	}
	
	if(valid){
		
		obj.submit();
		
	}
	
}

function checkNomeCognome(name){
	
	var pattern=/^[A-Za-z]+$/;
	if(name.match(pattern)){
		
		return true;
		
	}
	
	return false;
	
}

function checkData(nascita){
	
	var anno = nascita.substring(0, nascita.indexOf("-"));
	console.log(anno);
	anno = parseInt(anno, 10);
	if(anno >= 1900 && anno <= (new Date()).getFullYear() - 13){
		
		return true;
		
	} 
	
	return false;
}

function checkEmail(email){
	
	var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(email.match(pattern)){
		
		return true;
		
	}
	
	return false;
	
}

function checkUsername(username){
	
	var pattern = /^\w+$/;
	if(username.match(pattern)){
		
		return true;
		
	}
	
	return false;
	
}

function checkPassword(password){
	
	var pattern = /^((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{8,32}$/;
	if(password.match(pattern)){
		
		return true;
		
	}
	
	return false;
	
}

function setQta(a, i){
	
	var newFis = $("#fis" + i).val();
	var newDig = $("#dig" + i).val();
	var idProd = $("#id" + i).val();
	var request = "CarrelloServlet?action=updateQta&id=" + idProd + "&newQtaFis=" + newFis + "&newQtaDig=" + newDig;
	$.getJSON(request, function(data){
		
		console.log(data);
		
	});
		
}