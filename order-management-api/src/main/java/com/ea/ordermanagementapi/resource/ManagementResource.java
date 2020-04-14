package com.ea.ordermanagementapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;

import com.ea.ordermanagementapi.util.rest.Resource;
import com.ea.ordermanagementapi.util.rest.Response;

@Resource
public class ManagementResource
{
    private Environment env;

    @Autowired
    public ManagementResource(Environment env)
    {
        this.env = env;
    }

    @GetMapping("/checkSanity")
    public Response checkSanity()
    {
        return new Response( "Ready to Rock!");
    }

    @GetMapping("/getActiveProfiles")
    public Response checkProfile()
    {
        return new Response(env.getActiveProfiles());
    }


}
