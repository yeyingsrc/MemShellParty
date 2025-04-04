package com.reajason.javaweb.boot.dto;

import com.reajason.javaweb.memshell.Packers;
import com.reajason.javaweb.memshell.config.*;
import lombok.Data;

/**
 * @author ReaJason
 * @since 2024/12/18
 */
@Data
public class GenerateRequest {
    private ShellConfig shellConfig;
    private ShellToolConfigDTO shellToolConfig;
    private InjectorConfig injectorConfig;
    private Packers packer;

    @Data
    static class ShellToolConfigDTO {
        private String shellClassName;
        private String godzillaPass;
        private String godzillaKey;
        private String commandParamName;
        private String behinderPass;
        private String antSwordPass;
        private String headerName;
        private String headerValue;
        private String shellClassBase64;
    }

    public ShellToolConfig parseShellToolConfig() {
        return switch (shellConfig.getShellTool()) {
            case Godzilla -> GodzillaConfig.builder()
                    .shellClassName(shellToolConfig.getShellClassName())
                    .pass(shellToolConfig.getGodzillaPass())
                    .key(shellToolConfig.getGodzillaKey())
                    .headerName(shellToolConfig.getHeaderName())
                    .headerValue(shellToolConfig.getHeaderValue())
                    .build();
            case Behinder -> BehinderConfig.builder()
                    .shellClassName(shellToolConfig.getShellClassName())
                    .pass(shellToolConfig.getBehinderPass())
                    .headerName(shellToolConfig.getHeaderName())
                    .headerValue(shellToolConfig.getHeaderValue())
                    .build();
            case Command -> CommandConfig.builder()
                    .shellClassName(shellToolConfig.getShellClassName())
                    .paramName(shellToolConfig.getCommandParamName())
                    .build();
            case Suo5 -> Suo5Config.builder()
                    .shellClassName(shellToolConfig.getShellClassName())
                    .headerName(shellToolConfig.getHeaderName())
                    .headerValue(shellToolConfig.getHeaderValue())
                    .build();
            case AntSword -> AntSwordConfig.builder()
                    .shellClassName(shellToolConfig.getShellClassName())
                    .pass(shellToolConfig.getAntSwordPass())
                    .headerName(shellToolConfig.getHeaderName())
                    .headerValue(shellToolConfig.getHeaderValue())
                    .build();
            case NeoreGeorg -> NeoreGeorgConfig.builder()
                    .shellClassName(shellToolConfig.getShellClassName())
                    .headerName(shellToolConfig.getHeaderName())
                    .headerValue(shellToolConfig.getHeaderValue())
                    .build();
            case Custom -> CustomConfig.builder()
                    .shellClassBase64(shellToolConfig.getShellClassBase64())
                    .shellClassName(shellToolConfig.getShellClassName())
                    .build();
            default -> throw new UnsupportedOperationException("unknown shell tool " + shellConfig.getShellTool());
        };
    }
}