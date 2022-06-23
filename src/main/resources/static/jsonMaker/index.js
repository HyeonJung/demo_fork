
function tutorial() {
    try {
        $.ajax({
            type : 'get',
            url : '/api/nft/makeImage',
            error: function(xhr, status, error){
                alert(status);
            },
            success : function(){
                console.log("complete!");
            }
        });
    } catch (error) {
        console.log(error);
    }
}