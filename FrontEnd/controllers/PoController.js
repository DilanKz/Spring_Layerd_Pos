let selectCusIds = $('#customerIds');
let selectItemIds = $('#itemIds');
let totalField = $('#maxTot');
let orderTable = $('#allOrderTable');

let selectedCustomerID;
let total = 0;
let cartItems = [];
let orderID;

function nextOrderID() {

}

loadCustomerOptionIds();
loadItemOptionIds();
loadAllOrders();

function loadCustomerOptionIds() {
    /*selectCusIds.empty();
    selectCusIds.append($('<option selected>Select_ID</option>'));

    for (let index in customerList) {
        let option = $('<option value="'+index+'"> '+customerList[index].cid+' </option>');
        selectCusIds.append(option);
    }*/

    $.ajax({
        url: baseURI+'customer',
        success: function (res) {
            selectCusIds.empty();
            selectCusIds.append($('<option selected>Select_ID</option>'));

            for (let i = 0; i < res.data.length; i++) {
                let option = $('<option>' + res.data[i].id + '</option>');
                selectCusIds.append(option);
            }
        },
        error: function (error) {
            console.log(error.status);
        }
    })
}

function loadItemOptionIds() {

    $.ajax({
        url: baseURI+'item',
        success: function (res) {
            selectItemIds.empty();
            selectItemIds.append($('<option selected>Select_ID</option>'));

            for (let i = 0; i < res.data.length; i++) {
                let option = $('<option>' + res.data[i].code + '</option>');
                selectItemIds.append(option);
            }
        },
        error: function (error) {
            console.log(error.status);
        }
    })

    /*selectItemIds.empty();
    selectItemIds.append($('<option selected>Select_ID</option>'));

    for (let index in itemList) {
        let option = $('<option value="'+index+'"> '+itemList[index].iId+' </option>');
        selectItemIds.append(option);
    }*/
}

//Date formatter
/*
let date=new Date();

let fullDay = date.getDay();
let fullMonth = date.getMonth()+1;
let fullYear = date.getFullYear();

let dateFormatter=`${fullDay}-${fullMonth}-${fullYear}`;

$('#dtf').val(dateFormatter);*/

/*function getTodayDate() {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, "0");
    const day = String(today.getDate()).padStart(2, "0");

    $('#dtf').val(`${year}-${month}-${day}`);
}*/

function clearPoFields() {
    $('#itemIds option:contains("Select_ID")').prop('selected', true);
    $('#poItemDesc').val("");
    $('#poItemQtyOnHand').val("");
    $('#poItemUP').val("");
    $('#poItemQty').val("");
}

selectCusIds.click(function () {
    let selectedCusID = $("#customerIds :selected").text();

    if (selectedCusID !== "Select_ID") {
        console.log(selectedCusID);

        $.ajax({
            url: baseURI+'customer',
            success: function (res) {
                for (let i = 0; i < res.data.length; i++) {
                    if (res.data[i].id===selectedCusID){
                        console.log(res.data[i].name)
                        $('#poCustomerName').val(res.data[i].name);
                        selectedCustomerID=selectedCusID;
                    }
                }
            },
            error: function (error) {
                console.log(error.status);
            }
        })

    }

});

selectItemIds.click(function () {
    let selectedItemID = $("#itemIds :selected").text();

    if (selectedItemID !== "Select_ID") {
        console.log(selectedItemID);

        $.ajax({
            url: baseURI+'item',
            method: "get",
            success: function (res) {

                for (let i = 0; i < res.data.length; i++) {
                    if (res.data[i].code===selectedItemID){
                        $('#poItemDesc').val(res.data[i].description);
                        $('#poItemQtyOnHand').val(res.data[i].qtyOnHand);
                        $('#poItemUP').val(res.data[i].unitPrice);
                    }
                }



            },
            error: function (error) {
                console.log(error.status);
            }
        })

    }
    /*$('#poItemDesc').val(itemListElement.desc);
    $('#poItemQtyOnHand').val(itemListElement.qty);
    $('#poItemUP').val(itemListElement.unitP);*/

});

$('#btnAddToCart').click(function () {

    if (qtyValidate()) {

        console.log(qtyValidate())

        let bItemId = $("#itemIds :selected").text();
        let desc = $('#poItemDesc').val();
        let unitPrice = $('#poItemUP').val();
        let buyingQty = $('#poItemQty').val();

        total += unitPrice * buyingQty;

        let cart = $("#pOTBody");
        let tr = $('<tr> <td>' + bItemId + '</td> <td>' + desc + '</td> <td>' + buyingQty + '</td> <td>' + unitPrice + '</td></tr>');
        cart.append(tr);
        console.log(total);
        totalField.val(total);
    }

});

//
$('#pOTBody').dblclick(function (event) {
    if (confirm("Do You Want to delete item ?")) {
        event.target.closest("tr").remove();
    }
});

function getAllCartData() {
    let cart = $('#pOTBody tr');

    cart.each(function () {
        let rowData = {};

        let cells = $(this).find('td');

        rowData["oid"]=$('#currentOrderID').val();
        rowData["itemCode"] = $(cells[0]).text();
        rowData["qty"] = $(cells[2]).text();
        rowData["unitPrice"] = $(cells[3]).text();

        cartItems.push(rowData);
    });

    console.log(cartItems);
}

$('#purchaseOrder').click(function () {

    let orderID = $('#currentOrderID').val();
    let date = $('#dtf').val();
    getAllCartData();
    let order = {
        oid: orderID,
        date: date,
        cusID: selectedCustomerID,
        orderDetails: cartItems
    }

    $.ajax({
        url: baseURI+'placeOrder',
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(order),
        success: function (res) {
            console.log(res)
        },
        error: function (error) {
            console.log(error.status);
        }
    })

});

function updateItems() {
    /*$.ajax({
        url: 'http://localhost:8080/Back_End_Web_exploded/placeOrder',
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(cartItems),
        success: function (res) {

        },
        error: function (error) {
            console.log(error.status);
        }
    })*/
}


function qtyValidate() {

    if (/^\d+$/.test($('#poItemQty').val())) {
        let qtyOnHand = $('#poItemQtyOnHand').val();
        $('#poItemQty').css("border-color", 'white');

        if (qtyOnHand >= Number($('#poItemQty').val())) {
            $('#poItemQty').css("border-color", 'white');
            return true;
        } else {
            $('#poItemQty').css("border-color", 'red');
            return false;
        }

    } else {
        $('#poItemQty').css("border-color", 'red');
        return false;
    }
}


function getCurrentDate() {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
}
$('#dtf').val(getCurrentDate());

let allOrders;

function loadAllOrders() {
    /*$.ajax({
        url:'http://localhost:8080/Back_End_Web_exploded/placeOrder?option=orders',
        success:function (res) {
            //allOrderTable
            orderTable.empty();
            allOrders=res;
            incrementOrderID(res[res.length-1].id);
            $('#currentOrderID').val(orderID);

            for (let i = 0; i < res.length; i++) {
                let row = $('<tr> <td>'+ res[i].id +'</td> <td>'+ res[i].date +'</td> <td>'+ res[i].name +'</td> <td>'+ res[i].total +'</td> </tr>');
                orderTable.append(row);
            }
        },
        error:function (error) {
            console.log(error.status);
        }
    })*/
}

$('#searchBtn').click(function () {

    let indexes = findIndexesByProperty($('#searchField').val());

    if (indexes.length!==0){

        orderTable.empty();

        for (let i = 0; i < indexes.length; i++) {
            let row = $('<tr> <td>'+ allOrders[indexes[i]].id +'</td> <td>'+ allOrders[indexes[i]].date +'</td> <td>'+ allOrders[indexes[i]].name +'</td> <td>'+ allOrders[indexes[i]].total +'</td> </tr>');
            orderTable.append(row);
        }


    }else {
        loadAllOrders();
    }

    console.log(indexes)
    console.log(indexes[0])
    console.log(indexes.length)
})

function incrementOrderID(currentOrderID) {
    const parts = currentOrderID.split('-');
    const number = parseInt(parts[1], 10);
    const incrementedNumber = number + 1;
    const newID = String(incrementedNumber).padStart(3, '0');
    orderID = `O-${newID}`;
}

//searchBtn

function findIndexesByProperty(searchValue) {
    let indexes = [];
    console.log(searchValue);
    for (let i = 0; i < allOrders.length; i++) {
        if (allOrders[i].id === searchValue) {
            indexes.push(i);
            console.log(allOrders[i].id);
        }

        if (allOrders[i].name === searchValue) {
            indexes.push(i);
        }

        if (allOrders[i].date === searchValue) {
            indexes.push(i);
        }
    }

    return indexes;
}
