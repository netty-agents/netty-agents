// ---
// Copyright 2020 netty-agents team
// All rights reserved
// ---
package org.opentoolset.nettyagents;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

public class MessageDecoder extends ReplayingDecoder<MessageWrapper> {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		int length = in.readInt();
		String serializedMessageWrapper = in.readCharSequence(length, Constants.DEFAULT_CHARSET).toString();
		MessageWrapper messageWrapper = MessageWrapper.deserialize(serializedMessageWrapper);
		out.add(messageWrapper);
	}
}