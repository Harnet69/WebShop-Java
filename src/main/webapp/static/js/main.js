var productsInCart = new Map();
// dict.set(foo, "Foo");
// dict.set(bar, "Bar");
function mySubmit(theForm) {
    $.ajax({ // create an AJAX call...
        data: $(theForm).serialize(), // get the form data
        type: $(theForm).attr('method'), // GET or POST
        url: $(theForm).attr('action'), // the file to call
        success: function (response) { // on success..
            $('#products').html(response); // update the DIV
            console.log(response);
        }
    });
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
    console.log(calcQuantityProdInCart());
    if(productsInCart.has(prodId)){
        productsInCart.set(prodId, productsInCart.get(prodId)+ 1);
    }else{
        productsInCart.set(prodId, 1);
    }
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

addListenerToButton();