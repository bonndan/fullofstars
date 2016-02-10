package com.github.bonndan.fullofstars.controller;

import com.github.bonndan.fullofstars.models.Blip;
import com.github.bonndan.fullofstars.models.User;
import com.github.bonndan.fullofstars.service.BlipFactory;
import com.github.bonndan.fullofstars.service.BlipRepository;
import com.github.bonndan.fullofstars.service.GitHubClientFactory;
import com.github.bonndan.fullofstars.service.UserRepository;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;
import org.kohsuke.github.PagedIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the api.
 * 
 * 
 * 
 */
@RestController
public class ApiController {
    
    private static final int STARS_PAGE_SIZE = 10;

    @Autowired
    OAuth2ClientContext oauth2ClientContext;
    
    @Autowired
    BlipRepository blipRepository;
    
    @Autowired
    UserRepository userRepository;
    

    @RequestMapping({"/api/user"})
    public Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return map;
    }

    @RequestMapping({"/api/stars"})
    public List<Blip> stars(Principal principal) throws IOException {
        GitHub gitHubClient = GitHubClientFactory.forPrincipalAndToken(
                principal, 
                oauth2ClientContext.getAccessToken()
        );
        PagedIterable<GHRepository> listStarredRepositories = gitHubClient.getMyself().listStarredRepositories().withPageSize(STARS_PAGE_SIZE);

        List<Blip> blips = new ArrayList<>();
        PagedIterator<GHRepository> _iterator = listStarredRepositories._iterator(STARS_PAGE_SIZE);
        while (_iterator.hasNext()) {
            blips.add(BlipFactory.fromGHRepository(_iterator.next()));
        }

        return blips;
    }
    
    
    @RequestMapping(path = {"/api/blips"}, method = RequestMethod.GET)
    public List<Blip> blips(Principal principal) {
        return blipRepository.findByUser(getUser(principal), new PageRequest(1, 20)).getContent();
    }
    
    private User getUser(Principal principal)
    {
        return userRepository.findByUsername(principal.getName());
    }
}
