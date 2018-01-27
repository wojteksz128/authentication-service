$('#modal-info').on('show.bs.modal', function(e) {
    $(this).find('.modal-content').draggable({
        handle: '.modal-header'
    });
});

$(document).ready(function() {
    $('.btn-copy-key').popover({
        content: "Klucz aplikacji został skopiowany.",
        trigger: 'manual',
        placement: 'bottom'
    }).click(function (obj) {
        var jObj = $(obj.target);

        if (copyToClipboard(jObj.parent().parent().children("input")))
            showPopover(jObj);
    });
});