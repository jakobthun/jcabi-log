/**
 * Copyright (c) 2012-2017, jcabi.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the jcabi.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jcabi.log;

import java.io.StringWriter;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.WriterAppender;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for %L pattern.
 * If you change this class, you have to care about line number
 * in "com.jcabi.log.LineNumberTest:72"
 * @author Haris Peco (snpe60@gmail.com)
 * @version $Id$
 * @since 1.18
 */
public final class LineNumberTest {

    /**
     * Conversation pattern for test case.
     */
    private static final String CONV_PATTERN = "%c:%L";

    /**
     * PaternLayout can use %L properly.
     * @throws Exception If something goes wrong
     */
    @Test
    public void testLineNumber() throws Exception {
        final PatternLayout layout = new PatternLayout();
        layout.setConversionPattern(CONV_PATTERN);
        final org.apache.log4j.Logger root = LogManager.getRootLogger();
        final Level level = root.getLevel();
        root.setLevel(Level.INFO);
        final StringWriter writer = new StringWriter();
        final WriterAppender appender =
            new WriterAppender(layout, writer);
        root.addAppender(appender);
        try {
            Logger.info(this, "Test");
            TimeUnit.MILLISECONDS.sleep(1L);
            MatcherAssert.assertThat(
                writer.toString(),
                Matchers.containsString(
                    "com.jcabi.log.LineNumberTest:73"
                )
            );
        } finally {
            root.removeAppender(appender);
            root.setLevel(level);
        }
    }
}
