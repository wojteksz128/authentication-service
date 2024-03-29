$('#modal-create').on('show.bs.modal', function () {
    $(this).find('.modal-content').draggable({
        handle: '.modal-header'
    });
}).modal("show");

$('#modal-create .btn.btn-success.btn-ok').click(function () {
    $.post('/devApp', $('#modal-create form').serialize()).done(function (response) {
        if ($($.parseHTML(response)).hasClass('modal')) {
            replaceModalBody('modal-create', response);
        } else {
            $('#modal-create').modal("hide");
            updateContent($.parseHTML(response));
        }
    })
});