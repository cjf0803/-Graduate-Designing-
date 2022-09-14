export default{
    namespaced: true,
    state:{
        userInfo:(localStorage.getItem("loginData")&&JSON.parse(localStorage.getItem("loginData"))
        ||{}),
    },
    mutations: {
        setUserInfo(state,uInfo){
            state.userInfo=uInfo
        }
    },
    getters: {
        //get方法
        getUserInfo: state => state.userInfo
      },
}