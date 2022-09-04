package com.vmware.talentboost.client;

import com.vmware.talentboost.dto.ImaggaTagsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

public class ImaggaClient {

    private ImaggaBasicAuthConfiguration imaggaBasicAuthConfiguration;

    public ImaggaClient(ImaggaBasicAuthConfiguration imaggaBasicAuthConfiguration) {
        this.imaggaBasicAuthConfiguration = imaggaBasicAuthConfiguration;
    }

    @RequestMapping
    public ImaggaTagsResponseDto classifyImage(String imageUrl) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("https", "api.imagga.com", "/v2/tags/",
                "image_url=" + imageUrl, null);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", getBasicAuthenticationHeader(imaggaBasicAuthConfiguration.getUsername(),
                imaggaBasicAuthConfiguration.getPassword()));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ImaggaTagsResponseDto> response = restTemplate.exchange(
                uri.toString(), HttpMethod.GET, requestEntity, ImaggaTagsResponseDto.class);
        System.out.println(response.getStatusCode());
        ImaggaTagsResponseDto dto = response.getBody();

        return dto;
    }

    private final String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
