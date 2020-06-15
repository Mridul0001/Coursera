function change_color(){
  var v1 = document.getElementById("i1");
  var v2 = document.getElementById("i2");
  v1.className = "blackback";
  v2.className = "greenback";
  v1.style.color = "yellow"; document.getElementById("div3").innerHTML = "Changed!";
  v1.value = "changed";
}

function confirm_clicked(){
  const choice = confirm("You are about to change button color")
  var message;
  if(choice){
    change_color();
  }else{
    message = "You pressed cancel!"
    alert(message);
  }
}

