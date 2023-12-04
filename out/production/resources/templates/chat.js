function getSenMessageBox(msg,now){
    return `<div class = "sent_msg">
    <p>${msg}</p>
    <span class = "time_date">${now} </span>`
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
        sender: "ssar",
        receiver: "cos ",
        msg: msgInput.value
    };

    let response = await fetch("http://localhost:8080/chat", {
        method: "post",
        body: JSON.stringify(chat),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    })

    chatOutgonigBox.innerHTML = getSenMessageBox(msgInput.value, now);
    chatBox.append(chatOutgonigBox);


    console.log(response)
    let parse = response.json
    console.log(parse)
}

function initMessage(data){
    let chatBox = document.querySelector("#chat-box")
    let chatOutgonigBox = document.createElement("div")
    let msgInput = document.querySelector("#chat-outgoing-msg")

    chatOutgonigBox.className = "chat-send";

    chatOutgonigBox.innerHTML = getSenMessageBox(data.msg);
    chatBox.append(chatOutgonigBox);

}

const eventSource = new EventSource(`http://localhost:8080/chat/roomNumber/${roomNumber}`);

eventSource.onmessage = (event) => {
    console.log(1,event)
    const  data = JSON.parse(event.data)
    console.log(2,data)
    initMessage(historymsg)
}

document.querySelector("#chat-outgoing-msg").addEventListener("keydown",e => {
    if(e.keyCode ==13) {
        addMessage()
    }
})



document.querySelector("#chat-send").addEventListener("click", () =>{
 addMessage()
})

document.querySelector("#outgoing_msg").addEventListener("keydown", (e) =>{

    if(e.keydown == 13) {
        addMessage()
    }
})