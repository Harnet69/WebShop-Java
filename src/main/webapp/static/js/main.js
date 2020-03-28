let productsInCart = new Map();
let prodInCartSimple = [];

if (sessionStorage.getItem("CartData")) {
    prodsId = sessionStorage.getItem("CartData").split(',');
    for (i = 0; i < prodsId.length; i++) {
        prodInCartSimple.push(prodsId[i]);
    }
}

if (document.getElementsByClassName("quantity")[0]) {
    document.getElementsByClassName("quantity")[0].textContent = prodInCartSimple.length;
}


function mySubmit(theForm) {
    $.ajax({ // create an AJAX call...
        data: $(theForm).serialize(), // get the form data
        type: $(theForm).attr('method'), // GET or POST
        url: $(theForm).attr('action'), // the file to call
        success: function (response) { // on success..
            $('#products').html(response); // update the DIV
            addListenerToButton();
        }
    });
}

function goToCart() {
    let cartBtn = document.getElementById('headerlogoimg');
    if (cartBtn != null) {
        cartBtn.addEventListener("mouseover", function cartListener() {
            // let s = serialize(productsInCart); // work with Map
            if (prodInCartSimple.length > 0) {
                cartBtn.style.cursor = "pointer";
                showCartPreview();
            }
            // window.location = "cart"+mapToString();
        });
        cartBtn.addEventListener("mouseleave", function () {
            if (document.getElementById('cartPreview')) {
                document.getElementById('cartPreview').remove();
            }
        });

        cartBtn.addEventListener("click", function () {
            // showCartPreview();
            // document.getElementById('cartPreview').removeAttribute('id');
            if (prodInCartSimple.length > 0) {
                sessionStorage.SessionName = "CartData";
                sessionStorage.setItem("CartData", prodInCartSimple.toString());
                window.location.href = "cart" + mapToString();
            }
            // sessionStorage.getItem("CartData");

        });
    }
}

function showCartPreview() {
    let s = prodInCartSimple.toString();  // work with Array
    $.ajax({ // create an AJAX call...
        data: {"data": s}, // get the form data
        type: "GET", // GET or POST
        url: "cart-preview", // the file to call
        success: function (response) { // on success..
            let body = document.getElementsByClassName("headerlogo")[0];
            if (document.getElementById('cartPreview')) {
                document.getElementById('cartPreview').remove();
            }
            let cartDiv = document.createElement("div");
            cartDiv.className = "cartPreview";
            cartDiv.setAttribute('id', "cartPreview");
            body.appendChild(cartDiv);
            $('#cartPreview').html(response); // update the DIV
        }
    });
}

function serialize(map) {
    return JSON.stringify([...map.entries()])
}

function mapToString() {
    // let stringOfProduct = '?';
    // stringOfProduct += "qttOfProdTypes=" + prodInCartSimple.length + "&";
    // for (let [key, value] of productsInCart) {
    //     stringOfProduct += key + "=" + value + "&";
    // }
    // return stringOfProduct.substring(0, stringOfProduct.length - 1);
    return "?data=" + prodInCartSimple.toString();

}

function changeCartURL(addProduct) {
    let cart = document.getElementById("goToCart");
    cart.textContent += addProduct;
}


function addListenerToButton() {
    let submitBtns = document.getElementsByClassName("submit");
    productCounterLabel = document.getElementsByClassName("quantity")[0];
    for (let button of submitBtns) {
        button.addEventListener("click", function () {
            addProductsToCart(button);
        })
    }
}

function addProductsToCart(button) {
    let prodId = parseInt(button.getAttribute("data"));
    if (productsInCart.has(prodId)) {
        productsInCart.set(prodId, productsInCart.get(prodId) + 1);
    } else {
        productsInCart.set(prodId, 1);
    }
    prodInCartSimple.push(prodId);
    productCounterLabel.textContent = prodInCartSimple.length;
    console.log(productsInCart);
}

function calcQuantityProdInCart() {
    let qttInCart = 0;
    for (let [key, value] of productsInCart) {
        qttInCart += value;
    }
    return qttInCart;
}

function cartDelete() {
    if (document.getElementById("cartDelete")) {
        let cartDelBtn = document.getElementById("cartDelete");
        cartDelBtn.style.cursor = "pointer";
        cartDelBtn.addEventListener("click", function () {
            let answer = window.confirm("Delete the cart?");
            if (answer) {
                sessionStorage.removeItem("CartData");
                window.location.href = "/"
            }
        });
        cartDelBtn.addEventListener("mouseover", function () {
            cartDelBtn.style.opacity = "1";
        });
        cartDelBtn.addEventListener("mouseleave", function () {
            cartDelBtn.style.opacity = "0.5";
        });

    }
}

function itemChangeQttOfProd() {
    let prodQttInCartLabels = document.getElementsByClassName("prodQttInCart");
    for (let label of prodQttInCartLabels) {
        label.addEventListener("input", function (evt) {
            changeQttOfItemInQueryString(label);
        });
        $(label).keyup(function (event) {
            if (event.keyCode === 13) {
                changeQttOfItemInQueryString(label);
            }
        });
    }
}

// change quantity of the product in a cart
function changeQttOfItemInQueryString(label) {
    let queryString = sessionStorage.getItem("CartData");
    let prodIdForChange = label.getAttribute("data");
    let prodQtt = parseInt(label.value);
    let newString = [];

    //count accuracy of the prod id in a  cart
    let prodQttInCart = countProdQttInCart(queryString, prodIdForChange);

    if (prodQtt > 0 && prodQtt !== prodQttInCart) {
        if(prodQtt > prodQttInCart){
            for(let i = 0; i < prodQtt - prodQttInCart; i++){
                newString.push(prodIdForChange);
                prodInCartSimple = prodInCartSimple.concat(newString);
            }
        }else{
            let qttForDecr = prodQttInCart - prodQtt;
            let arrAfterRemProd = [];
            for (let num of prodInCartSimple) {
                if (num === prodIdForChange && qttForDecr > 0) {
                    qttForDecr --;
                }else{
                    arrAfterRemProd.push(parseInt(num));
                }
            }
            console.log(arrAfterRemProd);
            prodInCartSimple = arrAfterRemProd;
        }

        sessionStorage.setItem("CartData", prodInCartSimple);

        window.location.href = "cart" + mapToString();
    }
}

//count accuracy of the prod id in a  cart
function countProdQttInCart(str, prodId) {
    return countOfProdId = str.split(',')
        .filter(function (n) {
            return n === prodId
        })
        .length;
}

function itemDeleteBtn() {
    if (document.getElementsByClassName("delItmBtn")[0]) {
        let delItemBtns = document.getElementsByClassName("delItmBtn");
        for (let btn of delItemBtns) {
            btn.style.cursor = "pointer";
            btn.addEventListener("click", function () {
                deleteItemsFromQueryString(btn);
            })
        }
    }
}

function deleteItemsFromQueryString(btn) {
    let queryStringArr = sessionStorage.getItem("CartData").split(",");
    let newString = [];
    let prodIdForDel = btn.getAttribute("data");
    for (let num of queryStringArr) {
        if (num !== prodIdForDel) {
            newString.push(parseInt(num));
        }
    }
    sessionStorage.setItem("CartData", newString);
    prodInCartSimple = newString;

    if (prodInCartSimple.length > 0) {
        window.location.href = "cart" + mapToString();
    } else {
        window.location.href = "/";
    }

    // console.log("Delete! "+btn.getAttribute("data"));
    // console.log(sessionStorage.getItem("CartData"));
    // s = sessionStorage.getItem("CartData").replace(btn.getAttribute("data"), '');
    // console.log(s);
}

goToCart();
addListenerToButton();
cartDelete();
itemDeleteBtn();
itemChangeQttOfProd();