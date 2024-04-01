<script>
    import GradientCircleLogo from "../../../img/gradient-circle-logo.png"
 
    const downloadPdf = () => {
     const urlParams = new URLSearchParams(location.search);
     const user = urlParams.get("user");
     location.href = `/docs/certificate/list?user=${user}`;
    }
 </script>
 
 <div class="certificate-container">
     <div class="docs-title-wrap">
         <p class="docs-main-title">간편한</p>
         <p class="docs-sub-title">당신의 선행 인증서.</p>
         <p class="docs-description">
             오늘도 소소행으로 작지만 소중한
             마음을 전합니다.
         </p>
     </div>
     <div class="certificate-wrap">
         <div class="certificate-flex-wrap">
             <div class="certificate-title-wrap">
                 <p class="certificate-title">소소행 인증서</p>
                 <p class="certificate-date">문서를 열람해주세요.</p>
             </div>
             <div class="certificate-img-wrap">
                 <img src={GradientCircleLogo} alt="" class="certificate-img"/>
             </div>
         </div>
         <p class="certificate-description">본 인증서는 소소행에서 발행하는
             작지만 소중한 선행 기록입니다.</p>
     </div>
     <div class="button-wrap">
         <button class="pdf-download-button" on:click={() => downloadPdf()}>pdf 다운로드</button>
         <button class="go-main-button"><a href="/docs">메인으로 돌아가기</a></button>
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
 
 .certificate-container{
     width:100vw;
     height:100vh;
 }
 .certificate-container .docs-title-wrap{
     margin-top:104px;
 }
 .certificate-container .docs-main-title{
     font-size:24px;
     font-weight:700;
     color:#3B3D9A;
 }
 .certificate-container .docs-sub-title{
     font-size:24px;
     font-weight:700;
     color:#32323C;
     margin-top:2px;
 }
 .certificate-container .docs-description{
     width:92%;
     font-size:14px;
     font-weight:400;
     color:#646464;
     margin-top:8px;
 }
 
 .certificate-container .certificate-wrap{
     margin-top:44px;
     width:95%;
     background-color:#EAEEFA;
     border: 2px solid #B4B0D3;
     border-radius:15px;
     padding:20px;
     box-sizing:border-box;
 }
 .certificate-container .certificate-flex-wrap{
     display:flex;
     flex-direction:row;
     justify-content:space-between;
     align-items:center;
 }
 .certificate-container .certificate-flex-wrap .certificate-title-wrap{
 
 }
 .certificate-container .certificate-flex-wrap .certificate-title-wrap .certificate-title{
     font-size:12px;
     font-weight:500;
     color:#9E99DC;
 }
 .certificate-container .certificate-flex-wrap .certificate-title-wrap .certificate-date{
     font-size:10px;
     font-weight:700;
     color:#ACADC3;
 }
 .certificate-container .certificate-flex-wrap .certificate-img-wrap{
     width:50px;
     height:50px;
 }
 .certificate-container .certificate-flex-wrap .certificate-img-wrap .certificate-img{
     width:100%;
     height:100%;
 }
 .certificate-container .certificate-description{
     font-size:10px;
     font-weight:400;
     color:#B1B4C9;
     margin-top:10px;
 }
 .certificate-container .button-wrap{
     margin-top:38px;
 }
 .certificate-container .button-wrap .pdf-download-button{
     width:95%;
     height:40px;
     border-radius:6px;
     border:none;
     background-color:#427CF9;
     font-size:16px;
     font-weight:500;
     color:#FFFFFF;
 }
 .certificate-container .button-wrap .go-main-button{
     width:95%;
     height:40px;
     border-radius:6px;
     margin-top:10px;
     border:none;
     background-color:#F5F5F5;
     font-size:16px;
     font-weight:500;
     color:#5B5B5B;
 }
 </style>