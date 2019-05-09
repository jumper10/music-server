package data.access.repositories;

import data.access.entities.RoleEntity;
import data.access.entities.UserEntity;
import data.access.mapper.UserManagerMapper;
import data.access.models.User;
import library.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class UserManagerRepository {

    @Autowired
    UserManagerMapper userManagerMapper;
    public User findByUserName(String userName) throws CustomException{
        UserEntity userEntity=  userManagerMapper.findByUserName(userName);
        if (userEntity == null){
            throw  new CustomException(userName.concat(":不存在") );
        }

        User user  = new User();
        BeanUtils.copyProperties(userEntity,user);
        user.setRoles(userEntity.getRoles().stream().map(RoleEntity::getRoleName).collect(Collectors.toList()));
        return  user;
    }
}
