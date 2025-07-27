package cn.wanyj.mcp.server.test;

import com.alibaba.fastjson.JSON;
import cn.wanyj.mcp.server.domain.model.ArticleFunctionRequest;
import cn.wanyj.mcp.server.domain.model.ArticleFunctionResponse;
import cn.wanyj.mcp.server.domain.service.CSDNArticleService;
import cn.wanyj.mcp.server.infrastructure.gateway.ICSDNService;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.SaveArticleRequest;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.SaveArticleResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * CSDN API测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class APITest {

    @Autowired
    private ICSDNService csdnService;
    @Resource
    private CSDNArticleService csdnArticleService;
    @Value("csdn.api.cookie")
    private String cookie;

    /**
     * 测试保存文章接口
     */
    @Test
    public void testSaveArticle() throws IOException {
        // 构建请求参数
        SaveArticleRequest request = SaveArticleRequest.builder()
//                .article_id("147016478")
                .title("redis与mysql的区别")
                .description("redis与mysql的区别。")
                .content("<p>redis与mysql的区别</p>\n")
                .tags("vue.js")
//                .categories("")
//                .type("original")
//                .status(0)
//                .read_type("public")
//                .reason("")
//                .original_link("")
//                .authorized_status(false)
//                .check_original(false)
//                .source("pc_postedit")
//                .not_auto_saved(1)
//                .creator_activity_id("")
//                .cover_images(new ArrayList<>())
//                .cover_type(1)
//                .vote_id(0)
//                .resource_id("")
//                .scheduled_time(0)
//                .markdowncontent("")
//                .resource_url("")
//                .editor_type(0)
//                .plan(Collections.emptyList())
//                .level("0")
//                .is_new(1)
//                .sync_git_code(0)
                .build();

        // 调用接口
        Call<SaveArticleResponse> call = csdnService.saveArticle(
                cookie, request);

        Response<SaveArticleResponse> response = call.execute();

        // 打印响应结果
        if (response.isSuccessful() && response.body() != null) {
            SaveArticleResponse result = response.body();
            System.out.println("发布成功: " + result.getMsg());
            System.out.println("文章ID: " + result.getData().getArticle_id());
            System.out.println("文章URL: " + result.getData().getUrl());
        } else {
            System.out.println("发布失败: " + response.errorBody().string());
        }
    }


    @Test
    public void testSaveArticle_domain() throws IOException {
        String json = "{\"content\":\"<h2>场景：</h2>\\n<p>在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。</p>\\n<p><strong>面试官</strong>：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？</p>\\n<p><strong>程序员小张</strong>：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？</p>\\n<p><strong>面试官</strong>：嗯，第二个问题，请说说HashMap的工作原理。</p>\\n<p><strong>程序员小张</strong>：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……</p>\\n<p><strong>面试官</strong>：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？</p>\\n<p><strong>程序员小张</strong>：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……</p>\\n<p><strong>面试官</strong>：好，今天的问题就问到这里。回去等通知吧。</p>\\n<h2>答案解析：</h2>\\n<ol>\\n<li>\\n<p><strong>JVM内存管理</strong>：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。</p>\\n</li>\\n<li>\\n<p><strong>HashMap原理</strong>：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。</p>\\n</li>\\n<li>\\n<p><strong>Spring与SpringBoot区别</strong>：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。</p>\\n</li>\\n</ol>\\n\",\"cover_images\":[],\"cover_type\":0,\"description\":\"在互联网大厂的面试中，严肃的面试官与搞笑的程序员上演了一场精彩的对话。面试官提出Java核心知识、HashMap、Spring等问题，程序员则用幽默的方式作答。本文不仅展现了轻松的面试氛围，还附上了详细的技术问题答案解析，帮助读者更好地理解相关知识。\",\"is_new\":1,\"level\":\"0\",\"markdowncontent\":\"## 场景：\\n\\n在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。\\n\\n**面试官**：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？\\n\\n**程序员小张**：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？\\n\\n**面试官**：嗯，第二个问题，请说说HashMap的工作原理。\\n\\n**程序员小张**：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……\\n\\n**面试官**：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？\\n\\n**程序员小张**：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……\\n\\n**面试官**：好，今天的问题就问到这里。回去等通知吧。\\n\\n## 答案解析：\\n\\n1. **JVM内存管理**：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。\\n\\n2. **HashMap原理**：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。\\n\\n3. **Spring与SpringBoot区别**：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。\",\"not_auto_saved\":\"0\",\"pubStatus\":\"draft\",\"readType\":\"public\",\"resource_id\":\"\",\"resource_url\":\"\",\"source\":\"pc_mdeditor\",\"status\":0,\"sync_git_code\":0,\"tags\":\"Java,面试,互联网,程序员,Spring,SpringBoot,HashMap,JVM\",\"title\":\"互联网大厂Java面试：严肃面试官与搞笑程序员的对决\",\"vote_id\":0}";
        ArticleFunctionRequest request = JSON.parseObject(json, ArticleFunctionRequest.class);
        ArticleFunctionResponse response = csdnArticleService.saveArticleOfCsdn(request);
        log.info("测试结果:{}", JSON.toJSONString(response));
    }
}