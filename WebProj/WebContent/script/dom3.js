var names = [];
names[0] = '유정모';
names.push('구자혁'); //마지막 위치에 배열에 추가 push
names.push('석정원');
names.pop(); //마지막 위치에 push 했던 값 삭제
delete names[0]; //요소정보 삭제
names.shift(); //처음 위치 제거
names.unshift('김이담');
names.push('공도현');
names.push('소국진');
names.push('전형민');

var returnVal = names.map(function (val, index, ary) {
    var person = {}; //person object 선언
    person.name = val;
    person.num = index;
    return person;
});
document.write(names);
// Filter => true false 만 출력
var returnFilter = returnVal.filter(function (val, index, ary) {
    return index % 2 == 0;
});
console.log(returnFilter);

// names.forEach(function (val, index, ary){
//     // val = 배열의 value값 / index = 배열의 index값 / ary = 배열 그 자체
//     console.log(`names 요소 : ${val},${index},${ary}`);
// });
// console.log(names);