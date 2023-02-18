package xyz.gabear.learn.springboot.service;

import xyz.gabear.learn.springboot.domain.dto.UserQueryDTO;

/**
 * 类名称：ExcelExportService
 * ********************************
 * <p>
 * 类描述：Excel导出服务接口
 *
 * @author
 * @date 2020/3/8 上午10:40
 */
public interface ExcelExportService {

    /**
     * 同步导出服务
     * @param query
     * @param filename
     */
    void export(UserQueryDTO query, String filename);

    /**
     * 异步导出服务
     * @param query
     * @param filename
     */
    void asyncExport(UserQueryDTO query, String filename);

}
