# stdio方式使用
先本地install一下，得到jar包的路径，配置相关信息即可调用
```json
{
  "mcpServers": {
    "filesystem": {
      "command": "npx", //windows改为npx.cmd
      "args": [
        "-y",
        "@modelcontextprotocol/server-filesystem",
        "D:/桌面",
        "D:/桌面"
      ]
    },
    "mcp-server-computer": {
      "command": "java",
      "args": [
        "-jar",
        "E:\\maven\\.m2\\repository\\cn\\wanyj\\mcp-server-computer\\1.0.0\\mcp-server-computer-1.0.0.jar" //改为你的jar包地址
      ]
    },
    "mcp-server-csdn": {
      "command": "java",
      "args": [
        "-Dfile.encoding=utf-8",
        "-jar",
        "E:\\maven\\.m2\\repository\\cn\\wanyj\\mcp-server-csdn\\1.0.0\\mcp-server-csdn-1.0.0.jar", //改为你的jar包地址
        "--csdn.api.categories=Java场景面试宝典", //专栏名
        "--csdn.api.cookie=uuid_tt_dd=xxxx" //csdn的cookie
      ]
    },
    "mcp-server-juejin": {
      "command": "java",
      "args": [
        "-Dfile.encoding=utf-8",
        "-jar",
        "E:\\maven\\.m2\\repository\\cn\\wanyj\\mcp-server-juejin\\1.0.0\\mcp-server-juejin-1.0.0.jar", //改为你的jar包地址
        "--juejin.api.categories=Java场景面试宝典", //专栏名
        "--juejin.api.cookie=csrf_session_id=xxxx" //掘金的cookie
      ]
    },
    "mcp-server-weixin": {
      "command": "java",
      "args": [
        "-Dfile.encoding=utf-8",
        "-jar",
        "E:\\maven\\.m2\\repository\\cn\\wanyj\\mcp-server-weixin\\1.0.0\\mcp-server-weixin-1.0.0.jar", //改为你的jar包地址
        "--weixin.api.original_id=xxx",
        "--weixin.api.app_id=xxx",
        "--weixin.api.app_secret=xxx",
        "--weixin.api.template_id=xxx",
        "--weixin.api.touser=xxx"
      ]
    }
  }
}
```