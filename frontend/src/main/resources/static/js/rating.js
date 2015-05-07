function sendPutRating(score) {
    var params = {
        rate: score,
        rateId: $('#rateId').html()
    };
    $.ajax({
        url: "/rates?" + $.param(params),
        type: 'PUT',
        success: function (data) {
            $('#rateId').text(data.id);
            $('#rate').text(data.rate);
            getAverageRating();
            //alert('Load was performed.');
        }
    });
}
function sendPostRating(score) {
    var params = {
        rate: score,
        site: $('#siteId').html()
    };
    $.post("/rates?" + $.param(params),
        null,
        function (data, status) {
            //if(status == '')
            $('#rateId').text(data.id);
            $('#rate').text(data.rate);
            getAverageRating();
            //alert("Data: " + data + "\nStatus: " + status);
        });
}

function getAverageRating() {
    var params = {
        site: $('#siteId').html()
    };
    $.get("/avgrating?" + $.param(params),
        function (data) {
            $('#avgRate').html("avg = " + data.avg + " count = " + data.cnt);
        });
}


//$.get( "ajax/test.html", function( data ) {
//    $( ".result" ).html( data );
//    alert( "Load was performed." );
//});