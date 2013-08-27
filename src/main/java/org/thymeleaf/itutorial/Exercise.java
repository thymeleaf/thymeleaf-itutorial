/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2013, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.itutorial;

public enum Exercise {
    
    EXERCISE01("exercise01", "Exercise 1: bean values"),
    EXERCISE02("exercise02", "Exercise 2: simple formatting"),
    EXERCISE03("exercise03", "Exercise 3: string concatenation"),
    EXERCISE04("exercise04", "Exercise 4: internationalization"),
    EXERCISE05("exercise05", "Exercise 5: escaped and unescaped text"),
    EXERCISE06("exercise06", "Exercise 6: iteration"),
    EXERCISE07("exercise07", "Exercise 7: iteration stats"),
    EXERCISE08("exercise08", "Exercise 8: conditions"),
    EXERCISE09("exercise09", "Exercise 9: more on conditions"),
    EXERCISE10("exercise10", "Exercise 10: Spring expression language");
//    EXERCISE11("exercise11", "Exercise 11: links"),
//    EXERCISE12("exercise12", "Exercise 12: forms"),
//    EXERCISE13("exercise13", "Exercise 13: inlining"),
//    EXERCISE14("exercise14", "Exercise 14: fragments");

    private String path;
    private String description;

    private Exercise(final String path, final String description) {
        this.path = path;
        this.description = description;
    }

    /** Get Exercise with provided index. */
    public static Exercise get(final int index) {
        for (Exercise exercise : values()) {
            if (index == exercise.ordinal() + 1) {
                return exercise;
            }
        }
        throw new EnumConstantNotPresentException(Exercise.class, index + "");
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public int getIndex() {
        return ordinal() + 1;
    }

    /** Return previous exercise or null. */
    public Exercise getPrevious() {
        int index = getIndex();
        if (index > 1) {
            return Exercise.get(index - 1);
        }
        return null;
    }

    /** Return next exercise or null. */
    public Exercise getNext() {
        int nextIndex = getIndex() + 1;
        int size = values().length;
        if (nextIndex <= size) {
            return Exercise.get(nextIndex);
        }
        return null;
    }
}
