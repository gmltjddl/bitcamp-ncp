function jQuery(selector) {
 
  return new ElementBox(selector);
};
function ElementBox(selector) {
  this.el = []; 

  if(selector.startsWith("<")){
    this.el[0] = document.createElement(selector.substring(1,selector.length - 1));
  }else {
    let nodeList = document.querySelectorAll(selector);
    for (let e of nodeList) {
      this.el.push(e);
    }
  }
}

ElementBox.prototype.append = function(childBox) {
  //자식 태그를 복제해서 각 부모 태그에 붙인다.
  for(let parent of this.el){
    //자식들이 들어있는 상자에서 자식을 한개씩 꺼내 복제하여 각 부모의 자식에 붙인다.
    for(let child of childBox.el) {
    parent.appendChild(child.cloneNode(true));
    }
  }
  // 자식 태그는 제거한다 
  for(let child of childBox.el){
    if(child.parentElement != null || child.parentElement != undefined) {
     child.parentElement.removeChild(child);
    } 
 }
 return this; // 호출된 객체에 그대로 리턴
};
ElementBox.prototype.appendTo = function(parentBox) {
  //자식 태그를 복제해서 각 부모 태그에 붙인다.
  for(let parentTag of parentBox.el){
    //자식들이 들어있는 상자에서 자식을 한개씩 꺼내 복제하여 각 부모의 자식에 붙인다.
    for(let child of this.el) {
    parentTag.appendChild(child.cloneNode(true));
    }
  }
  // 자식 태그는 제거한다 
  for(let child of this.el){
    if(child.parentElement != null || child.parentElement != undefined) {
     child.parentElement.removeChild(child);
    } 
 }
 return this;
};
ElementBox.prototype.html = function(content) {
  if(this.el.length == 0){
    return;
  }
  if(arguments.length > 0) {
    // 파라미터 값이 있으면 setter로 동작한다. 
    for(let e of this.el) {
      e.innerHTML =content;
    }
    return this;
  }else {
   //파라미터 값이 없으면 getter로 동작한다.
    this.el[0].innerHTML;
  }
};

ElementBox.prototype.on = function(eventName, listener) {
  for(let e of this.el){
      e.addEventListener(eventName, listener);
      
  }
  return this;
};
 
ElementBox.prototype.click = function(handler) {
  return this.on('click', handler);
  // // for(let e of el){   주석과 위에 this.on과 같음 
  // //   e.addEventListener('click', handler);
  // }

};
//j
ElementBox.prototype.val = function(value) {
  if(this.el.length == 0){
    return;
  }
      //setter
  if (arguments.length >0){
    for(let e of this.el){
      e.value = value;
    }
    return this;

    //getter
  }else{
    return this.el[0].value;
  }
  
};

jQuery.ajax = function(settings) {
  if (settings.method == undefined) settings.method ="GET";
  if (settings.async == undefined) settings.async ="true";
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = () => {
    if (xhr.readyState == 4) {
        if (xhr.status == 200) {
          if(settings.success == undefined) {
            return;
          }
          let result;
          if(settings.dataType =="json"){
            //jsoN string->javascript object (deserialize)
            result = JSON.parse(xhr.responseText);
          }else{
            result = xhr.responseText;
          }
          settings.success(result);
    }else{
      if(settings.error == undefined){
        return;
      }
      settings.error();
    }
  }
};

xhr.open(settings.method, settings.url, settings.async);
xhr.send();
 
};

jQuery.getJSON = function(url, success) {
  jQuery.ajax({
    url : url,
    dataType :"json",
    success : success
  });
}



var $ = jQuery;