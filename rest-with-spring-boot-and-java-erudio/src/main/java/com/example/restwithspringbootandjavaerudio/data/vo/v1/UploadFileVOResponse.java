package com.example.restwithspringbootandjavaerudio.data.vo.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileVOResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 8027814789005494643L;

    private String filename;
    private String fileDownloadUri;
    private String filetype;
    private long size;

}
