function copyToClipboard(textInput) {
    textInput.select();

    var returned = false;

    try {
        returned = document.execCommand('copy');
        var msg = returned ? 'successful' : 'unsuccessful';
        console.log('Copying text command was ' + msg);
    } catch (err) {
        console.log('Oops, unable to copy');
    }

    return returned;
}

function updateContent(response) {
    $('#page-content').replaceWith($(response).filter('#page-content'));
    $('#message-container').replaceWith($(response).filter('#message-container'));
}