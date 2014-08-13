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

import java.nio.charset.Charset;

/**
 * @author Mike Heath <elcapo@gmail.com>
 */
public class ServerPublishFrame implements ServerFrame {

    private final String id;
    private final String subject;
    private final String replyTo;
    private final ByteBuf body;

    public ServerPublishFrame(String id, String subject, String replyTo, ByteBuf body) {
        this.id = id;
        this.subject = subject;
        this.replyTo = replyTo;
        this.body = body;
        body.markReaderIndex();
    }

    public String getId() {
        return id;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public String getSubject() {
        return subject;
    }

    public ByteBuf getBody() {
        return body;
    }
}
