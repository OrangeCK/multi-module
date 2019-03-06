package com.ck.multimoduledao.entity;

/**
 * @author ck
 * @date 2019/1/25 10:00
 * Description  :
 */
public class IndexQuery extends BaseForm {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 概要
     */
    private String outline;
    /**
     * 正文
     */
    private String content;
    /**
     * markdown格式的文本
     */
    private String markdownText;
    /**
     * 类别
     */
    private String category;
    /**
     * 上传附件信息
     */
    private UploadFile uploadFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMarkdownText() {
        return markdownText;
    }

    public void setMarkdownText(String markdownText) {
        this.markdownText = markdownText;
    }

    public UploadFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }
}
