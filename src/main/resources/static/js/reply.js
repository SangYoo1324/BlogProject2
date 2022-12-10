let replyIndex = {
    init: function () {

        $("#reply-btn-post").on("click", () => {
            this.saveReply(); // this = replyIndex
        });
        $(".reply-btn-edit").on("click",(e)=>{
            const targetBtn = e.target;
            console.log(targetBtn);
           this.editReply(targetBtn);
        });
        $(".btn-reply-edit-post").on("click",(e)=>{
            console.log("리플라이 업데이트 포스트 버튼 클릭");
            this.postEditedReply();
        })
        $(".reply-btn-delete").on("click",(e)=>{ //delete 버튼이 여러개 선택됨
           const targetBtn= e.target; //클릭이벤트에 관련된 버튼 가져오기
           console.log(targetBtn);
            this.deleteReply(targetBtn);
        });

    },
    saveReply: function () {
        let data = {
            content: $("#reply-content").summernote('code')
        };
        const board_id= $("#reply-btn-post").attr("data-reply-board_id");
        const user_id= $("#reply-btn-post").attr("data-reply-user_id");

        $.ajax({
            type:"POST",
            url:`/api/reply/create/${board_id}/${user_id}`,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("코멘트 등록 완료");
            window.location.reload();
        }).fail(function(error){
            alert("무언가가 잘못되었습니다");
        });
    },
    editReply:function(targetBtn){
        console.log("댓글수정창이 열렸습니다");
        //수정 Modal  Post 버튼에 수정 정보 입력
       const reply_id = targetBtn.getAttribute("data-reply-reply_id");
       const board_id =  targetBtn.getAttribute("data-reply-board_id");
       const user_id = targetBtn.getAttribute("data-reply-user_id");
       const content =  targetBtn.getAttribute("data-reply-content");
        $(".btn-reply-edit-post").attr("data-reply-reply_id",reply_id);
        $(".btn-reply-edit-post").attr("data-reply-board_id",board_id);
        $(".btn-reply-edit-post").attr("data-reply-user_id",user_id);
        $(".btn-reply-edit-post").attr("data-reply-content",content);

       $("#reply-modal-content").summernote('code',  $(".btn-reply-edit-post").attr("data-reply-content"));
      console.log(targetBtn);
       console.log(`${board_id}/${user_id}/${reply_id}/${content}`);

    },
    postEditedReply:function(){
        const reply_id=$("#btn-reply-edit-modal").attr("data-reply-reply_id");
        const board_id=$("#btn-reply-edit-modal").attr("data-reply-board_id");
        const user_id= $("#btn-reply-edit-modal").attr("data-reply-user_id");
        //Post버튼에 채워진 reply 정보
        let data = {
            reply_id: reply_id,
            board_id: board_id,
            user_id:  user_id,
            content:  $("#reply-modal-content").summernote('code')
        }
        console.log("reply Modal Post버튼이 클릭되었습니다");
        console.log($('#btn-reply-edit-modal'));
        $.ajax({
            type: "PATCH",
            url:`/api/reply/edit/${board_id}/${user_id}/${reply_id}`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("수정 완료");
            location.reload();

        }).fail(function(error){
            alert("무언가가 잘못되었습니다");
        });
    },

    deleteReply:function(targetBtn) {


        console.log("댓글이 삭제되었습니다");
        let data = {
            reply_id: targetBtn.getAttribute("data-reply-reply_id"),
            board_id: targetBtn.getAttribute("data-reply-board_id"),
            user_id: targetBtn.getAttribute("data-reply-user_id")

        }
        $.ajax({
            type: "DELETE",
            url:`/api/reply/delete/${data.board_id}/${data.user_id}/${data.reply_id}`,
            data:JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("댓글 "+data.reply_id+"이 삭제되었습니다");
            window.location.reload();
        }).fail(function(error){
            alert(JSON.stringify(error));
            alert("무언가가 잘못되었습니다");
        });

    }
}


replyIndex.init();