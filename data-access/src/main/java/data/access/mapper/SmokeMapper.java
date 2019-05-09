package data.access.mapper;

import data.access.entities.SmokeEntity;
import org.apache.ibatis.annotations.Select;

public interface SmokeMapper {

    @Select("select * from Smoke limit 1")
    SmokeEntity smoke();
}
