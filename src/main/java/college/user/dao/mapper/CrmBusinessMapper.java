package college.user.dao.mapper;


import college.user.controller.vo.business.CrmBusinessPageReqVO;
import college.user.dao.entity.CrmBusinessDO;
import college.user.vo.PageResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商机 Mapper
 *
 * @author ljlleo
 */
@Mapper
public interface CrmBusinessMapper extends BaseMapper<CrmBusinessDO> {

    default PageResult<CrmBusinessDO> selectPageByCustomerId(CrmBusinessPageReqVO pageReqVO) {
        IPage<CrmBusinessDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        selectPage(page, Wrappers.lambdaQuery(CrmBusinessDO.class)
                .eq(CrmBusinessDO::getCustomerId, pageReqVO.getCustomerId())
                .like(CrmBusinessDO::getName, pageReqVO.getName())
                .orderByDesc(CrmBusinessDO::getId));
        return new PageResult<>(page.getRecords(), page.getTotal());

    }
}
