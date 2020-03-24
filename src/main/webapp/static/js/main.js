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
    for(i=0; i<submitBtns.length; i++){
        submitBtns[i].addEventListener("click", function () {
            console.log("push!");
            productCounterLabel.textContent = parseInt(productCounterLabel.textContent)+1;
        })
    }
}
addListenerToButton();