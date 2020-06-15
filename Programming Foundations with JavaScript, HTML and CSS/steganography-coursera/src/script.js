//Image variables
var imgHide = null;
var imgHidein = null;
var imgDecrypt = null;
var imgNew = null;
//Variables to store different views
var setView = null;
var getView = null;
var view1 = document.getElementById("view1");
var view2 = document.getElementById("view2");
var view3 = document.getElementById("view3");
var view4 = document.getElementById("view4");
var loader = document.getElementById("anim");

//when encrypt is pressed
function selectedEncrypt(){
  getView = view1;
  setView = view2;
  //disable current view and set new view
  getView.style.display = "none";
  setView.style.display = "block";
}

//when decrypt is pressed
function selectedDecrypt(){
  getView = view1;
  setView = view3;
  //disable current view and set new view
  getView.style.display = "none";
  setView.style.display = "block";
}

//file upload functions
function uploadDecrypt(){
  var fileToDecrypt = document.getElementById("to_decrypt");
  imgDecrypt = new SimpleImage(fileToDecrypt);
}

function uploadHide(){
  var fileToHide = document.getElementById("to_hide");
  imgHide = new SimpleImage(fileToHide);
}

function uploadHidein(){
  var fileToHideIn = document.getElementById("hide_in");
  imgHidein = new SimpleImage(fileToHideIn);
}

//Control functions
//reset
function reset(){
  view1.style.display = "block";
  view2.style.display = "none";
  view3.style.display = "none";
  view4.style.display = "none";
  imgHide = null;
  imgHidden = null;
  imgDecrypt = null;
}

function next(view) {
  if(view==="view2"){
    //check if file is uploaded or not
    if(imgHide == null || !imgHide.complete())
      alert("Upload the file you wish to hide!");
    else if(imgHidein == null || !imgHidein.complete())
      alert("Upload the file in which you wish to hide!");
    else{
      //set height and width of new image
      var maxHeight =  imgHide.getHeight()>imgHidein.getHeight()?imgHide.getHeight() : imgHidein.getHeight();
      var maxWidth =  imgHide.getWidth()>imgHidein.getWidth()?imgHide.getWidth() : imgHidein.getWidth();
      imgNew = new SimpleImage(maxWidth,maxHeight);
      view2.style.display = "none";
      loader.style.display = "block";
      //call function to encrypt files
      doEncrypt();
      setTimeout(function(){
        loader.style.display = "none";
        view4.style.display = "block";
      },7000);
    }
  }else{
    //check if files are uploaded or not
    if(imgDecrypt == null || !imgDecrypt.complete())
      alert("Upload the file you wish to decrypt!");
    else{
      //set height and width of new image
      imgNew = new SimpleImage(imgDecrypt.getWidth(),imgDecrypt.getHeight());
      view3.style.display = "none";
      loader.style.display = "block";
      //call function to decrypt the file
      doDecrypt();
      setTimeout(function(){
        loader.style.display = "none";
        view4.style.display = "block";
      },7000);
    }
  }
}

function previous() {
  reset(); /* :D mistake */
}

//Encryption function
function doEncrypt(){
  for(var pixels of imgHidein.values()){
    var x = pixels.getX();
    var y = pixels.getY();
    var red = pixels.getRed();
    var green = pixels.getGreen();
    var blue = pixels.getBlue();
    chopHidein(x, y, red, green, blue);
  }
  
  for(var pixels of imgHide.values()){
    var x = pixels.getX();
    var y = pixels.getY();
    var red = pixels.getRed();
    var green = pixels.getGreen();
    var blue = pixels.getBlue();
    completeEncryption(x, y, red, green, blue);
  }
  printToCanvas("view2");
}

//Decryption function
function doDecrypt(){
  for(var pixels of imgDecrypt.values()){
    var x = pixels.getX();
    var y = pixels.getY();
    var red = pixels.getRed();
    var green = pixels.getGreen();
    var blue = pixels.getBlue();
    completeDecryption(x, y, red, green, blue);
  }
  printToCanvas("view3");
}

//helper functions
function chopHidein(x, y, red, green, blue){
  var newPixel = imgHidein.getPixel(x,y);
  var newRed = Math.floor(red/16)*16;
  var newGreen = Math.floor(green/16)*16;
  var newBlue = Math.floor(blue/16)*16;
  newPixel.setRed(newRed);
  newPixel.setGreen(newGreen);
  newPixel.setBlue(newBlue);
  imgNew.setPixel(x,y,newPixel);
}

function completeEncryption(x, y, red, green, blue){
  var newPixel = imgHidein.getPixel(x,y);
  var newRed = newPixel.getRed() + Math.floor(red/16);
  var newGreen = newPixel.getGreen() + Math.floor(green/16);
  var newBlue = newPixel.getBlue() + Math.floor(blue/16);
  newPixel.setRed(newRed);
  newPixel.setGreen(newGreen);
  newPixel.setBlue(newBlue);
  imgNew.setPixel(x,y,newPixel);
}

function completeDecryption(x, y, red, green, blue){
  var newPixel = imgDecrypt.getPixel(x,y);
  var newRed = (newPixel.getRed()%16)*16;
  var newGreen = (newPixel.getGreen()%16)*16;
  var newBlue = (newPixel.getBlue()%16)*16;
  newPixel.setRed(newRed);
  newPixel.setGreen(newGreen);
  newPixel.setBlue(newBlue);
  imgNew.setPixel(x,y,newPixel);
}

function printToCanvas(view){
  var outmsg = document.getElementById("outmsg");
  var canvas = document.getElementById("can");
  if(view === "view2"){
    outmsg.innerHTML = "Download your 'encrypted' file by right click";
  }else{
    outmsg.innerHTML = "Download your 'decrypted' file by right click";
  }
  imgNew.drawTo(canvas);
}