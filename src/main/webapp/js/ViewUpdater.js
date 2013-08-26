/**
 * Manage refreshing of views.
 */
function ViewUpdater(EDITOR, GENERATED_SOURCE, staticViewId, dynamicViewId, CONTEXT_PATH) {
    
    this.updateAll = function () {
        var code = EDITOR.getCode();
        showStaticView(code);
        showDynamicView(code);
        showGeneratedSource(code);
    }

    function showStaticView(code) {
        postCodeToIframe(code, CONTEXT_PATH + 'e/c/h/o', staticViewId);
    }

    function showGeneratedSource(code) {
        var url = CONTEXT_PATH + 'generatedSource';
        var data = { 'code' : code }
        $.ajax({
            url: url,
            type: 'POST',
            data: data,
            dataType: 'html',
            success : setGeneratedSourceCode
        });
    }

    function setGeneratedSourceCode(code) {
        GENERATED_SOURCE.setCode(code);
    }

    function showDynamicView(code) {
        postCodeToIframe(code, CONTEXT_PATH + 'dynamicView', dynamicViewId);
    }
    
    function postCodeToIframe(code, url, iframeId) {
        var frameName = iframeId;
        var frameRef = '#' + iframeId;
        var form = $('<form />', {
            action : url,
            method : 'post',
            target : frameName
        });
        var input = $('<input />', {
            type : 'hidden',
            name : 'code',
            value : code
        });
        form.append(input);
        form.appendTo(frameRef);
        form.submit();
    }
}
