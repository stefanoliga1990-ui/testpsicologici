package com.example.testpsicologici.service;

import com.example.testpsicologici.model.AmazonProductImage;

import java.util.List;
import java.util.Map;

public interface AmazonProductImageProvider {

    Map<String, AmazonProductImage> findImages(List<String> asins);
}
