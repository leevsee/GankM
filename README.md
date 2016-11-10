
**每天自动更新干货精选文章。包括图片、一个小视频、一系列精选程序猿干货（假日不更新）。**
数据来源于：gank.io 
<br/>
个人博客：https://leevsee.github.io/

架构：      MVP

图片加载：Picasso

网络请求：RxJava & Retrofit＋okhttp

三级缓存：greenDAO（待更）

界面：遵循Google Meterial 设计风格

其他：butterknife，CardView，RecyclerView，NavigationView，Snackbar，frgament。。。


直接下载：[点击我](http://pan.baidu.com/s/1i5bQIfr)
<br/><br/>


---
**v1.0 (MB)**  
* 重新构建框架 架构从MVC，换成MVP；
* 对Rxjava+Retrofit+Okhttp封装，从服务器获取数据；
* 使用CardView+RecyclerView卡片式显示图片和标题，实现下拉刷新，上拉到底部自动加载；
* 滑动、点击打开侧边栏菜单效果； 
* 新增 每日文章跳转提示；
* 新增 RecyclerView加载动画效果； <br/><br/>

已知BUG、待更新:   
* 侧边栏点击分类，没有跳转，因为框架还在摸索中（如何复用）；
* 三级缓存，greenDAO正在完成中；
* 右上角setting按钮无效，因为正在考虑新的功能和添加缓存；
* 将会新增图片放大、分享和保存功能； 

<br/>
<img src="/blog/shouye.jpg" alt="screenshot" title="screenshot"  width="270" height="486"/>
<img src="/blog/cebianlan.jpg" alt="screenshot" title="screenshot"  />
<img src="/blog/neirong.jpg" alt="screenshot" title="screenshot"  />
<br/><br/>

=======
最后吐槽两句：刚开始是使用MVC模式，因为业务一开始很简单，逻辑也比较清晰，但是到了后面添加了greenDAO作为数据库缓存后，
MVC模式的View爆炸了，何种判断和条件，理清楚一遍要花好久，不便于阅读，然后为了实现优雅明了的代码，还是重新开发，变成
MVP模式，这样子就各层分离解耦，太爽了！不过有些地方，还需改进，特指代码重用方面。
附上一个简单的UML图
<br/>
<img src="http://7xz8pr.com1.z1.glb.clouddn.com/GankMvp.png" alt="screenshot" title="screenshot"  />



