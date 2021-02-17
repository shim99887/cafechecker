<template>
  <div class="container">
    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">title</th>
          <th scope="col">writer</th>
          <th scope="col">img</th>
          <th scope="col">file</th>
        </tr>
      </thead>
      <tbody v-if="datas.length > 0">
        <tr v-for="(item, index) in datas" :key="index">
          <th scope="row">{{ item.no }}</th>
          <td>{{ item.title }}</td>
          <td>{{ item.writer }}</td>
          <td>{{ item.img }}</td>
          <td>{{ item.file }}</td>
        </tr>
      </tbody>
      <tbody v-else>
        데이터 없음
      </tbody>
    </table>
  </div>
</template>
<script>
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      datas: []
    };
  },
  created() {
    axios.get(`${SERVER_URL}/crawl/img`).then(response => {
      this.datas = response.data;
    });
  }
};
</script>
