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

function openModal(url, modalId) {
    $('#' + modalId + '-holder').load(url);
}

function replaceModalBody(modalId, response) {
    $('#' + modalId + ' .modal-body').replaceWith($('.modal-body', $($.parseHTML(response))));
}