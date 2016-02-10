package com.github.bonndan.fullofstars.service;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.extras.OkHttpConnector;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class GitHubClientFactory {

    public static GitHub forPrincipalAndToken(Principal principal, OAuth2AccessToken accessToken) throws IOException {
        
        if (principal == null)
            throw new IllegalArgumentException("Principal is null.");
        
        if (accessToken == null)
            throw new IllegalArgumentException("AccessToken is null");
        
        String tempDir = System.getProperty("java.io.tmpdir") + "/" + principal.getName();
        Cache cache = new Cache(new File(tempDir), 10 * 1024 * 1024); // 10MB cache
        GitHub gitHub = new GitHubBuilder().withOAuthToken(accessToken.getValue(), principal.getName())
                .withConnector(new OkHttpConnector(new OkUrlFactory(new OkHttpClient().setCache(cache))))
                .build();
        return gitHub;
    }
}
