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
    socket.on('chat join', (roomNo) => {
        socket.join(roomNo);
        console.log('roomNo', roomNo);
    })
    socket.on('send message', ({roomNo, msg}) => {
        console.log(msg);
        io.to(roomNo).emit('upload chat', {
            senderType: 'me',
            message: msg,
        });
    });
})

http.listen(PORT, () => {
    console.log(`server running on ${PORT}`);
});