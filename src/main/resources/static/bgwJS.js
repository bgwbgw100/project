bgw = {
    ajax: function(obj){

   /* obj.url
    obj.method
    obj.success
    obj.dataType*/

    let headerType="application/json"
    if(obj.dataType=="json"){
        headerType="application/json"
    }
    if(obj.dataType=="form"){
        headerType=="multipart/form-data";
    }

    let xhr = new XMLHttpRequest();
    // 요청을 초기화 합니다.
    xhr.open(obj.method, obj.url);
    // 보내는 본문의 Content-Type을 JSON으로 설정합니다.
    xhr.setRequestHeader('Content-Type', headerType);
    xhr.addEventListener('readystatechange', function (event) {

        let  target = event.target;

        if (target.readyState == XMLHttpRequest.DONE) {

            let  status  = target.status;
            if (status === 0 || (status >= 200 && status < 400)) {
                let data;
                let responseContentType = target.getResponseHeader("content-Type")

                if(responseContentType.includes("text/plain")){
                    data = target.responseText;
                }
                else if(responseContentType.includes("application/json")){
                    data = JSON.parse(target.responseText);
                }
                    //성공시
                obj.success(data);
            } else {
                   // 실패시
                obj.fail()
            }
        }
    });
     // 서버에 요청을 보냅니다.
    if(obj.dataType=="json"){
        xhr.send(JSON.stringify(obj.data));
    }
    if(obj.dataType=="form"){
            xhr.send(obj.data);
        }


    }
}
