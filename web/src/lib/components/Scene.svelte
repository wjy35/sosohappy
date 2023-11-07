<script lang="ts">
  import { T } from '@threlte/core'
  import { ContactShadows, Grid, OrbitControls } from '@threlte/extras'
</script>

<T.PerspectiveCamera
        makeDefault
        position={[10, 10, 10]}
        fov={15}
>
  <OrbitControls
          enableZoom={true}
          enableDamping
          target.y={0.5}
  />
</T.PerspectiveCamera>

<T.DirectionalLight
        intensity={5}
        position={[5, 5, 10]}
        castShadow
/>
<T.AmbientLight intensity={2} />

<!--<Grid-->
<!--        position.y={-0.001}-->
<!--        cellColor="#000000"-->
<!--        sectionColor="#ffffff"-->
<!--        sectionThickness={0}-->
<!--        fadeDistance={25}-->
<!--        cellSize={2}-->
<!--/>-->

<ContactShadows
        scale={10}
        blur={2}
        far={2.5}
        opacity={0.5}
/>