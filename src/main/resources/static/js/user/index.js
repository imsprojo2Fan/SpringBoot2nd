
function redirect(htmlName){
    var url = "/menu/redirect?htmlName="+htmlName;
    $('#mainframe').attr("src",url);
}

//动态更改iframe高度
$(window.parent.document).find("#mainframe").load(function(){
    var main = $(window.parent.document).find("#mainframe");
    var thisHeight = window.innerHeight+100;
    main.height(thisHeight);
});
window.onresize=function(){
    var main = $(window.parent.document).find("#mainframe");
    var thisHeight = window.innerHeight+100;
    main.height(thisHeight);
}

var clicCount = 0;
function isHide() {

    var iframe = document.getElementById("mainframe");
    var iwindow = iframe.contentWindow;
    var idoc = iwindow.document;
    /*console.log("window",iwindow);//获取iframe的window对象
    console.log("document",idoc);  //获取iframe的document
    console.log("html",idoc.documentElement);//获取iframe的html
    console.log("head",idoc.head);  //获取head
    console.log("body",idoc.body);  //获取body*/

    var panel = idoc.getElementById("panel");//动态设置panel宽度 ie没试

    clicCount++;
    if(clicCount%2==0){
        console.log("open");
        if(panel){
            panel.setAttribute("class", "col-sm-10");
        }
        $('#mainframe').css("margin-left","260px");
    }else{
        console.log("close");
        if(panel){
            panel.setAttribute("class", "col-sm-12");
        }
        $('#mainframe').css("margin-left","10px");
    }
}