<template>
  <div class="container">
    <!-- <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">title</th>
          <th scope="col">writer</th>
          <th scope="col">img</th>
          <th scope="col">file</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in datas" :key="index">
          <th scope="row">{{ item.no }}</th>
          <td>{{ item.title }}</td>
          <td>{{ item.writer }}</td>
          <td>{{ item.img }}</td>
          <td>{{ item.file }}</td>
        </tr>
      </tbody>
    </table> -->
    <nav aria-label="...">
      <ul class="pagination">
        <li class="page-item disabled">
          <a class="page-link" href="#" tabindex="-1">Previous</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item active">
          <a class="page-link" href="/list/2"
            >2 <span class="sr-only">(current)</span></a
          >
        </li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
          <a class="page-link" href="#">Next</a>
        </li>
      </ul>
    </nav>
  </div>
</template>
<script>
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      datas: [],
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/crawl`, {
        data: {},
      })
      .then((response) => {
        this.datas = response.data;
      });
  },
};
</script>
