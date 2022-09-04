package com.vmware.talentboost.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.vmware.talentboost.client.ImaggaClient;
import com.vmware.talentboost.data.Image;
import com.vmware.talentboost.data.ImageTags;
import com.vmware.talentboost.dto.ErrorDto;
import com.vmware.talentboost.dto.ImageRequestDto;
import com.vmware.talentboost.dto.ImageResponseDto;
import com.vmware.talentboost.service.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(final ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ImageResponseDto classifyImage(@RequestBody final ImageRequestDto request) throws URISyntaxException, IOException {
        Image image = imageService.classifyImage(request.getUrl());

        return mapImageToDto(image);
    }


    @GetMapping("{id}")
    public ResponseEntity<ImageResponseDto> get(@PathVariable final Integer id) {
        Image image = imageService.getImage(id);
        if (image == null) {
            throw new NoSuchElementException(
                    String.format("No task with ID %d found.", id));
        }

        ImageResponseDto dto = mapImageToDto(image);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public List<ImageResponseDto> getAll() {
        return mapImagesToDto(imageService.getAllImages());
    }

    private ImageResponseDto mapImageToDto(Image image) {
        ImageResponseDto dto = new ImageResponseDto();
        dto.setId(image.getId());
        dto.setUrl(image.getUrl());
        dto.setAnalysedAt(image.getAnalysedAt());
        dto.setHeight(image.getHeight());
        dto.setWidth(image.getWidth());

        Map<String, Double> map = new HashMap<>();
        for (ImageTags imageTags : image.getImageTags()) {
            map.put(imageTags.getTag().getName(), imageTags.getConfidence());
        }

        dto.setTags(map);

        return dto;
    }

    private List<ImageResponseDto> mapImagesToDto(List<Image> images) {
        List<ImageResponseDto> dtos = new ArrayList<>();

        for (Image image : images) {
            dtos.add(mapImageToDto(image));
        }

        return dtos;
    }
}
