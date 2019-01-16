<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>

<head>
    <meta charset="utf-8">
    <title>Список товаров</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="js/listProd.js" asyns></script>
    <script src="js/jquery-3.3.1.min.js" asyns></script>
    <script src="js/script.js" asyns></script>
</head>

<body>
<div class="popupMenu" id="popup2">
    <div class="popupContent">
        <div class="popupHeader clearfix">
            <a href="javascript:searchHide()">
                <img class="imgSearch" src="img/close.png">
            </a>
            <form action="" id="searchProd"></form>
            <input id="searchField" type="search" placeholder="Поиск..." style='width: 30%;' form="searchProd">
        </div>
        <div class="poupSearchProducts" id="searchProducts">
            <!--Продукты в поиске-->
        </div>
    </div>
</div>

<c:if test = "${user.role eq 'admin'}">
<div class="popupMenu" id="popup3">
    <div class="popupContent">
        <div class="popupHeader clearfix">
        Редактирование товара
            <a href="javascript:editHide()">
                <img class="imgSearch" src="img/close.png">
            </a>
        </div>
        <div class="poupEditProducts" id="editProducts">

            <table>
                <c:forEach var = "category" items = "${products}">

            			<c:forEach var = "product" items = "${category.products}">
            				<tr>
            				    <td>${product.id}</td>
            				    <td>${category.categoryName}</td>
            				    <td>${product.productName}</td>
            				    <td>${product.price}</td>
            				    <td><img src="${product.imgUrl}" value="logoButton" width="50px"></td>
            				    <td>
            				        <c:forEach var = "charact" items = "${product.characteristic}">
                                    ${charact.key}: ${charact.value};
                                    </c:forEach>
                                </td>
            				    <td align="right"><img class="editProduct" value="editProduct" src="img/edit.png"></td>
            				    <td align="right"><img class="removeProduct" value="removeProduct" src="img/removeFromBasket.png"></td>
            				</tr>
            			</c:forEach>

                </c:forEach>
            </table>
        </div>
        <div class="popupFooter">
            <form action="login" method="post" id = "editForm"></form>
            <table>
                <tr>
                    <td rowspan="3" style="width:10%;">
                        <button form = "editForm" value = "img" style="width:100%;">
                            <img src="prodImg/AMD_A4-6300_OEM.jpg" value="logoButton" style="width:100px;">
                        </button>
                    </td>
                    <td style="width:30%;">
                        <input type="text" name="categoryName" placeholder="Наименование категории" required value = "категория" form = "editForm">
                    </td>
                    <td colspan="2" style="width:50%;">
                        <input type="text" name="productName" placeholder="Наименование товара" required value = "наименование" form = "editForm">
                    </td>
                </tr>
                <tr>
                    <td colspan="3" style="width:100%;">
                        <input type="text" name="characteristic" placeholder="Характеристики" required value = "описание_дрмднгмдндрорломврто" form = "editForm">
                    </td>
                </tr>
                <tr>
                    <td>
                    ид-468252
                    </td>
                    <td align="right">
                        <input type="text" name="price" placeholder="Цена" required value = "10000" form = "editForm" style="width:50px;">
                    </td>
                    <td align="right"><button class="placeOrder">Сохранить</button></td>
                </tr>
            </table>

        </div>
    </div>
</div>
</c:if>

<div class="popupMenu" id="popup1">
    <div class="popupContent">
        <div class="popupHeader clearfix">
            Корзина
            <a href="javascript:basketHide()">
                <img class="imgBasket" src="img/close.png">
            </a>
        </div>
        <div class="poupBasketProducts" id="basketProducts">
            <!--Продукты в корзине -->

        </div>
        <div class="popupFooter">
            <table>
                <tr>
                    <td>Сумма товаров:</td>
                    <td id="sum">0</td>
                </tr>
                <tr>
                    <td id="discount">Скидка 10%:</td>
                    <td id="discountSum">0</td>
                </tr>
                <tr class="total">
                    <td>Итого:</td>
                    <td id="totalSum">0</td>
                </tr>
            </table>
            <button class="placeOrder">Оформить</button>
        </div>
    </div>
</div>

<div class="menu">
    <div class="cabinet">
        <a href="logout">
            <img class="imgLogout" value="Поиск" src="img/logout.png">
        </a>
        <div class="username" id="username">${user.firstName} ${user.lastName}</div>
        <c:if test = "${user.role eq 'admin'}">
            <a href="javascript:editShow()">
                <div>edit product</div>
            </a>
        </c:if>
    </div>
    <div class="search">
        <a href="javascript:searchShow()">
            <img class="imgSearch" value="Поиск" src="img/search.png">
        </a>
    </div>
    <div class="basket">
        <a href="javascript:basketShow()">
            <img class="imgBasket" value="Корзина" src="img/basket.png">
        </a>
        <div class="countProducts" id="countProducts"></div>
    </div>

</div>
<div class="content" id="content">
    <c:forEach var = "category" items = "${products}">
		<div class="category">
			<div>${category.categoryName}</div>
		</div>
        <div class="products">
			<c:forEach var = "product" items = "${category.products}">
				<div class="product" id= "${product.id}">
                    <div class="logo">
                        <img src="${product.imgUrl}" value="logoButton" width="150px">
                        <div class="productName">${product.productName}</div>
                    </div>
                    <div class="infoProduct">
                        <div class="characteristics">
                            <ul>
                                <c:forEach var = "charact" items = "${product.characteristic}">
                                    <li>${charact.key}: ${charact.value}</li>
                                </c:forEach>
                            </ul>
                        </div>
						<div class="price">${product.price}</div>
					</div>
					<img class="addToBasket" value="В корзину" src="img/addToBasket.png">
					<img class="removeFromBasket" value="В корзину" src="img/removeFromBasket.png">
				</div>
			</c:forEach>
		</div>
    </c:forEach>
</div>
<div class="footer">
    <div>Java RD. Project "HTML, CSS, JS basics". Made by Petr Bunegin.</div>
</div>
</body>

</html>