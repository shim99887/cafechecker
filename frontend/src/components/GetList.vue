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
      <tbody>
        <tr v-for="(item, index) in datas" :key="index">
          <th scope="row">{{ item.no }}</th>
          <td>{{ item.title }}</td>
          <td>{{ item.writer }}</td>
          <td>{{ item.img }}</td>
          <td>{{ item.file }}</td>
        </tr>
      </tbody>
    </table>
    <v-container>
      <v-row justify="center">
        <v-col cols="8">
          <v-container class="max-width">
            <v-pagination
              v-model="page"
              class="my-4"
              :length="maxPage"
              :total-visible="7"
              @input="next"
            ></v-pagination>
          </v-container>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      datas: [],
      maxPage: 0,
      page: 1,
    };
  },
  created() {
    console.log("created");
    axios.get(`${SERVER_URL}/crawl/list/size`).then((response) => {
      this.maxPage = response.data / 10 + 1;
      console.log(this.maxPage);
    });

    axios.get(`${SERVER_URL}/crawl/` + this.page).then((response) => {
      this.datas = response.data;
	console.log(this.datas);
    });
  },
  methods: {
    next(page) {
      this.page = page;
      axios.get(`${SERVER_URL}/crawl/` + this.page).then((response) => {
        this.datas = response.data;
      });
    },
  },
};
</script>
<style lang="css"></style>
