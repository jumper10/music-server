package data.access.mapper;

import data.access.entities.UserEntity;
import data.access.models.User;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface UserManagerMapper {

    UserEntity findByUserName(String userName);
}
