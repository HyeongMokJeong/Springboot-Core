package hello;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandLineBean {
    private final ApplicationArguments applicationArguments;

    @PostConstruct
    public void init() {
        log.info("source {}", List.of(applicationArguments.getSourceArgs()));
        log.info("optionName {}", applicationArguments.getOptionNames());
        for (String name : applicationArguments.getOptionNames()) {
            log.info("option args {} = {}", name, applicationArguments.getOptionValues(name));
        }
    }
}
