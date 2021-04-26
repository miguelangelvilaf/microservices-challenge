<template>
  <div class="w-75 mx-auto">
    <v-client-table
      :data="tableData"
      :columns="columns"
      :options="options">
    </v-client-table>
  </div>
</template>

<script>

export default {
  data() {
    return {
      columns: ['id', 'image', 'name', 'gender', 'status', 'species', 'popularity'],
      tableData: [],
      options: {
        filterByColumn: false,
        headings: {
          id: 'ID',
          image: 'Image',
          name: 'Name',
          gender: 'Gender',
          status: 'Status',
          species: 'Species',
          popularity: 'Popularity'
        },
        templates: {
          image: function (h, row) {
            return <img src={row.image} alt={row.name} width="200"/>
          },
          popularity: function (h, row) {
            return <span>{row.popularity}%</span>
          }
        },
        sortable: ['id', 'name', 'gender', 'status', 'popularity'],
        filterable: false
      }
    }
  },
  mounted() {
    this.axios.get('https://localhost:8443/rickandmorty-microservice/characters/5/27,181,478,12,310').then(res => {
      this.tableData = res.data
    })
  }

}
</script>

<style>
#app {
  width: 100%;
  margin-top: 50px;
}

.VuePagination {
  text-align: center;
}

.vue-title {
  text-align: center;
  margin-bottom: 10px;
}

.vue-pagination-ad {
  text-align: center;
}

.glyphicon.glyphicon-eye-open {
  width: 16px;
  display: block;
  margin: 0 auto;
}

tr td:nth-child(10) {
  width: 35%;
}

.VueTables__child-row-toggler {
  width: 16px;
  height: 16px;
  line-height: 16px;
  display: block;
  margin: auto;
  text-align: center;
}

.VueTables__child-row-toggler--closed::before {
  content: "+";
}

.VueTables__child-row-toggler--open::before {
  content: "-";
}

</style>
