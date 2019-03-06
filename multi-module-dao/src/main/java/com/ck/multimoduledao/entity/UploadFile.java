package com.ck.multimoduledao.entity;

/**
 * @author ck
 * @date 2019/1/4 11:09
 * Description  :
 */
public class UploadFile extends BaseForm {
    /**
     * 主键
     */
    private Long id;
    /**
     * 来源参考表
     */
    private String refTable;
    /**
     * 来源参考Id
     */
    private Long refId;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件大小
     */
    private String fileSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getRefTable() {
        return refTable;
    }

    public void setRefTable(String refTable) {
        this.refTable = refTable;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }
}
