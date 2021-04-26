import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import { ClientTable } from 'vue-tables-2';

Vue.use(ClientTable);

Vue.use(VueAxios, axios)

Vue.config.productionTip = false

import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '../node_modules/@fortawesome/fontawesome-free/css/all.css'
import '../node_modules/@fortawesome/fontawesome-free/js/all.js'

new Vue({
  render: h => h(App),
}).$mount('#app')
