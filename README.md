# M-Volunteer-SpringBoot
该项目是**美志愿APP**的服务端源码。美志愿是一个公益平台APP，志愿者可以通过它来寻找感兴趣的志愿活动，交到更多志同道合的朋友。美志愿将传统繁杂的志愿活动做进手机，为传统的发布志愿活动、参与志愿项目、活动签到、工时统计、证书申请等带来便利，一切自动化完成，极大的降低了传统的人工劳动力。并在传统基础上融入社交元素，提供智能建群、圈子分享等新互联网元素。 

## 启动
> 注意：因为这是我第一次使用spring-boot进行项目开发，当时还没有了解到可以分环境配置不同的yml配置文件，所以在本项目中，我把涉及隐私的yml文件加入了gitignore，并复制了一份相同的 **yml.backup** ，但修改了数据库信息。你可以clone以后自建一个yml，并复制backup中的配置。另外静态资源目录也需要重新配置

首先，你应该要将这个代码库克隆到你的磁盘上，并进入此目录。启动命令行窗口，键入：

```
git clone https://github.com/oneone1995/M-Volunteer-SpringBoot
cd M-Volunteer-SpringBoot
```

然后，启动应用（初次启动的话，这个过程会需要稍微久一点的时间）。在命令行窗口中键入：

```
mvn spring-boot:run
```
你也可以使用ide的check out form version control直接clone项目并打开。

此时，你可以尝试发出一个 HTTP 请求。例如，利用浏览器向你的 Web 应用发出一个 HTTP GET 请求，在地址栏键入：

```
http://localhost:9090/swagger-ui.html
```
你将会看到该项目所有的api接口以及参数，但因为找不到在swagger中配置headers的方法，所以部分需要带上token访问的接口无法直接在swagger中直接测试。

## 细节

### 项目目录
	项目目录树：
		M-Volunteer:.
			└─src
				├─main
				│  ├─java
				│  │  └─com
				│  │      └─github
				│  │          └─oneone1995
				│  │              └─mvolunteer
				│  │                  ├─config 	
				│  │                  │  ├─aliim
				│  │                  │  ├─mvc
				│  │                  │  ├─result
				│  │                  │  ├─security
				│  │                  │  └─swagger
				│  │                  ├─domain	
				│  │                  ├─mapper
				│  │                  ├─model
				│  │                  ├─service
				│  │                  │  └─impl
				│  │                  ├─utils
				│  │                  └─web
				│  └─resources
				│      ├─lib
				│      ├─mapper
				│      └─static
				└─test
					└─…(& so on)
- ```src/main/java/com/github/oneone1995/mvolunteer/config```: 该目录放置了配置类，包下又根据配置的作用域分为多个文件夹，其子目录aliim放置与阿里云旺sdk相关的配置类，mvc目录下放置springMVC相关配置类，result目录下放置api的返回状态码配置类，security目录下放置spring-security的相关配置类，swagger目录下放置文档生成工具swagger-ui的配置相关配置类
- ```src/main/java/com/github/oneone1995/mvolunteer/domain```: 该目录放置了各种模型类与对应域
- ```src/main/java/com/github/oneone1995/mvolunteer/mapper```: 该目录放置了数据库增删改查的mybatis代理接口
- ```src/main/java/com/github/oneone1995/mvolunteer/service```: 该目录下放置服务（一个服务对应于一些业务逻辑的集合）的接口，其子目录 impl 放置了这些接口的实现类
- ```src/main/java/com/github/oneone1995/mvolunteer/util```: 该目录放置了各种工具类
- ```src/main/java/com/github/oneone1995/mvolunteer/web```: 该目录放置了和网络层相关的一切，包括控制器、过滤器等等
- ```src/main/resources/mapeer```: 该目录放置了 MyBatis 的映射器 XML 文件
- ```src/main/resources/lib```: 该目录放置了maven项目以外的lib库文件

### 集成 OAuth 2.0

为了使服务器的资源受到保护，也就是只让信任的客户端访问受保护的资源，本项目选择 Spring Security OAuth 来集成 OAuth 2.0 来保护我们服务器的资源。

只有了解了 OAuth 2.0 的运行流程，我们才能正确的使用它。所以，首先，我们先来了解一下 OAuth 2.0 的运行流程。它的运行流程见[RFC 6749](http://www.rfcreader.com/#rfc6749)。


OAuth 2.0 有4种授权方式，分别是：授权码模式（authorization code），简化模式（implicit），密码模式（resource owner password credentials）和客户端模式（client credentials），本项目只采用密码模式。

既然清楚了运行流程，那么接下来要进行的是对 Spring Security OAuth 的配置，涉及到这些的类有：

- ```com.github.oneone1995.mvolunteer.domain.CustomUserDetails```: 该类是一个模型类，继承自User，包装了角色信息的User扩展类，实现了 ```UserDetails```接口。它主要负责传送用户的认证信息，包括：用户名,密码,该用户所拥有的权限等等。只需要将关联查询查询出用户信息和角色信息结果映射到这个模型类即可。
- ```com.github.oneone1995.mvolunteer.config.security.AuthorizationServerConfig```: 该类是一个配置类，继承了 ```AuthorizationServerConfigurerAdapter```。它主要负责授权服务器的配置，包括：信任的客户端信息的管理、请求令牌的 URL 的配置、 令牌的管理、如何认证用户的配置、对于请求令牌的 URL 的安全约束的配置等等
- ```com.github.oneone1995.mvolunteer.config.security.ResourceServerConfig```: 该类是一个配置类，继承了 ```ResourceServerConfigurerAdapter```。他主要负责资源服务器的配置，包括：对于请求资源的 URL 的安全约束的配置等等
- ```com.github.oneone1995.mvolunteer.config.security.WebSecurityConfig```: 该类是一个配置类，继承了 ```GlobalAuthenticationConfigurerAdapter```。它主要负责有关认证的配置，包括：用户的认证信息的获取等等
- ```com.github.oneone1995.mvolunteer.service.UserService```: 该类是一个服务类的接口，继承了 ```UserDetailsService``` 接口。
- ```com.github.oneone1995.mvolunteer.impl.UserServiceImpl```: 该类是 ```UserService``` 接口的实现类。

有了这些配置，我们实现的效果是：

- 获取 book 资源（查）的请求一律不需要认证
- 对 book 资源进行修改的请求（增删改）需要认证
- 对 user 资源的所有请求（增删改查）都需要认证

#### 使用途径

首先，我们尝试访问不需要认证的资源：

```
curl -s "http://localhost:9090/api/activity?page=1&rows=10&coordLong=120&coordLat=30"
```

正如我们所期待的，服务器返回了一个活动列表资源给客户端，如下：

```
...
{
	"id": 9,
	"distance": 34,
	"district": "上城区",
	"recruitedPersonNumber": 0,
	"recruitPersonNumber": 30,
	"picture": "/public/img/f129776e-1b44-4f67-a526-79796c051a38.jpeg",
	"name": "志愿者提供志愿服务，包括活动前期准备"
}
...
```

接下来，我们尝试不带认证信息的访问需要认证的资源（尝试查看某个活动的活动详情，这个api涉及志愿者报名和志愿组织推荐，因此需要登录以后才可以）：

```
curl -s "http://localhost:9090/api/activity/1?coordLong=120&coordLat=30"
```

我们收到如下的 JSON 字符串响应，告诉我们需要认证了才能访问这个资源：

```
{
  "error": "unauthorized",
  "error_description": "Full authentication is required to access this resource"
}
```

为了访问受保护的资源，我们需要先向授权服务器请求访问令牌（access token）：

```
curl http://localhost:9090/oauth/token -X POST -u client:m_volunteer -d "grant_type=password&username=voltest&password=voltest"
```

授权服务器验证了我们的客户端和用户信息，验证成功后将我们需要的令牌（token）信息作为响应传回：

```
{
  "access_token": "363860cb-253e-40e2-a719-7e447f147f97",
  "token_type": "bearer",
  "refresh_token": "e6cd3e81-6f18-4362-bded-24baa79dbf47",
  "expires_in": 2591999,
  "scope": "read write"
}
```

接下来，我们可以使用上个请求返回的 ```access_token```，查看受保护的资源，这里查看活动id为1的活动详情：

```
curl "http://localhost:9090/api/activity/1?coordLong=120&coordLat=30" -X GET -H "Authorization: Bearer 363860cb-253e-40e2-a719-7e447f147f97"
```

相应成功返回:
```
{
	"id": 1,
    "addressStreet": "紫荆花路",
    "address": "西湖区紫荆花路9号紫庭南弄紫金庭园芦荻苑9幢3号商铺",
    "serviceType": "敬老助残",
    "recruitTime": "3月3号",
    "recruitPersonNumber": 20,
    "name": "为老人开展志愿服务",
    "time": "3月5号",
    "principalName": "卜丽萍",
    "principalContact": "13805771623",
    "workingHours": 8,
    "picture": "/public/img/9629ec51-4968-4173-b109-4ac89616350a.jpeg",
    "organizationId": 3,
    "description": "为老人开展志愿服务，要求会理发，义诊，缝补，磨剪刀，心理咨询，清洗眼镜，量血压",
    "organization": "富春街道",
    "distance": 32,
    "recruitedPersonNumber": 3,
    "volunteers": [
      {
        "id": 1,
        "avatar": "/public/img/default_avatar.jpg"
      },
      {
        "id": 2,
        "avatar": "/public/img/e3e8f15a-56fd-426f-aaa7-bb9698a6f9c9.jpg"
      },
      {
        "id": 5,
        "avatar": "/public/img/default_avatar.jpg"
      }
    ],
    "signUp": true
  }
}
```


最后，随着时间（在本项目中是 2591999 秒）的消逝，```access_token``` 会过期。可以使用曾经请求访问令牌时返回的 ```refresh_token``` 来获取一个新的 ```access_token```：

```
curl http://localhost:9090/oauth/token -X POST -u client:m_volunteer -d "grant_type=refresh_token&refresh_token=e6cd3e81-6f18-4362-bded-24baa79dbf47"
```

### 集成spring-security

使用基于方法的注解来进行方法的权限控制,加在特定角色才能访问的方法上：
- ```@PreAuthorize("hasRole('ROLE_VOL')")```

### 静态资源配置

项目涉及图片上传，静态资源配置需要重新配置，配置教程可参照博客[springboot静态资源处理](http://blog.csdn.net/catoop/article/details/50501706)。
本项目中涉及这个的有yml中的
- ```upload.path``` 静态资源所在文件夹
- ```upload.dir```  除去静态资源根目录的根路径的目录
本项目中涉及这个的类有
- ```com.github.oneone1995.mvolunteer.config.mvc.MvcWebConfig``` 

可参考这些重新配置

### 集成阿里云旺IM
**群组功能** 是美志愿APP最大的亮点。群组功能免去传统志愿活动发起后需要手动建立qq群或者微信群并人工通知参与活动的志愿者。因为志愿者的分布问题而使得通知工作变得异常困难，美志愿APP为此提出**自动建群**的解决方案，只要志愿组织发布活动成功，系统便会自动建立一个群用于公告通知和志愿者间相互交流。活动发起者默认为群主，可以设立管理员，看活动大小决定管理员数量，志愿者报名活动申请通过后，会自动加入群。 
自己实现一个im是很困难的，因此项目采用集成阿里云旺sdk的解决方案，[官方文档](https://baichuan.taobao.com/docs/doc.htm?treeId=41&articleId=102626&docType=1)
项目中涉及建群、退群的方法都写在了 **util** 包中

## 总结
由于是比赛项目，进度十分紧张，最后完成的项目和我当初的想法相差甚大。项目初期本想设计良好的restful API，最后写出来的还是不尽人意，在关联查询和多参数的接口设计上陷入了较大的困难。虽然如此，但仍然是一个比较完整的app服务端项目，app效果可以参见客户端的仓库。