// 기존 jQuery에 기능 추가 

ElementBox.prototype.button = function(selector) {
  this.el.forEach((e) => {
    e.classList.add("btn");
  });
}
