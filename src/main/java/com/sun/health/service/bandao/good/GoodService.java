package com.sun.health.service.bandao.good;

import com.sun.health.core.comm.CheckSumAlgoType;
import com.sun.health.core.util.FileUtil;
import com.sun.health.core.util.SafeUtil;
import com.sun.health.core.util.StringUtil;
import com.sun.health.entity.bandao.cart.CartEntity;
import com.sun.health.entity.bandao.file.FileEntity;
import com.sun.health.entity.bandao.good.GoodEntity;
import com.sun.health.entity.bandao.good.GoodImageEntity;
import com.sun.health.repository.bandao.good.GoodImageRepository;
import com.sun.health.repository.bandao.good.GoodRepository;
import com.sun.health.service.AbstractService;
import com.sun.health.service.bandao.cart.CartService;
import com.sun.health.service.bandao.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodService extends AbstractService {

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private GoodImageRepository goodImageRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private FileService fileService;

    /**
     * 商品列表
     */
    public List<GoodEntity> getGoods() {
        return goodRepository.findAll();
    }

    /**
     * 商品列表
     *
     * @param goodIds 商品id列表
     */
    public List<GoodEntity> getByIds(List<Long> goodIds) {
        return goodRepository.findAllById(goodIds);
    }

    /**
     * 商品详情
     */
    public GoodEntity getById(Long goodId) {
        return goodRepository.getById(goodId);
    }

    /**
     * 保存商品图片
     */
    public boolean uploadGoodImage(Long goodId, MultipartFile file) {
        String filename = file.getOriginalFilename();
        byte[] content;

        try {
            InputStream is = file.getInputStream();
            content = FileUtil.inputStream2ByteArray(is);
        } catch (Exception e) {
            logger.error("upload good image error.goodId=" + goodId + ",fileName=" + filename, e);
            return false;
        }

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(filename);
        fileEntity.setContent(content);
        fileEntity.setUuid(StringUtil.rand32Str());
        fileEntity.setSize(content.length);
        try {
            fileEntity.setHash(SafeUtil.hash(content, CheckSumAlgoType.MD5.getName()));
        } catch (NoSuchAlgorithmException e) {
            logger.warn("generate file hash warn.fileName={}", filename);
        }
        fileService.save(fileEntity);


        GoodImageEntity goodImage = new GoodImageEntity();
        goodImage.setGoodId(goodId);
        goodImage.setContent(content);
        goodImage.setFileName(filename);
        goodImage.setFileId(fileEntity.getId());
        goodImageRepository.save(goodImage);


        return true;
    }

    /**
     * 获取指定商品图片
     */
    public GoodImageEntity findById(Long goodImageId) {
        return goodImageRepository.getById(goodImageId);
    }

    /**
     * 商品图片列表
     */
    public List<GoodImageEntity> getImagesById(Long goodId) {
        return goodImageRepository.findByGoodId(goodId);
    }


}
