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
package nats.client;

import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * Provides a default implementation of the {@link Message} interface.
 *
 * @author Mike Heath <elcapo@gmail.com>
 */
public class DefaultMessage implements Message {
    private final static Charset UTF8 = Charset.forName("utf-8");
	private final String subject;
	private final ByteBuf body;
	private final String queueGroup;
	private final boolean isRequest;

	public DefaultMessage(String subject, ByteBuf body, String queueGroup, boolean request) {
		this.subject = subject;
		this.body = body;
        body.markReaderIndex();
		this.queueGroup = queueGroup;
		isRequest = request;
	}

	@Override
	public boolean isRequest() {
		return isRequest;
	}

	@Override
	public String getSubject() {
		return subject;
	}

	public String getQueueGroup() {
		return queueGroup;
	}

	@Override
	public String getBodyAsString() {
        synchronized (body) {
            body.resetReaderIndex();
            return body.toString(UTF8);
        }
	}

    @Override public ByteBuffer getBodyAsBytes() {
        synchronized (body) {
            body.resetReaderIndex();
            return body.nioBuffer();
        }
    }

    @Override
	public void reply(String body) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void reply(String body, long delay, TimeUnit timeUnit) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "DefaultMessage{" +
				"subject='" + subject + '\'' +
				", body='" + body + '\'' +
				", queueGroup='" + queueGroup + '\'' +
				", isRequest=" + isRequest +
				'}';
	}
}
