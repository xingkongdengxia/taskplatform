package com.magicube.framework.common.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 文件上传工具类
 *
 * @author justincai
 */
public class UploadUtil {

    private static final Log log = LogFactory.getLog(UploadUtil.class);

    /**
     * 表单字段常量
     */
    public static String FORM_FIELDS = "form_fields";
    /**
     * 文件域常量
     */
    public static String FILE_FIELDS = "file_fields";

    /**
     * 验证文件域返回错误信息常量
     */
    public static String VALIDATE_ERROR_INFO = "validate_error_info";

    /**
     * 上传文件错误信息常量
     */
    public static String UPLOADFILE_ERROR_INFO = "uploadfile_error_info";

    /**
     * 文件保存目录路径常量
     */
    public static String FILE_SAVE_PATH = "file_save_path";

    /**
     * 文件保存目录url常量
     */
    public static String FILE_SAVE_URL = "file_save_url";

    /**
     * 文件最终的url包括文件名常量
     */
    public static String FILE_URL = "file_url";

    // 最大文件大小
    private long maxSize = 1000000;

    // 定义允许上传的文件扩展名
    private Map<String, String> extMap = new HashMap<>();

    // 文件保存目录相对路径
    private String basePath = "upload";

    // 文件的目录名
    private String dirName = "images";

    // 上传临时路径
    private static final String TEMP_PATH = "/temp";
    private String tempPath = basePath + TEMP_PATH;

    // 若不指定则文件名默认为 yyyyMMddHHmmss_xyz
    private String fileName;

    // 文件保存目录路径
    private String savePath;

    // 文件保存目录url
    private String saveUrl;

    // 文件最终的url包括文件名
    private String fileUrl;

    public UploadUtil() {
        // 其中images,flashs,medias,files,对应文件夹名称,对应dirName
        // key文件夹名称
        // value该文件夹内可以上传文件的后缀名
        extMap.put("images", "gif,jpg,jpeg,png,bmp");
        extMap.put("flashs", "swf,flv");
        extMap.put("medias", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("files", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
    }

    /**
     * 文件上传
     *
     * @param request
     * @return infos VALIDATE_ERROR_INFO 验证文件域返回错误信息 UPLOADFILE_ERROR_INFO
     * 上传文件错误信息 FILE_SAVE_PATH savePath FILE_SAVE_URL saveUrl FILE_URL fileUrl
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> uploadFile(HttpServletRequest request) {

        Map<String, String> infos = new HashMap<>();
        // 验证
        String validateErrorInfo = this.validateFields(request);
        log.debug("验证信息：" + validateErrorInfo);
        infos.put(UploadUtil.VALIDATE_ERROR_INFO, validateErrorInfo);

        // 初始化表单元素
        Map<String, Object> fieldsMap = new HashMap<>();
        if (validateErrorInfo.equals("true")) {
            log.debug("初始化表单元素");
            fieldsMap = this.initFields(request);
        }
        // 上传
        List<FileItem> fiList = (List<FileItem>) fieldsMap.get(UploadUtil.FILE_FIELDS);
        log.debug("获得List<FileItem> fiList");
        if (fiList != null) {
            log.debug("开始处理List<FileItem> fiList");
            for (FileItem item : fiList) {
                String uploadfileErrorInfo = this.saveFile(item);
                log.debug("uploadfileErrorInfo：" + uploadfileErrorInfo);
                infos.put(UploadUtil.UPLOADFILE_ERROR_INFO, uploadfileErrorInfo);
            }

            infos.put(UploadUtil.FILE_SAVE_PATH, savePath);
            log.info("文件保存目录路径savePath:" + savePath);

            infos.put(UploadUtil.FILE_SAVE_URL, saveUrl);
            log.info("文件保存目录URL saveUrl:" + saveUrl);

            infos.put(UploadUtil.FILE_URL, fileUrl);
            log.info("文件最终的url包括文件名 fileUrl:" + fileUrl);
        }
        return infos;
    }

    /**
     * 上传验证,并初始化文件目录
     *
     * @param request
     */
    private String validateFields(HttpServletRequest request) {
        String errorInfo = "true";

        // boolean errorFlag = true;
        // 获取内容类型
        String contentType = request.getContentType();
        int contentLength = request.getContentLength();

        // 文件保存目录路径
        //savePath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/resources/" + basePath + "/";
        savePath = "/var/projectupload/framework/" + basePath + "/";
        log.debug("文件保存目录路径savePath:" + savePath);

        // 文件保存目录URL
        saveUrl = request.getContextPath() + "/resources/" + basePath + "/";
        log.debug("文件保存目录URL saveUrl:" + saveUrl);

        File uploadDir = new File(savePath);
        if (contentType == null || !contentType.startsWith("multipart")) {
            errorInfo = "请求不包含multipart/form-data流";
            log.error(errorInfo);
        } else if (maxSize < contentLength) {
            errorInfo = "上传文件大小超出文件最大大小[" + maxSize + "]";
            log.error(errorInfo);
        } else if (!ServletFileUpload.isMultipartContent(request)) {
            errorInfo = "请选择文件";
            log.error(errorInfo);
        } else if (!uploadDir.isDirectory()) {// 检查目录
            errorInfo = "上传目录[" + savePath + "]不存在";
            log.error(errorInfo);
        } else if (!uploadDir.canWrite()) {
            errorInfo = "上传目录[" + savePath + "]没有写权限";
            log.error(errorInfo);
        } else if (!extMap.containsKey(dirName)) {
            // TODO
            errorInfo = "目录名不正确";
            log.error(errorInfo + ":" + dirName);
        } else {
            // .../basePath/dirName/
            // 创建文件夹
            savePath += dirName + "/";
            saveUrl += dirName + "/";
            File saveDirFile = new File(savePath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
            log.debug("文件保存目录路径savePath:" + savePath);
            log.debug("文件保存目录URL saveUrl:" + saveUrl);

            // .../basePath/dirName/yyyyMMdd/
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String ymd = sdf.format(new Date());
            savePath += ymd + "/";
            saveUrl += ymd + "/";
            File dirFile = new File(savePath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            log.debug("文件保存目录路径savePath:" + savePath);
            log.debug("文件保存目录URL saveUrl:" + saveUrl);

            // 获取上传临时路径
            tempPath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/resources/" + tempPath + "/";
            File file = new File(tempPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            log.debug("上传临时路径tempPath:" + tempPath);

        }

        return errorInfo;
    }

    /**
     * 处理上传内容
     *
     * @param request
     * @param maxSize
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> initFields(HttpServletRequest request) {

        // 存储表单字段和非表单字段
        Map<String, Object> map = new HashMap<>();

        // 第一步：判断request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        
        // 第二步：解析request
        if (isMultipart) {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // 阀值,超过这个值才会写到临时目录,否则在内存中
            factory.setSizeThreshold(1024 * 1024 * 10);
            factory.setRepository(new File(tempPath));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setHeaderEncoding("UTF-8");

            // 最大上传限制
            upload.setSizeMax(maxSize);

            /* FileItem */
            List<FileItem> items = null;
            // Parse the request
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                log.error("文件上传异常！", e);
            }

            // 第3步：处理uploaded items
            if (items != null && items.size() > 0) {
                Iterator<FileItem> iter = items.iterator();
                // 文件域对象
                List<FileItem> list = new ArrayList<>();
                // 表单字段
                Map<String, String> fields = new HashMap<>();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    // 处理所有表单元素和文件域表单元素
                    if (item.isFormField()) { // 表单元素
                        String name = item.getFieldName();
                        String value = item.getString();
                        fields.put(name, value);
                        log.debug("表单元素" + name + ":" + value);
                    } else { // 文件域表单元素
                        list.add(item);
                        log.debug("文件域表单元素:" + item.getString());
                    }
                }
                map.put(FORM_FIELDS, fields);
                map.put(FILE_FIELDS, list);
            }
        }
        return map;
    }

    /**
     * 保存文件
     *
     * @param obj 要上传的文件域
     * @param file
     * @return
     */
    private String saveFile(FileItem item) {
        String error = "true";
        String thisfileName = item.getName();
        log.debug("thisfileName:" + thisfileName);
        
        String thisfileExt = thisfileName.substring(thisfileName.lastIndexOf(".") + 1).toLowerCase();
        log.debug("thisfileExt:" + thisfileExt);

        if (item.getSize() > maxSize) { // 检查文件大小
            error = "上传文件大小超过限制";
            log.error(error);
        } else if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(thisfileExt)) {// 检查扩展名
            error = "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。";
            log.error(error);
        } else {
            String newFileName;
            if ("".equals(thisfileName.trim())) {
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + thisfileExt;
            } else {
                newFileName = thisfileName + "." + thisfileExt;
            }
            // .../basePath/dirName/yyyyMMdd/yyyyMMddHHmmss_xxx.xxx
            fileUrl = saveUrl + newFileName;
            log.info("开始上传文件：" + fileUrl);
            try {
                File uploadedFile = new File(savePath, newFileName);

                item.write(uploadedFile);

                /*
				 * FileOutputStream fos = new FileOutputStream(uploadFile); // 文件全在内存中 if (item.isInMemory()) { fos.write(item.get()); } else { InputStream is = item.getInputStream(); byte[] buffer =
				 * new byte[1024]; int len; while ((len = is.read(buffer)) > 0) { fos.write(buffer, 0, len); } is.close(); } fos.close(); item.delete();
                 */
            } catch (IOException e) {
                System.out.println("上传失败了！！！");
                log.error("上传失败了！！！", e);
            } catch (Exception e) {
                log.error("上传失败了！！！", e);
            }
        }
        return error;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public Map<String, String> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, String> extMap) {
        this.extMap = extMap;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getSaveUrl() {
        return saveUrl;
    }

    public void setSaveUrl(String saveUrl) {
        this.saveUrl = saveUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}
