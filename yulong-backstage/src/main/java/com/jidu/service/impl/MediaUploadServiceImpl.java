package com.jidu.service.impl;

import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.MediaFileMapper;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.media.MediaFile;
import com.jidu.service.MediaUploadService;
import com.jidu.utils.HlsVideoUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.*;
import java.util.*;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-09 14:09
 */
@Service
public class MediaUploadServiceImpl implements MediaUploadService {
    @Value("${FFmpeg.path}")
    String ffmpeg_path;
    static String upload_location = "/yulong/upload/";
    @Autowired
    private MediaFileMapper mediaFileMapper;

    static {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            upload_location = "c:/upload/";
        }
    }

    //得到文件所属目录路径
    private String getFileFolderPath(String fileMd5) {
        return upload_location + fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/";
    }

    //得到文件的路径
    private String getFilePath(String fileMd5, String fileExt) {
        return upload_location + fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/" + fileMd5 + "." + fileExt;
    }

    //得到块文件所属目录路径
    private String getChunkFileFolderPath(String fileMd5) {
        return upload_location + fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/chunk/";
    }

    /**
     * 文件上传前的注册，检查文件是否存在
     * 根据文件md5得到文件路径
     * 规则：
     * 一级目录：md5的第一个字符
     * 二级目录：md5的第二个字符
     * 三级目录：md5
     * 文件名：md5+文件扩展名
     *
     * @param fileMd5 文件md5值
     * @param fileExt 文件扩展名
     * @return 文件路径
     */
    public Result register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {

        //1  检查文件在磁盘上是否存在
        //文件所属目录的路径
        String fileFolderPath = this.getFileFolderPath(fileMd5);
        //文件的路径
        String filePath = this.getFilePath(fileMd5, fileExt);
        File file = new File(filePath);
        //文件是否存在
        boolean exists = file.exists();

        //2 检查文件信息在数据库中是否存在
        MediaFile mediaFile = mediaFileMapper.selectByPrimaryKey(fileMd5);
        if (exists && mediaFile != null) {
            //文件存在
            return new Result(100, "文件已存在", false);
        }
        //文件不存在时作一些准备工作，检查文件所在目录是否存在，如果不存在则创建
        File fileFolder = new File(fileFolderPath);
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }

        return new Result(ResultCode.SUCCESS);
    }

    //分块检查

    /**
     * @param fileMd5   文件md5
     * @param chunk     块的下标
     * @param chunkSize 块的大小
     * @return
     */
    public Result checkchunk(String fileMd5, Integer chunk, Integer chunkSize) {
        //检查分块文件是否存在
        //得到分块文件的所在目录
        String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
        //块文件
        File chunkFile = new File(chunkFileFolderPath + chunk);
        if (chunkFile.exists()) {
            //块文件存在
            return new Result(100, "块文件存在", true);
        } else {
            //块文件不存在
            return new Result(100, "块文件不存在", false);
        }

    }

    //上传分块
    public Result uploadchunk(MultipartFile file, String fileMd5, Integer chunk) {
        //检查分块目录，如果不存在则要自动创建
        //得到分块目录
        String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
        //得到分块文件路径
        String chunkFilePath = chunkFileFolderPath + chunk;

        File chunkFileFolder = new File(chunkFileFolderPath);
        //如果不存在则要自动创建
        if (!chunkFileFolder.exists()) {
            chunkFileFolder.mkdirs();
        }
        //得到上传文件的输入流
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(new File(chunkFilePath));
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Result(ResultCode.SUCCESS);

    }

    //合并文件
    public Result mergechunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {

        //1、合并所有分块
        //得到分块文件的属目录
        String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
        File chunkFileFolder = new File(chunkFileFolderPath);
        //分块文件列表
        File[] files = chunkFileFolder.listFiles();
        List<File> fileList = Arrays.asList(files);

        //创建一个合并文件
        String filePath = this.getFilePath(fileMd5, fileExt);
        File mergeFile = new File(filePath);

        //执行合并
        mergeFile = this.mergeFile(fileList, mergeFile);
        if (mergeFile == null) {
            //合并文件失败
            return new Result(100, "合并文件失败", false);
        }

        //2、校验文件的md5值是否和前端传入的md5一到
        boolean checkFileMd5 = this.checkFileMd5(mergeFile, fileMd5);
        if (!checkFileMd5) {
            //校验文件失败
            return new Result(100, "校验文件失败", false);
        }
        //3.将map4 文件转成m3u8
        String mp4_video_path = getFilePath(fileMd5, fileExt);
        //map4ToM3u8(mp4_video_path,fileMd5,fileName,fileExt,mimetype,fileSize);
        saveFile(mp4_video_path, fileMd5, fileName, fileExt, mimetype, fileSize, null);

        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public List<MediaFile> search() {
        return mediaFileMapper.selectAll();
    }

    @Override
    public Result add(String fileMd5, String videoName, String fileImg) {
        MediaFile mediaFile = mediaFileMapper.selectByPrimaryKey(fileMd5);
        if (mediaFile == null) {
            return new Result(201, "文件不存在", false);
        }
        mediaFile.setVideoName(videoName);
        mediaFile.setFileImg(fileImg);
        mediaFileMapper.updateByPrimaryKeySelective(mediaFile);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result delete(String fileId) {
        MediaFile mediaFile = mediaFileMapper.selectByPrimaryKey(fileId);
        if (mediaFile==null){
            return new Result(201,"文件不存在",false);

        }
        String fileUrl = mediaFile.getM3u8FileUrl();
        File file = new File(fileUrl);// 读取
        if(file.isFile()){
            file.delete();
        }
        mediaFileMapper.deleteByPrimaryKey(fileId);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public MediaFile find(String fileId) {
        return mediaFileMapper.selectByPrimaryKey(fileId);
    }


    /**
     * //4、将mp4生成m3u8和ts文件
     *
     * @param mp4_video_path mp4视频文件路径
     */
    private void map4ToM3u8(String mp4_video_path, String fileMd5, String fileName, String fileExt, String mimetype, long fileSize) {
        //m3u8_name文件名称
        String m3u8_name = fileMd5 + ".m3u8";
        //m3u8文件所在目录
        String m3u8folder_path = getFileFolderPath(fileMd5) + "hls/";
        HlsVideoUtil hlsVideoUtil = new HlsVideoUtil(ffmpeg_path, mp4_video_path, m3u8_name, m3u8folder_path);
        //生成m3u8和ts文件
        String tsResult = hlsVideoUtil.generateM3u8();
        if (tsResult == null || !tsResult.equals("success")) {
            return;
        }
        //处理成功
        //获取ts文件列表
        //List<String> ts_list = hlsVideoUtil.get_ts_list();
        //保存fileUrl（此url就是视频播放的相对路径）
        String m3u8FileUrl = m3u8folder_path + m3u8_name;
        //4、将文件的信息写入数据库
        saveFile(mp4_video_path, fileMd5, fileName, fileExt, mimetype, fileSize, m3u8FileUrl);


    }

    //校验文件
    private boolean checkFileMd5(File mergeFile, String md5) {

        try {
            //创建文件输入流
            FileInputStream inputStream = new FileInputStream(mergeFile);
            //得到文件的md5
            String md5Hex = DigestUtils.md5Hex(inputStream);
            //和传入的md5比较
            if (md5.equalsIgnoreCase(md5Hex)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;

    }

    //合并文件
    private File mergeFile(List<File> chunkFileList, File mergeFile) {
        try {
            //如果合并文件已存在则删除，否则创建新文件
            if (mergeFile.exists()) {
                mergeFile.delete();
            } else {
                //创建一个新文件
                mergeFile.createNewFile();
            }

            //对块文件进行排序
            Collections.sort(chunkFileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (Integer.parseInt(o1.getName()) > Integer.parseInt(o2.getName())) {
                        return 1;
                    }
                    return -1;

                }
            });
            //创建一个写对象
            RandomAccessFile raf_write = new RandomAccessFile(mergeFile, "rw");
            byte[] b = new byte[1024];
            for (File chunkFile : chunkFileList) {
                RandomAccessFile raf_read = new RandomAccessFile(chunkFile, "r");
                int len = -1;
                while ((len = raf_read.read(b)) != -1) {
                    raf_write.write(b, 0, len);
                }
                raf_read.close();
            }
            raf_write.close();
            return mergeFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveFile(String mp4_video_path, String fileMd5, String fileName, String fileExt, String mimetype, long fileSize, String m3u8FileUrl) {
        MediaFile mediaFile = new MediaFile();
        mediaFile.setFileId(fileMd5);
        mediaFile.setFileOriginalName(fileName);
        mediaFile.setFileName(fileMd5 + "." + fileExt);
        mp4_video_path=mp4_video_path.replaceAll("/yulong/","");
        mp4_video_path="http://39.96.95.40:8080/"+mp4_video_path;
        mediaFile.setFilePath(mp4_video_path);
        mediaFile.setFileSize(fileSize);
        mediaFile.setUploadTime(new Date());
        mediaFile.setMimeType(mimetype);
        mediaFile.setFileType(fileExt);
        mediaFile.setM3u8FileUrl("/yulong/"+mp4_video_path);
        //状态为上传成功
        mediaFile.setFileStatus("上传成功");
        mediaFile.setM3u8FileUrl(m3u8FileUrl);
        mediaFileMapper.insert(mediaFile);
    }
}
