const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // proxyTable: {
  //   "/api":{
  //     target:"https://restapi.amap.com/v3/weather/weatherInfo?city=330300&key=b36923bbbb20e23142f1d33ca8dc8ff6",
  //     changeOrigin:true,//是否跨域
  //     pathRewrite:{
  //       "^/api":""//重写为空,这个时候api就相当于上面target接口基准地址
  //     }
  //   }
  // }
  devServer: {
    proxy: {
      '/api': {
        target: 'https://restapi.amap.com/v3/weather/weatherInfo?city=330300&key=b36923bbbb20e23142f1d33ca8dc8ff6',//天气接口域名
        pathRewrite: { '^/api': '' },
      },
    },
  },

})