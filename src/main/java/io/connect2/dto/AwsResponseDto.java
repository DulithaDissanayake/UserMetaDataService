package io.connect2.dto;

import lombok.Data;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

@Data
public class AwsResponseDto implements Message<Object> {

    private Object payload;

    private MessageHeaders headers;

}
