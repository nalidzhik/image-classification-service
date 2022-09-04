package com.vmware.talentboost.service.impl;

import com.vmware.talentboost.client.ImaggaBasicAuthConfiguration;
import com.vmware.talentboost.client.ImaggaClient;
import com.vmware.talentboost.data.Image;
import com.vmware.talentboost.data.ImageTags;
import com.vmware.talentboost.data.Tag;
import com.vmware.talentboost.dto.ImaggaTagDto;
import com.vmware.talentboost.dto.ImaggaTagsResponseDto;
import com.vmware.talentboost.repository.ImageRepository;
import com.vmware.talentboost.repository.ImageTagRepository;
import com.vmware.talentboost.repository.TagRepository;
import com.vmware.talentboost.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ImageTagRepository imageTagRepository;

    @Autowired
    private ImaggaBasicAuthConfiguration imaggaBasicAuthConfiguration;

    @Override
    public Image classifyImage(String imageUrl) throws URISyntaxException, IOException {
        // Find image by url
        Optional<Image> optional = imageRepository.findByUrl(imageUrl);
        if(optional.isPresent()) {
            return optional.get();
        }

        ImaggaClient imaggaClient = new ImaggaClient(imaggaBasicAuthConfiguration );
        ImaggaTagsResponseDto dto = imaggaClient.classifyImage(imageUrl);

        Image image = new Image();
        image.setUrl(imageUrl);
        image.setAnalysedAt(LocalDateTime.now());

        URL urlToSet = new URL(imageUrl);
        final BufferedImage bufferedImage = ImageIO.read(urlToSet);
        image.setHeight(bufferedImage.getHeight());
        image.setWidth(bufferedImage.getWidth());

        List<ImageTags> imageTags = new ArrayList<>();
        for (ImaggaTagDto tagDto : dto.getResult().getTags()) {
            String tagName = tagDto.getTag().getEn();

            Tag tag = new Tag();
            tag.setName(tagName);
            tagRepository.saveAndFlush(tag);

            ImageTags imageTag = new ImageTags();
            imageTag.setImage(image);
            imageTag.setTag(tag);
            imageTag.setConfidence(tagDto.getConfidence());

            imageTags.add(imageTag);
        }
        image.setImageTags(imageTags);
        imageRepository.saveAndFlush(image);

        return image;
    }

    @Override
    public Image getImage(int id) {
        Optional<Image> optional = imageRepository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        return optional.get();
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
