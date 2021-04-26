<template>
  <div id="app">
    <a href="/"><img class="logo" alt="Rick and Morty" src="./assets/logo.png" width="600"></a>
    <vue-data-table></vue-data-table>
  </div>
</template>

<script>
import VueDataTable from './components/VueDataTable'

export default {
  name: 'App',
  components: {
    VueDataTable
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
