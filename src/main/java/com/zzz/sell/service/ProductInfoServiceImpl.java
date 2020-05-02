package com.zzz.sell.service;

import com.zzz.sell.dao.ProductInfo;
import com.zzz.sell.dao.ProductInfoDAO;
import com.zzz.sell.dao.dto.CartDto;
import com.zzz.sell.enums.ProductInfoStatusEnums;
import com.zzz.sell.enums.ResultEnum;
import com.zzz.sell.exception.SellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 商品service实现
 * @author Jans_zhang
 * 2020/4/29 17:01
 */
@Service
public class ProductInfoServiceImpl implements  ProductInfoService{

    @Autowired
    private ProductInfoDAO productInfoDAO;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDAO.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDAO.findByProductStatus(ProductInfoStatusEnums.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDAO.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDAO.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtoList) {
          for (CartDto cartDto :cartDtoList){
              Optional<ProductInfo> byId = productInfoDAO.findById(cartDto.getProductId());
              if (!byId.isPresent()) throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
              Integer result = byId.get().getProductStock()+cartDto.getProductQuantity();
              byId.get().setProductStock(result);
              productInfoDAO.save(byId.get());
          }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
         for (CartDto cartDto:cartDtoList){
             Optional<ProductInfo> byId = productInfoDAO.findById(cartDto.getProductId());
             if (!byId.isPresent()) throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
             Integer result = byId.get().getProductStock()-cartDto.getProductQuantity();
            if (result <0) throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
             byId.get().setProductStock(result);
             productInfoDAO.save(byId.get());
         }
    }
}
