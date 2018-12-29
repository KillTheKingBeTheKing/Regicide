package com.osrs.net.channel;

import com.osrs.net.NetworkConstants;
import com.osrs.net.PlayerSession;
import com.osrs.net.codec.LoginDecoder;
import com.osrs.net.codec.LoginEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Handles a channels events..
 *
 * @author Swiffy
 */
public class ChannelPipelineHandler extends ChannelInitializer<SocketChannel> {

    /**
     * The part of the pipeline that limits connections, and checks for any banned hosts.
     */
    private final ChannelFilter FILTER = new ChannelFilter();

    /**
     * The part of the pipeline that handles exceptions caught, channels being read, in-active
     * channels, and channel triggered events.
     */
    private final ChannelEventHandler HANDLER = new ChannelEventHandler();

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        final ChannelPipeline pipeline = channel.pipeline();

        channel.attr(NetworkConstants.SESSION_KEY).setIfAbsent(new PlayerSession(channel));

        pipeline.addLast("channel-filter", FILTER);
        pipeline.addLast("decoder", new LoginDecoder());
        pipeline.addLast("encoder", new LoginEncoder());
        pipeline.addLast("timeout", new IdleStateHandler(NetworkConstants.SESSION_TIMEOUT, 0, 0));
        pipeline.addLast("channel-handler", HANDLER);
    }
}
