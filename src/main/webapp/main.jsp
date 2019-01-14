<%@ page import="pojo.Category" %>
<%@ page import="pojo.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>

<head>
    <meta charset="utf-8">
    <title>Список товаров</title>

    <link rel="stylesheet" href="style.css">
    <script src="listProd.js" asyns></script>
    <script src="jquery-3.3.1.min.js" asyns></script>
    <script src="script.js" asyns></script>
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
                <!-- <input type="submit" value="Поиск" form="searchProd"> -->

            </div>
            <div class="poupSearchProducts" id="searchProducts">
                <!--Продукты в поиске-->
            </div>
        </div>
    </div>


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
                        <td>Сумма товаров: </td>
                        <td id="sum">0</td>
                    </tr>
                    <tr>
                        <td id="discount">Скидка 10%:</td>
                        <td id="discountSum">0</td>
                    </tr>
                    <tr class="total">
                        <td>Итого: </td>
                        <td id="totalSum">0</td>
                    </tr>
                </table>
                <button class="placeOrder">Оформить</button>
            </div>
        </div>
    </div>

    <div class="menu">
        <div class="cabinet">
            <a href="login">
                <img class="imgLogout" value="Поиск" src="/img/logout.png">
            </a>
            <div class="username" id="username"><%out.println((String)request.getAttribute("login")); %></div>
        </div>
        <div class="search">
            <a href="javascript:searchShow()">
                <img class="imgSearch" value="Поиск" src="/img/search.png">
            </a>
        </div>
        <div class="basket">
            <a href="javascript:basketShow()">
                <img class="imgBasket" value="Корзина" src="/img/basket.png">
            </a>
            <div class="countProducts" id="countProducts"></div>
        </div>

    </div>
    <div class="content" id="content">
        <%--<div class="category">--%>
            <%--<div>Телевизоры и медиа</div>--%>
        <%--</div>--%>
        <%--<div class="products">--%>
            <%--<div class="product">--%>
                <%--<div class="logo">--%>
                    <%--<img src="prod1.jpg" value="logoButton" width="100%">--%>
                    <%--<div class="productName">Телевизор</div>--%>
                <%--</div>--%>
                <%--<div class="infoProduct">--%>
                    <%--<div class="characteristics">--%>
                        <%--<ul>--%>
                            <%


                                List<Category> categories = (List<Category>) request.getAttribute("categories");
                                for (Category category: categories){
                                    out.println("<div class=\"category\"><div>" + category.getCategoryName() + "</div></div>");
                                    out.println("<div class=\"products\">");

                                    for (Product product: category.getProducts()){
                                        out.println("<div class=\"product\" id=\"" + product.getId() + "\">");
                                        out.println("<div class=\"logo\"><img src=" + product.getImgUrl() +
                                                " value=\"logoButton\" width=\"150px\"><div class=\"productName\">" +
                                                product.getProductName() + "</div></div><div class=\"infoProduct\">" +
                                                "<div class=\"characteristics\"><ul>");
                                        for (Map.Entry<String, String> charact: product.getCharacteristic().entrySet()){
                                            out.println("<li>" + charact.getKey() + ": " + charact.getValue() + "</li>");
                                        }
                                        out.println("</ul></div><div class=\"price\">" + product.getPrice() + "</div></div>" +
                                                "<img class=\"addToBasket\" value=\"В корзину\" src=\"/img/addToBasket.png\">" +
                                                "<img class=\"removeFromBasket\" value=\"В корзину\" src=\"/img/removeFromBasket.png\"></div>");
                                    }
                                    out.println("</div>");
                                }
                            %>


                        <%--</ul>--%>
                    <%--</div>--%>
                    <%--<div class="price">12000</div>--%>
                <%--</div>--%>

                <%--<img class="addToBasket" value="В корзину" src="img/addToBasket.png">--%>
                <%--<img class="removeFromBasket" value="В корзину" src="img/removeFromBasket.png">--%>

            <%--</div>--%>
        <%--</div>--%>
    </div>
    <div class="footer">
        <div>Java RD. Project "HTML, CSS, JS basics". Made by Petr Bunegin.</div>
    </div>
</body>

</html>