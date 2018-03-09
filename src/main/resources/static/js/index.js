$(function () {
    alert($.i18n.prop('hello'));
});


function insertKpi(id, name) {
    $.ajax({
        type: "POST",
        url: contextPath + "kpiInfo/insertKpiInfo",
        data: {id: id, name: name},
        success: function (result) {
            console.log(result);
        },
        error: function (result) {
            console.log(result);
        }
    });
}
function selectKpi(id) {
    $.ajax({
        type: "GET",
        url: contextPath + "kpiInfo/selectKpiInfo",
        data: {id: id},
        success: function (result) {
            console.log(result);
        },
        error: function (result) {
            console.log(result);
        }
    });
}