package xyz.gabear.learn.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.gabear.learn.springboot.domain.common.PageQuery;
import xyz.gabear.learn.springboot.domain.common.PageResult;
import xyz.gabear.learn.springboot.domain.common.ResponseResult;
import xyz.gabear.learn.springboot.domain.dto.UserDTO;
import xyz.gabear.learn.springboot.domain.dto.UserQueryDTO;
import xyz.gabear.learn.springboot.domain.vo.UserVO;
import xyz.gabear.learn.springboot.exception.ErrorCodeEnum;
import xyz.gabear.learn.springboot.service.ExcelExportService;
import xyz.gabear.learn.springboot.service.UserService;
import xyz.gabear.learn.springboot.util.InsertValidationGroup;
import xyz.gabear.learn.springboot.util.UpdateValidationGroup;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类名称：UserController
 * ********************************
 * <p>
 * 类描述：用户Controller
 *
 * @author
 * @date 下午12:54
 */
@RestController
@RequestMapping("/api/users")
@Validated
@Slf4j
@Api(
        value = "用户管理Controller",
        tags = {"用户管理Controller"},
        protocols = "http, https",
        hidden = false
)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExcelExportService excelExportService;

    @GetMapping("/hello")
    private String hello() {
        return "hello";
    }

    /**
     * POST /api/users UserDTO
     * 新建用户
     */
    @CacheEvict(cacheNames = "users-cache", allEntries = true)
    @PostMapping
    public ResponseResult save(
            @Validated(InsertValidationGroup.class)
            @RequestBody UserDTO userDTO) {
        int save = userService.save(userDTO);

        if (save == 1) {
            return ResponseResult.success("新增成功！");
        } else {
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }
    }

    /**
     * 更新用户信息
     * PUT /api/users/{id} UserDTO userDTO
     *
     * @return
     */
    @PutMapping("/{id}")
    @ApiOperation(
            value = "更新用户信息",
            notes = "备注说明信息",
            response = ResponseResult.class,
            httpMethod = "PUT"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "参数说明，用户主键",
                    required = true,
                    paramType = "path",
                    dataType = "Long",
                    example = "12345"
            ),
            @ApiImplicitParam(
                    name = "userDTO",
                    value = "用户信息",
                    required = true,
                    paramType = "body",
                    dataType = "UserDTO",
                    dataTypeClass = UserDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0000, message = "操作成功"),
            @ApiResponse(code = 3004, message = "更新失败")
    })
    public ResponseResult update(
            @NotNull @PathVariable("id") Long id,
            @Validated(UpdateValidationGroup.class)
            @RequestBody UserDTO userDTO) {
        int update = userService.update(id, userDTO);

        if (update == 1) {
            return ResponseResult.success("更新成功！");
        } else {
            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 删除用户信息
     * DELETE /api/users/{id}
     *
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult delete(
            @NotNull(message = "用户ID不能为空!")
            @PathVariable("id") Long id) {
        int delete = userService.delete(id);

        if (delete == 1) {
            return ResponseResult.success("删除成功！");
        } else {
            return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
        }
    }

    /**
     * 查询用户信息
     * GET
     *
     * @return
     */
    @Cacheable(cacheNames = "users-cache")
    @GetMapping
    public ResponseResult<PageResult> query(
            @NotNull Integer pageNo,
            @NotNull Integer pageSize,
            @Validated UserQueryDTO query) {

        log.info("未使用缓存！");

        // 构造查询条件
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setQuery(query);

        // 查询
        PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);

        // 实体转换
        List<UserVO> userVOList = Optional
                .ofNullable(pageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDTO -> {
                    UserVO userVO = new UserVO();

                    BeanUtils.copyProperties(userDTO, userVO);
                    // 对特殊字段做处理
                    userVO.setPassword("******");
                    if (!StringUtils.isEmpty(userDTO.getPhone())) {
                        userVO.setPhone(userDTO.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                    }

                    return userVO;
                })
                .collect(Collectors.toList());

        // 封装返回结果
        PageResult<List<UserVO>> result = new PageResult<>();
        BeanUtils.copyProperties(pageResult, result);
        result.setData(userVOList);

        return ResponseResult.success(result);
    }


    /**
     * 用户数据导出
     *
     * @param query
     * @param filename
     * @return
     */
    @GetMapping("/export")
    public ResponseResult<Boolean> export(
            @Validated UserQueryDTO query,
            @NotEmpty String filename) {

        log.info("接受到导出请求！filename = {}", filename);

        // 数据导出
//        excelExportService.export(query, filename);
        excelExportService.asyncExport(query, filename);

        return ResponseResult.success(Boolean.TRUE);
    }
}
