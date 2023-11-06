<script lang=ts>
  import { Canvas } from '@threlte/core'
  import Scene from '$lib/components/Scene.svelte'
  import Sosomon from "$lib/components/sosomon/Sosomon.svelte";
  export let data;
  const type = Number(data.type);
  const level = Number(data.level)-1;
  let animationStatus = false;

  const updateAnimationStatus = (status: boolean) => {
    animationStatus = status;
  }
  $: animationStatus;
</script>

<div>
    <Canvas>
        <Scene/>
<!--        <Ox/>-->
        <Sosomon sosomon={data[`type${type}`][level]} type={type} level={level} animationStatus={animationStatus} updateAnimationStatus={updateAnimationStatus}/>
    </Canvas>
    <button class="animation-button" on:click={() => updateAnimationStatus(true)}>애니메이션 보기</button>
</div>

<style>
    :global(body) {
        margin: 0;
    }

    div {
        width: 100vw;
        height: 100vh;
        background: rgb(47, 246, 246);
        /*background: linear-gradient(180deg, rgba(47, 246, 246, 1) 0%, rgba(47, 246, 246, 1) 100%);*/
    }
    .animation-button{
        padding:10px 20px;
        background-color: #FEA9B0;
        border-radius: 50px;
        border: none;
        position: absolute;
        bottom:10px;
        right:10px;
        font-size: 12px;
        font-weight: 600;
        color:#FFFFFF;
    }
</style>
