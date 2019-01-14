// $('.popupMenu .popupContent').css('height',window.innerHeight*0.7);

$(document).ready(function () {
    // createContent();

//    let loginPassword = location.href.split("?")[1];
//    let username = document.getElementById("username");
//
//    if (location.href.split("?")[0].endsWith('index.html')) {
//        if (!loginPassword) {
//            logout();
//        }
//        let login;
//        loginPassword.split("&").forEach(pair => {
//            if (pair.split("=")[0] == 'login') {
//                login = pair.split("=")[1];
//            }
//        });
//        username.innerText = login;
//    }

    $('.addToBasket').on('click', addToBasket);
    $('.content .product').children().not('.addToBasket').on('click', productShow);
    $('#searchField').on('input', searchOnSite);
});

// $('.imgLogout').on('click', logout);

function logout() {
    location.replace("login.html");
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
            bad = "В полях 'Пароль' комбинации символов не совпадают";
        }
    }
    if (bad != "") {
        $('#secondPassword')
            .css("background", "#ffcab2");
        alert(bad);
        return false;
    }
}

//__________________________

function createContent() {
    data.forEach(category => {
        let categoryDiv = '<div class="category"><div>' + category.categoryName + '</div></div>';
        let productsDiv = document.createElement('div');
        productsDiv.className = "products";

        for (let i = 0; i < category.products.length; i++) {
            let productDiv = createProduct(category.products[i]);
            productsDiv.appendChild(productDiv);
        }

        $('#content').append(categoryDiv);
        $('#content').append(productsDiv);
    });
}

function createProduct(product) {
    let productDiv = document.createElement('div');
    productDiv.className = "product";
    productDiv.id = product.id;

    productDiv.innerHTML = '<div class="logo"><img src="prodImg/' + product.id +
        '.jpg" value="logoButton" width="' + window.innerHeight * 0.2 + '">' +
        '<div class="productName">' + product.productName + '</div>' +
        '</div><div class="infoProduct"><div class="characteristics">' +
        '<ul><li>' + product.memory + '</li><li>' + product.frequency +
        '</li><li>' + product.price + '</li><li>' + product.id + '</li></ul>' +
        '</div><div class="price">' + product.price + '</div></div>' +
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
