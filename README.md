ActiveMQ_Practice
=================
ActiveMQ消息队列练习
--
#### 主要内容
activeMQ-all的依赖包
1. Producer类进行ActiveMQ 的 消息的创建和初始化，
    + javax.JMS 提供的 Queue 是 Destination接口的一种实现。
    + JMS 提供的 *Session* 和 *Connection* 是不支持 Try-with-resource语法的，提示其不支持Auto Closeable。 
    + *coon.createSession* 方法中需要提供的参数第二个 **ession.AUTO_ACKNOWLEDGE** 指点对点
    + *Session* 提供的 createpProducer()方法中需要传入 Destination的实现，其指消息队列的名称。
    + *Session* 只有调用 *commit()* 方法才能发送对应的队列到 ActiveMQ上。
2. Consumer类进行 从ActiveMQ消费数据的功能。
    + ***注意*** 获取消息之前，需要执行 *Connection.Start()* 方法。
    + 调用 *Consumer.recieve()* 获取数据。
    + *Recieve* 对象是 *Message*  接口的一个实现，一般建议将其转换为 *TextMessage* 进行操作
