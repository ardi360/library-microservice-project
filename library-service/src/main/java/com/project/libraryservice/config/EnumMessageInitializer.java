package com.project.libraryservice.config;

import com.project.libraryservice.payload.response.ResultStatus;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;


//statically modify the enums description values with messages.properties based on locale set, note that it do that one time statically in application boot up process so it cant be used dynamically to do that so with accept_language
//header dynamically in runtime because the process is one time during application boot
@Component
public class EnumMessageInitializer implements ApplicationRunner {
    private final MessageSource messageSource;

    public EnumMessageInitializer(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void run(ApplicationArguments args) {
        for (ResultStatus status : ResultStatus.values()) {
            String localizedMessage = messageSource.getMessage(status.getDescription(), null, Locale.of("fa"));
            status.setDescription(localizedMessage);
        }
    }
}