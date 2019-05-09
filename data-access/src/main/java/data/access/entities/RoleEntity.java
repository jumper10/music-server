package data.access.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class RoleEntity extends EntityBase {
    private String roleName,displayName;
    private  boolean enabled;
}
