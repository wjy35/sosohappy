<script>
    import GradientCircleLogo from "../../../../img/gradient-circle-logo.png"
</script>

<div class="cert-container">
    <table class="cert-table">
        <tr class="cert-tr">
            <td colspan="3" class="cert-title cert-td">봉사활동 확인서</td>
        </tr>
        <tr class="cert-tr">
            <td rowspan="3" class="cert-td">인적사항</td>
            <td class="cert-td">이름</td>
            <td class="cert-td">김석주</td>
        </tr>
        <tr class="cert-tr">
            <td class="cert-td">생년월일</td>
            <td class="cert-td">2023.11.08.</td>
        </tr>
        <tr class="cert-tr">
            <td class="cert-td">성별</td>
            <td class="cert-td">남</td>
        </tr>
        <tr class="cert-tr">
            <td colspan="3" class="cert-td">봉사내역</td>
        </tr>
        <tr class="cert-tr">
            <td class="cert-td">도와준 사람</td>
            <td class="cert-td">카테고리</td>
            <td class="cert-td">날짜</td>
        </tr>
        <tr class="cert-tr">
            <td class="cert-td">왕준영</td>
            <td class="cert-td">보행</td>
            <td class="cert-td">2023.11.07</td>
        </tr>
        <tr class="cert-tr">
            <td colspan="3" class="cert-td">
                <p>위 사람의 소중한 도움제공 내역을 소소행에서 발행해드립니다.</p>
                <p>2023.11.07.</p>
                <div class="stamp-wrap">
                    <p class="stamp-comp">소소행 (인)</p>
                    <img src={GradientCircleLogo} alt="" class="stamp-img"/>
                </div>
            </td>
        </tr>
    </table>
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
    text-decoration: none;
    color:inherit;
}

.cert-container{
    width:100vw;
    height:100vh;
}
.cert-container .cert-table{
    width:96%;
    text-align: center;
}
.cert-container .cert-table .cert-tr{
    border:1px solid #484848;
}
.cert-container .cert-table .cert-tr .cert-td{
    padding-top:20px;
    padding-bottom:20px;
}

.cert-container .cert-table .cert-title{
    height:40px;
    line-height:40px;
}

.cert-container .cert-table .cert-info-title{
    line-height:100px;
}


.cert-container .cert-table .stamp-wrap{
    display:flex;
    justify-content: center;
    align-items: center;
}
.cert-container .cert-table .stamp-wrap .stamp-comp{

}
.cert-container .cert-table .stamp-wrap .stamp-img{
    width:50px;
    height:50px;
    margin-left:10px;
}

</style>