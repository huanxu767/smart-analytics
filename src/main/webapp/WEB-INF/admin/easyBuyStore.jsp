<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>易购门店</title>
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
                        <button type="button" class="btn btn-w-m btn-primary" id="searchButton" onclick="easyBuyStore(false)">查询</button>
                        <button type="button" class="btn btn-w-m btn-default" id="addButton">导出编辑</button>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!--分析思路-->
    <div class="ibox" >
        <div class="ibox-title">
            <h5>分析思路</h5>
        </div>
        <div class="ibox-content">
            <p class="text-primary">A、营销策略分析：从分期与利息活动的角度分析金额的增幅</p>
            <p class="text-primary">B、构成分析：投放金额=人数*客单价，从品类客单和使用人数构成的角度查看对比增长</p>
            <p class="text-primary">C、营销能力分析：从品类渗透率的角度查看对比增长</p>
        </div>
    </div>

    <!--营销策略分析-->
    <div class="ibox" >
        <div class="ibox-title">
            <h5>营销策略分析【注：活动包含三零分期和购物贷电子券】</h5>
        </div>
        <div class="ibox-content">
            <div id="marketingChart" class="normal-chart">

            </div>
        </div>
    </div>

    <!--构成分析-->
    <div class="ibox" id="plkdrsBox">
        <div class="ibox-title">
            <h5>构成分析&nbsp;&nbsp;</h5>
            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;">品类客单人数</span>
        </div>
        <div class="ibox-content">
            <div class="sk-spinner sk-spinner-wave">
                <div class="sk-rect1"></div>
                <div class="sk-rect2"></div>
                <div class="sk-rect3"></div>
                <div class="sk-rect4"></div>
                <div class="sk-rect5"></div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>品类 </th>
                                <th>投放金额(万元)</th>
                                <th>增长率</th>
                                <th>客单价（元）</th>
                                <th>增长率</th>
                                <th>投放人数</th>
                                <th>增长率</th>
                            </tr>
                            </thead>
                            <tbody id="easyBuyCatTabls">

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-lg-6" >
                    <div id="circleChart" class="normal-chart">

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--构成分析-->
    <div class="ibox" id="easyBuyGcfxBox">
        <div class="ibox-title">
            <h5>客群构成分析&nbsp;&nbsp;</h5>
            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;"></span>
        </div>
        <div class="ibox-content" style="padding-top: 20px;">
            <div class="sk-spinner sk-spinner-wave">
                <div class="sk-rect1"></div>
                <div class="sk-rect2"></div>
                <div class="sk-rect3"></div>
                <div class="sk-rect4"></div>
                <div class="sk-rect5"></div>
            </div>

            <div class="row">
                <div class="col-lg-6">
                    <div id="custSTLChart" class="normal-chart">

                    </div>
                </div>
                <div class="col-lg-6">
                    <div id="custGCChart" class="normal-chart">

                    </div>
                </div>
            </div>
            <div class="row" style="padding-top: 20px;">
                <div class="col-lg-12" >
                    <div id="custQDChart" class="normal-chart">

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--新老客户分析-->
    <div class="row">
        <div class="col-lg-6" >
            <div class="ibox" id="easySgkhBox">
                <div class="ibox-title">
                    <h5>首购客户&nbsp;&nbsp;</h5>
                    <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" id="ebFirstBuy">-</span>
                    <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" >增长<span id="ebFirstBuyUp">-</span></span>
                    <div class="ibox-tools">
                        <%--<a href="javascript:void(0);" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal6">?</a>--%>
                    </div>
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
                                <th>顾客来源 </th>
                                <th>人数</th>
                                <th>增长率</th>
                            </tr>
                            </thead>
                            <tbody id="easyBuyFirstBuyTable">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-6" >
            <div class="ibox" id="easyFgBox">
                <div class="ibox-title">
                    <h5>复购客户&nbsp;&nbsp;</h5>
                    <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" id="ebAgainBuy">-</span>
                    <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" >增长<span id="ebAgainBuyUp">-</span></span>
                    <div class="ibox-tools">
                        <%--<a href="javascript:void(0);" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal6">?</a>--%>
                    </div>
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
                                <th>顾客来源 </th>
                                <th>人数</th>
                                <th>增长率</th>
                            </tr>
                            </thead>
                            <tbody id="easyBuyAgainBuyTable">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!--营销能力分析-->
    <div class="ibox" id="easyYxnlbox">
        <div class="ibox-title">
            <h5>营销能力分析</h5>
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
                        <th>品类 </th>
                        <th>投放金额(万元)</th>
                        <th>对比期金额(万元)</th>
                        <th>增长</th>
                        <th>实际渗透率</th>
                        <th>对比期实际</th>
                        <th>增长</th>
                        <th>可达渗透率</th>
                        <th>对比期可达</th>
                        <th>增长</th>
                        <th>实际/可达</th>
                        <th>对比期实际/可达</th>
                        <th>增长</th>
                    </tr>
                    </thead>
                    <tbody id="easyBuyCatTable">
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
        easybuyStoreInit(false);
        $("#addButton").click(function(){
            window.parent.goDownPage();
        });
    });
</script>
</html>






