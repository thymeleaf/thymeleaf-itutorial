/**
 * Show server files (Java, i18n) inside a jQuery-UI Dialog.
 */
function CodeDialog(CONTEXT_PATH) {

    this.show = function(file) {
        // FIXME: reuse dialog
        var dialogId = 'codeDialog';
        var url = CONTEXT_PATH + 'resources/' + file;
        var title = file.split('/')[1];
        var language = file.split('.')[1];
        var editor;
        // Load URL contents into a new dialog
        var div = $('<div />');
        div.attr('id', dialogId);
        $(div).load(url, '', function() {
            $(div).dialog({
                width: "60%",
                height: "400",
                title: title,
                close : function()  {
                    editor.destroy();
                    div.remove();
                }
            });
            editor = new Editor(dialogId);
            editor.setLanguage(language);
            editor.makeReadOnly();
        });
    }
}
