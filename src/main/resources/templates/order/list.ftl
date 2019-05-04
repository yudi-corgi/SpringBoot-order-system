<html>
<#include "../common/header.ftl">

<body>
    <div id="wrapper" class="toggled">
        <#--边栏 sidebar-->
        <#include "../common/nav.ftl">
        <#--主要内容 content-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th>订单id</th>
                                <th>姓名</th>
                                <th>手机号</th>
                                <th>地址</th>
                                <th>金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                        <#list orderDTOPage.content as orderDTO>

                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.buyerName}</td>
                            <td>${orderDTO.buyerPhone}</td>
                            <td>${orderDTO.buyerAddress}</td>
                            <td>${orderDTO.orderAmount}</td>
                            <td>${orderDTO.getOrderStatusEnum().msg}</td>
                            <td>${orderDTO.getPayStatusEnum().msg}</td>
                            <td>${orderDTO.createTime}</td>
                            <td>
                                <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                            </td>
                            <td>
                                <#if orderDTO.getOrderStatusEnum().msg == "新订单">
                                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}"> 取消</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                            </tbody>
                        </table>
                    </div>

                    <!-- 分页 -->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">逆滑</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}">逆滑</a></li>
                        </#if>
                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage == index >
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>
                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">顺溜</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}">顺溜</a></li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#-- 弹窗 -->
    <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                上帝叫你起来处理订单啦！
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
    <#--播放音乐-->
    <audio id="notice" loop="loop">
        <source src="/sell/mp3/song.mp3" type="audio/mpeg">
    </audio>


</div>


</body>
</html>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    //配置 websocket 客户端监听后端订单列表是否有变动
    var websocket = null;
    //判断浏览器是否支持 websocket
    if ('websocket' in window) {
        //url 是ws协议，非http协议
        websocket = new WebSocket('ws://hxxuyh.natappfree.cc/sell/webSocket')
    } else {
        alert('该浏览器不支持websocket')
    }
    //websocket建立
    websocket.onopen = function (event) {
        console.log('建立连接')
    };
    //关闭
    websocket.onclose = function (event) {
        console.log('关闭连接')
    };
    //接收消息
    websocket.onmessage = function (event) {
        console.log('收到消息：' + event.data);
        //弹窗提醒，播放音乐
        $('#myModal').modal('show');
        document.getElementById("notice").play();
    };
    //发生错误
    websocket.onerror = function () {
        alert('websocket通信发生错误')
    };

    //弹窗关闭后 关闭websocket
    window.onbeforeunload = function () {
        websocket.close()
    }
</script>
