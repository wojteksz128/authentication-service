function showPopover(obj) {
    obj.popover('show');
    setTimeout(function () {
        obj.popover('hide');
    }, 1000);
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