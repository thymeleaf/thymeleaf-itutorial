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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class AmountFormatter implements Formatter<Amount> {

    private final String pattern = "$ #,##0.00";
    
    @Override
    public String print(final Amount amount, final Locale locale) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(amount.getAmount());
    }

    @Override
    public Amount parse(final String string, final Locale locale) throws ParseException {
        DecimalFormat df = new DecimalFormat(pattern);
        BigDecimal amount = new BigDecimal(df.parse(string).doubleValue());
        return new Amount(amount);
    }
}
