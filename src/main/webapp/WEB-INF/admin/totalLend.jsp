<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>总投放</title>
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
    <link href="../css/total-lend.css" rel="stylesheet">
    <link href="../css/smart.css" rel="stylesheet">

    <style>
        .stl{
            background: #da7b13;
            position: absolute;
            left: 19px;
            height: 40px;
            line-height: 40px;
            top: 3px;
            padding: 0 5px;
        }
    </style>
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
                        <button type="button" class="btn btn-w-m btn-primary" id="searchButton" onclick="totalLend(false)">查询</button>
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
            <p class="text-primary">A、产品分析：从各产品的角度拆分总投放金额、人数、客单等，查看影响总投放的具体产品</p>
            <p class="text-primary">B、客群分析：从新老客户的角度查看影响总体使用人数的因素</p>
        </div>
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
                    <div class="my-box-title">易购整体<a class="label label-success stl"id="showDivChart">人数渗透率</a></div>
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


    <!--客群分析-->
    <!--新客户-->
    <div class="row">
        <div class="col-lg-6">
            <div class="ibox" id="newSquareBox">
                <div class="ibox-title">
                    <h5>首购客户&nbsp;&nbsp;</h5>
                    <span class="label label-primary " style="font-size: 14px;margin-top: -3px;" id="NEW_LOAN_ALL_CUST" >人数-</span>
                    <span class="label label-primary"  style="font-size: 14px;margin-top: -3px;" id="NEW_LOAN_ALL_UP">增长-</span>
                    <span class="label label-primary"  style="font-size: 14px;margin-top: -3px;" id="NEW_LOAN_ALL">贡献度(万元)-</span>
                    <span class="label label-primary"  style="font-size: 14px;margin-top: -3px;" id="NEW_GXD_UP">增长-</span>
                    <span class="label label-primary"  style="font-size: 14px;margin-top: -3px;" id="NEW_GXD">贡献率-</span>
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

    <!---->
    <div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <div id="custGroupChart" style="width: 890px;margin: 20px 0 20px 0;">

                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- Mainly scripts -->
    <script src="../js/jquery-3.1.1.min.js"></script>
    <!--<script src="../js/jquery-2.1.1.js"></script>-->

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
        totalLendInit(false);
        $("#showDivChart").click(function(){
            $("#myModal").modal();
            window.parent.backTop();
        });
        $("#addButton").click(function(){
            window.parent.goDownPage();
        });
    });

</script>
</html>






