function showPopover(obj) {
    obj.popover('show');
    setTimeout(function () {
        obj.popover('hide');
    }, 1000);
}

function copyToClipboard(text) {
    var textArea = document.createElement( "textarea" );
    textArea.value = text;
    document.body.appendChild( textArea );

    textArea.select();

    var returned = false;

    try {
        returned = document.execCommand( 'copy' );
        var msg = returned ? 'successful' : 'unsuccessful';
        console.log('Copying text command was ' + msg);
    } catch (err) {
        console.log('Oops, unable to copy');
    }

    document.body.removeChild( textArea );
    return returned;
}

$(document).ready(function() {
    $('.btn-copy-key').popover({
        content: "Klucz aplikacji został skopiowany.",
        trigger: 'manual',
        placement: 'bottom'
    }).click(function (obj) {
        var jObj = $(obj.target);

        if (copyToClipboard(jObj.parent().parent().children("input").val()))
            showPopover(jObj);
    });
});
