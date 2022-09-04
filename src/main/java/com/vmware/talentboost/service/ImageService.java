package com.vmware.talentboost.service;

import com.vmware.talentboost.data.Image;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;


public interface ImageService {

    Image classifyImage(String imageUrl) throws URISyntaxException, IOException;
     Image getImage(int id);
     List<Image> getAllImages();

}
