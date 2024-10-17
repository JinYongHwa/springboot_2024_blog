var title=document.getElementById("title")
var content=document.getElementById("content")

var submitBtn=document.getElementById("submit-btn")

submitBtn.addEventListener("click",event=>{
    console.log(title.value,content.value)
    fetch("/api/articles",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({
            title:title.value,
            content:content.value
        })
    })
    .then((res) => {
        return res.json();
    })
    .then(result=>{
       console.log(result)
       if(result.success){  //작성성공시
        location.href="/article/"+result.article.id //작성한 글로 이동
       }
    })
})