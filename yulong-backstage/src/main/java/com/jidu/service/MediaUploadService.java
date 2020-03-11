package com.jidu.service;

import com.jidu.entity.Result;
import org.springframework.web.multipart.MultipartFile;

public interface MediaUploadService {
    Result register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt);

    Result checkchunk(String fileMd5, Integer chunk, Integer chunkSize);

    Result uploadchunk(MultipartFile file, String fileMd5, Integer chunk);

    Result mergechunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt);
}
