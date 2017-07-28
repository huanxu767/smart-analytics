<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>智能分析平台</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="./css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="./css/animate.css" rel="stylesheet">
    <!-- Toastr style -->
    <link href="./css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="./css/style.css" rel="stylesheet">
    <link href="./css/plugins/ladda/ladda-themeless.min.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="./css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="./css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="./css/main.css" rel="stylesheet">

    <style>
        .main-scale{
            margin: 0 -15px;
        }
        .my-wrapper-content {
            padding: 20px 0 0 0 ;
        }
        /* Tabs */
        .my-tabs-container .panel-body {
            background: #f3f3f4;
            border: 1px solid #e7eaec;
            border-radius: 2px;
            /*padding: 20px;*/
            position: relative;
        }
        .my-tabs-container .nav-tabs > li.active > a,
        .my-tabs-container .nav-tabs > li.active > a:hover,
        .my-tabs-container .nav-tabs > li.active > a:focus {
            border: 1px solid #e7eaec;
            border-bottom-color: transparent;
            background: #18a689;
            color: #fff;
        }
        .my-tabs-container .nav-tabs > li {
            float: left;
            margin-bottom: -1px;
        }
        .my-tabs-container .tab-pane .panel-body {
            border-top: none;
        }
        .my-tabs-container .nav-tabs > li.active > a,
        .my-tabs-container .nav-tabs > li.active > a:hover,
        .my-tabs-container .nav-tabs > li.active > a:focus {
            border: 1px solid #e7eaec;
            border-bottom-color: transparent;
        }
        .my-tabs-container .nav-tabs {
            border-bottom: 1px solid #18a689;
        }
        .my-tabs-container .tab-pane .panel-body {
            border-top: none;
        }
        .my-tabs-container .tabs-left .tab-pane .panel-body,
        .my-tabs-container .tabs-right .tab-pane .panel-body {
            border-top: 1px solid #e7eaec;
        }
        .my-tabs-container .nav-tabs > li a:hover {
            background: transparent;
            border-color: transparent;
        }
        .my-tabs-container .tabs-below > .nav-tabs,
        .my-tabs-container .tabs-right > .nav-tabs,
        .my-tabs-container .tabs-left > .nav-tabs {
            border-bottom: 0;
        }
        .my-tabs-container .tabs-left .panel-body {
            position: static;
        }
        .my-tabs-container .tabs-left > .nav-tabs,
        .my-tabs-container .tabs-right > .nav-tabs {
            width: 20%;
        }
        .my-tabs-container .tabs-left .panel-body {
            width: 80%;
            margin-left: 20%;
        }
        .my-tabs-container .tabs-right .panel-body {
            width: 80%;
            margin-right: 20%;
        }
        .my-tabs-container .tab-content > .tab-pane,
        .my-tabs-container .pill-content > .pill-pane {
            display: none;
        }
        .my-tabs-container .tab-content > .active,
        .my-tabs-container .pill-content > .active {
            display: block;
        }
        .my-tabs-container .tabs-below > .nav-tabs {
            border-top: 1px solid #e7eaec;
        }
        .my-tabs-container .tabs-below > .nav-tabs > li {
            margin-top: -1px;
            margin-bottom: 0;
        }
        .my-tabs-container .tabs-below > .nav-tabs > li > a {
            -webkit-border-radius: 0 0 4px 4px;
            -moz-border-radius: 0 0 4px 4px;
            border-radius: 0 0 4px 4px;
        }
        .my-tabs-container .tabs-below > .nav-tabs > li > a:hover,
        .my-tabs-container .tabs-below > .nav-tabs > li > a:focus {
            border-top-color: #e7eaec;
            border-bottom-color: transparent;
        }
        .my-tabs-container .tabs-left > .nav-tabs > li,
        .my-tabs-container .tabs-right > .nav-tabs > li {
            float: none;
        }
        .my-tabs-container .tabs-left > .nav-tabs > li > a,
        .my-tabs-container .tabs-right > .nav-tabs > li > a {
            min-width: 74px;
            margin-right: 0;
            margin-bottom: 3px;
        }
        .my-tabs-container .tabs-left > .nav-tabs {
            float: left;
            margin-right: 19px;
        }
        .my-tabs-container .tabs-left > .nav-tabs > li > a {
            margin-right: -1px;
            -webkit-border-radius: 4px 0 0 4px;
            -moz-border-radius: 4px 0 0 4px;
            border-radius: 4px 0 0 4px;
        }
        .my-tabs-container .tabs-left > .nav-tabs .active > a,
        .my-tabs-container .tabs-left > .nav-tabs .active > a:hover,
        .my-tabs-container .tabs-left > .nav-tabs .active > a:focus {
            border-color: #e7eaec transparent #e7eaec #e7eaec;
        }
        .my-tabs-container .tabs-right > .nav-tabs {
            float: right;
            margin-left: 19px;
        }
        .my-tabs-container .tabs-right > .nav-tabs > li > a {
            margin-left: -1px;
            -webkit-border-radius: 0 4px 4px 0;
            -moz-border-radius: 0 4px 4px 0;
            border-radius: 0 4px 4px 0;
        }
        .my-tabs-container .tabs-right > .nav-tabs .active > a,
        .my-tabs-container .tabs-right > .nav-tabs .active > a:hover,
        .my-tabs-container .tabs-right > .nav-tabs .active > a:focus {
            border-color: #e7eaec #e7eaec #e7eaec transparent;
            z-index: 1;
        }
        @media (max-width: 767px) {
            .my-tabs-container .nav-tabs > li {
                float: none !important;
            }
            .my-tabs-container .nav-tabs > li.active > a {
                border-bottom: 1px solid #e7eaec !important;
                margin: 0;
            }
        }
        .my-panel-body{
            padding: 0;
            margin-right: -1px;
        }
    </style>
    <script src="./js/jquery-3.1.1.min.js"></script>
    <!-- Mainly scripts -->

    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="./js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="./js/inspinia.js"></script>
    <script src="./js/plugins/pace/pace.min.js"></script>

    <!-- iCheck -->
    <script src="./js/plugins/iCheck/icheck.min.js"></script>
    <!-- Ladda -->
    <script src="./js/plugins/ladda/spin.min.js"></script>
    <script src="./js/plugins/ladda/ladda.min.js"></script>
    <script src="./js/plugins/ladda/ladda.jquery.min.js"></script>
    <!-- Toastr script -->
    <script src="./js/plugins/toastr/toastr.min.js"></script>
    <!--Sweet alert -->
    <script src="./js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="./js/plugins/echarts/echarts.js"></script>
    <script src="./js/lib/jquery.page.js"></script>
    <!--Jquery Validate -->
    <script src="./js/plugins/validate/jquery.validate.min.js"></script>
    <script src="./js/main.js"></script>
</head>


<body class="">

<div id="wrapper">

    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="./img/headermy.png" width="40px"/>
                             </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0);">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">总部</strong>
                             </span>
                                <span class="text-muted text-xs block">&nbsp; </span>
                            </span> </a>
                        <!--<ul class="dropdown-menu animated fadeInRight m-t-xs">-->
                        <!--<li><a href="login.html">退出</a></li>-->
                        <!--</ul>-->
                    </div>
                    <div class="logo-element">
                        IN+
                    </div>
                </li>
                <li class="active">
                    <a href="#" onclick="showMenuDashBord(this)" data ='{"url":"achieve/init","id":"1000"}' >
                        <i class="fa fa fa-bar-chart-o"></i> <span class="nav-label">达成率</span>
                    </a>
                </li>

                <%--<li>--%>
                    <%--<a href="javascript:void(0)" onclick="showMenuSite(this)" data ='{"url":"achieve/init","id":"2101"}'><i class="fa fa fa-bar-chart-o"></i> <span class="nav-label">达成率</span></a>--%>
                <%--</li>--%>

                <li>
                    <a href="#"><i class="fa fa-pie-chart"></i> <span class="nav-label">对比分析 </span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="javascript:void(0)" onclick="showMenuSite(this,'son')" data ='{"url":"totalLend/init","id":"1101"}'>总投放</a></li>
                        <li><a href="javascript:void(0)" onclick="showMenuSite(this,'son')" data ='{"url":"easyBuyStore/init","id":"1102"}'>易购门店</a></li>
                        <li><a href="javascript:void(0)" onclick="showMenuSite(this,'son')" data ='{"url":"easyBuyOnline/init","id":"1103"}' >易购线上</a></li>
                        <li><a href="javascript:void(0)" onclick="showMenuSite(this,'son')" data ='{"url":"thirdParty/init","id":"1104"}'>第三方</a></li>
                        <li><a href="javascript:void(0)" onclick="showMenuSite(this,'son')" data ='{"url":"anytimePayment/init","id":"1105"}'>随借随还</a></li>
                        <li><a href="javascript:void(0)" onclick="showMenuSite(this,'son')" data ='{"url":"cashInstall/init","id":"1106"}'>现金分期</a></li>
                    </ul>
                </li>
                <c:if test="${fn:contains('10151430,10160861,10160400',empNo)}">
                    <li>
                        <a href="javascript:void(0)"><i class="fa fa fa-laptop"></i> <span class="nav-label">系统管理 </span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="javascript:void(0)" onclick="showMenuSite(this,'son')" data ='{"url":"monthGoalAdmin/init","id":"3101"}'>月指标维护</a></li>
                            <li><a href="javascript:void(0)" onclick="showMenuSite(this,'son')" data ='{"url":"relationshipAdmin/init","id":"3102"}'>苏宁公司-消金大区关系</a></li>
                            <li><a href="javascript:void(0)" onclick="showMenuSite(this,'son')" data ='{"url":"rateAdmin/init","id":"3103"}' >达成率权重配置</a></li>
                        </ul>
                    </li>
                </c:if>

            </ul>
        </div>
    </nav>

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top  white-bg" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="javascript:void(0)"><i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" action="">
                        <div class="form-group">
                        </div>
                    </form>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <span class="m-r-sm text-muted welcome-message">欢迎来到 智能分析平台</span>
                    </li>
                    <li>
                        <a href="">
                            <i class="fa "></i>
                        </a>
                    </li>
                </ul>

            </nav>
        </div>

        <!--<div class="row wrapper border-bottom white-bg my-page-heading" id="pageHeading" style="">-->
        <!--<div class="col-lg-10">-->
        <!--<ol class="breadcrumb"> <li><a href="./main">首页</a></li><li><a class="active"><strong>任务管理</strong></a></li></ol>-->
        <!--</div>-->
        <!--<div class="col-lg-2">-->
        <!--</div>-->
        <!--</div>-->
        <div class="wrapper wrapper-content main-scale my-wrapper-content" >
            <div class="">
                <div class="my-tabs-container">
                    <ul class="nav nav-tabs " id="tabHeader">
                        <li class="active m-l-md" data="1000">
                            <a data-toggle="tab" href="#1000" >
                                达成率
                            </a>
                        </li>
                    </ul>
                    <div class="tab-content" id="tabContent">
                        <div id="1000" class="tab-pane active">
                            <div class="panel-body  my-panel-body" >
                                <div class="iframe" >
                                    <iframe src="./achieve/init"  name="iframepage" frameBorder=0  width="100%" height="0"  onload="reinitIframeOnload(this);" ></iframe>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
</html>

