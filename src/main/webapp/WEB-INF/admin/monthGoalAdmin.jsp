<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>月指标维护</title>
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

    <style>
    </style>
</head>


<body style="background-color: transparent;">


<div class="wrapper my-wrapper ">
            <div class="ibox my-ibox" id="ibox">
                <div class="ibox-content">
                    <div class="sk-spinner sk-spinner-wave" >
                        <div class="sk-rect1"></div>
                        <div class="sk-rect2"></div>
                        <div class="sk-rect3"></div>
                        <div class="sk-rect4"></div>
                        <div class="sk-rect5"></div>
                    </div>

                    <div class="row">
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label class="control-label" for="">年份</label>
                                <select name="status" class="form-control" id="year">
                                    <c:forEach var="year" items="${years}">
                                        <option value="${year}" >${year}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="col-sm-2">
                            <div class="form-group">
                                <label class="control-label" for="">季度筛选：</label>
                                <select name="status" class="form-control" id="quarter">
                                    <option value=""></option>
                                    <option value="1">一季度</option>
                                    <option value="2">二季度</option>
                                    <option value="3">三季度</option>
                                    <option value="4">四季度</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label class="control-label" for="">消金大区名称</label>
                                <select name="status" class="form-control" id="areaCode">
                                    <option value=""></option>
                                    <c:forEach var="area" items="${areas}">
                                        <option value="${area.AREA_CODE}" >${area.AREA_NAME}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="col-sm-6 " >
                            <div  style="padding-top: 25px;">
                                <button type="button" class="btn btn-w-m btn-primary btn-sm" id="searchButton">查询</button>
                                <button type="button" class="btn btn-w-m btn-default btn-sm" id ="addMaster">新增总部</button>
                                <button type="button" class="btn btn-w-m btn-default btn-sm" id="addSlave">新增大区</button>
                            </div>
                        </div>
                    </div>

                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>总部</th>
                                <th>季度</th>
                                <th>总投放(万元)</th>
                                <th>购物贷(万元)</th>
                                <th>现金贷(万元)</th>
                                <th>门店购物贷(万元)</th>
                                <th>线上购物贷(万元)</th>
                                <th>第三方(万元)</th>
                                <th>随借随还(万元)</th>
                                <th>现金分期(万元)</th>
                                <th style="width: 100px">操作</th>
                            </tr>
                            </thead>
                            <tbody id="centerCompanyList">
                            </tbody>
                        </table>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>大区</th>
                                <th>季度</th>
                                <th>门店总投放(万元)</th>
                                <th>门店新客户投放(万元)</th>
                                <th>门店新客户数</th>
                                <th>现金贷总投放(万元)</th>
                                <th>自拓现金贷投放(万元)</th>
                                <th>自拓现金贷新客户数</th>
                                <th>第三方投放(万元)</th>
                                <th>第三方新客户数</th>
                                <th>APP申请人数</th>
                                <th style="width: 100px">操作</th>
                            </tr>
                            </thead>
                            <tbody id="areaCompanyList">
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="12" class="footable-visible">
                                    <div class="tcdPageCode m-pagination"></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>

                    </div>
                </div>
            </div>
</div>

<div class="modal inmodal fade" id="centerModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox " style="margin-bottom: 0;">
                            <div class="ibox-content" style="padding-bottom: 0;">
                                <form method="post" class="form-horizontal" id="centerCompanyForm" novalidate="novalidate">
                                    <input type="hidden" class="form-control" name="id" value="" id="cId">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">总部</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="areaName" id="cAreaName" value="总部"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">年份</label>
                                        <div class="col-sm-10">
                                            <select class="form-control m-b" name="yearNum" id="cYearNum">
                                                <option>2017</option>
                                                <option>2018</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">季度</label>
                                        <div class="col-sm-10">
                                            <select class="form-control m-b" name="quarterNum" id="cQuarterNum">
                                                <option value="1">一季度</option>
                                                <option value="2">二季度</option>
                                                <option value="3">三季度</option>
                                                <option value="4">四季度</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">总投放(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanAll" id="cLoanAll"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">购物贷(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanBuy" id="cLoanBuy"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">现金贷(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanCash" id="cLoanCash"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">门店购物贷(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanShop" id="cLoanShop"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">线上购物贷(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanOnline" id="cLoanOnline"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">第三方(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanOther" id="cLoanOther"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">随借随还(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanAny" id="cLoanAny"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">现金分期(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanInstall" id="cLoanInstall"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="col-sm-4 col-sm-offset-8">
                                        <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-primary"  id="centerCompanySOUButton">提交</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal inmodal fade" id="areaModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox " style="margin-bottom: 0;">
                            <div class="ibox-content" style="padding-bottom: 0;">
                                <form method="post" class="form-horizontal" id="areaCompanyForm" novalidate="novalidate">
                                    <input type="hidden" class="form-control" name="id" id="aId">
                                    <input type="hidden" class="form-control" name="areaName" id="areaName">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">大区</label>
                                        <div class="col-sm-10">
                                            <select class="form-control m-b" name="areaCode" id="aAreaCode" onchange="changeAreaName()">
                                                <c:forEach var="area" items="${areas}">
                                                    <option value="${area.AREA_CODE}" >${area.AREA_NAME}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">年份</label>
                                        <div class="col-sm-10">
                                            <select class="form-control m-b" name="yearNum" id="aYearNum">
                                                <c:forEach var="year" items="${years}">
                                                    <option value="${year}" >${year}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">季度</label>
                                        <div class="col-sm-10">
                                            <select class="form-control m-b" name="quarterNum" id="quarterNum">
                                                <option value="1">一季度</option>
                                                <option value="2">二季度</option>
                                                <option value="3">三季度</option>
                                                <option value="4">四季度</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">门店总投放(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanShop" id="loanShop"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">门店新客户投放(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanShopNewcust" id="loanShopNewcust"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">门店新客户数</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="shopNewcust" id="shopNewcust"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">现金贷总投放(万元)	</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanCash" id="loanCash"></div>
                                    </div>

                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">自拓现金贷投放(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanCashself" id="loanCashself"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">自拓现金贷新客户数(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="cashselfNewcust" id="cashselfNewcust"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">第三方投放(万元)</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="loanOther" id="loanOther"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">第三方新客户数</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="otherNewcust" id="otherNewcust"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">APP申请人数</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="appCustNo" id="appCustNo"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="col-sm-4 col-sm-offset-8">
                                        <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-primary" id="areaCompanySOUButton">提交</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
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
<script src="../js/lib/jquery.page.js"></script>
<!-- Jquery Validate -->
<script src="../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../js/systemadmin/monthGoalAdmin.js"></script>

</body>
</html>






