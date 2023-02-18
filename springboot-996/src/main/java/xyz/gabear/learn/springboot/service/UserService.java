package xyz.gabear.learn.springboot.service;

import xyz.gabear.learn.springboot.domain.common.PageQuery;
import xyz.gabear.learn.springboot.domain.common.PageResult;
import xyz.gabear.learn.springboot.domain.dto.UserDTO;
import xyz.gabear.learn.springboot.domain.dto.UserQueryDTO;

import java.util.List;

/**
 * 类名称：UserService
 * ********************************
 * <p>
 * 类描述：用户服务接口
 *
 * @author
 * @date 2020/3/1 下午2:20
 */
public interface UserService {

    /**
     * 新增
     * @param userDTO
     * @return
     */
    int save(UserDTO userDTO);

    /**
     * 更新
     * @param id
     * @param userDTO
     * @return
     */
    int update(Long id, UserDTO userDTO);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery);

}
