         for (var mon = 1; mon <= 12; mon++) {
            document.write(mon + '월');
            var today = new Date(2021, mon - 1, 1);
            var lastDay = new Date(2021, mon, 0); //mon달의 마지막 날
            console.log(today.getDay());
            console.log(lastDay.getDate());
            var days = ['sun', 'mon', 'tue', 'wed', 'thr', 'fri', 'sta']
            var tbl = '<table border="1"><tr>';
            for (var day of days) {
                tbl += '<th>' + day + '</th>';
            }
            tbl += '</tr><tr>';
            //공백
            for (var i = 1; i <= today.getDay(); i++) {
                tbl += '<td></td>';
            }
            for (var j = 1; j <= lastDay.getDate(); j++) {
                if ((j + today.getDay()) % 7 == 1) {
                    tbl += '</tr><tr>';
                }
                tbl += '<td>' + j + '</td>';
            }
            tbl += '</table>';
            document.write('<span>' + tbl + '</span>');
        }