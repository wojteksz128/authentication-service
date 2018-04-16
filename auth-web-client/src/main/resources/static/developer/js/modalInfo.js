$('#modal-info').on('show.bs.modal', function() {
    $(this).find('.modal-content').draggable({
        handle: '.modal-header'
    });
}).modal("show");

$('#modal-info .btn.btn-success.btn-ok').click(function () {
    var form = $('#modal-info form');
    $.post(form.attr('action'), form.serialize()).done(function (response) {
        $('#modal-info').modal("hide");
        updateContent($.parseHTML(response));
    });
});