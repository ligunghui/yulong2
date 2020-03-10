package com.jidu.pojo.media;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-09 15:03
 */
@Data
@Table(name = "media_file")
public class MediaFile {
    @Id
    private String fileId;
    private String fileOriginalName;
    private String fileName;
    private String filePath;
    private long fileSize;
    private Date uploadTime;
    private String fileType;
    private String fileStatus;
    private String mimeType;
    private  String m3u8FileUrl;


}
