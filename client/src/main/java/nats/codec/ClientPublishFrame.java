/*
 *   Copyright (c) 2013 Mike Heath.  All rights reserved.
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
 */
package nats.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @author Mike Heath <elcapo@gmail.com>
 */
public class ClientPublishFrame implements ClientFrame {
    private final Charset charset = Charset.forName("utf-8");
    private final String subject;

    private final String replyTo;

    private final ByteBuf body;

    public ClientPublishFrame(String subject, ByteBuf body, String replyTo) {
        // TODO Validate subject, replyTo What is valid?
        this.subject = subject;
        body.markReaderIndex();
        this.body = body;
        this.replyTo = replyTo;
    }

    public String getBody() {
        body.resetReaderIndex();
        return body.toString(charset);
    }

    public String getReplyTo() {
        return replyTo;
    }

    public String getSubject() {
        return subject;
    }

}
