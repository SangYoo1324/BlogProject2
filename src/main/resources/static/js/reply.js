let replyIndex = {
    init: function () {

        $("#reply-btn-post").on("click", () => {
            this.saveReply();
        });
        $("#btn-reply-edit-modal").on("click",()=>{
           this.editReply();
        });
        $(".reply-btn-delete").on("click",()=>{
            this.deleteReply();
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
    editReply:function(){
        console.log("댓글이 수정되었습니다");

    },

    deleteReply:function() {
        console.log("댓글이 삭제되었습니다");
        let data = {
            reply_id: $('.reply-btn-delete').attr("data-reply-reply_id"),
            board_id: $('.reply-btn-delete').attr("data-reply-board_id"),
            user_id: $('.reply-btn-delete').attr("data-reply-user_id")

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