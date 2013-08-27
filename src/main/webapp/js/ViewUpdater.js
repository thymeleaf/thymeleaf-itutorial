/**
 * Manage refreshing of views.
 */
function ViewUpdater(EDITOR, GENERATED_SOURCE, staticViewId, dynamicViewId, CONTEXT_PATH) {
    
    this.updateAll = function () {
        var code = EDITOR.getCode();
        var locale = $('#locale').val();
        showStaticView(code, locale);
        showGeneratedSource(code, locale);
        showDynamicView(code, locale);
    }

    function showStaticView(code, locale) {
        postCodeToIframe(code, locale, CONTEXT_PATH + 'e/c/h/o', staticViewId);
    }

    function showGeneratedSource(code, locale) {
        var url = CONTEXT_PATH + 'generatedSource';
        var data = { 'code' : code, 'locale' : locale }
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

    function showDynamicView(code, locale) {
        postCodeToIframe(code, locale, CONTEXT_PATH + 'dynamicView', dynamicViewId);
    }
    
    function postCodeToIframe(code, locale, url, iframeId) {
        var frameName = iframeId;
        var frameRef = '#' + iframeId;
        var form = $('<form />', {
            action : url,
            method : 'post',
            target : frameName
        });
        var codeInput = $('<input />', {
            type : 'hidden',
            name : 'code',
            value : code
        });
        form.append(codeInput);
        var localeInput = $('<input />', {
            type : 'hidden',
            name : 'locale',
            value : locale
        });
        form.append(localeInput);
        form.appendTo(frameRef);
        form.submit();
    }
}
