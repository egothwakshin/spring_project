const login = function(){
	
	const id = document.querySelector("#mid").value;
	const pw = document.querySelector("#aa").value;
	const checkbox = document.querySelectorAll(".ck");
	const checkArr = [];
	document.querySelectorAll("input[name='네임명칭']:checked")
	for(var a = 0; a < checkbox.length ; a++){
		checkArr[a] = checkbox[a].value;
	}
	JSON.stringify(checkArr);
	
	const user = {
		userid : id,
		userpw : pw	
	}
	fetch('/test/login',{
		method : "POST",
		headers : {
			"Content-Type" : "application/json"
		},
		body : JSON.stringify(user)
	})
	.then(function(response)	{
		return response.json();
	})
	.then(function(data){
		console.log(data.userid);
		//return data
	})
	.catch(function(error){
		console.log(error)
	})
}

document.querySelector("#btn").addEventListener("click", login);