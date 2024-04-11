package college.user.dao.mapper;

import college.user.dao.entity.OAuth2AccessTokenDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OAuth2AccessTokenMapper extends BaseMapper<OAuth2AccessTokenDO> {

    default OAuth2AccessTokenDO selectByAccessToken(String accessToken) {
        return selectOne(Wrappers.lambdaQuery(OAuth2AccessTokenDO.class).eq(OAuth2AccessTokenDO::getAccessToken, accessToken));
    }

    default List<OAuth2AccessTokenDO> selectListByRefreshToken(String refreshToken) {
        return selectList(Wrappers.lambdaQuery(OAuth2AccessTokenDO.class).eq(OAuth2AccessTokenDO::getRefreshToken, refreshToken));
    }

}
