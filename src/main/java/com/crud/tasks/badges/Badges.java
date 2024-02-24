package com.crud.tasks.badges;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Badges {
    int votes;
   AttachmentsByType attachmentsByType;

    public int getVotes() {
        return votes;
    }

    public AttachmentsByType getAttachmentsByType() {
        return attachmentsByType;
    }
}
