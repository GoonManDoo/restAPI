package com.restApi.restApi.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
//@JsonIgnoreProperties(value = {"password", "ssn"})
//@JsonFilter("UserInfo") // 컨트롤러 or 서비스에서 사용됨

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=2, message = "이름은 두 글자 이상 입력해주세요 !") // name size가 최소 2
    @Schema(name = "유저명", description = "유저명을 입력해주세요")
    private String name;

    @Past
    @Schema(name = "등록일", description = "등록일을 입력해주세요")
    private Date joinDate;

    //@JsonIgnore // 이 데이터 값을 무시해주세요~, 상단 JsonIgnoreProperties에 배열 형식으로도 지정 가능
    @Schema(name = "비밀번호", description = "비밀번호를 입력해주세요")
    private String password;
    
    @Schema(name = "주민번호", description = "주민번호를 입력해주세요")
    private String ssn;

}
