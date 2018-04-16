$('#modal-delete-question').on('show.bs.modal', function(e) {
    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    $(this).find('.modal-content').draggable({
        handle: '.modal-header'
    });
}).modal("show");