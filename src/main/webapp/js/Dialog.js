/**
 * Convenience object to manage easily a jQuery-UI Dialect.
 */
function Dialog(elementId) {

    // Initialization
    var elementRef = '#' + elementId;
    $(elementRef).dialog({
        width: '40%',
        height: '400',
        dialogClass: 'dialog'
    });

    this.close = function() {
        $(elementRef).dialog('close');
    }

    this.toggle = function() {
        var isOpen = $(elementRef).dialog('isOpen')
        var toggle = isOpen ? 'close' : 'open';
        $(elementRef).dialog(toggle);
    }
}
