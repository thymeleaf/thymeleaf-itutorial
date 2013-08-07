/**
 * Check for key strokes inside a text editor (maybe a textarea).
 * When the user is typing, it does nothing. When the user stop typing for
 * a while, executes a function.
 * @param String editorId Editor to be checked for key strokes.
 * @param Function action Function to be executed when the user stop typing.
 */
function StrokeDetector(editorId, action) {

    var CHECKING_INTERVAL = 1000; // 1 second
    var WAIT_FOR_ACTION_INTERVAL = 1000; // 1 second
    
    var lastStrokeTime, waitingAction;

    init();
    
    function init() {
        clearStroke(); // Initialize variables
        $('#' + editorId).keypress(storeStroke); // Every time a key is pressed store time
        setInterval(checkTypingEnd, CHECKING_INTERVAL); // Check if typing has ended regularly
    }
    
    function clearStroke() {
        lastStrokeTime = Number.MIN_VALUE;
        waitingAction = false;
    }
    
    function storeStroke() {
        waitingAction = true;
        lastStrokeTime = currentTime();
    }

    function checkTypingEnd() {
        if (waitingAction && typingHasEnded()) {
            clearStroke();
            action();
        }
    }

    function typingHasEnded() {
        var now = currentTime();
        var timeSinceLastStroke = now - lastStrokeTime;
        return timeSinceLastStroke > WAIT_FOR_ACTION_INTERVAL;
    }
}

function currentTime() {
    return new Date().getTime();
}
