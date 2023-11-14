const {app, BrowserWindow} = require("electron");
const path = require("path");

const createWindow = () => {
    const win = new BrowserWindow({
        width: 800,
        height: 500,
        webPreferences: { preload: path.join(__dirname, 'preload.js'), devTools:true, nodeIntegration: true, contextIsolation: false }
    })

    win.loadFile('index.html');
}

app.whenReady().then(() => {
    createWindow();
 
    app.on('activate', () => {
        if (BrowserWindow.getAllWindows().length === 0) createWindow();
    });
});
 
app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') app.quit();
});