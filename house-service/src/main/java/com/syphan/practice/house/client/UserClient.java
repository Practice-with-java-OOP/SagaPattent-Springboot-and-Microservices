package com.syphan.practice.house.client;

import com.syphan.practice.house.dto.UserDto;
import com.syphan.pratice.common.base.OpenApiWithDataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "registration-service")
public interface UserClient {

    @GetMapping("/api/v1/users/by-username")
    OpenApiWithDataResponse<UserDto> getByUsername(@RequestParam("username") String username);
}
