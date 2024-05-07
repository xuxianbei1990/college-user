package college.user.dao.mapper;


import college.user.controller.vo.business.CrmBusinessPageReqVO;
import college.user.dao.entity.CrmBusinessDO;
import college.user.enums.CrmBizTypeEnum;
import college.user.mybatis.core.query.MPJLambdaWrapperX;
import college.user.util.CrmPermissionUtils;
import college.user.util.MyBatisUtils;
import college.user.vo.PageResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商机 Mapper
 *
 * @author ljlleo
 */
@Mapper
public interface CrmBusinessMapper extends MPJBaseMapper<CrmBusinessDO> {

    default PageResult<CrmBusinessDO> selectPageByCustomerId(CrmBusinessPageReqVO pageReqVO) {
        IPage<CrmBusinessDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        selectPage(page, Wrappers.lambdaQuery(CrmBusinessDO.class)
                .eq(CrmBusinessDO::getCustomerId, pageReqVO.getCustomerId())
                .like(CrmBusinessDO::getName, pageReqVO.getName())
                .orderByDesc(CrmBusinessDO::getId));
        return new PageResult<>(page.getRecords(), page.getTotal());

    }

    default PageResult<CrmBusinessDO> selectPage(CrmBusinessPageReqVO pageReqVO, Long userId) {
        MPJLambdaWrapperX<CrmBusinessDO> query = new MPJLambdaWrapperX<>();
        // 拼接数据权限的查询条件
        CrmPermissionUtils.appendPermissionCondition(query, CrmBizTypeEnum.CRM_BUSINESS.getType(),
                CrmBusinessDO::getId, userId, pageReqVO.getSceneType(), Boolean.FALSE);
        // 拼接自身的查询条件
        query.selectAll(CrmBusinessDO.class)
                .likeIfPresent(CrmBusinessDO::getName, pageReqVO.getName())
                .orderByDesc(CrmBusinessDO::getId);
        // MyBatis Plus Join 查询
        IPage<CrmBusinessDO> mpPage = MyBatisUtils.buildPage(pageReqVO);
        mpPage = selectJoinPage(mpPage, CrmBusinessDO.class, query);
        // 转换返回
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }

}
