package io.connect2.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.connect2.dto.AwsResponseDto;
import io.connect2.dto.UserMetaDataDTO;
import io.connect2.service.UserMetaDataService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Component
public class MainHandler implements Function<APIGatewayProxyRequestEvent, Message<Object>> {

    private static final String CREATE_USERMETADATA = "/usermeta";
    private static final String VIEWALL_USERMETADATA = "/users";
    private static final String DELETE_USERMETADATA = "/userdelete/{id}";
    private static final String SEARCH_USERMETADATA = "/usersearch/{id}";
    private static final String UPDATE_USERMETADATA = "/userupdate/{id}";

    private final UserMetaDataService userMetaDataService;

    private final ObjectMapper objectMapper;

    @Value("${cors.allowed.origin}")
    private String allowedOrigins;

    @SneakyThrows
    @Override
    public Message<Object> apply(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {
        Map<String, Object> httpResponseHeaderMap = new HashMap<>();
        httpResponseHeaderMap.put("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        httpResponseHeaderMap.put("Access-Control-Allow-Headers", "Content-Type");
        httpResponseHeaderMap.put("Access-Control-Allow-Origin", allowedOrigins);

        AwsResponseDto awsResponseDto = new AwsResponseDto();

        Object object;


        if (CREATE_USERMETADATA.equals(apiGatewayProxyRequestEvent.getResource())) {
            object=userMetaDataService.addUserMetaData(objectMapper.readValue(apiGatewayProxyRequestEvent.getBody(), UserMetaDataDTO.class));
        } else if (VIEWALL_USERMETADATA.equals(apiGatewayProxyRequestEvent.getResource())) {
            object = userMetaDataService.getAllUserMetaData();
        } else if (DELETE_USERMETADATA.equals(apiGatewayProxyRequestEvent.getResource())) {
            object = userMetaDataService.deleteUserMeta(apiGatewayProxyRequestEvent
                    .getPathParameters().get("id"));
        }else if (SEARCH_USERMETADATA.equals(apiGatewayProxyRequestEvent.getResource())) {
            object = userMetaDataService.getUserMetaData(apiGatewayProxyRequestEvent
                    .getPathParameters().get("id"));
        }else if (UPDATE_USERMETADATA.equals(apiGatewayProxyRequestEvent.getResource())) {
            object=userMetaDataService.updateUserMetaData(
                    objectMapper.readValue(apiGatewayProxyRequestEvent.getBody(), UserMetaDataDTO.class),
                    apiGatewayProxyRequestEvent
                            .getPathParameters().get("id")
                    );
        }
        else {
            object = ResponseEntity.notFound().build();
        }

        awsResponseDto.setPayload(object);

        httpResponseHeaderMap.put("statusCode", 200);

        awsResponseDto.setHeaders(new MessageHeaders(httpResponseHeaderMap));

        return awsResponseDto;


    }
}
