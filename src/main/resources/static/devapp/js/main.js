var modal_lv = 0;

function copyToClipboard(text) {
    var textArea = document.createElement( "textarea" );
    textArea.value = text;
    document.body.appendChild( textArea );
 
    textArea.select();

    var returned = false;
 
    try {
       returned = document.execCommand( 'copy' );
       var msg = returned ? 'successful' : 'unsuccessful';
       console.log('Copying text command was ' + msg);
    } catch (err) {
       console.log('Oops, unable to copy');
    }
 
    document.body.removeChild( textArea );
    return returned;
 }

 function showPopover(obj) {
    obj.popover('show');
    setTimeout(function () {
        obj.popover('hide');
    }, 1000);
 }

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
        for (let i = 0; i < modal_lv; i++) {
            $('.modal-backdrop.fade.in:eq(' + i + ')').css('zIndex', 1049 + (i * 2));
        }
});

$('.btn-copy-key').popover({
    content: "Klucz aplikacji został skopiowany.",
    trigger: 'manual',
    placement: 'bottom'
}).click(function (obj) {
    var jObj = $(obj.target);

    if (copyToClipboard(jObj.parent().parent().children("input").val()))
        showPopover(jObj);
});

//$('[data-toggle="popover"]').popover();

$('#modal-delete-question').on('show.bs.modal', function(e) {
    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    $(this).find('.modal-content').draggable({
        handle: '.modal-header'
    });
});

$('#modal-create').on('show.bs.modal', function(e) {
    $(this).find('.modal-content').draggable({
        handle: '.modal-header'
    });
});

$('#modal-info').on('show.bs.modal', function(e) {
    $(this).find('.modal-content').draggable({
        handle: '.modal-header'
    });
});