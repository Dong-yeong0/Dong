// dom.js

var h1Tag = document.createElement('h1');
h1Tag.innerHTML = 'Hello';
console.log(h1Tag);
var aTag = document.createElement('a');
aTag.setAttribute('href', 'http://www.daum.net'); //a태그의 속성에 대입
// aTag.innerHTML = '다음 페이지';
var text = document.createTextNode('다음 사이트');
aTag.appendChild(text); // a태그의 textNode에 대입
console.log(aTag);

// document.body.appendChild(h1Tag);
// document.body.appendChild(aTag);

var show = document.getElementById('show');
//<div style="background-color : red;">
show.setAttribute('style', 'background-color : red');
show.appendChild(h1Tag);
show.appendChild(aTag);