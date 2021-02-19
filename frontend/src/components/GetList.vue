<template>
  <div class="container">
    <v-row align="center" class="">
      <v-spacer/>
      <v-col cols="2">
        <v-select
          :items="selectItems"
          label="항목"
          height="10px"
          v-model="selected"
          outlined
        ></v-select>
      </v-col>
      <v-col cols="4">
        <v-text-field v-model="content" label="검색할 항목 입력" />
      </v-col>
      <v-col>
        <v-btn secondary outlined @click="search">검색</v-btn>
      </v-col>
    </v-row>
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
        <tr v-for="(item, index) in datas.slice((page-1)*10, page*10)" :key="index">
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
              :total-visible="10"
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
      selected: "제목",
      page: 1,
      selectItems: ["제목", "닉네임"],
      content:'',
    };
  },
  created() {
    console.log("created");
    axios.get(`${SERVER_URL}/crawl/list`).then((response) => {
      this.datas = response.data;
      this.maxPage = Math.floor(this.datas.length / 10 + 1);
      console.log(this.maxPage);
    });
  },
  methods: {
    next(page) {
      this.page = page;
      // axios.get(`${SERVER_URL}/crawl/` + this.page).then((response) => {
      //   this.datas = response.data;
      // });
    },
    search(){
      const params = new URLSearchParams();
      params.append('type', this.selected);
      params.append('content', this.content);

      axios.get(`${SERVER_URL}/crawl`, {params})
      .then(response => {
        this.datas = response.data;
        this.maxPage = Math.floor(this.datas.length / 10 + 1);
        this.page = 1;
      })
    }
  },
};
</script>
<style lang="css"></style>
