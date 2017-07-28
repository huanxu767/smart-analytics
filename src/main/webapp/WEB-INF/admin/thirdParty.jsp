<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>第三方</title>
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
    <link href="../css/plugins/jedate/jedate.css" rel="stylesheet">
    <link href="../css/easybuy-store.css" rel="stylesheet">
    <link href="../css/smart.css" rel="stylesheet">

</head>


<body style="background-color: transparent;">


<div class="wrapper my-wrapper ">

    <!--指标期间选择-->
    <div class="ibox" >
        <div class="ibox-title">
            <h5>对比期间选择</h5>
        </div>
        <div class="ibox-content">
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="col-md-1 control-label">查询期：</label>
                    <div class="col-md-2">
                        <input type="text" id="cBeginTime" class="form-control " placeholder="" readonly>
                    </div>
                    <div class="col-md-2">
                        <input type="text" id="cEndTime" class="form-control"placeholder="" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-1 control-label">对比期：</label>
                    <div class="col-md-2">
                        <input type="text" id="dBeginTime" class="form-control" placeholder="" readonly>
                    </div>
                    <div class="col-md-2">
                        <input type="text" id="dEndTime" class="form-control" placeholder="" readonly>
                    </div>
                    <div class="col-md-6">
                        <button type="button" class="btn btn-w-m btn-primary" id="searchButton">查询</button>
                        <button type="button" class="btn btn-w-m btn-default" id="addButton">导出编辑</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <!--分析思路-->
    <div class="ibox" >
        <div class="ibox-title">
            <h5>分析思路【各产品分析请点击具体产品查看】</h5>
        </div>
        <div class="ibox-content">
            <p class="text-primary">A、产品明细：从所属第三方的各个产品查看查询期和对比期的增长率</p>
        </div>
    </div>




    <!--产品明细-->
    <div class="ibox" id="thirdPartyBox">
        <div class="ibox-title">
            <h5>产品明细</h5>
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
                        <th>产品明细 </th>
                        <th>投放金额(万元)</th>
                        <th>对比期金额(万元)</th>
                        <th>增长率</th>
                        <th>投放人数</th>
                        <th>对比期人数</th>
                        <th>增长率</th>
                        <th>客单价(元)</th>
                        <th>对比期客单价(元)</th>
                        <th>增长率</th>
                    </tr>
                    </thead>
                    <tbody id="thirdPartyTable" >

                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <div class="modal inmodal fade" id="myModal6" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">指标说明</h4>
                </div>
                <div class="modal-body">
                    <p class="text-primary">新使用客户：查询期内首次使用任性付的客户</p>
                    <p class="text-primary">老客户定义：查询期内非首次使用的客户</p>
                    <p class="text-primary">人数可达渗透率：易购购物有额度人数/易购购物人数</p>
                    <p class="text-primary">使用人数：查询期内使用任性付的用户</p>
                    <p class="text-primary">人数实际渗透率：使用人数/易购购物人数</p>
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
    <script src="../js/plugins/jedate/jquery.jedate.js"></script>

    <!--我的图表-->
    <script src="../js/smart/myChart.js"></script>
</body>
<script>
    $(document).ready(function(){
        thirdPartyInit(false);
        bindEvents();
    });
    function bindEvents() {
        $("#searchButton").click(function(){
            queryThirdPartyTable();
        });
        $("#addButton").click(function(){
            window.parent.goDownPage();
        });
    }
</script>
</html>






