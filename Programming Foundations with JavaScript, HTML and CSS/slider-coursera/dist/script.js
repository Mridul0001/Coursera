function doLime() {
  var c1 = document.getElementById("can");
  c1.style.backgroundColor = "lime";
}

function doColor(){
  var c1 = document.getElementById("can");
  var col = document.getElementById("col");
  c1.style.backgroundColor = col.value;
}

function makeSquare() {
  var c1 = document.getElementById("can");
  var slr = document.getElementById("sldr");
  var val = slr.value;
  var context = c1.getContext("2d");
  context.clearRect(0,0,c1.width,c1.height);
  context.fillStyle = "navy";
  context.fillRect(0,0,val,val);
  
}