package college.user.dao.mapper;

import college.user.dao.entity.CrmBusinessStatusDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商机状态 Mapper
 *
 * @author ljlleo
 */
@Mapper
public interface CrmBusinessStatusMapper extends BaseMapper<CrmBusinessStatusDO> {

    default List<CrmBusinessStatusDO> selectListByTypeId(Long typeId) {
        return selectList(Wrappers.<CrmBusinessStatusDO>lambdaQuery().eq(CrmBusinessStatusDO::getTypeId, typeId));
    }


}
