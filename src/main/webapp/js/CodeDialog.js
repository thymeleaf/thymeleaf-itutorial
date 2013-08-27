/**
 * Show server files (Java, i18n) inside a jQuery-UI Dialog.
 */
// FIXME: reuse dialog
function CodeDialog(CONTEXT_PATH) {
    
    var DIALOG_ID = 'code-editor';
    var editor, div, url, title, language;

    this.show = function(file) {
        if (div) {
            clearResources();
        }
        parseData(file);
        loadCode();
    }
    
    function parseData(file) {
        url = CONTEXT_PATH + 'resources/' + file;
        title = file.split('/')[1];
        language = file.split('.')[1];
    }
    
    function loadCode() {
        div = $('<div />');
        div.attr('id', DIALOG_ID);
        $(div).load(url, '', showDialog);
    }

    function showDialog() {
        $(div).dialog({
            width: '600',
            height: '500',
            title: title,
            close : clearResources
        });
        editor = new Editor(DIALOG_ID);
        editor.setLanguage(language);
        editor.makeReadOnly();
    }
    
    function clearResources() {
        editor.destroy();
        div.remove();
        div = null;
    }
}
