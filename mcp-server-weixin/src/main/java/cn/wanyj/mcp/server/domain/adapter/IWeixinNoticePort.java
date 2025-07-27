package cn.wanyj.mcp.server.domain.adapter;


import cn.wanyj.mcp.server.domain.model.WeixinNoticeFunctionRequest;
import cn.wanyj.mcp.server.domain.model.WeixinNoticeFunctionResponse;

import java.io.IOException;

/**
 * @author 永
 */
public interface IWeixinNoticePort {
    WeixinNoticeFunctionResponse sendMsg(WeixinNoticeFunctionRequest request) throws IOException;
}
