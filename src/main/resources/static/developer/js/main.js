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

function openAppInfoModal(guid) {
    $.ajax({
        url: "/devApp/" + guid,
        success: function (data) {
            $('#modal-info-holder').html("");
            $('#modal-info-holder').html(data);
            $('#modal-info').modal("show");
        }
    });
}

function openNewAppModal() {
    $.ajax({
        url: "/devApp/new",
        success: function (data) {
            $('#modal-create-holder').html("");
            $('#modal-create-holder').html(data);
            $('#modal-create').modal("show");
        }
    });
}