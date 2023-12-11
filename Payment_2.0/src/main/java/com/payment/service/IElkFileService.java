package com.payment.service;

import java.util.List;

import com.payment.pojo.ElkFile;

/**
 * 文件Service接口
 *
 * @author ruoyi
 * @date 2023-04-27
 */
public interface IElkFileService
{
    /**
     * 查询文件
     *
     * @param id 文件主键
     * @return 文件
     */
    public ElkFile selectElkFileById(Integer id);

    /**
     * 查询文件列表
     *
     * @param elkFile 文件
     * @return 文件集合
     */
    public List<ElkFile> selectElkFileList(ElkFile elkFile);

    /**
     * 新增文件
     *
     * @param elkFile 文件
     * @return 结果
     */
    public int insertElkFile(ElkFile elkFile);

    /**
     * 修改文件
     *
     * @param elkFile 文件
     * @return 结果
     */
    public int updateElkFile(ElkFile elkFile);

    /**
     * 批量删除文件
     *
     * @param ids 需要删除的文件主键集合
     * @return 结果
     */
    public int deleteElkFileByIds(Integer[] ids);

    /**
     * 删除文件信息
     *
     * @param id 文件主键
     * @return 结果
     */
    public int deleteElkFileById(Integer id);
}
