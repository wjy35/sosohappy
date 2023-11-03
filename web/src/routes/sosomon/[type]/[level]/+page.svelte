<script>
  import { Canvas } from '@threlte/core'
  import Scene from '$lib/components/Scene.svelte'
  import Sosomon from "$lib/components/sosomon/Sosomon.svelte";
  export let data;
  const type = Number(data.type);
  const level = Number(data.level)-1;
</script>

<div>
    <Canvas>
        <Scene/>
<!--        <Ox/>-->
        <Sosomon sosomon={data[`type${type}`][level]} type={type} level={level}/>
    </Canvas>
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
</style>
