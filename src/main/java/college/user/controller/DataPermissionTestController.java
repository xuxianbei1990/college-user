package college.user.controller;

import college.user.dao.entity.DataPermissionUserDO;
import college.user.service.DataPermissionTestService;
import college.user.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static college.user.vo.CommonResult.success;

/**
 * User: EDY
 * Date: 2024/4/20
 * Time: 10:46
 * Version:V1.0
 */
@RestController
@RequestMapping("/dataPermissionTest")
public class DataPermissionTestController {


    @Autowired
    private DataPermissionTestService dataPermissionTestService;

    @GetMapping("selectUserNames")
    public CommonResult<DataPermissionUserDO> selectUserNames(@RequestParam String username) {
        return success(dataPermissionTestService.selectUserNames(username));
    }
}
