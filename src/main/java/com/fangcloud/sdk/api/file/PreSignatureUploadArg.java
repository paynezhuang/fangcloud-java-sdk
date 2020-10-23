package com.fangcloud.sdk.api.file;

import com.fangcloud.sdk.YfyArg;
import com.fangcloud.sdk.exception.ClientValidationException;
import com.fangcloud.sdk.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PreSignatureUploadArg implements YfyArg {
    @JsonProperty("parent_id")
    private long parentId;
    private String name;
    @JsonProperty("upload_type")
    private String uploadType;
    @JsonProperty("is_covered")
    private boolean isCovered;

    public PreSignatureUploadArg(long parentId, String name, String uploadType, boolean isCovered) throws ClientValidationException {
        StringUtil.checkNameValid(name);
        this.parentId = parentId;
        this.name = name;
        this.uploadType = uploadType;
        this.isCovered = isCovered;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public boolean isCovered() {
        return isCovered;
    }

    public PreSignatureUploadArg setCovered(boolean covered) {
        isCovered = covered;
        return this;
    }
}
