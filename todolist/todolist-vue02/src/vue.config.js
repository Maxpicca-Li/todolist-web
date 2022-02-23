module.exports={
    // publicPath:'/todolist',
    baseUrl:'./',
    assetsDir:'static',
    productionSourceMap:false,
    devServer:{
        proxy:{
            "/":{
                // TODO 服务器部署
                target:'http://localhost:9000',  // 本地
                // target:'http://182.92.198.13:9000',  // 服务器
                changeOrigin:true,
                pathRewrite: {
                    '/': ''
                }
            }
        }
    }
}