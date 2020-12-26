package io.connect2.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;


@JsonDeserialize(builder = ResponseDto.ResponseDtoBuilder.class)
@Builder
@Value
public class ResponseDto {
    private String status;
    private Object data;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ResponseDtoBuilder {

    }
}
