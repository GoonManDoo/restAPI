package com.restApi.restApi.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfoV2") // 컨트롤러 or 서비스에서 사용됨
public class UserV2 extends User {
    private String grade;

}
