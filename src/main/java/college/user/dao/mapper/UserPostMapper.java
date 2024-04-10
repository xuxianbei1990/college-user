package college.user.dao.mapper;


import college.user.dao.entity.UserPostDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface UserPostMapper extends BaseMapper<UserPostDO> {

    default List<UserPostDO> selectListByUserId(Long userId) {
        return selectList(Wrappers.lambdaQuery(UserPostDO.class).eq(UserPostDO::getUserId, userId));
    }

    default void deleteByUserIdAndPostId(Long userId, Collection<Long> postIds) {
        delete(Wrappers.lambdaQuery(UserPostDO.class)
                .eq(UserPostDO::getUserId, userId)
                .in(UserPostDO::getPostId, postIds));
    }


    default void deleteByUserId(Long userId) {
        delete(Wrappers.lambdaUpdate(UserPostDO.class).eq(UserPostDO::getUserId, userId));
    }

    default void insertBatch(List<UserPostDO> objects) {
        for (UserPostDO object : objects) {
            insert(object);
        }
    }
}
