const express = require('express');
const app = express();
const http = require('http').Server(app);
const cors = require('cors');
const PORT = process.env.PORT || 4002;

app.use(cors());
const io = require('socket.io')(http, {
  cors:{
    origin: "*",
    methods:["GET", "POST"]
  }
});

io.on('connection', (socket) => {
    socket.on('send message', (msg) => {
        console.log(msg);
        socket.broadcast.emit('upload chat', {
            senderType: 'me',
            message: msg,
        });
    });
})

http.listen(PORT, () => {
    console.log(`server running on ${PORT}`);
});