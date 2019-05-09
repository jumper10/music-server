package data.access.entities;

import lombok.Data;

import java.util.Date;

@Data
class EntityBase {
    Long id,creatorId;
    Date createDateTime,lastUpdateTime;
}
