function sendPut (score) {
    var params = {
        rate: score,
        rateId: $('#rateId').html()
    }
    $.ajax({
        url: "/rates?" + $.param(params),
        type: 'PUT',
        success: function(data) {
            $('#rateId').text(data.id);
            $('#rate').text(data.rate);
            alert('Load was performed.');
        }
    });
};

function sendPost (score) {
    var params = {
        rate: score,
        site: $('#siteId').html()
    }
    $.post("/rates?" + $.param(params),
        null,
        function(data, status){
            //if(status == '')
            $('#rateId').text(data.id);
            $('#rate').text(data.rate);
            alert("Data: " + data + "\nStatus: " + status);
        });
};