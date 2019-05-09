package data.access.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class SmokeEntity extends EntityBase {
    private  String message;
}
