{
    /* <button>클릭</button>
    <button>클릭</button>
    <button>클릭</button> */
}

// <table border="1"><tr><td>성진아</td></tr>...</table>
var buttons = document.getElementsByTagName('button');
console.log(buttons);
for (var i = 0; i < buttons.length; i++) {
    buttons[i].setAttribute('onclick', 'showTable()');
}
var p1 = {
    name: '성진아',
    score: 80,
    gender: '여'
}
var p2 = {
    name: '김수민',
    score: 83,
    gender: '여'
}
var p3 = {
    name: '장재우',
    score: 85,
    gender: '남'
}
for (var field in p3) {
    console.log(field, p3[field]);
}
var persons = [p1, p2, p3];
for (var p of persons) {
    console.log(`이름요소 : ${p.name} 점수요소 : ${p.score}`);
}

function showTable() {
    var tbl = document.createElement('table');
    tbl.setAttribute('border', '1');
    for (var person of persons) { //배열에서 반복
        var tr1 = document.createElement('tr');
        for (var field in person) { //person의 배열갯수 만큼 td 생성 / object에서 반복
            var td1 = document.createElement('td');
            td1.innerHTML = person[field]; //person이 들고있는 field값 을 td1에 대입
            tr1.appendChild(td1);
        }
        tbl.appendChild(tr1);
    }
    var show = document.getElementById('show');
    show.appendChild(tbl);
    console.log(tbl);
}