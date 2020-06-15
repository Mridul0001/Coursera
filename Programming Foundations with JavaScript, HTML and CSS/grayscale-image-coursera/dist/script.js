var imgIn;
function upload() {
  var c1 = document.getElementById("canorg");
  var fileinput = document.getElementById("fin");
  imgIn = new SimpleImage(fileinput);
  imgIn.drawTo(c1);
}

function makeGray() {
  for(var pixels of imgIn.values()){
    var r = pixels.getRed();
    var g = pixels.getGreen();
    var b = pixels.getBlue();
    var avg = Math.floor((r+g+b)/3);
    pixels.setRed(avg);
    pixels.setGreen(avg);
    pixels.setBlue(avg);
  }
  var c2 = document.getElementById("cangray");
  imgIn.drawTo(c2);
}