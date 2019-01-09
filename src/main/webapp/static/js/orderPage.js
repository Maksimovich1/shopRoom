function onSubSearchOrder() {
    var numberApartment = document.forms["formSearchOrders"]["room"].value;
    var numberOrder = document.forms["formSearchOrders"]["orderId"].value;
    if (isNaN(numberApartment) || isNaN(numberOrder)){
        alert("Не коректные данные");
        return false;
    }
    return true;
}
