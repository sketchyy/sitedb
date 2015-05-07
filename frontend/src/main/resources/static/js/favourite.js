function fav() {
    var params = {
        site: $('#siteId').html()
    };

    if ($('#isFav').html() === 'true') {
        // Already in favs, so delete
        $.ajax({
            url: "/favourites?" + $.param(params),
            type: 'DELETE',
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
            url: "/favourites?" + $.param(params),
            type: 'PUT',
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