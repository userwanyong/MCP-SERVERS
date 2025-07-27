package cn.wanyj.mcp.server.domain.service;


import cn.wanyj.mcp.server.domain.adapter.IWeixinNoticePort;
import cn.wanyj.mcp.server.domain.model.WeixinNoticeFunctionRequest;
import cn.wanyj.mcp.server.domain.model.WeixinNoticeFunctionResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author 永
 */
@Slf4j
@Service
public class WeixinNoticeService {
    @Resource
    private IWeixinNoticePort port;

    @Tool(description = "发送消息到微信1")
    public WeixinNoticeFunctionResponse sendMsg(WeixinNoticeFunctionRequest request) throws IOException {
        log.info("微信消息通知，平台:{} 主题:{} 描述:{} 跳转地址:{}", request.getPlatform(),request.getSubject(),request.getDescription(),request.getJumpUrl());
        return port.sendMsg(request);
    }
}
