<script lang="ts">
    import { Group } from 'three'
    import { T, forwardEventHandlers } from '@threlte/core'
    import { useGltf, useGltfAnimations } from '@threlte/extras'
    import {onMount} from "svelte";
    import {LoopOnce} from "three/src/constants";

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

    export const ref = new Group()

    const gltf = useGltf('/Ox_Animations.glb', { useDraco: true })
    export const { actions, mixer } = useGltfAnimations<ActionName>(gltf, ref)

    const component = forwardEventHandlers()

    // let currentActionKey = 'Jump'
    // console.log(actions.current['Jump'])
    // actions.current.Jump?.play()
    const onEvent = () => {
        actions.current.Jump?.play()
    }

    const startAction = () => {
        console.log(actions)
    }

    onMount(()=>{
        startAction();
    })

    $: console.log($actions)
    $: mixer.timeScale = 0.5
    $: $actions['Death']?.setLoop(LoopOnce, 1)
    $: $actions['Death']?.play()

</script>

<T is={ref} dispose={false} {...$$restProps} bind:this={$component} on:click={onEvent}>
    {#await gltf}
        <slot name="fallback" />
    {:then gltf}
        <T.Group name="Scene" >
            <T.Group name="Rig" scale={0.4} on:click={onEvent} >
                <T is={gltf.nodes.root}/>
                <T.SkinnedMesh
                        name="Mesh"
                        geometry={gltf.nodes.Mesh.geometry}
                        material={gltf.materials.M_Ox}
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
