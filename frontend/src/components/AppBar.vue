<template>
  <div class="container">
    <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
      <li class="nav-item">
        <a
          class="nav-link active"
          id="pills-getList"
          data-toggle="pill"
          href="#getList"
          role="tab"
          aria-controls="getList"
          aria-selected="true"
          >모든 게시글</a
        >
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          id="getImageList-tab"
          data-toggle="pill"
          href="#getImageList"
          role="tab"
          aria-controls="getImageList"
          aria-selected="false"
          >이미지 오류</a
        >
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          id="getFileList-tab"
          data-toggle="pill"
          href="#getFileList"
          role="tab"
          aria-controls="getFileList"
          aria-selected="false"
          >파일 오류</a
        >
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          id="getTitleList-tab"
          data-toggle="pill"
          href="#getTitleList"
          role="tab"
          aria-controls="getTitleList"
          aria-selected="false"
          >말머리 오류</a
        >
      </li>
      <v-spacer />
      <li class="nav-item">
        <login-modal />
      </li>
      <li class="nav-item">
        <v-btn secondary outlined @click="refreshBoard" class="ml-2">글 목록 새로고침</v-btn>
      </li>
    </ul>
    <div class="tab-content" id="pills-tabContent">
      <div
        class="tab-pane fade show active"
        id="getList"
        role="tabpanel"
        aria-labelledby="getList-tab"
      >
        <get-list />
      </div>
      <div
        class="tab-pane fade"
        id="getImageList"
        role="tabpanel"
        aria-labelledby="getImageList-tab"
      >
        <get-image-list />
      </div>
      <div
        class="tab-pane fade"
        id="getFileList"
        role="tabpanel"
        aria-labelledby="getFileList-tab"
      >
        <get-file-list />
      </div>
      <div
        class="tab-pane fade"
        id="getTitleList"
        role="tabpanel"
        aria-labelledby="getTitleList-tab"
      >
        <get-title-list />
      </div>
    </div>
  </div>
</template>
<script>
import GetList from "./GetList.vue";
import GetFileList from "./GetFileList.vue";
import GetImageList from "./GetImageList.vue";
import GetTitleList from "./GetTitleList.vue";
import LoginModal from "./LoginModal.vue";
import { mapGetters } from "vuex";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  components: {
    GetList,
    GetFileList,
    GetImageList,
    GetTitleList,
    LoginModal,
  },
  computed: {
    ...mapGetters([
      "getAccessToken",
      "getUserId",
      "getUserName",
    ]),
  },

  methods: {
    refreshBoard() {
      let config = {
        onUploadProgress: (progressEvent) => {
          let percentCompleted = Math.floor(
            (progressEvent.loaded * 100) / progressEvent.total
          );
          // do whatever you like with the percentage complete
          // maybe dispatch an action that will update a progress bar or something
          console.log(percentCompleted);
        },
      };
      axios
        .post(`${SERVER_URL}/crawl/insert`, config)
        .then((response) => {
          console.log(response);
          window.location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
