# -springboot-bbs-
基于springboot的论坛系统，IDE为idea,主要使用的技术有springboot+mybatis+redis+自定义分页，数据库为mysql，前端为bootstrap+jquery+html5

演示地址：
http://47.101.193.185:8088/

项目主要的功能为正常博客站点那样，未登录用户可以进行浏览，登录用户可以进行评论、发布、编辑、删除等操作，关于文章和不同用户的评论的展示使用的是自己写的分页，关于整个论坛系统的权限管理问题，及权限分配，角色分配，会后续完成。登录注册，除了基本的逻辑判断外，还满足了以下要求：

1）	手机验证码有效期为60s，超过有效期需要重新获取；

2）	同一个手机号每分钟只能获取一次验证码；

3）	如果手机验证码输入出错，则需要添加图形验证码，只有图形验证码填写正确，才能获取手机验证码；

4）	密码进行MD5编码，存入数据库；

5）	注册成功后自动跳转到登录页面；

6）	手机验证码模拟生成，不需要发送到手机。


登录模块

1）	密码连续输错3次后，2分钟内将不能再次登录；

3）	登录成功后跳转到消息列表页面；

系统展示：

系统首页

![image](https://github.com/yonyong/springboot-bbs/blob/master/image/index.jpg)

登陆

![image](https://github.com/yonyong/springboot-bbs/blob/master/image/login.jpg)

注册

![image](https://github.com/yonyong/springboot-bbs/blob/master/image/zhuce.jpg)

文章列表

![image](https://github.com/yonyong/springboot-bbs/blob/master/image/toarticle.jpg)

文章详情

![image](https://github.com/yonyong/springboot-bbs/blob/master/image/articleDetail1.jpg)

![image](https://github.com/yonyong/springboot-bbs/blob/master/image/articleDetail2.jpg)

![image](https://github.com/yonyong/springboot-bbs/blob/master/image/articleDetail3.jpg)

发布文章

![image](https://github.com/yonyong/springboot-bbs/blob/master/image/fabu.jpg)

编辑文章

![image](https://github.com/yonyong/springboot-bbs/blob/master/image/bianji.jpg)

删除文章

![image](https://github.com/yonyong/springboot-bbs/blob/master/image/shanchu.jpg)

联系我们

![image](https://github.com/yonyong/springboot-bbs/blob/master/image/contact.jpg)

菜单

![image](https://github.com/yonyong/springboot-bbs/blob/master/image/menu.jpg)
