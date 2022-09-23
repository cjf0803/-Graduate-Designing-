export default{
    namespaced: true,
    state:{
        userInfo:(localStorage.getItem("loginData")&&JSON.parse(localStorage.getItem("loginData"))
        ||{}),
        userMoneyInfo:(localStorage.getItem("money")&&JSON.parse(localStorage.getItem("money"))
        ||{}),

    },
    mutations: {
        setUserInfo(state,uInfo){
            state.userInfo=uInfo
        },
        setMoneyInfo(state,mInfo){
            state.userMoneyInfo=mInfo
        },

    },
    getters: {
        //get方法
        getUserInfo: state => state.userInfo,
        getUserMoneyInfo: state => state.userMoneyInfo
      },
}