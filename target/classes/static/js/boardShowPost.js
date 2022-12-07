// 계정 접속 유저에 따라 게시글 삭제, 수정 버튼 숨김
// ex)현재 접속 유저 != 게시글 게시자 면 삭제나 수정 불가능하게 숨김
const deleteBtn= document.getElementById("btn-delete");
const editBtn =document.getElementById("btn-edit");
 if(editBtn.getAttribute("data-post-username")==
  editBtn.getAttribute("data-current-username")){
 deleteBtn.style.display = 'inline-block';
 editBtn.style.display = 'inline-block';
 }
 else{
   deleteBtn.style.display = 'none';
   editBtn.style.display = 'none';
  }




// 계정 접속 유저에 따라 덧글 삭제, 수정 버튼 숨김
// ex)현재 접속 유저 != 덧글 게시자 면 삭제나 수정 불가능하게 숨김

const replyDeleteBtn = $(".reply-btn-delete");
const replyEditBtn = $(".reply-btn-edit");
console.log("data-reply-user_id="+replyDeleteBtn.attr("data-reply-user_id"));
console.log("data-current-user_id="+replyDeleteBtn.attr("data-current-user_id"));

replyDeleteBtn.each(function(s){
    if( $(this).attr("data-reply-user_id")==$(this).attr("data-current-user_id")){
        $(this).css('display','inline-block');
        console.log($(this));
        console.log("생성됨");
    }else{$(this).css('display','none');
        console.log("생성 안됨");
    }


});

replyEditBtn.each(function(s){
    if( $(this).attr("data-reply-user_id")==$(this).attr("data-current-user_id")){
        $(this).css('display','inline-block');
        console.log($(this));
        console.log("생성됨");
    }else{$(this).css('display','none');
        console.log("생성 안됨");
    }

});



console.log(replyDeleteBtn);
console.log(replyEditBtn);






