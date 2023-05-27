package hello.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;

@Slf4j
public class CommandLineV2 {
    public static void main(String[] args) {
        for (String arg : args) {
            log.info("arg {}", arg);
        }

        ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
        log.info("SourceArgs = {}", List.of(applicationArguments.getSourceArgs()));
        log.info("NonOptionsArgs = {}", applicationArguments.getNonOptionArgs());
        log.info("OptionsNames = {}", applicationArguments.getOptionNames());

        for (String optionName : applicationArguments.getOptionNames()) {
            log.info("option arg {} = {}", optionName, applicationArguments.getOptionValues(optionName));
        }

        List<String> url = applicationArguments.getOptionValues("url");
        List<String> username = applicationArguments.getOptionValues("username");
        List<String> password = applicationArguments.getOptionValues("password");
        List<String> mode = applicationArguments.getOptionValues("mode");

        log.info("url={}", url);
        log.info("username={}", username);
        log.info("password={}", password);
        log.info("mode={}", mode);
    }
}
