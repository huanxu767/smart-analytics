var validator;
var pageNum = 1;
$(document).ready(function(){
    queryRelation();
    bindEvents();
    validation();
    initDate("begindate","enddate");
});
function bindEvents(){
    $("#searchButton").click(function(){
        queryFirstPageRelation();
    });
    $("#addButton").click(function(){
        addRelation();
    });
    $("#submitButton").click(function(){
       save();
    });
}
function addRelation(){
    $("#id").val("");
    $("#relationForm")[0].reset();
    validator.resetForm();
    $('#myModal').modal();
}
function queryFirstPageRelation(){
    pageNum = 1;
    queryRelation();
}
function queryRelation(){
    $('#ibox').children('.ibox-content').toggleClass('sk-loading');
    var paramsJson ={
            pageNum:pageNum,
            strCode:$("#qStrCode").val(),
            strName:$("#qStrName").val(),
            areaCode:$("#qAreaCode").val(),
            areaName:$("#qAreaName").val()
        };
    $.ajax({
        type : "POST",
        url : "./queryRelationships",
        data:paramsJson,
        dataType:"json",
        success:function(data){
            $('#ibox').children('.ibox-content').toggleClass('sk-loading');
            if(data.success){
                //成功
                ogniseRelationTable(data.result.page);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

function ogniseRelationTable(data){
    pageControl(data.pages);
    $("#relationList").html("");
    $(data.list).each(function(index, item){
        var rowHtml = "<tr>" +
            "<td>" + item.strCode + "</td>" +
            "<td>" + item.strName + "</td>" +
            "<td>" + item.areaCode + "</td>" +
            "<td>" + item.areaName + "</td>" +
            "<td>" + formatDate(item.begindate) + "至" + formatDate(item.enddate) +"</td>" +
            "<td>" +
                "<button class='btn btn-default btn-outline btn-xs' data='"+JSON.stringify(item)+"' onclick='modify(this)' type='button'>" +
                "<i class='fa fa-edit'></i> &nbsp;修改</button>&nbsp;"+
            "</td>" +
            "</tr>";
        $("#relationList").append(rowHtml);
    });

}

function modify(obj){
    validator.resetForm();
    var item = JSON.parse($(obj).attr("data"));
    $("#id").val(item.id);
    $("#strCode").val(item.strCode);
    $("#strName").val(item.strName);
    $("#areaCode").val(item.areaCode);
    $("#areaName").val(item.areaName);
    $("#begindate").val(formatDate(item.begindate));
    $("#enddate").val(formatDate(item.enddate));
    $('#myModal').modal();
}

function save(){
    if(!$("#relationForm").valid()){
        return;
    };
    $.ajax({
        type : "POST",
        url : "./insertOrUpdateRelationship",
        data:$("#relationForm").serialize(),
        dataType:"json",
        success:function(data){
            // console.log(data);
            if(data.success){
                modalHide();
                window.parent.toastrSuccess("操作成功");
                queryFirstPageRelation();
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

function modalHide(){
    $('#myModal').modal('hide');
}
function validation(){
    validator = $("#relationForm").validate({
        rules: {
            strCode: {
                required: true
            },
            strName:{
                required: true
            },
            areaCode:{
                required: true
            },
            areaName:{
                required: true
            },
            strName:{
                required: true
            },
            begindate:{
                required: true
            },
            enddate:{
                required: true
            }
        }
    });
}

function pageControl(pageCounts) {
    $(".tcdPageCode").createPage({
        pageCount: pageCounts,
        current: pageNum,
        backFn: function (p) {
            pageNum = p;
            queryRelation();
        }
    });
};

function initDate(bid,eid) {
    var start = {
        format: 'YYYY-MM-DD'
    };
    var end = {
        format: 'YYYY-MM-DD'
    };
    $('#'+bid).jeDate(start);
    $('#'+eid).jeDate(end);
}

function formatDate(date){
    if(date){
        return date.substr(0,4) + "-" +date.substr(4,2)+ "-" +date.substr(6,2)
    }
    return "";
}


