package cn.wanyj.mcp.server.domain.service;

import cn.wanyj.mcp.server.domain.adapter.ICSDNArticlePort;
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
public class CSDNArticleService {

    @Resource
    private ICSDNArticlePort port;

    @Tool(description = "发布文章到CSDN")
    public ArticleFunctionResponse saveArticleOfCsdn(ArticleFunctionRequest request) throws IOException {
        log.info("CSDN发帖，标题:{} 内容:{} 标签:{}", request.getTitle(), request.getMarkdowncontent(), request.getTags());
        return port.writeArticleOfCsdn(request);
    }

}

