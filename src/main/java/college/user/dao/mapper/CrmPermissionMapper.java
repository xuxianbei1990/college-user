package college.user.dao.mapper;


import college.user.dao.entity.CrmPermissionDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * crm 数据权限 mapper
 *
 * @author HUIHUI
 */
@Mapper
public interface CrmPermissionMapper extends BaseMapper<CrmPermissionDO> {

    default CrmPermissionDO selectByBizTypeAndBizIdByUserId(Integer bizType, Long bizId, Long userId) {
        return selectOne(Wrappers.<CrmPermissionDO>lambdaQuery()
                .eq(CrmPermissionDO::getBizType, bizType)
                .eq(CrmPermissionDO::getBizId, bizId)
                .eq(CrmPermissionDO::getUserId, userId));
    }

    default List<CrmPermissionDO> selectByBizTypeAndBizId(Integer bizType, Long bizId) {
        return selectList(Wrappers.<CrmPermissionDO>lambdaQuery()
                .eq(CrmPermissionDO::getBizType, bizType)
                .eq(CrmPermissionDO::getBizId, bizId));
    }

    default List<CrmPermissionDO> selectByBizTypeAndBizIds(Integer bizType, Collection<Long> bizIds) {
        return selectList(Wrappers.<CrmPermissionDO>lambdaQuery()
                .eq(CrmPermissionDO::getBizType, bizType)
                .in(CrmPermissionDO::getBizId, bizIds));
    }
    default List<CrmPermissionDO> selectListByBizTypeAndUserId(Integer bizType, Long userId) {
        return selectList(Wrappers.<CrmPermissionDO>lambdaQuery()
                .eq(CrmPermissionDO::getBizType, bizType)
                .eq(CrmPermissionDO::getUserId, userId));
    }

    default List<CrmPermissionDO> selectListByBizTypeAndBizIdAndLevel(Integer bizType, Long bizId, Integer level) {
        return selectList(Wrappers.<CrmPermissionDO>lambdaQuery()
                .eq(CrmPermissionDO::getBizType, bizType)
                .eq(CrmPermissionDO::getBizId, bizId)
                .eq(CrmPermissionDO::getLevel, level));
    }

    default CrmPermissionDO selectByIdAndUserId(Long id, Long userId) {
        return selectOne(Wrappers.<CrmPermissionDO>lambdaQuery().eq(CrmPermissionDO::getId, id).eq(CrmPermissionDO::getUserId, userId));
    }

    default CrmPermissionDO selectByBizIdAndUserId(Long bizId, Long userId) {
        return selectOne(Wrappers.<CrmPermissionDO>lambdaQuery().eq(CrmPermissionDO::getBizId, bizId).eq(CrmPermissionDO::getUserId, userId));
    }

    default int deletePermission(Integer bizType, Long bizId) {
        return delete(Wrappers.<CrmPermissionDO>lambdaQuery()
                .eq(CrmPermissionDO::getBizType, bizType)
                .eq(CrmPermissionDO::getBizId, bizId));
    }

    default Long selectListByBiz(Collection<Integer> bizTypes, Collection<Long> bizIds, Collection<Long> userIds) {
        return selectCount(Wrappers.<CrmPermissionDO>lambdaQuery().in(CrmPermissionDO::getBizType, bizTypes)
               .in(CrmPermissionDO::getBizId, bizIds).in(CrmPermissionDO::getUserId, userIds));
    }

}
