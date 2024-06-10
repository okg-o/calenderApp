var elmSubmit = document.getElementById("ID_SUBMIT");
elmSubmit.onclick = function(){
	var elmName   = document.getElementById("NAME_ID");
  var elmEmail   = document.getElementById("EMAIL_ID");
  var elmMessage = document.getElementById("MASSAGE_ID");
  var canSubmit = true;
  if(elmName.value == ""||elmEmail.value == ""|| elmMessage.value == ""){
    alert("不正な入力項目があります。");
    canSubmit = false;
  }
  return canSubmit;
}
