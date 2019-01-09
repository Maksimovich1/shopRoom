jQuery(document).ready(function ($) {
    $('#searchBlockDateForApartment').submit(function (event) {
        event.preventDefault();
        searchBlockDateForApartmentWithAjax();
    });
});
function searchBlockDateForApartmentWithAjax() {
    var optSelect = document.getElementById('idApartmentPeriod');
    var selectedApart = optSelect.options[optSelect.selectedIndex].value;
    $.ajax({
        type: "POST",
        contentType : "application/json",
        url : "getBlockForApart",
        data : selectedApart,
        dataType : 'json',
        timeout : 100000,
        success: function (data) {
            console.log(data);
        },
        error: function (e) {
            console.log(e);
        }
    });

}