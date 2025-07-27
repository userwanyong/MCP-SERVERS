package cn.wanyj.mcp.server.test;

import com.alibaba.fastjson.JSON;
import cn.wanyj.mcp.server.domain.model.ArticleFunctionRequest;
import cn.wanyj.mcp.server.domain.model.ArticleFunctionResponse;
import cn.wanyj.mcp.server.domain.service.JueJinArticleService;
import cn.wanyj.mcp.server.infrastructure.gateway.IJueJinService;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.JueJinCreateRequest;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.JueJinCreateResponse;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.JueJinPublishRequest;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.JueJinPublishResponse;
import cn.wanyj.mcp.server.type.properties.JueJinApiProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 掘金 API测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class APITest {

    @Autowired
    private IJueJinService jueJinService;

    @Resource
    private JueJinArticleService jueJinArticleService;

    @Resource
    private JueJinApiProperties juejinApiProperties;

    /**
     * 创建文章
     *
     * @throws IOException
     */
    @Test
    public void testCreateArticle() throws IOException {
        // 准备请求数据
        JueJinCreateRequest request = new JueJinCreateRequest();
        request.setCategory_id("6809637769959178254"); // 后端
        request.setTag_ids(Arrays.asList("6809640408797167623")); // Java
        request.setLink_url("");
        request.setCover_image("");
        request.setTitle("Java开发实践：掘金API集成测试");
        request.setBrief_content("本文介绍了如何使用Java集成掘金API进行文章发布本文介绍了如何使用Java集成掘金API进行文章发布");
        request.setEdit_type(10); // Markdown编辑模式
        request.setHtml_content("deprecated");
        request.setMark_content("hello");
        request.setTheme_ids(new ArrayList<>());
        request.setPics(new ArrayList<>());

        // 设置Cookie和授权信息（实际测试时需要替换为真实值）
        String cookie = juejinApiProperties.getCookie();
        String authorization = juejinApiProperties.getAuthorization();

        // 调用API
        Call<JueJinCreateResponse> call = jueJinService.createArticle(cookie, authorization, request);
        log.info("Sending request to JueJin API: {}", request);

        // 执行请求并验证响应
        // 注意：由于没有真实的Cookie，这里的测试会失败，仅作为示例
        Response<JueJinCreateResponse> response = call.execute();
        log.info("Received response from JueJin API: {}", response.body());

        // 验证响应
        if (response.isSuccessful() && response.body() != null) {
            JueJinCreateResponse responseBody = response.body();
            assertEquals(0, responseBody.getErr_no().intValue());
            assertEquals("success", responseBody.getErr_msg());
            assertNotNull(responseBody.getData());
            assertNotNull(responseBody.getData().getId());
            System.out.println(responseBody.getData().getId());
        } else {
            log.error("API call failed: {}", response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        }
    }

    /**
     * 发布文章
     *
     * @throws IOException
     */
    @Test
    public void testPublishArticle() throws IOException {
        // 准备请求数据
        JueJinPublishRequest request = new JueJinPublishRequest();
        request.setDraft_id("7490003622233489435"); // 草稿ID，实际测试时需要替换为真实值
        request.setSync_to_org(false);
        request.setColumn_ids(new ArrayList<>());
        request.setTheme_ids(new ArrayList<>());
        request.setEncrypted_word_count(1077885);
        request.setOrigin_word_count(1);

        // 设置Cookie和授权信息（实际测试时需要替换为真实值）
        String cookie = juejinApiProperties.getCookie();
        String authorization = juejinApiProperties.getAuthorization();

        // 调用API
        Call<JueJinPublishResponse> call = jueJinService.publishArticle(cookie, authorization, request);
        log.info("Sending publish request to JueJin API: {}", request);

        // 执行请求并验证响应
        // 注意：由于没有真实的Cookie和草稿ID，这里的测试会失败，仅作为示例
        Response<JueJinPublishResponse> response = call.execute();
        log.info("Received response from JueJin API: {}", response.body());

        // 验证响应
        if (response.isSuccessful() && response.body() != null) {
            JueJinPublishResponse responseBody = response.body();
            assertEquals(0, responseBody.getErr_no().intValue());
            assertEquals("success", responseBody.getErr_msg());
            assertNotNull(responseBody.getData());
            assertNotNull(responseBody.getData().getArticle_id());
        } else {
            log.error("API call failed: {}", response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        }
    }

    @Test
    public void testSaveArticle_domain() throws IOException {
        String json = "{\"content\":\"<h2>场景：</h2>\\n<p>在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。</p>\\n<p><strong>面试官</strong>：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？</p>\\n<p><strong>程序员小张</strong>：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？</p>\\n<p><strong>面试官</strong>：嗯，第二个问题，请说说HashMap的工作原理。</p>\\n<p><strong>程序员小张</strong>：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……</p>\\n<p><strong>面试官</strong>：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？</p>\\n<p><strong>程序员小张</strong>：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……</p>\\n<p><strong>面试官</strong>：好，今天的问题就问到这里。回去等通知吧。</p>\\n<h2>答案解析：</h2>\\n<ol>\\n<li>\\n<p><strong>JVM内存管理</strong>：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。</p>\\n</li>\\n<li>\\n<p><strong>HashMap原理</strong>：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。</p>\\n</li>\\n<li>\\n<p><strong>Spring与SpringBoot区别</strong>：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。</p>\\n</li>\\n</ol>\\n\",\"cover_images\":[],\"cover_type\":0,\"description\":\"在互联网大厂的面试中，严肃的面试官与搞笑的程序员上演了一场精彩的对话。面试官提出Java核心知识、HashMap、Spring等问题，程序员则用幽默的方式作答。本文不仅展现了轻松的面试氛围，还附上了详细的技术问题答案解析，帮助读者更好地理解相关知识。\",\"is_new\":1,\"level\":\"0\",\"markdowncontent\":\"## 场景：\\n\\n在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。\\n\\n**面试官**：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？\\n\\n**程序员小张**：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？\\n\\n**面试官**：嗯，第二个问题，请说说HashMap的工作原理。\\n\\n**程序员小张**：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……\\n\\n**面试官**：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？\\n\\n**程序员小张**：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……\\n\\n**面试官**：好，今天的问题就问到这里。回去等通知吧。\\n\\n## 答案解析：\\n\\n1. **JVM内存管理**：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。\\n\\n2. **HashMap原理**：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。\\n\\n3. **Spring与SpringBoot区别**：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。\",\"not_auto_saved\":\"0\",\"pubStatus\":\"draft\",\"readType\":\"public\",\"resource_id\":\"\",\"resource_url\":\"\",\"source\":\"pc_mdeditor\",\"status\":0,\"sync_git_code\":0,\"tags\":\"Java,面试,互联网,程序员,Spring,SpringBoot,HashMap,JVM\",\"title\":\"互联网大厂Java面试：严肃面试官与搞笑程序员的对决\",\"vote_id\":0}";
        ArticleFunctionRequest request = JSON.parseObject(json, ArticleFunctionRequest.class);
        ArticleFunctionResponse response = jueJinArticleService.saveArticleOfJueJin(request);
        log.info("测试结果:{}", JSON.toJSONString(response));
    }
}