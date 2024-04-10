package college.user.dao.mapper;


import college.user.dao.entity.OAuth2ClientDO;
import college.user.vo.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * OAuth2 客户端 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OAuth2ClientMapper extends BaseMapper<OAuth2ClientDO> {

    default OAuth2ClientDO selectByClientId(String clientId) {
        return selectOne(new LambdaQueryWrapper<OAuth2ClientDO>().eq(OAuth2ClientDO::getClientId, clientId));
    }

}
