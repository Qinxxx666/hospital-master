package com.qin.hospital.service;

import com.qin.hospital.entity.File;

/**
 * (File)表服务接口
 *
 * @author makejava
 * @since 2024-04-23 10:04:18
 */
public interface FileService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    File selectById(Long id);

    /**
     * 新增数据
     *
     * @param file 实例对象
     * @return 实例对象
     */
    Integer insert(File file);

    /**
     * 修改数据
     *
     * @param file 实例对象
     * @return 实例对象
     */
    Integer updateById(File file);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Integer deleteById(Long id);

}
