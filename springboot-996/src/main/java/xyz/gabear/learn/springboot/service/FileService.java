package xyz.gabear.learn.springboot.service;

import java.io.File;
import java.io.InputStream;

/**
 * 类名称：FileService
 * ********************************
 * <p>
 * 类描述：文件上传服务接口
 *
 * @author
 * @date 2020/3/8 上午9:42
 */
public interface FileService {

    /**
     * 文件上传
     * @param inputStream
     * @param filename
     */
    void upload(InputStream inputStream, String filename);

    /**
     * 文件上传
     * @param file
     */
    void upload(File file);

}
