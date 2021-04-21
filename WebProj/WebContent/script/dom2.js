        //form 생성
        var show = document.getElementById('show');
        var fTag = document.createElement('form');
        fTag.setAttribute('action', 'login.jsp');
        fTag.setAttribute('method', 'get');
        //Label => id: , pwd:
        var id = document.createTextNode('id : ');
        var pwd = document.createTextNode('passwd : ');
        // input => id , pwd
        var inputid = document.createElement('input');
        inputid.setAttribute('type', 'text');
        inputid.setAttribute('name', 'id');
        var inputpwd = document.createElement('input');
        inputpwd.setAttribute('type', 'password');
        inputpwd.setAttribute('name', 'passwd');

        var inputsub = document.createElement('input');
        inputsub.setAttribute('type', 'submit');
        inputsub.setAttribute('value', 'Send');

        var inputset = document.createElement('input');
        inputset.setAttribute('type', 'reset');
        inputset.setAttribute('value', 'Cancel');

        fTag.appendChild(id);
        fTag.appendChild(inputid);
        fTag.appendChild(pwd);
        fTag.appendChild(inputpwd);
        fTag.appendChild(inputsub);
        fTag.appendChild(inputset);
        document.body.appendChild(fTag);
        console.log(fTag);