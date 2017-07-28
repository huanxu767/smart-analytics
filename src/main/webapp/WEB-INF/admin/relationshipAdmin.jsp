<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>苏宁公司-消金大区关系</title>
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
    <link href="../css/plugins/jedate/jedate.css" rel="stylesheet">
    <link href="../css/main.css" rel="stylesheet">
    <link href="../css/smart.css" rel="stylesheet">
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
                                <label class="control-label" >苏宁公司编号</label>
                                <input type="text"  id="qStrCode" value=""  class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-2">
                            <div class="form-group">
                                <label class="control-label" >苏宁公司名称</label>
                                <input type="text"  id="qStrName" value=""  class="form-control">
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label class="control-label" >消金大区编号</label>
                                <input type="text"  id="qAreaCode" value=""  class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-2">
                            <div class="form-group">
                                <label class="control-label" >消金大区名称</label>
                                <input type="text"  id="qAreaName" value=""  class="form-control">
                            </div>
                        </div>
                        <div class="col-sm-4 " >
                            <div  style="padding-top: 25px;">
                                <button type="button" class="btn btn-w-m btn-primary btn-sm" id="searchButton">查询</button>
                                <button type="button" class="btn btn-w-m btn-default btn-sm" id="addButton">新增</button>
                            </div>
                        </div>
                    </div>

                    <div class="table-responsive">
                        <table class="table table-striped" >
                            <thead>
                            <tr>
                                <th>苏宁公司编号</th>
                                <th>苏宁公司名称</th>
                                <th>消金公司编号</th>
                                <th>消金公司名称</th>
                                <th>生效时间</th>
                                <th style="width: 100px">操作</th>
                            </tr>
                            </thead>
                            <tbody id="relationList">
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="8" class="footable-visible">
                                        <div class="tcdPageCode m-pagination"></div>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>

                    </div>

                </div>
    </div>
</div>



<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox " style="margin-bottom: 0;">
                            <!--<div class="ibox-title">-->
                            <!--<h5>修改权重</h5>-->
                            <!--</div>-->
                            <div class="ibox-content" style="padding-bottom: 0;">
                                <form method="post" class="form-horizontal" id="relationForm" novalidate="novalidate">
                                    <input type="hidden" class="form-control"  name="id" id="id">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">苏宁公司编号</label>
                                        <div class="col-sm-10"><input type="text" class="form-control"  name="strCode" id="strCode"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">苏宁公司名称</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="strName" id="strName"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">消金公司编号</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="areaCode" id="areaCode"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">消金公司名称</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="areaName" id="areaName"></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">生效开始时间</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="begindate" id="begindate" readonly></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">生效结束时间</label>
                                        <div class="col-sm-10"><input type="text" class="form-control" name="enddate" id="enddate" readonly></div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    <div class="col-sm-4 col-sm-offset-8">
                                        <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-primary" id="submitButton">提交</button>
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
<script src="../js/plugins/jedate/jquery.jedate.js"></script>
<script src="../js/systemadmin/relationshipAdmin.js"></script>

</body>
</html>






