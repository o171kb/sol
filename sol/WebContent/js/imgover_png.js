//이미지 오버처리

function imgRolloverpng(){
  if(!document.getElementsByTagName) return;

  var item = document.getElementsByTagName('*');
  for(var i=0; i<item.length; i++){
    if(item[i].className == 'imgoverpng'){
      var src = item[i].getAttribute('src');


      item[i].onmouseover = function(){
        var img = this.src.split('.png');
        this.src = img[0] + '_on.png';
      }


      item[i].onmouseout = function(){
        var img = this.src.split('_on.png');
        this.src = img[0] + '.png';
      }
    }
  }
}
window.onload = function(){
  imgRolloverpng();
}