var validator;
var areaValidator;
var pageNum = 1;
$(document).ready(function(){
    queryCenterCompanies();
    queryAreaCompanies();
    validation();
    bindEvents();
});
function bindEvents(){
    //搜索
    $("#searchButton").click(function(){
        pageNum = 1;
        queryCenterCompanies();
        queryAreaCompanies();
    });

    //总部新增或修改
    $("#centerCompanySOUButton").click(function(){
        centerCompanySOU();
    });
    //大区新增或修改
    $("#areaCompanySOUButton").click(function(){
        areaCompanySOU();
    });
    //新增总部
    $("#addMaster").click(function(){
        $("#cId").val("");
        $("#centerCompanyForm")[0].reset();
        validator.resetForm();
        $('#centerModal').modal();
    });
    //新增大区
    $("#addSlave").click(function(){
        $("#aId").val("");
        $("#areaCompanyForm")[0].reset();
        $('#areaModal').modal();
        changeAreaName();
    });
}
//总部列表
function queryCenterCompanies(){
    $('#ibox').children('.ibox-content').toggleClass('sk-loading');
    var paramsJson ={
        yearNum:$("#year").val(),
        quarterNum:$("#quarter").val()
    };
    $.ajax({
        type : "POST",
        url : "./queryCenterCompanies",
        data:paramsJson,
        dataType:"json",
        success:function(data){
            $('#ibox').children('.ibox-content').toggleClass('sk-loading');
            if(data.success){
                //成功
                ogniseCenterCompanyTable(data.result);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//总部列表组装table
function ogniseCenterCompanyTable(data){
    $("#centerCompanyList").html("");
    $(data.list).each(function(index, item){
        var rowHtml = "<tr>" +
            "<td>" + item.areaName + "</td>" +
            "<td>" + item.yearNum  + formatQuarter(item.quarterNum) +"</td>" +
            "<td>" + item.loanAll + "</td>" +
            "<td>" + item.loanBuy + "</td>" +
            "<td>" + item.loanCash + "</td>" +
            "<td>" + item.loanShop + "</td>" +
            "<td>" + item.loanOnline + "</td>" +
            "<td>" + item.loanOther + "</td>" +
            "<td>" + item.loanAny + "</td>" +
            "<td>" + item.loanInstall + "</td>" +
            "<td>" +
            "<button class='btn btn-default btn-outline btn-xs' data='"+JSON.stringify(item)+"' onclick='modifyMaster(this)' type='button'>" +
            "<i class='fa fa-edit'></i> &nbsp;修改</button>&nbsp;"+
            "</td>" +
            "</tr>";
        $("#centerCompanyList").append(rowHtml);
    });

}
//总部新增或更新
function centerCompanySOU(){
    if(!$("#centerCompanyForm").valid()){
        return;
    };
    console.log($("#centerCompanyForm").serialize());
    $.ajax({
        type : "POST",
        url : "./addOrUpdateCenterCompany",
        data:$("#centerCompanyForm").serialize(),
        dataType:"json",
        success:function(data){
            // console.log(data);
            if(data.success){
                $('#centerModal').modal('hide');
                window.parent.toastrSuccess("操作成功");
                queryCenterCompanies();
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//总部更新初始化
function modifyMaster(obj){
    var item = JSON.parse($(obj).attr("data"));
    $("#cId").val(item.id);
    $("#cYearNum").val(item.yearNum);
    $("#cQuarterNum").val(item.quarterNum);
    $("#cLoanAll").val(item.loanAll);
    $("#cLoanBuy").val(item.loanBuy);
    $("#cLoanCash").val(item.loanCash);
    $("#cLoanShop").val(item.loanShop);
    $("#cLoanOnline").val(item.loanOnline);
    $("#cLoanOther").val(item.loanOther);
    $("#cLoanAny").val(item.loanAny);
    $("#cLoanInstall").val(item.loanInstall);
    $('#centerModal').modal();
}
//总部、大区验证
function validation(){
    validator = $("#centerCompanyForm").validate({
        rules: {
            loanAll: {
                required: true,
                number: true
            },
            loanBuy:{
                required: true,
                number: true
            },
            loanCash:{
                required: true,
                number: true
            },
            loanShop:{
                required: true,
                number: true
            },
            loanOnline:{
                required: true,
                number: true
            },
            loanOther:{
                required: true,
                number: true
            },
            loanAny:{
                required: true,
                number: true
            },
            loanInstall:{
                required: true,
                number: true
            }
        }
    });

    areaValidator = $("#areaCompanyForm").validate({
        rules: {
            loanShop: {
                required: true,
                number: true
            },
            loanShopNewcust:{
                required: true,
                number: true
            },
            shopNewcust:{
                required: true,
                number: true
            },
            loanCashself:{
                required: true,
                number: true
            },
            cashselfNewcust:{
                required: true,
                number: true
            },
            loanOther:{
                required: true,
                number: true
            },
            otherNewcust:{
                required: true,
                number: true
            },
            appCustNo:{
                required: true,
                number: true
            }
        }
    });
}
//格式化季度
function formatQuarter(data){
    var temp;
    switch(data){
        case 1:
            temp = "一季度";
            break;
        case 2:
            temp =  "二季度";
            break;
        case 3:
            temp =  "三季度";
            break;
        case 4:
            temp =  "四季度";
            break;
        default :
            temp =  "";
    }
    return temp;
}

//大区列表
function queryAreaCompanies(){
    $('#ibox').children('.ibox-content').toggleClass('sk-loading');
    var paramsJson ={
        yearNum:$("#year").val(),
        quarterNum:$("#quarter").val(),
        areaCode:$("#areaCode").val(),
        pageNum:pageNum
    };
    $.ajax({
        type : "POST",
        url : "./queryAreaCompanies",
        data:paramsJson,
        dataType:"json",
        success:function(data){
            console.log(data);
            $('#ibox').children('.ibox-content').toggleClass('sk-loading');
            if(data.success){
                //成功
                ogniseAreaCompanyTable(data.result.page);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}
//公司列表
function ogniseAreaCompanyTable(data){
    pageControl(data.pages);
    $("#areaCompanyList").html("");
    $(data.list).each(function(index, item){
        var rowHtml = "<tr>" +
            "<td>" + item.areaName + "</td>" +
            "<td>" + item.yearNum  + formatQuarter(item.quarterNum) +"</td>" +
            "<td>" + item.loanShop + "</td>" +
            "<td>" + item.loanShopNewcust + "</td>" +
            "<td>" + item.shopNewcust + "</td>" +
            "<td>" + item.loanCash + "</td>" +
            "<td>" + item.loanCashself + "</td>" +
            "<td>" + item.cashselfNewcust + "</td>" +
            "<td>" + item.loanOther + "</td>" +
            "<td>" + item.otherNewcust + "</td>" +
            "<td>" + item.appCustNo + "</td>" +
            "<td>" +
            "<button class='btn btn-default btn-outline btn-xs' data='"+JSON.stringify(item)+"' onclick='modifyArea(this)' type='button'>" +
            "<i class='fa fa-edit'></i> &nbsp;修改</button>&nbsp;"+
            "</td>" +
            "</tr>";
        $("#areaCompanyList").append(rowHtml);
    });
}
//分页控制
function pageControl(pageCounts) {
    $(".tcdPageCode").createPage({
        pageCount: pageCounts,
        current: pageNum,
        backFn: function (p) {
            pageNum = p;
            queryAreaCompanies();
        }
    });
};
//大区更新初始化
function modifyArea(obj){
    var item = JSON.parse($(obj).attr("data"));
    $("#aId").val(item.id);
    $("#areaName").val(item.areaName);
    $("#aAreaCode").val(item.areaCode);
    $("#aYearNum").val(item.yearNum);
    $("#quarterNum").val(item.quarterNum);
    $("#loanShop").val(item.loanShop);
    $("#loanShopNewcust").val(item.loanShopNewcust);
    $("#shopNewcust").val(item.shopNewcust);
    $("#loanCash").val(item.loanCash);
    $("#loanCashself").val(item.loanCashself);
    $("#cashselfNewcust").val(item.cashselfNewcust);
    $("#loanOther").val(item.loanOther);
    $("#otherNewcust").val(item.otherNewcust);
    $("#appCustNo").val(item.appCustNo);
    $('#areaModal').modal();
}
function areaCompanySOU(){
    if(!$("#areaCompanyForm").valid()){
        return;
    };
    $.ajax({
        type : "POST",
        url : "./addOrUpdateAreaCompany",
        data:$("#areaCompanyForm").serialize(),
        dataType:"json",
        success:function(data){
            // console.log(data);
            if(data.success){
                $('#areaModal').modal('hide');
                window.parent.toastrSuccess("操作成功");
                queryAreaCompanies();
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

function changeAreaName(){
    $("#areaName").val($("#aAreaCode").find("option:selected").text());
}