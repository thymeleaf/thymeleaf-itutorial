/**
 * Convenience object to manage easily ACE Editor instances.
 */
function Editor(elementId) {
    var EDITOR_FONT_SIZE = 14;
    var editor;

    init();
    
    function init() {
        editor = ace.edit(elementId);
        editor.getSession().setMode("ace/mode/html");
        editor.setFontSize(EDITOR_FONT_SIZE);
    }

    this.setCode = function(code) {
        editor.setValue(code);
        editor.clearSelection();
        editor.gotoLine(1);
        editor.scrollToRow(0);
    }
    
    this.setLanguage = function(language) {
        editor.getSession().setMode("ace/mode/" + language);
    }

    this.makeReadOnly = function() {
        editor.setReadOnly("true");
    }
    
    this.getCode = function() {
        return editor.getValue();
    }
    
    this.destroy = function() {
        editor.destroy();
    }
}
