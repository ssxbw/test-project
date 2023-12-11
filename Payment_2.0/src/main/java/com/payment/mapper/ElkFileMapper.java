package com.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.payment.pojo.ElkFile;

/**
 * 文件Mapper接口
 *
 * @date 2023-04-27
 */
@Mapper
public interface ElkFileMapper
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
     * 删除文件
     *
     * @param id 文件主键
     * @return 结果
     */
    public int deleteElkFileById(Integer id);

    /**
     * 批量删除文件
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteElkFileByIds(Integer[] ids);
}
