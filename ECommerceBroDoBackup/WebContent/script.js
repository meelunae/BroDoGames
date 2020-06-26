function validateSignUp(obj){
		
	$("#name").focus(function (){
		
		$("#name").css("background-color", "white");
		$("#errNome").html("");
		
	});
	
	$("#surname").focus(function (){
		
		$("#surname").css("background-color", "white");
		$("#errCognome").html("");
		
	});
	
	$("#date").focus(function (){
		
		$("#date").css("background-color", "white");
		$("#errData").html("");
		
	});
	
	$("#email").focus(function(){
	
		$("#email").css("background-color", "white");
		$("#errEmail").html("");
		
	});
	
	$("#username").focus(function(){
		
		$("#username").css("background-color", "white");
		$("#errUsername").html("");
	
	});
	
	$("#password").focus(function(){
		
		$("#password").css("background-color", "white");
		$("#errPw").html("");
		
	
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

	//Per evitare che lo sfondo continui ad apparire rosso se si sbagliano le credenziali e che appaia la scritta di errore
	$("#email").focus(function(){
		
		$("#email").css("background-color", "white");
		$("#errEmail").html("");
		
	});
	
	$("#pw").focus(function(){
		
		$("#pw").css("background-color", "white");
		$("#errPw").html("");
	
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
	
	var pattern=/^[A-Za-z]+(\s([A-Za-z])+)*$/;
	if(name.match(pattern)){
		
		return true;
		
	}
	
	return false;
	
}

function checkData(nascita){
	
	var anno = nascita.substring(0, nascita.indexOf("-"));
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
	var request = "Carrello?action=updateQta&id=" + idProd + "&newQtaFis=" + newFis + "&newQtaDig=" + newDig;
	
	$.get(request, function(data){
		
		//fisDef e digDef servono per il calcolo del totale
		var fisDef = newFis;
		var digDef = newDig;
		var count = parseInt($("#counter").html(), 10);
		
		if(newFis == 0 && newDig == 0){
			
			$("#qtaCart").html("Carrello (" + data.sizeCart + ")");  //Aggiorna l'header
			$("#row"+i).remove();	//Rimuovi quella riga
			count = count - 1;		//Riduci il contatore che indica il numero di prodotti
			$("#counter").html(count);	//Aggiorna il contatore
			
		}

		if(!data.esito){	//se una delle due quantità eccede la quantità presente nel catalogo

			if(newFis != data.nuovoFisico){		//Se è la quantità fisica ad eccedere 
				
				$("#fis" + i).val(data.nuovoFisico);
				$("#fis" + i).css("background-color", "rgba(255, 0, 0, 0.5)");
				$("#errFis" + i).html("Quantita' ecceduta!");
				$("#errFis" + i).css("color", "red");
				fisDef = data.nuovoFisico;
				
			} else {	//Pulisci gli eventuali errori generati prima
				
				$("#fis" + i).val(newFis);
				$("#fis" + i).css("background-color", "white");
				$("#errFis" + i).html("");
				
			}

			if(newDig != data.nuovoDigitale){		//Se è la quantità digitale ad eccedere stessa cosa fatta per la quantità fisica
				

				$("#dig" + i).val(data.nuovoDigitale);		
				$("#dig" + i).css("background-color", "rgba(255, 0, 0, 0.5)");	
				$("#errDig" + i).html("Quantita' ecceduta!");
				$("#errDig" + i).css("color", "red");
				digDef = data.nuovoDigitale;
				
			} else {
				
				$("#dig" + i).val(newDig);
				$("#dig" + i).css("background-color", "white");
				$("#errDig" + i).html("");
				
			}
			
		} else {		//se invece i valori introdotti sono entrambi ok pulisci eventuali errori generati prima
			
			$("#fis" + i).val(newFis);
			$("#fis" + i).css("background-color", "white");
			$("#errFis" + i).html("");
			$("#dig" + i).val(newDig);
			$("#dig" + i).css("background-color", "white");
			$("#errDig" + i).html("");
					
		}
		
		
		if(count == 0){
			
			$("#tabella").append("<tr><td>Nessun prodotto nel carrello<td><tr>");
			$("#totd").remove();
			$("#checkout-submit").remove();
			
		}
		
		var prezzoF = parseFloat($("#priceF"+i).html());	//salvo il prezzo fisico
		var prezzoD = parseFloat($("#priceD"+i).html());	//salvo il prezzo digitale
		var totParziale = prezzoF * fisDef + prezzoD * digDef;	//calcolo il totale

		$("#totParziale"+i).html(totParziale.toFixed(2) + " EUR");		//aggiorno il totale parziale
		var importoTotale = 0;
		for(k = 0; k < count; k++){
			
			if(parseFloat($("#totParziale"+k).html()) >= 0){
				

				importoTotale += parseFloat($("#totParziale"+k).html())

				
			} else {
				
				count = count + 1;
				
			}
		}
		
		$("#totd").html("Totale Ordine: " + importoTotale.toFixed(2) + " EUR");

	});		
		
}

function remove(obj, count){
	
	$("#fis" + count).val("0");
	$("#dig" + count).val("0");
	setQta(obj, count);
	
}

function aggiungiCarrello(tipo, id){

	if(tipo == "fis"){
		var a;
		$.get("Catalogo?action=addC&tipo=fisico&id=" + id, function(data){
		
			if(data.esito == true){
				
				window.clearTimeout();
				$("#qtaCart").html("Carrello " + "(" + data.sizeCart + ")");
				$("#"+id).html("Aggiunto");
				$("#"+id).css("color", "#66FF00");
				a = window.setTimeout(function(){
				
					$("#"+id).html("");
					window.clearTimeout();
					
				}, 2000);
				
					
			} else {
				
				window.clearTimeout();
				$("#qtaCart").html("Carrello " + "(" + data.sizeCart + ")");
				$("#"+id).html("Non disponibile");
				$("#"+id).css("color", "red");
				a = window.setTimeout(function(){
				
					$("#"+id).html("");
					window.clearTimeout();
					
				}, 2000);	

			}
			
		});
	
	} else {
		
		$.get("Catalogo?action=addC&tipo=digitale&id=" + id, function(data){
			
			if(data.esito == true){
				
				window.clearTimeout();
				$("#qtaCart").html("Carrello " + "(" + data.sizeCart + ")");
				$("#"+id).html("Aggiunto");
				$("#"+id).css("color", "#66FF00");
				window.setTimeout(function(){
				
					$("#"+id).html("");
					window.clearTimeout();
					
				}, 2000);
					
			} else {
				
				window.clearTimeout();
				$("#qtaCart").html("Carrello " + "(" + data.sizeCart + ")");
				$("#"+id).html("Non disponibile");
				$("#"+id).css("color", "red");
				window.setTimeout(function(){
				
					$("#"+id).html("");
					window.clearTimeout();
					
				}, 2000);		
	
			}
			
		});
		
	}
}

$(document).ready(function(){
	
	$("input").focus(function(){
		
		$(this).css("border", "thick solid blue");
		
	});
	
	$("input").blur(function(){
	
		$(this).css("border", "none");
		
	});
	
	$(".custom-table img").mouseover(function(){
		
		if(window.innerWidth > 1000){
		
			$(this).animate({
				
				width: '50%'
				
			});
			
		}
		
	});
	
	
	
	$(".custom-table img").mouseout(function(){
		
		if(window.innerWidth > 1000){

			$(this).animate({
				
				width: '40%'
				
			});
			
		}
		
	
	});
	
	
	
});

function removeWishList(obj, count){
	
	$("#"+count).remove();
	
}