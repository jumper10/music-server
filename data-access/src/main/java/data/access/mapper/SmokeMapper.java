package data.access.mapper;

import org.apache.ibatis.annotations.Select;

public interface SmokeMapper {

    @Select("select message from Smoke limit 1")
    String smoke();
}
