import Vue from "vue";
import VueRouter from "vue-router";
import GetList from "../components/GetList.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/list/:page",
    name: "list",
    component: GetList
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
