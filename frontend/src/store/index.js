import Vue from "vue";
import Vuex from "vuex";
import axios from 'axios';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default new Vuex.Store({

  state: {
    accessToken: null,
    userId: '',
    userName: '',
  },
  plugins: [
    createPersistedState()
  ],
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getUserId(state) {
      return state.userId;
    },
    getUserName(state) {
      return state.userName;
    },
  },

  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload['auth-token'];
      state.userId = payload['user-id'];
      state.userName = payload['user-name'];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userId = '';
      state.userName = '';
    },
  },
  actions: {
    LOGIN(context, user) {
      const params = new URLSearchParams();
      params.append('id', user.id);
      params.append('pwd', user.pwd);

      return axios.post(`${SERVER_URL}/login`, params // pwd: user.pwd,
        // name:'',
        // role:''
      ).then((response) => {
        if (response.data.message) {
          console.log(response);
          alert('아이디 또는 비밀번호를 틀렸습니다.');
        }else{
          console.log(response);
          context.commit('LOGIN', response.data);
          axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
          alert('로그인 성공');
        }

      })
    },
    LOGOUT(context) {
      context.commit('LOGOUT');
      axios.defaults.headers.common['auth-token'] = undefined;
    },

  },
  modules: {}
});
