package data.access.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserEntity extends EntityBase {
    private String userName,password;
    private  boolean enabled;

    private List<RoleEntity> roles;
}
