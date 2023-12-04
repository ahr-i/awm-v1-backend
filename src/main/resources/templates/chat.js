function getSenMessageBox(data){
    return `<div class = "sent_msg">
    <p>${data.msg}</p>
    <span class = "time_date">${data.create} / ${data.sender} </span>`
}

let username = prompt("아이디를 입력하세요.")
let roomNumber = prompt("채팅방 입력")

async function addMessage() {
    let chatBox = document.querySelector("#chat-box")
    let chatOutgonigBox = document.createElement("div")
    let msgInput = document.querySelector("#chat-outgoing-msg")

    chatOutgonigBox.className = "chat-send";

    let data = new Date();
    let now = data.getHours() + " :" + data.getMinutes();


    let chat = {
        sender: username,
        receiver: roomNumber,
        msg: msgInput.value
    };

     fetch("http://localhost:8080/chat", {
        method: "post",
        body: JSON.stringify(chat),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    })

    chatOutgonigBox.innerHTML = getSenMessageBox(msgInput.value, now);
    chatBox.append(chatOutgonigBox);



}

function initMyMessage(data){
    let chatBox = document.querySelector("#chat-box")
    let charOutgoingBox = document.createElement("div")
    charOutgoingBox.className = "outgoing_msg";

    charOutgoingBox.innerHTML = getSenMessageBox(data.msg)
    chatBox.append(charOutgoingBox)

}

const eventSource = new EventSource(`http://localhost:8080/chat/roomNumber/${roomNumber}`);

eventSource.onmessage = (event) => {
    const  data = JSON.parse(event.data)

    if(data.sender === username){
        initMyMessage(data)
    }else  {


    }
    initMyMessage(data)
}

document.querySelector("#chat-outgoing-msg").addEventListener("keydown",e => {
    if(e.keyCode ==13) {
        addMessage()
    }
})


    function getSenMessageBox(data){
        return `<div class = "sent_msg">
    <p>${data.msg}</p>
    <span class = "time_date">${data.create} / ${data.sender} </span>`
    }




document.querySelector("#chat-send").addEventListener("click", () =>{
 addMessage()
})

document.querySelector("#outgoing_msg").addEventListener("keydown", (e) =>{

    if(e.keydown == 13) {
        addMessage()
    }
})