# shopping-mall
用于学习seata与其他分布式相关技术项目

当前已整合了一些spring cloud 的基本组件 与 seata,但是有一个问题使用 seata 后不能做批量的更新与插入数据处理，暂时还不知道为啥，后续的提交会做好相应的修改。
> 如果项目中出现了 putdata 这个模块的引用，请自行删除即可，因为该模块是我自己用于获取基础数据用的，为防止被乱用就没做上传处理

今天(2019-12-29)加了 [Seata官方](https://seata.io/zh-cn/community/index.html) 的钉钉群，向群里的开发者请教了下，回复为:**当前版本不支持以分号分割的批量更新，目前支持的批量更新为在程序中做循环更新处理，分号分割的批量更新会在后续的版本中实现**

##### 网关更新

此前的接口未通过网关来进行访问，是在 consumer 那个模块通过 Feign 直调的接口，目前使用了网关，这样让 consumer 这个模块显得有些多余，可以直接把它移除，当然在网关中我也配置了 Swagger，除了 consumer 把其他几个项目启动后，再启动 shopping-mall-gateway 项目，成功后再访问 [swagger-ui](http://127.0.0.1:9501/swagger-ui.html) 即可，网关配置 swagger 的参考了 [该项目](https://gitee.com/wxdfun/sw ) 的实现方式与源码

#### RocketMQ 更新

整合了`RocketMQ`，在订单支付的流程中更新仓库库存与库存存量时使用了二段式提交的事务消息，修改消费数据方式，由消费方主动提交消费完成的动作，更新 broker 中的 offset，以此确保消费端消费数据异常，即 MQ 数据在被消费后处理异常，无法再次消费，导致消费数据丢失。

#### Feign 更新
所有通过`feign`接口的调用的内容都需要通过网关走`rest`服务，添加熔断接口实现(这也是个坑，调用时间长也会触发熔断，即使接口未出现问题，当然这也是熔断的作用，如果对性能无太大要求的话也可以不适用熔断处理) 