let productsInCart = new Map();
let prodInCartSimple = new Array();
// dict.set(foo, "Foo");
// dict.set(bar, "Bar");
function mySubmit(theForm) {
    $.ajax({ // create an AJAX call...
        data: $(theForm).serialize(), // get the form data
        type: $(theForm).attr('method'), // GET or POST
        url: $(theForm).attr('action'), // the file to call
        success: function (response) { // on success..
            $('#products').html(response); // update the DIV
        }
    });
}

function goToCart() {
    cartBtn = document.getElementById('headerlogoimg');
    if(cartBtn != null) {
        cartBtn.addEventListener("click", function () {
            console.log("go to cart");
            // console.log(prodInCartSimple);
            // let s = serialize(productsInCart); // work with Map
            let s = prodInCartSimple.toString();  // work with Array
            // console.log(s);
            $.ajax({ // create an AJAX call...
                data: {"data" : s}, // get the form data
                type: "GET", // GET or POST
                url: "cart", // the file to call
                success: function (response) { // on success..
                    body = document.getElementsByClassName("headerlogo")[0];
                    cartDiv = document.createElement("div");
                    cartDiv.className = "cartPreview";
                    cartDiv.setAttribute('id',"cartPreview");
                    // cartDiv.textContent = "Products in Cart";
                    body.appendChild(cartDiv);
                    $('#cartPreview').html(response); // update the DIV
                    // console.log("Success"); // update the DIV
                }
            });
            // window.location = "cart"+mapToString();
        })
    }
}

function serialize (map) {
    return JSON.stringify([...map.entries()])
}

function mapToString() {
    let stringOfProduct = '?';
    stringOfProduct += "qttOfProdTypes="+productsInCart.size+"&";
    for(let [key, value] of productsInCart){
        stringOfProduct += key+"="+value+"&";
    }
    return stringOfProduct.substring(0, stringOfProduct.length - 1);

}

function changeCartURL(addProduct) {
    let cart = document.getElementById("goToCart");
    cart.textContent +=addProduct;

}



function addListenerToButton(){
    submitBtns = document.getElementsByClassName("submit");
    productCounterLabel = document.getElementsByClassName("quantity")[0];
    for(let button of submitBtns){
        button.addEventListener("click", function () {
            addProductsToCart(button);
        })
    }
}

function addProductsToCart(button) {
    let prodId = parseInt(button.getAttribute("data"));
    if(productsInCart.has(prodId)){
        productsInCart.set(prodId, productsInCart.get(prodId)+ 1);
    }else{
        productsInCart.set(prodId, 1);
    }
    prodInCartSimple.push(prodId);
    productCounterLabel.textContent = calcQuantityProdInCart();
    console.log(productsInCart);
}

function calcQuantityProdInCart() {
    let qttInCart = 0;
    for(let [key, value] of productsInCart){
        qttInCart += value;
    }
    return qttInCart;
}
goToCart();
addListenerToButton();