/**
 * Shows the solution to an exercise.
 */
function Solution(CONTEXT_PATH, EXERCISE, EDITOR, callback) {

    this.apply = function() {
        var url = CONTEXT_PATH + 'showSolution/' + EXERCISE;
        $.ajax({
            url: url,
            dataType: 'html',
            success : function(code) {
                EDITOR.setCode(code);
                callback();
            }
        });
    }
}
