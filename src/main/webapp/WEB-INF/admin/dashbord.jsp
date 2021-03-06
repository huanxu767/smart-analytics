<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>dashbord</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="../css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="../css/animate.css" rel="stylesheet">
    <!-- Toastr style -->
    <link href="../css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/plugins/ladda/ladda-themeless.min.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="../css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="../css/main.css" rel="stylesheet">


</head>

<!--background-color: transparent;-->
<body style="background-color: transparent;">

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5 class="m-b-md">Server status Q12</h5>
                    <h2 class="text-navy">
                        <i class="fa fa-play fa-rotate-270"></i> Up
                    </h2>
                    <small>Last down 42 days ago</small>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content ">
                    <h5 class="m-b-md">Server status Q13</h5>
                    <h2 class="text-navy">
                        <i class="fa fa-play fa-rotate-270"></i> Up
                    </h2>
                    <small>Last down 42 days ago</small>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5 class="m-b-md">Server status Q42</h5>
                    <h2 class="text-danger">
                        <i class="fa fa-play fa-rotate-90"></i> Down
                    </h2>
                    <small>Server down since 4 days</small>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5 class="m-b-md">Server status Q43</h5>
                    <h2 class="text-danger">
                        <i class="fa fa-play fa-rotate-90"></i> Down
                    </h2>
                    <small>Server down since 4:32 pm.</small>
                </div>
            </div>
        </div>

    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5>Visits in last 24h</h5>
                    <h2>198 009</h2>
                    <div id="sparkline1"><canvas width="265" height="60" style="display: inline-block; width: 265.75px; height: 60px; vertical-align: top;"></canvas></div>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5>Visits week</h5>
                    <h2>65 000</h2>
                    <div id="sparkline2"><canvas width="265" height="60" style="display: inline-block; width: 265.75px; height: 60px; vertical-align: top;"></canvas></div>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5>Last month</h5>
                    <h2>680 900</h2>
                    <div id="sparkline3"><canvas width="265" height="60" style="display: inline-block; width: 265.75px; height: 60px; vertical-align: top;"></canvas></div>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5>Avarage time</h5>
                    <h2>00:06:40</h2>
                    <div id="sparkline4"><canvas width="265" height="60" style="display: inline-block; width: 265.75px; height: 60px; vertical-align: top;"></canvas></div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5>Usage</h5>
                    <h2>65%</h2>
                    <div class="progress progress-mini">
                        <div style="width: 68%;" class="progress-bar"></div>
                    </div>

                    <div class="m-t-sm small">Server down since 4:32 pm.</div>
                </div>
            </div>
        </div>

        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5>Usage</h5>
                    <h2>50%</h2>
                    <div class="progress progress-mini">
                        <div style="width: 78%;" class="progress-bar"></div>
                    </div>

                    <div class="m-t-sm small">Server down since 4:32 pm.</div>
                </div>
            </div>
        </div>

        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5>Usage</h5>
                    <h2>14%</h2>
                    <div class="progress progress-mini">
                        <div style="width: 38%;" class="progress-bar progress-bar-danger"></div>
                    </div>

                    <div class="m-t-sm small">Server down since 4:32 pm.</div>
                </div>
            </div>
        </div>

        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5>Usage</h5>
                    <h2>20%</h2>
                    <div class="progress progress-mini">
                        <div style="width: 28%;" class="progress-bar progress-bar-danger"></div>
                    </div>

                    <div class="m-t-sm small">Server down since 4:32 pm.</div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5>Percentage distribution</h5>
                    <h2>42/20</h2>
                    <div class="text-center">
                        <div id="sparkline5"><canvas width="140" height="140" style="display: inline-block; width: 140px; height: 140px; vertical-align: top;"></canvas></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5>Percentage division</h5>
                    <h2>100/54</h2>
                    <div class="text-center">
                        <div id="sparkline6"><canvas width="140" height="140" style="display: inline-block; width: 140px; height: 140px; vertical-align: top;"></canvas></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5>Percentage distribution</h5>
                    <h2>685/211</h2>
                    <div class="text-center">
                        <div id="sparkline7"><canvas width="140" height="140" style="display: inline-block; width: 140px; height: 140px; vertical-align: top;"></canvas></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h5>Percentage division</h5>
                    <h2>240/32</h2>
                    <div class="text-center">
                        <div id="sparkline8"><canvas width="140" height="140" style="display: inline-block; width: 140px; height: 140px; vertical-align: top;"></canvas></div>
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
<!-- Toastr script -->
<script src="../js/plugins/toastr/toastr.min.js"></script>
<!-- Sweet alert -->
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="../js/plugins/echarts/echarts.js"></script>
<script src="../js/lib/jquery.page.js"></script>
<!-- Jquery Validate -->
<script src="../js/plugins/validate/jquery.validate.min.js"></script>

</body>

</html>




