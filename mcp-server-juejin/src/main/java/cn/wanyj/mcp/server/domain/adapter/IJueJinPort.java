package cn.wanyj.mcp.server.domain.adapter;

import cn.wanyj.mcp.server.domain.model.ArticleFunctionRequest;
import cn.wanyj.mcp.server.domain.model.ArticleFunctionResponse;

import java.io.IOException;

/**
 * @author 永
 */
public interface IJueJinPort {
    ArticleFunctionResponse writeArticleOfJueJin(ArticleFunctionRequest request) throws IOException;
}
