function upload() {
  var c1 = document.getElementById("can");
  var fileinput = document.getElementById("fin");
  var img = new SimpleImage(fileinput);
  img.drawTo(c1);
}