<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE HTML>

<head>
    <meta charset="utf-8">
    <title>Список товаров</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
            <form action="" id="searchProdForm"></form>
            <select size = "1" name = "searchParam" form="searchProdForm">
              <option value = "byName">По названию</option>
              <option value = "byPrice">По цене</option>
              <option value = "byCharacteristic">По характеристикам</option>
            </select>
            <input id="searchField" type="search" placeholder="Поиск..." style='width: 30%;' form="searchProdForm" name = "searchString" autofocus>
        </div>
        <div class="poupSearchProducts" id="searchProducts">
            <!--Продукты в поиске-->
        </div>
    </div>
</div>

<c:if test = "${user.role.roleName eq 'admin'}">
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
                <c:forEach var = "category" items = "${categories}">

            			<c:forEach var = "product" items = "${category.products}">
            				<tr data-edit-id=${product.id}>
            				    <td>${product.id}</td>
            				    <td>${category.categoryName}</td>
            				    <td>${product.productName}</td>
            				    <td>${product.price}</td>
            				    <td><img src="${product.imgUrl}" value="logoButton" width="50px"></td>
            				    <td><c:forEach var = "charact" items = "${fn:split(product.characteristic, ';')}">${charact};<br></c:forEach></td>
            				    <td align="right"><img class="editProductDB" value="editProduct" src="img/edit.png"></td>
            				    <td align="right"><img class="removeProductDB" value="removeProduct" src="img/removeFromBasket.png"></td>
            				</tr>
            			</c:forEach>

                </c:forEach>
            </table>
        </div>
        <div class="popupFooter">
            <form id = "editForm"/>
            <table>
                <tr>
                    <td rowspan="3">
                        <button id = "uploadImgButton"><img value="logoButton" alt="загрузить фото"></button>
                        <input type="file" name="uploadImg" form = "editForm" id = "uploadImg">
                    </td>
                    <td>
                        <input type="text" name="categoryName" placeholder="Наименование категории" required form = "editForm">
                    </td>
                    <td colspan="2">
                        <input type="text" name="productName" placeholder="Наименование товара" required form = "editForm">
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="text" name="characteristic" placeholder="Характеристики" required form = "editForm">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="id" form = "editForm">
                        <input type="text" name="price" placeholder="Цена" required form = "editForm">
                    </td>
                    <td align="right"><button class="placeOrder sendEditForm" form = "editForm" type="reset">Создать</button></td>
                    <td align="right"><button class="placeOrder sendEditForm" form = "editForm" type="submit">Сохранить</button></td>
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
            <button class="placeOrder" id = "createOrder">Оформить</button>
        </div>
    </div>
</div>

<div class="menu">
    <div class="cabinet">
        <a href="logout">
            <img class="imgLogout" value="Поиск" src="img/logout.png">
        </a>
        <div class="username" id="username">${user.firstName} ${user.lastName}</div>
        <c:if test = "${user.role.roleName eq 'admin'}">
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
    <c:forEach var = "category" items = "${categories}">
		<div class="category" data-category=${category.categoryName}>
			<div>${category.categoryName}</div>
		</div>
        <div class="products">
			<c:forEach var = "product" items = "${category.products}">
				<div class="product" data-id= "${product.id}">
                    <div class="logo">
                        <img src="${product.imgUrl}" value="logoButton">
                        <div class="productName">${product.productName}</div>
                    </div>
                    <div class="infoProduct">
                        <div class="characteristics">
                            <ul>
            				    <c:forEach var = "charact" items = "${fn:split(product.characteristic, ';')}"><li>${charact};</li></c:forEach>
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