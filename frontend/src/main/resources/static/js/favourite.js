var USERS_HOST = "http://localhost:8083";

function fav() {
    var siteId = $('#siteId').html();
    var userId = $('#userId').html();
    if (userId === null) {
        return;
    }

    var params = {
        site: siteId,
        user: userId
    };

    if ($('#isFav').html() === 'true') {

        // Already in favs, so delete
        $.ajax({
            url: USERS_HOST + "/favourites?" /*+ siteId*/ + $.param(params),
            type: 'DELETE',
            xhrFields: {
                withCredentials: true
            },
            success: function () {
                // Styling
                $('#isFav').html('false');
                $('#favButton').html('Favourite!');
                $('#siteName').find('span').css("color", "black");
            },
            fail: function () {
                alert('Cannot unfavourite, server error!')
            }
        });
    } else {
        // Not in favs, so put
        $.ajax({
            url: USERS_HOST + "/favourites?" + $.param(params),
            type: 'PUT',
            xhrFields: {
                withCredentials: true
            },
            success: function () {
                // Styling
                $('#isFav').html('true');
                $('#favButton').html('Unfavourite!');
                $('#siteName').find('span').css("color", "green");
            },
            fail: function () {
                alert('Cannot favourite, server error!')
            }
        });
    }
}