<template lang="html">
    <div class="motherland">
        <div class="header">
            <div class="headertext">
                <p class="sublogo" @click="goToProfile">{{activeChat.name}}</p>
            </div>
            <div class="closeButton" @click="killConvo"><i class="far fa-times-circle"></i></div>
        </div>
        <div class="main" id="boxThatCanScroll">
            <!-- <div class="hideScroll"> -->
                <!--  -->
            <!-- </div> -->
            <div v-for="(elem) of this.messages" class="singularity">
                <p class="sender">{{elem.sender.firstname}} {{elem.sender.lastname}}:</p>
                <p class="message">{{elem.message}}</p>
            </div>
        </div>
        <div class="footer">
            <input type="text" name="chatbox"
            v-on:keyup="handleMessage">
            <button type="button" name="button" @click="sendMessage">Send</button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Chat',
    computed: {
        messages(){
            this.readAll();
            setTimeout(() => {
                this.scrollToEnd();
            }, 1);
            return this.$store.state.chatMessages
                .filter(m => m.receiverid === this.activeChat.id
                    || m.senderid === this.activeChat.id);
        },
        activeChat(){
            return this.$store.state.activeChat;
        }
    },
    mounted(){
        // this.scrollToEnd();
        // this.$store.dispatch('readCurrentChat');
    },
    methods: {
        killConvo(){
            this.$store.commit('setActiveChat', null);
        },
        sendMessage(e){
            let msg = {
                receiverid: this.activeChat.id,
                message: e.target.value
            };
            // console.log(msg);
            this.$store.state.socket.send(JSON.stringify(msg));
            e.target.value = "";
        },
        handleMessage(e){
            if(e.key == 'Enter'){
                this.sendMessage(e);
            } else {
                e.target.value = e.target.value
                    .replace(/[ ]{2,}/g," ")
                    .replace(/^ $/g, "")
                    .substring(0,250);
            }
        },
        goToProfile(){
            this.$router.push('/profile/'+this.activeChat.id);
        },
        scrollToEnd(){
            let scroller = document.getElementById('boxThatCanScroll');
            scroller.scrollTop = scroller.scrollHeight;
        },
        readAll(){
            this.$store.dispatch('readCurrentChat');
            for(let i = 0; i < this.$store.state.chatMessages.length; i++){
                if(!this.$store.state.chatMessages[i].hasread && this.$store.state.chatMessages[i].senderid == this.$store.state.activeChat.id){
                    this.$store.state.chatMessages[i].hasread = 1;
                }
            }
        }
    }

}
</script>

<style lang="css" scoped>
.motherland{
    width: 19vw;
    min-height: 49vh;
    max-height: 49vh;
    display: flex;
    flex-direction: column;
}
.header{
    display: flex;
    flex-direction: row;
    width: 100%;
    cursor: pointer;
}
.main{
    flex: 1;
    overflow-y: scroll;
}
.headertext{
    flex: 1;
}
.singularity{
    padding-left: 10pt;
    text-align: left;
}
.sender{
    font-size: 10pt;
    font-weight: bold;
    margin: 0;
}
.message{
    margin: 0;
    font-size: 10pt;
}
.footer > *{
    padding: 0;
    margin: 0;
}
.footer > button{
    width: 4vw;
}
.footer > input{
    width: 15vw;
}
.closeButton > i{
    padding-top: 3px;
    font-size: 16pt;
    cursor: pointer;
}
button{
    background: white;
    border-radius: 3px;
    border: 1px solid black;
}
.hideScroll{
    background: white;
    width: 18px;
    height: 41.2vh;
    position: absolute;
    margin-left: 22.9vw;
}
</style>
