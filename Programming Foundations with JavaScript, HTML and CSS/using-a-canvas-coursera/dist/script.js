function doRed() {
  var c1 = document.getElementById("can1");
  c1.style.backgroundColor = "red";
}

function doGreen() {
  var c2 = document.getElementById("can2");
  c2.style.backgroundColor = "green";
  var con = c2.getContext("2d");
  con.fillStyle = "black";
  con.fillRect(10,10,50,50);
  con.fillRect(70,10,50,50);
  
  con.fillStyle = "white";
  con.font = "15px serif";
  con.fillText("hello!", 13, 30);
}

function doClear() {
  var can = document.getElementById("can2");
  
  var con = can.getContext("2d");
  con.fillStyle = "green";
  con.clearRect(10,10,110,50);
}