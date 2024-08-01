# Telegram Bot API Java Library

## Install

### Maven
```xml
<dependency>
    <groupId>ru.loolzaaa.tgbot4j</groupId>
    <artifactId>tgbot4j</artifactId>
    <version>0.3.0</version>
</dependency>
```

### Gradle
```
implementation("ru.loolzaaa.tgbot4j:tgbot4j:0.3.0")
```

## Usage

### Simple LongPolling Bot
```java
TelegramBot bot = TelegramBotBuilder.init("BOT_NAME", System.getenv("BOT_TOKEN")).build();
bot.registerUpdateProcessor(new EchoMessageUpdateProcessor());
bot.init();
```

### Configured LongPolling Bot
```java
TelegramBot bot = TelegramBotBuilder.init("BOT_NAME", System.getenv("BOT_TOKEN"))
        .methodSender(senderConfigurer -> {
            senderConfigurer.defaultSender(defaultSenderConfigurer -> {
                defaultSenderConfigurer.withOptions(options -> options.setMaxThreads(4));
            });
        })
        .updateReceiver(receiverConfigurer -> {
            receiverConfigurer.longPolling(longPollingConfigurer -> {
                longPollingConfigurer.withOptions(options -> {
                    options.setClearWebhookIfExist(true);
                    options.setConnectTimeout(4000);
                });
            });
        })
        .build();
bot.registerUpdateProcessor(new EchoMessageUpdateProcessor());
bot.init();
```

### Update processing

Implement `UpdateProcessor` and register it to bot. Each processor **can, but is not required to** pass processing to the next processor in the chain. 
```java
public class ExampleProcessor implements UpdateProcessor {
    @Override
    public void process(Update update, MethodSender methodSender, UpdateProcessorChain chain) {
        try {
            // do some stuff with update, execute methods with method sender
            chain.doProcess(update, methodSender);  // <--- step into next processor in chain
        } finally {
            // optionally, do some actions AFTER ALL processors
        }
    }
}
