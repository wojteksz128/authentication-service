$('#modal-create').on('show.bs.modal', function(e) {
    $(this).find('.modal-content').draggable({
        handle: '.modal-header'
    });
});