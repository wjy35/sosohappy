<script>
    import axios from "axios";

    let inputId = '';
    let inputPassword = '';

    const login = async () => {
        const loginRes = await axios.post("https://sosohappy.co.kr/member-command/sign-in",{
            id:inputId,
            password:inputPassword,
        },
        {
            headers:{
                'Content-Type': 'application/json',
            },
        })

        if(loginRes.status === 200){
            console.log("loginRes", loginRes.data.result.authorization.accessToken);
            location.href = "/docs/certificate"
        }
    }

</script>

<div class="login-container">
    <div class="docs-title-wrap">
        <p class="docs-main-title">증명서 발급</p>
        <p class="docs-sub-title">소중한 나의 선행 기록 </p>
        <p class="docs-description">
            개인의 증명을 위해 증명서 발급 시
            재로그인이 필요합니다.
        </p>
    </div>
    <div class="login-input-wrap">
        <input type="text" class="input-id" placeholder="아이디를 입력해주세요." bind:value={inputId}/>
        <input type="password" class="input-password" placeholder="비밀번호를 입력해주세요." bind:value={inputPassword}/>
        <button class="login-button" on:click={() => login()}>로그인</button>
        <span class="user-auth-text">회원가입은 ‘소소행’에서 진행해주세요.</span>
    </div>
</div>

<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;600;700;800;900&display=swap');
html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed, 
figure, figcaption, footer, header, hgroup, 
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
    font-family: 'Noto Sans KR', sans-serif !important;
}
/* HTML5 display-role reset for older browsers */
article, aside, details, figcaption, figure, 
footer, header, hgroup, menu, nav, section {
	display: block;
}
body {
	line-height: 1;
}
ol, ul {
	list-style: none;
}
blockquote, q {
	quotes: none;
}
blockquote:before, blockquote:after,
q:before, q:after {
	content: '';
	content: none;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}
a{
    text-decoration:none;
    color:inherit;
}

.login-container{
    width:100vw;
    height:100vh;
}
.login-container .docs-title-wrap{
    margin-top:104px;
}
.login-container .docs-main-title{
    font-size:24px;
    font-weight:700;
    color:#3B3D9A;
}
.login-container .docs-sub-title{
    font-size:24px;
    font-weight:700;
    color:#32323C;
    margin-top:2px;
}
.login-container .docs-description{
    width:92%;
    font-size:14px;
    font-weight:400;
    color:#646464;
    margin-top:8px;
}


.login-container .login-input-wrap{
    display:flex;
    flex-direction:column;
    margin-top:47px;
}
.login-container .input-id{
    width:92%;
    height:40px;
    border-radius:15px;
    border:1px solid #D1D1D1;
    padding:10px;
    box-sizing:border-box;
    font-size:12px;
    font-weight:500;
    color:#D1D1D1;
}
.login-container .input-password{
    width:92%;
    height:40px;
    border-radius:15px;
    border:1px solid #D1D1D1;
    margin-top:12px;
    padding:10px;
    box-sizing:border-box;
    font-size:12px;
    font-weight:500;
    color:#D1D1D1;
}
.login-container .login-button{
    width:95%;
    height:40px;
    background-color:#427CF9;
    border-radius:15px;
    border:none;
    margin-top:12px;
    font-size:16px;
    font-weight:600;
    color:#FFFFFF;
}
.login-container .user-auth-text{
    width:94%;
    font-size:12px;
    font-weight:500;
    color:#5B5B5B;
    text-align:center;
    margin-top:22px;
}

</style>