<script lang=ts>
    import { Canvas } from '@threlte/core'
    import Scene from '$lib/components/Scene.svelte'
    import Sosomon from "$lib/components/sosomon/Sosomon.svelte";
    export let data: any;
    const type = Number(data.type);
    const level = Number(data.level)-1;
    let actionStatus = "";

    $: actionStatus;
  
  </script>
  
  <div class="sosomon-wrap">
      <Canvas>
          <Scene/>
  <!--        <Ox/>-->
          <Sosomon sosomon={data[`type${type}`][level]} type={type} level={level} actionStatus={actionStatus}/>
      </Canvas>
      <div class="button-flex-box">
          <button class="animation-button" on:click={() => actionStatus = "attack"}>공격</button>
          <button class="animation-button" on:click={() => actionStatus = "death"}>후퇴</button>
          <button class="animation-button" on:click={() => actionStatus = "jump"}>점프</button>
          <button class="animation-button" on:click={() => actionStatus = "roll"}>구르기</button>
      </div>
  </div>
  
  <style>
      :global(body) {
          margin: 0;
      }
  
      .sosomon-wrap {
          width: 100vw;
          height: 100vh;
          background: rgb(47, 246, 246);
          /*background: linear-gradient(180deg, rgba(47, 246, 246, 1) 0%, rgba(47, 246, 246, 1) 100%);*/
      }
      .button-flex-box{
          display:"flex";
          align-items: center;
          position:fixed;
          bottom:10px;
          right:10px;
          background-color: transparent;
      }
      .animation-button{
          padding:10px 20px;
          background-color: #FEA9B0;
          border-radius: 50px;
          border: none;
          font-size: 12px;
          font-weight: 600;
          color:#FFFFFF;
          cursor: pointer;
      }
  </style>
  