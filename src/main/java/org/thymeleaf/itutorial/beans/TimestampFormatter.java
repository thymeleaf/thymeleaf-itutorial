/*
 * Copyright 2013 The THYMELEAF team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.thymeleaf.itutorial.beans;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;

public class TimestampFormatter implements Formatter<Timestamp> {

    private final String pattern = "EEEE dd of MMMM, yyyy, at HH:mm";

    @Override
    public String print(final Timestamp time, final Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(time);
    }

    @Override
    public Timestamp parse(final String string, final Locale locale) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(string);
        return new Timestamp(date.getTime());
    }
}
