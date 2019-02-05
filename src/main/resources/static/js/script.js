// $('.popupMenu .popupContent').css('height',window.innerHeight*0.7);

$(document).ready(setListener);

function setListener() {
    // createContent();

    $(document).on('click', '.addToBasket', addToBasket);
    $(document).on('click', '.content .product > * :not(.addToBasket)', productShow);
    $('#searchField').on('input', searchOnSite);
    $('#registration').on('submit', checkPass);
    $(document).on('click', '.editProductDB', editProductDB);
    $(document).on('click', '.removeProductDB', removeProductDB);
    $('#uploadImgButton').on('click', uploadImgButton);
    $('#popup3 [type="reset"]').on('click', resetUploadImgButton);
    $('#uploadImg').on('change', changeUploadImgButton);
    $('#editForm').submit(sendEditForm);
    $(document).on('click', '.removeFromBasket', removeFromBasket);
}

//__________________________

function checkPass() {
    const password = $('#password').val();
    const secondPassword = $('#secondPassword').val();
    let bad = "";
    if (secondPassword == "") {
        bad = "Пароль не подтвержден. Пожалуйста, повторите ввод пароля";
    }
    else {
        if (password != secondPassword) {
            bad = "Пароли не совпадают";
        }
    }
    if (bad != "") {
        $('#secondPassword')
            .css("background", "#ffcab2");
        alert(bad);
        return false
    }
}

//__________________________
function createOrUpdateProduct(product) {
    let categoryDiv = $('#content').children('[data-category="' + product.categoryName + '"]');
    let productDiv = $('.products').children('[data-id="' + product.id + '"]');
    let productDivFromBasket = $('#basketProducts').children('[data-id="' + product.id + '"]');
    let productForEdit = $('tbody').children('[data-edit-id="' + product.id + '"]');
    if (!productDiv.length) {
        let productsDiv = $(categoryDiv).next();
        if (!categoryDiv.length) {
            categoryDiv = $('<div class="category" data-category="' + product.categoryName + '"><div>'
                + product.categoryName + '</div></div>');
            productsDiv = $('<div class="products">');
            $('#content').append(categoryDiv).append(productsDiv);
        }
        $(productsDiv).append(createProduct(product));
        $('#editProducts table').append(createProductForEdit(product));
    } else {
        createProduct(product).replaceAll(productDivFromBasket);
        createProductForEdit(product).replaceAll(productForEdit);
        createProduct(product).replaceAll(productDiv);
    }

    productsHide();
}

function createContent(products) {
    products.forEach(product => {
        createOrUpdateProduct(product)
    });
}

function createProductForEdit(product) {
    let charactElement = '';
    product.characteristic.split(';').forEach(function (charact) {
        if (charact) {
            charactElement += charact + ';<br>';
        }
    });

    let productForEdit = $('<tr data-edit-id="'+product.id+'"><td>' + product.id + '</td>' +
        '<td>' + product.categoryName + '</td><td>' + product.productName + '</td><td>' + product.price + '</td>' +
        '<td><img src="' + product.imgUrl + '" value="logoButton" width="50px"></td>' + '<td>' + charactElement + '</td>' +
        '<td align="right"><img class="editProductDB" value="editProduct" src="img/edit.png"></td>' +
        '<td align="right"><img class="removeProductDB" value="removeProduct" src="img/removeFromBasket.png"></td></tr>');
    return productForEdit;
}

function createProduct(product) {
    let charactElement = '';
    product.characteristic.split(';').forEach(function (charact) {
        if (charact) {
            charactElement += '<li>' + charact + '</li>';
        }
    });
    let productDiv = $('<div class="product" data-id= "'+product.id+'"><div class="logo">' +
        '<img src="' + product.imgUrl + '" value="logoButton">' +
        '<div class="productName">' + product.productName + '</div></div><div class="infoProduct">' +
        '<div class="characteristics"><ul>' + charactElement + '</ul></div>' +
        '<div class="price">' + product.price + '</div></div>' +
        '<img class="addToBasket" value="В корзину" src="img/addToBasket.png">' +
        '<img class="removeFromBasket" value="В корзину" src="img/removeFromBasket.png"></div></div>');

    return productDiv;
}

function addToBasket() {
    let addToBasketElement = this.parentElement.cloneNode(true);
    $('#basketProducts .addToBasket').hide();
    addToBasketElement.getElementsByClassName("addToBasket")[0].removeAttribute("style");
    addToBasketElement.removeAttribute("style");

    $('#basketProducts').append(addToBasketElement);
    countProductsAndSum();
}

function productShow() {
    let product = $(this).closest('.product')[0];
    if (product.hasAttribute("style")) {
        product.removeAttribute("style");
        product.getElementsByClassName("infoProduct")[0].removeAttribute("style");
        product.getElementsByClassName("addToBasket")[0].removeAttribute("style");
        return;
    }

    productsHide();

    product.setAttribute("style", "width:100%");
    product.getElementsByClassName("infoProduct")[0].setAttribute("style", "display:flex;");
    product.getElementsByClassName("addToBasket")[0].setAttribute("style", "display:initial;");
}

function productsHide() {
    document.querySelectorAll(".content .product").forEach(prod => {
        prod.removeAttribute("style");
        prod.getElementsByClassName("infoProduct")[0].removeAttribute("style");
        prod.getElementsByClassName("addToBasket")[0].removeAttribute("style");
    });
}

function removeFromBasket() {
    $(this).closest('.product').remove();
    countProductsAndSum();
}

function countProductsAndSum() {
    let countProducts = document.getElementById("countProducts");
    let count = $("#basketProducts").children().length;
    countProducts.innerText = count;
    if (count > 0) {
        countProducts.setAttribute("style", "visibility: visible;");
    } else {
        countProducts.removeAttribute("style");
        countProducts.innerText = '';
    }
    let discount = Math.random() * 101 ^ 0;
    $('#discount').text('Скидка ' + discount + '%:');

    let sum = 0;
    let prices = document.querySelectorAll('#basketProducts .price');
    prices.forEach(element => {
        sum += +element.innerHTML;
    });

    $('#sum').text(sum);

    let discountSum = sum * discount / 100;
    $('#discountSum').text(~~discountSum);
    $('#totalSum').text(sum - discountSum ^ 0);
}

function basketShow() {
    $("#popup1").show();
    productsHide();
}

function basketHide() {
    $("#popup1").hide();
}

function searchShow() {
    $("#popup2").show();
    productsHide();
}

function searchHide() {
    $("#popup2").hide();
    $("#searchProducts").empty();
    $("#searchField").val('');
}

function editShow() {
    $("#popup3").show();
    $('#editForm')[0].reset();
}

function editHide() {
    $("#popup3").hide();
    $('#editForm')[0].reset();
    resetUploadImgButton();
}

function editProductDB() {
    $("#uploadImgButton [value = 'logoButton']").attr('src', $(this).closest('tr').find('td:eq(4) [value="logoButton"]').attr('src'));
    $("#popup3 [name='categoryName']").val($(this).closest('tr').find('td:eq(1)').text());
    $("#popup3 [name='productName']").val($(this).closest('tr').find('td:eq(2)').text());
    $("#popup3 [name='characteristic']").val($(this).closest('tr').find('td:eq(5)').text());
    $("#popup3 [name='id']").val($(this).closest('tr').find('td:eq(0)').text());
    $("#popup3 [name='price']").val($(this).closest('tr').find('td:eq(3)').text());
}

function removeProductDB() {
    let data = {};
    data.id = $(this).closest('tr').data('edit-id');
    $.ajax({
        type: "DELETE",
        url: "delete",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(data),
        success: removeProductOnSite
    });
    return false;

}

function removeProductOnSite(data) {
    $('.products').children('[data-id="' + data.id + '"]').remove();
    $('#basketProducts').children('[data-id="' + data.id + '"]').each(removeFromBasket);
    $('tbody').children('[data-edit-id="' + data.id + '"]').remove();
    let prodsDivRem = $('.products:not(.products:has(div))');
    prodsDivRem.prev().remove();
    prodsDivRem.remove();

}

function searchOnSite() {
    $('#searchProducts').empty();
    if (this.value.length > 2){
        let data = {};
        $('#searchProdForm').serializeArray().forEach(elem => {data[elem.name] = elem.value;});
        $.ajax({
            type: "POST",
            url: "search",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(data),
            success: function (data) {
                data.forEach(id => {
                    let elem = $('.products').children('[data-id="' + id + '"]');
                    $(elem).clone().appendTo('#searchProducts');
                });
            }
        });
        return false;
    }
}

function sendEditForm() {
    let data = new FormData(this);
    $.ajax({
        type: "PUT",
        processData: false,
        contentType: false,
        url: "edit",
        data: data,
        success: createOrUpdateProduct
    });

    $('#editForm')[0].reset();
    resetUploadImgButton();
    return false;
}

function uploadImgButton() {
    $('#uploadImg').click();
    return false;
}

function changeUploadImgButton() {
    let reader = new FileReader();
    reader.readAsDataURL(this.files[0]);
    reader.onload = function (e) {
        $("#uploadImgButton [value = 'logoButton']").attr('src', reader.result);
    }
}

function resetUploadImgButton() {
    $("#uploadImgButton [value = 'logoButton']").removeAttr('src');
    $("#popup3 [name='id']").val('');
}
