package ru.sbsoft.kafka_producer.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.sbsoft.dto.OperationParam;
import ru.sbsoft.kafka_producer.kafka.KafkaSender;

@ShellComponent
@RequiredArgsConstructor
public class CommandShell {

    private final KafkaSender kafkaSender;

    @ShellMethod(key = "hello-to", value = "Say hello to username")
    public String helloTo(@ShellOption({"username"}) String username) {
        return "Hello " + username;
    }

    @ShellMethod(key = "send1", value = "Send message to topic-1")
    @Transactional("chainedTransactionManager")
    public String sendMessage1(@ShellOption({"message"}) String message) {
        final OperationParam param = new OperationParam("A", message);
        kafkaSender.sendMessage("topic-1", param);
        return "message sending";
    }

    @ShellMethod(key = "send2", value = "Send message to topic-2")
    public String sendMessage2(@ShellOption({"message"}) String message) {
        final OperationParam param = new OperationParam("B", message);
        kafkaSender.sendMessage("topic-2", param);
        return "message sending";
    }

}
