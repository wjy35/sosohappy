package com.ssafy.notification.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.Map;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FormattedResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @Singular("result")
    private Map<String,Object> result;

}
