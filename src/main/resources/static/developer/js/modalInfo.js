$('#modal-info').on('show.bs.modal', function(e) {
    $(this).find('.modal-content').draggable({
        handle: '.modal-header'
    });
});

function openDeleteAppModal(guid) {
    $.ajax({
        url: "/devApp/" + guid + "/delete",
        success: function (data) {
            $('#modal-delete-holder').replaceWith(data);
            $('#modal-delete-question').modal("show");
        }
    });
}