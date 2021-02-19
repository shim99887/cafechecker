<template>
  <div class="container">
    <v-row justify="center">
      <v-dialog v-if="!getAccessToken" v-model="dialog" max-width="600px">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            class="ml-2"
            color="secondary"
            outlined
            v-bind="attrs"
            v-on="on"
          >
            로그인
          </v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">로그인</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    label="id*"
                    v-model="user.id"
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    label="Password*"
                    v-model="user.pwd"
                    type="password"
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="login">
              login
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <div v-else>
          {{getUserName}}님이 로그인하셨습니다.
          <v-btn secondary outlined class="ml-2 mr-2" @click="logout">로그아웃</v-btn>
      </div>
    </v-row>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data: () => ({
    dialog: false,
    user: {
      id: "",
      pwd: "",
    },
  }),
    computed: {
    ...mapGetters([
      "getAccessToken",
      "getUserId",
      "getUserName",
    ]),
  },
  methods: {
    login: function() {
      event.preventDefault();
      this.dialog = false;
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store.dispatch("LOGIN", this.user);
      this.user.id = "";
      this.user.pwd = "";

    },
    logout() {
      this.$store
        .dispatch("LOGOUT")
        .then(() => this.$router.replace("/").catch(() => {}));
    },
  },
};
</script>
