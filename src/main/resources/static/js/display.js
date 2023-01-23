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

//Nav bar toggleIcon focus effect
let navbarTogglerCount = 0;
$(".navbar-toggler").on("click", (e)=>{

        $(".navbar-toggler").css('box-shadow','0 0 0 0.2rem');

    if(navbarTogglerCount%2==1){
        $(".navbar-toggler").css('box-shadow','0 0 0 0.0rem');
        console.log(navbarTogglerCount);
    }
    navbarTogglerCount++;
    console.log(navbarTogglerCount)
});
//Nav bar toggleIcon focus effect

//Jumbotron 글자 Animation
const varSentence = $('.var-sentence');
const txtArr = ['Sharing Tips!','Memes Archive','Front-end Developing','Back-end Developing',
'Dev-Ops'];

let text_index = 0;
let currentTxt = txtArr[text_index].split("");
// console.log(currentTxt);

var txtArea = "";
function writeTxt(){
    // console.log(txtArea);
    varSentence.text(txtArea+=currentTxt.shift());
        // currentTxt.shift();
    if(currentTxt!=0){
        // console.log("글자가 생성된다");
        setTimeout(writeTxt,Math.floor(Math.random())*100+100);
        // console.log(currentTxt.length)
    }else{
        // console.log("글자가 지워진다")
        // innerHtml에 쓰여진 문장을 다시 char 로 쪼갬
        currentTxt = varSentence.text().split("");
        // console.log(currentTxt);// currentTxt = [s,h,a,r,i,n,g, ,T,i,p,s,!]
        setTimeout(deleteTxt,1000);
    }
}

function deleteTxt(){
    currentTxt.pop();  // S,h,a,r,i,n,g, ,T,i,p,s,! 에서 끝 문자 한개만 뺌
    varSentence.text(currentTxt.join("")); // Sharing Tips [!] 없어진 문장을 innerHtml에 넣음
    if(currentTxt !=0){ // [S,h,a,r,i,n,g, ,T,i,p,s,!] 다 빌 때까지
        setTimeout(deleteTxt,Math.floor(Math.random())*100+100);
    }else{
        txtArea =""; // 초기화
       text_index = (text_index+1) % txtArr.length; // 갈래수가 4개면 4321 4321 반복
        currentTxt = txtArr[text_index].split("");
        writeTxt();
    }


}
writeTxt();
//
// //Jumbotron 글자 Animation

// //Jumbotron 화살표 ICON Animation  (css로 구현 가능)
const ArrowIcon = $(".section.jumbotron .container i");
// ArrowIcon.css("left", parseInt(screen.width/2+38)+'px');

// //Jumbotron 화살표 ICON Animation


//Section Fade-Out Animation
let observer = new IntersectionObserver((e)=>{// 감시중인 객체가 화면에 등장하면 함수 실행해줌
  console.log(e);

  e.forEach((box)=>{
      if(box.isIntersecting)
    box.target.style.opacity = 1;
      else
          box.target.style.opacity = 0;

  });
});
//just for prac
const title = document.querySelectorAll('.title');
observer.observe(title[0]);
observer.observe(title[1]);
observer.observe(title[2]);
console.log(title[0]);
console.log(title[1]);
console.log(title[2]);
//Section Fade-Out Animation

//Snowflake Animation
function makeSnowFlake(){
    const snowflake = document.createElement("div");
    const delay = Math.random()*10;
    const randomOpacity = Math.random();
    //minimum duration of snowflake animation=5 +0~20s
    const animationDuration= Math.random()*30+5;


    snowflake.classList.add("snowflake");
    snowflake.style.left = `${Math.random()* window.screen.width}px`;
    snowflake.style.animationDelay=`${delay}s`;
    snowflake.style.opacity = randomOpacity;
    snowflake.style.animation= `fall ${animationDuration}s linear`
    document.body.appendChild(snowflake);
    console.log("눈방울 생겼다");

    //removes the snowflake element as the duration+delayed time passed
    setTimeout(function(){
        document.body.removeChild(snowflake);
        makeSnowFlake();
    },(animationDuration+delay)*1000);
}

function snowflakeInit(n){
for(let i =0; i<n; i++){
    setTimeout(makeSnowFlake,300*Math.random());
}
}
snowflakeInit(120);
//Snowflake Animation


