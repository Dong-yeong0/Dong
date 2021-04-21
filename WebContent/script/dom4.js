var ps = document.querySelector('div>p');
ps.textContent = 'hello';
ps.style.backgroundColor = 'yellow';
// var ps = document.querySelectorAll('div>p'); //div 자식으로 있는 p태그 전부 다 선택
// console.log(ps);
// ps.forEach(function(val){
//     console.log(val);
//     // val.innerHTML = '<h1>hello</h1>'; // p태그 안에 hello 대입
//     // val.innerText = '<h1>Hello</h1>'; // 텍스트로 출력
//     val.textContent = '<h1>Hello</h1>'; // 텍스트로 출력
//     val.setAttribute('style','color : red');
//     val.style.backgroundColor ='green';
// });
// numbers => 1~10
// filter, map, forEach => filter : 짝수만 걸러내도록. => evenVal라는 변수에 담기
// evenVal => map : n * 3; => mapVal 에 담기
// mapVal => console.log(각각 출력);
var numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
// => arrow function
numbers.filter((index) => index % 2 == 0)
    .map((index) => index * 3)
    .forEach((index) => console.log(index));

var sum = (a, b) => a + b; //arrow function
console.log(sum(20, 30));