/**
 * 
 */
$( document ).ready(function() {

    $.ajax({
        type: "GET",
        url: "formView",
        success: function(response) {
            $("#userformView").html( response );
        }
    });
});
