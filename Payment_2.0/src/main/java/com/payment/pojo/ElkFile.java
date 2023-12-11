package com.payment.pojo;



/**
 * 文件对象 elk_file
 * 
 * @author ruoyi
 * @date 2023-04-27
 */
public class ElkFile
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    private String url;

    /** $column.columnComment */
    private String fileName;

    /** $column.columnComment */
    private String newName;

    /** $column.columnComment */
    private String oldName;

    /** $column.columnComment */
    private Long docId;

    /** $column.columnComment */
    private String isDel;

    /** $column.columnComment */
    private String isElk;

    /** 文件大小 */
    private String fileSize;

    /** pdfurl路径 */
    private String pdfurl;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setNewName(String newName) 
    {
        this.newName = newName;
    }

    public String getNewName() 
    {
        return newName;
    }
    public void setOldName(String oldName) 
    {
        this.oldName = oldName;
    }

    public String getOldName() 
    {
        return oldName;
    }
    public void setDocId(Long docId) 
    {
        this.docId = docId;
    }

    public Long getDocId() 
    {
        return docId;
    }
    public void setIsDel(String isDel) 
    {
        this.isDel = isDel;
    }

    public String getIsDel() 
    {
        return isDel;
    }
    public void setIsElk(String isElk) 
    {
        this.isElk = isElk;
    }

    public String getIsElk() 
    {
        return isElk;
    }
    public void setFileSize(String fileSize) 
    {
        this.fileSize = fileSize;
    }

    public String getFileSize() 
    {
        return fileSize;
    }
    public void setPdfurl(String pdfurl) 
    {
        this.pdfurl = pdfurl;
    }

    public String getPdfurl() 
    {
        return pdfurl;
    }

 
}
