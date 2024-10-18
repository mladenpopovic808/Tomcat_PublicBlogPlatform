function showLoginHtml(){

    //Kada refreshujemo,a ulogovani smo,da nas ne baca na login stranicu
    fetch("api/users/current")
        .then(response => response.json())
        .then(user => {
            if(user!=null){
                showPostList()
            }
        })
    const body=document.getElementById("body")
    let postListHtml=document.getElementById("postListHtml")
    let commentListHtml=document.getElementById("commentListHtml")
    let addPostHtml=document.getElementById("addPostHtml")
    let loginHtml=document.getElementById("loginHtml")

    postListHtml.classList.add("display-none");
    commentListHtml.classList.add("display-none")
    addPostHtml.classList.add("display-none")
    loginHtml.classList.remove("display-none")
    body.append(loginHtml)
}
function submitLoginForm(){

    let username=document.getElementById("username").value
    let password=document.getElementById("password").value
    let user={
        "username":username,
        "password":password,
    }
    fetch("api/users/login", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
    })
        .then(response => response.json())
        .then(user => {
            showPostList()
            }
        )
    document.getElementById("username").value=""
    document.getElementById("password").value=""
}

function showPostList(){

    const body=document.getElementById("body")
    let postListHtml=document.getElementById("postListHtml")
    let commentListHtml=document.getElementById("commentListHtml")
    let addPostHtml=document.getElementById("addPostHtml")
    let loginHtml=document.getElementById("loginHtml")

    postListHtml.classList.remove("display-none");
    commentListHtml.classList.add("display-none")
    addPostHtml.classList.add("display-none")
    loginHtml.classList.add("display-none")
    body.append(postListHtml)
    let postListDiv=document.getElementById("postListDiv")
    let authorInput=document.getElementById("authorInput")


    ///
    fetch("api/users/current")
        .then(response => response.json())
        .then(user => {
            authorInput.value=user.username
        })

    //brisemo sve iz liste postova,da se ne bi stalno dodavali jedni isti postovi
    while (postListDiv.firstChild) {
        postListDiv.removeChild(postListDiv.firstChild);
    }


    fetch( "api/posts")
        .then(response => response.json())
        .then(posts => {
                posts.forEach(post => {
                    console.log(post)
                    let postDiv=document.createElement("div")
                    postDiv.classList.add("post")
                    postDiv.innerHTML=`<h3>${post.title}</h3>
                    <p>${post.description}</p>
                    <button class="btn btn-primary mb-5" onclick="showCommentsHtml(${post.id})">Show comments</button>`
                    postDiv.classList.add("divBorders")
                    postListDiv.append(postDiv)
                })
            }
        ).catch(error => console.log(error))
}

function showCommentsHtml(postId){
    const body=document.getElementById("body")
    let postListHtml=document.getElementById("postListHtml")
    let commentListHtml=document.getElementById("commentListHtml")
    let addPostHtml=document.getElementById("addPostHtml")
    let loginHtml=document.getElementById("loginHtml")

    postListHtml.classList.add("display-none");
    commentListHtml.classList.remove("display-none")
    addPostHtml.classList.add("display-none")
    loginHtml.classList.add("display-none")
    body.append(commentListHtml)
    let commentListDiv=document.getElementById("commentListDiv")
    let authorInput=document.getElementById("authorCommentName")

    //Popunjavamo authora
    fetch("api/users/current")
        .then(response => response.json())
        .then(user => {
            authorInput.value=user.username
        })

    //uzimamo post
    fetch( "api/posts/" + postId )
        .then(response => response.json())
        .then(post => {
                let postDiv=document.getElementById("postInCommentsSection")
            postDiv.innerHTML=`<h2>${post.title}</h2>
                <h3>${post.author}</h3>
                <p>${post.description}</p>`
            postDiv.classList.add("divBorders")
        })

    //clearujemo da bismo opet dodali
    while (commentListDiv.firstChild) {
        commentListDiv.removeChild(commentListDiv.firstChild);
    }
    //uzimamo postove komentare
    fetch( "api/comments/" + postId)
         .then(response => response.json())
            .then(comments => {
                comments.forEach(comment => {
                    let commentDiv=document.createElement("div")
                    commentDiv.classList.add("comment")
                    commentDiv.innerHTML=`<h4>${comment.author}</h4>
                    <p>${comment.comment}</p>`

                    commentDiv.classList.add("commentDiv")
                    commentListDiv.append(commentDiv)
                })
            }
        )
    let submitCommentButton=document.getElementById("submitNewCommentButton")
    submitCommentButton.addEventListener("click", function(){
        submitNewComment(postId)
    })
}
function submitNewComment(postId){

    let commentAuthor=document.getElementById("authorCommentName").value
    let commentText=document.getElementById("authorCommentContent").value
    let comment={
        "author":commentAuthor,
        "comment":commentText,

    }
    fetch("api/comments/" + postId, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(comment),
    })
        .then(response => response.json())
        .then(comment => {
                showCommentsHtml(postId)
        }
        )

    document.getElementById("authorCommentName").value=""
    document.getElementById("authorCommentContent").value=""





}
function showAddNewPost(){
    const body=document.getElementById("body")
    let postListHtml=document.getElementById("postListHtml")
    let commentListHtml=document.getElementById("commentListHtml")
    let addPostHtml=document.getElementById("addPostHtml")
    let loginHtml=document.getElementById("loginHtml")
    postListHtml.classList.add("display-none");
    commentListHtml.classList.add("display-none")
    addPostHtml.classList.remove("display-none")
    loginHtml.classList.add("display-none")
    body.append(addPostHtml)

}
function submitNewPost(){


    let postTitle=document.getElementById("titleInput").value
    let postAuthor=document.getElementById("authorInput").value
    let postDescription=document.getElementById("contentInput").value
    let post={
        "title":postTitle,
        "author":postAuthor,
        "description":postDescription,
    }
    fetch("api/posts", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(post),
    })
        .then(response => response.json())
        .then(post => {
            showPostList()
            }
        )
    document.getElementById("titleInput").value=""
    document.getElementById("authorInput").value=""
    document.getElementById("contentInput").value=""

}
