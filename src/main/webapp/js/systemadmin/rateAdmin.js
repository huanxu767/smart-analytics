var validator;
$(document).ready(function(){
    queryRates();
    validation();
});

function queryRates(){
    $('#ibox').children('.ibox-content').toggleClass('sk-loading');
    $.ajax({
        type : "POST",
        url : "./queryRates",
        dataType:"json",
        success:function(data){
            $('#ibox').children('.ibox-content').toggleClass('sk-loading');
            // console.log(data);
            if(data.success){
                //成功
                ogniseRateTable(data.result.list);
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

function ogniseRateTable(data){
    $("#rateList").html("");
    $(data).each(function(index, item){
        var rowHtml = "<tr>" +
            "<td>" + item.kpiName + "</td>" +
            "<td>" + item.weight + "</td>" +
            "<td>" + item.kpiDown + "%</td>" +
            "<td>" + item.kpiUp + "%</td>" +
            "<td>" +
                "<button class='btn btn-default btn-outline btn-xs' data='"+JSON.stringify(item)+"' onclick='modify(this)' type='button'>" +
                "<i class='fa fa-edit'></i> &nbsp;修改</button>&nbsp;"+
            "</td>" +
            "</tr>";
        $("#rateList").append(rowHtml);
    });

}
function modify(obj){
    validator.resetForm();
    var item = JSON.parse($(obj).attr("data"));
    $("#kpiCode").val(item.kpiCode);
    $("#kpiName").html(item.kpiName);
    $("#weight").val(item.weight);
    $("#kpiDown").val(item.kpiDown);
    $("#kpiUp").val(item.kpiUp);
    $('#myModal5').modal();
}
function save(){
    if(!$("#rateForm").valid()){
        return;
    };
    $.ajax({
        type : "POST",
        url : "./updateRate",
        data:$("#rateForm").serialize(),
        dataType:"json",
        success:function(data){
            // console.log(data);
            if(data.success){
                modalHide();
                window.parent.toastrSuccess("修改达成率指标成功");
                queryRates();
            }else{
                //失败
                window.parent.toastrError(data.errorMsg);
            }
        }
    });
}

function modalHide(){
    //成功
    $('#myModal5').modal('hide');
}
function validation(){
    validator = $("#rateForm").validate({
        rules: {
            weight: {
                required: true,
                number: true,
                max:1000,
                min:0
            },
            kpiDown:{
                required: true,
                number: true,
                max:1000,
                min:0
            },
            kpiUp:{
                required: true,
                number: true,
                max:1000,
                min:0
            },
        }
    });
}