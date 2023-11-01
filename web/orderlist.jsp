<%-- Document : orderlist Created on : Jun 9, 2023, 8:51:58 AM Author : ASUS PC --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
            <link href="https://fonts.googleapis.com/css?family=Arvo&display=swap" rel="stylesheet">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
                integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
                crossorigin="anonymous">
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/account.css" />
            <style>
                html {
                    /* zoom: 0.9; */
                    /* Giảm kích thước phần tử xuống 80% */
                }

                .orderlist p,
                h5 {
                    margin-bottom: 5px;
                }

                .menu h6 {
                    margin-top: 17px;
                }

                .text p {
                    display: inline;
                }

                .total {
                    justify-content: flex-end;
                }

                .text .right {
                    margin-left: 50px;
                }
            </style>
            <title>JSP Page</title>
        </head>

        <body>
            <div class="card content-product">
                <div class="card-header" style="background-color: #FF9EA2;">
                    <h2 style="font-family: 'Poppins'; font-weight: revert; color: white; text-align: center;">Shopping
                        Cart</h2>
                </div>
                <div class="orderlist" style="padding: 15px 20px;">

                    <c:forEach items="${listOrders}" var="orders" varStatus="loopStatus">

                        <c:forEach items="${orders.getListOrderDetails()}" var="orderDetail"
                            varStatus="innerLoopStatus">
                            <div class="row" style="margin-top: 10px; margin-bottom: 10px;">
                                <div class="col-md-8 product" style="padding: 0;">
                                    <div class="img">
                                        <img src="${orderDetail.getProduct().image}" alt="">
                                    </div>
                                    <div class="content">
                                        <h5 class="mt-0">${orderDetail.getProduct().name}</h5>
                                        <p>${orderDetail.getProduct().description}</p>
                                        <p class="mb-0">product code: ${orderDetail.getProduct().productID}</p>
                                    </div>
                                </div>

                                <div class="col-md-4 second-product total" style="padding: 0;">
                                    <div class="text">
                                        <div>
                                            <p>${orderDetail.quantity} Pcs</p>
                                            <p class="right">${orderDetail.getProduct().price}.000 ₫</p><br>
                                        </div>
                                        <div style="margin-top: 25px;">
                                            <p>total: </p>
                                            <p class="right">${orderDetail.quantity * orderDetail.getProduct().price}
                                                .000 ₫</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${!innerLoopStatus.last}">
                                <div
                                    style="background-color: rgb(226, 226, 226); margin-top: 20px; margin-bottom: 20px; padding: 0.3px 0px;">
                                </div>
                            </c:if>
                            <c:if test="${innerLoopStatus.last}">
                                <div
                                    style="background-color: rgb(226, 226, 226); margin-top: 20px; margin-bottom: 20px; padding: 0.1px 0px;">
                                </div>
                                <div class="row" style="margin-top: 10px; margin-bottom: 10px;">
                                    <div class="col-md-8 product" style="padding: 0;">
                                        <div class="content">
                                            <p>Order Date: ${orders.getMonth()}</p>
                                            <c:if test="${orders.status == 0}">
                                                <a href="cancelorder?orderID=${orders.orderID}"><input
                                                        class="btn btn-danger" style="margin-top: 8px;" type="submit"
                                                        value="Cancel order"></a>

                                            </c:if>
                                        </div>
                                    </div>

                                    <div class="col-md-4 second-product total" style="padding: 0;">
                                        <div class="text">
                                            <div>
                                                <p>Status: </p>
                                                <c:if test="${orders.status == -1}">
                                                    <p class="right" style="color: red;">canceled</p><br>
                                                </c:if>
                                                <c:if test="${orders.status == 0}">
                                                    <p class="right" style="color: orange;">Pending</p><br>
                                                </c:if>
                                                <c:if test="${orders.status == 1}">
                                                    <p class="right" style="color: green;">Completed</p><br>
                                                </c:if>
                                            </div>
                                            <div style="margin-top:5px;">
                                                <p style="padding-right: 37px;">Delivery:</p>
                                                <p class="right">0 ₫</p><br>
                                            </div>
                                            <div style="margin-top:5px;">
                                                <p style="margin-left: 30px;">Total: </p>
                                                <p class="right">${orders.getTotalPriceOrder()}.000 ₫</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                        <c:if test="${!loopStatus.last}">
                            <div
                                style="background-color: rgb(121, 121, 121); margin-top: 20px; margin-bottom: 20px; padding: 0.3px 0px;">
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </body>

        </html>