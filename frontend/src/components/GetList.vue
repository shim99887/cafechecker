<template>
  <div class="container">
    <v-row align="center" class="">
      <v-spacer />
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
    <v-row justify="center">

    <table class="center table table-striped custab">
      <thead>
        <tr>
          <th scope="col"></th>
          <th class="text-center" scope="col">#</th>
          <th scope="col">title</th>
          <th scope="col">writer</th>
          <th scope="col">img</th>
          <th scope="col">file</th>
        </tr>
      </thead>
      <tbody>
        <tr
          style="height:10px;"
          v-for="(item, index) in datas.slice((page - 1) * 10, page * 10)"
          :key="index"
        >
          <td class="p-0 text-center">
            <v-checkbox
              v-model="checked"
              style="margin-bottom:0px;"
              :value="item.no"
            />
          </td>
          <th
            style=" display:flex; align-items:center; justify-content:center;"
            scope="row"
          >
            {{ item.no }}
          </th>
          <td>{{ item.title }}</td>
          <td>{{ item.writer }}</td>
          <td>{{ item.img }}</td>
          <td>{{ item.file }}</td>
        </tr>
      </tbody>
    </table>
    </v-row>
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
      content: "",
      checked: [],
    };
  },
  created() {
    axios.get(`${SERVER_URL}/crawl/list`).then((response) => {
      this.datas = response.data;
      this.maxPage = Math.floor(this.datas.length / 10 + 1);
    });
  },
  methods: {
    next(page) {
      this.page = page;
      this.checked = [];
    },
    search() {
      const params = new URLSearchParams();
      params.append("type", this.selected);
      params.append("content", this.content);

      axios.get(`${SERVER_URL}/crawl`, { params }).then((response) => {
        this.datas = response.data;
        this.maxPage = Math.floor(this.datas.length / 10 + 1);
        this.page = 1;
      });
    },
  },
};
</script>
<style lang="css">
.custab {
  border: 1px solid #ccc;
  padding: 5px;
  box-shadow: 3px 3px 2px #ccc;
  transition: 0.5s;
}
.custab:hover {
  box-shadow: 3px 3px 0px transparent;
  transition: 0.5s;
}
table{
  align-items: center;
  justify-content: center;
}
.v-input__slot {
  align-items: center;
  justify-content: center;
  margin-bottom : 0px !important;
}
</style>
