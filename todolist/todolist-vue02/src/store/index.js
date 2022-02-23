import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    /*user: {
      username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username
    }*/
    user:JSON.parse(sessionStorage.getItem("user"))
  },
  mutations: {
    login (state, user) {
      state.user = user
      // window.localStorage.setItem('user', JSON.stringify(user))
      sessionStorage.setItem("user", JSON.stringify(user))
    },
    REMOVE_INFO: (state) => {
      state.user = {}
      sessionStorage.setItem("user", JSON.stringify(''))
    },
    setName(state,newName){
      state.user.username = newName
      sessionStorage.setItem("user", JSON.stringify(state.user))
    }
  },
  getters:{     // get 获取
    getUser:state => {
      return JSON.stringify(state.user)
    }
  },
})

/*
export default new Vuex.Store({
  //全局配置参数
  state: {
    token:'',
    userInfo:JSON.parse(sessionStorage.getItem("userInfo"))
  },
  mutations: { // set修改/设置
    SET_TOKEN: (state, token) => {
      state.token = token
      localStorage.setItem("token", token)
    },
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo
      sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
    },
    REMOVE_INFO: (state) => {
      state.token = ''
      state.userInfo = {}
      localStorage.setItem("token", '')
      sessionStorage.setItem("userInfo", JSON.stringify(''))
    }
  },
  getters:{     // get 获取
    getUser:state => {
      return state.userInfo
    }
  },
  actions: {
  },
  modules: {
  }
})
*/
