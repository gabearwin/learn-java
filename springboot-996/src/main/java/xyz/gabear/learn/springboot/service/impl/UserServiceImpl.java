package xyz.gabear.learn.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.gabear.learn.springboot.domain.common.PageQuery;
import xyz.gabear.learn.springboot.domain.common.PageResult;
import xyz.gabear.learn.springboot.domain.dto.UserDTO;
import xyz.gabear.learn.springboot.domain.dto.UserQueryDTO;
import xyz.gabear.learn.springboot.domain.entity.UserDO;
import xyz.gabear.learn.springboot.mapper.UserMapper;
import xyz.gabear.learn.springboot.service.UserService;
import xyz.gabear.learn.springboot.util.ValidatorUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类名称：UserServiceImpl
 * ********************************
 * <p>
 * 类描述：用户服务实现类
 *
 * @author
 * @date 下午2:27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(UserDTO userDTO) {
        UserDO userDO = new UserDO();

        // TODO 浅拷贝 属性名相同才能拷贝
        BeanUtils.copyProperties(userDTO, userDO);

        return userMapper.insert(userDO);
    }

    @Override
    public int update(Long id, UserDTO userDTO) {
        UserDO userDO = new UserDO();

        BeanUtils.copyProperties(userDTO, userDO);

        userDO.setId(id);

        return userMapper.updateById(userDO);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery) {

        // 手动校验功能
        ValidatorUtils.validate(pageQuery);

        // 参数构造
        Page page = new Page(pageQuery.getPageNo(), pageQuery.getPageSize());

        UserDO query = new UserDO();
        BeanUtils.copyProperties(pageQuery.getQuery(), query);
        // TODO 如果属性不一致，需要做特殊处理
        QueryWrapper queryWrapper = new QueryWrapper(query);

        // 查询
        IPage<UserDO> userDOIPage = userMapper.selectPage(page, queryWrapper);

        // 结果解析
        PageResult pageResult = new PageResult();
        pageResult.setPageNo((int) userDOIPage.getCurrent());
        pageResult.setPageSize((int) userDOIPage.getSize());
        pageResult.setTotal(userDOIPage.getTotal());
        pageResult.setPageNum(userDOIPage.getPages());

        // 对数据进行转换
        List<UserDTO> userDTOList = Optional
                .ofNullable(userDOIPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDO -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(userDO, userDTO);
                    return userDTO;
                })
                .collect(Collectors.toList());

        pageResult.setData(userDTOList);

        return pageResult;
    }
}
