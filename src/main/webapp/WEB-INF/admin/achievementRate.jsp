<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>达成率</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="../css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="../css/animate.css" rel="stylesheet">
    <!-- Toastr style -->
    <link href="../css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/plugins/ladda/ladda-themeless.min.css" rel="stylesheet">
    <link href="../css/plugins/steps/jquery.steps.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="../css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="../css/main.css" rel="stylesheet">
    <link href="../css/smart.css" rel="stylesheet">

</head>


<body style="background-color: transparent;">


<div class="wrapper my-wrapper ">

    <!--指标期间选择-->
    <div class="ibox" >
        <div class="ibox-title">
            <h5>指标期间选择</h5>
        </div>
        <div class="ibox-content">
            <div class="form-horizontal">
                <div class="form-group">

                    <label class="col-md-1 control-label">年份</label>
                    <div class="col-md-2">
                        <select class="form-control m-b" id="year" onchange="changeYear(this)">
                            <c:forEach var="year" items="${years}">
                                <option value="${year}" >${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <label class="col-md-2 control-label">季度筛选(累计截止)</label>
                    <div class="col-md-2">
                        <select class="form-control m-b"  id="quarter" onchange="changeQuarter(this)">
                            <option value="1"  <c:if test="${quarter eq 1}">selected="selected"</c:if>>一季度</option>
                            <option value="2"  <c:if test="${quarter eq 2}">selected="selected"</c:if>>二季度</option>
                            <option value="3"  <c:if test="${quarter eq 3}">selected="selected"</c:if>>三季度</option>
                            <option value="4"  <c:if test="${quarter eq 4}">selected="selected"</c:if>>四季度</option>
                        </select>
                    </div>

                    <div class="col-md-5">
                        <button type="button" class="btn btn-w-m btn-primary" id="searchButton">查询</button>
                        <button type="button" class="btn btn-w-m btn-default" id="addButton">导出编辑</button>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <!--达成情况及预测-->
    <div class="ibox" >
        <div class="ibox-title">
            <h5>达成情况及预测</h5>
        </div>
        <div class="ibox-content">
            <div id="areaChart" class="normal-chart">

            </div>

        </div>
    </div>
    <!--区域达成及排名-->
    <div class="ibox" id="rateListbox">
        <div class="ibox-title">
            <h5>区域达成及排名</h5>
        </div>
        <div class="ibox-content">
            <div class="sk-spinner sk-spinner-wave">
                <div class="sk-rect1"></div>
                <div class="sk-rect2"></div>
                <div class="sk-rect3"></div>
                <div class="sk-rect4"></div>
                <div class="sk-rect5"></div>
            </div>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>区域 </th>
                        <th>门店总投放</th>
                        <th>门店新客户投放</th>
                        <th>门店新客户</th>
                        <%--<th>现金贷总投放</th>--%>
                        <th>自拓现金贷投放</th>
                        <th>自拓现金贷新客户数</th>
                        <th>第三方总投放</th>
                        <th>第三方新增投放客户数</th>
                        <th>APP申请人数</th>
                        <th>排名</th>
                    </tr>
                    </thead>
                    <tbody id="rateList">

                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>


<!-- Mainly scripts -->
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="../js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="../js/inspinia.js"></script>
<!--<script src="../js/plugins/pace/pace.min.js"></script>-->

<!-- iCheck -->
<script src="../js/plugins/iCheck/icheck.min.js"></script>
<!-- Ladda -->
<script src="../js/plugins/ladda/spin.min.js"></script>
<script src="../js/plugins/ladda/ladda.min.js"></script>
<script src="../js/plugins/ladda/ladda.jquery.min.js"></script>
<!-- Sweet alert -->
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>
<!-- Steps -->
<script src="../js/plugins/steps/jquery.steps.js"></script>
<!-- echarts -->
<script src="../js/plugins/echarts/echarts.js"></script>
<script src="../js/lib/jquery.page.js"></script>
<!-- Jquery Validate -->
<script src="../js/plugins/validate/jquery.validate.min.js"></script>
<!--我的图表-->
<script src="../js/smart/myChart.js"></script>

</body>
<script>
    //初始化
    $(document).ready(function () {
        init();
        bindEvents();
        var storage = window.sessionStorage;
        storage.setItem("quarter",$("#quarter").val());
    });
    function init(){
        //达成率
        rateInit(false);
    }
    function bindEvents(){
        $("#searchButton").click(function(){
            rateInit();
        });
        $("#addButton").click(function(){
            window.parent.goDownPage();
        });
    }

    function changeQuarter(obj){
        var storage = window.sessionStorage;
        storage.setItem("quarter",$(obj).val());
    }

    function changeYear(obj){
        var storage = window.sessionStorage;
        storage.setItem("year",$(obj).val());
    }
</script>
</html>




