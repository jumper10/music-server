package data.access.models;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginModel  {

    @NotEmpty(message = "用户名不能为空！")
   // @Min(value = 5,message = "用户名长度大于等于5！")
   // @Max(value = 20,message = "用户名长度小于等于20")
    private String userName;

    @NotEmpty(message = "密码不能为空！")
 //   @Min(value = 5,message = "密码长度大于等于5！")
  //  @Max(value = 20,message = "密码长度小于等于20")
    private String  password;

    private String validCode;
}
