<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>销售分析</title>
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

    <link href="../css/total-lend.css" rel="stylesheet">
    <link href="../css/smart.css" rel="stylesheet">
    <style>
        .page-heading{
            border-top: 0;
            padding: 0 10px 8px 10px;
            margin-bottom: 20px;
        }
    </style>
</head>

<body style="background-color: transparent;">

<div  class="gray-bg">

    <div class="wrapper my-wrapper " >
        <!--目录-->
        <div class="row  white-bg page-heading">
            <h2>目录
                <h4 id="muluDesc"></h4>
                <h4 id="compareDesc"></h4>
            </h2>
        </div>
        <!--目录 明细-->
        <div class="ibox" >
            <div class="ibox-content">
                <div class="row">
                    <div class="col-lg-3">
                        <h4 class="font-bold"> 一、达成率</h4>
                        <p>达成情况及预测</p>
                        <p> 区域达成情况及排名</p>
                    </div>
                    <div class="col-lg-3">
                        <h4 class="font-bold">
                            二、总投放对比情况
                        </h4>
                        <p>产品拆分</p>
                        <p>客群分析</p>
                    </div>
                    <div class="col-lg-3">
                        <h4 class="font-bold">
                            三、易购门店对比情况
                        </h4>
                        <p>营销策略分析</p>
                        <p>构成分析</p>
                        <p>营销能力分析</p>
                    </div>
                    <div class="col-lg-3">
                        <h4 class="font-bold">
                            四、易购线上对比情况
                        </h4>
                        <p>营销策略分析</p>
                        <p>构成分析</p>
                        <p>营销能力分析</p>
                    </div>
                </div>

                <div class="row">

                    <div class="col-lg-3">
                        <h4 class="font-bold">
                            五、第三方对比情况
                        </h4>
                        <p>第三方产品对比数据</p>
                    </div>
                    <div class="col-lg-3">
                        <h4 class="font-bold">
                            六、随借随还对比情况
                        </h4>
                        <p>营销策略分析</p>
                        <p>客群分析</p>
                    </div>
                    <div class="col-lg-3">
                        <h4 class="font-bold">
                            七、现金分期对比情
                        </h4>
                        <p>营销策略分析</p>
                        <p>构成分析</p>
                    </div>
                </div>
            </div>
        </div>
        <!--达成率1111111111111111111111111111111111111111111111111111111-->
        <div>
            <!-- 目录1 达成率  -->
            <div class="row  white-bg page-heading">
                <h2>一、达成率</h2>
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
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>
        </div>
        <!--总投放2222222222222222222222222222222222222222222222222222222222222222222-->
        <div>
            <!-- 目录2 总投放对比情况  -->
            <div class="row  white-bg page-heading">
                <h2>二、总投放对比情况</h2>
            </div>
            <!--产品分析-->
            <div class="ibox" id="productAnaSquareBox" >
                <div class="ibox-title">
                    <h5>产品分析</h5>
                </div>

                <div class="ibox-content">
                    <div class="sk-spinner sk-spinner-wave">
                        <div class="sk-rect1"></div>
                        <div class="sk-rect2"></div>
                        <div class="sk-rect3"></div>
                        <div class="sk-rect4"></div>
                        <div class="sk-rect5"></div>
                    </div>

                    <div class="product-container">

                        <div class="my-box ztf-position">
                            <div class="my-box-title">总投放</div>
                            <div class="my-box-content width-50">
                                <div ><p>投放金额(万元)</p><p id="LOAN_ALL">-</p></div>
                                <div><p>增长率</p><p id="LOAN_ALL_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>投放人数</p><p id="LOAN_ALL_CUST">-</p></div>
                                <div><p>增长率</p><p id="LOAN_ALL_CUST_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>客单价(元)</p><p id="LOAN_ALL_KDJ">-</p></div>
                                <div><p>增长率</p><p id="LOAN_ALL_KDJ_UP">-</p></div>
                            </div>
                        </div>

                        <!--购物贷-->
                        <div class="my-box gwd-position">
                            <div class="my-box-title">购物贷</div>
                            <div class="my-box-content width-50">
                                <div ><p>投放金额(万元)</p><p id="LOAN_BUY">-</p></div>
                                <div><p>增长率</p><p id="LOAN_BUY_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>投放人数</p><p id="LOAN_BUY_CUST">-</p></div>
                                <div><p>增长率</p><p id="LOAN_BUY_CUST_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>客单价(元)</p><p id="LOAN_BUY_KDJ">-</p></div>
                                <div><p>增长率</p><p id="LOAN_BUY_KDJ_UP">-</p></div>
                            </div>
                        </div>

                        <!--现金贷-->
                        <div class="my-box xjd-position">
                            <div class="my-box-title">现金贷</div>
                            <div class="my-box-content width-50">
                                <div ><p>投放金额(万元)</p><p id="LOAN_CASH">-</p></div>
                                <div><p>增长率</p><p id="LOAN_CASH_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>投放人数</p><p id="LOAN_CASH_CUST">-</p></div>
                                <div><p>增长率</p><p id="LOAN_CASH_CUST_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>客单价(元)</p><p id="LOAN_CASH_KDJ">-</p></div>
                                <div><p>增长率</p><p id="LOAN_CASH_KDJ_UP">-</p></div>
                            </div>
                        </div>
                        <!--易购整体-->
                        <div class="my-long-box ygzt-position">
                            <div class="my-box-title">易购整体</div>
                            <div class="my-box-content width-25">
                                <div ><p>投放金额(万元)</p><p id="AMOUT_YG">-</p></div>
                                <div ><p>增长率</p><p id="AMOUT_YG_UP">-</p></div>
                                <div ><p>渗透率</p><p id="STL">-</p></div>
                                <div ><p>增长率</p><p id="STL_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-25">
                                <div ><p>投放人数</p><p id="AMOUT_YG_CUST">-</p></div>
                                <div ><p>增长率</p><p id="AMOUT_YG_CUST_UP">-</p></div>
                                <div ><p>可达渗透率</p><p id="KDSTL">-</p></div>
                                <div ><p>增长率</p><p id="KDSTL_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-25">
                                <div ><p>客单价(元)</p><p id="AMOUT_YG_KDJ">-</p></div>
                                <div ><p>增长率</p><p id="AMOUT_YG_KDJ_UP">-</p></div>
                                <div ><p>实际/可达</p><p id="SJKD">-</p></div>
                                <div ><p>增长率</p><p id="SJKD_UP">-</p></div>
                            </div>
                        </div>

                        <!--第三方-->
                        <div class="my-box dsf-position">
                            <div class="my-box-title">第三方</div>
                            <div class="my-box-content width-50">
                                <div ><p>投放金额(万元)</p><p id="LOAN_OTHER">-</p></div>
                                <div><p>增长率</p><p id="LOAN_OTHER_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>投放人数</p><p id="LOAN_OTHER_CUST">-</p></div>
                                <div><p>增长率</p><p id="LOAN_OTHER_CUST_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>客单价(元)</p><p id="LOAN_OTHER_KDJ">-</p></div>
                                <div><p>增长率</p><p id="LOAN_OTHER_KDJ_UP">-</p></div>
                            </div>
                        </div>

                        <!--随借随还-->
                        <div class="my-box sjsh-position">
                            <div class="my-box-title">随借随还</div>
                            <div class="my-box-content width-50">
                                <div ><p>投放金额(万元)</p><p id="LOAN_ANY">-</p></div>
                                <div><p>增长率</p><p id="LOAN_ANY_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>投放人数</p><p id="LOAN_ANY_CUST">-</p></div>
                                <div><p>增长率</p><p id="LOAN_ANY_CUST_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>客单价(元)</p><p id="LOAN_ANY_KDJ">-</p></div>
                                <div><p>增长率</p><p id="LOAN_ANY_KDJ_UP">-</p></div>
                            </div>
                        </div>

                        <!--现金分期-->
                        <div class="my-box xjfq-position">
                            <div class="my-box-title">现金分期</div>
                            <div class="my-box-content width-50">
                                <div ><p>投放金额(万元)</p><p id="LOAN_INSTALL">-</p></div>
                                <div><p>增长率</p><p id="LOAN_INSTALL_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>投放人数</p><p id="LOAN_INSTALL_CUST">-</p></div>
                                <div><p>增长率</p><p id="LOAN_INSTALL_CUST_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>客单价(元)</p><p id="LOAN_INSTALL_KDJ">-</p></div>
                                <div><p>增长率</p><p id="LOAN_INSTALL_KDJ_UP">-</p></div>
                            </div>
                        </div>
                        <!--易购门店-->
                        <div class="my-box ygmd-position">
                            <div class="my-box-title">易购门店</div>
                            <div class="my-box-content width-50">
                                <div ><p>投放金额(万元)</p><p id="LOAN_SHOP">-</p></div>
                                <div><p>增长率</p><p id="LOAN_SHOP_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>投放人数</p><p id="LOAN_SHOP_CUST">-</p></div>
                                <div><p>增长率</p><p id="LOAN_SHOP_CUST_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>客单价(元)</p><p id="LOAN_SHOP_KDJ">-</p></div>
                                <div><p>增长率</p><p id="LOAN_SHOP_KDJ_UP">-</p></div>
                            </div>
                        </div>
                        <!--易购线上-->
                        <div class="my-box ygxs-position">
                            <div class="my-box-title">易购线上</div>
                            <div class="my-box-content width-50">
                                <div ><p>投放金额(万元)</p><p id="LOAN_ONLINE">-</p></div>
                                <div><p>增长率</p><p id="LOAN_ONLINE_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>投放人数</p><p id="LOAN_ONLINE_CUST">-</p></div>
                                <div><p>增长率</p><p id="LOAN_ONLINE_CUST_UP">-</p></div>
                            </div>
                            <div class="my-box-content width-50">
                                <div><p>客单价(元)</p><p id="LOAN_ONLINE_KDJ">-</p></div>
                                <div><p>增长率</p><p id="LOAN_ONLINE_KDJ_UP">-</p></div>
                            </div>
                        </div>

                        <!--第一条线-->
                        <div class="connect-line one-two">
                            <div class="connect-line-top one-two-top"></div>
                            <span class="connect-line-hen"></span>
                            <div class="triangle-down-box left-triangle">
                                <div class="shu-line"></div>
                                <div class="triangle-down"></div>
                            </div>
                            <div class="triangle-down-box right-triangle">
                                <div class="shu-line"></div>
                                <div class="triangle-down"></div>
                            </div>
                        </div>
                        <!--第二条线-->
                        <div class="connect-line two-three">
                            <div class="connect-line-top two-three-top"></div>
                            <span class="connect-line-hen"></span>
                            <div class="triangle-down-box left-triangle">
                                <div class="shu-line"></div>
                                <div class="triangle-down"></div>
                            </div>
                            <div class="triangle-down-box right-triangle">
                                <div class="shu-line"></div>
                                <div class="triangle-down"></div>
                            </div>
                        </div>
                        <!--第三条线-->
                        <div class="connect-line two-three-right">
                            <div class="connect-line-top two-three-right-top"></div>
                            <span class="connect-line-hen"></span>
                            <div class="triangle-down-box left-triangle">
                                <div class="shu-line"></div>
                                <div class="triangle-down"></div>
                            </div>
                            <div class="triangle-down-box right-triangle">
                                <div class="shu-line"></div>
                                <div class="triangle-down"></div>
                            </div>
                        </div>
                        <!--第四条线-->
                        <div class="connect-line four">
                            <div class="connect-line-top four-top"></div>
                            <span class="connect-line-hen"></span>
                            <div class="triangle-down-box left-triangle">
                                <div class="shu-line"></div>
                                <div class="triangle-down"></div>
                            </div>
                            <div class="triangle-down-box right-triangle">
                                <div class="shu-line"></div>
                                <div class="triangle-down"></div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>
            <!--客群分析-->
            <!--新客户-->
            <div class="row">
                <div class="col-lg-6">
                    <div class="ibox" id="newSquareBox">
                        <div class="ibox-title">
                            <h5>首购客户&nbsp;&nbsp;</h5>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" id="NEW_LOAN_ALL_CUST">人数-</span>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" id="NEW_LOAN_ALL_UP">增长-</span>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" id="NEW_LOAN_ALL">贡献度(万元)-</span>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" id="NEW_GXD_UP">增长-</span>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" id="NEW_GXD">贡献率-</span>
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
                                        <th>产品 </th>
                                        <th>查询期人数</th>
                                        <th>人数增长</th>
                                        <th>查询期贡献(万元)</th>
                                        <th>贡献增长</th>
                                    </tr>
                                    </thead>
                                    <tbody id="newSquareTable">
                                    <tr><td>易购线上</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                                    <tr><td>易购门店</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                                    <tr><td>第三方</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                                    <tr><td>随借随还</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                                    <tr><td>现金分期</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="ibox" id="oldSquareBox">
                        <div class="ibox-title">
                            <h5>复购客户&nbsp;&nbsp;</h5>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" id="OLD_LOAN_ALL_CUST">人数-</span>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" id="OLD_LOAN_ALL_UP">增长-</span>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" id="OLD_LOAN_ALL">贡献度(万元)-</span>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" id="OLD_GXD_UP">增长-</span>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;" id="OLD_GXD">贡献率-</span>
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
                                        <th>产品 </th>
                                        <th>查询期人数</th>
                                        <th>人数增长</th>
                                        <th>查询期贡献(万元)</th>
                                        <th>贡献增长</th>
                                    </tr>
                                    </thead>
                                    <tbody id="oldSquareTable">
                                    <tr><td>易购线上</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                                    <tr><td>易购门店</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                                    <tr><td>第三方</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                                    <tr><td>随借随还</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                                    <tr><td>现金分期</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--客户申请分析（辅助分析）-->
            <div class="ibox" id="custApplyBox" >
                <div class="ibox-title">
                    <h5>客户申请分析（辅助分析）</h5>
                </div>
                <div class="ibox-content">
                    <div class="sk-spinner sk-spinner-wave">
                        <div class="sk-rect1"></div>
                        <div class="sk-rect2"></div>
                        <div class="sk-rect3"></div>
                        <div class="sk-rect4"></div>
                        <div class="sk-rect5"></div>
                    </div>

                    <div class="product-container" style="height: 372px;margin-bottom: 20px;    max-width: 982px;">
                        <!--第三层-->
                        <div class="my-box ktcg-khs-position" >
                            <div class="my-box-title">开通成功用户</div>
                            <div class="my-box-content width-50">
                                <div ><p>查询期客户</p><p id="CN_CUST_SUCOPN">-</p></div>
                                <div ><p>增长</p><p id="CN_KTCG_ZZ">-</p></div>
                            </div>
                            <div class="my-box-content ">
                                <div class="cunliang" >
                                    <span>累计开通成功</span><lo id="CN_HAVE_CUST_SUCOPN">-</lo>万人
                                </div>
                            </div>

                        </div>
                        <!--第四层-->
                        <!--第五层-->
                        <div class="my-box ysxyh-khs-position" >
                            <div class="my-box-title">定向邀请申请开通用户</div>
                            <div class="my-box-content width-50">
                                <div ><p>查询期客户</p><p id="CN_CUST_PRECRD">-</p></div>
                                <div><p>增长率</p><p id="CN_CUST_ZZ">-</p></div>
                            </div>
                            <div class="my-box-content ">
                                <div class="cunliang" >
                                    <span>累计申请开通</span><lo id="CN_HAVE_CUST_PRECRD">-</lo>万人
                                </div>
                            </div>
                        </div>
                        <div class="my-box sqcg-khs-position" >
                            <div class="my-box-title">主动申请额度成功用户</div>
                            <div class="my-box-content width-50">
                                <div ><p>查询期客户</p><p id="CN_CUST_SUCAPY">-</p></div>
                                <div><p>增长</p><p id="CN_SQCG_ZZ">-</p></div>
                            </div>
                            <div class="my-box-content ">
                                <div class="cunliang" >
                                    <span>累计申请成功</span><lo id="CN_HAVE_CUST_SUCAPY">-</lo>万人
                                </div>
                            </div>

                        </div>
                        <!--第六层-->
                        <div class="my-box sqyh-khs-position" >
                            <div class="my-box-title">主动申请额度用户</div>
                            <div class="my-box-content width-50">
                                <div><p>查询期客户</p><p id="CN_CUST_APPLY">-</p></div>
                                <div><p>增长</p><p id="CN_SQYH_ZZ">-</p></div>
                            </div>
                            <div class="my-box-content ">
                                <div class="cunliang" >
                                    <span>累计申请</span><lo id="CN_HAVE_CUST_APPLY">-</lo>万人
                                </div>
                            </div>
                        </div>


                        <!-- 向右箭头-->
                        <div class="left-zhe-triangle line-12">
                            <div class="h-line "></div>
                            <div class="triangle-right "></div>
                        </div>
                        <!-- 半包围框-->
                        <div class="kuang-triangle line-112">
                            <div class="h-line "></div>
                            <div class="h-line line-113"></div>
                            <div class="s-line-kuang "></div>
                        </div>
                        <!-- 向右箭头-->
                        <div class="left-zhe-triangle line-114">
                            <div class="h-line "></div>
                            <div class="triangle-right "></div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>主动申请渠道 </th>
                                        <th>申请用户</th>
                                        <th>申请成功客户</th>
                                        <th>申请通过率</th>
                                        <th>开通成功人数</th>
                                        <th>使用人数</th>
                                        <th>使用率</th>
                                    </tr>
                                    </thead>
                                    <tbody id="newCustChannelTable">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-lg-6" >

                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>定向邀请客户申请渠道 </th>
                                        <th>申请开通人数</th>
                                        <th>开通成功人数</th>
                                        <th>开通成功率 </th>
                                        <th>使用人数</th>
                                        <th>使用率 </th>
                                    </tr>
                                    </thead>
                                    <tbody id="dingCustChannelTable">

                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    <div class="row">
                        <p class="text-warning">【注：主动申请用户开通成功人数会包含部分昨日申请今日开通成功的用户】</p></p>
                        <p class="text-primary">指标说明【累计用户定义为开业到查询期前的用户】</p>
                        <ul>
                            <li> 主动申请额度用户：主动申请额度用户</li>
                            <li> 主动申请额度成功客户：主动申请额度中通过的客户</li>
                            <li> 定向邀请申请开通客户：定向邀请开通客户申请开通的人数</li>
                            <li> 开通成功客户：查询期内成功开通的客户</li>
                            <li> 首购客户人数：查询期内首次使用任性付的客户</li>
                            <li> 复购用户人数：最早贷款时间在查询期之前的客户以及在查询期内有两次及以上贷款的首购用户</li>
                            <li> 首购客户贡献额：查询期内首次使用任性付的用户的首笔贷款金额</li>
                            <li> 复购客户贡献额：复购客户在查询期内的贷款金额（查询期内有两次及以上贷款的首购客户取除第一笔以外的贷款金额）</li>
                        </ul>
                    </div>


                </div>
            </div>
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>
        </div>
        <!--易购门店3333333333333333333333333333333333333333333333333333333333333333-->
        <div>
            <!-- 目录1 达成率  -->
            <div class="row  white-bg page-heading">
                <h2>三、易购门店对比情况</h2>
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
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
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
                                        <th>客单价(元)</th>
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
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>
            <!--客群构成分析-->
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
                                        <th>顾客来源	 </th>
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
                                        <th>顾客来源	 </th>
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
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
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
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>
        </div>
        <!--4444444444444444444444444444444444444444444444444-->
        <div>
            <!-- 目录4 易购线上对比情况  -->
            <div class="row  white-bg page-heading">
                <h2>四、易购线上对比情况</h2>
            </div>
            <!--营销策略分析-->
            <div class="ibox" >
                <div class="ibox-title">
                    <h5>营销策略分析【注：活动包含三零分期和购物贷电子券】</h5>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-lg-4">
                            <div id="otfChart" class="normal-chart">

                            </div>
                        </div>
                        <div class="col-lg-8">
                            <div id="omarketingChart" class="normal-chart">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>
            <!--构成分析-->
            <div class="ibox" id="onlinePdkhrsBox">
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
                                        <th>客单价(元)</th>
                                        <th>增长率</th>
                                        <th>投放人数</th>
                                        <th>增长率</th>
                                    </tr>
                                    </thead>
                                    <tbody id="easyBuyOnlineCatTabls">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-lg-6" >
                            <div id="ocircleChart"  class="normal-chart">

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>

            <!--构成分析-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>客群构成分析&nbsp;&nbsp;</h5>
                    <span class="label label-primary" style="font-size: 14px;margin-top: -3px;"></span>
                </div>
                <div class="ibox-content" style="padding-top: 20px;">
                    <div class="row">
                        <div class="col-lg-6">
                            <div id="ocustSTLChart" class="normal-chart">

                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div id="ocustGCChart" class="normal-chart">

                            </div>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 20px;">
                        <div class="col-lg-12" >
                            <div id="ocustQDChart" class="normal-chart">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--新老客户分析-->
            <div class="row">
                <div class="col-lg-6" >
                    <div class="ibox" id="onlineSgbox">
                        <div class="ibox-title">
                            <h5>首购客户&nbsp;&nbsp;</h5>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;"><span id="onlineFirstBuy">-</span></span>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;">增长<span id="onlineFirstBuyUp">-</span></span>
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
                                    <tbody id="easyBuyOnlineFirstBuyTable">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6" >
                    <div class="ibox" id="onlineFgBox">
                        <div class="ibox-title">
                            <h5>复购客户&nbsp;&nbsp;</h5>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;"><span id="onlineAgainBuy">-</span></span>
                            <span class="label label-primary" style="font-size: 14px;margin-top: -3px;">增长<span id="onlineAgainBuyUp">-</span></span>
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
                                    <tbody id="easyBuyOnlineAgainBuyTable">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>
            <!--营销能力分析-->
            <div class="ibox" id="onlineYxnltBox">
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
                            <tbody id="easyBuyCatOnlineTable">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>
        </div>
        <!--555555555555555555555555555-->
        <div>
            <!-- 目录5 第三方对比情况  -->
            <div class="row  white-bg page-heading">
                <h2>五、第三方对比情况</h2>
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
        </div>
        <!--666666666666666666666666666666666666666666-->
        <div>
            <!-- 目录6 随借随还对比情况  -->
            <div class="row  white-bg page-heading">
                <h2>六、随借随还对比情况</h2>
            </div>
            <!--营销策略分析-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>营销策略分析&nbsp;&nbsp;</h5>
                    <span class="label label-primary" style="font-size: 14px;margin-top: -3px;"></span>
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
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
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
                                <li class="success-element"  style="background:#fff">
                                    复购客户：<span id="againBuyCust">-</span>
                                    <div class="stat-percent font-bold text-info">增长<span id="againBuyCustUp">-</span>% </div>
                                </li>
                                <li class="info-element"  style="background:#fff">
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
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>
        </div>
        <!--77777777777777777777777777777777777-->
        <div>
            <!-- 目录7 现金分期对比情况  -->
            <div class="row  white-bg page-heading">
                <h2>七、现金分期对比情况</h2>
            </div>
            <!--营销策略分析-->
            <div class="ibox" >
                <div class="ibox-title">
                    <h5>营销策略分析</h5>
                </div>
                <div class="ibox-content">
                    <div id="cmarketingChart" class="normal-chart">

                    </div>
                </div>
            </div>
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>
            <!--营销策略分析-->
            <div class="ibox" id="xjfqkdfxBox">
                <div class="ibox-title">
                    <h5>构成分析-客单分析</h5>
                </div>
                <div class="ibox-content" >
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
                                <th>天数 </th>
                                <th>投放金额(万元)</th>
                                <th>对比期金额(万元)</th>
                                <th>增长率</th>

                                <th>客单价(元)</th>
                                <th>对比期客单价(元)</th>
                                <th>增长率</th>

                                <th>投放人数</th>
                                <th>对比期人数</th>
                                <th>增长率</th>
                            </tr>
                            </thead>
                            <tbody id="xjfqkdfx">
                            <tr><td>3期</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                            <tr><td>6期</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                            <tr><td>9期</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                            <tr><td>12期</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                            <tr><td>24期</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                            <tr><td>36期</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                            <tr><td>48期</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                            <tr><td>60期</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>
            <!--客群分析-->
            <div class="ibox" id="cashSgfgBox">
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
                                <li class="warning-element"  style="background:#fff">
                                    首购客户：<span id="obfirstBuyCust">-</span>
                                    <div class="stat-percent font-bold text-info">增长<span  id="obfirstBuyCustUp">-</span>% </div>
                                </li>
                                <li class="success-element"  style="background:#fff">
                                    复购客户：<span id="obagainBuyCust">-</span>
                                    <div class="stat-percent font-bold text-info">增长<span id="obagainBuyCustUp">-</span>% </div>
                                </li>
                                <li class="info-element"  style="background:#fff">
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
                                            <tr><td>查询期</td><td id="obfirstBuyCustPersent">-</td><td id="obagainBuyCustPersent">-</td></tr>
                                            <tr><td>对比期</td><td id="obfirstBuyCustPersentCompare">-</td><td id="obagainBuyCustPersentCompare">-</td></tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="col-lg-6">
                            <div id="cxlkhChart" class="normal-chart">
                                <!--新老客户对比-->
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-4">
                            <div id="crygcChart" class="normal-chart">
                                <!--人员构成-->
                            </div>
                        </div>
                        <div class="col-lg-8">
                            <div id="cfyghylyChart" class="normal-chart">
                                <!--非易购会员来源渠道-->
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!--结论-->
            <div class="ibox">
                <div class="ibox-title">
                    <h5>结论：</h5>
                </div>
                <div class="ibox-content">
                    <textarea class="form-control" ></textarea>
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
    <script src="../js/smart/myChart.js"></script>
</body>
<script>
    //初始化
    $(document).ready(function(){
        initDesc();
        rateInit(true);
        totalLendInit(true);
        easybuyStoreInit(true);
        easybuyOnlineInit(true);
        thirdPartyInit(true);
        anytimeLendPayment(true);
        cashInstallments(true);
    });
    function initDesc(){
        var storage = window.sessionStorage;
        var year = storage.getItem("year");
        var quarter = storage.getItem("quarter");
        var quarterDesc = "";
        if(quarter == 1){
            quarterDesc = "一";
        }else if(quarter == 2){
            quarterDesc = "二";
        }else if(quarter == 3){
            quarterDesc = "三";
        }else{
            quarterDesc = "四";
        }
        $("#muluDesc").html("【达成率：累计截止"+year+"年"+quarterDesc+"季度】");
        $("#compareDesc").html("【对比分析：查询期"+storage.getItem("beginDate")+"至"+storage.getItem("endDate")+" ，对比期"+storage.getItem("compareBeginDate")+"至"+storage.getItem("compareEndDate")+"】");
    }
</script>
</html>




