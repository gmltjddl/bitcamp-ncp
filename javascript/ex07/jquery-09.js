function jQuery(selector) {
  let el = []; //생성한 태그나 찾은 태그를 담는 배열

  if(selector.startsWith("<")){
    el[0] = document.createElement(selector.substring(1,selector.length - 1));
  }else {
    let nodeList = document.querySelectorAll(selector);
    for (let e of nodeList) {
      el.push(e);
    }
  }
  el.append = function(childBox) {
    //자식 태그를 복제해서 각 부모 태그에 붙인다.
    for(let parent of el){
      //자식들이 들어있는 상자에서 자식을 한개씩 꺼내 복제하여 각 부모의 자식에 붙인다.
      for(let child of childBox) {
      parent.appendChild(child.cloneNode(true));
      }
    }
    // 자식 태그는 제거한다 
    for(let child of childBox){
      if(child.parentElement != null || child.parentElement != undefined) {
       child.parentElement.removeChild(child);
      } 
   }
   return this; // 호출된 객체에 그대로 리턴
  };

  el.appendTo = function(parents) {
    //자식 태그를 복제해서 각 부모 태그에 붙인다.
    for(let parent of parents){
      //자식들이 들어있는 상자에서 자식을 한개씩 꺼내 복제하여 각 부모의 자식에 붙인다.
      for(let child of el) {
      parent.appendChild(child.cloneNode(true));
      }
    }
    // 자식 태그는 제거한다 
    for(let child of el){
      if(child.parentElement != null || child.parentElement != undefined) {
       child.parentElement.removeChild(child);
      } 
   }
   return this;
  };

  el.html = function(content) {
    for (let e of el) {
      e.innerHTML = content;
    }
    return this;
  };

  el.on = function(eventName, listener) {
    for(let e of el){
        e.addEventListener(eventName, listener);
        
    }
    return this;
  };
  el.click = function(handler) {
    this.on('click', handler);
    // // for(let e of el){   주석과 위에 this.on과 같음 
    // //   e.addEventListener('click', handler);
    // }
    return this;
  };
    return el;
}

var $ = jQuery;