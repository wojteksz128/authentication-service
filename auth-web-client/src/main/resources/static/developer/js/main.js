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
    $('#dev-apps-content').replaceWith($(response).filter('#dev-apps-content'));
    $('#message-container').replaceWith($(response).filter('#message-container'));
}