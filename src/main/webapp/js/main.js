Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        // console.log(this[i]  + " " +val);
        if (this[i] == val) {
            return i;
        }
    }
    return -1;
};
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
//打开的标签ID,不包含首页
var openedIds = new Array();
function showMenuDashBord(obj){
    var data = $(obj).attr("data");
    // console.log(JSON.parse(data));
    data = JSON.parse(data);
    var id = $("#tabHeader .active").attr("data");
    if(id == data.id){
        //如果当前展示的是本身 则 不处理
        return;
    }
    //如果当前展示的不是本身 则 切换
    cancelOrignalActive();
    activeTab(0);
    changeMenuActive(obj);
}
function showMenuSite(obj,type){
    var data = $(obj).attr("data");
    // console.log(JSON.parse(data));
    data = JSON.parse(data);
    //取出所有已打开的id
    var index = openedIds.indexOf(data.id);
    if(index == -1 ){
        //取消原active
        cancelOrignalActive();
        //如果不存在 则 创建新tab
        var title = $(obj).text();
        var tabHeaderTemplate = '<li class="active" data="'+data.id+'"><a data-toggle="tab" href="#'+data.id+'">'+title+'</a> <i class="fa fa-times tab_close" onclick="closeTab(this)" data="'+data.id+'"></i> </li>';
        var tabBodyTemplate = '<div id="'+data.id+'" class="tab-pane active"><div class="panel-body my-panel-body"> <div class="iframe" >'+
            '<iframe src="'+data.url+'"  name="iframepage" frameBorder=0  width="100%" height="0"  onload="reinitIframeOnload(this);"   ></iframe>'+
            '</div></div></div>';
        $("#tabHeader").append(tabHeaderTemplate);
        $("#tabContent").append(tabBodyTemplate);
        openedIds.push(data.id);
        // console.log("openedIds="+openedIds);
        changeMenuActive(obj,type);
    }else{
        // console.log("已存在");
        //如果已存在 则 显示相应的tab
        var id = $("#tabHeader .active").attr("data");
        if(id == data.id){
            //如果当前展示的是本身 则 不处理
            return;
        }
        //如果当前展示的不是本身 则 切换
        cancelOrignalActive();
        activeTab(index+1);
        changeMenuActive(obj,type);
    }

}
function changeMenuActive(obj,type){
    $("#side-menu .active").each(function(){
        if($(this).children('ul').html() == undefined){
            $(this).removeClass("active");
        }
    });
    if(type){
        $(obj).parent().parent().parent().addClass("active");
    }else{
        $(obj).parent().addClass("active");
    }
}
//移除原本激活的标签
function cancelOrignalActive(){
    $("#tabHeader .active").removeClass("active");
    $("#tabContent .active").removeClass("active");
}
//关闭标签
function closeTabDetai(index){
    $("#tabHeader li").eq(index).remove();
    $("#tabContent .tab-pane").eq(index).remove();
}
//显示标签
function activeTab(index){
    $("#tabHeader li").eq(index).addClass("active");
    $("#tabContent .tab-pane").eq(index).addClass("active");
}
//关闭自身标签
function closeTab(ob){
    var mid = $(ob).attr("data");
    // console.log("mid="+mid);
    var id = $("#tabHeader .active").attr("data");
    var index = openedIds.indexOf(mid);
    // console.log("-------------index="+index);
    if(id == mid){
        //如果当前展示的是本身
        closeTabDetai(index+1);
        // console.log(openedIds.indexOf(id));
        if(index+1 >=openedIds.length ){
            // 最右边的那个 关闭后 显示最后一个
            // console.log("显示最后一个");
            activeTab(openedIds.length-1);
        }else{
            // 中间的 关闭后 显示最靠近右边那个
            // console.log("显示最靠近右边那个");
            activeTab(index+1);
        }
    }else{
        //如果当前展示的不是本身 则 直接删除
        closeTabDetai(index+1);
    }
    openedIds.remove(mid);
}

//提示框 -- begin
toastr.options = {
    "closeButton": true,
    "debug": false,
    "progressBar": true,
    "preventDuplicates": false,
    "positionClass": "toast-top-right",
    "onclick": null,
    "showDuration": "400",
    "hideDuration": "1000",
    "timeOut": "3000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}
//成功
function toastrSuccess(title){
    toastr["success"](title);
}
//失败
function toastrError(title,desc){
    toastr["error"](title,desc);
}
//失败
function toastrError(desc){
    toastr["error"]("失败",desc);
}
//回到顶部
function backTop(){
    $('html, body').animate({scrollTop:0}, 'slow');
}

//提示框 -- end
//修改iframe 在onload时候修改height为0，否则iframe只能变大不能变小
function reinitIframeOnload(obj){
    obj.height = 0 ;
    obj.height = $(obj.contentWindow.document).height();
}

//修改iframe值
function reinitIframe(){
    // var iframe = document.getElementById("iframepage");
    // console.log($("iframe").length);
    try{
        $("iframe").each(function(){
            var iframe = $(this)[0];
            // iframe.height = 0 ;
            // var bHeight = iframe.contentWindow.document.body.scrollHeight;
            // var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
            // console.log(    $(iframe.contentWindow.document.body).height()        );
            // var height = Math.max(bHeight, dHeight);
            // console.log($(window).height());
            var height = Math.max($(iframe.contentWindow.document).height(), $(window).height()-130);
            // console.log("---------------------------------"); //浏览器时下窗口可视区域高度
            // console.log($(iframe.contentWindow.window).height()); //浏览器时下窗口可视区域高度
            // console.log($(iframe.contentWindow.document).height()); //浏览器时下窗口文档的高度
            // console.log($(iframe.contentWindow.document.body).height());//浏览器时下窗口文档body的高度
            // console.log($(iframe.contentWindow.document.body).outerHeight(true));//浏览器时下窗口文档body的总高度 包括border padding margin
            // console.log($(iframe.contentWindow.window).width()); //浏览器时下窗口可视区域宽度
            // console.log($(iframe.contentWindow.document).width());//浏览器时下窗口文档对于象宽度
            // console.log($(iframe.contentWindow.document.body).width());//浏览器时下窗口文档body的高度
            // console.log($(iframe.contentWindow.document.body).outerWidth(true));//浏览器时下窗口文档body的总宽度 包括border padding margin
            // iframe.height = $(iframe.contentWindow.document).height();
            // if($(iframe.contentWindow.document.body).find(".my-wrapper")){
            //     height = $(iframe.contentWindow.document.body).find(".my-wrapper").height() +50;
            // }
            iframe.height = height;

        });
    }catch (ex){}
}
window.setInterval("reinitIframe()", 200);

//设置初始化时间
function initSessionStorage(){
    var storage = window.sessionStorage;
    var yesterday = getYesterday();
    var lastYear = getLastYear();
    //测试
    // yesterday = "2017-06-01";
    // lastYear = "2017-06-01";

    storage.setItem("beginDate",yesterday);
    storage.setItem("endDate",yesterday);
    storage.setItem("compareBeginDate",lastYear);
    storage.setItem("compareEndDate",lastYear);
    var date = new Date();
    storage.setItem("year",date.getFullYear());
    var month = date.getMonth()+1;
    var quarter = 1;
    if(month <= 3){
        quarter = 1;
    }else if(month <= 6){
        quarter = 2;
    }else if(month <= 9){
        quarter = 3;
    }else{
        quarter = 4;
    }
    storage.setItem("quarter",quarter);
}
initSessionStorage();

function getYesterday() {
    var dd = new Date();
    dd.setDate(dd.getDate()-1);
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;
    if(m<10){
        m = "0"+m;
    }
    var d = dd.getDate();
    if(d<10){
        d = "0"+d;
    }
    return y+"-"+m+"-"+d;
}

function getLastYear() {
    var dd = new Date();
    dd.setFullYear(dd.getFullYear()-1);
    dd.setDate(dd.getDate()-1);
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;
    if(m<10){
        m = "0"+m;
    }
    var d = dd.getDate();
    if(d<10){
        d = "0"+d;
    }
    return y+"-"+m+"-"+d;
}

function goDownPage(){
    window.open("../sale/downLoad");
}