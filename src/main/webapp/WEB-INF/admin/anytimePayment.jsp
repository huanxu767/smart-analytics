<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>随借随还</title>
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
                        <button type="button" class="btn btn-w-m btn-primary" id="searchButton" onclick="anytimeLendPayment(false)">查询</button>
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
            <p class="text-primary">A、营销策略分析：从利息活动的角度分析金额和人数的增幅</p>
            <p class="text-primary">B、客群分析：从使用人数构成的角度查看对比增长</p>
            <p class="text-primary">C、额度影响分析：从额度提升与否的角度查看与对比期数据对比</p>
            <p class="text-primary">D、营销活动明细：展现查询期间内具体营销活动带来的投放</p>

        </div>
    </div>

    <!--营销策略分析-->
    <div class="ibox">
        <div class="ibox-title">
            <h5>营销策略分析&nbsp;&nbsp;</h5>
            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" ></span>
        </div>
        <div class="ibox-content" style="padding-top: 20px;">
            <div class="row">
                <div class="col-lg-6">
                    <div id="tfjeChart" class="normal-chart">

                    </div>
                </div>
                <div class="col-lg-6">
                    <div id="tfrsChart" class="normal-chart">

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--客群分析-->
    <div class="ibox" id="anyKqfxBox">
        <div class="ibox-title">
            <h5>客群分析</h5>
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
            <div class="row">
                <div class="col-lg-6">
                    <ul class="sortable-list connectList agile-list ui-sortable" style="margin-top: 14px;">
                        <li class="warning-element" id="task1" style="background:#fff">
                            首购客户：<span id="firstBuyCust">-</span>
                            <div class="stat-percent font-bold text-info">增长<span  id="firstBuyCustUp">-</span>% </div>
                        </li>
                        <li class="success-element" id="task2" style="background:#fff">
                            复购客户：<span id="againBuyCust">-</span>
                            <div class="stat-percent font-bold text-info">增长<span id="againBuyCustUp">-</span>% </div>
                        </li>
                        <li class="info-element" id="task3" style="background:#fff">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>维度 </th>
                                        <th>首购占比</th>
                                        <th>复购客户占比</th>
                                    </tr>
                                    </thead>
                                    <tbody >
                                    <tr><td>查询期</td><td id="firstBuyCustPersent">-</td><td id="againBuyCustPersent">-</td></tr>
                                    <tr><td>对比期</td><td id="firstBuyCustPersentCompare">-</td><td id="againBuyCustPersentCompare">-</td></tr>
                                    </tbody>
                                </table>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="col-lg-6">
                    <div id="xlkhChart" class="normal-chart">
                        <!--新复购客户对比-->
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-4">
                    <div id="rygcChart" class="normal-chart">
                        <!--人员构成-->
                    </div>
                </div>
                <div class="col-lg-8">
                    <div id="fyghylyChart" class="normal-chart">
                        <!--非易购会员来源渠道-->
                    </div>
                </div>
            </div>

        </div>
    </div>


    <!--C、额度影响-->
    <div class="ibox">
        <div class="ibox-title">
            <h5>额度影响【20170520前报表按照20170628的额度数据进行统计，20170520之后的额度数据为每天统计的额度数据】</h5>
            <div class="ibox-tools">
                <%--<a href="javascript:void(0);" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal7">?</a>--%>
            </div>
        </div>
        <div class="ibox-content">
            <div class="row margin-top-20">
                <div class="normal-chart" id="gedsyrsChart">
                    <!--各额度使用人数对比-->
                </div>
            </div>

            <div class="row margin-top-20">
                <div class="normal-chart" id="gedrjtfChart">
                    <!--各额度人均投放金额对比-->
                </div>
            </div>
            <div class="row margin-top-20">
                <div class="normal-chart" id="gedsylChart">
                    <!--各额度用户使用率分析-->
                </div>
            </div>
        </div>
    </div>

    <!--营销活动明细-->
    <div class="ibox" id="sjshActivityBox">
        <div class="ibox-title" >
            <h5>营销活动明细</h5>
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
                        <th>活动名称</th>
                        <th>使用张数</th>
                        <th>贷款金额（元）</th>
                        <th>应收手续费（元）</th>
                        <th>券优惠手续费（元）</th>
                    </tr>
                    </thead>
                    <tbody id="activtyList" >
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="5" class="footable-visible" >
                                <div class="tcdPageCode m-pagination" id="sjshActivityPage"></div>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>


    <!--提示 1-->
    <div class="modal inmodal fade" id="myModal6" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <p class="text-primary">指标说明</p>
                    <p class="text-primary">首购客户：查询期内首次使用的客户</p>
                    <p class="text-primary">复购客户：查询期内非首次使用的客户</p>
                    <p class="text-primary">易购员工：易购员工开通任性付的人数</p>
                    <p class="text-primary">非易购员工：非易购员工开通任性付的人数</p>
                </div>
            </div>
        </div>
    </div>
    <!--提示 2-->
    <div class="modal inmodal fade" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <p class="text-primary">指标说明</p>
                    <p class="text-primary">• 使用人数：按用户额度区分各额度查询期和对比期的使用人数</p>
                    <p class="text-primary">• 人均投放金额：查询/对比期间内各额度区间内总投放金额÷使用人数</p>
                    <p class="text-primary">• 使用率：查询/对比期间内各额度区间内使用人数÷各额度总人数</p>
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
    <script src="../js/smart/myChart.js"></script>

</body>
<script>
    $(document).ready(function(){
        anytimeLendPaymentInit(false);
        $("#addButton").click(function(){
            window.parent.goDownPage();
        });
    });
</script>
</html>






