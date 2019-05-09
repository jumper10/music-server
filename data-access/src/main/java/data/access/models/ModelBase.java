package data.access.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
class ModelBase {
    Long id,creatorId;
    Date createDateTime,lastUpdateTime;
}
