let index = {
    init: function() {
    //btn-save event listener
    $("#btn-save").on("click", ()=>{
    this.save();
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

    }

}

console.log("연결확인");
index.init();