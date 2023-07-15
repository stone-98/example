package com.ocat.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "code-generator")
@Data
public class GeneratorConfig {
    private String author;
    private List<String> includeTable;
    private String projectPath;
    private String parentPackage;
}
