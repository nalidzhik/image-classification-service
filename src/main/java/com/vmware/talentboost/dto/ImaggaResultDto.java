package com.vmware.talentboost.dto;

import java.util.List;

public class ImaggaResultDto {
    public List<ImaggaTagDto> getTags() {
        return tags;
    }

    public void setTags(List<ImaggaTagDto> tags) {
        this.tags = tags;
    }

    private List<ImaggaTagDto> tags;
}
