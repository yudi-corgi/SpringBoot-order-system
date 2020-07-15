## 简介
一个采用 SpringBoot/FreeMarker + Vue 前后端分离的微信平台订单系统，挂靠微信公众号，分为前台(Vue)、管理系统(FreeMarker)两部分，用户可在前台进行浏览、下单/支付、退款等操作，管理系统则服务于商家，针对店铺进行商品管理和数据管理。

## 实现技术
- 后端：SpringBoot、Spring Data JPA、Freemarker
- 前端：Vue.js 及相关组件
- 数据库：Mysql、Redis(缓存)
- 其它：Tomcat、WebSocket、微信 SDK

## 补充
- 个人 DEMO，由于没有注册个人公众号，对于公众平台方面的功能 API 全部基于测试公众号
- 此仓库目前只上传管理系统代码，前台(Vue)部分省略
