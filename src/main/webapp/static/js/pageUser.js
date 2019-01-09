jQuery(document).ready(function ($) {
    $('#search_form').submit(function (event) {
        setEnabledSearchButton(true);
        event.preventDefault();
        searchUserWithAjax();
    });
});

function searchUserWithAjax() {
    var search = $('#user_select').val();
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "getUserByName",
        data : search,
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS: ", data);
            viewSearchUser(data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}
function setEnabledSearchButton(flag) {
    $('#search_btn').prop("disable", flag);
    console.log('enabled!!')
}
function viewSearchUser(data) {
    var userDetails = {
        actingOrder: '',
        cash: '',
        enabled: '',
        fullName: '',
        nickName: '',
        orderAmount: '',
        roleInSystem: '',
        summaryOfDebt: ''
    };
    userDetails = data;
    $('#fullName').text(userDetails.fullName);
    $('#cash').text(userDetails.cash);
    $('#enabled').text(userDetails.enabled);
    $('#nickname').text(userDetails.nickName);
    $('#orderAmount').text(userDetails.orderAmount);
    $('#roleInSystem').text(userDetails.roleInSystem);
    $('#summaryOfDebt').text(userDetails.summaryOfDebt);
    $('#mainDivUserBody').show().css('visibility', 'visible');
}
function setVisitableDiv() {
}