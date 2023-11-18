<script lang="ts">
    import { Group } from 'three'
    import { T, forwardEventHandlers } from '@threlte/core'
    import { useGltf, useGltfAnimations } from '@threlte/extras'
    import {onMount} from "svelte";
    import {LoopOnce, LoopRepeat, LoopPingPong} from "three/src/constants";

    type ActionName =
        | 'Attack'
        | 'Bounce'
        | 'Clicked'
        | 'Death'
        | 'Eat'
        | 'Fear'
        | 'Fly'
        | 'Hit'
        | 'Idle_A'
        | 'Idle_B'
        | 'Idle_C'
        | 'Jump'
        | 'Roll'
        | 'Run'
        | 'Sit'
        | 'Spin'
        | 'Swim'
        | 'Walk'

    export let sosomon: any;
    export let type: number;
    export let level: number;
    export let actionStatus: string;
    $: actionStatus;

    export const ref = new Group()

    const gltf = useGltf(sosomon.url, { useDraco: true })
    export const { actions, mixer } = useGltfAnimations<ActionName>(gltf, ref)

    const component = forwardEventHandlers()

    const onEvent = () => {
        actions.current.Jump?.play()
    }

    const startAction = () => {
        console.log(actions)
    }

    onMount(()=>{
        startAction();
    })

    $: if(actionStatus === "attack"){
        $actions['Attack']?.stop()
        $actions['Death']?.stop()
        $actions['Jump']?.stop()
        $actions['Roll']?.stop()
        mixer.timeScale = 0.5
        $actions['Attack']?.setLoop(LoopPingPong, 1)
        $actions['Attack']?.play()
    }

    $: if(actionStatus === "death"){
        $actions['Attack']?.stop()
        $actions['Death']?.stop()
        $actions['Jump']?.stop()
        $actions['Roll']?.stop()
        mixer.timeScale = 0.5
        $actions['Death']?.setLoop(LoopPingPong, 1)
        $actions['Death']?.play()
    }

    $: if(actionStatus === "jump"){
        $actions['Attack']?.stop()
        $actions['Death']?.stop()
        $actions['Jump']?.stop()
        $actions['Roll']?.stop()
        mixer.timeScale = 0.5
        $actions['Jump']?.setLoop(LoopPingPong, 1)
        $actions['Jump']?.play()
    }

    $: if(actionStatus === "roll"){
        $actions['Attack']?.stop()
        $actions['Death']?.stop()
        $actions['Jump']?.stop()
        $actions['Roll']?.stop()
        mixer.timeScale = 0.5
        $actions['Roll']?.setLoop(LoopRepeat, Infinity)
        $actions['Roll']?.play()
    }

</script>

<T is={ref} dispose={false} {...$$restProps} bind:this={$component} on:click={onEvent}>
    {#await gltf}
        <slot name="fallback" />
    {:then gltf}
        <T.Group name="Scene" >
            <T.Group name="Rig" scale={0.4} on:click={onEvent} >
                <T is={gltf.nodes.root}/>
                {#if type===2&&level===9}
                    <T is={gltf.nodes.body}/>
                {/if}
                <T.SkinnedMesh
                    name="Mesh"
                    geometry={gltf.nodes.Mesh.geometry}
                    material={gltf.materials[sosomon.name]}
                    material.color="white"
                    skeleton={gltf.nodes.Mesh.skeleton}
                    on:click={onEvent}
                />
            </T.Group>
        </T.Group>
    {:catch error}
        <slot name="error" {error} />
    {/await}
    <slot {ref} />
</T>
