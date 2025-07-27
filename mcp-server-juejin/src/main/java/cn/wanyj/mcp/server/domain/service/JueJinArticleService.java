package cn.wanyj.mcp.server.domain.service;

import cn.wanyj.mcp.server.domain.adapter.IJueJinPort;
import cn.wanyj.mcp.server.domain.model.ArticleFunctionRequest;
import cn.wanyj.mcp.server.domain.model.ArticleFunctionResponse;
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
public class JueJinArticleService {

    @Resource
    private IJueJinPort port;

    @Tool(description = "发布文章到JueJin")
    public ArticleFunctionResponse saveArticleOfJueJin(ArticleFunctionRequest request) throws IOException {
        log.info("JueJin发帖，标题:{} 内容:{} 标签:{}", request.getTitle(), request.getMarkdowncontent(), request.getTags());
        return port.writeArticleOfJueJin(request);
    }

}

