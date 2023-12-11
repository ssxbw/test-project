package com.payment.service.serviceIml;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.mapper.ElkFileMapper;
import com.payment.pojo.ElkFile;
import com.payment.service.IElkFileService;


/**
 * 文件Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-27
 */
@Service
public class ElkFileServiceImpl implements IElkFileService
{
    @Autowired
    private ElkFileMapper elkFileMapper;

    /**
     * 查询文件
     *
     * @param id 文件主键
     * @return 文件
     */
    @Override
    public ElkFile selectElkFileById(Integer id)
    {
        return elkFileMapper.selectElkFileById(id);
    }

    /**
     * 查询文件列表
     *
     * @param elkFile 文件
     * @return 文件
     */
    @Override
    public List<ElkFile> selectElkFileList(ElkFile elkFile)
    {
        return elkFileMapper.selectElkFileList(elkFile);
    }

    /**
     * 新增文件
     *
     * @param elkFile 文件
     * @return 结果
     */
    @Override
    public int insertElkFile(ElkFile elkFile)
    {
        return elkFileMapper.insertElkFile(elkFile);
    }

    /**
     * 修改文件
     *
     * @param elkFile 文件
     * @return 结果
     */
    @Override
    public int updateElkFile(ElkFile elkFile)
    {
        return elkFileMapper.updateElkFile(elkFile);
    }

    /**
     * 批量删除文件
     *
     * @param ids 需要删除的文件主键
     * @return 结果
     */
    @Override
    public int deleteElkFileByIds(Integer[] ids)
    {
        return elkFileMapper.deleteElkFileByIds(ids);
    }

    /**
     * 删除文件信息
     *
     * @param id 文件主键
     * @return 结果
     */
    @Override
    public int deleteElkFileById(Integer id)
    {
        return elkFileMapper.deleteElkFileById(id);
    }
}
