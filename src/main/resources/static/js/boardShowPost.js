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

  console.log("poster "+editBtn.getAttribute("data-post-username"));
  console.log("current "+editBtn.getAttribute("data-current-username"));
  console.log(editBtn.getAttribute("data-post-username")
               == editBtn.getAttribute("data-current-username"));






