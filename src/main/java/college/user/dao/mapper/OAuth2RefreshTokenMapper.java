package college.user.dao.mapper;


import college.user.dao.entity.OAuth2RefreshTokenDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2RefreshTokenMapper extends BaseMapper<OAuth2RefreshTokenDO> {

    default int deleteByRefreshToken(String refreshToken) {
        return delete(Wrappers.<OAuth2RefreshTokenDO>lambdaQuery().eq(OAuth2RefreshTokenDO::getRefreshToken, refreshToken));
    }

    default OAuth2RefreshTokenDO selectByRefreshToken(String refreshToken) {
        return selectOne(Wrappers.<OAuth2RefreshTokenDO>lambdaQuery().eq(OAuth2RefreshTokenDO::getRefreshToken, refreshToken));
    }

}
