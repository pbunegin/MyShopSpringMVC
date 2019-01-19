// $('.popupMenu .popupContent').css('height',window.innerHeight*0.7);

$(document).ready(function () {
    // createContent();

    $('.addToBasket').on('click', addToBasket);
    $('.content .product').children().not('.addToBasket').on('click', productShow);
    $('#searchField').on('input', searchOnSite);
    $('#registration').on('submit', checkPass);
    $('.editProductDB').on('click', editProductDB);
    $('.removeProductDB').on('click', removeProductDB);
    $('#uploadImgButton').on('click', uploadImgButton);
    $('#popup3 [type="reset"]').on('click', resetUploadImgButton);
    $('#uploadImg').on('change', changeUploadImgButton);
   $('#editForm').submit(sendEditForm);

});

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
function createOrUpdateProduct(product){
    let categoryDiv = $('#content').children('[data-category="'+product.categoryName+'"]');
    let productDiv = $('.products').children('[data-id="'+product.id+'"]');
    let productForEdit = $('tr').children('[data-editId="'+product.id+'"]');
    if (productDiv.length == 0){
        let productsDiv;
        if (categoryDiv.length == 0){
            categoryDiv = '<div class="category" data-category="'+product.categoryName+'"><div>'
                + product.categoryName + '</div></div>';
            productsDiv = '<div class="products"><div>';
        } else {
            productsDiv = $(categoryDiv).next();
        }
        productDiv = createProduct(product);
        productsDiv.appendChild(productDiv);
        $('#content').append(categoryDiv);
        $('#content').append(productsDiv);
        $('#editProducts tr').append(createProductForEdit(product));
    } else {
        productForEdit.html(createProductForEdit(product));
        productDiv.html(createProduct(product));
    }
}

function createContent(products) {
    products.forEach(product => {
        createOrUpdateProduct(product)
    });
}

function createProductForEdit(product) {
    let characteristic = '';
    product.characteristic.split(';').forEach(charact => {
        characteristic.concat(charact + ';<br>');
    });

    let productForEdit = '<td data-editId='+product.id+'>'+product.id+'</td>' +
        '<td>'+product.categoryName+'</td><td>'+product.productName+'</td><td>'+product.price+'</td>' +
        '<td><img src="'+product.imgUrl+'" value="logoButton" width="50px"></td>' + '<td>'+ characteristic+'</td>' +
        '<td align="right"><img class="editProductDB" value="editProduct" src="img/edit.png"></td>' +
        '<td align="right"><img class="removeProductDB" value="removeProduct" src="img/removeFromBasket.png"></td>';
    return productForEdit;
}


function createProduct(product) {
    let characteristic = '';
    product.characteristic.split(';').forEach(charact => {
        characteristic.concat('<li>' + charact + '</li>li>');
    });
    let productDiv = '<div class="product" data-id= "product.id"></div>';

    productDiv.innerHTML = '<div class="logo"><img src="'+product.imgUrl+'" value="logoButton">' +
        '<div class="productName">'+product.productName+'</div></div><div class="infoProduct">' +
        '<div class="characteristics"><ul>' + characteristic + '</ul></div>' +
        '<div class="price">'+product.price+'</div></div>' +
        '<img class="addToBasket" value="В корзину" src="img/addToBasket.png">' +
        '<img class="removeFromBasket" value="В корзину" src="img/removeFromBasket.png"></div>';
    return productDiv;
}

function addToBasket() {
    let addToBasketElement = this.parentElement.cloneNode(true);
    addToBasketElement.setAttribute("style", "width:auto");
    addToBasketElement.removeAttribute("id");
    addToBasketElement.getElementsByClassName("addToBasket")[0].removeAttribute("style");
    addToBasketElement.getElementsByClassName("removeFromBasket")[0].setAttribute("style", "display:initial;");
    

    $('#basketProducts').append(addToBasketElement);
    $('.removeFromBasket').on('click', removeFromBasket);

    countProductsAndSum();
}

function productShow() {
    let product = this.parentElement;
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
    document.getElementById('basketProducts').removeChild(this.parentElement);
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
    let discount = Math.random() * 101^0;
    $('#discount').text('Скидка ' + discount + '%:');

    let sum = 0;
    let prices = document.querySelectorAll('#basketProducts .price');
    prices.forEach(element => {
        sum += +element.innerHTML;
    });

    $('#sum').text(sum);

    let discountSum = sum * discount / 100;
    $('#discountSum').text(~~discountSum);
    $('#totalSum').text(sum - discountSum^0);
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
}

function editHide() {
    $("#popup3").hide();
}

function editProductDB(){
    $("#uploadImgButton [value = 'logoButton']").attr('src', $(this).closest('tr').find('td:eq(4) [value="logoButton"]').attr('src'));
    $("#popup3 [name='categoryName']").val($(this).closest('tr').find('td:eq(1)').text());
    $("#popup3 [name='productName']").val($(this).closest('tr').find('td:eq(2)').text());
    $("#popup3 [name='characteristic']").val($(this).closest('tr').find('td:eq(5)').text());
    $("#popup3 [name='id']").val($(this).closest('tr').find('td:eq(0)').text());
    $("#popup3 [name='price']").val($(this).closest('tr').find('td:eq(3)').text());
}

function removeProductDB(){

}

function searchOnSite() {
    $('#searchProducts').empty();
    let searchStr = this.value.toLowerCase();

    data.forEach(category => {
        category.products.forEach(product => {
            if (~product.productName.toLowerCase().indexOf(searchStr)) {
                let elem = document.getElementById(product.id).cloneNode(true);
                $('#searchProducts').append(elem);
            }
        });
    });
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
       return false;
}

function uploadImgButton(){
    $('#uploadImg').click();
}

function changeUploadImgButton(){
    let reader = new FileReader();
    reader.onload = function(e) { $("#uploadImgButton [value = 'logoButton']").attr('src', e.target.result); }
    reader.readAsDataURL(this.files[0]);
}

function resetUploadImgButton() {
    $("#uploadImgButton [value = 'logoButton']").removeAttr('src');
    $("#popup3 [name='id']").val('0');
}
