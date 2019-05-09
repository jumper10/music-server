package data.access.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

@Data
@RequiredArgsConstructor
@ToString(callSuper = true)
public class User extends ModelBase{
    private String userName,password;
    private Collection<String> roles;
    private  boolean enabled;
}
