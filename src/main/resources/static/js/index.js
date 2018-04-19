$(function () {
    // alert($.i18n.prop('hello'));

});

function test() {
    $.ajax({
        url:contextPath + 'excelTest/importExcel',
        method:'get',
        success:function(data){

        },
        error:function(data){

        }
    });
}


