//domEx.js
function cretaeTitle() {
    console.log(arguments);
    var trTag = document.createElement('tr');
    for (var i = 0; i < arguments.length; i++) {
        var td1 = document.createElement('th');
        td1.innerHTML = arguments[i];
        trTag.appendChild(td1);
    }
    tbl.appendChild(trTag);
}

function createData() {
    for (var person of persons) {
        var trTag = document.createElement('tr');
        trTag.setAttribute('id', person.id);
        trTag.onmouseover = mouseOverFnc;
        trTag.onmouseout = mouseOutFnc;

        for (var field in person) { //4번 반복
            if (field == 'id') {
                var tdTag = document.createElement('td');
                tdTag.onclick = modifyFunc;
                var text = document.createTextNode(person[field]);
                trTag.appendChild(tdTag);
                tdTag.appendChild(text);
            } else if (field == 'name') {
                var tdTag = document.createElement('td');
                var link = document.createElement('a');
                link.setAttribute('href', 'dom.jsp?name=' + person.name + '&id=' + person.id + '&score=' + person.score + '&gender=' + person.gender + '');
                link.innerHTML = person.name;
                tdTag.appendChild(link);
                trTag.appendChild(tdTag);
            } else {
                var tdTag = document.createElement('td');
                var text = document.createTextNode(person[field]);
                trTag.appendChild(tdTag);
                tdTag.appendChild(text);
            }
        }
        //삭제버튼
        var btn = document.createElement('button');
        btn.innerHTML = '삭제';
        btn.onclick = deleteRow;

        var tdTag = document.createElement('td');
        tdTag.appendChild(btn);
        trTag.appendChild(tdTag);
        tbl.appendChild(trTag);
    }
}

function mouseOverFnc() {
    this.style.backgroundColor = 'yellow';
}

function mouseOutFnc() {
    this.style.backgroundColor = '';
}

function deleteRow() {
    console.log(this.parentNode.parentNode.remove()); // 이벤트를 받는 객체 : this
}

function modifyFunc() {
    console.log(this);
    var idVal = this.innerHTML;
    var nameVal = this.previousSibling.innerHTML;
    var ScoreVal = this.nextSibling.innerHTML;
    var genVal = this.parentNode.childNodes[3].innerHTML;
    console.log(idVal, nameVal, ScoreVal, genVal);

    document.getElementById('name').value = nameVal;
    document.getElementById('id').value = idVal;
    document.getElementById('score').value = ScoreVal;
    var genders = document.querySelectorAll('input[name="gender"]');
    for (var i = 0; i < genders.length; i++) {
        if (genders[i].value == genVal) {
            genders[i].checked = true;
        }
    }
}

function saveBtnFnc() {
    var iName = document.getElementById('name');
    var iId = document.querySelector('input[name="id"]');
    var iScore = document.getElementsByTagName('input')[2]; //3번째 요소
    var iGender = document.querySelector('input[name="gender"]:checked');
    // console.log(iGender.value);

    var trTag = document.createElement('tr');
    // name
    var tdTag = document.createElement('td');
    tdTag.innerHTML = iName.value;
    trTag.appendChild(tdTag);
    // id
    var tdTag = document.createElement('td');
    tdTag.innerHTML = iId.value;
    trTag.appendChild(tdTag);
    // score
    var tdTag = document.createElement('td');
    tdTag.innerHTML = iScore.value;
    trTag.appendChild(tdTag);
    // gender
    var tdTag = document.createElement('td');
    tdTag.innerHTML = iGender.value;
    trTag.appendChild(tdTag);
    // button
    var btn = document.createElement('button');
    btn.innerHTML = '삭제';
    btn.onclick = deleteRow;

    var tdTag = document.createElement('td');
    trTag.onmouseover = mouseOverFnc;
    trTag.onmouseout = mouseOutFnc;
    tdTag.appendChild(btn);
    trTag.appendChild(tdTag);
    tbl.appendChild(trTag);
}
//수정버튼
function modifyBtnFnc() {
    var id = document.getElementById('id').value;
    var targetTr = document.getElementById(id);
    console.log(targetTr);
    targetTr.children[0].innerHTML = document.getElementById('name').value;
    targetTr.children[2].innerHTML = document.getElementById('score').value;
    targetTr.children[3].innerHTML = document.querySelector('input[name="gender"]:checked').value;
}
// for (var person of persons) {
//     var tr1 = document.createElement('tr');
//     for (var field in person) {
//         var td1 = document.createElement('td');
//         var td2 = document.createElement('td');
//         td1.innerHTML = person[field];
//         var btns = document.createElement('button');
//         btns.setAttribute('id', 'bt');
//         btns.textContent = '삭제';
//         tr1.appendChild(td1);
//     }
//     tr1.appendChild(td2);
//     td2.appendChild(btns);
//     var tbl = document.getElementById('tbl');
//     tbl.appendChild(tr1, btns);
// }
// var btn = document.querySelectorAll('tr>td>button')

// for (var i = 0; i < btn.length; i++) {
//     btn[i].onclick = function () {
//         var del = document.getElementById('bt').parentNode.previousSibling.parentNode.childNodes[0].firstChild.nodeValue;
//         delFunc(del);
//         document.getElementById('bt').parentNode.parentNode.remove();
//     }
// }
// console.log(document.getElementById('bt').parentNode.previousSibling.parentNode.childNodes[0]);
// function delFunc(del) {
//     console.log(`${del} 삭제됨`);
// }

// window.onload = function () {   //최상위 객체 . 페이지가 실행되는 시점에서 로딩이 되면 함수 실행
//     console.log(2);
//     var saveBtn = document.getElementById('saveBtn');
//     console.log(saveBtn);
// }