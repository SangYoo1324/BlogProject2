console.log("연결됬다");
// Nav bar 밑줄 animation
let horizontalUnderline = $('#horizontal-underline');
let menus = $('li.nav-item');
let logo = $('.navbar-logo');

menus.on('mouseover',(e)=>horizontalIndicator(e));
menus.on('mouseout',(e)=>{
        console.log("원상복귀");
        horizontalUnderline.css("backgroundColor","transparent");
    });
logo.on('mouseover',(e)=>horizontalIndicator(e));
logo.on('mouseout',(e)=>{
    console.log("원상복귀");
    horizontalUnderline.css("backgroundColor","transparent");
});


function horizontalIndicator(e){
console.log("닿았다");
horizontalUnderline.css('left',e.currentTarget.offsetLeft+'px');
horizontalUnderline.css('width',e.currentTarget.offsetWidth+'px');
horizontalUnderline.css('top',e.currentTarget.offsetTop+e.currentTarget.offsetHeight+'px');
    horizontalUnderline.css('backgroundColor','#FFFFFF');
}
// Nav bar 밑줄 animation


// //Jumbotron 글자 Animation
// const varSentence = $('.var-sentence');
// const txtArr = ['Sharing Tips!','Memes Archive','Front-end Developing','Back-end Developing',
// 'Dev-Ops'];
//
// let text_index = 0;
// let currentTxt = txtArr[text_index].split("");
// console.log(currentTxt);
//
// var txtArea = "";
// function writeTxt(){
//     console.log(txtArea);
//     varSentence.text(txtArea+=currentTxt.shift());
//         // currentTxt.shift();
//     if(currentTxt!=0){
//         console.log("글자가 생성된다");
//         setTimeout(writeTxt,Math.floor(Math.random())*100+100);
//         console.log(currentTxt.length)
//     }else{
//         console.log("글자가 지워진다")
//         // innerHtml에 쓰여진 문장을 다시 char 로 쪼갬
//         currentTxt = varSentence.text().split("");
//         console.log(currentTxt);// currentTxt = [s,h,a,r,i,n,g, ,T,i,p,s,!]
//         setTimeout(deleteTxt,1000);
//     }
// }
//
// function deleteTxt(){
//     currentTxt.pop();  // S,h,a,r,i,n,g, ,T,i,p,s,! 에서 끝 문자 한개만 뺌
//     varSentence.text(currentTxt.join("")); // Sharing Tips [!] 없어진 문장을 innerHtml에 넣음
//     if(currentTxt !=0){ // [S,h,a,r,i,n,g, ,T,i,p,s,!] 다 빌 때까지
//         setTimeout(deleteTxt,Math.floor(Math.random())*100+100);
//     }else{
//         txtArea =""; // 초기화
//        text_index = (text_index+1) % txtArr.length; // 갈래수가 4개면 4321 4321 반복
//         currentTxt = txtArr[text_index].split("");
//         writeTxt();
//     }
//
//
// }
// writeTxt();
//
// //Jumbotron 글자 Animation

//Section Title Animation
let observer = new IntersectionObserver((e)=>{// 감시중인 객체가 화면에 등장하면 함수 실행해줌
  console.log(e);

  e.forEach((box)=>{
      if(box.isIntersecting)
    box.target.style.opacity = 1;
      else
          box.target.style.opacity = 0;

  });
});
const title = document.querySelectorAll('.title');
observer.observe(title[0]);
observer.observe(title[1]);
observer.observe(title[2]);
console.log(title[0]);
console.log(title[1]);
console.log(title[2]);
//Section Title Animation
