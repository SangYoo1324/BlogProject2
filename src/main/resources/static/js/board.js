let index = {
    init: function() {
    //회원가입 btn-save event listener
    $("#btn-save").on("click", ()=>{
    this.save();
    });
    //로그인
    $("#btn-login").on("click", ()=>{
    this.login();
    });
    //로그아웃
        $("#logout").on("click", ()=>{
        console.log("로그아웃 버튼 클릭")
        this.logout();
        });
        //게시글 post
        $("#btn-post").on("click", ()=>{
        this.post();
        });
            //게시글 delete
                $("#btn-delete").on("click", (e)=>{
                this.delete(e);
                });
       //게시글 show post 창으로 이동
       $(".show-post-btn").on("click", ()=>{
       this.moveShowPost();
       });
           //게시글 modal edit 완료 버튼
             $("#btn-edit-modal").on("click", ()=>{
             console.log("edit 요청이 왔습니다");
             this.edit();
             });

    },

    //게시글 post
    post:function(){
//    alert("user saved함수 호출됨");
let data= {
title: $("#title").val(),
content: $("#content").summernote('code'),// summernote 텍스트 데이터 받아오는 방법
username: $("#username").val()
};
console.log(data);
//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
//fetch랑 비슷하다 보면 댐(data 객체를 받아서 java controller로 던짐)
$.ajax({// 데이터 object를 받아옴
    type:"POST",
    url:"/api/board/savePost/"+data.username,// 데이터를 보내줄 rest controller 주소
    data: JSON.stringify(data),
    contentType:"application/json; charset=utf-8",//body데이터가 어떤 타입인지(MIME)
    dataType: "json"//응답이 왔을 때

}).done(function(resp){//성공시 실행함수
alert("글쓰기가가 완료");
//alert(resp);// resp: /api/user로 받은 mapping 함수 return 값
location.href="/board/postList/"+data.username;
//게시글 다발
}).fail(function(error){//실패 시 실행함수
alert(JSON.stringify(error));
alert("post에 무언가가 잘못되었습니다");
});

    },
        //삭제
        //JQuery 방식
        delete:function(e){
        console.log("삭제 버튼이 클릭되었습니다");
        console.log($("#btn-delete"));
        let data={
       username: $("#btn-delete").attr("data-post-username"),
        board_id: $("#btn-delete").attr("data-post-board_id")
        };

//위하고 같은 방식(JavaScript)
//                       let  deletebtn= e.target;
//                       console.log(deletebtn);
//       let data={
//         username: deletebtn.getAttribute("data-post-username"),
//         board_id:  deletebtn.getAttribute("data-post-board_id")
//       };
        $.ajax({
        type: "DELETE",
        url: "/api/board/deletePost/"+data.username+"/"+data.board_id,
        data: JSON.stringify(data),
         contentType:"application/json; charset=utf-8",
         dataType: "json"

        }).done(function(resp){//성공시 실행함수
               alert("삭제 완료");
               alert(resp);// resp: /api/user로 받은 mapping 함수 return 값
               console.log(resp);
               location.href="/board/postList/"+data.username;
               }).fail(function(error){//실패 시 실행함수
               alert(JSON.stringify(error));
               });
        },


//로그인
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
        url:"/api/board/savePost/"+data.username,// 데이터를 보내줄 rest controller 주소
        data: JSON.stringify(data),
        contentType:"application/json; charset=utf-8",//body데이터가 어떤 타입인지(MIME)
        dataType: "json"//응답이 왔을 때

    }).done(function(resp){//성공시 실행함수
    alert("로그인완료");
    alert(resp);// resp: /api/user로 받은 mapping 함수 return 값
    console.log(resp);
    location.href="/board/postList"+data.username;
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
//                dataType: "json"//응답이 왔을 때 => data type에 맞는 entity가 있어야함

            }).done(function(resp){//성공시 실행함수
            alert("로그아웃 완료");
            alert(resp);// resp: /api/user로 받은 mapping 함수 return 값
            console.log(resp);

             location.href="/board/main";
          }).fail(function(error){//실패 시 실행함수
            alert(JSON.stringify(error));
            });

                },
 edit: function(){
 console.log("edit 요청이 왔습니다");
 //api 요청 주소에 username하고 board_id가 필요해서 가져옴

 let username= $("#modal-user-board_id-identifier").attr("data-username");
 let board_id =  $("#modal-user-board_id-identifier").attr("data-board_id");

 let data={
 title: $("#modal-title").val(),
 content: $("#modal-content").summernote('code')
     }
       $.ajax({
         type:"Patch",
         url:`/api/board/editPost/${username}/${board_id}`,
         data: JSON.stringify(data),
         contentType:"application/json; charset=utf-8",
         dataType: "json"

         }).done(function(response){
         alert("수정 완료");
         location.href=`/board/postList/show/${username}/${board_id}`;
         }).fail(function(error){
         alert("수정이 안됬습니다");

         });

 }

}

//Edit Modal Event 처리(Edit Modal이 뜨면 원래 post 내용 반영)
//Post modal fill
function modalFormFill (){
$("#modal-title").val($("#btn-edit").attr("data-post-title"));
$("#modal-content").summernote('code',$("#btn-edit").attr("data-post-content"));
};


//이 reply modal fill 왜 썼는지 모르겠음... prob unnecessary code
//Reply Modal fill
function replyModalFill(){
    $("#reply-modal-content").summernote('code',$("#reply-btn-edit").attr("data-reply-content"));
}






index.init();
//modal Filling button
modalFormFill();
replyModalFill();






//custom Alert
let alertMsg = document.getElementById("alert");
let customAlert = function(String){
alertMsg.innerHTML+=String;
alertMsg.style.display="block";
};