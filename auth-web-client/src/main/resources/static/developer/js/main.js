var modal_lv = 0;

$('body').on('show.bs.modal', function(e) {
    if (modal_lv > 0) {
        $('.modal-backdrop:last').css('zIndex', 1049 + (modal_lv++ * 2));
        $(e.target).css('zIndex', 1050 + (modal_lv * 2));
    }
    modal_lv++;
}).on('hidden.bs.modal', function() {
    if ( modal_lv > 0 )
        modal_lv--;
    if ( modal_lv > 0 )
        for (var i = 0; i < modal_lv; i++) {
            $('.modal-backdrop.fade.in:eq(' + i + ')').css('zIndex', 1049 + (i * 2));
        }
});

function copyToClipboard(textInput) {
    textInput.select();

    var returned = false;

    try {
        returned = document.execCommand( 'copy' );
        var msg = returned ? 'successful' : 'unsuccessful';
        console.log('Copying text command was ' + msg);
    } catch (err) {
        console.log('Oops, unable to copy');
    }

    return returned;
}

function openModal(url, modalHolderId, modalId) {
    $.ajax({
        url: url,
        success: function (data) {
            $(modalHolderId).html("");
            $(modalHolderId).html(data);
            $(modalId).modal("show");
        }
    })
}