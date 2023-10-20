const bellDisactiveColor: string = 'black';
const bellActiveColor: any = {
  bellColor: 'black',
  dotColor: '#FF0032',
};
const addPlusColor: any = {
  backgroundColor: '#F7F6FC',
  plusColor: '#959AA0',
};
const bookColor: string = 'black';
const hamburgerMenuColor: string = 'black';
const dotMenuColor: string = 'black';
const exitColor: string = 'black';
const backIconColor: string = '#000000';
const gearColor: string = 'black';
const peopleColor: string = 'black';
const microphoneColor: string = 'black';
const sendColor: string = 'black';


export const bellDisactive: string = `
<svg width="100" height="100" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <path d="M91.3679 84.3458L84.2137 72.4208C80.9095 66.9167 79.1637 60.6125 79.1637 54.1958V43.75C79.1637 30.5625 70.3637 19.4083 58.3304 15.8125V8.33333C58.3304 3.7375 54.5929 0 49.9971 0C45.4012 0 41.6637 3.7375 41.6637 8.33333V15.8125C29.6304 19.4083 20.8304 30.5625 20.8304 43.75V54.1958C20.8304 60.6125 19.0846 66.9125 15.7846 72.4167L8.63039 84.3417C8.24289 84.9875 8.23455 85.7875 8.60539 86.4417C8.97622 87.0958 9.66372 87.5 10.4137 87.5H89.5804C90.3304 87.5 91.022 87.0958 91.3929 86.4458C91.7637 85.7958 91.7512 84.9875 91.3679 84.3458Z" fill="${bellDisactiveColor}"/>
  <path d="M50.0024 100C55.7983 100 60.7733 96.575 63.1233 91.6666H36.8816C39.2316 96.575 44.2066 100 50.0024 100Z" fill="${bellDisactiveColor}"/>
</svg>
`;

export const bellActive: string = `
<svg width="100" height="100" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <path d="M91.3679 84.3458L84.2137 72.4208C80.9095 66.9167 79.1637 60.6125 79.1637 54.1958V43.75C79.1637 30.5625 70.3637 19.4083 58.3304 15.8125V8.33333C58.3304 3.7375 54.5929 0 49.9971 0C45.4012 0 41.6637 3.7375 41.6637 8.33333V15.8125C29.6304 19.4083 20.8304 30.5625 20.8304 43.75V54.1958C20.8304 60.6125 19.0846 66.9125 15.7846 72.4167L8.63039 84.3417C8.24289 84.9875 8.23455 85.7875 8.60539 86.4417C8.97622 87.0958 9.66372 87.5 10.4137 87.5H89.5804C90.3304 87.5 91.022 87.0958 91.3929 86.4458C91.7637 85.7958 91.7512 84.9875 91.3679 84.3458Z" fill="${bellActiveColor.bellColor}"/>
  <path d="M50.0024 100C55.7983 100 60.7733 96.575 63.1233 91.6666H36.8816C39.2316 96.575 44.2066 100 50.0024 100Z" fill="${bellActiveColor.bellColor}"/>
  <circle cx="87" cy="13" r="10" fill="${bellActiveColor.dotColor}"/>
</svg>
`;

export const addPlus: string = `
<svg width="100" height="100" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <circle cx="50" cy="50" r="50" fill="${addPlusColor.backgroundColor}"/>
  <path d="M50.0001 75.2525L50.0001 24.7474M24.7476 50H75.2526" stroke="${addPlusColor.plusColor}" stroke-width="10" stroke-linecap="round" stroke-linejoin="round"/>
</svg>
`;

export const book: string = `
<svg width="100" height="100" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <path d="M22.8842 20.8333H85.3842C85.4134 20.8292 85.4384 20.8333 85.4675 20.8333C86.6175 20.8333 87.5509 19.9 87.5509 18.75C87.5509 18.1167 87.2717 17.55 86.8259 17.1708C86.4967 16.6125 85.7925 15.0917 85.5175 12.5H22.8842C21.7342 12.5 20.8009 11.5667 20.8009 10.4167C20.8009 9.26667 21.7342 8.33333 22.8842 8.33333H85.5009C85.855 4.94167 86.9717 3.4375 86.9717 3.4375C87.5009 2.82083 87.6175 1.95 87.28 1.2125C86.9425 0.475 86.1967 0 85.3842 0H22.8842C17.1425 0 12.4675 4.675 12.4675 10.4167C12.4675 16.1583 17.1425 20.8333 22.8842 20.8333ZM85.3842 25H22.8842C20.3675 25 17.9175 24.3125 15.6009 22.9625C14.9592 22.5917 14.1634 22.5833 13.5134 22.9583C12.8675 23.3292 12.4675 24.0125 12.4675 24.7625V89.5833C12.4675 95.325 17.1425 100 22.8842 100H85.3842C86.5342 100 87.4675 99.0667 87.4675 97.9167V27.0833C87.4675 25.9333 86.5342 25 85.3842 25Z" fill="${bookColor}"/>
</svg>
`;

export const hamburgerMenu: string = `
<svg width="102" height="100" viewBox="0 0 102 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <path d="M3.08333 22.9167H98.9167C100.067 22.9167 101 21.9833 101 20.8333C101 19.6833 100.067 18.75 98.9167 18.75H3.08333C1.93333 18.75 1 19.6833 1 20.8333C1 21.9833 1.93333 22.9167 3.08333 22.9167Z" fill="${hamburgerMenuColor}" stroke="${hamburgerMenuColor}" stroke-width="2"/>
  <path d="M98.9167 47.9166H3.08333C1.93333 47.9166 1 48.85 1 50C1 51.15 1.93333 52.0833 3.08333 52.0833H98.9167C100.067 52.0833 101 51.15 101 50C101 48.85 100.067 47.9166 98.9167 47.9166Z" fill="${hamburgerMenuColor}" stroke="${hamburgerMenuColor}" stroke-width="2"/>
  <path d="M98.9167 77.0834H3.08333C1.93333 77.0834 1 78.0167 1 79.1667C1 80.3167 1.93333 81.25 3.08333 81.25H98.9167C100.067 81.25 101 80.3167 101 79.1667C101 78.0167 100.067 77.0834 98.9167 77.0834Z" fill="${hamburgerMenuColor}" stroke="${hamburgerMenuColor}" stroke-width="2"/>
</svg>
`;

export const dotMenu: string = `
<svg width="100" height="100" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <circle cx="20" cy="50" r="10" fill="${dotMenuColor}"/>
  <circle cx="50" cy="50" r="10" fill="${dotMenuColor}"/>
  <circle cx="80" cy="50" r="10" fill="${dotMenuColor}"/>
</svg>
`;

export const exit: string = `
<svg width="100" height="100" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <path d="M98.6778 48.8212L98.6767 48.8184C98.5408 48.4935 98.351 48.2127 98.1368 47.9743C98.1038 47.9277 98.0583 47.871 97.9991 47.8142L81.3445 31.1596C80.1415 29.9566 78.1875 29.9566 76.9845 31.1596C75.7814 32.3626 75.7814 34.3166 76.9845 35.5196L88.3857 46.9209H41.6666C39.9643 46.9209 38.5833 48.3019 38.5833 50.0042C38.5833 51.7065 39.9643 53.0875 41.6666 53.0875H88.3899L76.9845 64.4929C75.7814 65.696 75.7814 67.65 76.9844 68.853L76.9918 68.8602C77.592 69.4482 78.3787 69.75 79.1666 69.75C79.9551 69.75 80.7437 69.4477 81.3451 68.8524L81.3487 68.8488L98.0154 52.1821C98.1555 52.042 98.2411 51.8985 98.2838 51.8247C98.2879 51.8175 98.292 51.8103 98.2959 51.8036C98.3021 51.7926 98.3077 51.7827 98.3121 51.775L98.329 51.7451C98.3309 51.7418 98.3323 51.7394 98.3333 51.7378L98.3356 51.7344C98.3544 51.7072 98.3602 51.6999 98.3705 51.6871C98.3822 51.6724 98.3998 51.6504 98.4495 51.5825C98.5102 51.4995 98.6015 51.3672 98.6747 51.1948L98.6778 51.1872C98.992 50.4295 98.992 49.5789 98.6778 48.8212ZM66.6666 63.5834C64.9643 63.5834 63.5832 64.9644 63.5832 66.6667V87.5C63.5832 90.3936 61.2268 92.75 58.3332 92.75H12.4999C9.60637 92.75 7.24992 90.3936 7.24992 87.5V12.5C7.24992 9.60649 9.60637 7.25004 12.4999 7.25004H58.3332C61.2268 7.25004 63.5832 9.60649 63.5832 12.5V33.3334C63.5832 35.0357 64.9643 36.4167 66.6666 36.4167C68.3689 36.4167 69.7499 35.0357 69.7499 33.3334V12.5C69.7499 6.20609 64.6272 1.08337 58.3332 1.08337H12.4999C6.20597 1.08337 1.08325 6.20609 1.08325 12.5V87.5C1.08325 93.794 6.20597 98.9167 12.4999 98.9167H58.3332C64.6272 98.9167 69.7499 93.794 69.7499 87.5V66.6667C69.7499 64.9644 68.3689 63.5834 66.6666 63.5834Z" fill="${exitColor}" stroke="${exitColor}" stroke-width="2"/>
</svg>
`;

export const backIcon: string = `
<svg width="100" height="100" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <path d="M62.5 25L38.2071 49.2929C37.8166 49.6834 37.8166 50.3166 38.2071 50.7071L62.5 75" stroke="${backIconColor}" stroke-width="8" stroke-linecap="round" stroke-linejoin="round"/>
</svg>
`;

export const gear: string = `
<svg width="100" height="100" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <path d="M67.8626 65.0208C67.9459 64.1917 67.9917 63.3458 67.9917 62.5C67.9917 61.6417 67.9417 60.7875 67.8584 59.9292L74.4876 54.7292C75.9542 53.4917 76.3084 51.4125 75.3501 49.8167L68.2501 37.5458C67.2959 35.9625 65.4209 35.2125 63.5084 35.925L55.7876 39.0292C54.4001 38.0417 52.9542 37.1958 51.4126 36.4708L50.2417 28.1583C49.9084 26.3625 48.2667 25 46.4292 25H32.2626C30.4126 25 28.7709 26.3917 28.4292 28.3083L27.2751 36.5375C25.7167 37.2708 24.2542 38.125 22.9001 39.0833L15.1334 35.9583C13.4042 35.3167 11.4001 36 10.4251 37.625L3.35839 49.8417C2.38339 51.4667 2.74173 53.5417 4.26256 54.8292L10.8292 59.975C10.7501 60.8083 10.7001 61.6542 10.7001 62.5C10.7001 63.3583 10.7501 64.2125 10.8334 65.0708L4.20423 70.2708C2.73756 71.5083 2.38339 73.5875 3.34173 75.1833L10.4417 87.4542C11.3959 89.0417 13.2751 89.8 15.1834 89.075L22.9042 85.9708C24.2917 86.9583 25.7376 87.8042 27.2792 88.5292L28.4501 96.8417C28.7876 98.6375 30.4251 100 32.2626 100H46.4292C48.2792 100 49.9209 98.6083 50.2626 96.6917L51.4167 88.4667C52.9751 87.7292 54.4376 86.875 55.7876 85.9167L63.5542 89.0417C63.9917 89.2083 64.4542 89.2917 64.9251 89.2917C66.2959 89.2917 67.5376 88.5875 68.2667 87.3792L75.3334 75.1625C76.3084 73.5375 75.9501 71.4625 74.4292 70.175L67.8626 65.0208ZM39.3459 70.8333C34.7501 70.8333 31.0126 67.0958 31.0126 62.5C31.0126 57.9042 34.7501 54.1667 39.3459 54.1667C43.9417 54.1667 47.6792 57.9042 47.6792 62.5C47.6792 67.0958 43.9417 70.8333 39.3459 70.8333Z" fill="${gearColor}"/>
  <path d="M96.0501 24.1375L92.9543 21.7375C92.9793 21.4417 92.9918 21.1417 92.9918 20.8333C92.9918 20.5208 92.9834 20.2167 92.9668 19.9125L96.1209 17.4375C97.1959 16.4792 97.4709 14.9333 96.7626 13.6417L93.0376 7.2C92.5168 6.24167 91.5001 5.64583 90.3918 5.64583C90.1251 5.64583 89.5168 5.75417 89.2668 5.85417L85.6751 7.3C85.1543 6.95417 84.6334 6.65 84.1043 6.3875L83.5709 2.70417C83.4501 1.1875 82.1418 0 80.5959 0H73.0959C71.6293 0 70.3459 1.0875 70.0959 2.575L69.5459 6.4125C68.9959 6.69167 68.4793 6.9875 67.9876 7.30417L64.3209 5.82917C64.0918 5.74583 63.5251 5.64583 63.2793 5.64583C62.2293 5.64583 61.2584 6.17917 60.6376 7.15417L56.8668 13.6833C56.1459 15 56.4501 16.5708 57.6334 17.5208L60.7209 19.9375C60.7084 20.2333 60.7001 20.5292 60.7001 20.8333C60.7001 21.1375 60.7084 21.4333 60.7209 21.725L57.5376 24.2208C56.4584 25.1792 56.1793 26.725 56.8918 28.025L60.6168 34.4667C61.1376 35.425 62.1543 36.0208 63.2626 36.0208C63.5293 36.0208 64.1376 35.9125 64.3876 35.8125L67.9793 34.3667C68.5001 34.7125 69.0209 35.0167 69.5501 35.2792L70.1043 39.1375C70.3501 40.6042 71.6084 41.6667 73.0959 41.6667H80.5959C82.0626 41.6667 83.3459 40.5792 83.5959 39.0917L84.1459 35.25C84.6918 34.975 85.2043 34.675 85.6834 34.3667L89.3668 35.8417C89.5959 35.925 90.1626 36.025 90.4084 36.025C91.4584 36.025 92.4293 35.4917 93.0501 34.5167L96.8251 27.9792C97.5084 26.7208 97.2293 25.1792 96.0501 24.1375ZM76.8459 25C74.5501 25 72.6793 23.1292 72.6793 20.8333C72.6793 18.5375 74.5501 16.6667 76.8459 16.6667C79.1418 16.6667 81.0126 18.5375 81.0126 20.8333C81.0126 23.1292 79.1459 25 76.8459 25Z" fill="${gearColor}"/>
</svg>
`;

export const people: string = `
<svg width="100" height="100" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <path d="M49.9166 39.1667C57.9707 39.1667 64.4999 32.6375 64.4999 24.5833C64.4999 16.5292 57.9707 10 49.9166 10C41.8624 10 35.3333 16.5292 35.3333 24.5833C35.3333 32.6375 41.8624 39.1667 49.9166 39.1667Z" fill="${peopleColor}"/>
  <path d="M79.0834 47.5C84.8364 47.5 89.5001 42.8363 89.5001 37.0833C89.5001 31.3303 84.8364 26.6666 79.0834 26.6666C73.3304 26.6666 68.6667 31.3303 68.6667 37.0833C68.6667 42.8363 73.3304 47.5 79.0834 47.5Z" fill="${peopleColor}"/>
  <path d="M21.0429 47.5C26.7959 47.5 31.4596 42.8363 31.4596 37.0833C31.4596 31.3303 26.7959 26.6666 21.0429 26.6666C15.2899 26.6666 10.6262 31.3303 10.6262 37.0833C10.6262 42.8363 15.2899 47.5 21.0429 47.5Z" fill="${peopleColor}"/>
  <path d="M49.9166 43.3334C34.9833 43.3334 22.8333 55.4834 22.8333 70.4167C22.8333 71.5667 24.5681 90.6666 25.7181 90.6666H74C75.15 90.6666 76.9999 71.5667 76.9999 70.4167C76.9999 55.4834 64.8499 43.3334 49.9166 43.3334Z" fill="${peopleColor}"/>
  <path d="M24.6375 52.1166C23.3625 51.8458 22.0667 51.6666 20.75 51.6666C10.4125 51.6666 2 60.0791 2 70.4166C2 71.5666 2.85 84.1666 4 84.1666H19C18.7667 83.5125 18.6667 71.15 18.6667 70.4166C18.6667 63.5791 20.9 57.2666 24.6375 52.1166Z" fill="${peopleColor}"/>
  <path d="M79.0972 51.6666C77.7806 51.6666 76.4847 51.8458 75.2097 52.1166C78.9472 57.2666 81.1806 63.5791 81.1806 70.4166C81.1806 71.15 81.2333 83.5125 81 84.1666H84.5H88H92.5H96C97.15 84.1666 97.8472 71.5666 97.8472 70.4166C97.8472 60.0791 89.4347 51.6666 79.0972 51.6666Z" fill="${peopleColor}"/>
</svg>
`;

export const microphone: string = `
<svg width="100" height="100" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <path d="M43.7501 70.8333H52.0834C60.1251 70.8333 66.6667 64.2916 66.6667 56.25V14.5875C66.6667 6.54582 60.1251 0.00415039 52.0834 0.00415039H43.7501C35.7084 0.00415039 29.1667 6.54582 29.1667 14.5875V56.25C29.1667 64.2916 35.7084 70.8333 43.7501 70.8333Z" fill="${microphoneColor}"/>
  <path d="M72.9167 37.5C71.7667 37.5 70.8334 38.4333 70.8334 39.5833V60.4167C70.8334 68.4583 64.2917 75 56.25 75H39.5834C31.5417 75 25 68.4583 25 60.4167V39.5833C25 38.4333 24.0667 37.5 22.9167 37.5C21.7667 37.5 20.8334 38.4333 20.8334 39.5833V60.4167C20.8334 70.7542 29.2459 79.1667 39.5834 79.1667H45.8334V95.8333H31.25C30.1 95.8333 29.1667 96.7667 29.1667 97.9167C29.1667 99.0667 30.1 100 31.25 100H64.5834C65.7334 100 66.6667 99.0667 66.6667 97.9167C66.6667 96.7667 65.7334 95.8333 64.5834 95.8333H50V79.1667H56.25C66.5875 79.1667 75 70.7542 75 60.4167V39.5833C75 38.4333 74.0667 37.5 72.9167 37.5Z" fill="${microphoneColor}"/>
</svg>
`;

export const send: string = `
<svg width="100" height="100" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" clip-rule="evenodd" d="M94.613 5.38701C95.8178 6.59176 96.1677 8.40861 95.4965 9.97462L59.7823 93.308C59.1008 94.898 57.5077 95.9016 55.7793 95.8297C54.0509 95.7578 52.5467 94.6254 51.9996 92.9842L40.7537 59.2464L7.01582 48.0004C5.37468 47.4534 4.24226 45.9492 4.17037 44.2207C4.09848 42.4923 5.10206 40.8993 6.69211 40.2178L90.0254 4.50352C91.5915 3.83237 93.4083 4.18227 94.613 5.38701ZM20.0478 43.5603L45.3653 51.9995C46.6095 52.4142 47.5858 53.3905 48.0006 54.6347L56.4397 79.9522L83.7337 16.2664L20.0478 43.5603Z" fill="${sendColor}"/>
  <path fill-rule="evenodd" clip-rule="evenodd" d="M92.5296 13.3629L46.6963 59.1963L40.8037 53.3037L86.637 7.47038L92.5296 13.3629Z" fill="${sendColor}"/>
</svg>
`;

export const send2: string = `
<svg width="100" height="100" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
  <path d="M19.7175 45.4614L43.9359 53.5342C44.8669 53.8445 45.3325 53.9997 45.6663 54.3335C46.0002 54.6674 46.1554 55.1329 46.4657 56.064L54.5385 80.2824L54.5385 80.2825C56.1073 84.9889 56.8917 87.3421 58.3333 87.3421C59.7748 87.3421 60.5592 84.9889 62.128 80.2824L82.0683 20.4614C82.6203 18.8056 82.8962 17.9777 82.4592 17.5407C82.0222 17.1036 81.1943 17.3796 79.5385 17.9315L19.7175 37.8719C15.011 39.4407 12.6578 40.2251 12.6578 41.6666C12.6578 43.1081 15.011 43.8925 19.7175 45.4614Z" fill="${sendColor}" stroke="${sendColor}" stroke-width="5"/>
</svg>
`;
