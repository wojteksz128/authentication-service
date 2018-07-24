$('#modal-delete').on('show.bs.modal', function () {
    $(this).find('.modal-content').draggable({
        handle: '.modal-header'
    });
}).modal("show");

$('#modal-delete .btn.btn-ok').click(function () {
    var form = $('#modal-delete form');
    $.post(form.attr('action'), form.serialize()).done(function (response) {
        if ($($.parseHTML(response)).hasClass('modal')) {
            replaceModalBody('modal-delete', response);
        } else {
            $('.modal').modal("hide");
            updateContent($.parseHTML(response));
        }
    });
});