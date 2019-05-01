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
                        <div class="col-md-6 column">
                            <form role="form" method="post" action="/sell/seller/category/save">
                                <div class="form-group">
                                    <h2 style="color: #136b7a">请登录</h2>
                                </div>
                                <div class="form-group">
                                    <label >账户</label>
                                    <input name="categoryName" type="text" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label >密码</label>
                                    <input name="categoryType" type="password" class="form-control"/>
                                </div>
                                <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!''}">
                                <button type="submit" class="btn btn-default">登录</button>
                                <button type="reset" class="btn btn-default">撤销</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</body>
</html>