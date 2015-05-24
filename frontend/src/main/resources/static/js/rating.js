var USERS_HOST = "http://localhost:8083";

function sendPutRating(score) {
    var params = {
        rate: score,
        rateId: $('#rateId').html()
    };

    $.ajax({
        url: USERS_HOST + "/rates?" + $.param(params),
        type: 'PUT',
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            $('#rateId').text(data.id);
            $('#rate').text(data.rate);
            getAverageRating();
            //alert('Load was performed.');
        },
        error: function () {
            alert("Error! Can't update rate");
        }
    });
}

function sendPostRating(score) {
    var params = {
        rate: score,
        site: $('#siteId').html(),
        user: $('#userId').html()
    };

    $.ajax({
        url: USERS_HOST + "/rates?" + $.param(params),
        type: 'POST',
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            $('#rateId').text(data.id);
            $('#rate').text(data.rate);
            getAverageRating();
            //alert('Load was performed.');
        },
        error: function () {
            alert("Error! Can't update rate");
        }
    });

    //$.post( USERS_HOST + "/rates?" + $.param(params),
    //    null,
    //    function (data) {
    //        //if(status == '')
    //        $('#rateId').text(data.id);
    //        $('#rate').text(data.rate);
    //        getAverageRating();
    //    })
    //    .fail(
    //    function() {
    //        alert("Error! Can't update rate")
    //    }
    //);
}

function getAverageRating() {
    var params = {
        site: $('#siteId').html()
    };

    $.get( USERS_HOST + "/avgrating?" + $.param(params),
        function (data) {
            $('#avgRate').html("avg = " + data.avg + " count = " + data.cnt);
        })
        .fail(
        function () {
            alert("Error! Can't get average rate")
        }
    );
}

//$.get( "ajax/test.html", function( data ) {
//    $( ".result" ).html( data );
//    alert( "Load was performed." );
//});