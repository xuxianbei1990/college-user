package college.user.dao.mapper;

import college.user.dao.entity.CrmBusinessProductDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商机产品 Mapper
 *
 * @author lzxhqs
 */
@Mapper
public interface CrmBusinessProductMapper extends BaseMapper<CrmBusinessProductDO> {

    default List<CrmBusinessProductDO> selectListByBusinessId(Long businessId) {
        return selectList(Wrappers.lambdaQuery(CrmBusinessProductDO.class).in(CrmBusinessProductDO::getBusinessId, businessId));
    }

    default void insertBatch(List<CrmBusinessProductDO> businessProducts) {
        businessProducts.forEach(this::insert);
    }
}
