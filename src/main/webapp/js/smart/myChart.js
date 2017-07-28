var pageNum = 1;
var cashPageNum = 1;

//达成率
function rateInit(downFlag){
    initRateChart();
    initRateTable();
}
//总投放查询
var totalLendBox = 0;
//总投放
function totalLendInit(downFlag){
    getDate(downFlag);
    totalLend(downFlag);
}
function totalLend(downFlag){
    if(!validateDate()){
        window.parent.toastrError("时间区间不能超过31天");
        return;
    };
    totalLendBox = 0;
    //产品分析
    productAnaSquare();
    productCustSquare(1);
    productCustSquare(0);
    // 客户申请分析（辅助分析）
    //新客
    custChannelTable(1);
    //老客
    custChannelTable(2);
    //图表
    custChannelChart();
    if(!downFlag){
        custSLVChart();
    }
}
//易购门店
function easybuyStoreInit(downFlag){
    getDate(downFlag);
    easyBuyStore(downFlag);
}
function easyBuyStore(downFlag){
    if(!validateDate()){
        window.parent.toastrError("时间区间不能超过31天");
        return;
    };
    queryMarketingStrategyData();
    queryDeptConstituteData();
    queryCustomPermeabilityData();
    queryPersonConstituteData();
    queryPersonNotYgChannelData();
    queryNewOrOldCustomDataByChannelData();
    queryMarketingAnalysisData();
}
//易购线上
function easybuyOnlineInit(downFlag){
    getDate(downFlag);
    easybuyOnline(downFlag);
}
function easybuyOnline(downFlag){
    if(!validateDate()){
        window.parent.toastrError("时间区间不能超过31天");
        return;
    };
    queryLoanAmtDataDataOnline();
    queryMarketingStrategyDataOnline();
    queryCustomPermeabilityDataOnline();
    queryPersonConstituteDataOnline();
    queryDeptConstituteDataOnline();
    queryPersonNotYgChannelDataOnline();
    queryMarketingAnalysisDataOnline();
    queryNewOrOldCustomDataByChannelDataOnline();
}
//第三方
function thirdPartyInit(downFlag){
    getDate(downFlag);
    thirdParty(downFlag);
}
function thirdParty(downFlag){
    if(!validateDate()){
        window.parent.toastrError("时间区间不能超过31天");
        return;
    };
    queryThirdPartyTable();
}

//随借随还
function anytimeLendPaymentInit(downFlag) {
    getDate(downFlag);
    anytimeLendPayment(downFlag);
}
function anytimeLendPayment(downFlag){
    if(!validateDate()){
        window.parent.toastrError("时间区间不能超过31天");
        return;
    };
    sjshTfChart();
    queryAnyGroupNewAndOld();
    queryAnyGroupSuningOrNot();
    queryAnyCustChannel();
    if(!downFlag){
        queryActivity();
        querySjshEdyxChart();
    }

}
//现金分期
function cashInstallmentsInit(downFlag){
    getDate(downFlag);
    cashInstallments(downFlag);
}
function cashInstallments(downFlag){
    if(!validateDate()){
        window.parent.toastrError("时间区间不能超过31天");
        return;
    };
    queryCashLoanData();
    queryCashInstallSuningOrNot();
    queryCashInstallChannel();
    queryCashGroupNewAndOld();
    queryCashCustChannel();
    if(!downFlag){
        queryCashActivity();
        queryCashEdyxChart();
    }
}

function initRateChart(){
    var areaChart = echarts.init(document.getElementById("areaChart"));
       areaChart.showLoading();
    var storage = window.sessionStorage;
    var year = storage.getItem("year");
    var quarter = storage.getItem("quarter");;
    $.ajax({
        type: "POST",
        url: "../achieve/queryRateTargetChart",
        data: {year: year, quarter: quarter},
        dataType: "json",
        success: function (data) {
            // console.log(data);
            areaChart.hideLoading();
            if(data.success){
                var option = {
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['达成率','预期达成率'],
                        align:'right',
                        right:80,
                    },
                    grid: {
                        left: '0%',
                        right: '30',
                        bottom: '0px',
                        top:'25',
                        containLabel: true
                    },
                    color:['#a3e1d4','#8ac8e6'],
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            data : data.result.names,
                            xisLabel: {
                                formatter: '{value} %'
                            }
                        }
                    ],
                    yAxis : {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value}%'
                        }
                    },

                    series : [
                        {
                            name:'达成率',
                            type:'bar',
                            data:data.result.actLvList,
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    formatter: '{c}%',
                                    textStyle:{
                                        color:'#4ccaaf'
                                        // fontWeight:600
                                    }
                                }
                            },
                            barMaxWidth:40,
                            markLine : {
                                data : [
                                    {
                                        name: 'Y 轴值为 100 的水平线',
                                        yAxis: 100
                                    }
                                ]
                            }
                        },
                        {
                            name:'预期达成率',
                            type:'line',
//                    label: {
//                        normal: {
//                            show: true,
//                            position: 'top',
//                            formatter: '{c}%'
//                        }
//                    },
                            data:data.result.targetLvList
                        }
                    ]
                };

                areaChart.setOption(option);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });

}

function initRateTable(){
    toggleBox("rateListbox");
    var storage = window.sessionStorage;
    var year = storage.getItem("year");
    var quarter = storage.getItem("quarter");;
    $.ajax({
        type: "POST",
        url: "../achieve/queryRateTargetTable",
        data: {year: year, quarter: quarter},
        dataType: "json",
        success: function (data) {
            toggleBox("rateListbox");
            $("#rateList").html("");
            if(data.success){
               $(data.result.list).each(function(index,item){
                   var tempRow = '<tr>'+
                       '<td>'+item.AREA_NAME+'</td>'+
                       '<td>'+fomatNullToSpace(item.LOAN_SHOP_RATE)+'</td>'+
                       '<td>'+fomatNullToSpace(item.LOAN_SHOP_NEWCUST_RATE)+'</td>'+
                       '<td>'+fomatNullToSpace(item.SHOP_NEWCUST_RATE)+'</td>'+
                       // '<td>'+fomatNullToSpace(item.LOAN_CASH_RATE)+'</td>'+
                       '<td>'+fomatNullToSpace(item.LOAN_CASHSELF_RATE)+'</td>'+
                       '<td>'+fomatNullToSpace(item.CASHSELF_NEWCUST_RATE)+'</td>'+

                       '<td>'+fomatNullToSpace(item.LOAN_OTHER_RATE)+'</td>'+
                       '<td>'+fomatNullToSpace(item.OTHER_NEWCUST_RATE)+'</td>'+
                       '<td>'+fomatNullToSpace(item.APP_CUST_NO_RATE)+'</td>'+
                       '<td>'+fomatNullToSpace(item.ORD)+'</td>'+

                       '</tr>';
                   $("#rateList").append(tempRow);
               });
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });

}

function initChart(chart,title,legendArray,xArray,data1,data2,axisLabel){
    if(!axisLabel){
        axisLabel = "";
    }
    var option = {
        title: {
            text: title,
            x: 'left',
            textStyle:{
                color:'#666',
                fontWeight:'normal',
                fontSize:'14'
            }
        },
        // tooltip: {
        //     trigger: 'axis',
        //     axisPointer: {
        //         type: 'shadow'
        //     },
        //     formatter: function (params) {
        //         console.log(params);
        //         // if(params[1].value == undefined){
        //         //     return  params[0].seriesName + ' : ' + params[0].value;
        //         // }else{
        //         //     return  params[0].seriesName + ' : ' + params[0].value+'<br/>'+
        //         //         params[1].seriesName + ' : ' + params[1].value+'<br/>'+
        //         //         params[2].seriesName + ' : ' + params[2].value;
        //         // }
        //     }
        // },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data:legendArray,
            align:'right',
            right:80,
        },
        grid: {
            left: '0%',
            right: '20',
            bottom: '0px',
            top:'25',
            containLabel: true
        },
        color:['#a3e1d4','#8ac8e6'],
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : xArray
            }
        ],
        yAxis : {
            type: 'value',
            axisLabel: {
                formatter: '{value}'+axisLabel
            }
        },
        series : [
            {
                name:legendArray[0],
                type:'bar',
                data:data1,
                barMaxWidth:40
            },
            {
                name:legendArray[1],
                type:'bar',
                barMaxWidth:40,
                data:data2
            }
        ]
    };
    chart.setOption(option);
    chart.hideLoading();
}

function initCircleChart(chart,legendArray,data1,data2){
    var sjson = [];
    var cjson = [];
    $(legendArray).each(function(index,item){
        var sTemp={};
        var cTemp={};
        sTemp["name"] = item;
        sTemp["value"] = data1[index];
        sjson[index] = sTemp;

        cTemp["name"] = item;
        cTemp["value"] = data2[index];
        cjson[index] = cTemp;

    });
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: '10',
            y: 'middle',
            data: legendArray
        },
        grid: {
            top: '12%',
            left: 150,
            containLabel: true
        },
        series: [
            {
                name: '查询期',
                type: 'pie',
                radius: ['65%', '80%'],
                label: {
                    normal: {
                        show: true,
                        position: 'outside',
                        formatter: '{d}%',
                        textStyle: {
                            color: '#666'
                        }

                    }
                },
                labelLine: {
                    normal: {
                        show: true
                    }
                },
                data: sjson
            }, {
                name: '对比期',
                type: 'pie',
                radius: ['40%', '55%'],
                label: {
                    normal: {
                        show: true,
                        position: 'outside',
                        formatter: '{d}%',
                        textStyle: {
                            color: '#666'
                        }

                    }
                },
                labelLine: {
                    normal: {
                        show: true
                    }
                },
                data: cjson
            }
        ]
    }
    chart.setOption(option);
    chart.hideLoading();
}

function initChartWithLine(chart,title,legendArray,xArray,data1,data2,data3,axisLabel){
    if(!axisLabel){
        axisLabel = "";
    }
    var option = {
        title: {
            text: title,
            x: 'left',
            textStyle:{
                color:'#666',
                fontWeight:'normal',
                fontSize:'14'
            }
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:legendArray,
            align:'right',
            right:80,
        },
        grid: {
            left: '0%',
            right: '20',
            bottom: '0px',
            top:'25',
            containLabel: true
        },
        color:['#a3e1d4','#8ac8e6'],
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : xArray,
                xisLabel: {
                    formatter: '{value} %'
                }
            }
        ],
        yAxis : [
            {
                type: 'value',
                name: "",
//                    min: 0,
//                    max: 250,
//                    interval: 50,
                axisLabel: {
                    formatter: '{value} '+axisLabel
                }
            },
            {
                type: 'value',
                name: '',
                axisLabel: {
                    formatter: '{value} %'
                }
            }
        ],
        series : [
            {
                name:legendArray[0],
                type:'bar',
                data:data1,
                barMaxWidth:40
            },
            {
                name:legendArray[1],
                type:'bar',
                barMaxWidth:40,
                data:data2
            },
            {
                name:legendArray[2],
                type:'line',
                yAxisIndex: 1,
                data:data3
            }
        ]
    };
    chart.setOption(option);
    chart.hideLoading();
}

function initDate(bid,eid,beginDate,endDate) {
    var start = {
        format: 'YYYY-MM-DD',
        isClear:false,
        maxDate: $.nowDate({DD:-1}), //最大日期
        choosefun:function(elem,date) {
            var id = elem.attr("id");
            var storage = window.sessionStorage;
            if("cBeginTime" == id){
                storage.setItem("beginDate",date);
            }
            if("dBeginTime" == id){
                storage.setItem("compareBeginDate",date);
            }
        },
        okfun:function(elem,date) {
            var id = elem.attr("id");
            var storage = window.sessionStorage;
            if("cBeginTime" == id){
                storage.setItem("beginDate",date);
            }
            if("dBeginTime" == id){
                storage.setItem("compareBeginDate",date);
            }
        }
    };

    var end = {
        format: 'YYYY-MM-DD',
        isClear:false,
        maxDate:  $.nowDate({DD:-1}), //最大日期
        choosefun:function(elem,date) {
            var id = elem.attr("id");
            var storage = window.sessionStorage;
            if("cEndTime" == id){
                storage.setItem("endDate",date);
            }
            if("dEndTime" == id){
                storage.setItem("compareEndDate",date);
            }
        },
        okfun:function(elem,date) {
            var id = elem.attr("id");
            var storage = window.sessionStorage;
            if("cEndTime" == id){
                storage.setItem("endDate",date);
            }
            if("dEndTime" == id){
                storage.setItem("compareEndDate",date);
            }
        }
    };
    $('#'+bid).val(beginDate);
    $('#'+eid).val(endDate);
    $('#'+bid).jeDate(start);
    $('#'+eid).jeDate(end);
}

function getTimeParams(){
    var beginDate,endDate,compareBeginDate,compareEndDate;
    if($("#cBeginTime").val() != undefined){
        beginDate = $("#cBeginTime").val().replace(/-/g,"");
        endDate = $("#cEndTime").val().replace(/-/g,"");
        compareBeginDate = $("#dBeginTime").val().replace(/-/g,"");
        compareEndDate = $("#dEndTime").val().replace(/-/g,"");
    }else{
        var storage = window.sessionStorage;
        beginDate = storage.getItem("beginDate").replace(/-/g,"");
        endDate = storage.getItem("endDate").replace(/-/g,"");
        compareBeginDate = storage.getItem("compareBeginDate").replace(/-/g,"");
        compareEndDate = storage.getItem("compareEndDate").replace(/-/g,"");
    }
    return {beginDate: beginDate, endDate: endDate,compareBeginDate: compareBeginDate, compareEndDate: compareEndDate};
}
//第三方-产品明细
function queryThirdPartyTable(){
    toggleBox("thirdPartyBox");
    var params = getTimeParams();
    $.ajax({
        type: "POST",
        url: "../thirdParty/queryThirdPartyTable",
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(data);
            toggleBox("thirdPartyBox");
            if(data.success){
                $("#thirdPartyTable").html("");
                $(data.result.list).each(function(index,item){
                    var tempRow = '<tr>'+
                        '<td>'+item.PRODID_OTHER+'</td>'+
                        '<td>'+fomatNullToSpace(item.TFJE)+'</td>'+
                        '<td>'+fomatNullToSpace(item.COMPTFJE)+'</td>'+
                        '<td>'+fomatNullToSpace(item.TFZZL)+'%</td>'+
                        '<td>'+fomatNullToSpace(item.TFRS)+'</td>'+
                        '<td>'+fomatNullToSpace(item.COMPTFRS)+'</td>'+
                        '<td>'+fomatNullToSpace(item.RSZZL)+'%</td>'+
                        '<td>'+fomatNullToSpace(item.KDJ)+'</td>'+
                        '<td>'+fomatNullToSpace(item.COMPKDJ)+'</td>'+
                        '<td>'+fomatNullToSpace(item.KDJZZL)+'%</td>'+'</tr>';
                    $("#thirdPartyTable").append(tempRow);
                });
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//总投放 end
function productAnaSquare(){
    $('#productAnaSquareBox').children('.ibox-content').toggleClass('sk-loading');
    $.ajax({
        type: "POST",
        url: "../totalLend/queryTotalLendTFChart",
        data: getTimeParams(),
        dataType: "json",
        success: function (data) {
            // console.log(data);
            $('#productAnaSquareBox').children('.ibox-content').toggleClass('sk-loading');
            if(data.success){
                var item = data.result;
                //总投放
                $("#LOAN_ALL").html(item.LOAN_ALL);
                $("#LOAN_ALL_UP").html(item.LOAN_ALL_UP+"%");
                $("#LOAN_ALL_CUST").html(item.LOAN_ALL_CUST);
                $("#LOAN_ALL_CUST_UP").html(item.LOAN_ALL_CUST_UP+"%");
                $("#LOAN_ALL_KDJ").html(item.LOAN_ALL_KDJ);
                $("#LOAN_ALL_KDJ_UP").html(item.LOAN_ALL_KDJ_UP+"%");
                //购物贷

                $("#LOAN_BUY").html(item.LOAN_BUY);
                $("#LOAN_BUY_UP").html(item.LOAN_BUY_UP+"%");
                $("#LOAN_BUY_CUST").html(item.LOAN_BUY_CUST);
                $("#LOAN_BUY_CUST_UP").html(item.LOAN_BUY_CUST_UP+"%");
                $("#LOAN_BUY_KDJ").html(item.LOAN_BUY_KDJ);
                $("#LOAN_BUY_KDJ_UP").html(item.LOAN_BUY_KDJ_UP+"%");

                //易购整体
                $("#AMOUT_YG").html(item.AMOUT_YG);
                $("#AMOUT_YG_UP").html(item.AMOUT_YG_UP+"%");
                $("#AMOUT_YG_CUST").html(item.AMOUT_YG_CUST);
                $("#AMOUT_YG_CUST_UP").html(item.AMOUT_YG_CUST_UP+"%");
                $("#AMOUT_YG_KDJ").html(item.AMOUT_YG_KDJ);
                $("#AMOUT_YG_KDJ_UP").html(item.AMOUT_YG_KDJ_UP+"%");

                $("#STL").html(item.STL+"%");
                $("#STL_UP").html(item.STL_UP+"%");
                $("#KDSTL").html(item.KDSTL+"%");
                $("#KDSTL_UP").html(item.KDSTL_UP+"%");

                $("#SJKD").html(item.SJKD+"%");
                $("#SJKD_UP").html(item.SJKD_UP+"%");

                //现金贷
                $("#LOAN_CASH").html(item.LOAN_CASH);
                $("#LOAN_CASH_UP").html(item.LOAN_CASH_UP+"%");
                $("#LOAN_CASH_CUST").html(item.LOAN_CASH_CUST);
                $("#LOAN_CASH_CUST_UP").html(item.LOAN_CASH_CUST_UP+"%");
                $("#LOAN_CASH_KDJ").html(item.LOAN_CASH_KDJ);
                $("#LOAN_CASH_KDJ_UP").html(item.LOAN_CASH_KDJ_UP+"%");
                //第三方
                $("#LOAN_OTHER").html(item.LOAN_OTHER);
                $("#LOAN_OTHER_UP").html(item.LOAN_OTHER_UP+"%");
                $("#LOAN_OTHER_CUST").html(item.LOAN_OTHER_CUST);
                $("#LOAN_OTHER_CUST_UP").html(item.LOAN_OTHER_CUST_UP+"%");
                $("#LOAN_OTHER_KDJ").html(item.LOAN_OTHER_KDJ);
                $("#LOAN_OTHER_KDJ_UP").html(item.LOAN_OTHER_KDJ_UP+"%");
                //随借随还
                $("#LOAN_ANY").html(item.LOAN_ANY);
                $("#LOAN_ANY_UP").html(item.LOAN_ANY_UP+"%");
                $("#LOAN_ANY_CUST").html(item.LOAN_ANY_CUST);
                $("#LOAN_ANY_CUST_UP").html(item.LOAN_ANY_CUST_UP+"%");
                $("#LOAN_ANY_KDJ").html(item.LOAN_ANY_KDJ);
                $("#LOAN_ANY_KDJ_UP").html(item.LOAN_ANY_KDJ_UP+"%");
                //现金分期
                $("#LOAN_INSTALL").html(item.LOAN_INSTALL);
                $("#LOAN_INSTALL_UP").html(item.LOAN_INSTALL_UP+"%");
                $("#LOAN_INSTALL_CUST").html(item.LOAN_INSTALL_CUST);
                $("#LOAN_INSTALL_CUST_UP").html(item.LOAN_INSTALL_CUST_UP+"%");
                $("#LOAN_INSTALL_KDJ").html(item.LOAN_INSTALL_KDJ);
                $("#LOAN_INSTALL_KDJ_UP").html(item.LOAN_INSTALL_KDJ_UP+"%");
                //易购门店
                $("#LOAN_SHOP").html(item.LOAN_SHOP);
                $("#LOAN_SHOP_UP").html(item.LOAN_SHOP_UP+"%");
                $("#LOAN_SHOP_CUST").html(item.LOAN_SHOP_CUST);
                $("#LOAN_SHOP_CUST_UP").html(item.LOAN_SHOP_CUST_UP+"%");
                $("#LOAN_SHOP_KDJ").html(item.LOAN_SHOP_KDJ);
                $("#LOAN_SHOP_KDJ_UP").html(item.LOAN_SHOP_KDJ_UP+"%");
                //易购线上
                $("#LOAN_ONLINE").html(item.LOAN_ONLINE);
                $("#LOAN_ONLINE_UP").html(item.LOAN_ONLINE_UP+"%");
                $("#LOAN_ONLINE_CUST").html(item.LOAN_ONLINE_CUST);
                $("#LOAN_ONLINE_CUST_UP").html(item.LOAN_ONLINE_CUST_UP+"%");
                $("#LOAN_ONLINE_KDJ").html(item.LOAN_ONLINE_KDJ);
                $("#LOAN_ONLINE_KDJ_UP").html(item.LOAN_ONLINE_KDJ_UP+"%");
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

function productCustSquare(flag){
    if(flag == 1){
        $('#newSquareBox').children('.ibox-content').toggleClass('sk-loading');
    }else{
        $('#oldSquareBox').children('.ibox-content').toggleClass('sk-loading');
    }
    var params = getTimeParams();
    params["isNewFlag"] = flag;
    $.ajax({
        type: "POST",
        url: "../totalLend/queryTotalLendTBChart",
        data: params,
        dataType: "json",
        success: function (data) {
            if(flag == 1){
                $('#newSquareBox').children('.ibox-content').toggleClass('sk-loading');
            }else{
                $('#oldSquareBox').children('.ibox-content').toggleClass('sk-loading');
            }
            if(data.success){
                var item = data.result;
                if(flag == 1){
                    $("#NEW_LOAN_ALL_CUST").html("人数"+item.LOAN_ALL_CUST);
                    $("#NEW_LOAN_ALL_UP").html("增长"+item.LOAN_ALL_CUST_UP+"%");
                    $("#NEW_LOAN_ALL").html("贡献度"+item.LOAN_ALL+"万元");
                    $("#NEW_GXD_UP").html("增长"+item.LOAN_ALL_UP+"%");
                    $("#NEW_GXD").html("贡献率"+item.GXL+"%");

                    var tempHtml = ' <tr><td>易购线上</td><td>'+item.LOAN_ONLINE_CUST+'</td><td>'+item.LOAN_ONLINE_CUST_UP+'%</td><td>'+item.LOAN_ONLINE+'</td><td>'+item.LOAN_ONLINE_UP+'%</td></tr>'+
                        '<tr><td>易购门店</td><td>'+item.LOAN_SHOP_CUST+'</td><td>'+item.LOAN_SHOP_CUST_UP+'%</td><td>'+item.LOAN_SHOP+'</td><td>'+item.LOAN_SHOP_UP+'%</td></tr>'+
                        '<tr><td>第三方</td><td>'+item.LOAN_OTHER_CUST+'</td><td>'+item.LOAN_OTHER_CUST_UP+'%</td><td>'+item.LOAN_OTHER+'</td><td>'+item.LOAN_OTHER_UP+'%</td></tr>'+
                        '<tr><td>随借随还</td><td>'+item.LOAN_ANY_CUST+'</td><td>'+item.LOAN_ANY_CUST_UP+'%</td><td>'+item.LOAN_ANY+'</td><td>'+item.LOAN_ANY_UP+'%</td></tr>'+
                        '<tr><td>现金分期</td><td>'+item.LOAN_INSTALL_CUST+'</td><td>'+item.LOAN_INSTALL_CUST_UP+'%</td><td>'+item.LOAN_INSTALL+'</td><td>'+item.LOAN_INSTALL_UP+'%</td></tr>';
                    $("#newSquareTable").html(tempHtml);
                }else{
                    $("#OLD_LOAN_ALL_CUST").html("人数"+item.LOAN_ALL_CUST);
                    $("#OLD_LOAN_ALL_UP").html("增长"+item.LOAN_ALL_CUST_UP+"%");
                    $("#OLD_LOAN_ALL").html("贡献度"+item.LOAN_ALL+"万元");
                    $("#OLD_GXD_UP").html("增长"+item.LOAN_ALL_UP+"%");
                    $("#OLD_GXD").html("贡献率"+item.GXL+"%");
                    var tempHtml = ' <tr><td>易购线上</td><td>'+item.LOAN_ONLINE_CUST+'</td><td>'+item.LOAN_ONLINE_CUST_UP+'%</td><td>'+item.LOAN_ONLINE+'</td><td>'+item.LOAN_ONLINE_UP+'%</td></tr>'+
                        '<tr><td>易购门店</td><td>'+item.LOAN_SHOP_CUST+'</td><td>'+item.LOAN_SHOP_CUST_UP+'%</td><td>'+item.LOAN_SHOP+'</td><td>'+item.LOAN_SHOP_UP+'%</td></tr>'+
                        '<tr><td>第三方</td><td>'+item.LOAN_OTHER_CUST+'</td><td>'+item.LOAN_OTHER_CUST_UP+'%</td><td>'+item.LOAN_OTHER+'</td><td>'+item.LOAN_OTHER_UP+'%</td></tr>'+
                        '<tr><td>随借随还</td><td>'+item.LOAN_ANY_CUST+'</td><td>'+item.LOAN_ANY_CUST_UP+'%</td><td>'+item.LOAN_ANY+'</td><td>'+item.LOAN_ANY_UP+'%</td></tr>'+
                        '<tr><td>现金分期</td><td>'+item.LOAN_INSTALL_CUST+'</td><td>'+item.LOAN_INSTALL_CUST_UP+'%</td><td>'+item.LOAN_INSTALL+'</td><td>'+item.LOAN_INSTALL_UP+'%</td></tr>';
                    $("#oldSquareTable").html(tempHtml);
                }


            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

function custChannelTable(flag){
    var params = getTimeParams();
    params["flag"] = flag;
    $.ajax({
        type: "POST",
        url: "../totalLend/queryCustApplyTable",
        data: params,
        dataType: "json",
        success: function (data) {
            totalLendBox++;
            if(totalLendBox>= 3){
                toggleBox("custApplyBox");
            }
            if(data.success){
                if(flag == 1) {
                    $("#newCustChannelTable").html("");
                }else {
                    $("#dingCustChannelTable").html("");
                }
                $(data.result.list).each(function(index,item) {
                    if(flag == 1){
                        var tempRow = '<tr>' +
                            '<td>' + item.CUST_CHNL + '</td>' +
                            '<td>' + fomatNullToSpace(item.CUST_APPLY_TOTAL) + '</td>' +
                            '<td>' + fomatNullToSpace(item.CUST_SUCAPY_TOTAL) + '</td>' +
                            '<td>' + fomatNullToSpace(item.SQTGL) + '%</td>' +
                            '<td>' + fomatNullToSpace(item.CUST_SUCOPN_TOTAL) + '</td>' +
                            '<td>' + fomatNullToSpace(item.CUST_USE_TOTAL) + '</td>' +
                            '<td>' + fomatNullToSpace(item.SYL) + '%</td>' +
                            '</tr>';
                        $("#newCustChannelTable").append(tempRow);
                    }else {
                        var tempRow = '<tr>' +
                            '<td>' + item.CUST_CHNL + '</td>' +
                            '<td>' + fomatNullToSpace(item.CUST_OPEN_TOTAL) + '</td>' +
                            '<td>' + fomatNullToSpace(item.CUST_SUCOPN_TOTAL) + '</td>' +
                            '<td>' + fomatNullToSpace(item.KTCGL) + '%</td>' +
                            '<td>' + fomatNullToSpace(item.CUST_USE_TOTAL) + '</td>' +
                            '<td>' + fomatNullToSpace(item.SYL) + '%</td>' +
                            '</tr>';
                        $("#dingCustChannelTable").append(tempRow);
                    }
                });
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

function custChannelChart(){
    var params = getTimeParams();
    toggleBox("custApplyBox");
    $.ajax({
        type: "POST",
        url: "../totalLend/queryCustApplyChart",
        data: params,
        dataType: "json",
        success: function (data) {

            totalLendBox++;
            if(totalLendBox>= 3){
                toggleBox("custApplyBox");
            }
            if(data.success){
                var item = data.result;
                console.log(item);
                $("#CN_KTCG_ZZ").html(item.KTCG_ZZ+"%");
                // $("#CN_KTCG_KTL").html(item.KTCG_KTL+"%");

                $("#CN_CUST_SUCOPN").html(item.CUST_SUCOPN);
                $("#CN_HAVE_CUST_SUCOPN").html(item.HAVE_CUST_SUCOPN);

                // $("#CN_SQKT_ZZ").html(item.SQKT_ZZ+"%");
                // $("#CN_CUST_OPEN").html(item.CUST_OPEN);
                // $("#CN_HAVE_CUST_OPEN").html(item.HAVE_CUST_OPEN);


                $("#CN_CUST_ZZ").html(item.SQKT_ZZ+"%");
                $("#CN_CUST_PRECRD").html(item.CUST_OPEN);
                $("#CN_HAVE_CUST_PRECRD").html(item.HAVE_CUST_OPEN);


                $("#CN_SQCG_ZZ").html(item.SQCG_ZZ+"%");
                // $("#CN_SQ_KTL").html(item.SQ_KTL +"%");
                $("#CN_CUST_SUCAPY").html(item.CUST_SUCAPY);
                $("#CN_HAVE_CUST_SUCAPY").html(item.HAVE_CUST_SUCAPY);


                $("#CN_SQYH_ZZ").html(item.SQYH_ZZ +"%");
                $("#CN_HAVE_CUST_APPLY").html(item.HAVE_CUST_APPLY);
                $("#CN_CUST_APPLY").html(item.CUST_APPLY);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

function custSLVChart(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("custGroupChart"));
    chart.showLoading();
    $.ajax({
        type: "POST",
        url: "../totalLend/queryCustStlChart",
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(data);
            if(data.success){
                initChart(chart,"客户渗透率",['查询期','对比期'], ['人数实际渗透率','人数可达渗透率','实际/可达'],data.result.searchList,data.result.oldList,"%");
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//总投放 end

//随借随还begin
function sjshTfChart(){
    var params = getTimeParams();
    var tfjeChart = echarts.init(document.getElementById("tfjeChart"));
    tfjeChart.showLoading();
    var tfrsChart = echarts.init(document.getElementById("tfrsChart"));
    tfrsChart.showLoading();
    $.ajax({
        type: "POST",
        url: "../anytimePayment/querySjshTfChart",
        data: params,
        dataType: "json",
        success: function (data) {
            if(data.success){
                var result = data.result;
                initChartWithLine(tfjeChart,"投放金额对比",['查询期','对比期','增长率'],['常规','活动'],result.loanList,result.compareLoanList,result.compareUpList,"万元");
                initChartWithLine(tfrsChart,"投放人数对比",['查询期','对比期','增长率'],['常规','活动'],result.peopleLoanList,result.peopleCompareLoanList,result.peopleCompareUpList);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//额度影响
function querySjshEdyxChart(){
    var params = getTimeParams();
    var gedsyrsChart = echarts.init(document.getElementById("gedsyrsChart"));
    gedsyrsChart.showLoading();
    var gedrjtfChart = echarts.init(document.getElementById("gedrjtfChart"));
    gedrjtfChart.showLoading();
    var gedsylChart = echarts.init(document.getElementById("gedsylChart"));
    gedsylChart.showLoading();
    $.ajax({
        type: "POST",
        url: "../anytimePayment/querySjshEdyxChart",
        data: params,
        dataType: "json",
        success: function (data) {
            if(data.success){
                var result = data.result;
                initChartWithLine(gedsyrsChart,"各额度使用人数对比",['查询期','对比期','增长率'],result.names,result.searchCustList,result.compareCustList,result.custUpList);
                initChartWithLine(gedrjtfChart,"各额度人均投放金额对比",['查询期','对比期','增长率'],result.names,result.searchCustAvgList,result.compareCustAvgList,result.custUpAvgList,"元");
                initChart(gedsylChart,"各额度用户使用率分析",['查询期','对比期'],result.names,result.searchCustSylList,result.compareCustSylList,"%");
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//营销活动明细表格
function queryActivity(){
    toggleBox("sjshActivityBox");
    var params = getTimeParams();
    params["pageNum"]=pageNum;
    $.ajax({
        type : "POST",
        url : "../anytimePayment/queryTicketActivityName",
        data:params,
        dataType:"json",
        success:function(data){
            toggleBox("sjshActivityBox");
            if(data.success){
                //成功
                ogniseActivityTable(data.result.page);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//营销活动列表
function ogniseActivityTable(data){
    pageControl(data.pages);
    $("#activtyList").html("");
    $(data.list).each(function(index, item){
        var rowHtml = "<tr>" +
            "<td>" + item.TICKET_ACTIVITY_NAME + "</td>" +
            "<td>" + item.TICKET_NUM + "</td>" +
            "<td>" + item.CONSUME_AMOUNT + "</td>" +
            "<td>" + item.RECEIVABLE_POUNDAGE + "</td>" +
            "<td>" + item.TICKET_AMOUNT + "</td>" +
            "</tr>";
        $("#activtyList").append(rowHtml);
    });
}
//分页控制
function pageControl(pageCounts) {
    $("#sjshActivityPage").createPage({
        pageCount: pageCounts,
        current: pageNum,
        backFn: function (p) {
            pageNum = p;
            queryActivity();
        }
    });
};
//首购复购客户表格 图表
function queryAnyGroupNewAndOld(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("xlkhChart"));
    chart.showLoading();
    toggleBox("anyKqfxBox");
    $.ajax({
        type: "POST",
        url: "../anytimePayment/queryAnyGroupNewAndOld",
        data: params,
        dataType: "json",
        success: function (data) {
            toggleBox("anyKqfxBox");
            if(data.success){
                var result = data.result;
                $("#firstBuyCust").html(result.firstBuyCust);
                $("#againBuyCust").html(result.againBuyCust);
                $("#firstBuyCustUp").html(result.firstBuyCustUp);
                $("#againBuyCustUp").html(result.againBuyCustUp);
                $("#firstBuyCustPersent").html(result.firstBuyCustPersent+"%");
                $("#againBuyCustPersent").html(result.againBuyCustPersent+"%");
                $("#firstBuyCustPersentCompare").html(result.firstBuyCustPersentCompare+"%");
                $("#againBuyCustPersentCompare").html(result.againBuyCustPersentCompare+"%");
                initChartWithLine(chart,"首购复购客户对比",['查询期','对比期','增长率'],['首购客户','复购客户'],result.searchCustList,result.compareCustList,result.custUpList);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

//随借随还 苏宁员工非苏宁员工 图表
function queryAnyGroupSuningOrNot(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("rygcChart"));
    chart.showLoading();
    $.ajax({
        type: "POST",
        url: "../anytimePayment/queryAnyGroupSuningOrNot",
        data: params,
        dataType: "json",
        success: function (data) {
            if(data.success){
                var result = data.result;
                initChartWithLine(chart,"人员构成",['查询期','对比期','增长率'],['易购员工','非易购员工'],result.searchCustList,result.compareCustList,result.custUpList);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//随借随还  非苏宁员工来源渠道图表
function queryAnyCustChannel(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("fyghylyChart"));
    chart.showLoading();
    $.ajax({
        type: "POST",
        url: "../anytimePayment/queryAnyCustChannel",
        data: params,
        dataType: "json",
        success: function (data) {
            if(data.success){
                var result = data.result;
                initChartWithLine(chart,"非易购会员来源渠道",['查询期','对比期','增长率'],result.names,result.searchVal,result.compareVal,result.up);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//随借随还end


//现金分期begin

//营销侧率分析 图表
function queryCashLoanData(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("cmarketingChart"));
    chart.showLoading();
    $.ajax({
        type: "POST",
        url: "../cashInstall/queryCashLoanData",
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(data);
            if(data.success){
                var result = data.result;
                initChart(chart,"现金分期",['常规投放增长','活动投放增长'], result.names,result.normals,result.activities,"%");
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

//额度影响
function queryCashEdyxChart(){
    var params = getTimeParams();
    var cgedsyrsChart = echarts.init(document.getElementById("cgedsyrsChart"));
    cgedsyrsChart.showLoading();
    var cgedrjtfChart = echarts.init(document.getElementById("cgedrjtfChart"));
    cgedrjtfChart.showLoading();
    var cgedsylChart = echarts.init(document.getElementById("cgedsylChart"));
    cgedsylChart.showLoading();
    $.ajax({
        type: "POST",
        url: "../cashInstall/queryCashEdyxChart",
        data: params,
        dataType: "json",
        success: function (data) {
            if(data.success){
                var result = data.result;
                // console.log(result);
                initChartWithLine(cgedsyrsChart,"各额度使用人数对比",['查询期','对比期','增长率'],result.names,result.searchCustList,result.compareCustList,result.custUpList);
                initChartWithLine(cgedrjtfChart,"各额度人均投放金额对比",['查询期','对比期','增长率'],result.names,result.searchCustAvgList,result.compareCustAvgList,result.custUpAvgList,"元");
                initChart(cgedsylChart,"各额度用户使用率分析",['查询期','对比期'],result.names,result.searchCustSylList,result.compareCustSylList,"%");
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//营销活动明细表格
function queryCashActivity(){
    toggleBox("cashActivityBox");
    var params = getTimeParams();
    params["pageNum"]=cashPageNum;
    $.ajax({
        type : "POST",
        url : "../cashInstall/queryTicketActivityName",
        data:params,
        dataType:"json",
        success:function(data){
            toggleBox("cashActivityBox");
            if(data.success){
                //成功
                ogniseCashActivityTable(data.result.page);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//营销活动列表
function ogniseCashActivityTable(data){
    cashPageControl(data.pages);
    $("#cashActivtyList").html("");
    $(data.list).each(function(index, item){
        var rowHtml = "<tr>" +
            "<td>" + item.TICKET_ACTIVITY_NAME + "</td>" +
            "<td>" + item.TICKET_NUM + "</td>" +
            "<td>" + item.CONSUME_AMOUNT + "</td>" +
            "<td>" + item.RECEIVABLE_POUNDAGE + "</td>" +
            "<td>" + item.TICKET_AMOUNT + "</td>" +
            "</tr>";
        $("#cashActivtyList").append(rowHtml);
    });
}
function cashPageControl(pageCounts) {
    $("#cashActivityPage").createPage({
        pageCount: pageCounts,
        current: cashPageNum,
        backFn: function (p) {
            cashPageNum = p;
            queryCashActivity();
        }
    });
};
//现金分期-客群分析 非苏宁员工来源渠道
function queryCashInstallChannel(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("cfyghylyChart"));
    chart.showLoading();
    $.ajax({
        type: "POST",
        url: "../cashInstall/queryCashInstallChannel",
        data: params,
        dataType: "json",
        success: function (data) {
            if(data.success){
                var result = data.result;
                initChartWithLine(chart,"非易购会员来源渠道",['查询期','对比期','增长率'],result.names,result.searchVal,result.compareVal,result.up);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

//随借随还 苏宁员工非苏宁员工 图表
function queryCashInstallSuningOrNot(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("crygcChart"));
    chart.showLoading();
    $.ajax({
        type: "POST",
        url: "../cashInstall/queryCashInstallSuningOrNot",
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(data);
            if(data.success){
                var result = data.result;
                initChartWithLine(chart,"人员构成",['查询期','对比期','增长率'],['易购员工','非易购员工'],result.searchCustList,result.compareCustList,result.custUpList);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

//首购复购客户表格 图表
function queryCashGroupNewAndOld(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("cxlkhChart"));
    chart.showLoading();
    toggleBox("cashSgfgBox");
    $.ajax({
        type: "POST",
        url: "../cashInstall/queryCashGroupNewAndOld",
        data: params,
        dataType: "json",
        success: function (data) {
            toggleBox("cashSgfgBox");
            if(data.success){
                var result = data.result;
                $("#obfirstBuyCust").html(result.firstBuyCust);
                $("#obagainBuyCust").html(result.againBuyCust);
                $("#obfirstBuyCustUp").html(result.firstBuyCustUp);
                $("#obagainBuyCustUp").html(result.againBuyCustUp);
                $("#obfirstBuyCustPersent").html(result.firstBuyCustPersent+"%");
                $("#obagainBuyCustPersent").html(result.againBuyCustPersent+"%");
                $("#obfirstBuyCustPersentCompare").html(result.firstBuyCustPersentCompare+"%");
                $("#obagainBuyCustPersentCompare").html(result.againBuyCustPersentCompare+"%");
                initChartWithLine(chart,"首购复购客户对比",['查询期','对比期','增长率'],['首购客户','复购客户'],result.searchCustList,result.compareCustList,result.custUpList);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

//客单分析
function queryCashCustChannel(){
    toggleBox("xjfqkdfxBox");
    var params = getTimeParams();
    $.ajax({
        type: "POST",
        url: "../cashInstall/queryCashCustChannel",
        data: params,
        dataType: "json",
        success: function (data) {
            toggleBox("xjfqkdfxBox");
            if(data.success){
                $("#xjfqkdfx").html("");
                $(data.result.list).each(function(index,item){
                    var tempRow = '<tr>'+
                        '<td>'+item.cterm+'</td>'+
                        '<td>'+fomatNullToSpace(item.tfje)+'</td>'+
                        '<td>'+fomatNullToSpace(item.comtfje)+'</td>'+
                        '<td>'+fomatNullToSpace(item.tfup)+'%</td>'+

                        '<td>'+fomatNullToSpace(item.kdj)+'</td>'+
                        '<td>'+fomatNullToSpace(item.comtkdj)+'</td>'+
                        '<td>'+fomatNullToSpace(item.kdjup)+'%</td>'+

                        '<td>'+fomatNullToSpace(item.tfjers)+'</td>'+
                        '<td>'+fomatNullToSpace(item.comtfjers)+'</td>'+
                        '<td>'+fomatNullToSpace(item.tfrsup)+'%</td>'+'</tr>';
                    $("#xjfqkdfx").append(tempRow);
                });
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//现金分期end

//易购门店---------------

//营销策略分析
function queryMarketingStrategyData(){
    var chart = echarts.init(document.getElementById("marketingChart"));
    chart.showLoading();
    var params = getTimeParams();
    $.ajax({
        type: "POST",
        url: "../easyBuyStore/queryMarketingStrategyData",
        data: params,
        dataType: "json",
        success: function (data) {
            if(data.success){
                var result = data.result;
                initChart(chart,"营销策略分析",['常规投放增长','活动投放增长'], result.names,result.normals,result.activities,"%");
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

// 构成分析 表格 图表
function queryDeptConstituteData(){
    toggleBox("plkdrsBox");
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("circleChart"));
    chart.showLoading();

    $.ajax({
        type: "POST",
        url: "../easyBuyStore/queryDeptConstituteData",
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(data);
            toggleBox("plkdrsBox");
            if(data.success){
                var result = data.result;
                $("#easyBuyCatTabls").html("");
                $(result.tabDatas).each(function(index,item){
                    var tempRow = '<tr>'+
                        '<td>'+item.DEPT_NM+'</td>'+
                        '<td>'+fomatNullToSpace(item.LOAN_AMT)+'</td>'+
                        '<td>'+fomatNullToSpace(item.AMT_GROWTH_RATE)+'</td>'+
                        '<td>'+fomatNullToSpace(item.PER_TICKET_SALE)+'</td>'+
                        '<td>'+fomatNullToSpace(item.PTS_GROWTH_RATE)+'</td>'+
                        '<td>'+fomatNullToSpace(item.LOAN_CNT)+'</td>'+
                        '<td>'+fomatNullToSpace(item.CNT_GROWTH_RATE)+'</td>'+'</tr>';
                    $("#easyBuyCatTabls").append(tempRow);
                });
                initCircleChart(chart,result.names,result.querys,result.compares);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

//营销策略分析 图表
function queryCustomPermeabilityData(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("custSTLChart"));
    chart.showLoading();
    $.ajax({
        type: "POST",
        url: "../easyBuyStore/queryCustomPermeabilityData",
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(data);
            if(data.success){
                var result = data.result;
                initChart(chart,"客户渗透率",['查询期','对比期'], result.names,result.querys,result.compares,"%");
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//人员构成 图表
function queryPersonConstituteData(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("custGCChart"));
    chart.showLoading();

    $.ajax({
        type: "POST",
        url: "../easyBuyStore/queryPersonConstituteData",
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(data);
            if(data.success){
                var result = data.result;
                initChartWithLine(chart,"人员构成",['查询期','对比期','增长率'], result.names,result.querys,result.compares,result.increaseRate);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//非易购员工来源渠道 图表
function queryPersonNotYgChannelData(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("custQDChart"));
    chart.showLoading();
    $.ajax({
        type: "POST",
        url: "../easyBuyStore/queryPersonNotYgChannelData",
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(data);
            if(data.success){
                var result = data.result;
                initChartWithLine(chart,"非易购员工来源渠道",['查询期','对比期','增长率'],result.names,result.querys,result.compares,result.increaseRate);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//首购复购客户
function queryNewOrOldCustomDataByChannelData(){
    var params = getTimeParams();
    toggleBox("easySgkhBox");
    toggleBox("easyFgBox");
    $.ajax({
        type: "POST",
        url: "../easyBuyStore/queryNewOrOldCustomDataByChannelData",
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(data);
            toggleBox("easySgkhBox");
            toggleBox("easyFgBox");
            if(data.success){
                var result = data.result;
                $("#ebFirstBuy").html(result.newCustomCnt);
                $("#ebFirstBuyUp").html(result.newCustomIncreaseRate);
                $("#ebAgainBuy").html(result.oldCustomCnt);
                $("#ebAgainBuyUp").html(result.oldCustomIncreaseRate);
                $("#easyBuyFirstBuyTable").html("");
                $("#easyBuyAgainBuyTable").html("");
                $(result.newCustomDatas).each(function(index, item){
                    var rowHtml = "<tr>" +
                        "<td>" + item.CUST_CHNL + "</td>" +
                        "<td>" + item.QUERY_DATA + "</td>" +
                        "<td>" + item.INCREATE_RATE + "</td>" +
                        "</tr>";
                    $("#easyBuyFirstBuyTable").append(rowHtml);
                });
                $(result.oldCustomDatas).each(function(index, item){
                    var rowHtml = "<tr>" +
                        "<td>" + item.CUST_CHNL + "</td>" +
                        "<td>" + item.QUERY_DATA + "</td>" +
                        "<td>" + item.INCREATE_RATE + "</td>" +
                        "</tr>";
                    $("#easyBuyAgainBuyTable").append(rowHtml);
                });
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

//营销能力分析
function queryMarketingAnalysisData(){
    var params = getTimeParams();
    toggleBox("easyYxnlbox");
    $.ajax({
        type: "POST",
        url: "../easyBuyStore/queryMarketingAnalysisData",
        data: params,
        dataType: "json",
        success: function (data) {
            toggleBox("easyYxnlbox");
            if(data.success){
                var result = data.result;
                $("#easyBuyCatTable").html("");
                console.log(result.list)
                $(result.list).each(function(index, item){
                    var rowHtml = "<tr>" +
                        "<td>" + item.DEPT_NM + "</td>" +

                        "<td>" + item.QUERY_BAL_AMT + "</td>" +
                        "<td>" + item.COMPARE_BAL_AMT + "</td>" +
                        "<td>" + item.INCREATE_RATE + "</td>" +

                        "<td>" + item.QUERY_ACTUAL_SEEP_RATE + "</td>" +
                        "<td>" + item.COMPARE_ACTUAL_SEEP_RATE + "</td>" +
                        "<td>" + item.ACTUAL_SEEP_INCREATE_RATE + "</td>" +

                        "<td>" + item.QUERY_ACCESS_SEEP_RATE + "</td>" +
                        "<td>" + item.COMPARE_ACCESS_SEEP_RATE + "</td>" +
                        "<td>" + item.ACCESS_SEEP_INCREATE_RATE + "</td>" +
                        "<td>" + item.QUERY_ACTUAL_ACCESS + "</td>" +
                        "<td>" + item.COMPARE_ACTUAL_ACCESS + "</td>" +
                        "<td>" + item.ACTUAL_ACCESS_INCREATE_RATE + "</td>" +
                        "</tr>";
                    $("#easyBuyCatTable").append(rowHtml);
                });
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

//易购线上---------------------
//营销能力分析
function queryMarketingAnalysisDataOnline(){
    var params = getTimeParams();
    toggleBox("onlineYxnltBox");
    $.ajax({
        type: "POST",
        url: "../easyBuyOnline/queryMarketingAnalysisData",
        data: params,
        dataType: "json",
        success: function (data) {
            toggleBox("onlineYxnltBox");
            if(data.success){
                var result = data.result;
                $("#easyBuyCatOnlineTable").html("");
                $(result.list).each(function(index, item){
                    var rowHtml = "<tr>" +
                        "<td>" + item.DEPT_NM + "</td>" +

                        "<td>" + item.QUERY_BAL_AMT + "</td>" +
                        "<td>" + item.COMPARE_BAL_AMT + "</td>" +
                        "<td>" + item.INCREATE_RATE + "</td>" +

                        "<td>" + item.QUERY_ACTUAL_SEEP_RATE + "</td>" +
                        "<td>" + item.COMPARE_ACTUAL_SEEP_RATE + "</td>" +
                        "<td>" + item.ACTUAL_SEEP_INCREATE_RATE + "</td>" +

                        "<td>" + item.QUERY_ACCESS_SEEP_RATE + "</td>" +
                        "<td>" + item.COMPARE_ACCESS_SEEP_RATE + "</td>" +
                        "<td>" + item.ACCESS_SEEP_INCREATE_RATE + "</td>" +
                        "<td>" + item.QUERY_ACTUAL_ACCESS + "</td>" +
                        "<td>" + item.COMPARE_ACTUAL_ACCESS + "</td>" +
                        "<td>" + item.ACTUAL_ACCESS_INCREATE_RATE + "</td>" +
                        "</tr>";
                    $("#easyBuyCatOnlineTable").append(rowHtml);
                });
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//营销策略分析 投放金额 图表
function queryLoanAmtDataDataOnline(){
    var chart = echarts.init(document.getElementById("otfChart"));
    chart.showLoading();
    var params = getTimeParams();
    $.ajax({
        type: "POST",
        url: "../easyBuyOnline/queryLoanAmtData",
        data: params,
        dataType: "json",
        success: function (data) {
            if(data.success){
                var result = data.result;
                initChart(chart,"投放金额",['查询期','对比期'], result.names,result.querys,result.compares,"万元");
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//营销策略分析 投放金额 图表
function queryMarketingStrategyDataOnline(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("omarketingChart"));
    chart.showLoading();
    $.ajax({
        type: "POST",
        url: "../easyBuyOnline/queryMarketingStrategyData",
        data: params,
        dataType: "json",
        success: function (data) {
            if(data.success){
                var result = data.result;
                initChart(chart,"营销策略分析",['常规投放增长','活动投放增长'], result.names,result.normals,result.activities,"%");
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

// 构成分析 表格 图表
function queryDeptConstituteDataOnline(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("ocircleChart"));
    chart.showLoading();
    toggleBox("onlinePdkhrsBox");
    $.ajax({
        type: "POST",
        url: "../easyBuyOnline/queryDeptConstituteData",
        data: params,
        dataType: "json",
        success: function (data) {
            toggleBox("onlinePdkhrsBox");
            if(data.success){
                var result = data.result;
                $("#easyBuyOnlineCatTabls").html("");
                $(result.tabDatas).each(function(index,item){
                    var tempRow = '<tr>'+
                        '<td>'+item.DEPT_NM+'</td>'+
                        '<td>'+fomatNullToSpace(item.LOAN_AMT)+'</td>'+
                        '<td>'+fomatNullToSpace(item.AMT_GROWTH_RATE)+'</td>'+
                        '<td>'+fomatNullToSpace(item.PER_TICKET_SALE)+'</td>'+
                        '<td>'+fomatNullToSpace(item.PTS_GROWTH_RATE)+'</td>'+
                        '<td>'+fomatNullToSpace(item.LOAN_CNT)+'</td>'+
                        '<td>'+fomatNullToSpace(item.CNT_GROWTH_RATE)+'</td>'+'</tr>';
                    $("#easyBuyOnlineCatTabls").append(tempRow);
                });
                initCircleChart(chart,result.names,result.querys,result.compares);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

//客户渗透率 图表
function queryCustomPermeabilityDataOnline(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("ocustSTLChart"));
    chart.showLoading();
    $.ajax({
        type: "POST",
        url: "../easyBuyOnline/queryCustomPermeabilityData",
        data: params,
        dataType: "json",
        success: function (data) {
            if(data.success){
                var result = data.result;
                initChart(chart,"客户渗透率",['查询期','对比期'],result.names,result.querys,result.compares,"%");
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//非易购员工来源渠道数据 图表
function queryPersonNotYgChannelDataOnline(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("ocustQDChart"));
    chart.showLoading();
    $.ajax({
        type: "POST",
        url: "../easyBuyOnline/queryPersonNotYgChannelData",
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(data);
            if(data.success){
                var result = data.result;
                initChartWithLine(chart,"非易购员工来源渠道",['查询期','对比期','增长率'],result.names,result.querys,result.compares,result.increaseRate);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//查询人员构成数据
function queryPersonConstituteDataOnline(){
    var params = getTimeParams();
    var chart = echarts.init(document.getElementById("ocustGCChart"));
    chart.showLoading();
    $.ajax({
        type: "POST",
        url: "../easyBuyOnline/queryPersonConstituteData",
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(data);
            if(data.success){
                var result = data.result;
                initChartWithLine(chart,"人员构成",['查询期','对比期','增长率'],result.names,result.querys,result.compares,result.increaseRate);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//首购复购客户
function queryNewOrOldCustomDataByChannelDataOnline(){
    var params = getTimeParams();
    toggleBox("onlineFgBox");
    toggleBox("onlineSgbox");
    $.ajax({
        type: "POST",
        url: "../easyBuyOnline/queryNewOrOldCustomDataByChannelData",
        data: params,
        dataType: "json",
        success: function (data) {
            toggleBox("onlineFgBox");
            toggleBox("onlineSgbox");
            if(data.success){
                var result = data.result;
                $("#onlineFirstBuy").html(result.newCustomCnt);
                $("#onlineFirstBuyUp").html(result.newCustomIncreaseRate);
                $("#onlineAgainBuy").html(result.oldCustomCnt);
                $("#onlineAgainBuyUp").html(result.oldCustomIncreaseRate);
                $("#easyBuyOnlineFirstBuyTable").html("");
                $("#easyBuyOnlineAgainBuyTable").html("");
                $(result.newCustomDatas).each(function(index, item){
                    var rowHtml = "<tr>" +
                        "<td>" + item.CUST_CHNL + "</td>" +
                        "<td>" + item.QUERY_DATA + "</td>" +
                        "<td>" + item.INCREATE_RATE + "</td>" +
                        "</tr>";
                    $("#easyBuyOnlineFirstBuyTable").append(rowHtml);
                });
                $(result.oldCustomDatas).each(function(index, item){
                    var rowHtml = "<tr>" +
                        "<td>" + item.CUST_CHNL + "</td>" +
                        "<td>" + item.QUERY_DATA + "</td>" +
                        "<td>" + item.INCREATE_RATE + "</td>" +
                        "</tr>";
                    $("#easyBuyOnlineAgainBuyTable").append(rowHtml);
                });
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}


function fomatNullToSpace(data){
    if(data){
        return data;
    }else{
        return "-";
    }
}

//日期处理
function getDays(beginTime,endTime){
    var strSeparator = "-"; //日期分隔符
    beginTime = beginTime.split(strSeparator);
    endTime = endTime.split(strSeparator);
    var beginDate = new Date(beginTime[0], beginTime[1]-1, beginTime[2]);
    var endDate = new Date(endTime[0], endTime[1]-1, endTime[2]);
    var days = parseInt((endDate - beginDate ) / 1000 / 60 / 60 /24);
    if(days>=0 && days <= 31){
        return true;
    }else {
        return false;
    }
}
//验证日期
function validateDate(){
    var storage = window.sessionStorage;
    var bFlag = getDays(storage.getItem("beginDate"),storage.getItem("endDate"));
    var cFlag = getDays(storage.getItem("compareBeginDate"),storage.getItem("compareEndDate"));
    if(bFlag && cFlag){
        return true;
    }else{
        return false;
    }
}

function getDate(downFlag){
    var storage = window.sessionStorage;
    if(!downFlag){
        initDate("cBeginTime","cEndTime",storage.getItem("beginDate"),storage.getItem("endDate"));
        initDate("dBeginTime","dEndTime",storage.getItem("compareBeginDate"),storage.getItem("compareEndDate"));
    }
}

function toggleBox(id){
    $('#'+id).children('.ibox-content').toggleClass('sk-loading');
}

