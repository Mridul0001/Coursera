var imgOg = null;
var imgMod = null;
var can = document.getElementById("can");

function upload(){
  var fileinput = document.getElementById("fin");
  imgOg = new SimpleImage(fileinput);
  imgOg.drawTo(can);
}
//gray filter
function doGrayScale() {
  if(imgOg == null || !imgOg.complete()){
    alert("Image not uploaded");
  }
  imgMod = null;
  imgMod = new SimpleImage(imgOg);
  doReset();
  for(var pixels of imgMod.values()){
    var r = pixels.getRed();
    var g = pixels.getGreen();
    var b = pixels.getBlue();
    var avg = Math.floor((r+g+b)/3);
    pixels.setRed(avg);
    pixels.setGreen(avg);
    pixels.setBlue(avg);
  }
  imgMod.drawTo(can);
}

//red filter
function doRed() {
  if(imgOg == null || !imgOg.complete()){
    alert("Image not uploaded");
  }
  imgMod = null;
  imgMod = new SimpleImage(imgOg);
  doReset();
  for(var pixels of imgMod.values()){
    var r = pixels.getRed();
    var g = pixels.getGreen();
    var b = pixels.getBlue();
    var avg = Math.floor((r+g+b)/3);
    if(avg < 128){
      pixels.setRed(avg*2);
      pixels.setGreen(0);
      pixels.setBlue(0);
    }else{
      pixels.setRed(255);
      pixels.setGreen(avg*2 - 255);
      pixels.setBlue(avg*2 - 255);
    }
  }
  imgMod.drawTo(can);
}

//rainbow filter
function doRainbow(){
  if(imgOg == null || !imgOg.complete()){
    alert("Image not uploaded");
  }
  imgMod = null;
  imgMod = new SimpleImage(imgOg);
  doReset();
  var strip = imgMod.getHeight()/7;
  for(var pixels of imgMod.values()){
    var y = pixels.getY();
    var r = pixels.getRed();
    var g = pixels.getGreen();
    var b = pixels.getBlue();
    var avg = Math.floor((r+g+b)/3);
    
    //add stripes according to height of image
    //red strip
    if(y<=strip){
      if(avg < 128){
        pixels.setRed(avg*2);
        pixels.setGreen(0);
        pixels.setBlue(0);
      }else{
        pixels.setRed(255);
        pixels.setGreen(avg*2 - 255);
        pixels.setBlue(avg*2 - 255);
      }
    }else if(y>strip && y<=strip*2){ //orange strip
      if(avg < 128){
        pixels.setRed(avg*2);
        pixels.setGreen(0.8*avg);
        pixels.setBlue(0);
      }else{
        pixels.setRed(255);
        pixels.setGreen(avg*1.2 - 51);
        pixels.setBlue(avg*2 - 255);
      }
    }else if(y>strip*2 && y<=strip*3){  //yellow strip
      if(avg < 128){
        pixels.setRed(avg*2);
        pixels.setGreen(2*avg);
        pixels.setBlue(0);
      }else{
        pixels.setRed(255);
        pixels.setGreen(255);
        pixels.setBlue(avg*2 - 255);
      }
    }else if(y>strip*3 && y<=strip*4){  //green strip
      if(avg < 128){
        pixels.setRed(0);
        pixels.setGreen(2*avg);
        pixels.setBlue(0);
      }else{
        pixels.setRed(avg*2 - 255);
        pixels.setGreen(255);
        pixels.setBlue(avg*2 - 255);
      }
    }else if(y>strip*4 && y<=strip*5){  //blue strip
      if(avg < 128){
        pixels.setRed(0);
        pixels.setGreen(0);
        pixels.setBlue(2*avg);
      }else{
        pixels.setRed(avg*2 - 255);
        pixels.setGreen(avg*2 - 255);
        pixels.setBlue(255);
      }
    }else if(y>strip*5 && y<=strip*6){  //indigo strip
      if(avg < 128){
        pixels.setRed(0.8*avg);
        pixels.setGreen(0);
        pixels.setBlue(2*avg);
      }else{
        pixels.setRed(avg*1.2 - 51);
        pixels.setGreen(avg*2 - 255);
        pixels.setBlue(255);
      }
    }else{  //violet strip
      if(avg < 128){
        pixels.setRed(1.6*avg);
        pixels.setGreen(0);
        pixels.setBlue(1.6*avg);
      }else{
        pixels.setRed(avg*0.4 + 153);
        pixels.setGreen(avg*2 - 255);
        pixels.setBlue(avg*0.4 + 153);
      }
    }
  } 
  imgMod.drawTo(can);
}

//blur function
function doBlur() {
  var i = Math.random()*10 + 1;
  if(imgOg == null || !imgOg.complete()){
    alert("Image not uploaded");
  }
  imgMod = null;
  imgMod = new SimpleImage(imgOg);
  doReset();
  
  for(var pixels of imgMod.values()){
    var xd = Math.random()*10 - 5;
    var yd = Math.random()*10 - 5;
    if(i<=5){
      var x = pixels.getX();
      var y = pixels.getY();
      if(x<=5 || y<=5 || x>=imgMod.getWidth()-5 || y>=imgMod.getHeight()-5)
        continue;
      var newPixel = imgMod.getPixel(x+xd,y+yd);
      imgMod.setPixel(x,y,newPixel);
    }
  }
  imgMod.drawTo(can);
}
  
//reset to original image
function doReset() {
  if(imgOg == null || !imgOg.complete()){
    alert("Image not uploaded");
  }
  imgOg.drawTo(can);
}