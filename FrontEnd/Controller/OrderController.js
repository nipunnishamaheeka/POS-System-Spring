import { getAllOrders } from "../model/OrderModel.js";
import { getAllCustomers } from "../model/CustomerModel.js";
import { getAllItems, updateItem } from "../model/ItemModel.js";
import { saveOrder } from "../model/OrderModel.js";

var itemId;
var itemQty;
var orderQty;

$(document).ready(function () {
  refresh();
});

$(".orderManageBtn").click(function () {
  refresh();
});

async function refresh() {
  generateId();
  $("#OrderManage .orderDate").val(new Date().toISOString().split("T")[0]);
  loadCustomer();
  loadItems();
  $("#OrderManage .Total").text("");
  // $('#OrderManage .SubTotal').text("");
  // $('#OrderManage .SubTotal').text("");
  $("#OrderManage .Balance").val("");
  $("#OrderManage .Cash").val("");
  $("#OrderManage .Discount").val("");
  const allOrder = await getAllOrders();
  $(".counts .orders h2").text(allOrder.length);
}

function extractNumber(id) {
  var match = id.match(/OD(\d+)/);
  if (match && match.length > 1) {
    return match[1];
  }
  return null;
}

async function generateId() {
  let orders = await getAllOrders();
  // alert(orders.length);
  let id;
  if (orders.length === 0) {
    id = "OD01";
  } else {
    // alert('awa');
    let orderId = orders[orders.length - 1].id;
    // let number = extractNumber(orderId);
    var match = orderId.match(/OD(\d+)/);

    if (match && match.length > 1) {
      let number = match[1];
      number++;
      id = "OD0" + number;
    }
  }
  $("#OrderManage .orderId").val(id);
}

async function loadCustomer() {
  let cmb = $("#OrderManage .customers");
  cmb.empty();
  let option = [];
  let customers = await getAllCustomers();
  option.unshift("");
  for (let i = 0; i < customers.length; i++) {
    option.push(customers[i].custId);
  }

  $.each(option, function (index, value) {
    cmb.append($("<option>").val(value).text(value));
  });
}

$("#OrderManage .customers").change(async function () {
  const customers = await getAllCustomers();
  const customerId = $(this).val();
  const customer = customers.find((c) => c.custId === customerId);
  $("#OrderManage .custId").val(customer.custId);
  $("#OrderManage .custName").val(customer.custName);
  $("#OrderManage .custAddress").val(customer.custAddress);
  $("#OrderManage .custSalary").val(customer.custSalary);
});

async function loadItems() {
  let cmb = $("#OrderManage .itemCmb");
  cmb.empty();
  let option = [];
  let items = await getAllItems();

  for (let i = 0; i < items.length; i++) {
    option.push(items[i].id);
  }

  option.unshift("");

  $.each(option, function (index, value) {
    cmb.append($("<option>").val(value).text(value));
  });
}

$("#OrderManage .itemCmb").change(async function () {
  const items = await getAllItems();
  console.log(items);
  const getItemId = $(this).val();
  const item = items.find((c) => c.id === getItemId);
  console.log(item);
   itemId = item.id;

  // alert(item.itemQty);
   itemQty = item.qty;
  $("#OrderManage .addBtn").text("Add");
  $("#OrderManage .itemCode").val(item.id);
  $("#OrderManage .itemName").val(item.name);
  $("#OrderManage .itemQty").val(item.qty);
  $("#OrderManage .itemPrice").val(item.price);
});

let getItems = [];

function clear(tableCount) {
  if (tableCount === 1) {
    $("#OrderManage .itemCode").val("");
    $("#OrderManage .itemName").val("");
    $("#OrderManage .itemPrice").val("");
    $("#OrderManage .itemQty").val("");
    $("#OrderManage .orderQty").val("");
    $("#OrderManage .SubTotal").text("");
    $("#OrderManage .Cash").val("");
    $("#OrderManage .Total").text("");
    $("#OrderManage .Discount").val("");
    $("#OrderManage .itemCmb").val("");
  } else {
    $("#OrderManage .custId").val("");
    $("#OrderManage .custName").val("");
    $("#OrderManage .custAddress").val("");
    $("#OrderManage .custSalary").val("");
    $("#OrderManage .itemCode").val("");
    $("#OrderManage .itemName").val("");
    $("#OrderManage .itemPrice").val("");
    $("#OrderManage .itemQty").val("");
    $("#OrderManage .orderQty").val("");
  }
}

$("#OrderManage .addBtn").click(function () {
  if ($("#OrderManage .addBtn").text() === "delete") {
    dropItem();
  } else {
    let getItem = {
      itemCode: $("#OrderManage .itemCode").val(),
      getItems: $("#OrderManage .itemName").val(),
      itemPrice: parseFloat($("#OrderManage .itemPrice").val()),
      itemQty: parseInt($("#OrderManage .orderQty").val(), 10),
      total:
        parseFloat($("#OrderManage .itemPrice").val()) *
        parseInt($("#OrderManage .orderQty").val(), 10),
    };

    let itemQty = parseInt($("#OrderManage .itemQty").val(), 10);
    let orderQty = parseInt($("#OrderManage .orderQty").val(), 10);

    if (itemQty >= orderQty) {
      if (
        $("#OrderManage .custId").val() !== "" &&
        $("#OrderManage .custName").val() !== null
      ) {
        if (orderQty > 0) {
          let item = getItems.find((I) => I.itemCode === getItem.itemCode);
          if (item == null) {
            getItems.push(getItem);
            loadTable();
            clear(1);
            setTotal();
          } else {
            alert("Already Added");
          }
        } else {
          alert("Invalid Quantity");
        }
      } else {
        alert("Invalid Customer");
      }
    } else {
      alert("Not Enough Quantity");
    }
  }
});

function loadTable() {
  $("#OrderManage .tableRows").empty();
  for (let i = 0; i < getItems.length; i++) {
    $("#OrderManage .tableRows").append(
      "<div> " +
        "<div>" +
        getItems[i].itemCode +
        "</div>" +
        "<div>" +
        getItems[i].getItems +
        "</div>" +
        "<div>" +
        getItems[i].itemPrice +
        "</div>" +
        "<div>" +
        getItems[i].itemQty +
        "</div>" +
        "<div>" +
        getItems[i].total +
        "</div>" +
        "</tr>"
    );
  }
}

function setTotal() {
  let total = 0;
  for (let i = 0; i < getItems.length; i++) {
    total += getItems[i].total;
  }
  $("#OrderManage .Total").text(total);
}

$("#OrderManage .placeOrder").click(function () {
  let cash = parseFloat($("#OrderManage .Cash").val());
  let total = parseFloat($("#OrderManage .Total").text());
  let discount = parseFloat($("#OrderManage .Discount").val());

  // alert(cash + ' ' + total + ' ' + discount);

  if (cash >= total) {
    if (discount >= 0 && discount <= 100) {
      let subTotal = total - (total * discount) / 100;
      $("#OrderManage .SubTotal").text(subTotal.toFixed(2));
      let balance = cash - subTotal;
      $("#OrderManage .Balance").val(balance.toFixed(2));

      let Order = {
        orderId: $("#OrderManage .orderId").val(),
        orderDate: $("#OrderManage .orderDate").val(),
        custId: $("#OrderManage .custId").val(),
        items: getItems,
        total: total,
        discount: discount,
        subTotal: subTotal,
        cash: cash,
        balance: balance,
      };

      saveOrder(Order);
      updateItemData();
      getItems = [];
      loadTable();
      clear(2);
      alert("Order Placed");
      refresh();
    } else {
      alert("Invalid Discount");
    }
  } else {
    alert("Not Enough Cash");
  }
});

async function updateItemData() {
  let items = await getAllItems();
  for (let i = 0; i < getItems.length; i++) {
    let item = items.find((I) => I.itemId === getItems[i].itemCode);
    item.itemQty -= getItems[i].itemQty;
    let index = items.findIndex((I) => I.itemId === getItems[i].itemCode);
    let sendItem = {
      itemId: item.itemId,
      itemName: item.itemName,
      itemQty: item.itemQty,
      itemPrice: item.itemPrice,
    };
    updateItem(index, sendItem);
  }
}

$(".mainTable .tableRows").on("click", "div", function () {
  let itemCode = $(this).children("div:eq(0)").text();
  let itemName = $(this).children("div:eq(1)").text();
  let price = $(this).children("div:eq(2)").text();
  let qty = $(this).children("div:eq(3)").text();

  $("#OrderManage .itemCode").val(itemCode);
  $("#OrderManage .itemName").val(itemName);
  $("#OrderManage .itemPrice").val(price);
  $("#OrderManage .orderQty").val(qty);

  $("#OrderManage .ItemSelect .addBtn").text("delete");
  $("#OrderManage .ItemSelect .addBtn").css("background-color", "red");
});

function dropItem() {
  let itemCode = $("#OrderManage .itemCode").val();
  let item = getItems.find((I) => I.itemCode === itemCode);
  let index = getItems.findIndex((I) => I.itemCode === itemCode);
  getItems.splice(index, 1);
  alert("Item Removed");
  loadTable();
  clear(1);
  setTotal();
}

// $('#orderManage .itemCmb')
