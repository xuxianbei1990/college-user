package college.user.service;



import college.user.dao.entity.OAuth2AccessTokenDO;
import college.user.dto.OAuth2AccessTokenCheckRespDTO;
import college.user.vo.CommonResult;

import java.util.List;

/**
 * OAuth2.0 Token Service 接口
 *
 * 从功能上，和 Spring Security OAuth 的 DefaultTokenServices + JdbcTokenStore 的功能，提供访问令牌、刷新令牌的操作
 *
 * @author 芋道源码
 */
public interface OAuth2TokenService {

    /**
     * 创建访问令牌
     * 注意：该流程中，会包含创建刷新令牌的创建
     *
     * 参考 DefaultTokenServices 的 createAccessToken 方法
     *
     * @param userId 用户编号
     * @param userType 用户类型
     * @param clientId 客户端编号
     * @param scopes 授权范围
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO createAccessToken(Long userId, Integer userType, String clientId, List<String> scopes);

    /**
     * 刷新访问令牌
     *
     * 参考 DefaultTokenServices 的 refreshAccessToken 方法
     *
     * @param refreshToken 刷新令牌
     * @param clientId 客户端编号
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO refreshAccessToken(String refreshToken, String clientId);

    /**
     * 获得访问令牌
     *
     * 参考 DefaultTokenServices 的 getAccessToken 方法
     *
     * @param accessToken 访问令牌
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO getAccessToken(String accessToken);



    /**
     * 移除访问令牌
     * 注意：该流程中，会移除相关的刷新令牌
     *
     * 参考 DefaultTokenServices 的 revokeToken 方法
     *
     * @param accessToken 刷新令牌
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO removeAccessToken(String accessToken);

    CommonResult<OAuth2AccessTokenCheckRespDTO> checkAccessToken(String accessToken);



}
