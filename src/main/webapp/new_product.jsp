<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<c:url value="/styles.css"/>">
</head>
<body>
    <br>
    <div class="container">
        <div class="card">
            <h5 class="card-header">
                New Product
            </h5>
            <br>
            <div class="card-body">
                <form action="/products" method="post">
                    <input name="id" type="hidden" value="${updProduct.id}">
                    <div class="row">
                        <div class="form-group offset-lg-2 col-lg-4 offset-md-2 col-md-4">
                            <label for="title">Title:</label>
                            <input name="title" type="text" class="form-control" id="title" value="${updProduct.title}">
                        </div>
                        <div class="form-group col-lg-4 col-md-4">
                            <label for="price">Price:</label>
                            <input name="price" type="number" class="form-control" id="price" value="${updProduct.price}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group offset-lg-2 col-lg-8">
                            <label for="category">Category:</label>
                            <select name="category" class="form-control" id="category">
                                <option <c:if test="${updProduct.category == 'Phones'}">selected</c:if>>Phones</option>
                                <option <c:if test="${updProduct.category == 'Laptops'}">selected</c:if>>Laptops</option>
                                <option <c:if test="${updProduct.category == 'Tablets'}">selected</c:if>>Tablets</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group offset-lg-2 col-lg-8">
                            <label for="description">Description: </label>
                            <textarea name="description" class="form-control" id="description" rows="4">${updProduct.description}</textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-lg-12 text-center">
                            <c:if test="${empty updProduct}">
                                <button type="submit" class="btn btn-success">Create</button>
                            </c:if>
                            <c:if test="${not empty updProduct}">
                                <button type="submit" class="btn btn-warning">Update</button>
                            </c:if>
                            <button type="button" class="btn btn-danger">Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>
</html>
