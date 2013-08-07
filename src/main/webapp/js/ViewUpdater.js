/**
 * Manage refreshing of views.
 */
function ViewUpdater(EDITOR, GENERATED_SOURCE, staticViewId, dynamicViewId, CONTEXT_PATH) {
    
    this.updateAll = function () {
        var code = EDITOR.getEscapedCode();
        showStaticView(code);
        showDynamicView(code);
        showGeneratedSource(code);
    }

    function showStaticView(code) {
        var url = CONTEXT_PATH + 'e/c/h/o?code=' + code;
        document.getElementById(staticViewId).src = url;
    }

    function showGeneratedSource(code) {
        var url = CONTEXT_PATH + 'generatedSource?code=' + code;
        $.ajax({
            url: url,
            dataType: 'html',
            success : setGeneratedSourceCode
        });
    }

    function setGeneratedSourceCode(code) {
        GENERATED_SOURCE.setCode(code);
    }

    function showDynamicView(code) {
        var url = CONTEXT_PATH + 'dynamicView?code=' + code;
        document.getElementById(dynamicViewId).src = url;
    }

}