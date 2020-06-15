var imgBG = null;
var imgFG = null;
function uploadFG(){
  var can = document.getElementById("fg");
  var input = document.getElementById("upfg");
  imgFG = new SimpleImage(input);
  imgFG.drawTo(can);
}

function uploadBG(){
  var input = document.getElementById("upbg");
  imgBG = new SimpleImage(input);
}

function doGreenScreen() {
  if(imgFG == null || !imgFG.complete()){
    alert("Foregroung image not uploaded");
  }else if(imgBG == null || !imgBG.complete()){
    alert("Backgroung image not uploaded");
  }else{
    var newImg = new SimpleImage(imgFG.getWidth(), imgFG.getHeight());
    var can = document.getElementById("gs");
    for(var pixel of imgFG.values()){
      var x = pixel.getX();
      var y = pixel.getY();
      
      if(pixel.getGreen()<240){
        newImg.setPixel(x,y,pixel);
      }else{
        newImg.setPixel(x,y,imgBG.getPixel(x,y));
      }
    }
    //draw new image to canvas
    newImg.drawTo(can);
  }
}

function reset() {
  document.getElementById("upfg").value = "";
  document.getElementById("upbg").value = "";
  
  var can1 = document.getElementById("fg")
  var context1 = can1.getContext("2d");
  context1.fillStyle = "white";
  context1.clearRect(0,0,can1.width,can1.height);
  
  var can2 = document.getElementById("gs")
  var context2 = can2.getContext("2d");
  context2.fillStyle = "white";
  context2.clearRect(0,0,can2.width,can2.height);
}