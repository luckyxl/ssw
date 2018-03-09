package com.aas.ssw.common.util;

import com.aas.ssw.common.component.Constant;
import com.github.tobato.fastdfs.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * fastdfs工具类
 *
 * @author xl
 */
@Component
@ConditionalOnProperty(name = "fdfs.enabled")
public class FastDFSClientUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastDFSClientUtil.class);


    private static FastFileStorageClient fastFileStorageClient;

    private static FdfsWebServer fdfsWebServer;

    /**
     * 上传文件
     * @param file 文件
     * @return 文件访问路径
     */
    public static String uploadFile(MultipartFile file) {
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            return getResAccessUrl(storePath);
        } catch (IOException e) {
            LOGGER.error("FASTDFS上传文件异常！", e);
            return null;
        }
    }

    /**
     * 删除文件
     * @param fileUrl 文件访问地址
     * @return
     */
    public static String deleteFile(String fileUrl) {
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            return Constant.SUCCESS;
        } catch (FdfsUnsupportStorePathException e) {
            LOGGER.error("FASTDFS删除文件异常！",e);
            return Constant.ERROR;
        }
    }

    /**
     * 返回文件完整地址
     * @param storePath
     * @return
     */
    private static String getResAccessUrl(StorePath storePath) {
        String fileUrl = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
        return fileUrl;
    }


    @Resource
    public void setFastFileStorageClient(FastFileStorageClient fastFileStorageClient) {
        FastDFSClientUtil.fastFileStorageClient = fastFileStorageClient;
    }

    @Resource
    public void setFdfsWebServer(FdfsWebServer fdfsWebServer) {
        FastDFSClientUtil.fdfsWebServer = fdfsWebServer;
    }
}
