let index = {
    init: function() {
    //btn-save event listener
    $("#btn-save").on("click", ()=>{
    this.save();
    });
    $("#btn-login").on("click", ()=>{
    this.login();
    });
        $("#logout").on("click", ()=>{
        console.log("로그아웃 버튼 클릭")
        this.logout();
        });
    },
    save:function(){
//    alert("user saved함수 호출됨");
let data= {
username: $("#username").val(),
password: $("#password").val(),
email: $("#email").val()
}
console.log(data);
//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
//fetch랑 비슷하다 보면 댐(data 객체를 받아서 java controller로 던짐)
$.ajax({// 데이터 object를 받아옴
    type:"POST",
    url:"/api/user",// 데이터를 보내줄 rest controller 주소
    data: JSON.stringify(data),
    contentType:"application/json; charset=utf-8",//body데이터가 어떤 타입인지(MIME)
    dataType: "json"//응답이 왔을 때

}).done(function(resp){//성공시 실행함수
alert("회원가입이 완료");
alert(resp);// resp: /api/user로 받은 mapping 함수 return 값
console.log(resp);
location.href="/user/loginForm";
}).fail(function(error){//실패 시 실행함수
alert(JSON.stringify(error));
});

    },

        login:function(){
    //    alert("user saved함수 호출됨");
    let data= {
    username: $("#username").val(),
    password: $("#password").val(),
    }
    console.log(data);
    //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
    //fetch랑 비슷하다 보면 댐(data 객체를 받아서 java controller로 던짐)
    $.ajax({// 데이터 object를 받아옴
        type:"POST",
        url:"/api/user/login",// 데이터를 보내줄 rest controller 주소
        data: JSON.stringify(data),
        contentType:"application/json; charset=utf-8",//body데이터가 어떤 타입인지(MIME)
        dataType: "json"//응답이 왔을 때

    }).done(function(resp){//성공시 실행함수
    alert("로그인 완료");
    alert(resp);// resp: /api/user로 받은 mapping 함수 return 값
    console.log(resp);
    window.location.reload();
    location.href="/board/main/"+data.username;
    }).fail(function(error){//실패 시 실행함수
    alert(JSON.stringify(error));
    });

        },
               logout:function(){
            //    alert("user saved함수 호출됨");
            let data= 1;
            console.log(data);
            //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
            //fetch랑 비슷하다 보면 댐(data 객체를 받아서 java controller로 던짐)
            $.ajax({// 데이터 object를 받아옴
                type:"POST",
                url:"/api/user/logout",// 데이터를 보내줄 rest controller 주소
                data: JSON.stringify(data),
                contentType:"application/json; charset=utf-8",//body데이터가 어떤 타입인지(MIME)
//                dataType: "json"//응답이 왔을 때

            }).done(function(resp){//성공시 실행함수
            alert("로그아웃 완료");
            alert(resp);// resp: /api/user로 받은 mapping 함수 return 값
            console.log(resp);
            location.href="/board/main";

           }).fail(function(error){//실패 시 실행함수
            alert(JSON.stringify(error));
            });

                }



}

//Login 시 navbar JS
//let welcome = document.getElementById("welcome");
//let write = document.getElementById("write");
//let logout = document.getElementById("logout");
//
//if( write.dataset.status== "loggin"){
//    write.innerHTML="Write";
//    logout.innerHTML="Logout";
//    console.log("로그인 되었습니다");
//
//}
console.log("연결확인");
index.init();

//Logout btn
