package com.ea.ordermanagementapi.test;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ea.ordermanagementapi.mapper.ApiObjectMapper;
import com.ea.ordermanagementapi.util.rest.Response;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class AbstractRestService
{
    @Value("${test.baseUrl}")
    private String baseUrl;

    @Autowired
    private ApiObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;

    protected URI encodeURI(String url, String... params)
    {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(url).build().expand(params).encode();
        return uriComponents.toUri();
    }

    protected Response get(String path)
    {
        return restTemplate.getForObject(encodeURI(baseUrl + path), Response.class);
    }

    protected <T> T get(Class<T> clazz, String path)
    {
        return get(clazz, path, "");
    }

    protected <T> T get(Class<T> clazz, String path, String... params)
    {
        return toObject(restTemplate.getForObject(encodeURI(baseUrl + path, params), Response.class), clazz);
    }

    protected <T> T post(Class<T> clazz, String path, Object object)
    {
        return toObject(restTemplate.postForObject(encodeURI(baseUrl + path, ""), object, Response.class), clazz);
    }

    protected <T> T post(Class<T> clazz, String path, Object object, String... params)
    {
        return toObject(restTemplate.postForObject(encodeURI(baseUrl + path, params), object, Response.class), clazz);
    }


    public <T> T toObject(Response resp, Class<T> clazz)
    {
        if (null == resp)
            return null;
        try
        {
            return objectMapper.treeToValue(objectMapper.valueToTree(resp.getData()), clazz);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T toObject(Object resp, Class<T> clazz)
    {
        if (null == resp)
            return null;
        try
        {
            return objectMapper.treeToValue(objectMapper.valueToTree(resp), clazz);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> toList(Object map, Class<T> clazz) {
        if (null == map)
            return null;
        try
        {
            return (List<T>) objectMapper.readValue(objectMapper.writeValueAsString(map),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
